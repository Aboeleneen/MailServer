package eg.edu.alexu.csd.datastructure.mailServer.cs02_cs32_cs48;



/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import eg.edu.alexu.csd.datastructure.mailServer.IMail;
import eg.edu.alexu.csd.datastructure.linkedList.cs32_cs02.Single;
import eg.edu.alexu.csd.datastructure.mailServer.cs02_cs32_cs48.inboxdemo;

/**
 *
 * @author Geek
 */
public class Mains {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
Readmessage y=new Readmessage();
IMail c=new IMail();
Single l=new Single();
l.add("C:\\server\\13903221_1633913230240914_2907273290759126889_n.jpg");
l.add("C:\\server\\14362638_1235297556491369_7693166304055219420_o.jpg");
l.add("C:\\server\\13903221_1633913230240914_2907273290759126889_n.jpg");
l.add("C:\\server\\13903221_1633913230240914_2907273290759126889_n.jpg");
l.add("C:\\server\\13903221_1633913230240914_2907273290759126889_n.jpg");
l.add("C:\\server\\13903221_1633913230240914_2907273290759126889_n.jpg");
l.add("C:\\server\\13903221_1633913230240914_2907273290759126889_n.jpg");
l.add("C:\\server\\13903221_1633913230240914_2907273290759126889_n.jpg");
l.add("C:\\server\\13903221_1633913230240914_2907273290759126889_n.jpg");
l.add("C:\\server\\13903221_1633913230240914_2907273290759126889_n.jpg");
l.add("C:\\server\\13903221_1633913230240914_2907273290759126889_n.jpg");
l.add("C:\\server\\13903221_1633913230240914_2907273290759126889_n.jpg");
l.add("C:\\server\\13903221_1633913230240914_2907273290759126889_n.jpg");
l.add("C:\\server\\13903221_1633913230240914_2907273290759126889_n.jpg");

c.attachments=l;
c.message="hi kimo\n are you fine";
c.sender="kimo";
c.subject="haha";
y.setmail(c);
y.setVisible(true);
    }
    
}
