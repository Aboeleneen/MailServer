package eg.edu.alexu.csd.datastructure.mailServer.cs02_cs32_cs48;






import eg.edu.alexu.csd.datastructure.mailServer.cs02_cs32_cs48.Splash;
import eg.edu.alexu.csd.datastructure.mailServer.cs02_cs32_cs48.signupdemo;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Geek
 */
public class Start {
     public static void main(String[] args) {
        // TODO code application logic here
         Splash y=new Splash();
         y.setVisible(true);
         signupdemo x=new signupdemo();
         try {
             
         for(int i=0;i<=100;i++){
         Thread.sleep(40);
y.loading.setText(Integer.toString(i)+"%");
y.loadingbar.setValue(i);
if(i==100)
         {
             y.loading.setText("Welcome.");
                      Thread.sleep(400);
             y.loading.setText("Welcome..");
                      Thread.sleep(400);
             y.loading.setText("Welcome...");
                      Thread.sleep(400);
         signupdemo v=new signupdemo();
         v.setVisible(true);
         y.setVisible(false);
         }
         
         }}
         catch (InterruptedException ex) {
                 Logger.getLogger(Start.class.getName()).log(Level.SEVERE, null, ex);
             }
         
    }
}
