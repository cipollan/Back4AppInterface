package httpConnector;

import java.lang.reflect.Type;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Properties;


import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpMessage;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPatch;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.*;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.*;
import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;



import org.xml.sax.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import httputil.AddressResponse;
import httputil.CertificateResponse;
import httputil.GenericResponse;
import httputil.UserResponse;

import model.Address;
import model.User;
import model.UserInfo;
import utilPackage.HttpMethod;

public class MyHttpHub<T> {
	
	static Logger logger = Logger.getLogger(MyHttpHub.class.getName());  
	
	private HttpMethod httpMethod;
	
	public synchronized HttpMethod getHttpMethod() {
		return httpMethod;
	}

	public synchronized void setHttpMethod(HttpMethod httpMethod) {
		this.httpMethod = httpMethod;
	}

	private T t;
	
	
	public synchronized T getT() {
		return t;
	}

	public synchronized void setT(T t) {
		this.t = t;
	}

	public synchronized CloseableHttpClient getClient() {
		return client;
	}

	public synchronized void setClient(CloseableHttpClient client) {
		this.client = client;
	}

	public synchronized CloseableHttpResponse getHttpResponse() {
		return httpResponse;
	}

	public synchronized void setHttpResponse(CloseableHttpResponse httpResponse) {
		this.httpResponse = httpResponse;
	}

