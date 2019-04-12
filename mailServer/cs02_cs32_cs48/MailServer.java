package eg.edu.alexu.csd.datastructure.mailServer.cs02_cs32_cs48;

import java.awt.image.BufferedImageFilter;
import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.Calendar;
import java.util.GregorianCalendar;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import eg.edu.alexu.csd.datastructure.linkedList.ILinkedList;
import eg.edu.alexu.csd.datastructure.linkedList.cs32_cs02.Single;
import eg.edu.alexu.csd.datastructure.linkedList.cs32_cs02.SingleNode;
import eg.edu.alexu.csd.datastructure.mailServer.IApp;
import eg.edu.alexu.csd.datastructure.mailServer.IContact;
import eg.edu.alexu.csd.datastructure.mailServer.IFilter;
import eg.edu.alexu.csd.datastructure.mailServer.IFolder;
import eg.edu.alexu.csd.datastructure.mailServer.IMail;
import eg.edu.alexu.csd.datastructure.mailServer.ISort;
import java.nio.file.StandardCopyOption;

public class MailServer implements IApp {
	
	//set mails.
	public Single emails = new Single();
	public boolean isPriority;
	
	
	
	
	public void folders() throws IOException {
		File server = new File("C:\\server");
		if (!server.exists()) {
			server.mkdir();
		}
		File accounts = new File("C:\\server\\accounts");
		if (!accounts.exists()) {
			accounts.mkdir();
			
		}
		File users = new File("C:\\server\\users");
		if (!users.exists()) {
			users.mkdir();
		}
		File names = new File("C:\\server/accounts/name.txt");
		if (!names.exists()) {
			names.createNewFile();
		}
		File passwords = new File("C://server/accounts/pass.txt");
		if (!passwords.exists()) {
			passwords.createNewFile();
		}
		
		
		
    
	}
	public boolean isFound(File file ,String user) throws IOException{
		BufferedReader input =new BufferedReader(new FileReader(file));
		String line =null;
		 while((line = input.readLine())!=null){
			 if(user.equals(line)){
				 return true;
			 }
		 }
		return false;
	}

	
	@Override
	public boolean signin(String email, String password) throws IOException {
		// TODO Auto-generated method stub
				folders();
				BufferedReader input = new BufferedReader(new FileReader("C:\\server/accounts/name.txt"));
				String line = null;
				String name = email;
				int i = 0;
				List<String> passwords = Files
						.readAllLines(Paths.get("C:\\server/accounts/pass.txt"),Charset.forName("ISO-8859-1"));
			
				while ((line = input.readLine()) != null) {
					if (name.equals(line)) {
						String pass = password;
						if (pass.equals(passwords.get(i))) {
							return true;
						} else {
							return false;
						}
					}
					i++;
				}
				return false;
	}
        public Single getmails(){
        SingleNode i=emails.head;
        boolean flag = false;

        int index=0;
        while (i != null) {
            if (i.value.equals("email number one")) {
                emails.remove(index);
                break;
            }
            i = i.next;
            index++;
        }
        return emails;
        }

