package models.chatClients;

import models.Message;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class InMemoryChatClient implements ChatClient{
    private String loggedUser;
    private List<String> loggedUsers;
    private List<Message> messages;

    private List<ActionListener> listenerLoggedUsersChanged = new ArrayList<>();
    private List<ActionListener> listenerMessagesChanged = new ArrayList<>();

    public InMemoryChatClient() {
        loggedUsers = new ArrayList<>();
        messages = new ArrayList<>();
    }

    @Override
    public void sendMessage(String text) {
        messages.add(new Message(loggedUser,text));
        raiseEventMessagesChanged();
    }

    @Override
    public void login(String userName) {
        loggedUser = userName;
        loggedUsers.add(userName);
        raiseEventLoggedUsersChanged();
    }

    @Override
    public void logout() {
        loggedUsers.remove(loggedUser);
        loggedUser = null;
        raiseEventLoggedUsersChanged();
    }

    @Override
    public Boolean isAuthenticated() {
        return loggedUser!=null;
    }

    @Override
    public List<String> getLoggedUsers() {
        return loggedUsers;
    }

    @Override
    public List<Message> getMessages() {
        return messages;
    }

    @Override
    public void addActionListenerLoggedUsersChanged(ActionListener toAdd) {
        listenerLoggedUsersChanged.add(toAdd);

    }

    @Override
    public void addActionListenerMessagesChanged(ActionListener toAdd) {
        listenerMessagesChanged.add(toAdd);
    }

    private void raiseEventLoggedUsersChanged(){
        for (ActionListener al :
                listenerLoggedUsersChanged) {
            al.actionPerformed(new ActionEvent(this,1,"userChanged"));
        }
    }
    private void raiseEventMessagesChanged(){
        for (ActionListener al:
             listenerMessagesChanged) {
            al.actionPerformed(new ActionEvent(this,1,"messagesChanged"));
        }
    }
}
