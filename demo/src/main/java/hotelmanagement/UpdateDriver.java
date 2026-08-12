package hotelmanagement;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UpdateDriver extends JFrame implements ActionListener, ItemListener {

    Choice cDriverName;
    JTextField tfprice, crmField, vehiclenoField;
    JComboBox<String> avlcjb, stscjb;
    JButton updateBtn, backBtn;

    public UpdateDriver() {

        JLabel heading = new JLabel("UPDATE DRIVER DETAILS");
        heading.setFont(new Font("Serif", Font.BOLD, 20));
        heading.setBounds(330, 10, 270, 30);
        add(heading);

        // Driver Selection
        JLabel lblName = new JLabel("Select Driver");
        lblName.setFont(new Font("Tahoma", Font.PLAIN, 18));
        lblName.setBounds(70, 60, 100, 25);
        add(lblName);

        cDriverName = new Choice();
        cDriverName.setBounds(200, 60, 150, 25);
        add(cDriverName);

        // Load drivers into Choice
        loadDriverNames();
        
        // Listen for selection changes automatically
        cDriverName.addItemListener(this);

        // Age
        JLabel price = new JLabel("Age");
        price.setFont(new Font("Tahoma", Font.PLAIN, 18));
        price.setBounds(70, 110, 200, 25);
        add(price);

        tfprice = new JTextField();
        tfprice.setBounds(200, 110, 150, 30);
        tfprice.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent e) {
                char c = e.getKeyChar();
                if (!Character.isDigit(c)
                        && c != java.awt.event.KeyEvent.VK_BACK_SPACE
                        && c != java.awt.event.KeyEvent.VK_DELETE) {
                    e.consume();
                }
            }
        });
        add(tfprice);

        // Gender
        JLabel clean = new JLabel("Gender");
        clean.setFont(new Font("Tahoma", Font.PLAIN, 18));
        clean.setBounds(70, 160, 200, 25);
        add(clean);

        String[] sts = { "Male", "Female" };
        stscjb = new JComboBox<>(sts);
        stscjb.setBounds(200, 160, 150, 30);
        stscjb.setBackground(Color.WHITE);
        add(stscjb);

        // Vehicle Number
        JLabel bed = new JLabel("Vehicle NO.");
        bed.setFont(new Font("Tahoma", Font.PLAIN, 18));
        bed.setBounds(70, 210, 200, 25);
        add(bed);

        vehiclenoField = new JTextField();
        vehiclenoField.setBounds(200, 210, 150, 30);
        vehiclenoField.setBackground(Color.WHITE);
        add(vehiclenoField);

        // Car Model
        JLabel rmsts = new JLabel("Car Model");
        rmsts.setFont(new Font("Tahoma", Font.PLAIN, 18));
        rmsts.setBounds(70, 260, 200, 25);
        add(rmsts);

        crmField = new JTextField();
        crmField.setBounds(200, 260, 150, 30);
        crmField.setBackground(Color.WHITE);
        add(crmField);

        // Availability Status Dropdown
        JLabel avl = new JLabel("Availability");
        avl.setFont(new Font("Tahoma", Font.PLAIN, 18));
        avl.setBounds(70, 310, 200, 25);
        add(avl);

        String[] str = { "Available", "Not available" };
        avlcjb = new JComboBox<>(str);
        avlcjb.setBounds(200, 310, 150, 30);
        avlcjb.setBackground(Color.WHITE);
        add(avlcjb);

        // Action Buttons
        updateBtn = createStyledButton("Update Driver", 120, 390, 150, 35);
        updateBtn.addActionListener(this);
        add(updateBtn);

        backBtn = createStyledButton("Back", 290, 390, 120, 35);
        backBtn.addActionListener(this);
        add(backBtn);

        // Image
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/eleven.jpg"));
        Image i2 = i1.getImage().getScaledInstance(380, 330, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(430, 60, 380, 330);
        add(image);

        // Auto-fetch data for the initial record on window launch
        fetchDriverDetails();

        setLayout(null);
        setBounds(300, 100, 850, 500);
        getContentPane().setBackground(Color.WHITE);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
    }

    private void loadDriverNames() {
        try {
            Conn c = new Conn();
            String query = "SELECT name FROM Drivers";
            PreparedStatement ps = c.c.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            cDriverName.removeAll();
            while (rs.next()) {
                cDriverName.add(rs.getString("name"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void fetchDriverDetails() {
        String name = cDriverName.getSelectedItem();
        if (name == null || name.isEmpty()) return;

        try {
            Conn c = new Conn();
            String query = "SELECT * FROM Drivers WHERE name = ?";
            PreparedStatement ps = c.c.prepareStatement(query);
            ps.setString(1, name);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                tfprice.setText(rs.getString(2));       // Age
                stscjb.setSelectedItem(rs.getString(3)); // Gender
                vehiclenoField.setText(rs.getString(4)); // Vehicle No
                crmField.setText(rs.getString(5));       // Car Model
                avlcjb.setSelectedItem(rs.getString(6)); // Availability
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private JButton createStyledButton(String text, int x, int y, int width, int height) {
        JButton button = new JButton(text);
        button.setBounds(x, y, width, height);
        button.setFont(new Font("Arial", Font.BOLD, 14));
        button.setFocusPainted(false);
        button.setBackground(new Color(0, 0, 0, 200));
        button.setForeground(Color.WHITE);
        button.setBorder(BorderFactory.createLineBorder(Color.CYAN, 2, true));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));

        button.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent evt) {
                button.setBackground(new Color(0, 120, 215));
            }

            public void mouseExited(MouseEvent evt) {
                button.setBackground(new Color(0, 0, 0, 200));
            }
        });

        return button;
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        if (e.getSource() == cDriverName) {
            fetchDriverDetails();
        }
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == updateBtn) {
            String name = cDriverName.getSelectedItem();
            String age = tfprice.getText().trim();
            String gender = (String) stscjb.getSelectedItem();
            String vehicleNo = vehiclenoField.getText().trim();
            String model = crmField.getText().trim();
            String availability = (String) avlcjb.getSelectedItem();

            if (name == null || name.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please select a driver.", "Validation Error", JOptionPane.WARNING_MESSAGE);
                return;
            }
            if (age.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Age cannot be empty.", "Validation Error", JOptionPane.WARNING_MESSAGE);
                return;
            }
            if (vehicleNo.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Vehicle number cannot be empty.", "Validation Error", JOptionPane.WARNING_MESSAGE);
                return;
            }
            if (model.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Car Model cannot be empty.", "Validation Error", JOptionPane.WARNING_MESSAGE);
                return;
            }

            try {
                Conn conn = new Conn();
                String query = "UPDATE Drivers SET age = ?, gender = ?, vehicle_no = ?, car = ?, available = ? WHERE name = ?";
                PreparedStatement ps = conn.c.prepareStatement(query);
                ps.setString(1, age);
                ps.setString(2, gender);
                ps.setString(3, vehicleNo);
                ps.setString(4, model);
                ps.setString(5, availability);
                ps.setString(6, name);

                ps.executeUpdate();

                JOptionPane.showMessageDialog(this, "Driver details and availability status updated successfully.");
                setVisible(false);
                dispose();
            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(this, "Database Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else if (ae.getSource() == backBtn) {
            setVisible(false);
            dispose();
        }
    }

    public static void main(String[] args) {
        new UpdateDriver();
    }
}