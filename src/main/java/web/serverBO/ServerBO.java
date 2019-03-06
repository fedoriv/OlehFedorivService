package web.serverBO;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import web.Mail;
import web.utils.MailSerializer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ServerBO {
    private static Logger LOG = LogManager.getLogger(ServerBO.class);

    public boolean send(Mail mail) {
        LOG.info("Send message with id=" + mail.getId());
        List<Mail> allMails = MailSerializer.read();
        if (allMails.contains(mail)) {
            LOG.warn("Current email already exist");
            return false;
        }
        allMails.add(mail);
        MailSerializer.write(allMails);
        return true;
    }

    public boolean remove(List<Mail> mailsToRemove) {
        LOG.info("Removing emails from file");
        if (mailsToRemove != null && mailsToRemove.size() > 0) {
            String[] ids = mailsToRemove.stream().map(m -> m.getId()).toArray(String[]::new);
            List<Mail> allMails = MailSerializer.read();
            if (allMails.size() > 0) {
                for (String id : ids) {
                    allMails = new ArrayList(Arrays.asList(allMails.stream().filter(mail -> mail.getId().equals(id)).toArray()));
                }
                MailSerializer.write(allMails);
                return true;
            }
        }
        LOG.warn("No mails to remove");
        return false;
    }

    public void removeAll() {
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

    public Mail findById(String id) {
        LOG.info("Get mail by id: " + id);
        List<Mail> mails = MailSerializer.read();
        for (Mail m : mails) {
            if (m.getId().equals(id))
                return m;
        }
        return null;
    }

}
