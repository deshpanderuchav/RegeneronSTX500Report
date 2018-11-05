package com.liconic.db;

import com.liconic.binding.regeneron.ObjectFactory;
import com.liconic.binding.regeneron.Command;
import com.liconic.binding.regeneron.Exceptions;
import com.liconic.binding.regeneron.Plates;
import com.liconic.binding.regeneron.STXRequest;
import com.liconic.binding.regeneron.Plate;
import com.liconic.binding.regeneron.Tubes;
import com.liconic.binding.regeneron.Tube;
import com.liconic.binding.sys.Job;
import com.liconic.binding.sys.Task;
import com.liconic.report.Report;
import com.liconic.utils.BufferPosition;
import com.liconic.utils.ReportPartition;
import java.io.FileOutputStream;
import java.io.StringWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

public class DBKIWIModule {

    private DBKIWIConnection DB;
    private Report report = null;

    public DBKIWIModule(DBKIWIConnection DB, Report report) {
        this.DB = DB;
        this.report = report;
    }

      public synchronized void DoneReportPickTubes(int idJob, int idTask) {

        Connection conn = null;
        PreparedStatement stat = null;
        ResultSet rs = null;
        String SQLVal = "";

        STXRequest request = null;

        String JobId = "";
        String Status = "Exported";
        String Partition = "";
        int idUser = 0;

        ObjectFactory of = new ObjectFactory();

        try {

            conn = DB.getConnection();

            SQLVal = "SELECT job_id, id_user, user_name "
                    + "FROM jobs, users "
                    + "WHERE id_job=? AND link_user=id_user ";

            stat = conn.prepareStatement(SQLVal);

            stat.setInt(1, idJob);

            rs = stat.executeQuery();

            while (rs.next()) {

                JobId = rs.getString("job_id");

                idUser = rs.getInt("id_user");

                break;
            }

            SQLVal = "SELECT partition_id "
                    + "FROM partition, user_link_partition "
                    + "WHERE link_partition=id_partition AND link_user=?";

            stat = conn.prepareStatement(SQLVal);
            stat.setInt(1, idUser);

            rs = stat.executeQuery();

            while (rs.next()) {

                Partition = rs.getString("partition_id");
                break;
            }

            rs.close();
            stat.close();

            request = new STXRequest();

            Command command = new Command();
            command.setCmd("PickTubes");
            command.setID(JobId);
            command.setStatus(of.createCommandStatus(Status));

            request.setCommand(command);

            Exceptions exceptions = new Exceptions();

            SQLVal = "SELECT src_tube_bcr, src_x, src_y, trg_plate_bcr, trg_x, trg_y, "
                    + "src_trg_result, is_done, src_trg_info, "
                    + "id_plate, plate_bcr, tube_x, tube_y, tube_ya, tube_bcr "
                    + "FROM tasks "
                    + "INNER JOIN task_src_link_trg ON task_src_link_trg.link_task=id_task "
                    + "INNER JOIN src_trg_link_csv ON link_src_trg=id_src_link_trg "
                    + "INNER JOIN task_csv_sequence ON link_csv=id_csv_sequence "
                    + "LEFT JOIN tube_link_task ON tube_link_task.link_task=id_src_link_trg "
                    + "LEFT JOIN tubes ON tube_link_task.link_tube=id_tube "
                    + "LEFT JOIN tubes_link_plate ON tubes_link_plate.link_tube=id_tube "
                    + "LEFT JOIN plate_link_tube_pos ON link_pos=id_link_tube_pos "
                    + "LEFT JOIN tube_positions ON id_tube_position=link_tube_pos "
                    + "LEFT JOIN plates ON link_plate=id_plate "
                    + "WHERE id_task=? "
                    + "GROUP BY src_tube_bcr, src_x, src_y, trg_plate_bcr, trg_x, trg_y, "
                    + "src_trg_result, is_done, src_trg_info, id_plate, plate_bcr, tube_x, tube_y, tube_ya, tube_bcr "
                    + "ORDER BY trg_plate_bcr, tube_x, tube_y";

            stat = conn.prepareStatement(SQLVal);
            stat.setInt(1, idTask);

            rs = stat.executeQuery();

            Plates plates = new Plates();

            request.setPlates(of.createSTXRequestPlates(plates));

            Plate plate = null;
            Tubes tubes = null;

            String plateGroupId = "";

            String trg_x, trg_y, src_tube_bcr, Tube_x, Tube_y, TubeBarcode;

            while (rs.next()) {

                if (rs.getInt("is_done") == 1) {

                    if (!plateGroupId.equals(rs.getString("trg_plate_bcr"))) {

                        plateGroupId = rs.getString("trg_plate_bcr");

                        plate = new Plate();

                        plates.getPlate().add(plate);

                        String plateGroup = rs.getString("trg_plate_bcr");

                        plateGroup = plateGroup.replace("[Plate ", "");
                        plateGroup = plateGroup.replace("]", "");

                        try {
                            plate.setPlateOrder(Integer.valueOf(plateGroup));
                        } catch (Exception E) {
                            System.out.println("REPORT Error get plate group: " + E.getMessage());
                        }

                        String barcode = "";

                        if (rs.getString("plate_bcr") != null) {
                            barcode = rs.getString("plate_bcr");
                        }

                        plate.setPlateBarcode(of.createPlatePlateBarcode(barcode));

                        tubes = new Tubes();

                        plate.setTubes(tubes);

                    }

                    Tube tube = new Tube();

                    tube.setPX(of.createTubePX(rs.getByte("tube_x")));
                    tube.setPY(of.createTubePY(rs.getString("tube_ya")));

                    TubeBarcode = "";

                    if (rs.getString("tube_bcr") != null) {
                        TubeBarcode = rs.getString("tube_bcr");
                    }

                    tube.setBarcode(TubeBarcode);

                    tubes.getTube().add(tube);

                    try {

                        src_tube_bcr = "";

                        if (rs.getString("src_tube_bcr") != null) {
                            src_tube_bcr = rs.getString("src_tube_bcr");
                        }

                        trg_x = "";

                        if (rs.getString("trg_x") != null) {
                            trg_x = rs.getString("trg_x");
                        }

                        trg_y = "";

                        if (rs.getString("trg_y") != null) {
                            trg_y = rs.getString("trg_y");
                        }

                        Tube_x = "";

                        if (rs.getString("tube_x") != null) {
                            Tube_x = rs.getString("tube_x");
                        }

                        Tube_y = "";

                        if (rs.getString("tube_ya") != null) {
                            Tube_y = rs.getString("tube_ya");
                        }

                        if (!src_tube_bcr.equals(TubeBarcode)) {
                            System.out.println("REPORT Warning: Requested Barcode = " + src_tube_bcr + ", Result = " + TubeBarcode);
                        }

                        if (!trg_x.equals(Tube_x)) {
                            System.out.println("REPORT Warning: Requested X = " + Tube_x + ", Result = " + Tube_x);
                        }

                        if (!trg_y.equals(Tube_y)) {
                            System.out.println("REPORT Warning: Requested Y = " + trg_y + ", Result = " + Tube_y);
                        }

                    } catch (Exception E) {
                        System.out.println("REPORT Error of compsrison: " + E.getMessage());
                    }

                } else {

                    com.liconic.binding.regeneron.Exception exception = new com.liconic.binding.regeneron.Exception();

                    exception.setErrCode(Byte.valueOf("5"));

                    String Barcode = "";

                    if (rs.getString("src_tube_bcr") != null) {
                        Barcode = rs.getString("src_tube_bcr");
                    }

                    exception.setErrBarcode(of.createExceptionErrBarcode(Barcode));

                    String ErrorInfo = "";

                    if (rs.getString("src_trg_info") != null) {
                        ErrorInfo = rs.getString("src_trg_info");
                    }

                    exception.setErrMsg(ErrorInfo);

                    exceptions.getException().add(exception);

                }

            }

            if (!exceptions.getException().isEmpty()) {
                request.setExceptions(of.createSTXRequestExceptions(exceptions));
            }

            rs.close();
            stat.close();

            conn.close();

            SaveReport(request, Partition, false);

        } catch (Exception E) {
            System.out.println("REPORT Error DoneReportPickTubes: " + E.getMessage());
        }

    }

