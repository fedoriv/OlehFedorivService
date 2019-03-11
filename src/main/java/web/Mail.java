package web;

import java.io.Serializable;
import java.util.Objects;

public class Mail implements Serializable {

    private String date;
    private String id;
    private String receiver;
    private String title;
    private String text;

    public Mail(){

    }

    public String getId(){
        return id;
    }

    public String getDate(){
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

    public void setDate(String date) {
        this.date = date;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Mail mail = (Mail) o;
        return id.equals(mail.id) &&
                date.equals(mail.date) &&
                receiver.equals(mail.receiver) &&
                Objects.equals(title, mail.title) &&
                Objects.equals(text, mail.text);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, date, receiver, title, text);
    }

    @Override
    public String toString() {
        return "Mail{" +
                "id='" + id + '\'' +
                ", receiver='" + receiver + '\'' +
                ", title='" + title + '\'' +
                '}';
    }
}
