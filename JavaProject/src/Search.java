import javax.swing.*;
import java.awt.event.*;
import java.sql.*;

public class Search extends JFrame implements ActionListener {
    JLabel l1;
    JTextField tf1;
    JTextArea ta;
    JButton b1, backBtn;


    Search() {
        setTitle("Search Employee");
        setLayout(null);

        l1 = new JLabel("Enter Employee ID:");
        l1.setBounds(30, 30, 150, 30);
        add(l1);

        tf1 = new JTextField();
        tf1.setBounds(180, 30, 150, 30);
        add(tf1);

        b1 = new JButton("Search");
        b1.setBounds(130, 80, 100, 30);
        b1.addActionListener(this);
        add(b1);

        ta = new JTextArea();
        ta.setBounds(30, 130, 300, 150);
        ta.setEditable(false);
        add(ta);
        
        backBtn = new JButton("Back");
        backBtn.setBounds(240, 80, 100, 30);
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
        if (idStr.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter employee ID.");
            return;
        }

        try {
            int id = Integer.parseInt(idStr);
            Connection con = Connections.getConnection();
            CallableStatement stmt = con.prepareCall("CALL get_employee_by_id(?)");
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                String result = "ID: " + rs.getInt("id") +
                                "\nName: " + rs.getString("name") +
                                "\nEmail: " + rs.getString("email") +
                                "\nSalary: " + rs.getDouble("salary");
                ta.setText(result);
            } else {
                ta.setText("Employee not found.");
            }
            con.close();
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
        }
    }

    public static void main(String[] args) {
        new Search();
    }
}
