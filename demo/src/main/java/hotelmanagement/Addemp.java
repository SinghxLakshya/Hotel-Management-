package hotelmanagement;

import java.awt.*;

import javax.swing.*;

public class Addemp extends JFrame {
    public Addemp() {
        setLayout(null);
        setBounds(300, 100, 850, 540);
        getContentPane().setBackground(Color.white);

        // all the labels are placed here
        JLabel name = new JLabel("Name");
        name.setBounds(60, 30, 120, 30);
        name.setFont(new Font("Tahoma", Font.PLAIN, 17));
        add(name);

        JTextField lname=new JTextField();
        lname.setBounds(200, 30, 220, 30);
        add(lname);

        JLabel age = new JLabel("Age");
        age.setBounds(60, 80, 120, 30);
        age.setFont(new Font("Tahoma", Font.PLAIN, 17));
        add(age);
        JTextField tage=new JTextField();
        tage.setBounds(200, 80, 220, 30);
        add(tage);


        JLabel gnd = new JLabel("Gender");
        gnd.setBounds(60, 120, 120, 30);
        gnd.setFont(new Font("Tahoma", Font.PLAIN, 17));
        add(gnd);
        JRadioButton rbg=new JRadioButton("Male");
        rbg.setBounds(200, 120, 70, 30);
        rbg.setFont(new Font("tahoma",Font.PLAIN,14));
        rbg.setBackground(Color.white);
        add(rbg);

        JRadioButton female=new JRadioButton("Female");
        female.setBounds(290, 120, 70, 30);
        female.setFont(new Font("tahoma",Font.PLAIN,14));
        female.setBackground(Color.white);
        add(female);


        String str[]={"Front Desk clerks","Porters","House Keeping","Receptionist","Roomservice","Kitchen staff","Head-chef"};
        setVisible(true);
    }

    public static void main(String[] args) {
        new Addemp();
    }

}
