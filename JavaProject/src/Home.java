import javax.swing.*;
import java.awt.event.*;

public class Home extends JFrame implements ActionListener {
    JButton b1, b2, b3, b4, b5;

    Home() {
        setTitle("Home");
        setLayout(null);

        b1 = new JButton("Add Employee");
        b1.setBounds(100, 30, 200, 30);
        add(b1);

        b2 = new JButton("Update Employee");
        b2.setBounds(100, 70, 200, 30);
        add(b2);

        b3 = new JButton("Search Employee");
        b3.setBounds(100, 110, 200, 30);
        add(b3);

        b4 = new JButton("Show All Employees");
        b4.setBounds(100, 150, 200, 30);
        add(b4);

        b5 = new JButton("Help");
        b5.setBounds(100, 190, 200, 30);
        add(b5);

        b1.addActionListener(this);
        b2.addActionListener(this);
        b3.addActionListener(this);
        b4.addActionListener(this);
        b5.addActionListener(this);

        setSize(400, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == b1) {
            new Add();
        } else if (e.getSource() == b2) {
            new Update();
        } else if (e.getSource() == b3) {
            new Search();
        } else if (e.getSource() == b4) {
            new Show();
        } else if (e.getSource() == b5) {
            new Help();
        }
    }

    public static void main(String[] args) {
        new Home();
    }
}
