package eg.edu.alexu.csd.datastructure.mailServer;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Set;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import eg.edu.alexu.csd.datastructure.linkedList.cs32_cs02.Single;
import eg.edu.alexu.csd.datastructure.mailServer.cs02_cs32_cs48.MailServer;

public class IFilter {
	/**
	* Filter By Subject.
	*/
	public Single subject(IFolder folder, String sub) throws FileNotFoundException, IOException, ParseException {
		Single emails = new Single();
		File index = new File(folder.Path + "\\indexfile.txt");
		JSONParser parserInbox = new JSONParser();
		Object obj1 = parserInbox.parse(new FileReader(index));
		JSONObject j = (JSONObject) obj1;
		JSONArray a = new JSONArray();
		//get keys
		Set keys = j.keySet();
		Object[] d = keys.toArray();
		System.out.println(Arrays.toString(d));
		//transform to array
		//first key
		String in = d[0].toString();
		a = (JSONArray) j.get(in);
		//array of email components
		Object[] aa = (Object[]) a.toArray();
		Ssubject[] ss = new Ssubject[d.length];
		
		for (int i = 0; i < d.length; i++) {
			in = d[i].toString();
			a = (JSONArray) j.get(in);
			aa = (Object[]) a.toArray();
			String temp = (String) aa[1];
			ss[i] = new Ssubject(d[i].toString(), temp);
		}
		for(int i = 0;i < ss.length; i++) {
			String temp = (String) ss[i].subject;
			if(temp == null) {
				continue;
			}
			if(temp.equals(sub)) {
				emails.add(ss[i].name);
			}
		}
		
		return emails;
		
	}
	/**
	* Filter By Sender.
	*/
	public Single receivers(IFolder folder, String re) throws FileNotFoundException, IOException, ParseException {
		Single emails = new Single();
		File index = new File(folder.Path + "\\indexfile.txt");
		JSONParser parserInbox = new JSONParser();
		Object obj1 = parserInbox.parse(new FileReader(index));
		JSONObject j = (JSONObject) obj1;
		JSONArray a = new JSONArray();
		//get keys
		Set keys = j.keySet();
		Object[] d = keys.toArray();
		System.out.println(Arrays.toString(d));
		//transform to array
		//first key
		String in = d[0].toString();
		a = (JSONArray) j.get(in);
		//array of email components
		Object[] aa = (Object[]) a.toArray();
		Sreceiver[] ss = new Sreceiver[d.length];
		MailServer m = new MailServer();
		
		for (int i = 0; i < d.length; i++) {
			in = d[i].toString();
			a = (JSONArray) j.get(in);
			aa = (Object[]) a.toArray();
			
			ss[i] = new Sreceiver(d[i].toString(), null);
		}
		
		for(int i = 0; i < d.length; i++) {
			String sender = "";
			for(int t = 0; t < d[i].toString().length(); t++) {
				if(d[i].toString().charAt(t) == '_') {
					t++;
					while(d[i].toString().charAt(t) != '_') {
					sender += d[i].toString().charAt(t);
					t++;
				}
				}
				ss[i].receiver = sender;
			}
			
			
			
			
			
		}
		
		
		
		
		for(int i = 0;i < ss.length; i++) {
			String temp = (String) ss[i].receiver;
			if(temp.equals(re)) {
				emails.add(ss[i].name);
			}
		}
		
		
		
		return emails;
		
		
		
		
		
		
		
		
		
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	

}