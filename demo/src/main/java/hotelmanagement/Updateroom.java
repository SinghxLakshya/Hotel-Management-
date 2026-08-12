package hotelmanagement;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Updateroom extends JFrame implements ActionListener {

    Choice ccustomer;
    JTextField tfroom, tfavailable, tfstatus;
    JButton check, update, back;

    public Updateroom() {
        setLayout(null);
        getContentPane().setBackground(Color.WHITE);

        // Heading
        JLabel heading = new JLabel("Update Room Status");
        heading.setFont(new Font("Tahoma", Font.BOLD, 20));
        heading.setForeground(new Color(0, 102, 204));
        heading.setBounds(30, 20, 250, 30);
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
                ccustomer.add(rs.getString("number"));
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

        // Availability
        JLabel lblavailable = new JLabel("Availability");
        lblavailable.setBounds(30, 160, 100, 20);
        lblavailable.setFont(new Font("Tahoma", Font.BOLD, 12));
        add(lblavailable);

        tfavailable = new JTextField();
        tfavailable.setBounds(150, 160, 150, 25);
        add(tfavailable);

        // Cleaning Status
        JLabel lblstatus = new JLabel("Cleaning Status");
        lblstatus.setBounds(30, 200, 100, 20);
        lblstatus.setFont(new Font("Tahoma", Font.BOLD, 12));
        add(lblstatus);

        tfstatus = new JTextField();
        tfstatus.setBounds(150, 200, 150, 25);
        add(tfstatus);

        // Action Buttons
        check = createStyledButton("Check", 30);
        update = createStyledButton("Update", 125);
        back = createStyledButton("Back", 220);

        check.addActionListener(this);
        add(check);

        update.addActionListener(this);
        add(update);

        back.addActionListener(this);
        add(back);

        // Right side image layout
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/seventh.jpg"));
        Image i2 = i1.getImage().getScaledInstance(500, 370, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(330, 38, 500, 370);
        add(image);

        setBounds(300, 100, 850, 450);
        setVisible(true);
    }

    private JButton createStyledButton(String text, int xPosition) {
        JButton button = new JButton(text);
        button.setBounds(xPosition, 310, 85, 30);
        button.setFont(new Font("Tahoma", Font.BOLD, 12));
        button.setFocusPainted(false);
        button.setBackground(new Color(0, 0, 0, 200));
        button.setForeground(Color.WHITE);
        button.setBorder(BorderFactory.createLineBorder(Color.CYAN, 2, true));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));

        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent evt) {
                button.setBackground(new Color(0, 120, 215));
                button.setForeground(Color.WHITE);
            }

            @Override
            public void mouseExited(MouseEvent evt) {
                button.setBackground(new Color(0, 0, 0, 200));
                button.setForeground(Color.WHITE);
            }
        });

        return button;
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == check) {
            String id = ccustomer.getSelectedItem();
            if (id == null || id.isEmpty()) {
                JOptionPane.showMessageDialog(null, "No customer selected!");
                return;
            }

            try {
                Conn c = new Conn();
                PreparedStatement pstmt = c.c.prepareStatement("SELECT * FROM customer WHERE number = ?");
                pstmt.setString(1, id);
                ResultSet rs = pstmt.executeQuery();

                while (rs.next()) {
                    tfroom.setText(rs.getString("roomno"));
                }

                // Fetch status of room from addrooms table
                PreparedStatement pstmt2 = c.c.prepareStatement("SELECT * FROM addrooms WHERE roomno = ?");
                pstmt2.setString(1, tfroom.getText());
                ResultSet rs2 = pstmt2.executeQuery();

                while (rs2.next()) {
                    tfavailable.setText(rs2.getString("availablity"));
                    tfstatus.setText(rs2.getString("cleaning_status"));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (ae.getSource() == update) {
            String room = tfroom.getText();
            String available = tfavailable.getText();
            String status = tfstatus.getText();

            try {
                Conn c = new Conn();
                PreparedStatement pstmt = c.c.prepareStatement(
                    "UPDATE addrooms SET availablity = ?, cleaning_status = ? WHERE roomno = ?"
                );

                pstmt.setString(1, available);
                pstmt.setString(2, status);
                pstmt.setString(3, room);

                pstmt.executeUpdate();
                JOptionPane.showMessageDialog(null, "Room Status Updated Successfully");
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
        new Updateroom();
    }
}