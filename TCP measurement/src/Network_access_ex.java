import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Scanner;

public class Network_access_ex extends GenericTest {
	private static double response_code;
	private static double valid =100;
	private static double invalid;
	private static String url;
	private static String key="Homepage";
		
	public  Network_access_ex(String[]args) 
	{
		super(args);
		setMeasureCount(measureCount); 
	}
	
	public static double isUrlValid(String url) throws IOException
	{
		
	    URL u = new URL(url); 
	    HttpURLConnection con =  (HttpURLConnection)  u.openConnection(); 
	    con.setRequestMethod("GET"); 
	    try 
	    {
			con.connect();
		} 
	    catch (IOException e) 
	    {
			return 0;
		} 
	    return con.getResponseCode();
	}

	
	public void computeMeasures(Hashtable htable) 
	{
		
		      url=getValueForParam("URL");
	    try 
	    {
		      response_code=isUrlValid(url);
	      } 
	    catch (IOException e) 
	   {
		     e.printStackTrace();
	   }
	   
	    ArrayList<Double> List=new ArrayList<Double>();
	    if (response_code==HttpURLConnection.HTTP_OK) 
	     {
	    	 List.add(valid);
	    	 System.out.println(valid);
	     }
	     else 
	     {
	    	 List.add(invalid);
	    	 System.out.println(invalid);
	     }
	     System.out.println(List);
	     addNewMeasure(key, List);
	}
	

	
   public static void main(String[]args) throws IOException, InterruptedException 
	 {
	    Network_access_ex ref=new  Network_access_ex(args);
		ref.computeMeasures(new Hashtable());
     
	 }
    
}
