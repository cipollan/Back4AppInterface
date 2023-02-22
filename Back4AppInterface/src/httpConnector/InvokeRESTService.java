package httpConnector;

 
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.cert.X509Certificate;
import java.util.List;
import java.util.Properties;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import javax.xml.bind.DatatypeConverter;

public class InvokeRESTService {
	Properties property;

	public static String getToken(String url, Properties property,
			String user, 
			String password) throws Exception {
		
		System.out.println("InvokeRESTService.getToken (BEGIN)"  );

		String response = "";
		
		try {
			
			System.out.println("BEGIN getToken urlHttps=" + url);
			java.net.HttpURLConnection conn = getConnectionHttps(url);
			conn.setRequestMethod("GET");
			String credentials = user + ":" + password;
			byte[] message = "hello world".getBytes("UTF-8");
			String encoded = DatatypeConverter.printBase64Binary(message);
			System.out.println("getToken encoded=" + encoded);
			
			String encoding3 = DatatypeConverter.printBase64Binary(credentials.getBytes());
			encoding3 = encoding3.replaceAll("\n", "");

			System.out.println("getToken encoding3=" + encoding3);
			
			conn.setDoOutput(true);
			
			System.out.println("BEGIN getToken urlHttps=" + url);
			System.out.println("getToken encoding3=" + encoding3);
			
			conn.setRequestProperty("Content-Type", "application/json");
			conn.setRequestProperty("Authorization", "Basic " + encoding3);
			conn.setRequestProperty(property.getProperty("app.appid.tag"),property.getProperty("app.appid"));
			conn.setRequestProperty(property.getProperty("app.restapi.tag"),property.getProperty("app.restapi.key"));
			 
			
			System.out.println("WRITE OUTPUT STREAM AX-getToken  ");
			java.io.OutputStream out = conn.getOutputStream();
			java.io.Writer wr = new java.io.OutputStreamWriter(out);
			wr.close();
			
			java.io.InputStream  in = conn.getInputStream();
			System.out.println("X1-getToken  ");
			java.io.Reader       reader = new java.io.InputStreamReader(in);
			System.out.println("X2-getToken  ");
			java.io.StringWriter sw = new java.io.StringWriter();
			System.out.println("WRITE OUTPUT STREAM X3-getToken  ");
			char[] buf = new char[5000];
			int bread = 0;

			while ((bread = reader.read(buf)) != -1) 
			{
				System.out.println("RESPONSE XX-getToken  bread:" + bread);
				sw.write(buf, 0, bread);
				response = response + new String(buf);
				System.out.println("RESPONSE XX-getToken  response:" + response);
			}

			reader.close();

			int inx = response.indexOf("201");
			
			System.out.println("RESPONSE inx:" + inx);
			
			if (response.indexOf("201") > 0) {
			} else {
			}

		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println("Exception ex=" + ex);
			ex.printStackTrace(System.out);
		}
		System.out.println("EnD getToken response=" + response);
		System.out.println("InvokeRESTService.getToken (END)"  );
		System.out.println("                                 "  );
		return response;
	}

	
	
	public static String invokeRestApi(String method,String url, Properties property,
			String user, 
			String password) throws Exception {
		
		System.out.println("InvokeRESTService.invokeRestApi (BEGIN)"  );

		String response = "";
		
		try {
			
			System.out.println("BEGIN getToken urlHttps=" + url);
			java.net.HttpURLConnection conn = getConnectionHttps(url);
			conn.setRequestMethod(method);
			String credentials = user + ":" + password;
			byte[] message = "hello world".getBytes("UTF-8");
			String encoded = DatatypeConverter.printBase64Binary(message);
			System.out.println("getToken encoded=" + encoded);
			
			String encoding3 = DatatypeConverter.printBase64Binary(credentials.getBytes());
			encoding3 = encoding3.replaceAll("\n", "");

			System.out.println("getToken encoding3=" + encoding3);
			
			conn.setDoOutput(true);
			
			System.out.println("BEGIN getToken urlHttps=" + url);
			System.out.println("getToken encoding3=" + encoding3);
			
			conn.setRequestProperty("Content-Type", "application/json");
			conn.setRequestProperty("Authorization", "Basic " + encoding3);
			conn.setRequestProperty(property.getProperty("app.appid.tag"),property.getProperty("app.appid"));
			conn.setRequestProperty(property.getProperty("app.restapi.tag"),property.getProperty("app.restapi.key"));
			 
			
			System.out.println("WRITE OUTPUT STREAM AX-getToken  ");
			java.io.OutputStream out = conn.getOutputStream();
			System.out.println("BX-getToken  ");
			java.io.Writer wr = new java.io.OutputStreamWriter(out);
			 
			wr.close();
			System.out.println("WRITE OUTPUT STREAM EX-getToken  ");
			
			
			
			System.out.println("READ OUTPUT STREAM X-getToken  ");
			java.io.InputStream  in = conn.getInputStream();
			System.out.println("X1-getToken  ");
			java.io.Reader       reader = new java.io.InputStreamReader(in);
			System.out.println("X2-getToken  ");
			java.io.StringWriter sw = new java.io.StringWriter();
			System.out.println("WRITE OUTPUT STREAM X3-getToken  ");
			char[] buf = new char[5000];
			int bread = 0;

			while ((bread = reader.read(buf)) != -1) 
			{
				System.out.println("RESPONSE XX-getToken  bread:" + bread);
				sw.write(buf, 0, bread);
				response = response + new String(buf);
				System.out.println("RESPONSE XX-getToken  response:" + response);
			}

			reader.close();

			int inx = response.indexOf("201");
			
			System.out.println("RESPONSE inx:" + inx);
			
			if (response.indexOf("201") > 0) {
			} else {
			}

		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println("Exception ex=" + ex);
			ex.printStackTrace(System.out);
		}
		System.out.println("EnD getToken response=" + response);
		System.out.println("InvokeRESTService.invokeRestApi (END)"  );
		System.out.println("                                 "  );
		return response;
	}
	
