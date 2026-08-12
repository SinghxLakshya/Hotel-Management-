package hotelmanagement;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;

public class UpdateEmp extends JFrame implements ActionListener {

    Choice cEmpName;
    JTextField tage, slryField, phnField, emField;
    JComboBox<String> cjb;
    JRadioButton rbg, female;
    ButtonGroup genderGroup;
    JButton updateBtn, checkBtn, backBtn;

    public UpdateEmp() {

        JLabel heading = new JLabel("UPDATE EMPLOYEE DETAILS");
        heading.setFont(new Font("Tahoma", Font.BOLD, 18));
        heading.setBounds(180, 20, 300, 30);
        add(heading);

        // Employee Selection
        JLabel lblName = new JLabel("Select Employee");
        lblName.setBounds(60, 70, 120, 30);
        lblName.setFont(new Font("Tahoma", Font.PLAIN, 17));
        add(lblName);

        cEmpName = new Choice();
        cEmpName.setBounds(200, 70, 220, 30);
        add(cEmpName);

        // Load Employee Names
        try {
            Conn c = new Conn();
            ResultSet rs = c.s.executeQuery("SELECT * FROM employee1");
            while (rs.next()) {
                cEmpName.add(rs.getString("name"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Fetch Button
        checkBtn = createStyledButton("Fetch Data", 430, 70, 110, 30);
        checkBtn.addActionListener(this);
        add(checkBtn);

        // Age
        JLabel age = new JLabel("Age");
        age.setBounds(60, 120, 120, 30);
        age.setFont(new Font("Tahoma", Font.PLAIN, 17));
        add(age);

        tage = new JTextField();
        tage.setBounds(200, 120, 220, 30);
        tage.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent e) {
                char c = e.getKeyChar();
                if (!Character.isDigit(c)) {
                    e.consume();
                }
            }
        });
        add(tage);

        // Gender
        JLabel gnd = new JLabel("Gender");
        gnd.setBounds(60, 170, 120, 30);
        gnd.setFont(new Font("Tahoma", Font.PLAIN, 17));
        add(gnd);

        rbg = new JRadioButton("Male");
        rbg.setBounds(200, 170, 70, 30);
        rbg.setFont(new Font("Tahoma", Font.PLAIN, 14));
        rbg.setBackground(Color.WHITE);
        add(rbg);

        female = new JRadioButton("Female");
        female.setBounds(290, 170, 80, 30);
        female.setFont(new Font("Tahoma", Font.PLAIN, 14));
        female.setBackground(Color.WHITE);
        add(female);

        genderGroup = new ButtonGroup();
        genderGroup.add(rbg);
        genderGroup.add(female);

        // Job
        JLabel job = new JLabel("Job");
        job.setBounds(60, 220, 120, 30);
        job.setFont(new Font("Tahoma", Font.PLAIN, 17));
        add(job);

        String[] roles = { "Manager", "Front Desk clerks", "Porters", "House Keeping", "Receptionist", "Roomservice",
                "Kitchen staff", "Head-chef" };
        cjb = new JComboBox<>(roles);
        cjb.setBounds(200, 220, 220, 30);
        cjb.setBackground(Color.WHITE);
        add(cjb);

        // Salary
        JLabel slry = new JLabel("Salary");
        slry.setBounds(60, 270, 120, 30);
        slry.setFont(new Font("Tahoma", Font.PLAIN, 17));
        add(slry);

        slryField = new JTextField();
        slryField.setBounds(200, 270, 220, 30);
        slryField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent e) {
                char c = e.getKeyChar();
                if (!Character.isDigit(c)) {
                    e.consume();
                }
            }
        });
        add(slryField);

        // Phone
        JLabel phn = new JLabel("Phone");
        phn.setBounds(60, 320, 120, 30);
        phn.setFont(new Font("Tahoma", Font.PLAIN, 17));
        add(phn);

        phnField = new JTextField();
        phnField.setBounds(200, 320, 220, 30);
        phnField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent e) {
                char c = e.getKeyChar();
                if (!Character.isDigit(c)) {
                    e.consume();
                }
            }
        });
        add(phnField);

        // Email
        JLabel eml = new JLabel("Email");
        eml.setBounds(60, 370, 120, 30);
        eml.setFont(new Font("Tahoma", Font.PLAIN, 17));
        add(eml);

        emField = new JTextField();
        emField.setBounds(200, 370, 220, 30);
        add(emField);

        // Action Buttons
        updateBtn = createStyledButton("Update", 130, 430, 130, 35);
        updateBtn.addActionListener(this);
        add(updateBtn);

        backBtn = createStyledButton("Back", 290, 430, 130, 35);
        backBtn.addActionListener(this);
        add(backBtn);

        // Image
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/tenth.jpg"));
        Image i2 = i1.getImage().getScaledInstance(380, 380, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(440, 110, 380, 320);
        add(image);

        // Initial Data Fetch
        fetchDetails();

        setLayout(null);
        setBounds(300, 100, 860, 530);
        getContentPane().setBackground(Color.WHITE);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
    }

    private void fetchDetails() {
        String name = cEmpName.getSelectedItem();
        if (name == null) return;

        try {
            Conn c = new Conn();
            String query = "SELECT * FROM employee1 WHERE name = '" + name + "'";
            ResultSet rs = c.s.executeQuery(query);

            if (rs.next()) {
                tage.setText(rs.getString("age"));

                String gender = rs.getString("gender");
                if ("Male".equalsIgnoreCase(gender)) {
                    rbg.setSelected(true);
                } else if ("Female".equalsIgnoreCase(gender)) {
                    female.setSelected(true);
                } else {
                    genderGroup.clearSelection();
                }

                cjb.setSelectedItem(rs.getString("job"));
                slryField.setText(rs.getString("salary"));
                phnField.setText(rs.getString("phone"));
                emField.setText(rs.getString("email"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private JButton createStyledButton(String text, int x, int y, int width, int height) {
        JButton button = new JButton(text);
        button.setBounds(x, y, width, height);
        button.setFont(new Font("Arial", Font.BOLD, 15));
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
            fetchDetails();
        } else if (ae.getSource() == updateBtn) {
            String name = cEmpName.getSelectedItem();
            String age = tage.getText();
            String slry = slryField.getText();
            String phn = phnField.getText();
            String eml = emField.getText();

            String gnd = null;
            if (rbg.isSelected()) {
                gnd = "Male";
            } else if (female.isSelected()) {
                gnd = "Female";
            }

            String job = (String) cjb.getSelectedItem();

            if (name == null || name.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Please select an employee");
                return;
            }

            try {
                Conn conn = new Conn();
                String query = "UPDATE employee1 SET age = '" + age + "', gender = '" + gnd + "', job = '" + job
                        + "', salary = '" + slry + "', phone = '" + phn + "', email = '" + eml + "' WHERE name = '" + name + "'";

                conn.s.executeUpdate(query);
                JOptionPane.showMessageDialog(null, "Employee details updated successfully");
                setVisible(false);
                dispose();

            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (ae.getSource() == backBtn) {
            setVisible(false);
            dispose();
        }
    }

    public static void main(String[] args) {
        new UpdateEmp();
    }
}