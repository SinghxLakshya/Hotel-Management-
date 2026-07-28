package hotelmanagement;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;

public class Hotelmanagement extends JFrame implements ActionListener {

    JButton clickButton;
    Timer timer; // Class-level reference to stop it when transitioning

    public Hotelmanagement() {
        setSize(1366, 695);
        setLayout(null);
        setLocationRelativeTo(null); // Centers the frame on the screen
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Exit app on window close

        // --- Background Image ---
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/splash4.png"));
        JLabel image = new JLabel(i1);
        image.setBounds(0, 0, 1366, 695);
        add(image);

        // --- Heading Text ---
        JLabel text = new JLabel("Hotel Management System");
        text.setBounds(10, 100, 500, 90);
        text.setForeground(Color.WHITE);
        text.setFont(new Font("serif", Font.PLAIN, 40));
        image.add(text);

        // --- Blinking Animation Timer ---
        timer = new Timer(500, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                text.setVisible(!text.isVisible());
            }
        });
        timer.start();

        // --- Continue Button ---
        clickButton = new JButton("CLICK HERE TO CONTINUE");
        clickButton.setBounds(455, 580, 320, 40);
        clickButton.setFont(new Font("Arial", Font.BOLD, 16));
        clickButton.setFocusPainted(false);
        clickButton.setBackground(new Color(0, 0, 0, 200));
        clickButton.setForeground(Color.WHITE);
        clickButton.setBorder(BorderFactory.createLineBorder(Color.CYAN, 2, true));
        clickButton.setCursor(new Cursor(Cursor.HAND_CURSOR));

        // Hover effect
        clickButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent evt) {
                clickButton.setBackground(new Color(0, 120, 215));
                clickButton.setForeground(Color.WHITE);
            }

            @Override
            public void mouseExited(MouseEvent evt) {
                clickButton.setBackground(new Color(0, 0, 0, 200));
                clickButton.setForeground(Color.WHITE);
            }
        });

        clickButton.addActionListener(this);
        image.add(clickButton);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == clickButton) {
            timer.stop(); // Stop the timer so it doesn't run in memory
            setVisible(false);
            dispose(); // Free system resources
            new Login();
        }
    }

    public static void main(String[] args) {
        new Hotelmanagement();
    }
}