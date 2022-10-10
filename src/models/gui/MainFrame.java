package models.gui;

import models.Message;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainFrame extends JFrame {
    private JTextField txtInputName,txtInputMessage;
    private  JTextArea txtChat;
    public MainFrame(int width, int height) {
        super("PRO2 2022 ChatClient");
        setSize(width,height);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        initGui();
        setVisible(true);
    }

    private void initGui(){
        JPanel panelMain = new JPanel(new BorderLayout());

        panelMain.add(initLoginPanel(), BorderLayout.NORTH);
        panelMain.add(intiChatPanel(), BorderLayout.CENTER);
        panelMain.add(initMessagePanel(),BorderLayout.SOUTH);

        add(panelMain);

    }

    private JPanel initLoginPanel(){
        JPanel panelLogin = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelLogin.add(new JLabel("Username"));
        txtInputName = new JTextField("",30);
        panelLogin.add(txtInputName);
        JButton btnLogin = new JButton("Login");
        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                System.out.println("Login clicked - " + txtInputName.getText());
            }
        });
        panelLogin.add(btnLogin);
        return panelLogin;
    }

    private JPanel intiChatPanel(){
        JPanel panelChat = new JPanel();
        panelChat.setLayout(new BoxLayout(panelChat,BoxLayout.X_AXIS));

        txtChat = new JTextArea();
        txtChat.setEditable(false);

        JScrollPane scrollPane = new JScrollPane(txtChat);

        panelChat.add(scrollPane);
//        for (int i = 0; i < 50; i++) {
//            txtChat.append("Message" + i + "\n");
//        }

        return panelChat;
    }

    private JPanel initMessagePanel(){
        JPanel panelMessage = new JPanel(new FlowLayout(FlowLayout.LEFT));
        txtInputMessage = new JTextField("",50);
        panelMessage.add(txtInputMessage);
        JButton btnSend = new JButton("Send");
        btnSend.addActionListener(e ->{
            System.out.println("btnSend click - " + txtInputMessage.getText());
            txtChat.append(txtInputMessage.getText() + "\n");
            txtInputMessage.setText("");

        });
        panelMessage.add(btnSend);

        return panelMessage;
    }

}
