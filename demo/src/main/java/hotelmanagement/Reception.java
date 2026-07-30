package hotelmanagement;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.PreparedStatement;

public class Reception extends JFrame {
    JButton submit, room, department, employee, customerin, managerin, chekout, update, updaterm, pickup, search,
            logout;

    public Reception() {
        submit = new JButton("New Customer forum");
        submit.setBounds(10, 30, 200, 30);
        submit.setFont(new Font("tahoma", Font.PLAIN, 16));
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

        room = new JButton("Rooms");
        room.setBounds(10, 70, 200, 30);
        room.setFont(new Font("tahoma", Font.PLAIN, 16));
        room.setFocusPainted(false);
        room.setBackground(new Color(0, 0, 0, 200));
        room.setForeground(Color.WHITE);
        room.setBorder(BorderFactory.createLineBorder(Color.CYAN, 2, true));
        room.setCursor(new Cursor(Cursor.HAND_CURSOR));
        // action listener will be attached after full initialization to avoid 'this'
        // escaping during construction

        // Hover effect
        room.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent evt) {
                room.setBackground(new Color(0, 120, 215));
                room.setForeground(Color.WHITE);
            }

            public void mouseExited(MouseEvent evt) {
                room.setBackground(new Color(0, 0, 0, 200));
                room.setForeground(Color.WHITE);
            }
        });
        add(room);

        department = new JButton("Department");
        department.setBounds(10, 110, 200, 30);
        department.setFont(new Font("tahoma", Font.PLAIN, 16));
        department.setFocusPainted(false);
        department.setBackground(new Color(0, 0, 0, 200));
        department.setForeground(Color.WHITE);
        department.setBorder(BorderFactory.createLineBorder(Color.CYAN, 2, true));
        department.setCursor(new Cursor(Cursor.HAND_CURSOR));
        // action listener will be attached after full initialization to avoid 'this'
        // escaping during construction

        // Hover effect
        department.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent evt) {
                department.setBackground(new Color(0, 120, 215));
                department.setForeground(Color.WHITE);
            }

            public void mouseExited(MouseEvent evt) {
                department.setBackground(new Color(0, 0, 0, 200));
                department.setForeground(Color.WHITE);
            }
        });
        add(department);

        employee = new JButton("Employee");
        employee.setBounds(10, 150, 200, 30);
        employee.setFont(new Font("tahoma", Font.PLAIN, 16));
        employee.setFocusPainted(false);
        employee.setBackground(new Color(0, 0, 0, 200));
        employee.setForeground(Color.WHITE);
        employee.setBorder(BorderFactory.createLineBorder(Color.CYAN, 2, true));
        employee.setCursor(new Cursor(Cursor.HAND_CURSOR));
        // action listener will be attached after full initialization to avoid 'this'
        // escaping during construction

        // Hover effect
        employee.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent evt) {
                employee.setBackground(new Color(0, 120, 215));
                employee.setForeground(Color.WHITE);
            }

            public void mouseExited(MouseEvent evt) {
                employee.setBackground(new Color(0, 0, 0, 200));
                employee.setForeground(Color.WHITE);
            }
        });
        add(employee);

        customerin = new JButton("Customer info");
        customerin.setBounds(10, 190, 200, 30);
        customerin.setFont(new Font("tahoma", Font.PLAIN, 16));
        customerin.setFocusPainted(false);
        customerin.setBackground(new Color(0, 0, 0, 200));
        customerin.setForeground(Color.WHITE);
        customerin.setBorder(BorderFactory.createLineBorder(Color.CYAN, 2, true));
        customerin.setCursor(new Cursor(Cursor.HAND_CURSOR));
        // action listener will be attached after full initialization to avoid 'this'
        // escaping during construction

        // Hover effect
        customerin.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent evt) {
                customerin.setBackground(new Color(0, 120, 215));
                customerin.setForeground(Color.WHITE);
            }

            public void mouseExited(MouseEvent evt) {
                customerin.setBackground(new Color(0, 0, 0, 200));
                customerin.setForeground(Color.WHITE);
            }
        });
        add(customerin);

        room = new JButton("Rooms");
        room.setBounds(10, 230, 200, 30);
        room.setFont(new Font("tahoma", Font.PLAIN, 16));
        room.setFocusPainted(false);
        room.setBackground(new Color(0, 0, 0, 200));
        room.setForeground(Color.WHITE);
        room.setBorder(BorderFactory.createLineBorder(Color.CYAN, 2, true));
        room.setCursor(new Cursor(Cursor.HAND_CURSOR));
        // action listener will be attached after full initialization to avoid 'this'
        // escaping during construction

        // Hover effect
        room.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent evt) {
                room.setBackground(new Color(0, 120, 215));
                room.setForeground(Color.WHITE);
            }

            public void mouseExited(MouseEvent evt) {
                room.setBackground(new Color(0, 0, 0, 200));
                room.setForeground(Color.WHITE);
            }
        });
        add(room);
        room = new JButton("Rooms");
        room.setBounds(10, 270, 200, 30);
        room.setFont(new Font("tahoma", Font.PLAIN, 16));
        room.setFocusPainted(false);
        room.setBackground(new Color(0, 0, 0, 200));
        room.setForeground(Color.WHITE);
        room.setBorder(BorderFactory.createLineBorder(Color.CYAN, 2, true));
        room.setCursor(new Cursor(Cursor.HAND_CURSOR));
        // action listener will be attached after full initialization to avoid 'this'
        // escaping during construction

        // Hover effect
        room.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent evt) {
                room.setBackground(new Color(0, 120, 215));
                room.setForeground(Color.WHITE);
            }

            public void mouseExited(MouseEvent evt) {
                room.setBackground(new Color(0, 0, 0, 200));
                room.setForeground(Color.WHITE);
            }
        });
        add(room);
        room = new JButton("Rooms");
        room.setBounds(10, 310, 200, 30);
        room.setFont(new Font("tahoma", Font.PLAIN, 16));
        room.setFocusPainted(false);
        room.setBackground(new Color(0, 0, 0, 200));
        room.setForeground(Color.WHITE);
        room.setBorder(BorderFactory.createLineBorder(Color.CYAN, 2, true));
        room.setCursor(new Cursor(Cursor.HAND_CURSOR));
        // action listener will be attached after full initialization to avoid 'this'
        // escaping during construction

        // Hover effect
        room.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent evt) {
                room.setBackground(new Color(0, 120, 215));
                room.setForeground(Color.WHITE);
            }

            public void mouseExited(MouseEvent evt) {
                room.setBackground(new Color(0, 0, 0, 200));
                room.setForeground(Color.WHITE);
            }
        });
        add(room);
        room = new JButton("Rooms");
        room.setBounds(10, 350, 200, 30);
        room.setFont(new Font("tahoma", Font.PLAIN, 16));
        room.setFocusPainted(false);
        room.setBackground(new Color(0, 0, 0, 200));
        room.setForeground(Color.WHITE);
        room.setBorder(BorderFactory.createLineBorder(Color.CYAN, 2, true));
        room.setCursor(new Cursor(Cursor.HAND_CURSOR));
        // action listener will be attached after full initialization to avoid 'this'
        // escaping during construction

        // Hover effect
        room.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent evt) {
                room.setBackground(new Color(0, 120, 215));
                room.setForeground(Color.WHITE);
            }

            public void mouseExited(MouseEvent evt) {
                room.setBackground(new Color(0, 0, 0, 200));
                room.setForeground(Color.WHITE);
            }
        });
        add(room);
        room = new JButton("Rooms");
        room.setBounds(10, 390, 200, 30);
        room.setFont(new Font("tahoma", Font.PLAIN, 16));
        room.setFocusPainted(false);
        room.setBackground(new Color(0, 0, 0, 200));
        room.setForeground(Color.WHITE);
        room.setBorder(BorderFactory.createLineBorder(Color.CYAN, 2, true));
        room.setCursor(new Cursor(Cursor.HAND_CURSOR));
        // action listener will be attached after full initialization to avoid 'this'
        // escaping during construction

        // Hover effect
        room.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent evt) {
                room.setBackground(new Color(0, 120, 215));
                room.setForeground(Color.WHITE);
            }

            public void mouseExited(MouseEvent evt) {
                room.setBackground(new Color(0, 0, 0, 200));
                room.setForeground(Color.WHITE);
            }
        });
        add(room);
        room = new JButton("Rooms");
        room.setBounds(10, 430, 200, 30);
        room.setFont(new Font("tahoma", Font.PLAIN, 16));
        room.setFocusPainted(false);
        room.setBackground(new Color(0, 0, 0, 200));
        room.setForeground(Color.WHITE);
        room.setBorder(BorderFactory.createLineBorder(Color.CYAN, 2, true));
        room.setCursor(new Cursor(Cursor.HAND_CURSOR));
        // action listener will be attached after full initialization to avoid 'this'
        // escaping during construction

        // Hover effect
        room.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent evt) {
                room.setBackground(new Color(0, 120, 215));
                room.setForeground(Color.WHITE);
            }

            public void mouseExited(MouseEvent evt) {
                room.setBackground(new Color(0, 0, 0, 200));
                room.setForeground(Color.WHITE);
            }
        });
        add(room);
        room = new JButton("Rooms");
        room.setBounds(10, 470, 200, 30);
        room.setFont(new Font("tahoma", Font.PLAIN, 16));
        room.setFocusPainted(false);
        room.setBackground(new Color(0, 0, 0, 200));
        room.setForeground(Color.WHITE);
        room.setBorder(BorderFactory.createLineBorder(Color.CYAN, 2, true));
        room.setCursor(new Cursor(Cursor.HAND_CURSOR));
        // action listener will be attached after full initialization to avoid 'this'
        // escaping during construction

        // Hover effect
        room.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent evt) {
                room.setBackground(new Color(0, 120, 215));
                room.setForeground(Color.WHITE);
            }

            public void mouseExited(MouseEvent evt) {
                room.setBackground(new Color(0, 0, 0, 200));
                room.setForeground(Color.WHITE);
            }
        });
        add(room);

        setLayout(null);
        setBounds(300, 100, 850, 560);
        getContentPane().setBackground(Color.white);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setVisible(true);
    }

    public static void main(String[] args) {
        new Reception();
    }
}
