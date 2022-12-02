import models.chatClients.ChatClient;
import models.chatClients.InMemoryChatClient;
import models.Message;
import models.chatClients.ToFileChatClient;
import models.chatClients.api.ApiChatClient;
import models.chatClients.chatFileOperations.ChatFileOperations;
import models.chatClients.chatFileOperations.JsonChatFileOperations;
import models.chatClients.database.DbInitializer;
import models.chatClients.database.JdbcDatabaseOperations;
import models.gui.MainFrame;

import java.lang.reflect.Field;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        String databaseDriver = "org.apache.derby.jdbc.EmbeddedDriver";
        String databaseUrl = "jdbc:derby:ChatClientDb";

        DbInitializer dbInitializer = new DbInitializer(databaseDriver,databaseUrl);
        //dbInitializer.init();

        //ChatFileOperations chatFileOperations = new JdbcDatabaseOperations(databaseDriver,databaseUrl);


        //ChatClient client = new ApiChatClient();
        //ChatClient client = new ToFileChatClient();
        //ChatClient client = new JdbcDatabaseOperations(databaseDriver,databaseUrl);
        //ChatClient client = new InMemoryChatClient();

        Class<ApiChatClient> reflectionExample = ApiChatClient.class;
        List<Field> fields = getAllFields(reflectionExample);

        //System.out.println("Class name: " + reflectionExample.getName() + " | " + reflectionExample.getSimpleName());


        JdbcDatabaseOperations operations = new JdbcDatabaseOperations(databaseDriver,databaseUrl);

        Message m = new Message("Ahoj", "zlesaja");
        operations.addMessage(m);

        System.out.println(operations.getMessages());
/*
        for (Field f :
                fields) {
            System.out.println(f.getName() + ":" + f.getType() );
        }
*/



        //System.out.println("Hello World!");
        //MainFrame window = new MainFrame(800,600, client);



    }
    private void test(){
        ChatClient client = new InMemoryChatClient();
        client.login("zlesaja");
        System.out.println(client.isAuthenticated());
        client.sendMessage("Hello");
        client.sendMessage("Hi");
        for (Message m :
                client.getMessages()) {
            System.out.println(m.getText());
        }
        client.logout();
        System.out.println(client.isAuthenticated());
    }

    private static List<Field> getAllFields(Class<?> cls){
        List<Field> fields = new ArrayList<>();
        for(Field f : cls.getDeclaredFields()){
            fields.add(f);
        }
        return fields;
    }


}
