
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import com.google.gson.Gson; 
import com.google.gson.GsonBuilder;
//import com.sun.org.slf4j.internal.LoggerFactory;

import connector.MongoDbConnector;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import httpConnector.MyHttpGateWay;
import httputil.AddressResponse;
import httputil.CertificateResponse;
import httputil.GenericResponse;
import httputil.UserResponse;

import model.*;
import utilPackage.HttpMethod;
import java.text.SimpleDateFormat;
import java.util.Date;
 
 




import java.util.ListIterator;

public class MagicMain  {

	
	static Logger logger = Logger.getLogger(MagicMain.class.getName());  
	
	public static void main(String[] args) throws IOException 
	{
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
	            
				// System.out.println (  ") MyHttpHub.doCallApi Size                 <" +addrResp.getT().getResults().size() + ">");
				// System.out.println (  ") MyHttpHub.doCallApi zz element           <" + element.getObjectId() + ">" );
				// System.out.println (  ") MyHttpHub.doCallApi zz element.getComune <" + element.getComune() + ">" );
				// System.out.println (  ") MyHttpHub.doCallApi zz element.getVia    <" + element.getVia() + ">" );
	        
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
			            
						// System.out.println (  ") MyHttpHub.doCallApi Size                    <" +userResp.getT().getResults().size() + ">");
						// System.out.println (  ") MyHttpHub.doCallApi zz element.getObjectId  <" + element.getObjectId() + ">" );
						// System.out.println (  ") MyHttpHub.doCallApi zz element.getCognome   <" + element.getCognome()  + ">" );
						// System.out.println (  ") MyHttpHub.doCallApi zz element.getNome      <" + element.getNome()     + ">" );
						// System.out.println (  ") MyHttpHub.doCallApi zz element.getCreatedAt <" + element.getCreatedAt() + ">" );
			        
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
			            
						// System.out.println (  ") MyHttpHub.doCallApi Size                         <" + certifResp.getT().getResults().size() + ">");
						// System.out.println (  ") MyHttpHub.doCallApi zz element.getObjectId       <" + element.getCertifNum() + ">" );
						// System.out.println (  ") MyHttpHub.doCallApi zz element.getCertiType      <" + element.getCertiType()  + ">" );
						// System.out.println (  ") MyHttpHub.doCallApi zz element.getDateOfEmission <" + element.getDateOfEmission()   + ">" );
						// System.out.println (  ") MyHttpHub.doCallApi zz element.getCertifNum      <" + element.getCertifNum()   + ">" );
			        	
			        }
					
				} catch (Exception e1) 
				{
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				 

				configure();
				configure2();
				
				 logger.debug ( "Prima di MongoDbConnector <" );
				 
				 try {
					 MongoDbConnector nMongoDbConnectorA = new MongoDbConnector();
					 nMongoDbConnectorA.doMonGoConnectA("xxxxxxxxxxxxxxxxx");
				 }
				 catch (Exception e1) 
				 {
					 // TODO Auto-generated catch block
					 logger.debug ( "Exception <" + e1.getMessage());
				 }
				 
				 try {
					 MongoDbConnector nMongoDbConnector = new MongoDbConnector();
					 nMongoDbConnector.doMonGoConnect("nMongoDbConnector");
				 }
				 catch (Exception e2) 
				 {
					 logger.debug ( "Exception <" + e2.getMessage());
				 }
				  
				 
				 try {
					 MongoDbConnector nMongoDbConnectorB = new MongoDbConnector();
					 nMongoDbConnectorB.doMonGoConnectB("nMongoDbConnector");
				 }
				 catch (Exception e3) 
				 {
					 logger.debug ( "Exception <" + e3.getMessage());
				 }
				  
				 
				 
				String nomeMiaBambina = "Angelica";
				logger.info ( "------------------<" + nomeMiaBambina);
                 
				 
				 int lato1 = 9;
				 int lato2 = 23;

                 int perimetro = 0;
                 
                 perimetro = lato2 + (lato1  * 2);
                 
                 if (lato2 >= (lato1  * 2)) 
                 {
                	 logger.info ( "ERRORE :" + lato2);
      	         }
                 else
                 {
    			     logger.info ( "------------------<" + nomeMiaBambina);
    			     logger.info ( "lato1:" + lato1);
    			     logger.info ( "lato2:" + lato2);
    			     logger.info ( "perimetro:" + perimetro);
                 }
                	 
				 
					
			    
			    // logger.info ( "------------------<" + nomeMiaBambina);
			    // logger.info ( "------------------<" + nomeMiaBambina);
			     
					
				
				
				System.out.println ("MagicMain rootPath:"  + rootPath );
				logger.info ( "------------------<" + HttpMethod.POST); 
				logger.info ( "------------------<" + HttpMethod.GET); 
				logger.info ( "------------------<" + HttpMethod.UPDATE); 
        
				
				
        logger.debug ( " END MagicMain ");
        
			
	}
	
	public static void configure() {
	    ClassLoader cl = Thread.currentThread().getContextClassLoader();
	    
	    
	    try {
	        String[] names = { "ch.qos.logback.classic.LoggerContext", "ch.qos.logback.classic.joran.JoranConfigurator","ch.qos.logback.classic.util.ContextInitializer" };
	        for (String name : names) 
	        {
	        	System.out.println("Prima name--->" + name);
	            Class.forName(name, false, cl);
	            System.out.println("Dopo name--->" + name);
	        }
	       
	         
	    } catch (ClassNotFoundException e) {
	        System.err.println("WLogC ClassNotFoundException: Logback JARs are missing in classpath<" + e.getMessage());
	    } catch (NoClassDefFoundError e) {
	        System.err.println("WLogC NoClassDefFoundError : Logback JARs are missing in classpath<" + e.getMessage());
	    } catch (Throwable e) {
	        e.printStackTrace();
	    }
	}
	
	public static void configure2() {
	    ClassLoader cl = Thread.currentThread().getContextClassLoader();
	    
	    
	    try {
	        String[] names = { "ch.qos.logback.classic.util.ContextInitializer.autoConfig" };
	        for (String name : names) 
	        {
	        	System.out.println("Prima name--->" + name);
	            Class.forName(name, false, cl);
	            System.out.println("Dopo name--->" + name);
	        }
	       
	         
	    } catch (ClassNotFoundException e) {
	        System.err.println("WLogC ClassNotFoundException: Logback JARs are missing in classpath<" + e.getMessage());
	    } catch (NoClassDefFoundError e) {
	        System.err.println("WLogC NoClassDefFoundError: Logback JARs are missing in classpath<" + e.getMessage());
	    } catch (Throwable e) {
	        e.printStackTrace();
	    }
	}

}
