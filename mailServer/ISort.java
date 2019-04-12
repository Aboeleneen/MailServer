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
import eg.edu.alexu.csd.datastructure.stack.cs32.Mystack1;

public class ISort {

	@SuppressWarnings("rawtypes")
	public void priority (IFolder folder, Single emails) throws
	IOException, ParseException {
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
		Pnode[] pc = new Pnode[d.length];
		for (int i = 0; i < d.length; i++) {
			in = d[i].toString();
			a = (JSONArray) j.get(in);
			aa = (Object[]) a.toArray();
			int temp = Integer.parseInt(aa[3].toString());
			pc[i] = new Pnode(d[i].toString(), temp);
		}
		Mystack1 s = new Mystack1();
		Mystack1 h = new Mystack1();
		for (int i = 0; i < pc.length; i++) {
			s.push(pc[i]);
		}
		Pnode current = new Pnode(null, 0);
		Pnode temp = new Pnode(null, 0);
		while (!(s.isEmpty())) {
			current = (Pnode) s.pop();
		if (h.isEmpty()) {
				h.push(current); }

			else {
			temp = (Pnode) h.peek();
		if (temp.p > current.p) {
				while (temp.p > current.p) {
						s.push(h.pop());
				if (h.isEmpty()) {
				break; }
					temp = (Pnode) h.peek();
					}
					h.push(current);}
				else {
					h.push(current);
				}
			}
		}
		while (!(h.isEmpty())) {
			s.push(h.pop());
		}
		while (!(s.isEmpty())) {
			temp = (Pnode) s.pop();
			emails.add(temp.name); 
		}
		
		
		
}
	public void date(IFolder folder, Single emails) throws FileNotFoundException, IOException, ParseException {
		File index = new File(folder.Path + "\\indexfile.txt");
		JSONParser parserInbox = new JSONParser();
		Object obj1 = parserInbox.parse(new FileReader(index));
		JSONObject j = (JSONObject) obj1;
		JSONArray a = new JSONArray();
		//get keys
		Set keys = j.keySet();
		Object[] d = keys.toArray();
		
		//transform to array
		//first key
		String in = d[0].toString();
		a = (JSONArray) j.get(in);
		//array of email components
		Object[] aa = (Object[]) a.toArray();
		Dnode[] dc = new Dnode[d.length];
		
		for (int i = 0; i < d.length; i++) {
			in = d[i].toString();
			a = (JSONArray) j.get(in);
			aa = (Object[]) a.toArray();
			String temp = (String) aa[2];
			dc[i] = new Dnode(d[i].toString(), temp.toString());
		}
		for(int y = 0; y < dc.length; y++) {
			for(int i = 3; i < dc[y].date.length(); i++) {
				
				if(('0' <= dc[y].date.charAt(i)) && ('9' >= dc[y].date.charAt(i))) {
					dc[y].myDate += dc[y].date.charAt(i);
				}
				
			
			}
			dc[y].trick = Long.parseLong(dc[y].myDate);
		}
		
		
		Mystack1 s = new Mystack1();
		Mystack1 h = new Mystack1();
		for (int i = 0; i < dc.length; i++) {
			s.push(dc[i]);
		}
		Dnode current = new Dnode(null, null);
		Dnode temp = new Dnode(null, null);
		while (!(s.isEmpty())) {
			current = (Dnode) s.pop();
		if (h.isEmpty()) {
				h.push(current); }

			else {
			temp = (Dnode) h.peek();
		if (temp.trick > current.trick) {
				while (temp.trick > current.trick) {
						s.push(h.pop());
				if (h.isEmpty()) {
				break; }
					temp = (Dnode) h.peek();
					}
					h.push(current);}
				else {
					h.push(current);
				}
			}
		}
		while (!(h.isEmpty())) {
			s.push(h.pop());
		}
		while (!(s.isEmpty())) {
			temp = (Dnode) s.pop();
			emails.add(temp.name); 
		}
		
		
		
		
		
		
		
		
		
	}
	
	
	
	
	
	
	
	
	
	
	

}
