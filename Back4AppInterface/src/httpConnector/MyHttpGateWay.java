package httpConnector;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ListIterator;
import java.util.Properties;

import org.apache.http.client.methods.HttpDelete;
import org.apache.log4j.Logger;

import httputil.AddressResponse;
import httputil.GenericResponse;
import httputil.UserResponse;
import model.Address;
import model.User;
import utilPackage.HttpMethod;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;



public class MyHttpGateWay {
	
 
	static Logger logger = Logger.getLogger(MyHttpGateWay.class.getName()); 
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
	
	
	public <T> int doCallRest3(Object o) throws Exception
	{
		int errCode = 200;
		Class<?> objClass = o.getClass();
		System.out.println ( " BEGIN MyHttpGateWay.doCallRest3  " + objClass); 
		
		// Supponiamo Address
		 
		MyHttpHub<UserResponse> myHttpClient = new MyHttpHub<UserResponse>(myUrl+objClass,catalogProps);
		 
		myHttpClient.setStubsApiBaseUri(myUrl+objClass);
		myHttpClient.setCatalogProps(catalogProps);
		 
		
		System.out.println ( " END MyHttpGateWay.doCallRest3  " + errCode); 
		myHttpClient.setStubsApiBaseUri(myUrl+objClass);
		myHttpClient.setCatalogProps(catalogProps);
		myHttpClient.setHttpMethod(HttpMethod.POST);
		errCode = myHttpClient.doCallApi();
		
		
	
		Address a = new Address();
		AddressResponse arr = new AddressResponse();
		MyHttpHub<AddressResponse> myHttpClientAddr = new MyHttpHub<AddressResponse>(myUrl+"Address",catalogProps);
		myHttpClientAddr.setStubsApiBaseUri(myUrl+"Address");
		GenericResponse<AddressResponse> ar = new GenericResponse<AddressResponse>(arr);
		myHttpClientAddr.setHttpMethod(HttpMethod.GET);
		errCode = myHttpClientAddr.doGetApi(ar,  a) ;
		
		
		Address aa = new Address();
		
		for (ListIterator<Address> iter =  ar.getT().getResults().listIterator(); iter.hasNext(); ) 
		{
        	Address element = iter.next();
             
        	System.out.println ( ar.getT().getResults().size() + ") ---------------------------------------<"  + ">" );
        	System.out.println ( ar.getT().getResults().size() + ") MyHttpHub.doMapResponseToAddressResp<" + element.via + ">" );
        	System.out.println ( ar.getT().getResults().size() + ") MyHttpHub.doMapResponseToAddressResp<" + element.getObjectId() + ">" );
        	System.out.println ( ar.getT().getResults().size() + ") MyHttpHub.doMapResponseToAddressResp<" + element.via + ">" );
        	
        	Object objId = element.getObjectId();
    		//aa.setObjectId(objId);
    		//MyHttpHub<AddressResponse> myHttpClientAddrD = new MyHttpHub<AddressResponse>(myUrl+"Address/"+aa.getObjectId(),catalogProps);
    		//errCode = myHttpClientAddrD.doCallApiDELETE() ;   
        }
		
		  // 2021-03-24 16:48:05.591
		  Timestamp timestamp = new Timestamp(System.currentTimeMillis());

		  // 2021-03-24 16:48:05.591
		  Date date = new Date();
		  Timestamp timestamp2 = new Timestamp(date.getTime());

		  // convert Instant to Timestamp
		  Timestamp ts = Timestamp.from(Instant.now());

		  // convert ZonedDateTime to Instant to Timestamp
		  Timestamp ts2 = Timestamp.from(ZonedDateTime.now().toInstant()) ;

		  // convert Timestamp to Instant
		  Instant instant = ts.toInstant();
		  
		
		AddressResponse aaar = new AddressResponse();
		  
		Address address = new Address();
		address.setCivico("33339");
		address.setComune("DaEclipse");
		address.setProvincia("MI");
		address.setStato("Italia");
		address.setWhen(timestamp2.toString());
		address.setVia("viamia");
		
		GenericResponse<AddressResponse> uuurPost = new GenericResponse<AddressResponse>(aaar);
        ObjectMapper mapper = new ObjectMapper();
		String strBody = mapper.writeValueAsString(address);
		System.out.println (  ")-> MyHttpHub.doMapResponseToAddressResp<" + strBody + ">" );
    	
		MyHttpHub<AddressResponse> myHttpClientUserPost = new MyHttpHub<AddressResponse>(myUrl+"Address",catalogProps);
		errCode = myHttpClientUserPost.doPostApi(uuurPost,address,strBody) ;   
		System.out.println (  ")-> MyHttpHub.doMapResponseToAddressResp<" + strBody + ">" );
		
		return errCode;
	}
	
