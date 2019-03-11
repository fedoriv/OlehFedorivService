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
            response = Response.ok().build();
        } else {
            faultInfo = new ServiceFaultInfo(FaultMessage.MAIL_ALREADY_EXIST, mail);
            response = Response.status(Response.Status.NOT_ACCEPTABLE).entity(faultInfo).build();
        }
        return response;
    }

    @Override
    public Response remove(List<Mail> mail) {
        if (server.remove(mail)) {
            response = Response.ok().build();
        } else {
            responseFault(Response.Status.NOT_FOUND, new ServiceFaultInfo(FaultMessage.MAIL_NOT_FOUND, mail.toArray()));
        }
        return response;
    }

    @Override
    public Response clearMails() {
        server.removeAll();
        return response = Response.ok().build();
    }

    @Override
    public Response getAll() {
        List<Mail> mails = server.getAll();

        if (mails.size()==0) {
            responseFault(Response.Status.NOT_FOUND, new ServiceFaultInfo(FaultMessage.EMPTY_MAIL_LIST));
        } else {
            response = Response.ok().entity(mails).build();
        }
        return response;
    }

    @Override
    public Response findByEmail(String email) {
        List<Mail> mails = server.findByEmail(email);

        if (mails.size()==0) {
            responseFault(Response.Status.NOT_FOUND, new ServiceFaultInfo(FaultMessage.MAIL_NOT_FOUND, email));
        } else {
            response = Response.ok().entity(mails).build();
        }
        return response;
    }

    @Override
    public Response findByTitle(String title) {
        List<Mail> mails = server.findByTitle(title);
        if (mails.size()==0) {
            responseFault(Response.Status.NOT_FOUND, new ServiceFaultInfo(FaultMessage.MAIL_NOT_FOUND, title));

        } else {
            response = Response.ok().entity(mails).build();
        }
        return response;
    }

    @Override
    public Response findById(String id) {
        try {
            Mail mail = server.findById(id);
            response = Response.ok().entity(mail).build();
        } catch (ServiceException e){
            responseFault(Response.Status.NOT_FOUND, new ServiceFaultInfo(FaultMessage.MAIL_NOT_FOUND, id));
        }
        return response;
    }

    private void responseFault(Response.Status status, ServiceFaultInfo faultInfo){
        LOG.warn(faultInfo.getMessage());
        response = Response.status(status).entity(faultInfo).build();
    }
}