    public synchronized void CancelReportPickTubes(int idJob) {

        Connection conn = null;
        PreparedStatement stat = null;
        ResultSet rs = null;
        String SQLVal = "";

        STXRequest request = null;

        String JobId = "";
        String Status = "Cancelled";
        String Partition = "";
        int idUser = 0;
        String UserName = "";
        Timestamp timeStamp = null;

        ObjectFactory of = new ObjectFactory();

        try {

            conn = DB.getConnection();

            SQLVal = "SELECT user_login, status_time, id_user, job_id "
                    + "FROM users, tasks, task_link_status, task_status, jobs "
                    + "WHERE link_job=id_job AND id_job=? AND link_task=id_task AND status_user=id_user AND "
                    + "link_status=id_task_status AND task_status.task_status=?";

            stat = conn.prepareStatement(SQLVal);

            stat.setInt(1, idJob);
            stat.setString(2, "Canceled");

            rs = stat.executeQuery();

            while (rs.next()) {

                JobId = rs.getString("job_id");
                UserName = rs.getString("user_login");
                timeStamp = rs.getTimestamp("status_time");
                break;
            }

            if (UserName.isEmpty()) {
                System.out.println("REPORT WARNING User is not found for Cancelling job id=" + JobId);
            }

            SQLVal = "SELECT id_user "
                    + "FROM jobs, users "
                    + "WHERE id_job=? AND link_user=id_user ";

            stat = conn.prepareStatement(SQLVal);

            stat.setInt(1, idJob);

            rs = stat.executeQuery();

            while (rs.next()) {

                idUser = rs.getInt("id_user");

                break;
            }

            SQLVal = "SELECT partition_id "
                    + "FROM partition, user_link_partition "
                    + "WHERE link_partition=id_partition AND link_user=?";

            stat = conn.prepareStatement(SQLVal);
            stat.setInt(1, idUser);

            rs = stat.executeQuery();

            while (rs.next()) {

                Partition = rs.getString("partition_id");
                break;
            }

            rs.close();
            stat.close();

            request = new STXRequest();

            Command command = new Command();
            command.setCmd("PickTubes");
            command.setID(JobId);
            command.setStatus(of.createCommandStatus(Status));
            command.setCancelledBy(of.createCommandCancelledBy(UserName));

            try {

                GregorianCalendar c = new GregorianCalendar();
                c.setTime(timeStamp);
                XMLGregorianCalendar date = DatatypeFactory.newInstance().newXMLGregorianCalendar(c);
                command.setDate(of.createCommandDate(date));

            } catch (Exception E) {

            }

            request.setCommand(command);

            rs.close();
            stat.close();

            conn.close();

            SaveReport(request, Partition, false);

        } catch (Exception E) {
            System.out.println("REPORT Error DoneReportPickTubes: " + E.getMessage());
        }

    }

