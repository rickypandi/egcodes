package com.eg.enterprise;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class cscript {

	public static void main(String[] args) {
		int linecount=4;
		Runtime rt = Runtime.getRuntime();
		HashMap<String,ArrayList<String>> hm=new HashMap<String,ArrayList<String>>();
		
		try {
			String line;
			Process p = rt.exec("cscript C:\\Users\\pandikumar.n\\Desktop\\eg_diskspace.vbs");
			BufferedReader input = new BufferedReader(new InputStreamReader(p.getInputStream()));
		while ((line = input.readLine()) != null)
		  {
			
			if(linecount<=1) 
			{	
				ArrayList<String> al=new ArrayList<String>();
				String key=line.substring(0,line.indexOf(":"));
				String[]  split=line.split(":");
				for(String elements:split) 
				{
					al.add(elements);
			    }
				al.remove(0);
				hm.put(key, al);
				
			}
			
			linecount--;
		    }
		
		System.out.println(hm);
			input.close();
		}

		catch (Exception e) 
		{
			e.printStackTrace();
		}
		ArrayList<String> list = hm.get("E");
		for(String str: list)
		{
			System.out.println(str);
		}
		

	}

}
