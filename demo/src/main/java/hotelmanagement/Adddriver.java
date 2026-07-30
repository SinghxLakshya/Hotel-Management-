package hotelmanagement;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.PreparedStatement;

public class Adddriver extends JFrame implements ActionListener {

    JTextField tfroom, tfprice, crmField, vehiclenoField;
    JComboBox<String> avlcjb, stscjb;
    JButton submit, cncl;

    public Adddriver() {

        JLabel heading = new JLabel("ADD DRIVERS");
        heading.setFont(new Font("Serif", Font.BOLD, 20));
        heading.setBounds(390, 10, 220, 30);
        add(heading);

        JLabel roomno = new JLabel("Driver Name");
        roomno.setFont(new Font("tahoma", Font.PLAIN, 18));
        roomno.setBounds(70, 60, 200, 20);
        add(roomno);

        tfroom = new JTextField();
        tfroom.setBounds(200, 60, 150, 30);

        // Restrict tfroom to numbers only
        tfroom.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent e) {
                char c = e.getKeyChar();
                if (!Character.isWhitespace(c) && !Character.isAlphabetic(c)
                 && c != java.awt.event.KeyEvent.VK_BACK_SPACE
                && c != java.awt.event.KeyEvent.VK_DELETE) {
                    
                    e.consume(); // Ignore digit keypresses
                }
            }
        });
        add(tfroom);

        JLabel price = new JLabel("Age");
        price.setFont(new Font("tahoma", Font.PLAIN, 18));
        price.setBounds(70, 120, 200, 20);
        add(price);

        tfprice = new JTextField();
        tfprice.setBounds(200, 120, 150, 30);
        // Restrict tfprice to numbers only
        tfprice.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent e) {
                char c = e.getKeyChar();
                if (!Character.isDigit(c)
                 && c != java.awt.event.KeyEvent.VK_BACK_SPACE
                && c != java.awt.event.KeyEvent.VK_DELETE) {
                    e.consume(); // Ignore non-digit keypresses
                }
            }
        });
        add(tfprice);

        JLabel clean = new JLabel("Gender");
        clean.setFont(new Font("tahoma", Font.PLAIN, 18));
        clean.setBounds(70, 180, 200, 20);
        add(clean);
        String sts[] = { "Male", "Female" };
        stscjb = new JComboBox<>(sts);
        stscjb.setBounds(200, 180, 150, 30);
        stscjb.setBackground(Color.white);
        add(stscjb);

        JLabel bed = new JLabel("Vehicle NO.");
        bed.setFont(new Font("tahoma", Font.PLAIN, 18));
        bed.setBounds(70, 240, 200, 20);
        add(bed);
        vehiclenoField = new JTextField();
        vehiclenoField.setBounds(200, 240, 150, 30);
        vehiclenoField.setBackground(Color.white);
        add(vehiclenoField);

        // String bd[] = { "Ford", "Hyundai", "Mahindra", "Mitsubishi", "Maruti",
        // "Renault", "Tata", "Toyota" };
        // bedcjb = new JComboBox<>(bd);
        // bedcjb.setBounds(200, 240, 150, 30);
        // bedcjb.setBackground(Color.white);
        // add(bedcjb);

        JLabel rmsts = new JLabel("Car Model");
        rmsts.setFont(new Font("tahoma", Font.PLAIN, 18));
        rmsts.setBounds(70, 300, 200, 20);
        add(rmsts);
        crmField = new JTextField();
        crmField.setBounds(200, 300, 150, 30);
        crmField.setBackground(Color.white);
        add(crmField);

        // String rm[] = { "AC", "Non Ac", "Cooler" };
        // rmcjb = new JComboBox<>(rm);
        // rmcjb.setBounds(200, 300, 150, 30);
        // rmcjb.setBackground(Color.white);
        // add(rmcjb);

        JLabel avl = new JLabel("Availablity");
        avl.setFont(new Font("tahoma", Font.PLAIN, 18));
        avl.setBounds(70, 360, 200, 20);
        add(avl);
        String str[] = { "Available", "Not available" };
        avlcjb = new JComboBox<>(str);
        avlcjb.setBounds(200, 360, 150, 30);
        avlcjb.setBackground(Color.white);
        add(avlcjb);

        submit = new JButton("Add Driver");
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

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/eleven.jpg"));
        Image i2 = i1.getImage().getScaledInstance(450, 450, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(360, 60, 480, 370);
        add(image);

        // attach listeners now that the object is fully initialized
        submit.addActionListener(this);
        cncl.addActionListener(this);

        setLayout(null);
        setBounds(300, 100, 850, 540);
        getContentPane().setBackground(Color.white);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setVisible(true);
    }

    public static void main(String[] args) {
        new Adddriver();

    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == submit) {
            String room = tfroom.getText().trim();
            String price = tfprice.getText().trim();
            String rmString = crmField.getText().trim();
            String bedString = vehiclenoField.getText().trim();
            String avlString = (String) avlcjb.getSelectedItem();
            String stsString = (String) stscjb.getSelectedItem();

            // Corrected input validation check
            if (room.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Driver Name cannot be empty.", "Validation Error",
                        JOptionPane.WARNING_MESSAGE);
                return;
            }
            if (price.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Age cannot be empty.", "Validation Error",
                        JOptionPane.WARNING_MESSAGE);
                return;
            }
            if (bedString.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Vehicle number cannot be empty.", "Validation Error",
                        JOptionPane.WARNING_MESSAGE);
                return;
            }
            if (rmString.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Car Model number cannot be empty.", "Validation Error",
                        JOptionPane.WARNING_MESSAGE);
                return;
            }

            try {
                Conn conn = new Conn();
                String query = "INSERT INTO Drivers VALUES(?,?,?,?,?,?)";
                PreparedStatement ps = conn.c.prepareStatement(query);
                ps.setString(1, room);
                ps.setString(2, price);
                ps.setString(3, stsString);
                ps.setString(4, bedString);
                ps.setString(5, rmString);
                ps.setString(6, avlString);
                ps.executeUpdate();

                JOptionPane.showMessageDialog(this, "Drivers data added successfully.");
                setVisible(false);
                dispose();
            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(this, "Database Error: " + e.getMessage(), "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        } else if (ae.getSource() == cncl) {
            setVisible(false);
            dispose();

        }
    }
}
