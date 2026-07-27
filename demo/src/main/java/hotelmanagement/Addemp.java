package hotelmanagement;

import java.awt.*;
import java.awt.event.MouseAdapter;

import javax.swing.*;

import org.w3c.dom.events.MouseEvent;

public class Addemp extends JFrame {
    public Addemp() {
        setLayout(null);
        setBounds(300, 100, 850, 540);
        getContentPane().setBackground(Color.white);

        // all the labels are placed here
        JLabel name = new JLabel("Name");
        name.setBounds(60, 40, 120, 30);
        name.setFont(new Font("Tahoma", Font.PLAIN, 17));
        add(name);

        JTextField lname = new JTextField();
        lname.setBounds(200, 40, 220, 30);
        add(lname);

        JLabel age = new JLabel("Age");
        age.setBounds(60, 100, 120, 30);
        age.setFont(new Font("Tahoma", Font.PLAIN, 17));
        add(age);
        JTextField tage = new JTextField();
        tage.setBounds(200, 100, 220, 30);
        add(tage);

        JLabel gnd = new JLabel("Gender");
        gnd.setBounds(60, 160, 120, 30);
        gnd.setFont(new Font("Tahoma", Font.PLAIN, 17));
        add(gnd);
        JRadioButton rbg = new JRadioButton("Male");
        rbg.setBounds(200, 160, 70, 30);
        rbg.setFont(new Font("tahoma", Font.PLAIN, 14));
        rbg.setBackground(Color.white);
        add(rbg);

        JRadioButton female = new JRadioButton("Female");
        female.setBounds(290, 160, 70, 30);
        female.setFont(new Font("tahoma", Font.PLAIN, 14));
        female.setBackground(Color.white);
        add(female);

        // FIXED: Group gender radio buttons so only one can be selected
        ButtonGroup genderGroup = new ButtonGroup();
        genderGroup.add(rbg);
        genderGroup.add(female);

        JLabel job = new JLabel("Job");
        job.setBounds(60, 220, 120, 30);
        job.setFont(new Font("Tahoma", Font.PLAIN, 17));
        add(job);

        String str[] = { "Front Desk clerks", "Porters", "House Keeping", "Receptionist", "Roomservice",
                "Kitchen staff", "Head-chef" };
        JComboBox cjb = new JComboBox(str);
        cjb.setBounds(200, 220, 150, 30);
        cjb.setBackground(Color.white);
        add(cjb);

        JLabel slry = new JLabel("Salary");
        slry.setBounds(60, 280, 120, 30);
        slry.setFont(new Font("Tahoma", Font.PLAIN, 17));
        add(slry);
        JTextField slryField = new JTextField();
        slryField.setBounds(200, 280, 220, 30);
        add(slryField);

        JLabel phn = new JLabel("Phone");
        phn.setBounds(60, 340, 120, 30);
        phn.setFont(new Font("Tahoma", Font.PLAIN, 17));
        add(phn);
        JTextField pFieldhn = new JTextField();
        pFieldhn.setBounds(200, 340, 220, 30);
        add(pFieldhn);
        JLabel eml = new JLabel("Email");
        eml.setBounds(60, 400, 120, 30);
        eml.setFont(new Font("Tahoma", Font.PLAIN, 17));
        add(eml);
        JTextField emField = new JTextField();
        emField.setBounds(200, 400, 220, 30);
        add(emField);

        JButton submit = new JButton("Submit");
        submit.setBounds(200, 450, 150, 30);
        submit.setFont(new Font("Arial", Font.BOLD, 16));
        submit.setFocusPainted(false);
        submit.setBackground(new Color(0, 0, 0, 200));
        submit.setForeground(Color.WHITE);
        submit.setBorder(BorderFactory.createLineBorder(Color.CYAN, 2, true));
        submit.setCursor(new Cursor(Cursor.HAND_CURSOR));

        // Hover effect
        submit.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent evt) {
                submit.setBackground(new Color(0, 120, 215));
                submit.setForeground(Color.WHITE);
            }

            public void mouseExited(MouseEvent evt) {
                submit.setBackground(new Color(0, 0, 0, 200));
                submit.setForeground(Color.WHITE);
            }
        });
        add(submit);

        // submit.setBounds(200,430,150,30);
        // submit.setBackground(Color.white);
        // submit.setForeground(Color.black);

        setVisible(true);
    }

    public static void main(String[] args) {
        new Addemp();
    }

}
