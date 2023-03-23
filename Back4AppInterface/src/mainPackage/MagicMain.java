package mainPackage;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import com.google.gson.Gson; 
import com.google.gson.GsonBuilder;
import org.slf4j.Logger.*;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import httpConnector.MyHttpGateWay;
import httpConnector.MyHttpHub;
import httputil.AddressResponse;
import httputil.CertificateResponse;
import httputil.GenericResponse;
import httputil.UserResponse;
import jdk.jfr.Timestamp;
import model.*;
import utilPackage.HttpMethod;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.ListIterator;

public class MagicMain extends Thread {
	
	
	static Logger logger = Logger.getLogger(MagicMain.class.getName());  
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
		logger.debug ( " BEGIN MagicMain ");
		
		String log4jConfigFile = System.getProperty("user.dir") + File.separator + "resource\\log4j.properties";
		logger.debug ( " BEGIN MagicMain log4jConfigFile:" + log4jConfigFile);
		
        PropertyConfigurator.configure(log4jConfigFile);
        
    
		
		
		String rootPath = Thread.currentThread().getContextClassLoader().getResource("").getPath();
		String appConfigPath = rootPath + "config.properties";
		String catalogConfigPath = rootPath + "catalog";
		
		appConfigPath = System.getProperty("user.dir") + File.separator + "resource\\config.properties";
		