	public synchronized void showHeaders() {
		// Headers
        Header[] headers = httpResponse.getAllHeaders();
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
	private HttpPut 	putStubMethod		= null;
	private HttpPatch 	patchStubMethod		= null;
	private HttpMessage httpMsg 			= null;
	private CloseableHttpClient client 		= null;
	private URIBuilder builder 				= null;
	private CloseableHttpResponse httpResponse 	= null;
	
	public MyHttpHub() {
		super();
		 
	}
	
	public MyHttpHub (String stubsApiBaseUri, Properties catalogProps) throws URISyntaxException 
	{
		super();
		
		this.stubsApiBaseUri  	= stubsApiBaseUri;
		this.catalogProps 		= catalogProps;
		this.client 			= HttpClients.createDefault();
		this.builder 			= new URIBuilder(stubsApiBaseUri);
		 
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
		
        
        patchStubMethod = new HttpPatch(listStubsUri); 
        patchStubMethod.setHeader("Content-Type", "application/json");
        patchStubMethod.setHeader(catalogProps.getProperty("app.appid.tag") ,catalogProps.getProperty("app.appid"));
        patchStubMethod.setHeader(catalogProps.getProperty("app.restapi.tag"),catalogProps.getProperty("app.restapi.key"));
		
        
        putStubMethod = new HttpPut(listStubsUri); 
        putStubMethod.setHeader("Content-Type", "application/json");
        putStubMethod.setHeader(catalogProps.getProperty("app.appid.tag") ,catalogProps.getProperty("app.appid"));
        putStubMethod.setHeader(catalogProps.getProperty("app.restapi.tag"),catalogProps.getProperty("app.restapi.key"));
		
        
        
        logger.info ( " " + catalogProps.getProperty("app.appid.tag") );
        logger.info ( " " + catalogProps.getProperty("app.appid") );
        logger.info ( " " + catalogProps.getProperty("app.restapi.tag") );
        logger.info ( " " + catalogProps.getProperty("app.restapi.key") );
        logger.info ( "->>>>>" + HttpMethod.POST); 
        logger.info ( "->>>>>" + HttpMethod.GET); 
         
        
	}

	public String stubsApiBaseUri = "";
	public Properties  catalogProps;
	
	public int doCallApiPOST()
	{
		int errCode = 200;
		logger.debug ( " BEGIN MyHttpHub.doCallApiPOST   :" + stubsApiBaseUri );
		
		logger.debug ( " END   MyHttpHub.doCallApiPOST   :" + errCode );
		return errCode;
		
	}
	
	
	public int doCallApiDELETE()
	{
		errCode = 200;
		logger.debug ( " BEGIN MyHttpHub.doCallApiDELETE   :" + stubsApiBaseUri );
		
		try 
		{
			logger.debug ( " BEGIN MyHttpHub.doCallApiDELETE deleteStubMethod:"  + this.deleteStubMethod );
	        httpResponse  = client.execute(deleteStubMethod);
	        
	        errCode = httpResponse.getStatusLine().getStatusCode();
	        logger.debug ( "  MyHttpHub.doCallApiDELETE 4 errCode:" + errCode  );
	      
	        if (errCode < 200 || errCode >= 300) {
	           // Handle non-2xx status code
	        	 System.out.println(errCode);
	            return errCode;
	        }
	        
	        showHeaders();
	        String responseBody = EntityUtils.toString(httpResponse.getEntity());
	        
	        logger.debug ( " END MyHttpHub.doCallApiDELETE  <" + responseBody );
	       
		} catch (Exception e  ) 
		{
			// TODO Auto-generated catch block
			errCode = -1;
			e.printStackTrace();
		}
	    
		logger.debug ( " END MyHttpHub.doCallApiDELETE  <" + errCode );
		
		return errCode;
		
	}
	
	
	
	public int _doMapResponseToAddressResp (String response, AddressResponse addrResponse ) 
	{
		logger.debug ( " MyHttpHub.doMapResponseToAddressResp <>" );
		errCode = 200;
		
		Gson gsonL = new Gson();
		addrResponse =   gsonL.fromJson(response, AddressResponse.class);
		
        for (ListIterator<Address> iter =  addrResponse.getResults().listIterator(); iter.hasNext(); ) {
        	Address element = iter.next();
            // 1 - can call methods of element
            // 2 - can use iter.remove() to remove the current element from the list
            // 3 - can use iter.add(...) to insert a new element into the list
            //     between element and iter->next()
            // 4 - can use iter.set(...) to replace the current element

        	logger.debug ( " MyHttpHub.doMapResponseToAddressResp<" + element.via + ">" );
         /*
        	System.out.println ( " MyHttpHub.doCallApi zz element.getCreatedAt <" + element.getCreatedAt() + ">" );
        	System.out.println ( " MyHttpHub.doCallApi zz element.updatedAt    <" + element.getUpdatedAt() + ">" );
        	System.out.println ( " MyHttpHub.doCallApi zz element.getLastName  <" + element.getLastName() + ">" );
        	System.out.println ( " MyHttpHub.doCallApi zz element.getId        <" + element.getId() + ">" );
        	System.out.println ( " MyHttpHub.doCallApi zz element.getAge       <" + element.getAge() + ">" );
        	System.out.println ( " MyHttpHub.doCallApi zz element.getPhone     <" + element.getPhone() + ">" );
        	System.out.println ( " MyHttpHub.doCallApi zz element.getTel       <" + element.getTel() + ">" );
        	System.out.println ( " MyHttpHub.doCallApi zz element.getNome       <" + element.getNome() + ">" );
        */
        	 
        }
        
		return errCode;
	}
	
	
	public int _doMapResponseToUser(String response, List<User> objList ) 
	{
		errCode = 200;
		
		Gson gsonL = new Gson();
	
	    UserResponse userResponse = gsonL.fromJson(response, UserResponse.class);
       
	    logger.debug ( " MyHttpHub.doMapResponseToUser yy responseBody <" + response + ">" );
        
        
        for (ListIterator<User> iter =  userResponse.getResults().listIterator(); iter.hasNext(); ) {
        	User element = iter.next();
            // 1 - can call methods of element
            // 2 - can use iter.remove() to remove the current element from the list
            // 3 - can use iter.add(...) to insert a new element into the list
            //     between element and iter->next()
            // 4 - can use iter.set(...) to replace the current element

        	logger.debug ( " MyHttpHub.doCallApi zz element <" + element.getObjectId() + ">" );
         /*
        	System.out.println ( " MyHttpHub.doCallApi zz element.getCreatedAt <" + element.getCreatedAt() + ">" );
        	System.out.println ( " MyHttpHub.doCallApi zz element.updatedAt    <" + element.getUpdatedAt() + ">" );
        	System.out.println ( " MyHttpHub.doCallApi zz element.getLastName  <" + element.getLastName() + ">" );
        	System.out.println ( " MyHttpHub.doCallApi zz element.getId        <" + element.getId() + ">" );
        	System.out.println ( " MyHttpHub.doCallApi zz element.getAge       <" + element.getAge() + ">" );
        	System.out.println ( " MyHttpHub.doCallApi zz element.getPhone     <" + element.getPhone() + ">" );
        	System.out.println ( " MyHttpHub.doCallApi zz element.getTel       <" + element.getTel() + ">" );
        	System.out.println ( " MyHttpHub.doCallApi zz element.getNome       <" + element.getNome() + ">" );
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
	
	
	
	public int doPostApi(Object u) 
	{
		errCode = 200;
		logger.debug ( " >-----------------------------<:"  );
		logger.debug ( " 1-BEGIN MyHttpHub.doPostApi---<:" + stubsApiBaseUri );
		logger.debug ( " 2-BEGIN MyHttpHub.doPostApi---<:" + u.getClass() );
		
		try 
		{
			logger.debug ( " 4-BEGIN MyHttpHub.doPostApi postStubMethod:"  + this.postStubMethod.getMethod() );
	       
		    // Set the request body
			
			ObjectMapper mapper = new ObjectMapper();
			String strBody = mapper.writeValueAsString(u);
			
			logger.debug ( " 3-BEGIN MyHttpHub.doPostApi---<:" + strBody );
	           
	        StringEntity stringEntity = new StringEntity(strBody);
	        this.postStubMethod.setEntity(stringEntity);
	        this.postStubMethod.setHeader("Content-Type", "application/json");

	
	        logger.debug ( " 5-BEGIN MyHttpHub.doPostApi postStubMethod:"  + postStubMethod );
		     httpResponse  = client.execute(postStubMethod);
		     HttpEntity responseEntity = httpResponse.getEntity();
		        
		     errCode = httpResponse.getStatusLine().getStatusCode();
		   
		     logger.debug ( "  6-MyHttpHub.doPostApi 4 errCode:" + errCode  );
	      
	         if (errCode < 200 || errCode >= 300) 
	         { 
	        	 return errCode; 
	         }
	         
	         // Print the response body
		     String responseBody = EntityUtils.toString(responseEntity);
		     logger.debug(" 7-responseBody DA SECONDA client.execute " + responseBody);
	        
		} catch (Exception e  ) 
		{
			// TODO Auto-generated catch block
			errCode = -1;
			e.printStackTrace();
		}
	    
		logger.debug ( " END MyHttpHub.doPostApi  <" + errCode );
		
		return errCode;
	}
	
	
	
	public int doPostApi(GenericResponse<T> ar, Object u, String strBody) 
	{
		errCode = 200;
		logger.debug ( " >------------------------------<:"  );
		logger.debug ( " 1-BEGIN MyHttpHub.doPostApi---<:" + stubsApiBaseUri );
		logger.debug ( " 2-BEGIN MyHttpHub.doPostApi---<:" + u.getClass() );
		logger.debug ( " 3-BEGIN MyHttpHub.doPostApi---<:" + strBody );
		
		
		try 
		{
			logger.debug ( " 4-BEGIN MyHttpHub.doPostApi postStubMethod:"  + this.postStubMethod.getMethod() );
	       
		    // Set the request body
	           
	        StringEntity stringEntity = new StringEntity(strBody);
	        this.postStubMethod.setEntity(stringEntity);
	        this.postStubMethod.setHeader("Content-Type", "application/json");

	
	        logger.debug ( " 5-BEGIN MyHttpHub.doPostApi postStubMethod:"  + postStubMethod );
		     httpResponse  = client.execute(postStubMethod);
		     HttpEntity responseEntity = httpResponse.getEntity();
		        
		     errCode = httpResponse.getStatusLine().getStatusCode();
		   
		     logger.debug ( "  6-MyHttpHub.doPostApi 4 errCode:" + errCode  );
	      
	         if (errCode < 200 || errCode >= 300) 
	         { 
	        	 return errCode; 
	         }
	         
	         // Print the response body
		     String responseBody = EntityUtils.toString(responseEntity);
		     logger.debug(" 7-responseBody DA SECONDA client.execute " + responseBody);
	        
	         errCode = doMapResponseToObj(responseBody, ar) ;
	       
		} catch (Exception e  ) 
		{
			// TODO Auto-generated catch block
			errCode = -1;
			e.printStackTrace();
		}
	    
		logger.debug ( " END MyHttpHub.doPostApi  <" + errCode );
		
		return errCode;
	}
	
	
	public int doGetApi(GenericResponse<T> response, Object u) 
	{
		errCode = 200;
		logger.debug ( " BEGIN MyHttpHub.doGetApi   :" + stubsApiBaseUri );
		//System.out.println ( " BEGIN MyHttpHub.doGetApi.respTObj.getClass   :" + ar.t.getClass() );
		logger.debug ( " BEGIN MyHttpHub.doGetApi():"  + u.getClass() );
		
		try 
		{
			logger.debug ( " BEGIN MyHttpHub.doGetApi getStubMethod:"  + getStubMethod );
			
	        httpResponse  = client.execute(getStubMethod);
	        
	        errCode = httpResponse.getStatusLine().getStatusCode();
	        logger.debug ( "  MyHttpHub.doGetApi 4 errCode:" + errCode  );
	      
	        if (errCode < 200 || errCode >= 300) {
	           // Handle non-2xx status code
	        	 System.out.println(errCode);
	            return errCode;
	        }
	        
	        showHeaders();
	        String responseBody = EntityUtils.toString(httpResponse.getEntity());
	        
	        errCode = doMapResponseToObj(responseBody, response) ;
	        
	       
		} catch (Exception e  ) 
		{
			// TODO Auto-generated catch block
			errCode = -1;
			e.printStackTrace();
		}
	    
		logger.debug ( " END MyHttpHub.doGetApi  <" + errCode );
		
		
		return errCode;
		
	}
	
	private int doMapResponseToObj (String responseBody, GenericResponse<T>  ar) 
	{
		
		Gson gsonL = new Gson();
		logger.debug ( " BEGIN MyHttpHub.doMapResponseToObj              <"  + ar.t.getClass());
		logger.debug ( " BEGIN MyHttpHub.doMapResponseToObj responseBody <"  + responseBody +">");
		logger.debug ( " HTTPMETHOD->>>>>" + this.httpMethod ); 
		if (UserResponse.class == (ar.t.getClass()))
		{
			logger.debug ( " BEGIN MyHttpHub.doMapResponseToObj   dentro UserResponse "   );
			UserResponse    userResponse 		= gsonL.fromJson(responseBody, UserResponse.class);
			T userResponse3 = (T) userResponse;
			T userResponse2 = userResponse3;
			ar.t = userResponse2;
		}
			 
		if (AddressResponse.class == (ar.t.getClass()))
		{
			logger.debug ( " BEGIN MyHttpHub.doMapResponseToObj   dentro AddressResponse "   );
			AddressResponse addressResponse 	= gsonL.fromJson(responseBody, AddressResponse.class);
			T addressResponse2 = (T) addressResponse;
			ar.t = addressResponse2;
		}
		
		if (CertificateResponse.class == (ar.t.getClass()))
		{
			logger.debug ( " BEGIN MyHttpHub.doMapResponseToObj   dentro Certificate "   );
			CertificateResponse certifResponse 	= gsonL.fromJson(responseBody, CertificateResponse.class);
			ar.t = (T) certifResponse;
		}
		 
	     
		return errCode;
	}

	public int doGetApi(List<User> objList, Object u) 
	{
		errCode = 200;
    
		logger.debug ( " BEGIN MyHttpHub.doGetApi   :"  + stubsApiBaseUri );
		logger.debug ( " BEGIN MyHttpHub.c.getClass():"  + u.getClass() );
		 
    
	try 
	{
		logger.debug ( " BEGIN MyHttpHub.doGetApi getStubMethod:"  + getStubMethod );
        httpResponse  = client.execute(getStubMethod);
        logger.debug ( "  MyHttpHub.doGetApi 3 "  );
        errCode = httpResponse.getStatusLine().getStatusCode();
        logger.debug ( "  MyHttpHub.doGetApi 4 errCode:" + errCode  );
      
        if (errCode < 200 || errCode >= 300) {
           // Handle non-2xx status code
        	 System.out.println(errCode);
            return errCode;
        }
        
        showHeaders();
        String responseBody = EntityUtils.toString(httpResponse.getEntity());
        
        //errCode = doMapResponseToUser(responseBody, (List<User>) userList ) ;
        
     // Define the type of the list of User objects
        Type userListType = new TypeToken<List<T>>(){}.getType();
        logger.debug ( " MyHttpHub.doGetApi xx userListType <" + userListType + ">" );
        logger.debug ( " MyHttpHub.doGetApi xx responseBody <" + responseBody + ">" );
        logger.debug ( " MyHttpHub.doGetApi xx responseBody <" + responseBody.getClass() + ">" );
        logger.debug ( " MyHttpHub.doGetApi               u <" + u.getClass() + ">" );
      
    
       // System.out.println ( " MyHttpHub.doCallApi zz responseBody <" + responseBody + ">" ); 
       // System.out.println ( " MyHttpHub.doCallApi 4 responseBody<" + responseBody + ">" );
        logger.debug ( " MyHttpHub.doGetApi 4 errCode<" + errCode + ">" );
        logger.debug (errCode);
        
        
       
	} catch (Exception e  ) 
	{
		// TODO Auto-generated catch block
		errCode = -1;
		e.printStackTrace();
		logger.warn("");
		
	}
    
	logger.debug ( " END MyHttpHub.doGetApi  <" + errCode );
   
 
		
	 return this.errCode;
	}
	
	
	
	public int doDeleteApi(Object deleteObj) 
	{
		return this.errCode;
	}
	
	public int doCallApi() 
	{
		errCode = 200;
		logger.debug ( " BEGIN MyHttpHub.doCallApi  "  + stubsApiBaseUri );
        List<User> objList = new ArrayList<>();
       
		try 
		{
			User u = new User();
			errCode = doGetApi( objList, u) ;
			logger.debug ( " BEGIN MyHttpHub.doCallApi objList.size() "  + objList.size() );
	        
	       
		} catch (Exception e  ) 
		{
			// TODO Auto-generated catch block
			errCode = -1;
			e.printStackTrace();
		}
        
		logger.debug ( " END MyHttpHub.doCallApi  <" + errCode );
       
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
	
	private static String getFormDataAsString(Map<String, String> formData) {
	    StringBuilder formBodyBuilder = new StringBuilder();
	    for (Map.Entry<String, String> singleEntry : formData.entrySet()) {
	        if (formBodyBuilder.length() > 0) {
	            formBodyBuilder.append("&");
	        }
	        formBodyBuilder.append(URLEncoder.encode(singleEntry.getKey(), StandardCharsets.UTF_8));
	        formBodyBuilder.append("=");
	        formBodyBuilder.append(URLEncoder.encode(singleEntry.getValue(), StandardCharsets.UTF_8));
	    }
	    return formBodyBuilder.toString();
	}

	public void setHttpMsg(HttpMethod post) {
		// TODO Auto-generated method stub
		
	}

}

