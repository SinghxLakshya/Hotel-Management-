package hotelmanagement;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;

public class Checkout extends JFrame implements ActionListener {

    Choice ccustomer;
    JLabel lblroomnumber, lblcheckintime, lblcheckouttime;
    JButton checkout, back, tick;

    public Checkout() {
        setLayout(null);
        getContentPane().setBackground(Color.WHITE);

        // Title
        JLabel text = new JLabel("Checkout");
        text.setFont(new Font("Tahoma", Font.BOLD, 20));
        text.setForeground(new Color(100, 100, 225));
        text.setBounds(100, 20, 100, 30);
        add(text);

        // Customer ID
        JLabel lblid = new JLabel("Customer Id");
        lblid.setBounds(30, 80, 100, 30);
        lblid.setFont(new Font("Tahoma", Font.BOLD, 12));
        add(lblid);

        ccustomer = new Choice();
        ccustomer.setBounds(150, 85, 150, 25);
        add(ccustomer);

        // Tick/Check Button
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/tick.png"));
        Image i2 = i1.getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        tick = new JButton(i3);
        tick.setBounds(310, 85, 20, 20);
        tick.setBorderPainted(false);
        tick.setContentAreaFilled(false);
        tick.setCursor(new Cursor(Cursor.HAND_CURSOR));
        tick.addActionListener(this);
        add(tick);

        // Room Number
        JLabel lblroom = new JLabel("Room Number");
        lblroom.setBounds(30, 130, 100, 30);
        lblroom.setFont(new Font("Tahoma", Font.BOLD, 12));
        add(lblroom);

        lblroomnumber = new JLabel();
        lblroomnumber.setBounds(150, 130, 150, 30);
        lblroomnumber.setFont(new Font("Tahoma", Font.BOLD, 12));
        add(lblroomnumber);

        // Checkin Time
        JLabel lblcheckin = new JLabel("Checkin Time");
        lblcheckin.setBounds(30, 180, 100, 30);
        lblcheckin.setFont(new Font("Tahoma", Font.BOLD, 12));
        add(lblcheckin);

        lblcheckintime = new JLabel();
        lblcheckintime.setBounds(150, 180, 160, 30);
        lblcheckintime.setFont(new Font("Tahoma", Font.BOLD, 12));
        add(lblcheckintime);

        // Checkout Time
        JLabel lblcheckout = new JLabel("Checkout Time");
        lblcheckout.setBounds(30, 230, 100, 30);
        lblcheckout.setFont(new Font("Tahoma", Font.BOLD, 12));
        add(lblcheckout);

        Date date = new Date();
        lblcheckouttime = new JLabel("" + date);
        lblcheckouttime.setBounds(150, 230, 180, 30);
        lblcheckouttime.setFont(new Font("Tahoma", Font.BOLD, 11));
        add(lblcheckouttime);

        // Populate Choice Dropdown & Initial Room Details
        try {
            Conn c = new Conn();
            ResultSet rs = c.s.executeQuery("SELECT * FROM customer");
            while (rs.next()) {
                ccustomer.add(rs.getString("number"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Action Buttons
        checkout = createStyledButton("Checkout", 30);
        back = createStyledButton("Back", 170);

        checkout.addActionListener(this);
        add(checkout);

        back.addActionListener(this);
        add(back);

        // Image Layout
        ImageIcon img1 = new ImageIcon(ClassLoader.getSystemResource("icons/sixth.jpg"));
        Image img2 = img1.getImage().getScaledInstance(400, 250, Image.SCALE_DEFAULT);
        ImageIcon img3 = new ImageIcon(img2);
        JLabel image = new JLabel(img3);
        image.setBounds(350, 50, 400, 250);
        add(image);

        setBounds(300, 200, 800, 400);
        setVisible(true);
    }

    private JButton createStyledButton(String text, int xPosition) {
        JButton button = new JButton(text);
        button.setBounds(xPosition, 300, 85, 30);
        button.setFont(new Font("Tahoma", Font.BOLD, 12)); // Adjusted font size to fit inside 85px width
        button.setFocusPainted(false);
        button.setBackground(new Color(0, 0, 0, 200));
        button.setForeground(Color.WHITE);
        button.setBorder(BorderFactory.createLineBorder(Color.CYAN, 2, true));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));

        // Hover effect
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
        if (ae.getSource() == tick) {
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

                if (rs.next()) {
                    lblroomnumber.setText(rs.getString("roomno"));
                    lblcheckintime.setText(rs.getString("checkin"));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (ae.getSource() == checkout) {
            String id = ccustomer.getSelectedItem();
            String room = lblroomnumber.getText();

            if (room.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Please click the tick icon to fetch room details first!");
                return;
            }

            String query1 = "DELETE FROM customer WHERE number = ?";
            String query2 = "UPDATE addrooms SET availablity = 'Available' WHERE roomno = ?";

            try {
                Conn c = new Conn();

                // Delete customer entry
                PreparedStatement pstmt1 = c.c.prepareStatement(query1);
                pstmt1.setString(1, id);
                pstmt1.executeUpdate();

                // Update room availability status back to Available
                PreparedStatement pstmt2 = c.c.prepareStatement(query2);
                pstmt2.setString(1, room);
                pstmt2.executeUpdate();

                JOptionPane.showMessageDialog(null, "Checkout Successful!");
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
        new Checkout();
    }
}