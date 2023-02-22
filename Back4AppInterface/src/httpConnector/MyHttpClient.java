package httpConnector;

import java.io.*;
import java.net.*;
import javax.xml.xpath.*;
import org.apache.http.*;
import org.apache.http.client.*;
import org.apache.http.client.methods.*;
import org.apache.http.client.utils.*;
import org.apache.http.entity.*;
import org.apache.http.impl.client.*;
import org.apache.http.util.*;
import org.xml.sax.*;


import model.User;
import model.UserInfo;

public class MyHttpClient {
	
	String stubsApiBaseUri = "";
	
	public int doCallApiPOST()
	{
		int errCode = 200;
		
		return errCode;
		
	}
	
	public int doCallApi() 
	{
		int errCode = 200;
	    
        String domain = "MioDominio";
        String environment = "MioEnvironment";
        String stubName = "mioStubName";
        
        System.out.println ( " BEGIN MyHttpClient.doCallApi  "  + stubsApiBaseUri );
        
        HttpClient client = HttpClients.createDefault();

		try 
		{
			UserInfo userInfo = new UserInfo();	 
			URIBuilder builder = new URIBuilder(stubsApiBaseUri);
			builder.addParameter("domain", domain);
	        builder.addParameter("env", environment);
	        builder.addParameter("stub", stubName);
	        builder.addParameter("user", userInfo.getUser().getName());
	        builder.addParameter("userId", userInfo.getUser().getLastName());
	        builder.addParameter("machine", userInfo.getMachine().getMachineName());
	        builder.addParameter("TagFunct", "CHECKCONNECTION");
	        builder.addParameter("MachineName", userInfo.getMachine().getMachineName());
	        
	        String listStubsUri = builder.build().toString();
	        
	        System.out.println ( "  MyHttpClient.doCallApi 1 "  );
	        
	        HttpGet getStubMethod = new HttpGet(listStubsUri); 
	        getStubMethod.setHeader("Content-Type", "application/json");
	        getStubMethod.setHeader("user", userInfo.getUser().getName());
	        getStubMethod.setHeader("userId", userInfo.getUser().getPhone());
	        getStubMethod.setHeader("machine", userInfo.getMachine().getMachineName());
	        getStubMethod.setHeader("X-Parse-Application-Id","PyIyBIcTEKZbn9aAsE5TbpdbHGIpwMBE379u1THy");
	        getStubMethod.setHeader("X-Parse-REST-API-Key","QrNbRBlaPgpRTrdS6C4nGz4Sb6VuFHvdqaz1RmJq");
	        getStubMethod.setHeader("X-UserToken","b7017c0b87a4a1187b8b54a83cbb35efa3176bc285088e1079a435c2d17190288e8bf275");
	        
	        System.out.println ( "  MyHttpClient.doCallApi 2 "  );
	        
	        
	        HttpResponse getStubResponse = client.execute(getStubMethod);
	        System.out.println ( "  MyHttpClient.doCallApi 3 "  );
	        errCode = getStubResponse.getStatusLine().getStatusCode();
	        System.out.println ( "  MyHttpClient.doCallApi 4 errCode:" + errCode  );
	        
	        // Headers
	        Header[] headers = getStubResponse.getAllHeaders();
	        for (int i = 0; i < headers.length; i++) 
	        {
	            System.out.println(i+")Header:" + headers[i]);
	        }
	        
	        
	        if (errCode < 200 || errCode >= 300) {
	           // Handle non-2xx status code
	           return errCode;
	        }
	        
	        
	        String responseBody = EntityUtils.toString(getStubResponse.getEntity());
	        
	        System.out.println ( "  MyHttpClient.doCallApi 4 responseBody<" + responseBody + ">" );
	        
	        System.out.println ( "  MyHttpClient.doCallApi 4 errCode<" + errCode + ">" );
	        System.out.println(errCode);
	       
		} catch (Exception e  ) {
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
		this.stubsApiBaseUri = stubsApiBaseUri;
	}
	

}
