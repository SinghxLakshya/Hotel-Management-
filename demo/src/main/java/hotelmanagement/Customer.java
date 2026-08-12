package hotelmanagement;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;

public class Customer extends JFrame implements ActionListener {

    JComboBox<String> idCombo;
    JTextField tfnumber, tfname, tfcountry, tfdeposit, tfcheckintime;
    JRadioButton rbmale, rbfemale;
    Choice croom;
    JButton submit, cncl;

    public Customer() {

        // Heading
        JLabel heading = new JLabel("NEW CUSTOMER FORM");
        heading.setFont(new Font("Tahoma", Font.PLAIN, 20));
        heading.setBounds(100, 20, 300, 30);
        add(heading);

        // ID Label & ComboBox
        JLabel lblid = new JLabel("ID");
        lblid.setFont(new Font("Tahoma", Font.PLAIN, 18));
        lblid.setBounds(35, 80, 100, 25);
        add(lblid);

        String options[] = { "Passport", "Aadhar Card", "Driving License", "Voter Id" };
        idCombo = new JComboBox<>(options);
        idCombo.setBounds(200, 80, 150, 25);
        idCombo.setBackground(Color.WHITE);
        add(idCombo);

        // Number Label & TextField
        JLabel lblnumber = new JLabel("Number");
        lblnumber.setFont(new Font("Tahoma", Font.PLAIN, 18));
        lblnumber.setBounds(35, 120, 100, 25);
        add(lblnumber);

        tfnumber = new JTextField();
        tfnumber.setBounds(200, 120, 150, 25);
        // Restrict tfroom to numbers only
        tfnumber.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent e) {
                char c = e.getKeyChar();
                if (!Character.isDigit(c)
                        && c != java.awt.event.KeyEvent.VK_BACK_SPACE
                        && c != java.awt.event.KeyEvent.VK_DELETE) {

                    e.consume(); // Ignore digit keypresses
                }
            }
        });
        add(tfnumber);

        // Name Label & TextField
        JLabel lblname = new JLabel("Name");
        lblname.setFont(new Font("Tahoma", Font.PLAIN, 18));
        lblname.setBounds(35, 160, 100, 25);
        add(lblname);

        tfname = new JTextField();
        tfname.setBounds(200, 160, 150, 25);

        tfname.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent e) {
                char c = e.getKeyChar();
                if (!Character.isWhitespace(c) && !Character.isAlphabetic(c)
                        && c != java.awt.event.KeyEvent.VK_BACK_SPACE
                        && c != java.awt.event.KeyEvent.VK_DELETE) {

                    e.consume(); // Ignore digit keypresses
                }
            }
        });
        add(tfname);

        // Gender Label & RadioButtons
        JLabel lblgender = new JLabel("Gender");
        lblgender.setFont(new Font("Tahoma", Font.PLAIN, 18));
        lblgender.setBounds(35, 200, 100, 25);
        add(lblgender);

        rbmale = new JRadioButton("Male");
        rbmale.setFont(new Font("Tahoma", Font.PLAIN, 14));
        rbmale.setBounds(200, 200, 60, 25);
        rbmale.setBackground(Color.WHITE);
        add(rbmale);

        rbfemale = new JRadioButton("Female");
        rbfemale.setFont(new Font("Tahoma", Font.PLAIN, 14));
        rbfemale.setBounds(270, 200, 80, 25);
        rbfemale.setBackground(Color.WHITE);
        add(rbfemale);

        ButtonGroup bg = new ButtonGroup();
        bg.add(rbmale);
        bg.add(rbfemale);

        // Country Label & TextField
        JLabel lblcountry = new JLabel("Country");
        lblcountry.setFont(new Font("Tahoma", Font.PLAIN, 18));
        lblcountry.setBounds(35, 240, 100, 25);
        add(lblcountry);

        tfcountry = new JTextField();
        tfcountry.setBounds(200, 240, 150, 25);
        add(tfcountry);

        // Room Number Label & Choice Dropdown
        JLabel lblroom = new JLabel("Room Number");
        lblroom.setFont(new Font("Tahoma", Font.PLAIN, 18));
        lblroom.setBounds(35, 280, 150, 25);
        add(lblroom);

        croom = new Choice();
        try {
            Conn conn = new Conn();
            String query1 = "select *from addrooms where availablity='Available'";
            ResultSet rs = conn.s.executeQuery(query1);
            while (rs.next()) {
                croom.add(rs.getString("roomno"));

            }
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }

        // croom.add("101");
        // croom.add("102");
        // croom.add("103");
        // croom.add("104");
        croom.setBounds(200, 280, 150, 25);
        add(croom);

        // Check-in Time Label & Field
        JLabel lbltime = new JLabel("Checkin time");
        lbltime.setFont(new Font("Tahoma", Font.PLAIN, 18));
        lbltime.setBounds(35, 320, 150, 25);
        add(lbltime);

        Date date = new Date();
        tfcheckintime = new JTextField("" + date);
        tfcheckintime.setBounds(200, 320, 150, 25);
        tfcheckintime.setFont(new Font("Tahoma", Font.PLAIN, 12));
        tfcheckintime.setBorder(null);
        tfcheckintime.setBackground(Color.WHITE);
        tfcheckintime.setEditable(false);
        add(tfcheckintime);

        // Deposit Label & TextField
        JLabel lbldeposit = new JLabel("Deposit");
        lbldeposit.setFont(new Font("Tahoma", Font.PLAIN, 18));
        lbldeposit.setBounds(35, 360, 100, 25);
        add(lbldeposit);

        tfdeposit = new JTextField();
        tfdeposit.setBounds(200, 360, 150, 25);
        tfdeposit.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent e) {
                char c = e.getKeyChar();
                if (!Character.isDigit(c)
                        && c != java.awt.event.KeyEvent.VK_BACK_SPACE
                        && c != java.awt.event.KeyEvent.VK_DELETE) {

                    e.consume(); // Ignore digit keypresses
                }
            }
        });
        add(tfdeposit);

        // Buttons
        submit = new JButton("Add Customer");
        submit.setBounds(200, 450, 150, 30);
        submit.setFont(new Font("Arial", Font.BOLD, 16));
        submit.setFocusPainted(false);
        submit.setBackground(new Color(0, 0, 0, 200));
        submit.setForeground(Color.WHITE);
        submit.setBorder(BorderFactory.createLineBorder(Color.CYAN, 2, true));
        submit.setCursor(new Cursor(Cursor.HAND_CURSOR));
        // action listener will be attached after full initialization to avoid 'this'
        // escaping during construction

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
        // action listener will be attached after full initialization to avoid 'this'
        // escaping during construction

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
        // attach listeners now that the object is fully initialized
        submit.addActionListener(this);
        cncl.addActionListener(this);

        // Image Label
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/cust.png"));

        Image i2 = i1.getImage().getScaledInstance(350, 450, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(470, 30, 350, 450);
        add(image);

        // Frame Settings
        setLayout(null);
        setBounds(300, 100, 850, 540);
        getContentPane().setBackground(Color.WHITE);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == submit) {
            // Add database insert logic here
            String number = tfnumber.getText().trim();
            String name = tfname.getText().trim();
            String country = tfcountry.getText().trim();
            String deposit = tfdeposit.getText().trim();
            String id = (String) idCombo.getSelectedItem();
            String gnd = null;
            if (rbmale.isSelected()) {
                gnd = "Male";
            } else if (rbfemale.isSelected()) {
                gnd = "Female";
            }
            String rommno = (String) croom.getSelectedItem();
            String time = tfcheckintime.getText();

            // Validation Checks
            if (number.equals("")) {
                JOptionPane.showMessageDialog(null, "ID Number field cannot be empty!", "Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (name.equals("")) {
                JOptionPane.showMessageDialog(null, "Name field cannot be empty!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (!rbmale.isSelected() && !rbfemale.isSelected()) {
                JOptionPane.showMessageDialog(null, "Please select a Gender!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (country.equals("")) {
                JOptionPane.showMessageDialog(null, "Country field cannot be empty!", "Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (deposit.equals("")) {
                JOptionPane.showMessageDialog(null, "Deposit field cannot be empty!", "Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }
            try {
                Conn conn = new Conn();
                String query = "INSERT INTO customer VALUES(?,?,?,?,?,?,?,?)";
                String query2 = "update addrooms set availablity='occupied' where roomno='" + rommno + "'";
                ;
                PreparedStatement ps = conn.c.prepareStatement(query);
                PreparedStatement qs = conn.c.prepareStatement(query2);
                ps.setString(1, id);
                ps.setString(2, number);
                ps.setString(3, name);
                ps.setString(4, gnd);
                ps.setString(5, country);
                ps.setString(6, rommno);
                ps.setString(7, time);
                ps.setString(8, deposit);
                ps.executeUpdate();
                qs.executeUpdate();

                // conn.s.executeUpdate(query2);
                JOptionPane.showMessageDialog(this, "Customer data added successfully.");
                setVisible(false);
                dispose();
                new Reception();

            } catch (Exception e) {
                // TODO: handle exception
                e.printStackTrace();
                JOptionPane.showMessageDialog(this, "Database Error: " + e.getMessage(), "Error",
                        JOptionPane.ERROR_MESSAGE);
            }

            JOptionPane.showMessageDialog(null, "New Customer Added Successfully");
            setVisible(false);
        } else if (ae.getSource() == cncl) {
            setVisible(false);
            dispose();

            new Reception(); // Open previous screen
        }
    }

    public static void main(String[] args) {
        new Customer();
    }
}