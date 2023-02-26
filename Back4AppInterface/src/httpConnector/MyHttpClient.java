package httpConnector;

import java.io.*;
import java.lang.reflect.Type;
import java.net.*;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.Properties;

import javax.xml.xpath.*;
import org.apache.http.*;
import org.apache.http.client.*;
import org.apache.http.client.methods.*;
import org.apache.http.client.utils.*;
import org.apache.http.entity.*;
import org.apache.http.impl.client.*;
import org.apache.http.util.*;
import org.json.JSONArray;
import org.json.JSONObject;
import org.xml.sax.*;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import model.User;
import model.UserInfo;
import model.UserResponse;

public class MyHttpClient<T> {
	
	private T t;
	
	
	public synchronized T getT() {
		return t;
	}

	public synchronized void setT(T t) {
		this.t = t;
	}

	public synchronized HttpClient getClient() {
		return client;
	}

	public synchronized void setClient(HttpClient client) {
		this.client = client;
	}

	public synchronized HttpResponse getHttpResponse() {
		return httpResponse;
	}

	public synchronized void setHttpResponse(HttpResponse httpResponse) {
		this.httpResponse = httpResponse;
	}

	public synchronized void showHeaders() {
		// Headers
        Header[] headers = httpResponse.getAllHeaders();
        for (int i = 0; i < headers.length; i++) 
        {
            System.out.println(i+")Header:" + headers[i]);
        }
		 
	}
	
	public synchronized int getErrCode() {
		return errCode;
	}

	public synchronized void setErrCode(int errCode) {
		this.errCode = errCode;
	}

	public synchronized HttpGet getGetStubMethod() {
		return getStubMethod;
	}

	public synchronized void setGetStubMethod(HttpGet getStubMethod) {
		this.getStubMethod = getStubMethod;
	}

	public synchronized HttpPost getPostStubMethod() {
		return postStubMethod;
	}

	public synchronized void setPostStubMethod(HttpPost postStubMethod) {
		this.postStubMethod = postStubMethod;
	}

	public synchronized HttpDelete getDeleteStubMethod() {
		return deleteStubMethod;
	}

	public synchronized void setDeleteStubMethod(HttpDelete deleteStubMethod) {
		this.deleteStubMethod = deleteStubMethod;
	}

	public synchronized URIBuilder getBuilder() {
		return builder;
	}

	public synchronized void setBuilder(URIBuilder builder) {
		this.builder = builder;
	}

	private int 		errCode 			= 200;
	private HttpGet 	getStubMethod 		= null;
	private HttpPost 	postStubMethod 		= null;
	private HttpDelete 	deleteStubMethod	= null;
	private HttpMessage httpMsg 			= null;
	private HttpClient client 				= null;
	private URIBuilder builder 				= null;
	private HttpResponse httpResponse 	= null;
	
	public MyHttpClient() {
		super();
		 
	}
	
	public MyHttpClient (String stubsApiBaseUri, Properties catalogProps) throws URISyntaxException {
		super();
		
		this.stubsApiBaseUri  = stubsApiBaseUri;
		this.catalogProps = catalogProps;
		this.client = HttpClients.createDefault();
		this.builder = new URIBuilder(stubsApiBaseUri);
		 
        String listStubsUri = builder.build().toString();
        
        getStubMethod = new HttpGet(listStubsUri); 
        getStubMethod.setHeader("Content-Type", "application/json");
        getStubMethod.setHeader(catalogProps.getProperty("app.appid.tag") ,catalogProps.getProperty("app.appid"));
        getStubMethod.setHeader(catalogProps.getProperty("app.restapi.tag"),catalogProps.getProperty("app.restapi.key"));
		
        postStubMethod = new HttpPost(listStubsUri); 
        postStubMethod.setHeader("Content-Type", "application/json");
        postStubMethod.setHeader(catalogProps.getProperty("app.appid.tag") ,catalogProps.getProperty("app.appid"));
        postStubMethod.setHeader(catalogProps.getProperty("app.restapi.tag"),catalogProps.getProperty("app.restapi.key"));
		
        deleteStubMethod = new HttpDelete(listStubsUri); 
        deleteStubMethod.setHeader("Content-Type", "application/json");
        deleteStubMethod.setHeader(catalogProps.getProperty("app.appid.tag") ,catalogProps.getProperty("app.appid"));
        deleteStubMethod.setHeader(catalogProps.getProperty("app.restapi.tag"),catalogProps.getProperty("app.restapi.key"));
		
        
        System.out.println ( " " + catalogProps.getProperty("app.appid.tag") );
        System.out.println ( " " + catalogProps.getProperty("app.appid") );
        System.out.println ( " " + catalogProps.getProperty("app.restapi.tag") );
        System.out.println ( " " + catalogProps.getProperty("app.restapi.key") );
        
        
	}

	public String stubsApiBaseUri = "";
	public Properties  catalogProps;
	