    public synchronized void CompletedReportPickTubes(int idJob) {

        Connection conn = null;
        PreparedStatement stat = null;
        ResultSet rs = null;
        String SQLVal = "";

        STXRequest request = null;

        String JobId = "";
        String Status = "Completed";
        String Partition = "";
        int idUser = 0;
        String UserName = "";
        Timestamp timeStamp = null;
        int idTask = 0;

        ObjectFactory of = new ObjectFactory();

        try {

            conn = DB.getConnection();

            SQLVal = "SELECT job_id, link_user FROM jobs WHERE id_job=?";

            stat = conn.prepareStatement(SQLVal);

            stat.setInt(1, idJob);

            rs = stat.executeQuery();

            while (rs.next()) {
                JobId = rs.getString("job_id");
                idUser = rs.getInt("link_user");
                System.out.println("REPORT job_id: " + rs.getString("job_id"));
                break;
            }

            stat.close();
            rs.close();

            SQLVal = "SELECT MAX(id_task) "
                    + "FROM tasks, task_link_status, task_status "
                    + "WHERE link_task=id_task AND link_status=id_task_status AND "
                    + "UPPER(task_status)=? AND link_job=?";

            stat = conn.prepareStatement(SQLVal);

            stat.setString(1, "DONE");
            stat.setInt(2, idJob);

            rs = stat.executeQuery();

            while (rs.next()) {
                idTask = rs.getInt(1);
                break;
            }

            SQLVal = "SELECT user_login, status_time, id_user "
                    + "FROM tasks, task_link_status, task_status, users "
                    + "WHERE link_task=id_task AND link_status=id_task_status AND "
                    + "status_user=id_user AND UPPER(task_status)=? AND id_task=?";

            stat = conn.prepareStatement(SQLVal);

            stat.setInt(2, idTask);
            stat.setString(1, "WAITING");

            rs = stat.executeQuery();

            while (rs.next()) {

                UserName = rs.getString("user_login");
                break;
            }

            SQLVal = "SELECT status_time "
                    + "FROM tasks, task_link_status, task_status "
                    + "WHERE link_task=id_task AND link_status=id_task_status AND "
                    + "UPPER(task_status)=? AND id_task=?";

            stat = conn.prepareStatement(SQLVal);

            stat.setInt(2, idTask);
            stat.setString(1, "DONE");

            rs = stat.executeQuery();

            while (rs.next()) {

                timeStamp = rs.getTimestamp("status_time");
                break;
            }

            SQLVal = "SELECT partition_id "
                    + "FROM partition, user_link_partition "
                    + "WHERE link_partition=id_partition AND link_user=?";

            stat = conn.prepareStatement(SQLVal);
            stat.setInt(1, idUser);

            rs = stat.executeQuery();

            while (rs.next()) {

                Partition = rs.getString("partition_id");
                break;
            }

            rs.close();
            stat.close();

            request = new STXRequest();

            Command command = new Command();
            command.setCmd("PickTubes");
            command.setID(JobId);
            command.setStatus(of.createCommandStatus(Status));
            command.setReceiver(of.createCommandReceiver(UserName));

            try {
                GregorianCalendar c = new GregorianCalendar();
                c.setTime(timeStamp);
                XMLGregorianCalendar date = DatatypeFactory.newInstance().newXMLGregorianCalendar(c);
                command.setDate(of.createCommandDate(date));
            } catch (Exception E) {

            }

            request.setCommand(command);

            rs.close();
            stat.close();

            conn.close();

            SaveReport(request, Partition, false);

        } catch (Exception E) {
            System.out.println("REPORT Error CompletedReportPickTubes: " + E.getMessage());
        }

    }

