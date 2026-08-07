package hotelmanagement;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import net.proteanit.sql.*;

public class Pickup extends JFrame implements ActionListener {
    JTable t1;
    JButton cncl, submit;
    Choice car;
    JCheckBox Available;

    public Pickup() {

        setLayout(null);
        getContentPane().setBackground(Color.WHITE);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel heading = new JLabel("Search Drivers for Pickup");
        heading.setFont(new Font("Serif", Font.BOLD, 20));
        heading.setBounds(450, 0, 250, 30);
        add(heading);

        JLabel bed = new JLabel("Type of Car");
        bed.setBounds(79, 30, 100, 25);
        add(bed);

        car = new Choice();
        car.setBounds(250, 30, 150, 25);
        car.setBackground(Color.WHITE);
        
        // Add "All" option to display all drivers without filtering by car model
        car.add("All");
        
        try {
            Conn c = new Conn();
            ResultSet rs = c.s.executeQuery("SELECT DISTINCT car FROM Drivers");
            while (rs.next()) {
                car.add(rs.getString("car"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        add(car);

        Available = new JCheckBox("Display Available Drivers");
        Available.setBounds(650, 30, 250, 25);
        Available.setBackground(Color.WHITE);
        add(Available);

        // Create table inside JScrollPane
        t1 = new JTable();
        JScrollPane jsp = new JScrollPane(t1);
        jsp.setBounds(0, 65, 1065, 400);
        jsp.setBorder(BorderFactory.createEmptyBorder());
        jsp.getViewport().setBackground(Color.WHITE);
        jsp.setBackground(Color.WHITE);
        add(jsp);

        // Submit and Cancel buttons
        submit = new JButton("Find Drivers");
        submit.setBounds(200, 500, 150, 30);
        submit.setFont(new Font("Arial", Font.BOLD, 16));
        submit.setFocusPainted(false);
        submit.setBackground(new Color(0, 0, 0, 200));
        submit.setForeground(Color.WHITE);
        submit.setBorder(BorderFactory.createLineBorder(Color.CYAN, 2, true));
        submit.setCursor(new Cursor(Cursor.HAND_CURSOR));

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
        submit.addActionListener(this);
        add(submit);

        cncl = new JButton("Cancel");
        cncl.setBounds(650, 500, 150, 30);
        cncl.setFont(new Font("Arial", Font.BOLD, 16));
        cncl.setFocusPainted(false);
        cncl.setBackground(new Color(0, 0, 0, 200));
        cncl.setForeground(Color.WHITE);
        cncl.setBorder(BorderFactory.createLineBorder(Color.CYAN, 2, true));
        cncl.setCursor(new Cursor(Cursor.HAND_CURSOR));

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

        // Initial table load
        loadData();

        setBounds(150, 80, 1065, 600);
        setVisible(true);
    }

    private void loadData() {
        try {
            Conn con = new Conn();
            ResultSet rs = con.s.executeQuery("SELECT * FROM Drivers");
            t1.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new Pickup();
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == submit) {
            try {
                String selectedCar = car.getSelectedItem();
                boolean isAvailableChecked = Available.isSelected();

                StringBuilder query = new StringBuilder("SELECT * FROM Drivers WHERE 1=1");

                if (selectedCar != null && !selectedCar.equalsIgnoreCase("All")) {
                    query.append(" AND car = ?");
                }

                if (isAvailableChecked) {
                    query.append(" AND available = 'Available'");
                }

                Conn c = new Conn();
                PreparedStatement pstmt = c.c.prepareStatement(query.toString());

                if (selectedCar != null && !selectedCar.equalsIgnoreCase("All")) {
                    pstmt.setString(1, selectedCar);
                }

                ResultSet rs = pstmt.executeQuery();
                t1.setModel(DbUtils.resultSetToTableModel(rs));

            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (ae.getSource() == cncl) {
            setVisible(false);
            new Reception();
        }
    }
}