	@SuppressWarnings("unchecked")
	@Override
	public boolean signup(IContact contact) throws IOException {
		// TODO Auto-generated method stub
		folders();
		String name = contact.email;
		BufferedReader input = new BufferedReader(new FileReader("C:\\server/accounts/name.txt"));
		String line = null;
		while ((line = input.readLine()) != null) {
			if (name.equals(line)) {
				input.close();
				return false;

			}
		}
		input.close();
		String pass = contact.password;
		PrintWriter output = new PrintWriter(new FileWriter("C:\\server/accounts/name.txt", true));
		output.printf("%s\r\n", name);
		output.close();
		PrintWriter output2 = new PrintWriter(new FileWriter("C:\\server/accounts/pass.txt", true));
		output2.printf("%s\r\n", pass);
		output2.close();
		File f1 = new File("C:\\server/users/" + name);
		f1.mkdir();
		File f2 = new File("C:\\server/users/" + name + "/inbox");
		f2.mkdir();
		
		File indexfile1 = new File("C:\\server/users/" +name + "/inbox/"+"indexfile.txt");
		
		indexfile1.createNewFile();
		/* new update */
		JSONObject obj = new JSONObject();
		JSONArray email = new JSONArray();
		
		email.add("email number one");
		email.add("suject one");
                email.add(getDate());
		email.add(1);
                email.add("sender");
        email.add("receviers");
        email.add("attach");
        email.add("attachments");
		obj.put("email number one",email);
	
		try (FileWriter file = new FileWriter("C:\\server/users/" +name + "/inbox/"+"indexfile.txt")) {
			file.write(obj.toJSONString());}
		/* end of update one */
		
		File f3 = new File("C:\\server/users/" + name + "/sent");
		f3.mkdir();
		File indexfile2 = new File("C:\\server/users/" +name + "/sent/"+"indexfile.txt");
		indexfile2.createNewFile();
		
		
		/*update two */
		 obj = new JSONObject();
		 email = new JSONArray();
		 email.add("email number one");
		 email.add("suject one");
                 		 email.add(getDate());

		 email.add(1);
                 email.add("sender");
        email.add("receviers");
        email.add("attach");
        email.add("attachments");
			obj.put("email number one",email);
			
		try (FileWriter file = new FileWriter("C:\\server/users/" +name + "/sent/"+"indexfile.txt")) {
			file.write(obj.toJSONString());}
		 /* end of update two */
		
		File f4 = new File("C:\\server/users/" + name + "/trash");
		f4.mkdir();
		File indexfile3 = new File("C:\\server/users/" +name + "/trash/"+"indexfile.txt");
		indexfile3.createNewFile();
		
		/* update three */
		obj = new JSONObject();
		 email = new JSONArray();
		 email.add("email number one");
		 email.add("suject one");
                 		 email.add(getDate());
		 email.add(1);
                 email.add("sender");
        email.add("receviers");
        email.add("attach");
        email.add("attachments");
			obj.put("email number one",email);
		try (FileWriter file = new FileWriter("C:\\server/users/" +name + "/trash/"+"indexfile.txt")) {
			file.write(obj.toJSONString());}
		/* end of update three */
		

		return true;
	}

	@Override
	public void setViewingOptions(IFolder folder, IFilter filter, ISort sort) throws IOException, ParseException {
		// TODO Auto-generated method stub
		sort = new ISort();
		File index = new File(folder.Path +"\\indexfile.txt");
		emails.clear();
		JSONParser parser_inbox = new JSONParser();
		Object obj1 = parser_inbox.parse(new FileReader(index));
		JSONObject j = (JSONObject) obj1;
		JSONArray a = new JSONArray();
		
		//get keys
		Set keys = j.keySet();
		Object [] namesOfMailes = keys.toArray();
		
		for(int i = 0; i < namesOfMailes.length; i++) {
			emails.add(namesOfMailes[i]);
		}
		
		if(isPriority) {
			emails.clear();
			emails = new Single();
			sort.priority(folder, emails);
		}
		else {

			emails.clear();
			emails = new Single();
			sort.date(folder, emails);
				}
	}