    public synchronized void FaileddReportPickTubes(int idJob) {

        Connection conn = null;
        PreparedStatement stat = null;
        ResultSet rs = null;
        String SQLVal = "";

        STXRequest request = null;

        String JobId = "";
        String Status = "Failed";
        String Partition = "";
        int idUser = 0;
        Timestamp timeStamp = null;
        int idTask = 0;

        ObjectFactory of = new ObjectFactory();

        try {

            conn = DB.getConnection();

            SQLVal = "SELECT job_id, link_user FROM jobs WHERE id_job=?";

            stat = conn.prepareStatement(SQLVal);

            stat.setInt(1, idJob);

            rs = stat.executeQuery();

            while (rs.next()) {
                JobId = rs.getString("job_id");
                idUser = rs.getInt("link_user");
                System.out.println("REPORT job_id: " + rs.getString("job_id"));
                break;
            }

            stat.close();
            rs.close();

            SQLVal = "SELECT MAX(id_task) "
                    + "FROM tasks, task_link_status, task_status "
                    + "WHERE link_task=id_task AND link_status=id_task_status AND "
                    + "UPPER(task_status)=? AND link_job=?";

            stat = conn.prepareStatement(SQLVal);

            stat.setString(1, "ERROR");
            stat.setInt(2, idJob);

            rs = stat.executeQuery();

            while (rs.next()) {
                idTask = rs.getInt(1);
                break;
            }

            SQLVal = "SELECT status_time "
                    + "FROM task_link_status, task_status "
                    + "WHERE link_task=? AND link_status=id_task_status AND UPPER(task_status)=?";

            stat = conn.prepareStatement(SQLVal);

            stat.setInt(1, idTask);
            stat.setString(2, "ERROR");

            rs = stat.executeQuery();

            while (rs.next()) {

                timeStamp = rs.getTimestamp("status_time");
                break;
            }

            SQLVal = "SELECT partition_id "
                    + "FROM partition, user_link_partition "
                    + "WHERE link_partition=id_partition AND link_user=?";

            stat = conn.prepareStatement(SQLVal);
            stat.setInt(1, idUser);

            rs = stat.executeQuery();

            while (rs.next()) {

                Partition = rs.getString("partition_id");
                break;
            }

            rs.close();
            stat.close();

            request = new STXRequest();

            Command command = new Command();
            command.setCmd("PickTubes");
            command.setID(JobId);
            command.setStatus(of.createCommandStatus(Status));

            try {
                GregorianCalendar c = new GregorianCalendar();
                c.setTime(timeStamp);
                XMLGregorianCalendar date = DatatypeFactory.newInstance().newXMLGregorianCalendar(c);
                command.setDate(of.createCommandDate(date));
            } catch (Exception E) {

            }

            request.setCommand(command);

            Exceptions exceptions = new Exceptions();

            com.liconic.binding.regeneron.Exception exception = new com.liconic.binding.regeneron.Exception();
            exception.setErrCode(Byte.valueOf("8"));

            exception.setErrMsg("Equipment Failure");

            exceptions.getException().add(exception);

            request.setExceptions(of.createSTXRequestExceptions(exceptions));

            rs.close();
            stat.close();

            conn.close();

            SaveReport(request, Partition, false);

        } catch (Exception E) {
            System.out.println("REPORT Error CompletedReportPickTubes: " + E.getMessage());
        }

    }

