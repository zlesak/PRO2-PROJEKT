package models.chatClients.database;

import models.Message;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JdbcDatabaseOperations implements DatabaseOperations{

    private final Connection connection;

    public JdbcDatabaseOperations(String driver, String url) throws ClassNotFoundException, SQLException {
        Class.forName(driver);
        this.connection = DriverManager.getConnection(url);
    }


    @Override
    public void addMessage(Message message) {
        try{
            Statement statement = connection.createStatement();
            String sql = "INSERT INTO ChatMessages (author,text,created) VALUES (" +
                    "'" +message.getAuthor()+"', "+
                    "'" +message.getText()+"', "+
                    "'" + Timestamp.valueOf(message.getCreated()) +"' "
                    +")";
            statement.execute(sql);
            statement.close();
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    @Override
    public List<Message> getMessages() {
        /*TODO DODĚLAT NAČÍTÁNÍ ZPRÁV*/
        /*TODO DODĚLAT CSV CELKOVÁ IMPLEMENTACE*/
        List<Message> messagesList = new ArrayList<>();

        try{
            Statement statement = connection.createStatement();
            String sql = "SELECT * FROM ChatMessages";
            statement.execute(sql);
            ResultSet res = statement.getResultSet();
            while (res.next()){
                Message m = new Message(res.getString("author"),res.getString("text"));
                messagesList.add(m);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return messagesList;
    }
}
