package com.liconic.report;

import com.liconic.binding.conffiles.Parameter;
import com.liconic.binding.conffiles.ParameterGroup;
import com.liconic.binding.conffiles.Parameters;
import com.liconic.utils.ReportPartition;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

public class AppListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {

        ServletContext context = sce.getServletContext();

        String ParamFile = "";

        String KIWIDBDriver = "";

        String KIWIDBUser = "";
        String KIWIDBPswd = ""; //       
        String KIWIConnectString = "";

        String SMTPAddress = "";
        String SMTPPort = "";
        String SMTPUser = "";
        String EMMailFrom = "";

        List<ReportPartition> ListReportPartition = new ArrayList<>();

        ParamFile = context.getInitParameter("ConfigFile");

        System.out.println("REPORT read config: Reading ConfFile:" + ParamFile);

        try {

            JAXBContext jaxbContent = JAXBContext.newInstance(com.liconic.binding.conffiles.ObjectFactory.class);

            Unmarshaller um = jaxbContent.createUnmarshaller();

            FileInputStream fis = new FileInputStream(ParamFile);

            Parameters params = (Parameters) um.unmarshal(fis);

            for (int i = 0; i < params.getParameterGroup().size(); i++) {
                ParameterGroup paramGroup = (ParameterGroup) params.getParameterGroup().get(i);

                if (paramGroup.getName().equals("KIWI Database")) {

                    for (int j = 0; j < paramGroup.getParameter().size(); j++) {
                        Parameter param = (Parameter) paramGroup.getParameter().get(j);

                        if (param.getName().equals("KIWIDBDriver")) {
                            KIWIDBDriver = param.getValue();
                        } else {
                            if (param.getName().equals("KIWIDBUser")) {
                                KIWIDBUser = param.getValue();
                            } else {
                                if (param.getName().equals("KIWIDBPswd")) {
                                    KIWIDBPswd = param.getValue();
                                } else {
                                    if (param.getName().equals("KIWIConnectString")) {
                                        KIWIConnectString = param.getValue();
                                    }
                                }
                            }
                        }
                    }

                } else if (paramGroup.getName().equals("Report")) {
                   
                    for (ParameterGroup reportGroup : paramGroup.getParameterGroup()) {

                        ReportPartition reportPartition = null;

                        for (Parameter param : reportGroup.getParameter()) {

                            if (param.getName().equals("Partition")) {
                                reportPartition = new ReportPartition(param.getValue(), "", "", "", "");
                            } else {
                                if (param.getName().equals("LocalPathImport")) {
                                    reportPartition.setLocalPathImport(param.getValue());
                                 //   System.out.println(param.getValue());
                                } else {
                                    if (param.getName().equals("LocalPathExport")) {
                                        reportPartition.setLocalPathExport(param.getValue());
                                      //    System.out.println(param.getValue());
                                    } else {
                                        if (param.getName().equals("RemotePathImport")) {
                                            reportPartition.setRemotePathImport(param.getValue());
                                             // System.out.println(param.getValue());
                                        } else {
                                            if (param.getName().equals("RemotePathExport")) {
                                                reportPartition.setRemotePathExport(param.getValue());
                                                 // System.out.println(param.getValue());
                                            }
                                        }
                                    }
                                }
                            }
                        }

                        if (reportPartition != null) {
                            ListReportPartition.add(reportPartition);
                        }

                    }

                } else if (paramGroup.getName().equals("E-mail")) {

                    for (ParameterGroup reportGroup : paramGroup.getParameterGroup()) {

                        for (Parameter param : reportGroup.getParameter()) {

                            if (param.getName().equals("SMTPAddress")) {
                                SMTPAddress = param.getValue();
                            } else {
                                if (param.getName().equals("SMTPPort")) {
                                    SMTPPort = param.getValue();
                                } else {
                                    if (param.getName().equals("SMTPUser")) {
                                        SMTPUser = param.getValue();
                                    } else {
                                        if (param.getName().equals("E-mailFrom")) {
                                            EMMailFrom = param.getValue();
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        System.out.println("REPORT read config: STX Database Settings --------------------");
        System.out.println("REPORT read config: KIWIDBDriver: " + KIWIDBDriver);
        System.out.println("REPORT read config: KIWIDBUser: " + KIWIDBUser);
        System.out.println("REPORT read config: KIWIDBPswd: " + KIWIDBPswd);
        System.out.println("REPORT read config: KIWIConnectString: " + KIWIConnectString);

        System.out.println("");
        System.out.println("");

        Report report = Report.getInstance();

        for (ReportPartition rp : ListReportPartition) {
            System.out.println(rp.getParttiton() + "  -  " + rp.getLocalPathImport() + " - " + rp.getRemotePathImport());
            System.out.println(rp.getParttiton() + "  -  " + rp.getLocalPathExport() + " - " + rp.getRemotePathExport());
        }

        report.setListReportPartition(ListReportPartition);

        report.setKIWIDBDriver(KIWIDBDriver);
        report.setKIWIDBPath(KIWIConnectString);
        report.setKIWIDBPswd(KIWIDBPswd);
        report.setKIWIDBUser(KIWIDBUser);

        report.setEMMailFrom(EMMailFrom);
        report.setSMTPAddress(SMTPAddress);
        report.setSMTPPort(SMTPPort);
        report.setSMTPUser(SMTPUser);

        context.setAttribute("DBPath", KIWIConnectString);
        context.setAttribute("KIWIReport", report);

        report.Init();

    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }
}