    private void SaveReport(STXRequest request, String partition, boolean IsImport) {

        try {
            StringWriter sw = new StringWriter();
            DateFormat df = new SimpleDateFormat("dd_MM_yyyy_HHmmss");
            String reportDate = df.format(new Date());

            JAXBContext context = JAXBContext.newInstance(com.liconic.binding.regeneron.ObjectFactory.class);

            Marshaller m = context.createMarshaller();

            m.marshal(request, sw);

            System.out.println("REPORT Export Report:\r" + sw.toString());
           
            String localPath = "";
            String remotePath = "";

            String localPathImport = "", remotePathImport = "";
            String localPathExport = "", remotePathExport = "";

            for (ReportPartition rp : report.getListReportPartition()) {

                if (rp.getParttiton().equals("Storage")) {
                    localPathImport = rp.getLocalPathImport();
                    remotePathImport = rp.getRemotePathImport();

                    localPathExport = rp.getLocalPathExport();
                    remotePathExport = rp.getRemotePathExport();

                    break;
                }
            }

            if ((localPathImport.isEmpty()) && (remotePathImport.isEmpty()) && (localPathExport.isEmpty()) && (remotePathExport.isEmpty())) {
                System.out.println("REPORT Error of creating the report: Can not find paths for partition: " + partition);
                return;
            }

            if (IsImport) {
                localPath = localPathImport;
                remotePath = remotePathImport;
            } else {
                localPath = localPathExport;
                remotePath = remotePathExport;

            }

            boolean saveError = false;

            try {

                System.out.println("REPORT Save remote report: " + remotePath + "\\" + reportDate + ".xml");

                FileOutputStream fosRemote = new FileOutputStream(remotePath + "\\" + reportDate + ".xml");

                m.marshal(request, fosRemote);

                fosRemote.flush();
                fosRemote.close();

            } catch (Exception Ef) {
                System.out.println("REPORT ERROR Save remote report: " + Ef.getMessage());
                Ef.printStackTrace();
                saveError = true;
            }

            try {

                FileOutputStream fosLocal;

                if (!saveError) {
                    System.out.println("REPORT Save local report: " + localPath + "\\" + reportDate + ".xml");
                    fosLocal = new FileOutputStream(localPath + "\\" + reportDate + ".xml");
                } else {
                    System.out.println("REPORT Save local report: " + localPath + "\\error_" + reportDate + ".xml");
                    fosLocal = new FileOutputStream(localPath + "\\error_" + reportDate + ".xml");
                }

                m.marshal(request, fosLocal);
                fosLocal.flush();
                fosLocal.close();

            } catch (Exception E) {
                System.out.println("REPORT ERROR Save local report: " + E.getMessage());
                E.printStackTrace();
            }

        } catch (Exception E) {
            System.out.println("REPORT Error of Create Report: " + E.getMessage());
            E.printStackTrace();
        }
    }

