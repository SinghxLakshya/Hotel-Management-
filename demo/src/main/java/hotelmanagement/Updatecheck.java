package hotelmanagement;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;

public class Updatecheck extends JFrame implements ActionListener {

    Choice ccustomer;
    JTextField tfroom, tfname, tfcheckin, tfpaid, tfpending;
    JButton check, update, back;

    public Updatecheck() {
        setLayout(null);
        getContentPane().setBackground(Color.WHITE);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Heading
        JLabel heading = new JLabel("Update Status");
        heading.setFont(new Font("Tahoma", Font.BOLD, 20));
        heading.setForeground(new Color(0, 102, 204));
        heading.setBounds(120, 20, 200, 30);
        add(heading);

        // Customer ID
        JLabel lblid = new JLabel("Customer Id");
        lblid.setBounds(30, 80, 100, 20);
        lblid.setFont(new Font("Tahoma", Font.BOLD, 12));
        add(lblid);

        ccustomer = new Choice();
        ccustomer.setBounds(150, 80, 150, 25);
        add(ccustomer);

        // Fetch Customer IDs from database
        try {
            Conn c = new Conn();
            ResultSet rs = c.s.executeQuery("SELECT * FROM customer");
            while (rs.next()) {
                ccustomer.add(rs.getString("number")); // Assumes 'number' store the ID value
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Room Number
        JLabel lblroom = new JLabel("Room Number");
        lblroom.setBounds(30, 120, 100, 20);
        lblroom.setFont(new Font("Tahoma", Font.BOLD, 12));
        add(lblroom);

        tfroom = new JTextField();
        tfroom.setBounds(150, 120, 150, 25);
        add(tfroom);

        // Name
        JLabel lblname = new JLabel("Name");
        lblname.setBounds(30, 160, 100, 20);
        lblname.setFont(new Font("Tahoma", Font.BOLD, 12));
        add(lblname);

        tfname = new JTextField();
        tfname.setBounds(150, 160, 150, 25);
        add(tfname);

        // Checkin Time
        JLabel lblcheckin = new JLabel("Checkin Time");
        lblcheckin.setBounds(30, 200, 100, 20);
        lblcheckin.setFont(new Font("Tahoma", Font.BOLD, 12));
        add(lblcheckin);

        tfcheckin = new JTextField();
        tfcheckin.setBounds(150, 200, 150, 25);
        add(tfcheckin);

        // Amount Paid
        JLabel lblpaid = new JLabel("Amount Paid");
        lblpaid.setBounds(30, 240, 100, 20);
        lblpaid.setFont(new Font("Tahoma", Font.BOLD, 12));
        add(lblpaid);

        tfpaid = new JTextField();
        tfpaid.setBounds(150, 240, 150, 25);
        add(tfpaid);

        // Pending Amount
        JLabel lblpending = new JLabel("Pending Amount");
        lblpending.setBounds(30, 280, 120, 20);
        lblpending.setFont(new Font("Tahoma", Font.BOLD, 12));
        add(lblpending);

        tfpending = new JTextField();
        tfpending.setBounds(150, 280, 150, 25);
        add(tfpending);

        // Action Buttons
        check = new JButton("Check");
        check.setBackground(Color.BLACK);
        check.setForeground(Color.WHITE);
        check.setBounds(30, 340, 80, 30);
        check.setFocusPainted(false);
        check.setCursor(new Cursor(Cursor.HAND_CURSOR));
        check.addActionListener(this);
        add(check);

        update = new JButton("Update");
        update.setBackground(Color.BLACK);
        update.setForeground(Color.WHITE);
        update.setBounds(125, 340, 80, 30);
        update.setFocusPainted(false);
        update.setCursor(new Cursor(Cursor.HAND_CURSOR));
        update.addActionListener(this);
        add(update);

        back = new JButton("Back");
        back.setBackground(Color.BLACK);
        back.setForeground(Color.WHITE);
        back.setBounds(220, 340, 80, 30);
        back.setFocusPainted(false);
        back.setCursor(new Cursor(Cursor.HAND_CURSOR));
        back.addActionListener(this);
        add(back);

        // Right side image layout
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/nine.jpg")); // Ensure image path matches your project assets
        Image i2 = i1.getImage().getScaledInstance(400, 250, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(330, 60, 450, 300);
        add(image);

        setBounds(300, 100, 850, 450);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == check) {
            String id = ccustomer.getSelectedItem();
            String query = "SELECT * FROM customer WHERE number = '" + id + "'";
            try {
                Conn c = new Conn();
                ResultSet rs = c.s.executeQuery(query);
                while (rs.next()) {
                    tfroom.setText(rs.getString("room"));
                    tfname.setText(rs.getString("name"));
                    tfcheckin.setText(rs.getString("checkintime"));
                    tfpaid.setText(rs.getString("deposit"));
                }

                // Fetch total room price to calculate pending amount
                ResultSet rs2 = c.s.executeQuery("SELECT * FROM addrooms WHERE roomnumber = '" + tfroom.getText() + "'");
                while (rs2.next()) {
                    String price = rs2.getString("price");
                    int amountPaid = Integer.parseInt(tfpaid.getText());
                    int pendingAmount = Integer.parseInt(price) - amountPaid;
                    tfpending.setText("" + pendingAmount);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (ae.getSource() == update) {
            String number = ccustomer.getSelectedItem();
            String room = tfroom.getText();
            String name = tfname.getText();
            String checkin = tfcheckin.getText();
            String deposit = tfpaid.getText();

            try {
                Conn c = new Conn();
                c.s.executeUpdate("UPDATE customer SET room = '" + room + "', name = '" + name + "', checkintime = '" + checkin + "', deposit = '" + deposit + "' WHERE number = '" + number + "'");
                JOptionPane.showMessageDialog(null, "Data Updated Successfully");
                setVisible(false);
                new Reception();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (ae.getSource() == back) {
            setVisible(false);
            new Reception();
        }
    }

    public static void main(String[] args) {
        new Updatecheck();
    }
}