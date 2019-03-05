package web.soap.server;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import web.soap.utils.MailSerializer;
import javax.jws.WebService;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@WebService(endpointInterface = "web.soap.server.MailService")
public class MailServiceImpl implements MailService {
    private static Logger LOG = LogManager.getLogger(MailServiceImpl.class);

    @Override
    public void send(Mail mail) {
        LOG.info("Send message with id=" + mail.getId());
        List<Mail> allMails = MailSerializer.read();
        allMails.add(mail);
        MailSerializer.write(allMails);
    }

    @Override
    public void remove(List<Mail> mailsToRemove) {
        LOG.info("Removing emails from file");
        if (mailsToRemove != null && mailsToRemove.size() > 0) {
            String[] ids = mailsToRemove.stream().map(m -> m.getId()).toArray(String[]::new);
            List<Mail> allMails = MailSerializer.read();
            if (allMails.size() > 0) {
                for (String id : ids) {
                    allMails = new ArrayList(Arrays.asList(allMails.stream().filter(mail -> mail.getId().equals(id)).toArray()));
                }
                MailSerializer.write(allMails);
            }
        }
    }

    @Override
    public void removeAll() {
        MailSerializer.write(new ArrayList<>());
    }

    @Override
    public List<Mail> getAll() {
        LOG.info("Get all mails from file");
        return MailSerializer.read();
    }

    @Override
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

    @Override
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

    @Override
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
