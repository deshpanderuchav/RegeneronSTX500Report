package com.liconic.report;

import com.liconic.binding.sys.Job;
import com.liconic.db.DBKIWIConnection;
import com.liconic.db.DBKIWIModule;
import com.liconic.utils.BufferPosition;
import com.liconic.utils.ReportPartition;
import java.io.BufferedInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.DefaultHttpClient;

public class Report {

    private static Report instance;

    private DBKIWIConnection KIWIConn = null;

    private DBKIWIModule DMKIWI = null;

    private List<ReportPartition> ListReportPartition = null;

    List LabwareList = null;

    String STXAddress = "";
    Integer STXPort = 0;

    String KIWIDBDriver = "org.firebirdsql.jdbc.FBDriver";

    String KIWIDBPath = "";

    String KIWIDBUser = "";
    String KIWIDBPswd = "";

    String SMTPAddress = "";
    String SMTPPort = "";
    String SMTPUser = "";
    String EMMailFrom = "";

    public void setKIWIDBDriver(String value) {
        KIWIDBDriver = value;
    }

    public void setKIWIDBPath(String value) {
        KIWIDBPath = value;
    }

    public void setKIWIDBUser(String value) {
        KIWIDBUser = value;
    }

    public void setKIWIDBPswd(String value) {
        KIWIDBPswd = value;
    }

    public List<ReportPartition> getListReportPartition() {
        return ListReportPartition;
    }

    public void setListReportPartition(List<ReportPartition> ListReportPartition) {
        this.ListReportPartition = ListReportPartition;
    }

    public static Report getInstance() {

        if (instance == null) {
            instance = new Report();
        }

        return instance;

    }

    public void Init() {
        try {

            KIWIConn = new DBKIWIConnection(KIWIDBDriver, KIWIDBPath, KIWIDBUser, KIWIDBPswd);

            DMKIWI = new DBKIWIModule(KIWIConn, this);

            System.out.println("REPORT KIWI Database connection created");

            CheckEmptyspace checkEmptyspace = new CheckEmptyspace(KIWIDBPath, KIWIDBUser, KIWIDBPswd, SMTPAddress, SMTPPort, SMTPUser, EMMailFrom);

            checkEmptyspace.RunCheck();

        } catch (Exception E) {
            System.out.println("REPORT Eerror: Create KIWI Database connection - " + E.getMessage());
        }

    }

    public DBKIWIModule getDMKIWI() {
        return DMKIWI;
    }

    public void DoneReport(int id) {

        Job job = DMKIWI.getJobByTaskId(id);

        if (job != null) {

            if (job.getName().equals("Job File")) {

                System.out.println("REPORT Create Done Report for Job Id=" + job.getId() + ", Name=" + job.getName() + ", Task Id=" + id + ", Name=" + job.getTasks().get(0).getName());

                DMKIWI.DoneReportPickTubes(job.getId(), id);

            } else {

            }

        } else {
            System.out.println("REPORT Can not find Job by Task Id=" + id);
        }

    }

    public void CancelReport(int id) {

        Job job = DMKIWI.getJobById(id);

        if (job != null) {

            if (job.getName().equals("Job File")) {

                System.out.println("REPORT Create Cancel Report for Job Id=" + job.getId() + ", Name=" + job.getName());

                DMKIWI.CancelReportPickTubes(id);

            } else {

            }

        } else {
            System.out.println("REPORT Can not find Job by Task Id=" + id);
        }

    }

    public void CompletedJobReport(int id) {

        Job job = DMKIWI.getJobById(id);

        if (job != null) {

            if (job.getName().equals("Job File")) {

                System.out.println("REPORT Create Complete Report for Job Id=" + job.getId() + ", Name=" + job.getName());

                DMKIWI.CompletedReportPickTubes(id);

            } else {

            }

        } else {
            System.out.println("REPORT Can not find Job by Task Id=" + id);
        }

    }

    public void FailedJobReport(int id) {

        Job job = DMKIWI.getJobById(id);

        if (job != null) {

            if (job.getName().equals("Job File")) {

                System.out.println("REPORT Create Failed Report for Job Id=" + job.getId() + ", Name=" + job.getName());

                DMKIWI.FaileddReportPickTubes(id);

            } else {

            }

        } else {
            System.out.println("REPORT Can not find Job by Task Id=" + id);
        }

    }

    public void CompletedTaskReport(int idTask) {

        Job job = DMKIWI.getJobByTaskId(idTask);

        if (job != null) {

            if (job.getName().equals("Job File")) {

                System.out.println("REPORT Create Complete Report for Job Id=" + job.getId() + ", Name=" + job.getName());

                DMKIWI.CompletedReportPickTubes(idTask);

            } else if (job.getName().equals("Import")) {
                DMKIWI.CompletedReportImport(job.getId(), idTask);
            }

        } else {
            System.out.println("REPORT Can not find Job by Task Id=" + idTask);
        }

    }

    public void ImportTaskFailSendEMail(int idTask, String Error) {

        String e_mail = DMKIWI.GetUserEmailByTaskId(idTask);

        if (e_mail.isEmpty()) {
            System.out.println("REPORT Can not find User E-mail fot failed import Task Id=" + idTask);
            return;
        }

        String Barcode = DMKIWI.GetPlateBarcodeByTaskId(idTask);

        SendEmailFailedImport(Barcode, e_mail, Error);

    }

    public String getSMTPAddress() {
        return SMTPAddress;
    }

