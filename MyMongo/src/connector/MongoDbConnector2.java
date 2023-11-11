package connector;

 
import static com.mongodb.MongoClientSettings.getDefaultCodecRegistry;
import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.time.Instant;
import java.time.ZonedDateTime;

import com.mongodb.client.*;
import com.mongodb.client.model.Filters.*;

import model.Address;
import model.Persona;

import static com.mongodb.client.model.Filters.*;
import org.bson.Document;
import org.bson.codecs.configuration.CodecProvider;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.bson.conversions.Bson;
import org.bson.Document;
import org.bson.conversions.Bson;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.MongoException;
 
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;

 


public class MongoDbConnector2 {
	
	//static Logger logger = Logger.getLogger(MongoDbConnector2.class.getName());
	 
	//public String uri = "mongodb+srv://cipollan:Cippop8Cippop8@cluster0.53g76t2.mongodb.net/?retryWrites=true&w=majority";
	
	 
	//public String uri = "mongodb+srv://cipollan:Cippop8Cippop8@cluster0.53g76t2.mongodb.net/?ssl=true&ssl_ca_certs=cert";
	//public String uri = "mongodb+srv://cipollan:Cippop8Cippop8@cluster0.53g76t2.mongodb.net/?authSource=admin&authMechanism=SCRAM-SHA-1";
		
	public String uri = "mongodb+srv://admin:admin@cluster0.53g76t2.mongodb.net/?authSource=admin&authMechanism=SCRAM-SHA-1";
	
	//public String uri = "mongodb+srv://cipollan:Cippop8Cippop8@cluster0.53g76t2.mongodb.net/test";
	
	private static final SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
	
	public MongoDbConnector2()
	{
		System.out.println ( " +-----------------------------------------------+ " );
		System.out.println ( "     MongoDbConnector2 Constructor " + getUri() );
		System.out.println ( " +-----------------------------------------------+ " );
	}
	
	
	public synchronized String getUri() {
		return uri;
	}


	public synchronized void setUri(String uri) {
		this.uri = uri;
	}


	public void doMonGoConnect1 (String  i) 
	{
		System.out.println ( " BEGIN doMonGoConnect1<" + getUri() + "> " + i);

    	try  {
    		ConnectionString connectionString = new ConnectionString(getUri());
    		MongoClientSettings settings = MongoClientSettings.builder()
    		        .applyConnectionString(connectionString)
    		        .build();
    		MongoClient mongoClient = MongoClients.create(settings);
    		MongoDatabase database = mongoClient.getDatabase("MyDatabase");
    		
    		System.out.println ( " +------>  doMonGoConnect1 database.getName<" + database.getName() + "> "  ); 
    		
    		for (String name : database.listCollectionNames()) 
    		{
    		    System.out.println( " +------>  doMonGoConnect1 collection name " + name);
    		}
    		
    		
    		MongoCollection<Document> coll = database.getCollection("MyCollection2");
    		
    		// find code goes here
    		Bson filter = eq("nome", "andrea");
    		Bson filter2 = and(eq("hasRings", false), eq("mainAtmosphere", "Ar"));
    		 
    		MongoCursor<Document> cursor = coll.find(filter).iterator();
    		
    		try {
    		    while (cursor.hasNext()) {
    		        System.out.println(cursor.next().toJson());
    		    }
    		} finally {
    		    cursor.close();
    		}
    		
    		// Crea 
    		CodecProvider pojoCodecProvider = PojoCodecProvider.builder().automatic(true).build();
    		CodecRegistry pojoCodecRegistry = fromRegistries(getDefaultCodecRegistry(), fromProviders(pojoCodecProvider));

    		
    		 
    		MongoDatabase database2 = mongoClient.getDatabase("MyDatabase").withCodecRegistry(pojoCodecRegistry);
    		MongoCollection<Persona> collection = database2.getCollection("MyCollection2", Persona.class);
     
    		for (int ii = 0 ; ii < 2000 ; ii++) 
    		{
    		     
    		    Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        		Persona p = new Persona("andrea", "aglio" ,77 + ii,  timestamp);
        		collection.insertOne(p);
        		System.out.println ( " ins ()<" + p.getAge() + "> "  );
        		
    		}
    		
    		
    		MongoCollection<Address> collectionAddress = database2.getCollection("MyAddress", Address.class);
    	     
    		for (int ii = 0 ; ii < 2000 ; ii++) 
    		{
    		    Address a = new Address("State"+ii,"Street"+ii,ii);
    		    collectionAddress.insertOne(a);
        		System.out.println ( " ins ()<" + a.getState() + "> "  );
        		
    		}
    		
    		
            MongoCollection<Document> collAddr = database.getCollection("MyAddress");
    		
    		// find code goes here
    		Bson filterA = eq("State", "State");
    		
    		 
    		MongoCursor<Document> cursorA = coll.find(filterA).iterator();
    		
    		try {
    		    while (cursorA.hasNext()) {
    		        System.out.println(cursorA.next().toJson());
    		    }
    		} finally {
    		    cursorA.close();
    		}
    		
    	
    		
    		System.out.println ( " END doMonGoConnect1  > "  );
 
    	}	 
    	catch (Exception me) 
	 	{
    		System.out.println(" Exception <" + me.getMessage()); 
	 		//System.out.println(" 1x---dopo doMonGoConnect1--<" + me.getMessage());
	 	}
	 	finally
	 	{
	 		System.out.println ( " doMonGoConnect1 1finally"     );
	 	}

	}
	
	public void doMonGoConnect2 (String  i) 
	{
		System.out.println ( " BEGIN doMonGoConnect2<" + getUri() + "> " + i);

    	try  {
    		ConnectionString connectionString = new ConnectionString(getUri());
    		MongoClientSettings settings = MongoClientSettings.builder()
    		        .applyConnectionString(connectionString)
     		       // .serverApi(ServerApi.builder()
     	//	            .version(ServerApiVersion.V1)
     	//	            .build())
    		        .build();
    		MongoClient mongoClient = MongoClients.create(settings);
    		MongoDatabase database = mongoClient.getDatabase("MyDatabase");       
    	}
    	catch (Exception me) 
	 	{
    		System.out.println(" Exception:	" + me.getMessage()); 
	 		System.out.println(" 1x---dopo doMonGoConnect2--<" + me.getMessage());
	 	}
	 	finally
	 	{
	 		System.out.println ( " doMonGoConnectA 1finally"     );
	 	}
    	System.out.println ( " doMonGoConnect2 END"     );
    	
	}
}

