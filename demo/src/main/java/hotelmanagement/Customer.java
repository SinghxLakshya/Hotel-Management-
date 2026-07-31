package hotelmanagement;

import javax.swing.*;
import java.awt.*;

public class Customer extends JFrame {
    JComboBox idcjb;

    public Customer() {

        JLabel heading = new JLabel("New Customer Forum");
        heading.setFont(new Font("Tahoma", Font.BOLD, 20));
        heading.setBounds(350, 10, 220, 30);
        add(heading);

     JLabel id=new JLabel("Id");
      id.setFont(new Font("tahoma", Font.PLAIN, 18));
     id.setBounds(60, 60, 200, 20);
      add(id);
 
      String sts[] = { "Aadhar Card", "Passport","Driving Licence","PAN Card" };
        idcjb = new JComboBox<>(sts);
        idcjb.setBounds(150, 60, 200, 30);
        idcjb.setBackground(Color.white);
        add(idcjb);





        setLayout(null);
        setBounds(300, 100, 850, 560);
        getContentPane().setBackground(Color.WHITE);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setVisible(true);
    }

    public static void main(String[] args) {
        new Customer();

    }

}