    public void setSMTPAddress(String SMTPAddress) {
        this.SMTPAddress = SMTPAddress;
    }

    public String getSMTPPort() {
        return SMTPPort;
    }

    public void setSMTPPort(String SMTPPort) {
        this.SMTPPort = SMTPPort;
    }

    public String getSMTPUser() {
        return SMTPUser;
    }

    public void setSMTPUser(String SMTPUser) {
        this.SMTPUser = SMTPUser;
    }

    public String getEMMailFrom() {
        return EMMailFrom;
    }

    public void setEMMailFrom(String EMMailFrom) {
        this.EMMailFrom = EMMailFrom;
    }

    public void ImportFromBufferUnknownPlates(int IdTask, List<Integer> list) {

        String UserEmail;

        List<String> adminMails = new ArrayList<>();

        UserEmail = DMKIWI.GetUserEmailByTaskId(IdTask);

        adminMails = DMKIWI.GetAdminEmails();

        List<BufferPosition> bufferList = new ArrayList<>();

        for (Integer i : list) {

            int lvlId = i;

            BufferPosition bufferPosition = DMKIWI.getBufferPosition(lvlId);

            if (bufferPosition != null) {
                bufferList.add(bufferPosition);
            }

        }

        if (!bufferList.isEmpty()) {

            if (!UserEmail.isEmpty()) {
                SendEmail(bufferList, UserEmail);
            }
        }

        for (String admEmail : adminMails) {
            SendEmail(bufferList, admEmail);
        }
    }

    private void SendEmail(List<BufferPosition> bufferList, String email) {

        System.out.println("REPORT LIST OF UNKNOWN PLATES IN BUFFER Send e-mail to " + email + " - ");

        String msg = "";

        for (BufferPosition bp : bufferList) {

            if (msg.isEmpty()) {
                msg = bp.getDeviceID() + "\n";
            }

            msg = msg + "Cassette: " + String.valueOf(bp.getCassetteId()) + ", Level: " + String.valueOf(bp.getLivelId());

            if (!bp.getBarcode().isEmpty()) {
                msg = msg + ", " + bp.getBarcode();
            }

            msg = msg + "\n";

        }

        try {
            Properties props = new Properties();
            props.setProperty("mail.transport.protocol", "smtp");
            props.setProperty("mail.smtp.port", SMTPPort);
            props.setProperty("mail.host", SMTPAddress);
            props.setProperty("mail.user", SMTPUser);

            Session mailSession = Session.getDefaultInstance(props, null);
            Transport transport = mailSession.getTransport();

            MimeMessage message = new MimeMessage(mailSession);
            message.setSubject("List of unknown plates");
            message.setContent(msg, "text/plain");
            message.setFrom(new InternetAddress(SMTPUser));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(email));

            transport.connect();

            transport.sendMessage(message, message.getRecipients(Message.RecipientType.TO));
            transport.close();

        } catch (Exception E) {
            System.out.println("REPORT CHECKEMPTYSPACE Send e-mail to " + email + " ERROR: " + E.getMessage());
        }
    }

    private void SendEmailFailedImport(String Barcode, String email, String error) {

        System.out.println("REPORT Failed Import Barcode=" + Barcode + " Send e-mail to " + email + ", error: " + error);

        String msg = "Import failed, barcode: " + Barcode + ", error: " + error;

        try {
            Properties props = new Properties();
            props.setProperty("mail.transport.protocol", "smtp");
            props.setProperty("mail.smtp.port", SMTPPort);
            props.setProperty("mail.host", SMTPAddress);
            props.setProperty("mail.user", SMTPUser);

            Session mailSession = Session.getDefaultInstance(props, null);
            Transport transport = mailSession.getTransport();

            MimeMessage message = new MimeMessage(mailSession);
            message.setSubject("Import Failed");
            message.setContent(msg, "text/plain");
            message.setFrom(new InternetAddress(SMTPUser));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(email));

            transport.connect();

            transport.sendMessage(message, message.getRecipients(Message.RecipientType.TO));
            transport.close();

        } catch (Exception E) {
            System.out.println("REPORT CHECKEMPTYSPACE Send e-mail to " + email + " ERROR: " + E.getMessage());
        }
    }
    
    public  void sendReports(String report, String uri){
    String response;
    System.out.println("Report to Moderna: " + report);
    System.out.println("Report sent to : " + uri);

     DefaultHttpClient httpClient = new DefaultHttpClient();
    try {
      HttpPost postRequest = new HttpPost(uri);
      postRequest.setHeader("Content-Type", "application/json");
      HttpEntity entity = new ByteArrayEntity(report.getBytes("UTF-8"));
      postRequest.setEntity(entity);
      HttpResponse httpResponse = httpClient.execute(postRequest);
  
      byte[] buffer = new byte[1024];
      if (httpResponse != null) {
        InputStream inputStream = httpResponse.getEntity().getContent();
        try {
          int bytesRead = 0;
          BufferedInputStream bis = new BufferedInputStream(inputStream);
          StringBuilder stringBuilder = new StringBuilder();
          while ((bytesRead = bis.read(buffer)) != -1) {
          stringBuilder.append(new String(buffer, 0, bytesRead));
          }
         
         response  = stringBuilder.toString();
       //  System.out.println("Response from Moderna: " + response);
        } catch (Exception e) {
          e.printStackTrace();
        } finally {
          try { inputStream.close(); } catch (Exception ignore) {}
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      httpClient.getConnectionManager().shutdown();
    }
}

}
