package web.serverBO;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import web.Mail;
import web.ServiceException;
import web.fault.FaultMessage;
import web.fault.ServiceFaultInfo;
import web.utils.MailSerializer;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ServerBO {

    private static Logger LOG = LogManager.getLogger(ServerBO.class);
    private static ServerBO serverBO;

    private ServerBO() {
    }

    public static ServerBO getServer() {
        if (serverBO == null) {
            serverBO = new ServerBO();
        }
        return serverBO;
    }

    public boolean send(Mail mail) {
        LOG.info("Send mail with id=" + mail.getId());

        List<Mail> allMails = MailSerializer.read();
        if (allMails.contains(mail)) {
            LOG.warn("Current mail already exist");
            return false;
        }
        allMails.add(mail);
        MailSerializer.write(allMails);
        return true;
    }

    public boolean remove(List<Mail> mailsToRemove) {
        LOG.info("Removing mails from file");
        List<Mail> allMails = MailSerializer.read();
        if (mailsToRemove != null && mailsToRemove.size() > 0 && allMails.size() > 0) {
            for (Mail m : allMails) {
                allMails = new ArrayList(allMails.stream().filter(mail -> mail.equals(m)).collect(Collectors.toList()));
            }
            MailSerializer.write(allMails);
            return true;
        }
        LOG.info("Removing failed. No mails to remove");
        return false;
    }

    public void removeAll() {
        LOG.info("Removing all mails");
        MailSerializer.write(new ArrayList<>());
    }

    public List<Mail> getAll() {
        LOG.info("Get all mails from file");
        return MailSerializer.read();
    }

    public List<Mail> findByEmail(String email) {
        LOG.info("Get mails by email: " + email);
        List<Mail> mails = MailSerializer.read();
        List<Mail> mailsByEmail = new ArrayList<>();
        for (Mail m : mails) {
            if (m.getReceiver().equalsIgnoreCase(email))
                mailsByEmail.add(m);
        }
        return mailsByEmail;
    }

    public List<Mail> findByTitle(String title) {
        LOG.info("Get mails by Title: " + title);
        List<Mail> mails = MailSerializer.read();
        List<Mail> mailsByTitle = new ArrayList<>();
        for (Mail m : mails) {
            if (m.getTitle().toLowerCase().contains(title.toLowerCase()))
                mailsByTitle.add(m);
        }
        return mailsByTitle;
    }

    public Mail findById(String id) throws ServiceException {
        LOG.info("Get mail by id: " + id);
        List<Mail> mails = MailSerializer.read();
        for (Mail m : mails) {
            if (m.getId().equals(id))
                LOG.info("Received mail with id: " + id);
                return m;
        }
        LOG.info("Mail with id: " + id + "doesn't exist");
        throw new ServiceException(new ServiceFaultInfo(FaultMessage.EMAIL_NOT_FOUND, id));
    }
    //TODO Рефакторнути. Методи в класах імплементації замінити одним методом тут.
    private String mailsToString(List<Mail> list) {
        return list.stream().map(Object::toString)
                .collect(Collectors.joining(", "));
    }

}
