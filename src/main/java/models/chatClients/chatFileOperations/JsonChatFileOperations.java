package models.chatClients.chatFileOperations;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import models.Message;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class JsonChatFileOperations implements ChatFileOperations{
    private Gson gson;
    private static final String MESSAGES_FILE="./messages.json";

    public JsonChatFileOperations(){
        gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().setPrettyPrinting().create();
    }
    @Override
    public List<Message> loadMessages() {
        try{
            FileReader reader = new FileReader(MESSAGES_FILE);
            BufferedReader bufferedReader = new BufferedReader(reader);
            StringBuilder jsonText = new StringBuilder();

            String line;
            while ((line = bufferedReader.readLine()) != null){
                jsonText.append(line);
            }
            reader.close();

            Type targetType = new TypeToken<ArrayList<Message>>(){}.getType();

            List<Message> messages = gson.fromJson(jsonText.toString(),targetType);
            return messages;

        }catch (IOException e){
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    @Override
    public void writeMessages(List<Message> messages) {
        String jsonText = gson.toJson(messages);

        try{
            FileWriter writer = new FileWriter(MESSAGES_FILE);
            writer.write(jsonText);
            writer.flush();
            writer.close();
        }catch (IOException e){
            e.printStackTrace();
        }

    }

    @Override
    public List<String> loadLoggedUsers() {
        return null;
    }

    @Override
    public void writeLoggedUsers(List<String> users) {

    }
}
