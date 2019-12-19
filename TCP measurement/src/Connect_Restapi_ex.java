

import java.io.IOException;
import java.nio.charset.Charset;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Hashtable;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;

import org.apache.commons.codec.binary.Base64;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContextBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;


public class Connect_Restapi_ex extends GenericTest 
{
	private static int response_code;
	private static String key="Authorization Status";
	private static String username = "admin";
	private static String password = "eGurkha@2018";
	private static String API; 

	public Connect_Restapi_ex(String[] args)
	{
		super(args);
		setMeasureCount(measureCount); 
	}

	public void computeMeasures(Hashtable htable) 
	
	{

	    TrustManager[] trustAllCerts = new TrustManager[] 
	    		  {               new TrustManager() 
	    		      {
	    					public java.security.cert.X509Certificate[] getAcceptedIssuers() 
	    			       {
	    					return null;
	    			        }

	    					public void checkClientTrusted(java.security.cert.X509Certificate[] certs, String authType) 
	    					{
	    					}

	    					public void checkServerTrusted(java.security.cert.X509Certificate[] certs, String authType) 
	    					{
	    					}
	    				} 
	    		  };
		
	//API=getValueForParam("API");
		HttpGet request = new HttpGet("https://192.168.10.144:9440/PrismGateway/services/rest/v1/hosts");
		
		
try {

		System.out.println(entity +"Test in");
		SSLContext sslContext=null;
		
		try 
		{
			
			sslContext = new SSLContextBuilder().loadTrustMaterial(null, (x509CertChain, authType) -> true).build();
		} 
		catch (KeyManagementException | NoSuchAlgorithmException | KeyStoreException e1)
		{
			e1.printStackTrace();
		}

		CloseableHttpClient httpClient  = HttpClientBuilder.create().setSslcontext(sslContext)
		.setConnectionManager(new PoolingHttpClientConnectionManager(RegistryBuilder
		.<ConnectionSocketFactory>create()
		.register("http", PlainConnectionSocketFactory.INSTANCE)
		.register("https",
		new SSLConnectionSocketFactory(sslContext))
		.build()))
		.build();               

		
		
		String auth = username+ ":" +password;
		byte[] encodedAuth = Base64.encodeBase64(auth.getBytes(Charset.forName("US-ASCII")));
		String authHeader = "Basic " + new String(encodedAuth);
		request.setHeader(HttpHeaders.AUTHORIZATION, authHeader);

		httpClient = HttpClientBuilder.create().build();

		HttpResponse response=null;
		try 
		{
			response = httpClient.execute(request);
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}

		response_code = response.getStatusLine().getStatusCode();
		System.out.println(entity +"response_code :"+response_code);
		ArrayList aList=new ArrayList();
		System.out.println(entity +"before aList :"+aList);
		if (response_code==200) 
			aList.add(100);
		else 
			aList.add(0);
			
		System.out.println("Connect_Restapi_ex key :"+key+", Al :"+aList);
		addNewMeasure(key, aList);
}
	catch(Exception ex) {
		System.out.println(ex);
	}
}
	public static void main(String[] args) 
	{
		try 
		{
			Connect_Restapi_ex connect= new Connect_Restapi_ex(args);
			connect.computeMeasures(new Hashtable<Object, Object>());	
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
	}
}



