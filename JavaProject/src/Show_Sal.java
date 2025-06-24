import javax.swing.*;
import java.awt.event.*;
import java.sql.*;

public class Show_Sal extends JFrame implements ActionListener {
    JLabel l1;
    JTextField tf1;
    JTextArea ta;
    JButton b1, backBtn;


    Show_Sal() {
        setTitle("Show Employees by Salary");
        setLayout(null);

        l1 = new JLabel("Enter Minimum Salary:");
        l1.setBounds(30, 30, 150, 30);
        add(l1);

        tf1 = new JTextField();
        tf1.setBounds(180, 30, 150, 30);
        add(tf1);

        b1 = new JButton("Show");
        b1.setBounds(130, 80, 100, 30);
        b1.addActionListener(this);
        add(b1);

        ta = new JTextArea();
        ta.setBounds(30, 130, 350, 200);
        ta.setEditable(false);
        add(ta);

        backBtn = new JButton("Back");
        backBtn.setBounds(240, 80, 100, 30);
        backBtn.addActionListener(e -> {
            new Home();
            dispose();
        });
        add(backBtn);

        setSize(450, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        String salStr = tf1.getText();
        if (salStr.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter a salary value.");
            return;
        }

        try {
            double salary = Double.parseDouble(salStr);
            Connection con = Connections.getConnection();
            CallableStatement stmt = con.prepareCall("CALL get_employees_by_salary(?)");
            stmt.setDouble(1, salary);
            ResultSet rs = stmt.executeQuery();

            StringBuilder sb = new StringBuilder();
            while (rs.next()) {
                sb.append("ID: ").append(rs.getInt("id"))
                  .append(", Name: ").append(rs.getString("name"))
                  .append(", Email: ").append(rs.getString("email"))
                  .append(", Salary: ").append(rs.getDouble("salary"))
                  .append("\n");
            }
            ta.setText(sb.toString());
            con.close();
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
        }
    }

    public static void main(String[] args) {
        new Show_Sal();
    }
}
