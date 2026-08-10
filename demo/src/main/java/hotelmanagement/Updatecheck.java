package hotelmanagement;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.PreparedStatement;
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
        // Restrict tfroom to numbers only
        tfpaid.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent e) {
                char c = e.getKeyChar();
                if (!Character.isDigit(c)) {
                    e.consume(); // Ignore non-digit keypresses
                }
            }
        });
        add(tfpaid);

        // Pending Amount
        JLabel lblpending = new JLabel("Pending Amount");
        lblpending.setBounds(30, 280, 120, 20);
        lblpending.setFont(new Font("Tahoma", Font.BOLD, 12));
        add(lblpending);

        tfpending = new JTextField();
        tfpending.setBounds(150, 280, 150, 25);
        // Restrict tfroom to numbers only
        tfpending.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent e) {
                char c = e.getKeyChar();
                if (!Character.isDigit(c)) {
                    e.consume(); // Ignore non-digit keypresses
                }
            }
        });
        add(tfpending);

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
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/nine.jpg"));
        Image i2 = i1.getImage().getScaledInstance(400, 250, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(330, 60, 450, 300);
        add(image);

        setBounds(300, 100, 850, 450);
        setVisible(true);
    }

    private JButton createStyledButton(String text, int xPosition) {
        JButton button = new JButton(text);
        button.setBounds(xPosition, 340, 85, 30);
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
                    tfname.setText(rs.getString("name"));
                    tfcheckin.setText(rs.getString("checkin"));
                    tfpaid.setText(rs.getString("deposit"));
                }

                // Fetch total room price to calculate pending amount
                PreparedStatement pstmt2 = c.c.prepareStatement("SELECT * FROM addrooms WHERE roomno = ?");
                pstmt2.setString(1, tfroom.getText());
                ResultSet rs2 = pstmt2.executeQuery();

                while (rs2.next()) {
                    String priceStr = rs2.getString("price");
                    int price = (priceStr != null && !priceStr.isEmpty()) ? Integer.parseInt(priceStr) : 0;
                    
                    String paidStr = tfpaid.getText();
                    int amountPaid = (paidStr != null && !paidStr.isEmpty()) ? Integer.parseInt(paidStr) : 0;
                    
                    int pendingAmount = price - amountPaid;
                    tfpending.setText(String.valueOf(pendingAmount));
                }
            } catch (NumberFormatException nfe) {
                JOptionPane.showMessageDialog(null, "Invalid number format in amount paid or amount pending.");
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
                PreparedStatement pstmt = c.c.prepareStatement(
                    "UPDATE customer SET roomno = ?, name = ?, checkin = ?, deposit = ? WHERE number = ?"
                );
                pstmt.setString(1, room);
                pstmt.setString(2, name);
                pstmt.setString(3, checkin);
                pstmt.setString(4, deposit);
                pstmt.setString(5, number);

                pstmt.executeUpdate();
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