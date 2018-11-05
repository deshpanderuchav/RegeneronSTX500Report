package com.liconic.restful;

import com.liconic.binding.sys.Job;
import com.liconic.binding.sys.Property;
import com.liconic.binding.sys.Sys;
import com.liconic.binding.sys.Task;
import com.liconic.report.Report;
import java.io.StringWriter;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;

@Path("completed")
public class CompletedJob {

    @Context
    private UriInfo context;

    private Report report;

    public CompletedJob() {
        report = Report.getInstance();
    }

    @GET
    @Produces("application/xml")
    public String getXml() {
        throw new UnsupportedOperationException();
    }

    @POST
    @Consumes("application/xml")
    public void putXml(Sys sys) {
        try {

            StringWriter sw = new StringWriter();

            JAXBContext context = JAXBContext.newInstance(com.liconic.binding.sys.ObjectFactory.class);

            Marshaller m = context.createMarshaller();
            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

            m.marshal(sys, sw);

            System.out.println("REPORT Complete Request:\r" + sw.toString());

            if (sys.getJobs() != null) {

                for (Job job : sys.getJobs().getValue().getJob()) {

                    if (job.getTasks().isEmpty()) {
                        report.CompletedJobReport(job.getId());
                    } else {

                        for (Task task : job.getTasks()) {

                            boolean isError = false;
                            String result = "";

                            for (Property prop : task.getProps()) {

                                if (prop.getName().equals("Error")) {
                                    isError = true;
                                } else if (prop.getName().equals("Result")) {
                                    result = prop.getVal();
                                }
                            }

                            if (!isError) {

                                report.CompletedTaskReport(task.getId());

                            } else {
                                report.ImportTaskFailSendEMail(task.getId(), result);
                            }

                        }
                    }
                }
            }

        } catch (Exception E) {
            System.out.println("REPORT Error save XML export command: " + E.toString());
        }
    }
}
