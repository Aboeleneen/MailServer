package eg.edu.alexu.csd.datastructure.mailServer;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Set;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import eg.edu.alexu.csd.datastructure.linkedList.cs32_cs02.Single;
import eg.edu.alexu.csd.datastructure.stack.cs32.Mystack1;

public class ISort1 {

	
	@SuppressWarnings("rawtypes")
	public void priority (IFolder folder, Single emails) throws IOException, ParseException {
		
		
		
		File index = new File(folder.Path +"\\indexfile.txt");
		
		JSONParser parser_inbox = new JSONParser();
		Object obj1 = parser_inbox.parse(new FileReader(index));
		JSONObject j = (JSONObject) obj1;
		JSONArray a = new JSONArray();
		
		//get keys
		Set keys = j.keySet();
		Object [] d = keys.toArray();
		System.out.println(Arrays.toString(d));
		
		
		
		//transform to array
		
		
		String in = d[0].toString();//first key
		a = (JSONArray) j.get(in);
		Object[] aa = (Object[]) a.toArray();  //array of email components
		
		
		Pnode[] pc = new Pnode[d.length];
		
		
		for(int i = 0; i < d.length; i++) {
			in = d[i].toString();
			a = (JSONArray) j.get(in);
			aa = (Object[]) a.toArray();
			int temp = Integer.parseInt(aa[2].toString());
			pc[i] = new Pnode(d[i].toString(), temp);
		}
		
		
		
		Mystack1 s = new Mystack1();
		Mystack1 h = new Mystack1();
		
		for(int i = 0; i < pc.length; i++) {
			s.push(pc[i]);
		}
		
		
		Pnode current = new Pnode(null, 0);
		Pnode temp = new Pnode(null, 0);
		while(!(s.isEmpty())) {
			current = (Pnode) s.pop();
			
			if(h.isEmpty()) {
				h.push(current);
			}
			
			else {
				temp = (Pnode) h.peek();
				if(temp.p > current.p) {
					while(temp.p > current.p) {
						s.push(h.pop());
						if(h.isEmpty()) {
							break;
						}
						temp = (Pnode) h.peek();
						
					}
					h.push(current);
				}
				else {
					h.push(current);
				}
				
			}
		
		}
		while(!(h.isEmpty())) {
			s.push(h.pop());
		}
		
		while(!(s.isEmpty())) {
			temp = (Pnode) s.pop();
			emails.add(temp.name); 
		}
		
		
		
		
		
		
		
		
		
		
		
		
		
		
			
			
			
			
			
			
		
			
			
}

	
	
	
	
	
	
	
	
}