	@Override
	public IMail[] listEmails(int page) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteEmails(ILinkedList mails) {
		// TODO Auto-generated method stub
		try {
			int num = mails.size();
			File names = new File("C:\\server/accounts/name.txt");
			Object[] fileArray = Files.readAllLines(names.toPath(),Charset.forName("ISO-8859-1")).toArray();
			int num_user = fileArray.length;
			for(int i=0;i<num;i++){
				
				for(int j =1 ;j<num_user;j++){
					File user_inbox = new File("C:\\server/users/"+fileArray[j]+"/inbox/"+"indexfile.txt");
					JSONParser parser_inbox = new JSONParser();
					Object obj1 = parser_inbox.parse(new FileReader(user_inbox ));
					/*update eight */
					JSONObject jsonObject1 = (JSONObject) obj1;
					if(jsonObject1.containsKey(mails.get(i))){
						/* end of update eight */
						IFolder dest = new IFolder();
						dest.Path = "C:\\server/users/"+fileArray[j]+"/trash";
						Single mail = new Single();
						mail.add(mails.get(i));
						moveEmails(mail,dest);
						break;
					}
				   File user_sent = new File("C:\\server/users/"+fileArray[j]+"/sent/"+"indexfile.txt");
				   JSONParser parser_sent = new JSONParser();
					Object obj2 = parser_sent.parse(new FileReader(user_sent ));
					/* update nine */
					JSONObject jsonObject2 = (JSONObject) obj2;
					
					if(jsonObject2.containsKey(mails.get(i))){
						/*end of update nine */
						IFolder dest = new IFolder();
						dest.Path = "C:\\server/users/"+fileArray[j]+"/trash";
						Single mail = new Single();
						mail.add(mails.get(i));
						moveEmails(mail,dest);
						break;
					}
					
				}
				
				
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		

	}

	@SuppressWarnings("unchecked")
	@Override
	public void moveEmails(ILinkedList mails, IFolder des) {
		// TODO Auto-generated method stub
		
		
		try {
			int num = mails.size();
			File names = new File("C:\\server/accounts/name.txt");
			Object[] fileArray = Files.readAllLines(names.toPath(),Charset.forName("ISO-8859-1")).toArray();
			int num_user = fileArray.length;
			for(int i=0;i<num;i++){
				for(int j =1 ;j<num_user;j++){
					File user_inbox = new File("C:\\server/users/"+fileArray[j]+"/inbox/"+"indexfile.txt");
					JSONParser parser_inbox = new JSONParser();
					Object obj1 = parser_inbox.parse(new FileReader(user_inbox ));
					
					/* update five */
					JSONObject jsonObject1 = (JSONObject) obj1;
					
					if(jsonObject1.containsKey(mails.get(i))){
						File source = new File("C:\\server/users/"+fileArray[j]+"/inbox/"+mails.get(i));
					    File dest = new File(des.Path+"/"+mails.get(i));
					    copyDirectory(source,dest);
					    
					    // add new email to index file of dest folder
					    parser_inbox = new JSONParser();
					    obj1 = parser_inbox.parse(new FileReader(des.Path+ "/indexfile.txt" ));
						JSONObject jsonObject = (JSONObject) obj1;
						JSONArray emails = (JSONArray) jsonObject1.get(mails.get(i));
					    
						jsonObject.put(mails.get(i), emails);
						 
						 
						 try (FileWriter file = new FileWriter(des.Path+ "/indexfile.txt")) {
								file.write(jsonObject.toJSONString());}
				     
						// delete email name from source folder 
						 parser_inbox = new JSONParser();
						 obj1 = parser_inbox.parse(new FileReader("C:\\server/users/"+fileArray[j]+"/inbox/"+ "indexfile.txt" ));
						 jsonObject = (JSONObject) obj1;
						 jsonObject.remove(mails.get(i));
						 
						 emails.remove(mails.get(i));
						
						 
						 try (FileWriter file = new FileWriter("C:\\server/users/"+fileArray[j]+"/inbox/"+ "indexfile.txt")) {
								file.write(jsonObject.toJSONString());}
					    
					    /*end of update five */
					    deleteDir(source);
					    
					    break;
					}
				   File user_sent = new File("C:\\server/users/"+fileArray[j]+"/sent/"+"indexfile.txt");
				   JSONParser parser_sent = new JSONParser();
					Object obj2 = parser_sent.parse(new FileReader(user_sent ));
					
					/* update six */
					JSONObject jsonObject2 = (JSONObject) obj2;
				
					if(jsonObject2.containsKey(mails.get(i))){
						File source = new File("C:\\server/users/"+fileArray[j]+"/sent/"+mails.get(i));
						File dest = new File(des.Path+"/"+mails.get(i));
						copyDirectory(source,dest);
						
						// add new email to index file of dest folder
					    
						parser_sent = new JSONParser();
					    obj1 = parser_sent.parse(new FileReader(des.Path+ "/indexfile.txt" ));
						JSONObject jsonObject = (JSONObject) obj1;
						JSONArray emails = (JSONArray) jsonObject2.get(mails.get(i));
					    
						jsonObject.put(mails.get(i),emails);
						 
						 
						 try (FileWriter file = new FileWriter(des.Path+ "/indexfile.txt")) {
								file.write(jsonObject.toJSONString());}
						 
						 
						// delete email name from source folder 
						
						 parser_sent = new JSONParser();
						 obj1 = parser_sent.parse(new FileReader("C:\\server/users/"+fileArray[j]+"/sent/"+ "indexfile.txt" ));
						 jsonObject = (JSONObject) obj1;
						 
						 
						 jsonObject.remove(mails.get(i));
						
						 
						 try (FileWriter file = new FileWriter("C:\\server/users/"+fileArray[j]+"/sent/"+ "indexfile.txt")) {
								file.write(jsonObject.toJSONString());}
						 /* end of update six */
						deleteDir(source);
						break;
					}
					File user_trash = new File("C:\\server/users/"+fileArray[j]+"/trash/"+"indexfile.txt");
					   JSONParser parser_trash = new JSONParser();
						Object obj3 = parser_trash.parse(new FileReader(user_trash ));
						
						/*update seven */
						JSONObject jsonObject3= (JSONObject) obj3;
					
						if(jsonObject3.containsKey(mails.get(i))){
							File source = new File("C:\\server/users/"+fileArray[j]+"/trash/"+mails.get(i));
							File dest = new File(des.Path+"/"+mails.get(i));
							copyDirectory(source,dest);
							
							// add new email to index file of dest folder
						    
							parser_trash = new JSONParser();
						    obj1 = parser_trash.parse(new FileReader(des.Path+ "/indexfile.txt" ));
							JSONObject jsonObject = (JSONObject) obj1;
							JSONArray emails = (JSONArray) jsonObject3.get(mails.get(i));
						    
							jsonObject.put(mails.get(i),emails);
							
							
							 
							 try (FileWriter file = new FileWriter(des.Path+ "/indexfile.txt")) {
									file.write(jsonObject.toJSONString());}
							
							// delete email name from source folder 
								
							 parser_trash = new JSONParser();
							 obj1 = parser_trash.parse(new FileReader("C:\\server/users/"+fileArray[j]+"/trash/"+ "indexfile.txt" ));
							 jsonObject = (JSONObject) obj1;
							 
							 
							 jsonObject.remove(mails.get(i));
							 
							 
							 
							 try (FileWriter file = new FileWriter("C:\\server/users/"+fileArray[j]+"/trash/"+ "indexfile.txt")) {
									file.write(jsonObject.toJSONString());}
							 
							 /* end of update seven */
							deleteDir(source);
							break;
						}
					
				}
				
				
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		


	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean compose(IMail email) throws IOException, ParseException {
		// TODO Auto-generated method stub
		String message = email.message;
		String name = email.sender;
		boolean userFound=false;
		File sent = new File("C:\\server/users/" + name + "/sent");
		
		/* update four */
		JSONParser parser = new JSONParser();
		Object obj = parser.parse(new FileReader("C:\\server/users/" + name + "/sent/" + "indexfile.txt" ));
		JSONObject jsonObject = (JSONObject) obj;
		 JSONArray emails = new JSONArray();
		
		String[] messages = sent.list();
		int numMessage = messages.length + 1;
		File folder2 = new File("C:\\server/users/" + name + "/sent/" + "message" + numMessage +"_" + name);
		folder2.mkdirs();
                File attachment = new File("C:\\server/users/" + name + "/sent/" +
                "message" + numMessage + "_" + name+"/attachment");
        attachment.mkdirs();
       
      
      for(int i = 0;i<email.attachments.size();i++ ){
         
          File SrcPath = new File((String)email.attachments.get(i));
          File DesPath = new File(
        		  "C:\\server/users/" + name + "/sent/" +
        	                "message" + numMessage + "_" + name+"/attachment/" + SrcPath.getName() );
          DesPath.createNewFile();
          Files.copy(SrcPath.toPath(),DesPath.toPath(),
                  StandardCopyOption.REPLACE_EXISTING);
      }
		File mess = new File("C:\\server/users/" + name + "/sent/" + "message" + numMessage +"_" + name +"/"+"message" + numMessage +"_" + name+".txt");
		 mess.createNewFile();
		 
		 emails.add("message" + numMessage +"_"+name );
		 emails.add(email.subject);
                 		 emails.add(getDate());
		 emails.add(email.priority);
                  emails.add(email.sender);
       
        
        for(int n=0;n<email.receivers.size();n++){
        	emails.add(email.receivers.get(n));
        }
        emails.add("attach");
        for(int n=0;n<email.attachments.size();n++){
        	emails.add(email.attachments.get(n));
        }
		 
		 
		 jsonObject.put("message" + numMessage +"_"+name,emails );
		 
		 try (FileWriter file = new FileWriter("C:\\server/users/" + name + "/sent/" +"indexfile.txt")) {
				file.write(jsonObject.toJSONString());}
		 
		 
         BufferedWriter sentMess =new BufferedWriter(new FileWriter(mess));
         sentMess.write(message);
         sentMess.close();
         int numOfReceviers =email.receivers.size();
         String[] users=new String[numOfReceviers];
         
         for(int counter=0;counter<numOfReceviers;counter++){
     
        	 users[counter]=(String) email.receivers.get(counter);
         }
         File file =new File("C:\\server/accounts/name.txt");
         BufferedReader input =new BufferedReader(new FileReader(file));
         for(int i=0;i<users.length;i++){
        	 if(isFound(file,users[i])){
        		 File f2=new File("C:\\server/users/"+users[i]+"/inbox");
        		 String[] inbox =f2.list();
        		 int numMessage2= inbox.length+1;
        		 File folder =  new File("C:\\server/users/"+users[i]+"/inbox/"+"message"+numMessage2+"_"+name+"_"+users[i]);
        		 folder.mkdir();
                         File attachment2 = new File("C:\\server/users/" + users[i] + "/inbox/" + "message" + numMessage2 + "_" + name + "_" + users[i]+"/attachment");
                attachment2.mkdirs();
                
                for(int j = 0;j<email.attachments.size();j++ ){
                    
                    File SrcPath = new File((String)email.attachments.get(j));
                    File DesPath = new File(
                  		  "C:\\server/users/" + users[i] + "/inbox/" + "message" + numMessage2 + "_" + name + "_" + users[i]+"/attachment/" + SrcPath.getName() );
                    DesPath.createNewFile();
                    Files.copy(SrcPath.toPath(),DesPath.toPath(),
                            StandardCopyOption.REPLACE_EXISTING);
                }
        		 File mess2=new File("C:\\server/users/"+users[i]+"/inbox/"+"message"+numMessage2+"_"+name+"_"+users[i]+"/"+"message"+numMessage2+"_"+name+"_"+users[i]+".txt");
                 mess2.createNewFile();
                 
                 obj= parser.parse(new FileReader("C:\\server/users/"+users[i]+"/inbox/"+"indexfile.txt"));
         		 jsonObject= (JSONObject) obj;
         		 emails= new JSONArray();
         		
         		 emails.add("message"+numMessage2+"_"+name+"_"+users[i]);
         		 emails.add(email.subject);
                                		     emails.add(getDate());
       		     emails.add(email.priority);
                     emails.add(email.sender);
               
                
                for(int n=0;n<email.receivers.size();n++){
                	emails.add(email.receivers.get(n));
                }
                emails.add("attach");
                for(int n=0;n<email.attachments.size();n++){
                	emails.add(email.attachments.get(n));
                }
       		  jsonObject.put("message"+numMessage2+"_"+name+"_"+users[i],emails );
         		
         		try (FileWriter file2 = new FileWriter("C:\\server/users/" + users[i] + "/inbox/" +"indexfile.txt")) {
    				file2.write(jsonObject.toJSONString());
    				}
         		
         		/* END of update four */
         		
                 BufferedWriter sentMess2 =new BufferedWriter(new FileWriter(mess2));
                 sentMess2.write(message);
                 sentMess2.close();
                 userFound=true;
        	 }
         }
         if(!userFound){
		 return false;}
         else
        	return true ;
	}
	
	
	 public  boolean deleteDir(File dir) {
	      if (dir.isDirectory()) {
	         String[] children = dir.list();
	         for (int i = 0; i < children.length; i++) {
	            boolean success = deleteDir (new File(dir, children[i]));
	            
	            if (!success) {
	               return false;
	            }
	         }
	      }
	      return dir.delete();
	   }
	 
	 public void copyDirectory(File sourceLocation , File targetLocation ) throws IOException {

			        if (sourceLocation.isDirectory()) {
			            if (!targetLocation.exists()) {
			                targetLocation.mkdir();
			            }

			            String[] children = sourceLocation.list();
			            for (int i=0; i<children.length; i++) {
			                copyDirectory(new File(sourceLocation, children[i]),
			                        new File(targetLocation, children[i]));
			            }
			        } else {
                         if(sourceLocation.exists()){
			            InputStream in = new FileInputStream(sourceLocation);
			            OutputStream out = new FileOutputStream(targetLocation);

			            // Copy the bits from instream to outstream
			            byte[] buf = new byte[1024];
			            int len;
			            while ((len = in.read(buf)) > 0) {
			                out.write(buf, 0, len);
			            }
			            in.close();
			            out.close();
			        }
			        }
			    }
	 public boolean deactivate(String email) throws IOException {
		 
		 //Delete From Users Part
		 File f = new File("C:\\server\\users\\" + email);
		deleteDir(f);
		 //Delete From Accounts Part
		File users = new File("C:\\server\\accounts\\name.txt");
		
		BufferedReader reader = new BufferedReader(new FileReader(users));
		Single s = new Single();
		
		String line = reader.readLine();
		
		int myIndex = 0;
		int y = 0;
		while(line != null) {
			
			if(!(line.equals(email))) {
				s.add(line);
				line = reader.readLine();
				y++;
				continue;
			}
			line = reader.readLine();
			myIndex = y;
		}
		PrintWriter output = new PrintWriter(new FileWriter(users), true);
		
		int i = 0;
		
		while(i < s.size()) {
			line = (String) s.get(i);
			i++;
			output.printf("%s\r\n", line);
		}
			
		reader.close();
		//Delete The Password
		File users1 = new File("C:\\server\\accounts\\pass.txt");
		
		BufferedReader reader1 = new BufferedReader(new FileReader(users1));
		Single s1 = new Single();
		
		String line1 = reader1.readLine();
		
		
		while(line1 != null) {
			s1.add(line1);
			line1 = reader1.readLine();
			
		}
		s1.remove(myIndex);
		
		@SuppressWarnings("resource")
		PrintWriter output1 = new PrintWriter(new FileWriter(users1), true);
		i = 0;
		while(i < s1.size()) {
			line = (String) s1.get(i);
			i++;
			output1.printf("%s\r\n", line);
		}
		
		 return true;
	 }	 
	public IMail getmail(String name) throws FileNotFoundException, IOException, ParseException{
    	 File names = new File("C:\\server/accounts/name.txt");
         Object[] fileArray = Files.readAllLines(names.toPath(),
                 Charset.forName("ISO-8859-1")).toArray();
         int num_user = fileArray.length;
         for (int j = 1; j < num_user; j++) {
        	 
        	 // find mail in inbox folder
             File user_inbox = new File("C:\\server/users/" +
                     fileArray[j] + "/inbox/" + "indexfile.txt");
             JSONParser parser_inbox = new JSONParser();
             Object obj1 = parser_inbox.parse(new FileReader(user_inbox));
             /*update eight */
             JSONObject jsonObject1 = (JSONObject) obj1;
             if (jsonObject1.containsKey(name)) {
            	 IMail mail = new IMail();
            	JSONArray contain= (JSONArray) jsonObject1.get(name);
            	Single attachments = new Single();
            	Single receviers = new Single();
            	mail.subject=(String) contain.get(1);
            	mail.priority=(String) contain.get(2);
            	mail.sender=(String) contain.get(3);
            	int i=4;
            	
            	while(! contain.get(i).equals("attach")){
            		receviers.add(contain.get(i));
            		i++;
            	}
            	mail.receivers=receviers;
            	for(int n=i+1;n<contain.size();n++ ){
            		attachments.add(contain.get(n));
            	}
            	mail.attachments=attachments;
            	File message = new File("C:\\server\\users\\"+fileArray[j]+"\\inbox\\"+name+"\\"+name+".txt");
            	String mess = Files.readAllLines(message.toPath(),
                        Charset.forName("ISO-8859-1")).toString();
            	mail.message=mess.toString();
            	return mail; 
             }
            	 
             
             // find mail in sent folder 
             File user_sent = new File("C:\\server/users/" +
                     fileArray[j] + "/sent/" + "indexfile.txt");
             JSONParser parser_sent = new JSONParser();
             Object obj2 = parser_sent.parse(new FileReader(user_sent));
             /*update eight */
             JSONObject jsonObject2 = (JSONObject) obj2;
             if (jsonObject2.containsKey(name)) {
            	 IMail mail = new IMail();
            	JSONArray contain= (JSONArray) jsonObject2.get(name);
            	Single attachments = new Single();
            	Single receviers = new Single();
            	mail.subject=(String) contain.get(1);
            	mail.priority=(String) contain.get(2);
            	mail.sender=(String) contain.get(3);
            	int i=4;
            	
            	while(! contain.get(i).equals("attach")){
            		receviers.add(contain.get(i));
            		i++;
            	}
            	mail.receivers=receviers;
            	for(int n=i+1;n<contain.size();n++ ){
            		attachments.add(contain.get(n));
            	}
            	mail.attachments=attachments;
            	File message = new File("C:\\server\\users\\"+fileArray[j]+"\\sent\\"+name+"\\"+name+".txt");
            	String mess = Files.readAllLines(message.toPath(),
                        Charset.forName("ISO-8859-1")).toString();
            	mail.message=mess.toString();
            	return mail; 
             }
             
             
             // find mail in trash folder 
             File user_trash = new File("C:\\server/users/" +
                     fileArray[j] + "/trash/" + "indexfile.txt");
             JSONParser parser_trash = new JSONParser();
             Object obj3 = parser_sent.parse(new FileReader(user_trash));
             /*update eight */
             JSONObject jsonObject3 = (JSONObject) obj3;
             if (jsonObject3.containsKey(name)) {
            	 IMail mail = new IMail();
            	JSONArray contain= (JSONArray) jsonObject3.get(name);
            	Single attachments = new Single();
            	Single receviers = new Single();
            	mail.subject=(String) contain.get(1);
            	mail.priority=(String) contain.get(2);
            	mail.sender=(String) contain.get(3);
            	int i=4;
            	
            	while(! contain.get(i).equals("attach")){
            		receviers.add(contain.get(i));
            		i++;
            	}
            	mail.receivers=receviers;
            	for(int n=i+1;n<contain.size();n++ ){
            		attachments.add(contain.get(n));
            	}
            	mail.attachments=attachments;
            	File message = new File("C:\\server\\users\\"+fileArray[j]+"\\trash\\"+name+"\\"+name+".txt");
            	String mess = Files.readAllLines(message.toPath(),
                        Charset.forName("ISO-8859-1")).toString();
            	mail.message=mess.toString();
            	return mail; 
             }
             
             }
         
         
		return null;
    }
    public String getDate(){
       Calendar cal = new GregorianCalendar();
       int year=cal.get(Calendar.YEAR);
              int month=cal.get(Calendar.MONTH);
       int day=cal.get(Calendar.DAY_OF_MONTH);
       int hour=cal.get(Calendar.HOUR_OF_DAY);
       int min=cal.get(Calendar.MINUTE);
       int sec=cal.get(Calendar.SECOND);

		 String time=year+""+month+""+day+""+hour+":"+min+":"+sec;  
		    return time;
	}
   
}

