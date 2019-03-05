package web.soap.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import web.soap.server.Mail;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class MailSerializer {

    private static Logger LOG = LogManager.getLogger(MailSerializer.class);
    private final static String FILE_PATH = "src/main/resources/mails";

    public synchronized static void write(List<Mail> mails) {
        LOG.debug("Start writing into file");
        try(ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(new File(FILE_PATH)))) {
            objectOutputStream.writeObject(mails);
            objectOutputStream.flush();
        } catch (FileNotFoundException e) {
            LOG.error("Cant find file in given location: " + FILE_PATH);
            e.printStackTrace();
        } catch (IOException e) {
            LOG.error("Problem with writing into file: " + FILE_PATH);
            e.printStackTrace();
        }
    }

    public synchronized static List<Mail> read() {
        LOG.debug("Start reading from file");
        List<Mail> mails = new ArrayList<>();
        try(ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(new File(FILE_PATH)))) {
            mails = (List<Mail>) objectInputStream.readObject();
        } catch (FileNotFoundException e) {
            LOG.error("Cant find file in given location: " + FILE_PATH);
            e.printStackTrace();
        } catch (IOException e) {
            LOG.error("Problem with reading file: " + FILE_PATH);
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return mails;
    }
}
