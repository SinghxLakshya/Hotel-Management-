package hotelmanagement;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class Dashboard extends JFrame implements ActionListener {
JMenuItem ademp,adrms,addrv;
    public Dashboard() {

        setBounds(0, 0, 1550, 1000);
        setLayout(null);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/third.jpg"));
        Image i2 = i1.getImage().getScaledInstance(1366, 1000, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(0, 0, 1366, 1000);
        add(image);

        // menubar is cooded here
        JMenuBar mb = new JMenuBar();
        mb.setBounds(0, 0, 1366, 30);
        image.add(mb);

        JMenu hotel = new JMenu("Hotel Management");
        mb.add(hotel);

        JMenu ad = new JMenu("Admin");
        ad.setForeground(Color.RED);
        mb.add(ad);

        // Menu itemsare here
        JMenuItem recep=new JMenuItem("Reception");
        recep.setForeground(new Color(0, 123, 255));
        hotel.add(recep);

       ademp=new JMenuItem("Add employee");
        ademp.setForeground(new Color(0, 123, 255));
        ademp.addActionListener(this);
        ad.add(ademp);

        adrms=new JMenuItem("Add rooms");
        adrms.setForeground(new Color(0, 123, 255));
        adrms.addActionListener(this);
        ad.add(adrms);
        
       addrv=new JMenuItem("Add Drives");
        addrv.setForeground(new Color(0, 123, 255));
        addrv.addActionListener(this);
        ad.add(addrv);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setVisible(true);
    }

    public static void main(String[] args) {
        new Dashboard();
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if(ae.getSource() == ademp){
            
            new Addemp();
        }
         if(ae.getSource() == adrms){
            new Addroom();
        }
         if(ae.getSource() == addrv){
            new Adddriver();
        }
    }
}
