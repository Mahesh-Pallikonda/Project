import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class Show extends JFrame {
    JTextArea ta;
    JButton backBtn;

    Show() {
        setTitle("All Employees");
        setLayout(new BorderLayout());

        ta = new JTextArea();
        ta.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(ta);
        add(scrollPane, BorderLayout.CENTER);

        backBtn = new JButton("Back");
        backBtn.addActionListener(e -> {
            new Home();
            dispose();
        });
        add(backBtn, BorderLayout.SOUTH);

        setSize(500, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);

        loadEmployees();
    }

    void loadEmployees() {
        try {
            Connection con = Connections.getConnection();
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("select * from employee");

            StringBuilder sb = new StringBuilder();
            while (rs.next()) {
                sb.append("ID: ").append(rs.getInt("id"))
                  .append(", Name: ").append(rs.getString("name"))
                  .append(", Email: ").append(rs.getString("email"))
                  .append(", Salary: ").append(rs.getDouble("salary"))
                  .append("\n");
            }

            if (sb.length() == 0) {
                ta.setText("No employee data found.");
            } else {
                ta.setText(sb.toString());
            }

            con.close();
        } catch (Exception e) {
            e.printStackTrace();
            ta.setText("Error loading data: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        new Show();
    }
}
