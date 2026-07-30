package hotelmanagement;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Reception extends JFrame {
    JButton submit, room, department, employee, customerin, managerin, chekout, update, updaterm, pickup, search,
            logout;

    public Reception() {
        // Initialize all buttons with their corresponding labels and vertical Y offsets
        submit     = createStyledButton("New Customer Form", 30);
        room       = createStyledButton("Rooms", 70);
        department = createStyledButton("Department", 110);
        employee   = createStyledButton("Employee", 150);
        customerin = createStyledButton("Customer Info", 190);
        managerin  = createStyledButton("Manager Info", 230);
        chekout    = createStyledButton("Check Out", 270);
        update     = createStyledButton("Update Status", 310);
        updaterm   = createStyledButton("Update Room Status", 350);
        pickup     = createStyledButton("Pick up Service", 390);
        search     = createStyledButton("Search Room", 430);
        logout     = createStyledButton("Logout", 470);

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
        // Image i2 = i1.getImage().getScaledInstance(500, 580, Image.SCALE_DEFAULT);
        // ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i1);
        image.setBounds(250, 30, 550, 470);
        add(image);

        setLayout(null);
        setBounds(300, 100, 850, 560);
        getContentPane().setBackground(Color.WHITE);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setVisible(true);
    }

    /**
     * Helper method to construct styled JButtons with hover effects to reduce redundant code.
     */
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
}