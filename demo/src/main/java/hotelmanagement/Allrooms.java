package hotelmanagement;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import net.proteanit.sql.*;
public class Allrooms extends JFrame {
   public Allrooms(){
    JTable t1;
setLayout(null);
 getContentPane().setBackground(Color.WHITE);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);




 ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/eight.jpg"));
        Image i2 = i1.getImage().getScaledInstance(550, 550, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(380, 0, 550, 550);
        add(image);

t1=new JTable();
t1.setBounds(0,40,500,400);

try {
    
} catch (Exception e) {
    // TODO: handle exception
    e.printStackTrace();
}
        setBounds(300, 100, 850, 540);
       setVisible(true);
   } 





   public static void main(String[] args) {
    new Allrooms();
   }
}
