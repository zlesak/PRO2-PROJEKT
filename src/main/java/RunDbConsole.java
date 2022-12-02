import org.apache.derby.tools.ij;

public class RunDbConsole {
    public static void main(String[] arg) {
        //connect 'jdbc:derby:ChatClientDb;create=true';

        try {
            ij.main(arg);
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
