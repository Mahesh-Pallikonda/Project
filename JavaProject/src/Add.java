import javax.swing.*;
import java.awt.event.*;
import java.sql.*;

public class Add extends JFrame implements ActionListener {
    JLabel l1, l2, l3;
    JTextField tf1, tf2, tf3;
    JButton b1, backBtn;

    Add() {
        setTitle("Add Employee");
        setLayout(null);

        l1 = new JLabel("Name:");
        l1.setBounds(30, 30, 100, 30);
        add(l1);

        tf1 = new JTextField();
        tf1.setBounds(150, 30, 150, 30);
        add(tf1);

        l2 = new JLabel("Email:");
        l2.setBounds(30, 80, 100, 30);
        add(l2);

        tf2 = new JTextField();
        tf2.setBounds(150, 80, 150, 30);
        add(tf2);

        l3 = new JLabel("Salary:");
        l3.setBounds(30, 130, 100, 30);
        add(l3);

        tf3 = new JTextField();
        tf3.setBounds(150, 130, 150, 30);
        add(tf3);

        b1 = new JButton("Add");
        b1.setBounds(50, 200, 100, 30);
        b1.addActionListener(this);
        add(b1);

        backBtn = new JButton("Back");
        backBtn.setBounds(170, 200, 100, 30);
        backBtn.addActionListener(e -> {
            new Home();
            dispose();
        });
        add(backBtn);

        setSize(400, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        String name = tf1.getText();
        String email = tf2.getText();
        String salaryStr = tf3.getText();

        if (name.isEmpty() || email.isEmpty() || salaryStr.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill all fields.");
            return;
        }

        try {
            double salary = Double.parseDouble(salaryStr);
            Connection con = Connections.getConnection();
            CallableStatement stmt = con.prepareCall("CALL add_employee(?, ?, ?)");
            stmt.setString(1, name);
            stmt.setString(2, email);
            stmt.setDouble(3, salary);
            stmt.execute();
            JOptionPane.showMessageDialog(this, "Employee Added Successfully.");
            con.close();
        } catch (NumberFormatException nfe) {
            JOptionPane.showMessageDialog(this, "Invalid salary input.");
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
        }
    }

    public static void main(String[] args) {
        new Add();
    }
}