	public static String invokeRestApi2(	String method, 
										String url, 
										Properties property,
										String token, 
										String jsonInput) throws Exception 
	{
		String response = "";

		System.out.println("\nInvokeRESTService.invokeRestApi (BEGIN) " + method );
		
		try {
			
			System.out.println("invokeRestApi url      :" + url );
			System.out.println("invokeRestApi token    :" + token );
			System.out.println("invokeRestApi jsonInput:" + jsonInput );
			System.out.println("invokeRestApi method   :" + method );
			
			
			byte[] message = "hello world".getBytes("UTF-8");
			String encoded = DatatypeConverter.printBase64Binary(message);
			System.out.println("getToken encoded=" + encoded);
			String credentials =  "aaaaaaaaaa";
			String encoding3 = DatatypeConverter.printBase64Binary(credentials.getBytes());
			encoding3 = encoding3.replaceAll("\n", "");
			
			java.net.HttpURLConnection conn = getConnectionHttps(url);
			 
			conn.setRequestMethod(method);
			conn.setRequestProperty("Content-Type", "application/json");
			conn.setRequestProperty("Authorization", "Basic " + encoding3);
			conn.setRequestProperty(property.getProperty("app.appid.tag"),property.getProperty("app.appid"));
			conn.setRequestProperty(property.getProperty("app.restapi.tag"),property.getProperty("app.restapi.key"));
			
			 
			 
			//conn.setRequestProperty("Authorization", "Bearer " + token);
			
			
			conn.setDoOutput(true);
			java.io.OutputStream out = conn.getOutputStream();
			java.io.Writer wr = new java.io.OutputStreamWriter(out);
			
			if (jsonInput != null) 
			{
				wr.write(jsonInput);
			}

			wr.close();
			//--------------------------------------------------------------
			//--------------------------------------------------------------
			
			System.out.println("invokeRestApi leggo response     :"   );
			java.io.InputStream in 		= conn.getInputStream();
			java.io.Reader reader 		= new java.io.InputStreamReader(in);
			java.io.StringWriter sw 	= new java.io.StringWriter();
			 
			char[] buf = new char[5000];
			int bread = 0;
			 
			while ((bread = reader.read(buf)) != -1) 
			{
				sw.write(buf, 0, bread);
				System.out.println("5->Read:" + buf);
				response = response + new String(buf);
			}
			
			System.out.println("----before closing reader--");
			reader.close();
			System.out.println("RESp**:=" + response);
			
		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println("ex=" + ex);
			ex.printStackTrace(System.out);
		}
		return response;
	}

	public static String invokeRESTApiByGet(String url,Properties property, String token, String jsonInput1) throws Exception {

		String response = "";
		url = url + jsonInput1;
		try {
			System.out.println("urlHttps=" + url);
			java.net.HttpURLConnection conn = getConnectionHttps(url);
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Content-Type", "application/json");
			conn.setRequestProperty(property.getProperty("app.appid.tag"),property.getProperty("app.appid.tag"));
			conn.setRequestProperty(property.getProperty("app.appid"),property.getProperty("app.appid"));
			conn.setRequestProperty(property.getProperty("app.restapi.tag"),property.getProperty("app.restapi.tag"));
			conn.setRequestProperty(property.getProperty("app.restapi.key"),property.getProperty("app.restapi.key"));
			

			conn.setDoOutput(true);
			int code = conn.getResponseCode();
			System.out.println("code=" + code);
			java.io.InputStream in = conn.getInputStream();
			java.io.Reader reader = new java.io.InputStreamReader(in);
			java.io.StringWriter sw = new java.io.StringWriter();
			int bread = 0;
			while ((bread = reader.read()) != -1) {
				char ch = (char) bread;
				response = response + ch;

			}

			reader.close();
			System.out.println("RESp**:=" + response);
			int inx = response.indexOf("201");

		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println("ex=" + ex);
			ex.printStackTrace(System.out);
		}
		return response;
	}

