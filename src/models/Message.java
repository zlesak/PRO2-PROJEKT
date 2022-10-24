package models;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Message {
    private String author;
    private String text;
    private LocalDateTime created;

    public static final int USER_LOGGED_IN = 1;
    public static final int USER_LOGGED_OUT = 2;

    private static final String AUTHOR_SYSTEM = "System";

    public Message(String author, String text) {
        this.author = author;
        this.text = text;
        this.created = LocalDateTime.now();
    }

    public Message(int type,String userName){
        this.author = AUTHOR_SYSTEM;
        created = LocalDateTime.now();
        if(type == USER_LOGGED_IN){
            text = userName + " has joined the chat";
        }else if(type == USER_LOGGED_OUT){
            text = userName + " has left the chat";
        }
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
