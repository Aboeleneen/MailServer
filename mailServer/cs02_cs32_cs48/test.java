package eg.edu.alexu.csd.datastructure.mailServer.cs02_cs32_cs48;

import java.awt.event.ItemEvent;
import java.io.IOException;
import org.json.simple.parser.ParseException;
import eg.edu.alexu.csd.datastructure.linkedList.cs32_cs02.Single;
import eg.edu.alexu.csd.datastructure.mailServer.IContact;
import eg.edu.alexu.csd.datastructure.mailServer.IFilter;
import eg.edu.alexu.csd.datastructure.mailServer.IFolder;
import eg.edu.alexu.csd.datastructure.mailServer.IMail;
import eg.edu.alexu.csd.datastructure.mailServer.ISort;



public class test {

	public static void main(String[] args) throws IOException, ParseException {
		// TODO Auto-generated method stub

		MailServer m = new MailServer();
		
		IFolder f = new IFolder();
		f.Path = "C:\\server\\users\\karim\\inbox";
		ISort s = new ISort();
		
		m.isPriority = true;
		m.setViewingOptions(f, null, s);
		
		String[] names = new String[m.emails.size()];
		
		for(int i = 0; i < names.length; i++) {
			names[i] = (String) m.emails.get(i);
			
		}
		
		return;
		
	}

}
