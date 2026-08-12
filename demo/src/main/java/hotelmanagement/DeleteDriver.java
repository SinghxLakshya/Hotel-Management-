package hotelmanagement;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DeleteDriver extends JFrame implements ActionListener {

    Choice cDriverName;
    JLabel lblAge, lblGender, lblVehicleNo, lblModel, lblAvailability;
    JButton deleteBtn, fetchBtn, backBtn;

    public DeleteDriver() {

        JLabel heading = new JLabel("DELETE DRIVER");
        heading.setFont(new Font("Serif", Font.BOLD, 20));
        heading.setBounds(140, 20, 220, 30);
        add(heading);

        // Select Driver Dropdown
        JLabel lblName = new JLabel("Select Driver");
        lblName.setBounds(40, 70, 120, 25);
        lblName.setFont(new Font("Tahoma", Font.PLAIN, 16));
        add(lblName);

        cDriverName = new Choice();
        cDriverName.setBounds(180, 70, 180, 25);
        add(cDriverName);

        // Load drivers from database
        loadDriverNames();

        // Age
        JLabel ageLabel = new JLabel("Age:");
        ageLabel.setBounds(40, 110, 120, 25);
        ageLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
        add(ageLabel);

        lblAge = new JLabel("");
        lblAge.setBounds(180, 110, 180, 25);
        lblAge.setFont(new Font("Tahoma", Font.BOLD, 14));
        add(lblAge);

        // Gender
        JLabel genderLabel = new JLabel("Gender:");
        genderLabel.setBounds(40, 150, 120, 25);
        genderLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
        add(genderLabel);

        lblGender = new JLabel("");
        lblGender.setBounds(180, 150, 180, 25);
        lblGender.setFont(new Font("Tahoma", Font.BOLD, 14));
        add(lblGender);

        // Vehicle Number
        JLabel vehLabel = new JLabel("Vehicle No:");
        vehLabel.setBounds(40, 190, 120, 25);
        vehLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
        add(vehLabel);

        lblVehicleNo = new JLabel("");
        lblVehicleNo.setBounds(180, 190, 180, 25);
        lblVehicleNo.setFont(new Font("Tahoma", Font.BOLD, 14));
        add(lblVehicleNo);

        // Car Model
        JLabel modelLabel = new JLabel("Car Model:");
        modelLabel.setBounds(40, 230, 120, 25);
        modelLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
        add(modelLabel);

        lblModel = new JLabel("");
        lblModel.setBounds(180, 230, 180, 25);
        lblModel.setFont(new Font("Tahoma", Font.BOLD, 14));
        add(lblModel);

        // Availability
        JLabel avlLabel = new JLabel("Availability:");
        avlLabel.setBounds(40, 270, 120, 25);
        avlLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
        add(avlLabel);

        lblAvailability = new JLabel("");
        lblAvailability.setBounds(180, 270, 180, 25);
        lblAvailability.setFont(new Font("Tahoma", Font.BOLD, 14));
        add(lblAvailability);

        // Action Buttons
        fetchBtn = createStyledButton("Fetch Details", 40, 320, 120, 30);
        fetchBtn.addActionListener(this);
        add(fetchBtn);

        deleteBtn = createStyledButton("Delete", 170, 320, 100, 30);
        deleteBtn.addActionListener(this);
        add(deleteBtn);

        backBtn = createStyledButton("Back", 280, 320, 100, 30);
        backBtn.addActionListener(this);
        add(backBtn);

        // Side Image
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/eleven.jpg"));
        Image i2 = i1.getImage().getScaledInstance(320, 300, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(400, 40, 320, 300);
        add(image);

        // Initial fetch
        fetchDriverDetails();

        setLayout(null);
        setBounds(300, 100, 760, 420);
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
                lblAge.setText(rs.getString(2));
                lblGender.setText(rs.getString(3));
                lblVehicleNo.setText(rs.getString(4));
                lblModel.setText(rs.getString(5));
                lblAvailability.setText(rs.getString(6));
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
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == fetchBtn) {
            fetchDriverDetails();
        } else if (ae.getSource() == deleteBtn) {
            String name = cDriverName.getSelectedItem();
            if (name == null || name.isEmpty()) {
                JOptionPane.showMessageDialog(this, "No driver selected.", "Warning", JOptionPane.WARNING_MESSAGE);
                return;
            }

            int confirm = JOptionPane.showConfirmDialog(
                    this,
                    "Are you sure you want to delete driver: " + name + "?",
                    "Confirm Deletion",
                    JOptionPane.YES_NO_OPTION
            );

            if (confirm == JOptionPane.YES_OPTION) {
                try {
                    Conn conn = new Conn();
                    String query = "DELETE FROM Drivers WHERE name = ?";
                    PreparedStatement ps = conn.c.prepareStatement(query);
                    ps.setString(1, name);
                    ps.executeUpdate();

                    JOptionPane.showMessageDialog(this, "Driver record deleted successfully.");
                    setVisible(false);
                    dispose();
                } catch (Exception e) {
                    e.printStackTrace();
                    JOptionPane.showMessageDialog(this, "Database Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        } else if (ae.getSource() == backBtn) {
            setVisible(false);
            dispose();
        }
    }

    public static void main(String[] args) {
        new DeleteDriver();
    }
}