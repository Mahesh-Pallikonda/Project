import javax.swing.*;

public class Help extends JFrame {
    JTextArea ta;
    JButton backBtn;

    Help() {
        setTitle("Help");
        setLayout(null);

        ta = new JTextArea();
        ta.setBounds(20, 20, 350, 200);
        ta.setEditable(false);
        ta.setText(
            "Instructions:\n\n" +
            "1. Add Employee - Enter name, email, salary.\n" +
            "2. Update Employee - Modify employee by ID.\n" +
            "3. Search Employee - Enter ID to view details.\n" +
            "4. Show All - Lists all employee records.\n" +
            "5. Show By Salary - Filter employees by salary.\n"
        );
        add(ta);

        backBtn = new JButton("Back");
        backBtn.setBounds(140, 230, 100, 30);
        backBtn.addActionListener(e -> {
            new Home();
            dispose();
        });
        add(backBtn);

        setSize(400, 320);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    public static void main(String[] args) {
        new Help();
    }
}