    public Job getJobByTaskId(int idtask) {

        Connection conn = null;
        PreparedStatement stat = null;
        ResultSet rs = null;
        String SQLVal = "";

        Job job = null;

        try {

            conn = DB.getConnection();

            SQLVal = "SELECT id_job, job_types.job_type, task_types.task_type "
                    + "FROM tasks, task_types, jobs, job_types "
                    + "WHERE tasks.task_type=id_task_type AND jobs.job_type=id_job_type "
                    + "AND id_job=link_job AND id_task=? "
                    + "GROUP BY id_job, job_types.job_type, task_types.task_type ";

            stat = conn.prepareStatement(SQLVal);

            stat.setInt(1, idtask);

            rs = stat.executeQuery();

            while (rs.next()) {

                job = new Job();

                job.setId(rs.getInt("id_job"));
                job.setName(rs.getString(2));

                Task task = new Task();

                task.setId(idtask);
                task.setName(rs.getString(3));

                job.getTasks().add(task);

                break;

            }

            stat.close();
            rs.close();

            conn.close();

        } catch (Exception E) {
            System.out.println("REPORT Error get Job: " + E.getMessage());
        }

        return job;

    }

    public Job getJobById(int id) {

        Connection conn = null;
        PreparedStatement stat = null;
        ResultSet rs = null;
        String SQLVal = "";

        Job job = null;

        try {

            conn = DB.getConnection();

            SQLVal = "SELECT id_job, job_types.job_type "
                    + "FROM jobs, job_types "
                    + "WHERE id_job=? AND jobs.job_type=id_job_type "
                    + "GROUP BY id_job, job_types.job_type ";

            stat = conn.prepareStatement(SQLVal);

            stat.setInt(1, id);

            rs = stat.executeQuery();

            while (rs.next()) {

                job = new Job();

                job.setId(rs.getInt("id_job"));
                job.setName(rs.getString(2));

                break;

            }

            stat.close();
            rs.close();

            conn.close();

        } catch (Exception E) {
            System.out.println("REPORT Error get Job: " + E.getMessage());
        }

        return job;

    }

