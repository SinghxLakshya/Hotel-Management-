package hotel.management.system;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;

import javax.swing.*;

import org.w3c.dom.events.MouseEvent;

public class Hotelmanagement extends JFrame implements ActionListener{
    JButton clickButton;

    public Hotelmanagement(){
        
        setSize(1366,695);
        setLayout(null);

ImageIcon i1=new ImageIcon(ClassLoader.getSystemResource("icons/splash4.png"));
JLabel image=new JLabel(i1);
image.setBounds(0,0,1366,695);
image.setSize(1366,695);
add(image);


JLabel text=new JLabel("Hotel Management System");
text.setBounds(10,100,500,90);
text.setForeground(Color.white);
text.setFont(new Font("serif",Font.PLAIN,40));
image.add(text);

Timer timer = new Timer(500, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                text.setVisible(!text.isVisible());
            }
        });
        timer.start(); // Start the blinking effect
        // --- BLINKING LOGIC END ---

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
                    public void mouseEntered(MouseEvent evt) {
                        clickButton.setBackground(new Color(0, 120, 215));
                        clickButton.setForeground(Color.WHITE);
                    }
        
                    public void mouseExited(MouseEvent evt) {
                        clickButton.setBackground(new Color(0, 0, 0, 200));
                        clickButton.setForeground(Color.WHITE);
                    }
                });
        
                clickButton.addActionListener(this);
                image.add(clickButton);
        


// JButton next=new JButton("Next");
// next.setBounds(1000,550,100,50);
// next.setBackground(Color.BLACK);
// next.setForeground(Color.white);
// next.setFont(new Font("serif",Font.PLAIN,20));
// image.add(next);

        setVisible(true);
    }
    public static void main(String[] args) {
        new Hotelmanagement();
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'actionPerformed'");
    }
}