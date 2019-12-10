
	import java.io.BufferedReader;
	import java.io.InputStreamReader;
	import java.util.ArrayList;
	import java.util.Arrays;
	import java.util.HashMap;
	import java.util.Hashtable;
	import java.util.Scanner;
	import java.util.StringTokenizer;

	public class TCP_ex extends GenericTest{
		private static String line;
		private static String key;
		private static int array_index=0;
		public static Scanner sc;
		Runtime rt = Runtime.getRuntime();
		HashMap<String,ArrayList<Double>> table=new HashMap<String,ArrayList<Double>>();
		public  TCP_ex(String[]args) 
		{
			super(args);
			setMeasureCount(measureCount); 
		}
		
		public void computeMeasures(Hashtable htable) {
		
			
			
			try {
				String path=getValueForParam("Path");
				Process p = rt.exec(path);
				BufferedReader input = new BufferedReader(new InputStreamReader(p.getInputStream()));
				
			while ((line = input.readLine()) != null)
			  {	
					
					ArrayList<Double> List=new ArrayList<Double>();
					key=line.substring(0,line.indexOf("="));

					line=line.substring(6);
					
					StringTokenizer st = new StringTokenizer(line, "#"); 
					
					 String[] array=new String [st.countTokens()];
					 while(st.hasMoreElements()) 
					 {
					array[array_index]=st.nextElement().toString();
					array_index++;
					 }
				   double[] doubleArray = Arrays.stream(array).mapToDouble(Double::parseDouble).toArray();
					for(Double elements:doubleArray) 
					{
						List.add(elements);
				
				    }
			
				addNewMeasure(key, List);
				array_index=0;
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
			 TCP_ex ref=new  TCP_ex(args);
		ref.computeMeasures(new Hashtable());
		
		}

	}

		

