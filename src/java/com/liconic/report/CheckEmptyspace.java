package com.liconic.report;

import com.liconic.utils.Partition;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class CheckEmptyspace {

    private String KIWIDBPath;

    private String KIWIDBUser;
    private String KIWIDBPswd;

    private String SMTPAddress;
    private String SMTPPort;
    private String SMTPUser;
    private String EMMailFrom;

    public CheckEmptyspace(String KIWIDBPath, String KIWIDBUser, String KIWIDBPswd, String SMTPAddress, String SMTPPort, String SMTPUser, String EMMailFrom) {
        this.KIWIDBPath = KIWIDBPath;
        this.KIWIDBUser = KIWIDBUser;
        this.KIWIDBPswd = KIWIDBPswd;
        this.SMTPAddress = SMTPAddress;
        this.SMTPPort = SMTPPort;
        this.SMTPUser = SMTPUser;
        this.EMMailFrom = EMMailFrom;
    }

    public void RunCheck() {

        if (KIWIDBPath.isEmpty()) {
            System.out.println("REPORT CHECKEMPTYSPACE can not run chek partitio free sapce KIWIDBPath is empty");
            return;
        }

        if (KIWIDBUser.isEmpty()) {
            System.out.println("REPORT CHECKEMPTYSPACE can not run chek partitio free sapce KIWIDBUser is empty");
            return;
        }

        if (KIWIDBPswd.isEmpty()) {
            System.out.println("REPORT CHECKEMPTYSPACE can not run chek partitio free sapce KIWIDBPswd is empty");
            return;
        }

        if (SMTPAddress.isEmpty()) {
            System.out.println("REPORT CHECKEMPTYSPACE can not run chek partitio free sapce SMTPAddress is empty");
            return;
        }

        if (SMTPPort.isEmpty()) {
            System.out.println("REPORT CHECKEMPTYSPACE can not run chek partitio free sapce SMTPPort is empty");
            return;
        }

        if (SMTPUser.isEmpty()) {
            System.out.println("REPORT CHECKEMPTYSPACE can not run chek partitio free sapce SMTPUser is empty");
            return;
        }

        if (EMMailFrom.isEmpty()) {
            System.out.println("REPORT CHECKEMPTYSPACE can not run chek partitio free sapce EMMailFrom is empty");
            return;
        }

        new Thread(new Runnable() {

            @Override
            public void run() {

                while (true) {

                    try {
                        Thread.currentThread().sleep(20000);
                    } catch (Exception E) {
                    }

                    check();

                    try {
                        Thread.currentThread().sleep(120 * 60 * 1000);
                    } catch (Exception E) {
                    }
                }

            }

        }).start();

    }

    public void check() {

        try {

            System.out.println("REPORT CHECK for Empty Space");

            List<String> emailsList = new ArrayList<>();

            List<Partition> partitonList = new ArrayList<>();

            Connection connection = DriverManager.getConnection(KIWIDBPath, KIWIDBUser, KIWIDBPswd);

            String SQLVal = "SELECT user_e_mail "
                    + "FROM users "
                    + "WHERE user_role=1";

            PreparedStatement stat = connection.prepareStatement(SQLVal);

            ResultSet rs = stat.executeQuery();

            while (rs.next()) {

                if ((rs.getString("user_e_mail") != null) && (!rs.getString("user_e_mail").isEmpty())) {
                    emailsList.add(rs.getString("user_e_mail"));
                }
            }

            rs.close();
            stat.close();

            if (emailsList.isEmpty()) {
                System.out.println("REPORT CHECKEMPTYSPACE no e-mails");
                connection.close();
                return;
            }

            // find how empty coud be 
            SQLVal = "SELECT setting_value FROM settings WHERE setting_name=?";

            stat = connection.prepareStatement(SQLVal);

            stat.setString(1, "Partition empty space");

            rs = stat.executeQuery();

            int fullness = 0;

            while (rs.next()) {

                try {
                    fullness = Integer.valueOf(rs.getString("setting_value"));
                } catch (Exception E) {
                    System.out.println("REPORT CHECKEMPTYSPACE get fullness error: " + E.getMessage());
                }
            }

            stat.close();
            rs.close();

            if (fullness == 0) {
                System.out.println("REPORT CHECKEMPTYSPACE get fullness is 0");
                connection.close();
                return;
            }

            // find list of partitions in the Store
            SQLVal = "SELECT id_partition, partition_id "
                    + "FROM partition, cassette_link_partition, cassettes, devices, "
                    + "param_link_device, device_params "
                    + "WHERE link_partition=id_partition AND link_cassette=id_cassette AND "
                    + "cassette_device=id_device AND link_device=id_device AND "
                    + "link_param=id_device_params AND param_name=? "
                    + "GROUP BY id_partition, partition_id "
                    + "ORDER BY id_partition, partition_id";

            stat = connection.prepareStatement(SQLVal);

            stat.setString(1, "Storage");

            rs = stat.executeQuery();

            while (rs.next()) {

                partitonList.add(new Partition(rs.getInt("id_partition"), rs.getString("partition_id")));

            }

            rs.close();
            stat.close();

            for (Partition part : partitonList) {

                int amountLevels = 0;
                int freeLevels = 0;

                SQLVal = "SELECT count(id_level) "
                        + "FROM levels, cassettes, cassette_link_partition "
                        + "WHERE level_cassette=id_cassette AND link_cassette=id_cassette AND link_partition=?";

                stat = connection.prepareStatement(SQLVal);

                stat.setInt(1, part.getId());

                rs = stat.executeQuery();

                while (rs.next()) {
                    amountLevels = rs.getInt(1);
                }

                rs.close();
                stat.close();

                SQLVal = "SELECT COUNT(DISTINCT id_level) "
                        + "FROM levels L, cassettes, cassette_link_partition "
                        + "WHERE level_cassette=id_cassette AND link_cassette=id_cassette AND link_partition=? "
                        + "AND NOT EXISTS "
                        + "( "
                        + "    SELECT link_level FROM plate_link_level "
                        + "    WHERE link_level=l.id_level "
                        + ")";

                stat = connection.prepareStatement(SQLVal);

                stat.setInt(1, part.getId());

                rs = stat.executeQuery();

                while (rs.next()) {
                    freeLevels = rs.getInt(1);
                }

                rs.close();
                stat.close();

                if ((freeLevels * 100 / amountLevels) <= fullness) {
                    part.setIsAlarm(true);
                }

            }

            connection.close();

            for (Partition part : partitonList) {

                if (part.isIsAlarm()) {

                    for (String address : emailsList) {

                        SendEmail(part.getPartitionId(), fullness, address);

                    }

                }

            }

        } catch (Exception E) {
            System.out.println("REPORT CHECKEMPTYSPACE ERROR: " + E.getMessage());
        }

    }

    private void SendEmail(String partition, int size, String email) {

        System.out.println("REPORT CHECKEMPTYSPACE Send e-mail to " + email + " - " + "Free space in partition " + partition + " below " + String.valueOf(size) + "%");

        try {
            Properties props = new Properties();
            props.setProperty("mail.transport.protocol", "smtp");
            props.setProperty("mail.smtp.port", SMTPPort); 
            props.setProperty("mail.host", SMTPAddress);
            props.setProperty("mail.user", SMTPUser);

            Session mailSession = Session.getDefaultInstance(props, null);
            Transport transport = mailSession.getTransport();

            MimeMessage message = new MimeMessage(mailSession);
            message.setSubject("Free space warning");
            message.setContent("Free space in partition " + partition + " below " + String.valueOf(size) + "%", "text/plain");
            message.setFrom(new InternetAddress(SMTPUser));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(email));

            transport.connect();

            transport.sendMessage(message, message.getRecipients(Message.RecipientType.TO));
            transport.close();
        } catch (Exception E) {
            System.out.println("REPORT CHECKEMPTYSPACE Send e-mail to " + email + " ERROR: " + E.getMessage());
        }
    }

}
