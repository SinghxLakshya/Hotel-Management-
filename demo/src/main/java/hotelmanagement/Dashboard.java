package hotelmanagement;

import java.awt.*;
import javax.swing.*;

public class Dashboard extends JFrame {


    public Dashboard(){
        
        setBounds(0,0,1550,1000);
        setLayout(null);

     ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/third.jpg"));
     Image i2=i1.getImage().getScaledInstance(1366,1000,Image.SCALE_DEFAULT);
     ImageIcon i3=new ImageIcon(i2);
     JLabel image=new JLabel(i3);
     image.setBounds(0, 0, 1366, 1000);
     add(image);



     // menubar is cooded here
        
        setVisible(true);
        }
    public static void main(String[] args) {
        new Dashboard();
    }
}
