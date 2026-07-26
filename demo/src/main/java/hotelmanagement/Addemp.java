package hotelmanagement;

import java.awt.*;

import javax.swing.*;

public class Addemp extends JFrame {
    public Addemp() {
        setLayout(null);
        setBounds(300, 100, 850, 540);

        // all the labels are placed here
        JLabel name = new JLabel("Name");
        name.setBounds(60, 30, 120, 30);
        name.setFont(new Font("Tahoma", Font.PLAIN, 17));
        add(name);

        JTextField lname=new JTextField();
        lname.setBounds(200, 30, 120, 30);

        JLabel age = new JLabel("Age");
        age.setBounds(60, 80, 120, 30);
        age.setFont(new Font("Tahoma", Font.PLAIN, 17));
        add(age);

        JLabel gnd = new JLabel("Gender");
        gnd.setBounds(60, 120, 120, 30);
        gnd.setFont(new Font("Tahoma", Font.PLAIN, 17));
        add(gnd);

        setVisible(true);
    }

    public static void main(String[] args) {
        new Addemp();
    }

}
