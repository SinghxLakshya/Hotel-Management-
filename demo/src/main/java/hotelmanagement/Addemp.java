package hotelmanagement;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;

import javax.swing.*;


public class Addemp extends JFrame implements ActionListener {

    JTextField lname, tage, slryField, phnField, emField;
    JRadioButton rbg, female;
    JButton submit, cncl;
    JComboBox <String> cjb;

    public Addemp() {

        // all the labels are placed here
        JLabel name = new JLabel("Name");
        name.setBounds(60, 40, 120, 30);
        name.setFont(new Font("Tahoma", Font.PLAIN, 17));
        add(name);

        lname = new JTextField();
        lname.setBounds(200, 40, 220, 30);
        add(lname);

        JLabel age = new JLabel("Age");
        age.setBounds(60, 100, 120, 30);
        age.setFont(new Font("Tahoma", Font.PLAIN, 17));
        add(age);
        tage = new JTextField();
        tage.setBounds(200, 100, 220, 30);
        add(tage);

        JLabel gnd = new JLabel("Gender");
        gnd.setBounds(60, 160, 120, 30);
        gnd.setFont(new Font("Tahoma", Font.PLAIN, 17));
        add(gnd);
        rbg = new JRadioButton("Male");
        rbg.setBounds(200, 160, 70, 30);
        rbg.setFont(new Font("tahoma", Font.PLAIN, 14));
        rbg.setBackground(Color.white);
        add(rbg);

        female = new JRadioButton("Female");
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

        String str[] = { "Manager","Front Desk clerks", "Porters", "House Keeping", "Receptionist", "Roomservice",
                "Kitchen staff", "Head-chef" };
        cjb = new JComboBox<>(str);
        cjb.setBounds(200, 220, 150, 30);
        cjb.setBackground(Color.white);
        add(cjb);

        JLabel slry = new JLabel("Salary");
        slry.setBounds(60, 280, 120, 30);
        slry.setFont(new Font("Tahoma", Font.PLAIN, 17));
        add(slry);
        slryField = new JTextField();
        slryField.setBounds(200, 280, 220, 30);
        add(slryField);

        JLabel phn = new JLabel("Phone");
        phn.setBounds(60, 340, 120, 30);
        phn.setFont(new Font("Tahoma", Font.PLAIN, 17));
        add(phn);
        phnField = new JTextField();
        phnField.setBounds(200, 340, 220, 30);
        add(phnField);
        JLabel eml = new JLabel("Email");
        eml.setBounds(60, 400, 120, 30);
        eml.setFont(new Font("Tahoma", Font.PLAIN, 17));
        add(eml);
        emField = new JTextField();
        emField.setBounds(200, 400, 220, 30);
        add(emField);

        submit = new JButton("Submit");
        submit.setBounds(200, 450, 150, 30);
        submit.setFont(new Font("Arial", Font.BOLD, 16));
        submit.setFocusPainted(false);
        submit.setBackground(new Color(0, 0, 0, 200));
        submit.setForeground(Color.WHITE);
        submit.setBorder(BorderFactory.createLineBorder(Color.CYAN, 2, true));
        submit.setCursor(new Cursor(Cursor.HAND_CURSOR));
        submit.addActionListener(this);

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
        cncl = new JButton("Cancel");
        cncl.setBounds(400, 450, 150, 30);
        cncl.setFont(new Font("Arial", Font.BOLD, 16));
        cncl.setFocusPainted(false);
        cncl.setBackground(new Color(0, 0, 0, 200));
        cncl.setForeground(Color.WHITE);
        cncl.setBorder(BorderFactory.createLineBorder(Color.CYAN, 2, true));
        cncl.setCursor(new Cursor(Cursor.HAND_CURSOR));
        cncl.addActionListener(this);

        // Hover effect
        cncl.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent evt) {
                cncl.setBackground(new Color(0, 120, 215));
                cncl.setForeground(Color.WHITE);
            }

            public void mouseExited(MouseEvent evt) {
                cncl.setBackground(new Color(0, 0, 0, 200));
                cncl.setForeground(Color.WHITE);
            }
        });
        add(cncl);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/tenth.jpg"));
        Image i2 = i1.getImage().getScaledInstance(450, 450, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(410, 70, 450, 380);
        add(image);

        setLayout(null);
        setBounds(300, 100, 850, 540);
        getContentPane().setBackground(Color.white);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

   public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == submit) {
            String name = lname.getText();
            String age = tage.getText();
            String slry = slryField.getText();
            String phn = phnField.getText();
            String eml = emField.getText();

            String gnd = null;
            if (rbg.isSelected()) {
                gnd = "Male";
            } else if (female.isSelected()) {
                gnd = "Female";
            }

            String job = (String) cjb.getSelectedItem();

            if (name.equals("")) {
                JOptionPane.showMessageDialog(null, "Name should not be empty");
                return;
            }

            try {
                Conn conn = new Conn();
                String query = "insert into employee1 values('" + name + "','" + age + "','" + gnd + "','" + job + "','"
                        + slry + "','" + phn + "','" + eml + "')";

                conn.s.executeUpdate(query);
                JOptionPane.showMessageDialog(null, "Employee data added successfully");
                setVisible(false);

            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (ae.getSource() == cncl) {
            setVisible(false);
            dispose();
        }
    }
    

    public static void main(String[] args) {
        new Addemp();
    }

}
