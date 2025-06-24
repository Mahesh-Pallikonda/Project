import javax.swing.*;
import java.awt.event.*;
import java.sql.*;

public class Update extends JFrame implements ActionListener {
    JLabel l1, l2, l3, l4;
    JTextField tf1, tf2, tf3, tf4;
    JButton b1, backBtn;


    Update() {
        setTitle("Update Employee");
        setLayout(null);

        l1 = new JLabel("Employee ID:");
        l1.setBounds(30, 30, 100, 30);
        add(l1);

        tf1 = new JTextField();
        tf1.setBounds(150, 30, 150, 30);
        add(tf1);

        l2 = new JLabel("Name:");
        l2.setBounds(30, 80, 100, 30);
        add(l2);

        tf2 = new JTextField();
        tf2.setBounds(150, 80, 150, 30);
        add(tf2);

        l3 = new JLabel("Email:");
        l3.setBounds(30, 130, 100, 30);
        add(l3);

        tf3 = new JTextField();
        tf3.setBounds(150, 130, 150, 30);
        add(tf3);

        l4 = new JLabel("Salary:");
        l4.setBounds(30, 180, 100, 30);
        add(l4);

        tf4 = new JTextField();
        tf4.setBounds(150, 180, 150, 30);
        add(tf4);

        b1 = new JButton("Update");
        b1.setBounds(100, 240, 100, 30);
        b1.addActionListener(this);
        add(b1);

        backBtn = new JButton("Back");
        backBtn.setBounds(220, 240, 100, 30);
        backBtn.addActionListener(e -> {
            new Home();
            dispose();
        });
        add(backBtn);

        
        setSize(400, 350);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        String idStr = tf1.getText();
        String name = tf2.getText();
        String email = tf3.getText();
        String salaryStr = tf4.getText();

        if (idStr.isEmpty() || name.isEmpty() || email.isEmpty() || salaryStr.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill all fields.");
            return;
        }

        try {
            int id = Integer.parseInt(idStr);
            double salary = Double.parseDouble(salaryStr);
            Connection con = Connections.getConnection();
            CallableStatement stmt = con.prepareCall("CALL update_employee(?, ?, ?, ?)");
            stmt.setInt(1, id);
            stmt.setString(2, name);
            stmt.setString(3, email);
            stmt.setDouble(4, salary);
            stmt.execute();
            JOptionPane.showMessageDialog(this, "Employee Updated Successfully.");
            con.close();
        } catch (NumberFormatException nfe) {
            JOptionPane.showMessageDialog(this, "Invalid input for ID or salary.");
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
        }
    }

    public static void main(String[] args) {
        new Update();
    }
}
