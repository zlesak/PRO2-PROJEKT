package models.chatClients.chatFileOperations;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import models.Message;
import models.chatClients.LocalDateTimeDeserializer;
import models.chatClients.LocalDateTimeSerializer;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class JsonChatFileOperations implements ChatFileOperations{
    private Gson gson;
    private static final String MESSAGES_FILE="./messages.json";
    private static final String LOGGED_USERS_FILE="./users.json";

    public JsonChatFileOperations(){
        GsonBuilder gsonBuilder = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().setPrettyPrinting();
        gsonBuilder.registerTypeAdapter(LocalDateTime.class, new LocalDateTimeSerializer());
        gsonBuilder.registerTypeAdapter(LocalDateTime.class,new LocalDateTimeDeserializer());
        gson = gsonBuilder.create();
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
        try{

            FileReader reader = new FileReader(LOGGED_USERS_FILE);
            BufferedReader bufferedReader = new BufferedReader(reader);
            StringBuilder jsonText = new StringBuilder();
            String line;
            while ((line = bufferedReader.readLine()) != null){
                jsonText.append(line);
            }
            reader.close();

            Type targetType = new TypeToken<String>(){}.getType();

            List<String> loggedUsersList = gson.fromJson(jsonText.toString(),targetType);

            return loggedUsersList;
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    @Override
    public void writeLoggedUsers(List<String> users) {
        String jsonText = gson.toJson(users);

        try{
            FileWriter writer = new FileWriter(LOGGED_USERS_FILE);
            writer.write(jsonText);
            writer.flush();
            writer.close();
        }catch (IOException e){
            e.printStackTrace();
        }

    }
}