		System.out.println ("MagicMain appConfigPath:"  + appConfigPath );
		System.out.println ("MagicMain rootPath:"  + rootPath );
		 
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
			logger.debug ( "  MagicMain myMyHttpGateWay.doCallRest ");
			int errCode = myMyHttpGateWay.doCallRest();
			logger.debug ( "  MagicMain myMyHttpGateWay.doCallRest ");
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		try {
			String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss.SSS").format(new java.util.Date());
			
			User u = new User();
			u.setCognome	("Cognome-" 	+ timeStamp);
			u.setNome		("Nome-"       	+ timeStamp);
			u.setCreatedAt	(timeStamp);
			u.setUpdatedAt	(timeStamp);
			u.setTel		(123345);
			
			logger.debug("User:" + u.getCognome());
			logger.debug("User:" + u.getNome());
			logger.debug("User:" + u.getTel());
			
			logger.debug ( "  Prima MagicMain myMyHttpGateWay.doCallRest2 " + u.getCognome());
			int errCode = myMyHttpGateWay.doCallRest2(u);
			logger.debug ( "  Dopo MagicMain myMyHttpGateWay.doCallRest2 " + u.getCognome());
			
			
			logger.debug("User:" + u.getCognome());
			logger.debug("User:" + u.getNome());
			logger.debug("User:" + u.getTel());
			
			
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	
	
		
		
	// Creo Indirizzo
		try {
			int errCode 		= 0;
			Address 		a 	= new Address("Via Tito Vignoli", "44", "", "Milano", "MI",
												"Italia","Now");
			AddressResponse 		ar 	= new AddressResponse();
			
			MyHttpGateWay myMyHttpGateWay2 = new MyHttpGateWay(catalogProps);
			
			GenericResponse<AddressResponse> addrResp = new GenericResponse<AddressResponse>(ar);
			
			errCode 		= myMyHttpGateWay2.doPOST(a);
			errCode 		= myMyHttpGateWay2.doGET(addrResp, a);
			errCode 		= myMyHttpGateWay2.doUPDATE(a);
			//errCode 		= myMyHttpGateWay2.doDELETE(a);
			
			for (ListIterator<Address> iter =  addrResp.getT().getResults().listIterator(); iter.hasNext(); ) {
				Address element = iter.next();
	            
				System.out.println (  ") MyHttpHub.doCallApi Size                 <" +addrResp.getT().getResults().size() + ">");
	        	System.out.println (  ") MyHttpHub.doCallApi zz element           <" + element.getObjectId() + ">" );
	        	System.out.println (  ") MyHttpHub.doCallApi zz element.getComune <" + element.getComune() + ">" );
	        	System.out.println (  ") MyHttpHub.doCallApi zz element.getVia    <" + element.getVia() + ">" );
	        
	        }
			
			
			  
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		 
		// Creo Utente
		
				try {
					int errCode 		= 0;
					User 		a 	= new User();
					UserResponse 		ar 	= new UserResponse();
					
					MyHttpGateWay myMyHttpGateWay2 = new MyHttpGateWay(catalogProps);
					
					GenericResponse<UserResponse> userResp = new GenericResponse<UserResponse>(new UserResponse());
					
					a.setCognome("Cipo");
					a.setNome("Andre");
					a.setTel(1234567);
					
					errCode 		= myMyHttpGateWay2.doPOST(a);
					errCode 		= myMyHttpGateWay2.doGET(userResp, a);
					errCode 		= myMyHttpGateWay2.doUPDATE(a);
					errCode 		= myMyHttpGateWay2.doDELETE(a);
					
					for (ListIterator<User> iter =  userResp.getT().getResults().listIterator(); iter.hasNext(); ) 
					{
						User element = iter.next();
			            
						System.out.println (  ") MyHttpHub.doCallApi Size                    <" +userResp.getT().getResults().size() + ">");
			        	System.out.println (  ") MyHttpHub.doCallApi zz element.getObjectId  <" + element.getObjectId() + ">" );
			        	System.out.println (  ") MyHttpHub.doCallApi zz element.getCognome   <" + element.getCognome()  + ">" );
			        	System.out.println (  ") MyHttpHub.doCallApi zz element.getNome      <" + element.getNome()     + ">" );
			        	System.out.println (  ") MyHttpHub.doCallApi zz element.getCreatedAt <" + element.getCreatedAt() + ">" );
			        
			        }
					
					
					  
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				 
				
				
				// CreoCertificato
				
				try {
					int errCode 		= 0;
					Certificate 		a 	= new Certificate(jsonString, errCode, null);
					CertificateResponse ar 	= new CertificateResponse();
				 
					 
					
					MyHttpGateWay myMyHttpGateWay2 = new MyHttpGateWay(catalogProps);
					
					GenericResponse<CertificateResponse> certifResp = new GenericResponse<CertificateResponse>(new CertificateResponse());
					String d  = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
					a.setCertifNum(987653);
					a.setCertiType("GREEN");
					a.setDateOfEmission(d);
					
					errCode 		= myMyHttpGateWay2.doPOST(a);
					errCode 		= myMyHttpGateWay2.doGET(certifResp, a);
					errCode 		= myMyHttpGateWay2.doUPDATE(a);
					errCode 		= myMyHttpGateWay2.doDELETE(a);
					
					System.out.println (  ") MyHttpHub.doCallApi Size                    <" + certifResp.getT().getResults().size() + ">");
					
					for (ListIterator<Certificate> iter =  certifResp.getT().getResults().listIterator(); iter.hasNext(); ) 
					{
						Certificate element = iter.next();
			            
						System.out.println (  ") MyHttpHub.doCallApi Size                         <" + certifResp.getT().getResults().size() + ">");
			        	System.out.println (  ") MyHttpHub.doCallApi zz element.getObjectId       <" + element.getCertifNum() + ">" );
			        	System.out.println (  ") MyHttpHub.doCallApi zz element.getCertiType      <" + element.getCertiType()  + ">" );
			        	System.out.println (  ") MyHttpHub.doCallApi zz element.getDateOfEmission <" + element.getDateOfEmission()   + ">" );
			        	System.out.println (  ") MyHttpHub.doCallApi zz element.getCertifNum      <" + element.getCertifNum()   + ">" );
			        	
			        	 
			        
			        }
					
					
					  
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				 
				
				
				
		
				System.out.println ("MagicMain rootPath:"  + rootPath );
        logger.info ( "------------------<" + HttpMethod.POST); 
        logger.info ( "------------------<" + HttpMethod.GET); 
        logger.info ( "------------------<" + HttpMethod.UPDATE); 
        logger.debug ( " END MagicMain ");
        
			
	}

}