package hotelmanagement;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class Dashboard extends JFrame implements ActionListener {
    JMenuItem ademp, adrms, addrv, recep,rmemp,rmdrv,upemp,updrv;

    public Dashboard() {

        setBounds(0, 0, 1550, 1000);
        setLayout(null);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/third.jpg"));
        Image i2 = i1.getImage().getScaledInstance(1366, 1000, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);

        image.setBounds(0, 0, 1366, 1000);
        add(image);

        // Welcome Text on Image
        JLabel text = new JLabel("THE  CRYSTAL OAK WELCOMES YOU");
        text.setBounds(300, 80, 800, 50);
        text.setFont(new Font("serif", Font.PLAIN, 38));
        text.setForeground(Color.white); // Midnight Blue / Dark Navy
        image.add(text);

        // Menu bar
        JMenuBar mb = new JMenuBar();
        mb.setBounds(0, 0, 1366, 30);
        image.add(mb);

        JMenu hotel = new JMenu("Hotel Management");
        mb.add(hotel);

        JMenu ad = new JMenu("Admin");
        ad.setForeground(Color.RED);
        mb.add(ad);

        // Menu items
        recep = new JMenuItem("Reception");
        recep.setForeground(new Color(0, 123, 255));
        recep.addActionListener(this);
        hotel.add(recep);

        ademp = new JMenuItem("Add Employee");
        ademp.setForeground(new Color(0, 123, 255));
        ademp.addActionListener(this);
        ad.add(ademp);

        adrms = new JMenuItem("Add Rooms");
        adrms.setForeground(new Color(0, 123, 255));
        adrms.addActionListener(this);
        ad.add(adrms);

        addrv = new JMenuItem("Add Drivers");
        addrv.setForeground(new Color(0, 123, 255));
        addrv.addActionListener(this);
        ad.add(addrv);

        rmemp = new JMenuItem("Remove Employee");
        rmemp.setForeground(new Color(0, 123, 255)); 
        rmemp.addActionListener(this);
        ad.add(rmemp);

        rmdrv = new JMenuItem("Remove Drivers");
        rmdrv.setForeground(new Color(0, 123, 255));
        rmdrv.addActionListener(this);
        ad.add(rmdrv);

        upemp = new JMenuItem("Update Employee");
        upemp.setForeground(new Color(0, 123, 255));
        upemp.addActionListener(this);
        ad.add(upemp);

        updrv = new JMenuItem("Update Drivers");
        updrv.setForeground(new Color(0, 123, 255));
        updrv.addActionListener(this);
        ad.add(updrv);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public static void main(String[] args) {
        new Dashboard();
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == ademp) {
            new Addemp();
        } else if (ae.getSource() == adrms) {
            new Addroom();
        } else if (ae.getSource() == addrv) {
            new Adddriver();
        }
         else if (ae.getSource() == recep) {
            new Reception();
        }
         else if (ae.getSource() == rmemp) {
            new DeleteEmp();
        }
         else if (ae.getSource() == rmdrv) {
            new DeleteDriver();
        }
         else if (ae.getSource() == upemp) {
            new UpdateEmp();
        }
         else if (ae.getSource() ==updrv ) {
            new UpdateDriver();
        }









        
    }
}