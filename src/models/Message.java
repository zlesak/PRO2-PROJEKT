package models;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Message {
    private String author;
    private String text;
    private LocalDateTime created;

    public Message(String author, String text) {
        this.author = author;
        this.text = text;
        this.created = LocalDateTime.now();
    }

    public String getAuthor() {
        return author;
    }

    public String getText() {
        return text;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    @Override
    public String toString() {
        String s = author + " [" + created.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME)+"]\n";
        s +=text +"\n";
        return s;
    }
}
