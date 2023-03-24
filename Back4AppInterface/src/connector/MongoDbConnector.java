package connector;


import org.apache.log4j.Logger;
import org.bson.BsonDocument;
import org.bson.BsonInt64;
import org.bson.Document;
import org.bson.conversions.Bson;

import com.mongodb.*;
import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.MongoException;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;

import mainPackage.MagicMain;

public class MongoDbConnector {
	
	static Logger logger = Logger.getLogger(MongoDbConnector.class.getName());
	
    public void doMonGoConnect(String  args) {
        // Replace the placeholder with your Atlas connection string
    	logger.debug ( " BEGIN doMonGoConnect ");
    	
    	String uri = "mongodb+srv://cipollan:Cippop8@Cippop8@@cluster0.53g76t2.mongodb.net/?retryWrites=true&w=majority";

    	logger.debug ( " BEGIN doMonGoConnect:" + uri );
    	
        // Construct a ServerApi instance using the ServerApi.builder() method
        ServerApi serverApi = ServerApi.builder()
                .version(ServerApiVersion.V1)
                .build();
        
        logger.debug ( " BEGIN doMonGoConnect.serverApi:" + serverApi.toString() );
        

        MongoClientSettings settings = MongoClientSettings.builder()
                .applyConnectionString(new ConnectionString(uri))
                .serverApi(serverApi)
                .build();
        
        logger.debug ( " BEGIN doMonGoConnect.settings:" + settings.toString() );

        // Create a new client and connect to the server
        try 
        {
        	
            MongoClient mongoClient = MongoClients.create(settings);
            MongoDatabase database = mongoClient.getDatabase("admin");
            try {
                // Send a ping to confirm a successful connection
                Bson command = new BsonDocument("ping", new BsonInt64(1));
                Document commandResult = database.runCommand(command);
                System.out.println("Pinged your deployment. You successfully connected to MongoDB!");
            } catch (MongoException me) {
                System.err.println(me);
            }
            
            mongoClient.close();
            
        }
        finally 
        {
        	System.out.println("finally");
        }
    }
}

