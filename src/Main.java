import models.chatClients.ChatClient;
import models.chatClients.InMemoryChatClient;
import models.Message;
import models.gui.MainFrame;

public class Main {
    public static void main(String[] args) {

        System.out.println("Hello World!");
        MainFrame window = new MainFrame(800,600);

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
