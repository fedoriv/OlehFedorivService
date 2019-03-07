package web.rest.server;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import web.Mail;
import web.ServiceException;
import web.fault.FaultMessage;
import web.fault.ServiceFaultInfo;
import web.serverBO.ServerBO;

import javax.ws.rs.core.Response;
import java.util.List;
import java.util.stream.Collectors;

public class MailServiceImpl implements MailService{

    private static Logger LOG = LogManager.getLogger(ServerBO.class);
    private ServerBO server;
    private Response response;
    private ServiceFaultInfo faultInfo;


    MailServiceImpl(){
        server = ServerBO.getServer();
    }
    @Override
    public Response send(Mail mail) {
        if(server.send(mail)){

        }
        return null;
    }

    @Override
    public Response remove(List<Mail> mail) {
        return null;
    }

    @Override
    public Response removeAll() {
        server.removeAll();
        return response = Response.ok().build();
    }

    @Override
    public Response getAll() {
        List<Mail> mails = server.getAll();

        if (mails.size()==0) {
            response = Response.status(Response.Status.NOT_FOUND).entity(faultInfo).build();
        } else {
            LOG.info("Method result:" + mailsToString(mails));
            response = Response.ok().entity(mails).build();
        }
        return response;
    }

    @Override
    public Response findByEmail(String email) {
        List<Mail> mails = server.findByEmail(email);

        if (mails.size()==0) {
            faultInfo = new ServiceFaultInfo(FaultMessage.EMAIL_NOT_FOUND, email);
            LOG.warn(faultInfo.getMessage());

            response = Response.status(Response.Status.NOT_FOUND).entity(faultInfo).build();
        } else {
            LOG.info("Method result:" + mailsToString(mails));
            response = Response.ok().entity(mails).build();
        }
        return response;
    }

    @Override
    public Response findByTitle(String title) {
        List<Mail> mails = server.findByEmail(title);
        if (mails.size()==0) {
            faultInfo = new ServiceFaultInfo(FaultMessage.EMAIL_NOT_FOUND, title);
            LOG.warn(faultInfo.getMessage());
            response = Response.status(Response.Status.NOT_FOUND).entity(faultInfo).build();
        } else {
            LOG.info("Method result:" + mailsToString(mails));
            response = Response.ok().entity(mails).build();
        }
        return response;
    }

    @Override
    public Response findById(String id) {
        try {
            Mail mail = server.findById(id);
            response = Response.ok().entity(mail).build();
            LOG.info("method result:" + id);
        } catch (ServiceException e){
            LOG.warn(e.getServiceFaultInfo());
            response = Response.status(Response.Status.NOT_FOUND).entity(faultInfo).build();
        }
        return response;
    }
    //TODO перемістити в ServerBO
    private String mailsToString(List<Mail> list){
        return list.stream().map(Object::toString)
                .collect(Collectors.joining(", "));
    }
}
