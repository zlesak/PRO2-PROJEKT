package models.chatClients.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class DbInitializer {
    private final String driver;
    private final String url;

    public DbInitializer(String driver, String url){
        this.driver = driver;
        this.url = url;
    }

    public void init(){
        try {
            //nacterme driver
            Class.forName(driver);

            //otevreme spojeni
            Connection connection = DriverManager.getConnection(url);
            Statement statement = connection.createStatement();

            String sql = "CREATE TABLE ChatMessages"
                    + "(id INT NOT NULL GENERATED ALWAYS AS IDENTITY "
                        + "CONSTRAINT ChatMessages_PK PRIMARY KEY, "
                    + "author VARCHAR(50), "
                    + "text VARCHAR(1000), "
                    + "created timestamp)";

            statement.executeUpdate(sql);
            statement.close();
            connection.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
