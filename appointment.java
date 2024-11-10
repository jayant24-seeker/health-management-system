import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class appointment extends JFrame {
    private JTextField nameField, lastNameField, phField, emailField, addressField, issueField;

    public appointment() {
        setTitle("Appointment Area");
        setLayout(new GridBagLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        JLabel[] labels = {
            new JLabel("Name:"), new JLabel("Last Name:"), new JLabel("Ph.no:"), 
            new JLabel("Email:"), new JLabel("Per. Address:"), new JLabel("Health Issue:")
        };

        JTextField[] fields = {
            nameField = new JTextField(20), lastNameField = new JTextField(20),
            phField = new JTextField(20), emailField = new JTextField(20),
            addressField = new JTextField(20), issueField = new JTextField(20)
        };

        for (int i = 0; i < labels.length; i++) {
            gbc.gridx = 0;
            gbc.gridy = i;
            add(labels[i], gbc);

            gbc.gridx = 1;
            add(fields[i], gbc);
        }

        JButton confirmButton = new JButton("Confirm");
        confirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showDoctorSelection();
            }
        });

        gbc.gridx = 1;
        gbc.gridy = labels.length + 1;
        add(confirmButton, gbc);

        pack();
        setVisible(true);
    }

    private void showDoctorSelection() {
        String[] doctors = {"Dr. Arpit", "Dr. Swaraj", "Dr. John", "Dr. David"};
        String docName = (String) JOptionPane.showInputDialog(
            this, "Select a doctor:", "Appointment Area",
            JOptionPane.QUESTION_MESSAGE, null, doctors, doctors[0]
        );

        if (docName != null) {
            String name = nameField.getText();
            String condition = issueField.getText();
            showDoctorScreen(docName, name, condition);
        }
    }

    private void showDoctorScreen(String docName, String patientName, String healthIssue) {
        JFrame docFrame = new JFrame("Doctor Confirmation");
        docFrame.setLayout(new GridBagLayout());
        docFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        docFrame.setSize(400, 300);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.gridwidth = 2;

        JLabel messageLabel = new JLabel("<html>New Appointment for Doctor:<br/>" +
                "Patient Name: " + patientName + "<br/>" +
                "Health Issue: " + healthIssue + "<br/>" +
                "Appointment Time: 11:00 AM<br/>Will you be there?</html>");
        gbc.gridy = 0;
        docFrame.add(messageLabel, gbc);

        JButton confirmButton = new JButton("Confirm");
        JButton cancelButton = new JButton("Decline");

        confirmButton.addActionListener(e -> showMessage(docName, "confirmed"));
        cancelButton.addActionListener(e -> showMessage(docName, "not confirmed"));

        gbc.gridwidth = 1;
        gbc.gridy = 1;
        gbc.gridx = 0;
        docFrame.add(confirmButton, gbc);

        gbc.gridx = 1;
        docFrame.add(cancelButton, gbc);

        docFrame.setVisible(true);
    }

    private void showMessage(String doctorName, String status) {
        JOptionPane.showMessageDialog(this, "Your appointment is " + status +
                " by " + doctorName + " at 11:00 am", "Report", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(appointment::new);
    }
}
