package app.telecom.ui;

import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class EditGradeDialog extends JDialog {
    private JTextField idField, nameField, discountRateField;
    private JButton saveButton;

    public EditGradeDialog(JFrame parent, DefaultTableModel tableModel, int rowIndex) {
        setTitle("Grade Save Dialog");
        setSize(300, 200);
        setLayout(new GridLayout(4, 2));
        setLocationRelativeTo(parent);

        // 선택된 row의 각 항목의 값을 구함
        String id = (String) tableModel.getValueAt(rowIndex, 0);
        String name = (String) tableModel.getValueAt(rowIndex, 1);
        String discountRate = (String) tableModel.getValueAt(rowIndex, 2);

        // field 초기화
        idField = new JTextField(id);
        nameField = new JTextField(name);
        discountRateField = new JTextField(discountRate);

        // button
        saveButton = new JButton("Save");

        // field와 label, button 추가
        add(new JLabel("ID"));
        add(idField);
        add(new JLabel("Name"));
        add(nameField);
        add(new JLabel("Discount Rate"));
        add(discountRateField);
        add(new JLabel(""));
        add(saveButton);

        // saveButton 클릭 시 이벤트 처리
        saveButton.addActionListener(e -> {
            tableModel.setValueAt(idField.getText(), rowIndex, 0);
            tableModel.setValueAt(nameField.getText(), rowIndex, 1);
            tableModel.setValueAt(discountRateField.getText(), rowIndex, 2);
            dispose();
        });
    }
}
