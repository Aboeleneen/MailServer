package eg.edu.alexu.csd.datastructure.mailServer;


import eg.edu.alexu.csd.datastructure.linkedList.cs32_cs02.Single;


public class IMail {
   public String sender;
   public Single receivers= new Single() ;
   public String message ;
   public Single attachments= new Single();
   public String subject;
   public String priority ;
}