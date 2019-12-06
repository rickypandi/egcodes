
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Hashtable;


public class Script_ex extends GenericTest{
	private static int linecount=4;
	private static String line;
	private static String key;
	Runtime rt = Runtime.getRuntime();
	HashMap<String,ArrayList<Double>> table=new HashMap<String,ArrayList<Double>>();
	
	public  Script_ex(String[]args) 
	{
		super(args);
		setMeasureCount(measureCount); 
	}
	
	public void computeMeasures(Hashtable hTable) {
	
		
		
		try {
			
			Process p = rt.exec("cscript C:\\Users\\pandikumar.n\\Desktop\\eg_diskspace.vbs");
			BufferedReader input = new BufferedReader(new InputStreamReader(p.getInputStream()));
			
		while ((line = input.readLine()) != null)
		  {
			
			if(linecount<=1) 
			{	
				
				ArrayList<Double> List=new ArrayList<Double>();
				key=line.substring(0,line.indexOf(":"));
				System.out.println("\n"+line);
			   String[] split=line.substring(2).split(":");
			   
			   double[] doubleArray = Arrays.stream(split).mapToDouble(Double::parseDouble).toArray();
				for(Double elements:doubleArray) 
				{
					List.add(elements);
			
			    }
				
				System.out.println("key is " +key);
				System.out.println(List);
				addNewMeasure(key, List);
			
			}
			
			linecount--;
		    }
		
			input.close();
		}

		catch (Exception e) 
		{
			e.printStackTrace();
		}
		
	}
	public static void main(String[]args) 
	{
	Script_ex ref=new Script_ex(args);
	ref.computeMeasures(new Hashtable());
	
	}

}
