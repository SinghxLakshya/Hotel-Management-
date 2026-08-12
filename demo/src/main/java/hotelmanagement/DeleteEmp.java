package hotelmanagement;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;

public class DeleteEmp extends JFrame implements ActionListener {

    Choice cEmpName;
    JLabel lblJob, lblPhone, lblEmail, lblSalary;
    JButton deleteBtn, backBtn, checkBtn;

    public DeleteEmp() {

        JLabel heading = new JLabel("DELETE EMPLOYEE");
        heading.setFont(new Font("Tahoma", Font.BOLD, 18));
        heading.setBounds(120, 20, 220, 30);
        add(heading);

        // Select Employee Label & Choice
        JLabel lblName = new JLabel("Select Employee");
        lblName.setBounds(40, 70, 130, 25);
        lblName.setFont(new Font("Tahoma", Font.PLAIN, 15));
        add(lblName);

        cEmpName = new Choice();
        cEmpName.setBounds(180, 70, 180, 25);
        add(cEmpName);

        // Populates employee names from database
        try {
            Conn c = new Conn();
            ResultSet rs = c.s.executeQuery("SELECT * FROM employee1");
            while (rs.next()) {
                cEmpName.add(rs.getString("name"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Job
        JLabel jobLabel = new JLabel("Job Role:");
        jobLabel.setBounds(40, 110, 120, 25);
        jobLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
        add(jobLabel);

        lblJob = new JLabel("");
        lblJob.setBounds(180, 110, 180, 25);
        lblJob.setFont(new Font("Tahoma", Font.BOLD, 14));
        add(lblJob);

        // Phone
        JLabel phoneLabel = new JLabel("Phone:");
        phoneLabel.setBounds(40, 150, 120, 25);
        phoneLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
        add(phoneLabel);

        lblPhone = new JLabel("");
        lblPhone.setBounds(180, 150, 180, 25);
        lblPhone.setFont(new Font("Tahoma", Font.BOLD, 14));
        add(lblPhone);

        // Email
        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setBounds(40, 190, 120, 25);
        emailLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
        add(emailLabel);

        lblEmail = new JLabel("");
        lblEmail.setBounds(180, 190, 180, 25);
        lblEmail.setFont(new Font("Tahoma", Font.BOLD, 14));
        add(lblEmail);

        // Salary
        JLabel salaryLabel = new JLabel("Salary:");
        salaryLabel.setBounds(40, 230, 120, 25);
        salaryLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
        add(salaryLabel);

        lblSalary = new JLabel("");
        lblSalary.setBounds(180, 230, 180, 25);
        lblSalary.setFont(new Font("Tahoma", Font.BOLD, 14));
        add(lblSalary);

        // Check/Fetch Details Button
        checkBtn = createStyledButton("Fetch Details", 40, 280, 130, 30);
        checkBtn.addActionListener(this);
        add(checkBtn);

        // Delete Button
        deleteBtn = createStyledButton("Delete", 180, 280, 100, 30);
        deleteBtn.addActionListener(this);
        add(deleteBtn);

        // Back Button
        backBtn = createStyledButton("Back", 290, 280, 100, 30);
        backBtn.addActionListener(this);
        add(backBtn);

        // Side Image
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/tenth.jpg"));
        Image i2 = i1.getImage().getScaledInstance(300, 350, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(410, 30, 300, 280);
        add(image);

        // Initial fetch for the first item if available
        fetchEmployeeDetails();

        setLayout(null);
        setBounds(350, 150, 750, 380);
        getContentPane().setBackground(Color.WHITE);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
    }

    private void fetchEmployeeDetails() {
        String selectedName = cEmpName.getSelectedItem();
        if (selectedName == null) return;

        try {
            Conn c = new Conn();
            String query = "SELECT * FROM employee1 WHERE name = '" + selectedName + "'";
            ResultSet rs = c.s.executeQuery(query);
            if (rs.next()) {
                lblJob.setText(rs.getString("job"));
                lblPhone.setText(rs.getString("phone"));
                lblEmail.setText(rs.getString("email"));
                lblSalary.setText(rs.getString("salary"));
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
        if (ae.getSource() == checkBtn) {
            fetchEmployeeDetails();
        } else if (ae.getSource() == deleteBtn) {
            String name = cEmpName.getSelectedItem();
            if (name == null || name.isEmpty()) {
                JOptionPane.showMessageDialog(null, "No employee selected");
                return;
            }

            int confirm = JOptionPane.showConfirmDialog(
                    null,
                    "Are you sure you want to delete employee: " + name + "?",
                    "Confirm Delete",
                    JOptionPane.YES_NO_OPTION
            );

            if (confirm == JOptionPane.YES_OPTION) {
                try {
                    Conn c = new Conn();
                    String query = "DELETE FROM employee1 WHERE name = '" + name + "'";
                    c.s.executeUpdate(query);

                    JOptionPane.showMessageDialog(null, "Employee record deleted successfully");
                    setVisible(false);
                    dispose();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } else if (ae.getSource() == backBtn) {
            setVisible(false);
            dispose();
        }
    }

    public static void main(String[] args) {
        new DeleteEmp();
    }
}