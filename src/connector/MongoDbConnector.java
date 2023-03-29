package connector;


import org.apache.log4j.Logger;
import org.bson.Document;
import com.mongodb.*;
import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.MongoException;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;


public class MongoDbConnector {
	
	static Logger logger = Logger.getLogger(MongoDbConnector.class.getName());
	//public String uri = "mongodb://cipollan:Cippop8Cippop8@cluster0.53g76t2.mongodb.net/?retryWrites=true&w=majority";
	public String uri = "mongodb+srv://cipollan:Cippop8Cippop8@cluster0.53g76t2.mongodb.net/test";
	
	public MongoDbConnector()
	{
		logger.debug ( " +-----------------------------------------------+ " );
		logger.debug ( "     MongoDbConnector Constructor " + getUri() );
		logger.debug ( " +-----------------------------------------------+ " );
	}
	
	
	public synchronized String getUri() {
		return uri;
	}


	public synchronized void setUri(String uri) {
		this.uri = uri;
	}


	public void doMonGoConnectA(String  i) 
	{
    	logger.debug ( " BEGIN doMonGoConnectA<" + getUri() + "> " + i);

    	try  {
    			logger.debug (" 1x---Before ConnectionString--<" );
    			MongoClient mongoClientA = MongoClients.create(getUri());
    			//logger.debug (" 1A---Before ConnectionString--<" );
    			MongoDatabase databaseA = mongoClientA.getDatabase("cluster0");
    			//logger.debug (" 1B---Before ConnectionString--<" );
    			MongoCollection<Document> collection = databaseA.getCollection("MyCollection");
    			logger.debug (" 1C---Before ConnectionString--<" + collection.count());                
    	}
    	catch (Exception me) 
	 	{
	 		logger.error(" Exception:	", me); 
	 		System.out.println(" 1x---dopo ConnectionString--<" + me.getMessage());
	 	}
	 	finally
	 	{
		   logger.error ( " doMonGoConnectA 1finally"     );
	 	}

	}
	
    public void doMonGoConnect(String  args) {
        // Replace the placeholder with your Atlas connection string
    	 
    	logger.debug ( " BEGIN doMonGoConnect<" + getUri() + ">");

    	try  {
    		
    		logger.debug (" 1---Before ConnectionString--<" );
    	
    	 
    		logger.debug (" 1x---Before ConnectionString--<" );
    		MongoClient mongoClientA = MongoClients.create(uri);
    		logger.debug (" 1A---Before ConnectionString--<" );
    		
            MongoDatabase databaseA = mongoClientA.getDatabase("cluster0");
            logger.debug (" 1B---Before ConnectionString--<" );
                
            MongoCollection<Document> collection = databaseA.getCollection("MyCollection");
            logger.debug (" 1C---Before ConnectionString--<" );
                
            //Bson projectionFields = Projections.fields(Projections.include("title", "imdb"), Projections.excludeId());
           
            logger.debug (" 1D---Before ConnectionString--<" );
                
                /*
                Document doc = collection.find(eq("title", "The Room"))
                        .projection(projectionFields)
                        .sort(Sorts.descending("imdb.rating"))
                        .first();
				*/
                
    		ConnectionString connectionString = new ConnectionString(getUri());
    		
    		
    		logger.debug (" 1.1---Before ConnectionString--<" );
    		
    		/*
    		MongoClientSettings settings = MongoClientSettings.builder()
    		        .applyConnectionString(connectionString)
    		        .serverApi(ServerApi.builder()
    		            .version(ServerApiVersion.V1)
    		            .build())
    		        .build();
    		        */
    		
    		MongoClientSettings settings = MongoClientSettings.builder()
    		        .applyConnectionString(connectionString)
    		        .build();
    		
    		logger.debug (" 1.2<---After ConnectionString--<" );
    		
    		MongoClient mongoClient = MongoClients.create(settings);
    		
    		logger.debug (" 1.5<---After ConnectionString--<" );
    		
    		MongoDatabase database = mongoClient.getDatabase("cluster0");
    		
    		
    		logger.debug (" 2---After ConnectionString--<" );
    		

    		MongoClient mongoClient2 = MongoClients.create(
    			    MongoClientSettings.builder()
    			    .applyConnectionString(connectionString)
    			    .build());
    		
    		logger.debug (" 3---After mongoClient--<" );
    		    		
    		MongoDatabase database1 = mongoClient.getDatabase("Cluster0");
    		
    		logger.debug (" 5---------------------------->" );

    	}
    	catch (Exception me) 
	 	{
	 		logger.debug (" Exception:	" + me);
	 	}
	 	finally
	 	{
		   logger.debug ( " doMonGoConnect 1finally"     );
	 	}

    	
    	 try  {
    		 		String id = "641c93658c80684391753e2c";
    		 		logger.debug ("0-dopo doc");
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
    	 
    	 
    	 logger.debug ("AAAAAAAAAAAAAAAAAAAAAAAAA");
    	
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
    
    
    public void doMonGoConnectB(String  args) {
        // Replace the placeholder with your Atlas connection string
    	 
    	logger.debug ( " BEGIN doMonGoConnectB<" + getUri() + ">");

    	    	
    	 try  {
    		 		String id = "641c93658c80684391753e2c";
    		 		logger.debug ("0-dopo doc");
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
    		   logger.debug ( " doMonGoConnectB 1finally"     );
    	 	}
    	 
    	 
    	 logger.debug ("AAAAAAAAAAAAAAAAAAAAAAAAA");
    	
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


