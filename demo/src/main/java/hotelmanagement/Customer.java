package hotelmanagement;

import javax.swing.JFrame;
import java.awt.*;

public class Customer extends JFrame
 {
   public Customer(){
      setLayout(null);
        setBounds(300, 100, 850, 560);
        getContentPane().setBackground(Color.WHITE);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setVisible(true);
   } 
   public static void main(String [] args){
    new Customer();
    

   }
   
}
