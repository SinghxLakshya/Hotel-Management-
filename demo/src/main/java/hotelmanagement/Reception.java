package hotelmanagement;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Reception extends JFrame implements ActionListener {
    JButton submit, room, department, employee, customerin, managerin, chekout, update, updaterm, pickup, search, logout;

    public Reception() {
        // Initialize all buttons with their corresponding labels and vertical Y offsets
        submit = createStyledButton("New Customer Form", 30);
        room = createStyledButton("Rooms", 70);
        department = createStyledButton("Department", 110);
        employee = createStyledButton("Employee", 150);
        customerin = createStyledButton("Customer Info", 190);
        managerin = createStyledButton("Manager Info", 230);
        chekout = createStyledButton("Check Out", 270);
        update = createStyledButton("Update Status", 310);
        updaterm = createStyledButton("Update Room Status", 350);
        pickup = createStyledButton("Pick up Service", 390);
        search = createStyledButton("Search Room", 430);
        logout = createStyledButton("Logout", 470);

        // Add action listeners to ALL buttons
        submit.addActionListener(this);
        room.addActionListener(this);
        department.addActionListener(this);
        employee.addActionListener(this);
        customerin.addActionListener(this);
        managerin.addActionListener(this);
        chekout.addActionListener(this);
        update.addActionListener(this);
        updaterm.addActionListener(this);
        pickup.addActionListener(this);
        search.addActionListener(this);
        logout.addActionListener(this);

        // Add buttons to frame
        add(submit);
        add(room);
        add(department);
        add(employee);
        add(customerin);
        add(managerin);
        add(chekout);
        add(update);
        add(updaterm);
        add(pickup);
        add(search);
        add(logout);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/fourth.jpg"));
        JLabel image = new JLabel(i1);
        image.setBounds(250, 30, 550, 470);
        add(image);

        setLayout(null);
        setBounds(300, 100, 850, 560);
        getContentPane().setBackground(Color.WHITE);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setVisible(true);
    }

    private JButton createStyledButton(String text, int yPosition) {
        JButton button = new JButton(text);
        button.setBounds(10, yPosition, 200, 30);
        button.setFont(new Font("Tahoma", Font.PLAIN, 16));
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

    public static void main(String[] args) {
        new Reception();
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == submit) {
            setVisible(false);
            new Customer();
        } else if (ae.getSource() == room) {
            setVisible(false);
            new Allrooms();
        } else if (ae.getSource() == department) {
            setVisible(false);
            new Dept();
        } else if (ae.getSource() == employee) {
            setVisible(false);
            new Allemp();
        } else if (ae.getSource() == customerin) {
            setVisible(false);
            new Customerinfo();
        } else if (ae.getSource() == managerin) {
            setVisible(false);
            new Manager();
        } else if (ae.getSource() == chekout) {
            setVisible(false);
            
        } else if (ae.getSource() == update) {
            setVisible(false);
            
        } else if (ae.getSource() == updaterm) {
            setVisible(false);
            
        } else if (ae.getSource() == pickup) {
            setVisible(false);
            new Pickup();
            
        } else if (ae.getSource() == search) {
            setVisible(false);
            new Search();
        } else if (ae.getSource() == logout) {
            setVisible(false);
            new Login();
        }
    }
}