package hotelmanagement;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;
import net.proteanit.sql.*;

public class Customerinfo extends JFrame implements ActionListener {
    JTable t1;
    JButton cncl;

    public Customerinfo() {

        setLayout(null);
        getContentPane().setBackground(Color.WHITE);

        // ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/eight.jpg"));
        // Image i2 = i1.getImage().getScaledInstance(550, 550, Image.SCALE_DEFAULT);
        // ImageIcon i3 = new ImageIcon(i2);
        // JLabel image = new JLabel(i3);
        // image.setBounds(490, 0, 580, 580);
        // add(image);

        // 1. Create the table
        t1 = new JTable();

        // 2. Put table inside JScrollPane (This makes table headers visible)
        JScrollPane jsp = new JScrollPane(t1);
        jsp.setBounds(0, 14, 850, 400); // Set bounds on ScrollPane, not table

        jsp.setBorder(BorderFactory.createEmptyBorder()); // Removes the scrollpane border
        jsp.getViewport().setBackground(Color.WHITE); // Turns the empty space inside white
        jsp.setBackground(Color.WHITE);
        add(jsp);

        cncl = new JButton("Cancel");
        cncl.setBounds(360, 450, 150, 30);
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
        cncl.addActionListener(this);

        try {
            Conn con = new Conn();
            ResultSet rs = con.s.executeQuery("select * from customer");
            t1.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (Exception e) {
            e.printStackTrace();
        }

        setBounds(300, 100, 850, 540);
        setVisible(true);
    }

    public static void main(String[] args) {
        new Customerinfo();
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
       if (ae.getSource()==cncl) {
        setVisible(false);
        new Reception();
       }
    }
}