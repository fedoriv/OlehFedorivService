package web.rest.server;

import web.Mail;

import java.util.List;

public class MailServiceImpl implements MailService{
    @Override
    public boolean send(Mail mail) {
        return false;
    }

    @Override
    public boolean remove(List<Mail> mail) {
        return false;
    }

    @Override
    public boolean removeAll() {
        return false;
    }

    @Override
    public List<Mail> getAll() {
        return null;
    }

    @Override
    public List<Mail> findByEmail(String email) {
        return null;
    }

    @Override
    public List<Mail> findByTitle(String title) {
        return null;
    }

    @Override
    public Mail findById(String id) {
        return null;
    }
}
