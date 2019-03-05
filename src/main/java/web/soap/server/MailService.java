package web.soap.server;
import javax.jws.WebService;
import java.util.List;

@WebService
public interface MailService {

    void send(Mail mail);

    void remove(List<Mail> mail);

    void removeAll();

    List<Mail> getAll();

    List<Mail> findByEmail(String email);

    List<Mail> findByTitle(String title);

    Mail findById(String id);

}