    public synchronized void CompletedReportImport(int idJob, int idTask) {

        Connection conn = null;
        PreparedStatement stat = null;
        ResultSet rs = null;
        String SQLVal = "";

        STXRequest request = null;

        String Status = "Done";
        String Partition = "";
        int idUser = 0;
        String UserName = "";

        ObjectFactory of = new ObjectFactory();

        try {

            conn = DB.getConnection();

            SQLVal = "SELECT task_status "
                    + "FROM task_link_status, task_status "
                    + "WHERE link_task=? AND link_status=id_task_status AND "
                    + "id_task_link_status=( "
                    + "    SELECT MAX(id_task_link_status) "
                    + "    FROM task_link_status "
                    + "    WHERE link_task=? "
                    + ")";

            stat = conn.prepareStatement(SQLVal);

            stat.setInt(1, idTask);
            stat.setInt(2, idTask);

            rs = stat.executeQuery();

            boolean isReport = true;

            while (rs.next()) {

                if (!rs.getString("task_status").equals("Done")) {
                    isReport = false;
                    System.out.println("REPORT Task Status: " + rs.getString("task_status"));
                }
            }

            stat.close();
            rs.close();

            if (!isReport) {
                conn.close();
                System.out.println("REPORT No report need");
                return;
            }

            stat.close();
            rs.close();

            SQLVal = "SELECT id_user, user_login "
                    + "FROM users, jobs "
                    + "WHERE id_job=? AND jobs.link_user=id_user";

            stat = conn.prepareStatement(SQLVal);

            stat.setInt(1, idJob);

            rs = stat.executeQuery();

            while (rs.next()) {

                UserName = rs.getString("user_login");
                idUser = rs.getInt("id_user");
                break;
            }

            SQLVal = "SELECT partition_id "
                    + "FROM partition, user_link_partition "
                    + "WHERE link_partition=id_partition AND link_user=?";

            stat = conn.prepareStatement(SQLVal);
            stat.setInt(1, idUser);

            rs = stat.executeQuery();

            while (rs.next()) {

                Partition = rs.getString("partition_id");
                break;
            }

            rs.close();
            stat.close();

            request = new STXRequest();

            Command command = new Command();
            command.setID(String.valueOf(idJob));
            command.setStatus(of.createCommandStatus(Status));
            command.setUser(of.createCommandUser(UserName));

            request.setCommand(command);

            int idPlate = 0;
            String PlateBCR = "";

            SQLVal = "SELECT id_plate, plate_bcr "
                    + "FROM plates, plate_link_task, task_src_link_trg "
                    + "WHERE task_src_link_trg.link_task=? AND plate_link_task.link_task=id_src_link_trg AND "
                    + "link_plate=id_plate";

            stat = conn.prepareStatement(SQLVal);

            stat.setInt(1, idTask);

            rs = stat.executeQuery();

            while (rs.next()) {

                idPlate = rs.getInt("id_plate");
                PlateBCR = rs.getString("plate_bcr");

            }

            rs.close();
            stat.close();

            Plates plates = new Plates();

            Plate plate = new Plate();

            plate.setPlateBarcode(of.createPlatePlateBarcode(PlateBCR));

            plates.getPlate().add(plate);

            SQLVal = "SELECT tube_x, tube_ya, tube_bcr, volume "
                    + "FROM plate_link_tube_pos, tube_positions, tubes_link_plate, tubes "
                    + "WHERE link_plate=? AND link_tube_pos=id_tube_position AND "
                    + "link_pos=id_link_tube_pos AND link_tube=id_tube";

            stat = conn.prepareStatement(SQLVal);
            stat.setInt(1, idPlate);

            rs = stat.executeQuery();

            Tubes tubes = new Tubes();

            while (rs.next()) {

                Tube tube = new Tube();

                tube.setBarcode(rs.getString("tube_bcr"));
                     if (rs.getString("volume") != null) {
                tube.setVolume(of.createTubeVolume(Float.valueOf(rs.getString(4))));
                }
                tube.setPX(of.createTubePX(rs.getByte("tube_x")));
                tube.setPY(of.createTubePY(rs.getString("tube_ya")));

                tubes.getTube().add(tube);

            }

            stat.close();
            rs.close();

            plate.setTubes(tubes);

            request.setPlates(of.createSTXRequestPlates(plates));

            conn.close();

            SaveReport(request, Partition, true);

        } catch (Exception E) {
            System.out.println("REPORT Error CompletedReportImport: " + E.getMessage());
        }

    }

