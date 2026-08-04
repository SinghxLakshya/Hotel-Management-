package hotelmanagement;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;
import net.proteanit.sql.*;

public class Allrooms extends JFrame {
JTable t1;

    public Allrooms() {
        
        setLayout(null);
        getContentPane().setBackground(Color.WHITE);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/eight.jpg"));
        Image i2 = i1.getImage().getScaledInstance(550, 550, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(490, 0, 580, 580);
        add(image);

        // 1. Create the table
        t1 = new JTable();

        // 2. Put table inside JScrollPane (This makes table headers visible)
        JScrollPane jsp = new JScrollPane(t1);
        jsp.setBounds(0, 14, 505, 600); // Set bounds on ScrollPane, not table
        add(jsp);

        try {
            Conn con = new Conn();
            ResultSet rs = con.s.executeQuery("select * from addrooms");
            t1.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (Exception e) {
            e.printStackTrace();
        }

        setBounds(150, 80, 1065, 600);
        setVisible(true);
    }

    public static void main(String[] args) {
        new Allrooms();
    }
}