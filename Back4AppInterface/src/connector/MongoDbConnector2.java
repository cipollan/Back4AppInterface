package connector;






import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import org.bson.Document;
import java.net.URLEncoder;

public class MongoDbConnector2 {

    public   void doConnect(String  args){

        try{
            String username = URLEncoder.encode("Cippop8", "UTF-8");
            String password = URLEncoder.encode("Cippop8", "UTF-8");
            String cluster = "cluster0";
            String authSource = "<authSource>";
            String authMechanism = "<authMechanism>";
            
            String uri = "mongodb+srv://" + username + ":" + password + "@" + cluster + 
                         "/?authSource=" + authSource + "&authMechanism=" + authMechanism;

            MongoClient mongoClient = MongoClients.create(uri);
    
            MongoDatabase database = mongoClient.getDatabase("MyDatabase");
            MongoCollection<Document> collection = database.getCollection("MyCollection2");
             

        } catch(Exception e){
            System.err.println(e.getCause());

        }
    }
}


