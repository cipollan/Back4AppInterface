package httpConnector;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.Properties;

import httputil.GenericResponse;
import model.Address;
import model.AddressResponse;
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
		
		
		UserResponse uur = new UserResponse();
		User u = new User();
		GenericResponse<UserResponse> ur = new GenericResponse<UserResponse>(uur);
		List<User> userList = new ArrayList<>();
		errCode = myHttpClient.doGetApi(ur,  u) ;
		
		
		for (ListIterator<User> iter =  ur.getT().getResults().listIterator(); iter.hasNext(); ) {
        	User element = iter.next();
            // 1 - can call methods of element
            // 2 - can use iter.remove() to remove the current element from the list
            // 3 - can use iter.add(...) to insert a new element into the list
            //     between element and iter->next()
            // 4 - can use iter.set(...) to replace the current element

        	System.out.println ( " MyHttpClient.doCallApi zz element <" + element.getObjectId() + ">" );
         
        	System.out.println ( " MyHttpClient.doCallApi zz element.getCreatedAt <" + element.getCreatedAt() + ">" );
        	System.out.println ( " MyHttpClient.doCallApi zz element.updatedAt    <" + element.getUpdatedAt() + ">" );
        	System.out.println ( " MyHttpClient.doCallApi zz element.getLastName  <" + element.getLastName() + ">" );
        	System.out.println ( " MyHttpClient.doCallApi zz element.getId        <" + element.getId() + ">" );
        	System.out.println ( " MyHttpClient.doCallApi zz element.getAge       <" + element.getAge() + ">" );
        	System.out.println ( " MyHttpClient.doCallApi zz element.getPhone     <" + element.getPhone() + ">" );
        	System.out.println ( " MyHttpClient.doCallApi zz element.getTel       <" + element.getTel() + ">" );
        	System.out.println ( " MyHttpClient.doCallApi zz element.getNome       <" + element.getNome() + ">" );
        
        	 
        }
        
		
		
		
		Address a = new Address();
		AddressResponse arr = new AddressResponse();
		MyHttpClient<AddressResponse> myHttpClientAddr = new MyHttpClient<AddressResponse>(myUrl+"MyUser",catalogProps);
		myHttpClientAddr.setStubsApiBaseUri(myUrl+"Address");
		GenericResponse<AddressResponse> ar = new GenericResponse<AddressResponse>(arr);
		errCode = myHttpClientAddr.doGetApi(ar,  a) ;
		
		for (ListIterator<Address> iter =  ar.getT().getResults().listIterator(); iter.hasNext(); ) {
        	Address element = iter.next();
             

        	System.out.println ( " MyHttpClient.doMapResponseToAddressResp<" + element.via + ">" );
        	System.out.println ( " MyHttpClient.doMapResponseToAddressResp<" + element.objectId + ">" );
        	System.out.println ( " MyHttpClient.doMapResponseToAddressResp<" + element.via + ">" );
        	
         /*
        	System.out.println ( " MyHttpClient.doCallApi zz element.getCreatedAt <" + element.getCreatedAt() + ">" );
        	System.out.println ( " MyHttpClient.doCallApi zz element.updatedAt    <" + element.getUpdatedAt() + ">" );
        	System.out.println ( " MyHttpClient.doCallApi zz element.getLastName  <" + element.getLastName() + ">" );
        	System.out.println ( " MyHttpClient.doCallApi zz element.getId        <" + element.getId() + ">" );
        	System.out.println ( " MyHttpClient.doCallApi zz element.getAge       <" + element.getAge() + ">" );
        	System.out.println ( " MyHttpClient.doCallApi zz element.getPhone     <" + element.getPhone() + ">" );
        	System.out.println ( " MyHttpClient.doCallApi zz element.getTel       <" + element.getTel() + ">" );
        	System.out.println ( " MyHttpClient.doCallApi zz element.getNome       <" + element.getNome() + ">" );
        */
        	 
        }
		
		
		
		
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
