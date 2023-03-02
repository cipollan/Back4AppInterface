package mainPackage;


import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import com.google.gson.Gson; 
import com.google.gson.GsonBuilder; 

import httpConnector.MyHttpGateWay;
import model.*;

public class MagicMain extends Thread {
	
	/**
	 * @param args
	 * @throws IOException 
	 * 
	 */
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
		String rootPath = Thread.currentThread().getContextClassLoader().getResource("").getPath();
		String appConfigPath = rootPath + "config.properties";
		String catalogConfigPath = rootPath + "catalog";
		
		System.out.println ("MagicMain "  );
		 
		Properties appProps = new Properties();
		appProps.load(new FileInputStream(appConfigPath));

		Properties catalogProps = new Properties();
		catalogProps.load(new FileInputStream(appConfigPath));
		
		 
			
			System.out.println ("rootPath			:" + rootPath);
			System.out.println ("catalogConfigPath->:" + catalogConfigPath);
			System.out.println ("appConfigPath		:" + appConfigPath);
			System.out.println ("app.dbname		:" + catalogProps.getProperty("app.dbname"));
			System.out.println ("app.appid.tag			:" + catalogProps.getProperty("app.appid.tag"));
			System.out.println ("app.appid		:" + catalogProps.getProperty("app.appid"));
			System.out.println ("app.restapi.tag			:" + catalogProps.getProperty("app.restapi.tag"));
			System.out.println ("app.restapi.key			:" + catalogProps.getProperty("app.restapi.key"));
			 
			MyHttpGateWay myMyHttpGateWay = new MyHttpGateWay(catalogProps);
		
		 User user = new User();
		 user.setNome("Andre");
		 user.setTel(23452);
		 
	      //Creating the ObjectMapper object
		 
		 GsonBuilder builder = new GsonBuilder(); 
		 builder.setPrettyPrinting(); 
		      
		 Gson gson = builder.create(); 
		 String jsonString = gson.toJson(user); 
		      
	      
	      System.out.println("----------------------------- JSON STRING --------------------------------------");  
	      System.out.println(jsonString);  
	      System.out.println("--------------------------------------------------------------------------------");
		
		try {
			int errCode = myMyHttpGateWay.doCallRest();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		try {
			int errCode = myMyHttpGateWay.doCallRest2();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
			
	}

}
