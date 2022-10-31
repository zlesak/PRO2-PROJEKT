import models.chatClients.ChatClient;
import models.chatClients.InMemoryChatClient;
import models.Message;
import models.chatClients.ToFileChatClient;
import models.chatClients.chatFileOperations.ChatFileOperations;
import models.chatClients.chatFileOperations.JsonChatFileOperations;
import models.gui.MainFrame;

public class Main {
    public static void main(String[] args) {
        ChatFileOperations chatFileOperations = new JsonChatFileOperations();
        ChatClient client = new ToFileChatClient(chatFileOperations);
        //ChatClient client = new InMemoryChatClient();

        System.out.println("Hello World!");
        MainFrame window = new MainFrame(800,600, client);



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
}
