package httpConnector;

import java.util.ArrayList;



import java.util.List;
import java.util.ListIterator;
import java.util.Properties;

import org.apache.http.client.methods.HttpDelete;

import httputil.GenericResponse;
import model.Address;
import model.AddressResponse;
import model.User;
import model.UserResponse;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;



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
		
		
		UserResponse uur = new UserResponse();
		User u = new User();
		GenericResponse<UserResponse> ur = new GenericResponse<UserResponse>(uur);
		 
		errCode = myHttpClient.doGetApi(ur,  u) ;
		
		
		for (ListIterator<User> iter =  ur.getT().getResults().listIterator(); iter.hasNext(); ) {
        	User element = iter.next();
            
        	System.out.println ( ur.getT().getResults().size() + ")  MyHttpClient.doCallApi zz element <" + element.getObjectId() + ">" );
        	System.out.println ( ur.getT().getResults().size() + ") MyHttpClient.doCallApi zz element.getCreatedAt <" + element.getCreatedAt() + ">" );
        	System.out.println ( ur.getT().getResults().size() + ") MyHttpClient.doCallApi zz element.updatedAt    <" + element.getUpdatedAt() + ">" );
        	 
        	System.out.println ( ur.getT().getResults().size() + ") MyHttpClient.doCallApi zz element.getTel       <" + element.getTel()       + ">" );
        	System.out.println ( ur.getT().getResults().size() + ") MyHttpClient.doCallApi zz element.getNome      <" + element.getNome()      + ">" );
        	
        	User uu = new User();
        	uu.setObjectId(element.getObjectId());
    		MyHttpClient<UserResponse> myHttpClientAddrD = new MyHttpClient<UserResponse>(myUrl+"MyUser/"+uu.getObjectId(),catalogProps);
    		errCode = myHttpClientAddrD.doCallApiDELETE() ;
    		System.out.println ( " myHttpClientAddrD.doCallApiDELETE       <" + errCode + ">" );
        }
        
		
		 
		
		Address a = new Address();
		AddressResponse arr = new AddressResponse();
		MyHttpClient<AddressResponse> myHttpClientAddr = new MyHttpClient<AddressResponse>(myUrl+"Address",catalogProps);
		myHttpClientAddr.setStubsApiBaseUri(myUrl+"Address");
		GenericResponse<AddressResponse> ar = new GenericResponse<AddressResponse>(arr);
		errCode = myHttpClientAddr.doGetApi(ar,  a) ;
		String objId = "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx"  ;
		
		
		Address aa = new Address();
		
		for (ListIterator<Address> iter =  ar.getT().getResults().listIterator(); iter.hasNext(); ) 
		{
        	Address element = iter.next();
             
        	System.out.println ( ar.getT().getResults().size() + ") ---------------------------------------<"  + ">" );
        	System.out.println ( ar.getT().getResults().size() + ") MyHttpClient.doMapResponseToAddressResp<" + element.via + ">" );
        	System.out.println ( ar.getT().getResults().size() + ") MyHttpClient.doMapResponseToAddressResp<" + element.objectId + ">" );
        	System.out.println ( ar.getT().getResults().size() + ") MyHttpClient.doMapResponseToAddressResp<" + element.via + ">" );
        	
        	objId = element.objectId;
    		aa.setObjectId(objId);
    		MyHttpClient<AddressResponse> myHttpClientAddrD = new MyHttpClient<AddressResponse>(myUrl+"Address/"+aa.getObjectId(),catalogProps);
    		errCode = myHttpClientAddrD.doCallApiDELETE() ;   
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
		System.out.println (  ")-> MyHttpClient.doMapResponseToAddressResp<" + strBody + ">" );
    	
		MyHttpClient<UserResponse> myHttpClientUserPost = new MyHttpClient<UserResponse>(myUrl+"MyUser",catalogProps);
		errCode = myHttpClientUserPost.doPostApi(uuurPost,user,strBody) ;   
		
		 
		System.out.println (  ")-> MyHttpClient.doMapResponseToAddressResp<" + strBody + ">" );
		
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
