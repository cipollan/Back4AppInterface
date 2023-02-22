package mainPackage;

import java.io.BufferedReader;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;

import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.regex.*;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.github.lbovolini.mapper.ObjectMapper;
 
import com.google.gson.Gson; 
import com.google.gson.GsonBuilder; 

import httpConnector.MyHttpGateWay;
import model.*;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.fasterxml.jackson.*;

import utilPackage.*;

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
		 user.setId(001);
		 user.setName("Andre");
		 user.setAge(53);
		 user.setPhone("123456789101112131415");
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
