package hotelmanagement;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import net.proteanit.sql.*;

public class Search extends JFrame implements ActionListener {
    JTable t1;
    JButton cncl, submit;
    JComboBox<String> bedtype;
    JCheckBox Available;

    public Search() {

        setLayout(null);
        getContentPane().setBackground(Color.WHITE);

        JLabel heading = new JLabel("Search Rooms");
        heading.setFont(new Font("Serif", Font.BOLD, 20));
        heading.setBounds(470, 0, 220, 30);
        add(heading);

        JLabel bed = new JLabel("Bedtype");
        bed.setBounds(79, 30, 100, 25);
        add(bed);

        bedtype = new JComboBox<>(new String[] { "All", "Single bed", "Double bed", "two single beds", "Dormitory for kids" });
        bedtype.setBounds(150, 30, 150, 25);
        bedtype.setBackground(Color.WHITE);
        add(bedtype);

        Available = new JCheckBox("Display Available Rooms");
        Available.setBounds(650, 30, 250, 25);
        Available.setBackground(Color.WHITE);
        add(Available);

        // 1. Create the table
        t1 = new JTable();

        // 2. Put table inside JScrollPane
        JScrollPane jsp = new JScrollPane(t1);
        jsp.setBounds(0, 65, 1065, 400);
        jsp.setBorder(BorderFactory.createEmptyBorder());
        jsp.getViewport().setBackground(Color.WHITE);
        jsp.setBackground(Color.WHITE);
        add(jsp);

        // Submit and Cancel buttons
        submit = new JButton("Find rooms");
        submit.setBounds(200, 500, 150, 30);
        submit.setFont(new Font("Arial", Font.BOLD, 16));
        submit.setFocusPainted(false);
        submit.setBackground(new Color(0, 0, 0, 200));
        submit.setForeground(Color.WHITE);
        submit.setBorder(BorderFactory.createLineBorder(Color.CYAN, 2, true));
        submit.setCursor(new Cursor(Cursor.HAND_CURSOR));

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

        // Populate initial table data
        loadData();

        setBounds(150, 80, 1065, 600);
        setVisible(true);
    }

    private void loadData() {
        try {
            Conn con = new Conn();
            ResultSet rs = con.s.executeQuery("SELECT * FROM addrooms");
            t1.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new Search();
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == submit) {
            try {
                String selectedBed = (String) bedtype.getSelectedItem();
                boolean isAvailableChecked = Available.isSelected();

                StringBuilder query = new StringBuilder("SELECT * FROM addrooms WHERE 1=1");

                if (selectedBed != null && !selectedBed.equalsIgnoreCase("All")) {
                    query.append(" AND bedtype = ?");
                }

                if (isAvailableChecked) {
                    query.append(" AND availablity = 'Available'");
                }

                Conn c = new Conn();
                PreparedStatement pstmt = c.c.prepareStatement(query.toString());

                if (selectedBed != null && !selectedBed.equalsIgnoreCase("All")) {
                    pstmt.setString(1, selectedBed);
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