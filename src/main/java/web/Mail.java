package web;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import java.util.UUID;

public class Mail implements Serializable {

    public Date date;
    public String id;
    private String receiver;
    private String title;
    private String text;

    public Mail(){
        date = new Date();
        id = UUID.randomUUID().toString();
    }

    public String getId(){
        return id;
    }

    public Date getDate(){
        return date;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Mail mail = (Mail) o;
        return id == mail.id &&
                date.equals(mail.date) &&
                receiver.equals(mail.receiver) &&
                Objects.equals(title, mail.title) &&
                Objects.equals(text, mail.text);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, date, receiver, title, text);
    }
}
