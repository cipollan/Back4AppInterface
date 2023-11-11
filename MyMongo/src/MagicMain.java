import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ListIterator;
import java.util.Properties;

import connector.MongoDbConnector;
import connector.MongoDbConnector2;

 
 
public class MagicMain  {

	public static void main(String[] args) throws IOException 
	{
		
		String log4jConfigFile = System.getProperty("user.dir") + File.separator + "resource\\log4j.properties";
		
		System.out.println ( " BEGIN MagicMain ");
		System.out.println ( " BEGIN MagicMain log4jConfigFile:" + log4jConfigFile);
		
		try {
		
			System.out.println ( " BEGIN MagicMain ");
			MongoDbConnector2 mongoDbConnector2 = new MongoDbConnector2();
			mongoDbConnector2.doMonGoConnect1("aaaaaaaaaaaaaaaa");
			//mongoDbConnector2.doMonGoConnect2("bbbbbbbbbbbbbbbbb");
			
		} catch (Throwable e) {
			e.printStackTrace();
		}  
		
		
		try {
			
			System.out.println ( " BEGIN MagicMain ");
			MongoDbConnector2 mongoDbConnector3 = new MongoDbConnector2();
			 
			mongoDbConnector3.doMonGoConnect2("bbbbbbbbbbbbbbbbb");
			
		} catch (Throwable e) {
			e.printStackTrace();
		}  
		
		
		
		
		
		try {
			
			System.out.println ( " BEGIN MagicMain ");
			MongoDbConnector mongoDbConnector  = new MongoDbConnector();
			// mongoDbConnector.doMonGoConnectA("");
			// mongoDbConnector.doMonGoConnect("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");
			
		} catch (Throwable e) {
			e.printStackTrace();
		}   
		
	}
}

