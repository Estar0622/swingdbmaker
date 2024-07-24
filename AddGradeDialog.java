package app.telecom.ui;

import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class AddGradeDialog extends JDialog {
    private JTextField idField, nameField, discountRateField;
    private JButton addButton;

    public AddGradeDialog(JFrame parent, DefaultTableModel tableModel) {
        setTitle("Grade Add Dialog");
        setSize(300, 200);
        setLayout(new GridLayout(4, 2));
        setLocationRelativeTo(parent);

        idField = new JTextField();
        nameField = new JTextField();
        discountRateField = new JTextField();
        addButton = new JButton("Add");

        add(new JLabel("ID"));
        add(idField);
        add(new JLabel("Name"));
        add(nameField);
        add(new JLabel("Discount Rate"));
        add(discountRateField);
        add(new JLabel(""));
        add(addButton);

        addButton.addActionListener(e -> {
            String id = idField.getText();
            String name = nameField.getText();
            String discountRate = discountRateField.getText();
            tableModel.addRow(new Object[]{id, name, discountRate});
            dispose();
        });
    }
}
