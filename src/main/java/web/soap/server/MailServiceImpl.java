package web.soap.server;

import web.Mail;
import web.ServiceException;
import web.serverBO.ServerBO;
import javax.jws.WebService;
import java.util.List;

@WebService(endpointInterface = "web.soap.server.MailService")
public class MailServiceImpl implements MailService {

    private ServerBO serverBO;

    public MailServiceImpl(){
        serverBO = ServerBO.getServer();
    }

    @Override
    public boolean send(Mail mail) {
        return serverBO.send(mail);
    }

    @Override
    public boolean remove(List<Mail> mailsToRemove) {
        return serverBO.remove(mailsToRemove);
    }

    @Override
    public boolean clearMails() {
        return serverBO.removeAll();
    }

    @Override
    public List<Mail> getAll() {
        return serverBO.getAll();
    }

    @Override
    public List<Mail> findByEmail(String email) {
        return serverBO.findByEmail(email);
    }

    @Override
    public List<Mail> findByTitle(String title) {
        return serverBO.findByTitle(title);
    }

    @Override
    public Mail findById(String id) throws ServiceException {
        return serverBO.findById(id);
    }
}