    public synchronized String GetUserEmailByTaskId(int id) {

        Connection conn = null;
        PreparedStatement stat = null;
        ResultSet rs = null;
        String SQLVal = "";

        String UserEmail = "";

        try {

            conn = DB.getConnection();

            SQLVal = "SELECT user_e_mail "
                    + "FROM users, jobs, tasks "
                    + "WHERE id_task=? AND link_job=id_job AND link_user=id_user ";

            stat = conn.prepareStatement(SQLVal);

            stat.setInt(1, id);

            rs = stat.executeQuery();

            while (rs.next()) {

                UserEmail = rs.getString("user_e_mail");
                break;
            }

            stat.close();
            rs.close();

            conn.close();

        } catch (Exception E) {
            System.out.println("REPORT Error GetUserEmailByTaskId: " + E.getMessage());
        }

        return UserEmail;
    }

    public synchronized String GetPlateBarcodeByTaskId(int id) {

        Connection conn = null;
        PreparedStatement stat = null;
        ResultSet rs = null;
        String SQLVal = "";

        String BC = "";

        try {

            conn = DB.getConnection();

            SQLVal = "SELECT plate_bcr "
                    + "FROM task_src_link_trg, plate_link_task, plates "
                    + "WHERE task_src_link_trg.link_task=? AND plate_link_task.link_task=id_src_link_trg AND "
                    + "link_plate=id_plate";

            stat = conn.prepareStatement(SQLVal);

            stat.setInt(1, id);

            rs = stat.executeQuery();

            while (rs.next()) {

                BC = rs.getString("plate_bcr");
                break;
            }

            stat.close();
            rs.close();

            conn.close();

        } catch (Exception E) {
            System.out.println("REPORT Error GetUserEmailByTaskId: " + E.getMessage());
        }

        return BC;
    }

    public synchronized List<String> GetAdminEmails() {

        Connection conn = null;
        PreparedStatement stat = null;
        ResultSet rs = null;
        String SQLVal = "";

        List<String> AdminEmails = new ArrayList<>();

        try {

            conn = DB.getConnection();

            SQLVal = "SELECT user_e_mail\n"
                    + "FROM users\n"
                    + "WHERE user_role=1 ";

            stat = conn.prepareStatement(SQLVal);

            rs = stat.executeQuery();

            while (rs.next()) {

                if ((rs.getString("user_e_mail") != null) && (!rs.getString("user_e_mail").isEmpty())) {
                    AdminEmails.add(rs.getString("user_e_mail"));
                }

            }

            stat.close();
            rs.close();

            conn.close();

        } catch (Exception E) {
            System.out.println("REPORT Error GetAdminEmails: " + E.getMessage());
        }

        return AdminEmails;
    }

    public synchronized BufferPosition getBufferPosition(int id) {

        Connection conn = null;
        PreparedStatement stat = null;
        ResultSet rs = null;
        String SQLVal = "";

        BufferPosition bufferPosition = null;

        try {

            conn = DB.getConnection();

            SQLVal = "SELECT device_id, cassette_id, level_cassette_number, id_plate, plate_bcr "
                    + "FROM levels "
                    + "INNER JOIN cassettes ON level_cassette=id_cassette "
                    + "INNER JOIN devices ON id_device=cassette_device "
                    + "INNER JOIN plate_link_level ON link_level=id_level "
                    + "INNER JOIN plates ON link_plate=id_plate "
                    + "WHERE id_level=? ";

            stat = conn.prepareStatement(SQLVal);

            stat.setInt(1, id);

            rs = stat.executeQuery();

            while (rs.next()) {

                String Device = "";

                if (rs.getString("device_id") != null) {
                    Device = rs.getString("device_id");
                }

                String barcode = "";

                if (rs.getString("plate_bcr") != null) {
                    barcode = rs.getString("plate_bcr");
                }

                bufferPosition = new BufferPosition(Device, String.valueOf(rs.getInt("cassette_id")), String.valueOf(rs.getInt("level_cassette_number")), barcode);

                break;
            }

            stat.close();
            rs.close();

            conn.close();

        } catch (Exception E) {
            System.out.println("REPORT Error GetAdminEmails: " + E.getMessage());
        }

        return bufferPosition;

    }

}
