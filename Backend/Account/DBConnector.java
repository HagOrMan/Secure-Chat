package Backend.Account;
import java.util.List;
import java.util.ArrayList;

// DBConnector.java
public class DBConnector {
    //not in account controller
    String driver;
    String server;
    String userID;
    String password;
    int port;

    public void connect() {
        // connect to the database
        
    }

    public void disconnect() {
        // disconnect from the database
    }

    public List<String> query(String queryString) {
        // query the database
        return new ArrayList<>();
    }

    public void commit() {
        // commit the transaction
    }
}


