package httpConnector;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import model.User;
import model.UserResponse;

public class MyHttpGateWay {
	
	//private String myUrl = "https://myonionlab.altervista.org/php/myserviceprovider.php?TagFunct=CHECKCONNECTION&MachineName=MachineName&UserName=USER_NAME&Pwd=Ciao";
	 
	//private String myUrl = "https://parseapi.back4app.com/classes/Access";
	private String myUrl = "";
	
	private Properties catalogProps;
	 
	
	private String user = "cipollan@gmail.com";
	private String password = "Cippop8@Cippop8@";
	
	public MyHttpGateWay() {
		super();
		System.out.println ( " MyHttpGateWay CONSTRUCTOR:" + myUrl); 
	}
	
	public MyHttpGateWay(Properties icatalogProps) {
		super();
		 
		System.out.println ("MyHttpGateWay (constructor) BEGIN" );
		setCatalogProps(icatalogProps);
		setMyUrl(catalogProps.getProperty("app.dbname"));
		System.out.println ( " MyHttpGateWay CONSTRUCTOR "); 
		
		System.out.println ("app.dbname		:<" + catalogProps.getProperty("app.dbname")+">");
		System.out.println ("app.appid.tag			:" + catalogProps.getProperty("app.appid.tag"));
		System.out.println ("app.appid		:" + catalogProps.getProperty("app.appid"));
		System.out.println ("app.restapi.tag			:" + catalogProps.getProperty("app.restapi.tag"));
		System.out.println ("app.restapi.key			:" + catalogProps.getProperty("app.restapi.key"));
		 
		System.out.println ("MyHttpGateWay (constructor) END" );
	}
	
	public int doCallRest() throws Exception
	{
		int errCode = 200;
		System.out.println ( " BEGIN MyHttpGateWay.doCallRest  "  ); 
		 
		String token     = InvokeRESTService.getToken(myUrl+"/MyUser", catalogProps,user, password) ;
		String jsonInput = "Ciao:Ciao";
		
		System.out.println ( " END MyHttpGateWay.doCallRest token:" + token); 
		String response     = InvokeRESTService.invokeRestApi( "GET", myUrl+"/MyUser", catalogProps,user, password) ;
		//String response =  InvokeRESTService.invokeRestApi("GET", myUrl, catalogProps,token,  jsonInput);
		
		System.out.println ( " END MyHttpGateWay.doCallRest response:" + response); 
		return errCode;
	}
	
	public <T> int doCallRest2() throws Exception
	{
		int errCode = 200;
		System.out.println ( " BEGIN MyHttpGateWay.doCallRest2  " ); 
		 
		MyHttpClient<UserResponse> myHttpClient = new MyHttpClient<UserResponse>(myUrl+"MyUser",catalogProps);
		 
		myHttpClient.setStubsApiBaseUri(myUrl+"MyUser");
		myHttpClient.setCatalogProps(catalogProps);
		errCode = myHttpClient.doCallApi();
		
		System.out.println ( " END MyHttpGateWay.doCallRest2  " + errCode); 
		
		
		 
		 
		myHttpClient.setStubsApiBaseUri(myUrl+"Address");
		myHttpClient.setCatalogProps(catalogProps);
		errCode = myHttpClient.doCallApi();
		
		
		
		
		
		
		return errCode;
	}

	public synchronized String getMyUrl() {
		return myUrl;
	}

	public synchronized void setMyUrl(String myUrl) {
		this.myUrl = myUrl;
	}

	public synchronized Properties getCatalogProps() {
		return catalogProps;
	}

	public synchronized void setCatalogProps(Properties catalogProps) {
		this.catalogProps = catalogProps;
	}
	
	

}
