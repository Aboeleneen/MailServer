package eg.edu.alexu.csd.datastructure.mailServer.cs02_cs32_cs48;

import org.json.simple.JSONObject;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;

import org.json.simple.JSONArray;
import org.json.simple.parser.ParseException;
import org.json.simple.parser.JSONParser;

class JsonEncodeDemo {

   public static void main(String[] args) throws IOException, ParseException{
	
	   JSONParser parser = new JSONParser();
	   Object obj = parser.parse(new FileReader(
               "C:server/file1.txt"));
	   JSONObject jsonObject = (JSONObject) obj;
	   String name = (String) jsonObject.get("name");
       String author = (String) jsonObject.get("author");
       JSONArray companyList = (JSONArray) jsonObject.get("companyList");
       companyList.add("company: koko4");
       System.out.println("Name: " + name);
       System.out.println("Author: " + author);
       System.out.println("\nCompany List:");
       Iterator<String> iterator = companyList.iterator();
       while (iterator.hasNext()) {
           System.out.println(iterator.next());
       }
   }
}