	public <T> int doCallRest2(Object o) throws Exception
	{
		int errCode = 200;
		System.out.println ( " BEGIN MyHttpGateWay.doCallRest2  " ); 
		 
		MyHttpHub<UserResponse> myHttpClient = new MyHttpHub<UserResponse>(myUrl+"MyUser",catalogProps);
		 
		myHttpClient.setStubsApiBaseUri(myUrl+"MyUser");
		myHttpClient.setCatalogProps(catalogProps);
		errCode = myHttpClient.doCallApi();
		
		System.out.println ( " END MyHttpGateWay.doCallRest2  " + errCode); 
		myHttpClient.setStubsApiBaseUri(myUrl+"Address");
		myHttpClient.setCatalogProps(catalogProps);
		errCode = myHttpClient.doCallApi();
		
		
		UserResponse uur = new UserResponse();
		User u = new User();
		GenericResponse<UserResponse> ur = new GenericResponse<UserResponse>(uur);
		 
		errCode = myHttpClient.doGetApi(ur,  u) ;
		
		
		for (ListIterator<User> iter =  ur.getT().getResults().listIterator(); iter.hasNext(); ) {
        	User element = iter.next();
            
        	System.out.println ( ur.getT().getResults().size() + ")  MyHttpHub.doCallApi zz element <" + element.getObjectId() + ">" );
        	System.out.println ( ur.getT().getResults().size() + ") MyHttpHub.doCallApi zz element.getCreatedAt <" + element.getCreatedAt() + ">" );
        	System.out.println ( ur.getT().getResults().size() + ") MyHttpHub.doCallApi zz element.updatedAt    <" + element.getUpdatedAt() + ">" );
        	 
        	System.out.println ( ur.getT().getResults().size() + ") MyHttpHub.doCallApi zz element.getTel       <" + element.getTel()       + ">" );
        	System.out.println ( ur.getT().getResults().size() + ") MyHttpHub.doCallApi zz element.getNome      <" + element.getNome()      + ">" );
        	
        	User uu = new User();
        	uu.setObjectId(element.getObjectId());
    		MyHttpHub<UserResponse> myHttpClientAddrD = new MyHttpHub<UserResponse>(myUrl+o.getClass().getSimpleName()+"/"+uu.getObjectId(),catalogProps);
    		errCode = myHttpClientAddrD.doCallApiDELETE() ;
    		System.out.println ( " myHttpClientAddrD.doCallApiDELETE       <" + errCode + ">" );
        }
        
		
		UserResponse uuur = new UserResponse();
		User user = new User();
		user.setNome	("AndreaSetNome");
		user.setTel		(111111111);
		user.setCognome("AndreaSetsetCognome");
		
		uuur.getResults().add(user);
		GenericResponse<UserResponse> uuurPost = new GenericResponse<UserResponse>(uuur);
        ObjectMapper mapper = new ObjectMapper();
		String strBody = mapper.writeValueAsString(user);
		System.out.println (  ")-> MyHttpHub.doMapResponseToAddressResp<" + strBody + ">" );
    	
		MyHttpHub<UserResponse> myHttpClientUserPost = new MyHttpHub<UserResponse>(myUrl+o.getClass().getSimpleName(),catalogProps);
		errCode = myHttpClientUserPost.doPostApi(uuurPost,user,strBody) ;   
		
		 
		System.out.println (  ")-> MyHttpHub.doMapResponseToAddressResp<" + strBody + ">" );
		
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
	
	
	public <T> int doPOST(Object o) throws Exception
	{
		int errCode = 200;
		 
		System.out.println ( " BEGIN MyHttpGateWay.doPOST kk ---<" + o.getClass().getSimpleName()); 
		
		MyHttpHub<UserResponse> myHttpClient = new MyHttpHub<UserResponse>(myUrl+o.getClass().getSimpleName(),catalogProps);
		 
		myHttpClient.setStubsApiBaseUri(myUrl+o.getClass().getSimpleName());
		myHttpClient.setCatalogProps(catalogProps);
		myHttpClient.setHttpMethod(HttpMethod.POST);
		errCode = myHttpClient.doPostApi(o);
		
		System.out.println ( " END MyHttpGateWay.doCallRest2  " + errCode); 
		
		
		return errCode;
	}
	
	public  <T> int doGET(GenericResponse<T> response,Object o) throws Exception
	{
		logger.debug ( " BEGIN MyHttpHub.doGET   :" + o.getClass());
		 
		int errCode = 200;
		logger.debug ( " BEGIN MyHttpGateWay.doGET  " + o.getClass()); 
		logger.debug ( " BEGIN MyHttpGateWay.doGET  " + o.toString()); 
		
		MyHttpHub<T> myHttpClient = new MyHttpHub<T>(myUrl+o.getClass().getSimpleName(),catalogProps);
		 
		myHttpClient.setStubsApiBaseUri(myUrl+o.getClass().getSimpleName());
		myHttpClient.setCatalogProps(catalogProps);
		myHttpClient.setHttpMethod(HttpMethod.GET);
		 
		errCode = myHttpClient.doGetApi(response, o);
		
		logger.debug ( " BEGIN MyHttpHub.doGET   ErrCode :" + errCode);
		
		return  errCode;
	}
	
	
	
	
	public int  doUPDATE(Object o) throws Exception
	{
		 
		int errCode = 200;
		System.out.println ( " BEGIN MyHttpGateWay.doUPDATE <" + o.getClass()); 
		System.out.println ( " BEGIN MyHttpGateWay.doUPDATE <" + o.toString()); 
		return errCode;
	}
	
	public int doDELETE(Object o) throws Exception
	{
		 
		int errCode = 200;
		System.out.println ( " BEGIN MyHttpGateWay.doDELETE <" + o.getClass()); 
		System.out.println ( " BEGIN MyHttpGateWay.doDELETE <" + o.toString()); 
		return errCode;
	}
	
	
 
	

}
