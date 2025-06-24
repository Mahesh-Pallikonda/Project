import javax.swing.*;
import java.awt.event.*;
import java.sql.*;

public class Login extends JFrame implements ActionListener {
    JLabel l1, l2;
    JTextField tf1;
    JPasswordField pf1;
    JButton b1, backBtn;

    Login() {
        setTitle("Login");
        setLayout(null);

        l1 = new JLabel("Username:");
        l1.setBounds(30, 30, 100, 30);
        add(l1);

        tf1 = new JTextField();
        tf1.setBounds(140, 30, 150, 30);
        add(tf1);

        l2 = new JLabel("Password:");
        l2.setBounds(30, 80, 100, 30);
        add(l2);

        pf1 = new JPasswordField();
        pf1.setBounds(140, 80, 150, 30);
        add(pf1);

        b1 = new JButton("Login");
        b1.setBounds(100, 140, 100, 30);
        b1.addActionListener(this);
        add(b1);

        b1 = new JButton("Login");
        b1.setBounds(50, 140, 100, 30);
        b1.addActionListener(this);
        add(b1);

        backBtn = new JButton("Signup");
        backBtn.setBounds(170, 140, 100, 30);
        backBtn.addActionListener(e -> {
            new Signup();
            dispose();
        });
        add(backBtn);

        setSize(350, 250);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        String user = tf1.getText();
        String pass = String.valueOf(pf1.getPassword());

        try {
            Connection con = Connections.getConnection();
            PreparedStatement ps = con.prepareStatement("select * from users where username=? and password=?");
            ps.setString(1, user);
            ps.setString(2, pass);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                JOptionPane.showMessageDialog(this, "Login Successful.");
                new Home();
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Invalid credentials.");
            }
            con.close();
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
        }
    }

    public static void main(String[] args) {
        new Login();
    }
}