	public static String invokeRestApiByPost(String url, Properties property,String token, String jsonInput, List storeResponse)
			throws Exception {

		String response = "";

		try {
			System.out.println("urlHttps=" + url);

			java.net.HttpURLConnection conn = getConnectionHttps(url);
			conn.setRequestMethod("POST");

			String credentials = token;

			conn.setRequestProperty("Content-Type", "application/json");
			//conn.setRequestProperty("Authorization", "Bearer " + token);
			conn.setRequestProperty(property.getProperty("app.appid.tag"),property.getProperty("app.appid.tag"));
			conn.setRequestProperty(property.getProperty("app.appid"),property.getProperty("app.appid"));
			conn.setRequestProperty(property.getProperty("app.restapi.tag"),property.getProperty("app.restapi.tag"));
			conn.setRequestProperty(property.getProperty("app.restapi.key"),property.getProperty("app.restapi.key"));
			

			conn.setDoOutput(true);
			java.io.OutputStream out = conn.getOutputStream();
			java.io.Writer wr = new java.io.OutputStreamWriter(out);

			if (jsonInput != null) {
				wr.write(jsonInput);
			}

			wr.close();
			int code = conn.getResponseCode();

			java.io.InputStream in = conn.getInputStream();
			java.io.Reader reader = new java.io.InputStreamReader(in);
			int bread = 0;
			while ((bread = reader.read()) != -1) {
				char ch = (char) bread;
				response = response + ch;
			}

			reader.close();

			System.out.println("RESpcode**:=" + code);
			storeResponse.add(response);
			response = " {\"ResponseCode\": " + code + "}";

		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println("ex=" + ex);
			ex.printStackTrace(System.out);
			storeResponse.add("" + ex);
		}
		System.out.println("response:" + response);
		return response;
	}

	public static String invokeRestApiByPUT(String url, String token, String jsonInput) throws Exception {
		String response = "";
		try {
			System.out.println("urlHttps=" + url);
			java.net.HttpURLConnection conn = getConnectionHttps(url);
			conn.setRequestMethod("PUT");

			conn.setRequestProperty("Content-Type", "application/json");
			conn.setRequestProperty("Authorization", "Bearer " + token);
			conn.setDoOutput(true);
			java.io.OutputStream out = conn.getOutputStream();
			java.io.Writer wr = new java.io.OutputStreamWriter(out);

			if (jsonInput != null) {
				wr.write(jsonInput);
			}

			wr.close();
			int code = conn.getResponseCode();
			java.io.InputStream in = conn.getInputStream();
			java.io.Reader reader = new java.io.InputStreamReader(in);
			java.io.StringWriter sw = new java.io.StringWriter();
			char[] buf = new char[500];
			int bread = 0;

			while ((bread = reader.read(buf)) != -1) {
				sw.write(buf, 0, bread);
				response = response + new String(buf);
			}

			reader.close();
			int inx = response.indexOf("201");
			System.out.println("inx=" + inx + response);
			if (code == 202) {
				response = " {\"ResponseCode\": 202}";
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			ex.printStackTrace(System.out);
		}
		return response;
	}

	public static HttpURLConnection getConnectionHttps(String urlHttps) throws Exception {
		System.out.println("urlHttps=" + urlHttps);
		TrustManager[] trustCerts = new TrustManager[] { new X509TrustManager() {
			public java.security.cert.X509Certificate[] getAcceptedIssuers() {
				return null;
			}

			public void checkClientTrusted(X509Certificate[] certs, String authType) {
			}

			public void checkServerTrusted(X509Certificate[] certs, String authType) {
			}
		} };

		SSLContext sc = SSLContext.getInstance("SSL");
		sc.init(null, trustCerts, new java.security.SecureRandom());
		HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());

		HostnameVerifier allHostsValid = new HostnameVerifier() {
			public boolean verify(String hostname, SSLSession session) {
				return true;
			}
		};
		HttpsURLConnection.setDefaultHostnameVerifier(allHostsValid);
		URL url = new URL(urlHttps);
		HttpURLConnection con = (java.net.HttpURLConnection) url.openConnection();
		return con;
	}
}
