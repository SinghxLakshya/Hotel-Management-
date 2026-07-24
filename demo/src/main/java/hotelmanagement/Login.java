package hotelmanagement;
import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

public class Login extends JFrame implements ActionListener {
    JTextField tfusername;
    JPasswordField tfpsswrd;

Login() {
        setTitle("Login Page");

        setLayout(new BorderLayout());

        // LEFT SIDE IMAGE
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/second.jpg"));
        if (i1.getImageLoadStatus() != MediaTracker.COMPLETE) {
            System.out.println("⚠️ Image not found!");
        }
        Image i2 = i1.getImage().getScaledInstance(600, 650, Image.SCALE_SMOOTH);
        JLabel leftImage = new JLabel(new ImageIcon(i2));
        add(leftImage, BorderLayout.WEST);

        // LOGIN PANEL
        JPanel loginPanel = new JPanel(new GridBagLayout());
        loginPanel.setBackground(Color.WHITE);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(15, 15, 15, 15);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel heading = new JLabel("Welcome User!");
        heading.setFont(new Font("Segoe UI", Font.BOLD, 26));
        heading.setForeground(new Color(60, 60, 60));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        loginPanel.add(heading, gbc);
        gbc.gridwidth = 1;

        JLabel lblusername = new JLabel("Username:");
        lblusername.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        gbc.gridx = 0;
        gbc.gridy = 1;
        loginPanel.add(lblusername, gbc);
        // code for entering username
        tfusername = new JTextField(18);
        tfusername.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        gbc.gridx = 1;
        gbc.gridy = 1;
        loginPanel.add(tfusername, gbc);

        JLabel lblpsswrd = new JLabel("Password:");
        lblpsswrd.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        gbc.gridx = 0;
        gbc.gridy = 2;
        loginPanel.add(lblpsswrd, gbc);
        // code for entering user's passwrd
        tfpsswrd = new JPasswordField(18);
        tfpsswrd.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        gbc.gridx = 1;
        gbc.gridy = 2;
        loginPanel.add(tfpsswrd, gbc);

//button

        JButton loginBtn = new JButton("Login");
        loginBtn.setBackground(new Color(0, 123, 255));
        loginBtn.setForeground(Color.WHITE);
        loginBtn.setFocusPainted(false);
        loginBtn.setFont(new Font("Segoe UI", Font.BOLD, 16));
        loginBtn.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        loginBtn.addActionListener( this);

        // Rounded button fix
        loginBtn.setUI(new javax.swing.plaf.basic.BasicButtonUI() {
            @Override
            public void paint(Graphics g, JComponent c) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(loginBtn.getBackground());
                g2.fillRoundRect(0, 0, c.getWidth(), c.getHeight(), 20, 20);
                g2.dispose();
                super.paint(g, c);
            }
        });

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        loginPanel.add(loginBtn, gbc);

        add(loginPanel, BorderLayout.CENTER);

        setSize(1000, 650);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

@Override
public void actionPerformed(ActionEvent e) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'actionPerformed'");
} 


public static void main(String[] args) {
    new Login();
}}