	public int doCallApiPOST()
	{
		int errCode = 200;
		
		return errCode;
		
	}
	
	
	public int doMapResponseToUser(String response, List<User> objList ) 
	{
		errCode = 200;
		
		Gson gsonL = new Gson();
	
	    UserResponse userResponse = gsonL.fromJson(response, UserResponse.class);
       
        System.out.println ( " MyHttpClient.doMapResponseToUser yy responseBody <" + response + ">" );
        
        
        for (ListIterator<User> iter =  userResponse.getResults().listIterator(); iter.hasNext(); ) {
        	User element = iter.next();
            // 1 - can call methods of element
            // 2 - can use iter.remove() to remove the current element from the list
            // 3 - can use iter.add(...) to insert a new element into the list
            //     between element and iter->next()
            // 4 - can use iter.set(...) to replace the current element

        	System.out.println ( " MyHttpClient.doCallApi zz element <" + element.getObjectId() + ">" );
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
        	boolean add = objList.add((User) element);
        }
        
		return errCode;
	}
	
	public int doMapResponse(List<T> objList) 
	{
		errCode = 200;
		
		return errCode;
		
	}
	
	
	public int doGetApi(List<T> objList, Object u) 
	{
		errCode = 200;
    
		System.out.println ( " BEGIN MyHttpClient.doCallApi   :"  + stubsApiBaseUri );
		System.out.println ( " BEGIN MyHttpClient.c.getClass():"  + u.getClass() );
		 
    
	try 
	{
		System.out.println ( " BEGIN MyHttpClient.doGetApi getStubMethod:"  + getStubMethod );
        httpResponse  = client.execute(getStubMethod);
        System.out.println ( "  MyHttpClient.doCallApi 3 "  );
        errCode = httpResponse.getStatusLine().getStatusCode();
        System.out.println ( "  MyHttpClient.doCallApi 4 errCode:" + errCode  );
      
        if (errCode < 200 || errCode >= 300) {
           // Handle non-2xx status code
        	 System.out.println(errCode);
            return errCode;
        }
        
        showHeaders();
        String responseBody = EntityUtils.toString(httpResponse.getEntity());
        
        errCode = doMapResponseToUser(responseBody, (List<User>) objList ) ;
        
     // Define the type of the list of User objects
        Type userListType = new TypeToken<List<T>>(){}.getType();
        System.out.println ( " MyHttpClient.doCallApi xx userListType <" + userListType + ">" );
        System.out.println ( " MyHttpClient.doCallApi xx responseBody <" + responseBody + ">" );
        System.out.println ( " MyHttpClient.doCallApi xx responseBody <" + responseBody.getClass() + ">" );
        System.out.println ( " MyHttpClient.doCallApi               u <" + u.getClass() + ">" );
      
    
       // System.out.println ( " MyHttpClient.doCallApi zz responseBody <" + responseBody + ">" ); 
       // System.out.println ( " MyHttpClient.doCallApi 4 responseBody<" + responseBody + ">" );
        System.out.println ( " MyHttpClient.doCallApi 4 errCode<" + errCode + ">" );
        System.out.println(errCode);
        
        
       
	} catch (Exception e  ) 
	{
		// TODO Auto-generated catch block
		errCode = -1;
		e.printStackTrace();
	}
    
	System.out.println ( " END MyHttpClient.doCallApi  <" + errCode );
   
 
		
	 return this.errCode;
	}
	
	public int doPostApi(Object postObj) 
	{
		return this.errCode;
	}
	
	public int doDeleteApi(Object deleteObj) 
	{
		return this.errCode;
	}
	
	public int doCallApi() 
	{
		errCode = 200;
        System.out.println ( " BEGIN MyHttpClient.doCallApi  "  + stubsApiBaseUri );
        List<User> objList = new ArrayList<>();
       
		try 
		{
			User u = new User();
			errCode = doGetApi((List<T>) objList, u) ;
			System.out.println ( " BEGIN MyHttpClient.doCallApi objList.size() "  + objList.size() );
	        
	       
		} catch (Exception e  ) 
		{
			// TODO Auto-generated catch block
			errCode = -1;
			e.printStackTrace();
		}
        
		System.out.println ( " END MyHttpClient.doCallApi  <" + errCode );
       
		return errCode;
	}

	public synchronized String getStubsApiBaseUri() 
	{
		return stubsApiBaseUri;
	}

	public synchronized void setStubsApiBaseUri(String stubsApiBaseUri) 
	{
		this.stubsApiBaseUri = stubsApiBaseUri.replaceAll("\\s+","");
	}

	public synchronized Properties getCatalogProps() {
		return catalogProps;
	}

	public synchronized void setCatalogProps(Properties catalogProps) {
		this.catalogProps = catalogProps;
	}

	public HttpMessage getHttpMsg() {
		return httpMsg;
	}

	public void setHttpMsg(HttpMessage httpMsg) {
		this.httpMsg = httpMsg;
	}
	

}
