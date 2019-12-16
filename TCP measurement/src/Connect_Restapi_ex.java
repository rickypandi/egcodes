

import java.io.IOException;
import java.nio.charset.Charset;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Hashtable;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import org.apache.commons.codec.binary.Base64;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;


public class Connect_Restapi_ex extends GenericTest {
	private static int response_code;
	private static double valid =100;
	private static double invalid;
	private static String key="Authorization Status";
	private static String username = "admin";
	private static String password = "eGurkha@2018";
	private static String API; 

	public   Connect_Restapi_ex(String[]args) 
	{
		super(args);
		setMeasureCount(measureCount); 
	}
	
	
public void computeMeasures(Hashtable htable) 
	{
		
		TrustManager[] trustAllCerts = new TrustManager[]{
				new X509TrustManager() {
				    public java.security.cert.X509Certificate[] getAcceptedIssuers() {
				        return null;
				    }
				    public void checkClientTrusted(
				        java.security.cert.X509Certificate[] certs, String authType) {
				    }
				    public void checkServerTrusted(
				        java.security.cert.X509Certificate[] certs, String authType) {
				    }
				}};
		
		API=getValueForParam("API");

				   try {
				    SSLContext sc = SSLContext.getInstance("SSL");
				    sc.init(null, trustAllCerts, new java.security.SecureRandom());
				    HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
				    } catch (Exception e) {
				    }
				  
		HttpGet request = new HttpGet(API);
		String auth = username+ ":" +password;
		byte[] encodedAuth = Base64.encodeBase64(auth.getBytes(Charset.forName("US-ASCII")));
		String authHeader = "Basic " + new String(encodedAuth);
		request.setHeader(HttpHeaders.AUTHORIZATION, authHeader);

		HttpClient client = HttpClientBuilder.create().build();
		
		HttpResponse response=null;
		try {
			response = client.execute(request);
		} catch (IOException e) {
		
			e.printStackTrace();
		}
           
		response_code = response.getStatusLine().getStatusCode();
	           
	
		ArrayList<Double> AL=new ArrayList<Double>();
	  if (response_code==200) 
	  {
		System.out.println("Login authorized");
		AL.add(valid);
		
	 }
	 else 
	 {
	
		System.out.println("Login unauthorized");
		AL.add(invalid);
		
	}
	System.out.println("\n"+AL);
	addNewMeasure(key, AL);
		
	}
	
 public static void main(String[] args) throws KeyManagementException, NoSuchAlgorithmException, IOException 
 {
	 Connect_Restapi_ex connect= new Connect_Restapi_ex(args);
			connect.computeMeasures(new Hashtable<Object, Object>());		 
	 
 }
	}
