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
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import mainPackage.MagicMain;

public class MongoDbConnector {
	
	static Logger logger = Logger.getLogger(MongoDbConnector.class.getName());
	
    public void doMonGoConnect(String  args) {
        // Replace the placeholder with your Atlas connection string
    	logger.debug ( " BEGIN doMonGoConnect ");
    	
    	String uri = "mongodb+srv://cipollan:Cippop8%40Cippop8%40@cluster0.53g76t2.mongodb.net/?retryWrites=true&w=majority";

    	logger.debug ( " BEGIN doMonGoConnect<" + uri + ">");
    	
    	
    	 try  {
    		 		String id = "641c93658c80684391753e2c";
    		 		logger.debug ("0dopo doc");
    		 		MongoClient mongoClient = MongoClients.create(uri);
    		 		logger.debug ("1dopo doc");
    		 		MongoDatabase database = mongoClient.getDatabase("Cluster0");
    		 		logger.debug ("2dopo doc");
    		 		MongoCollection<Document> collection = database.getCollection("MyCollection");
    		 		logger.debug ("3dopo doc");
    		 		//Document doc = collection.find(eq("title", "Back to the Future")).first();
             
    		 		Document doc = collection.find(Filters.eq("_id", id)).first();
    		 		logger.debug ("4dopo doc");
    		 		
             
    		 		if (doc != null) 
    		 		{
    		 			logger.debug ("not null doc");
    		 			logger.debug ("not null doc" + doc.toJson());
    		 		} 
    		 		else 
    		 		{
    		 			logger.debug ("No matching documents found.");
    		 		}
    	     } 
    	 	catch (MongoException me) 
 	 		{
    	 		logger.debug (" Exception:	" + me);
 	 		
 	 		}
    	 	catch (Exception me) 
 	 		{
    	 		logger.debug (" Exception:	" + me);
     
 	 		}
    	 	finally
    	 	{
    		   logger.debug ( " 1finally"     );
    	 	}
    	 
    	 
    	 logger.debug ("AAAAAAAAAAAAAAAAAAAAAAAAAAA");
    	
        // Construct a ServerApi instance using the ServerApi.builder() method
    	 // Construct a ServerApi instance using the ServerApi.builder() method
        ServerApi serverApi = ServerApi.builder()
                .version(ServerApiVersion.V1)
                .build();
        
        logger.debug ("BBBBBBBBBBBBBBBBBBBBBBBBBBBB");
        
         /*
          
        logger.debug ( " BEGIN doMonGoConnect.serverApi:" 
        //+ serverApi.toString() 
        
        		);
        

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
        
        */
    }
}

