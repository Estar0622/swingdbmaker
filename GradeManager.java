package app.telecom.ui;

import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

import app.telecom.dao.GradeDao;
import app.telecom.dto.Grade;

import java.util.List;

public class GradeManager extends JFrame {

    private JTable table;
    private DefaultTableModel tableModel;
    private JButton addButton, editButton;

    // GradeDao 인스턴스 생성
    private GradeDao gradeDao = new GradeDao();

    public GradeManager() {
        setTitle("Grade Manager");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        tableModel = new DefaultTableModel(new Object[]{"ID", "Name", "Discount Rate"}, 0);
        table = new JTable(tableModel);

        // DB 로부터 현재 Grade 테이블에 있는 데이터를 가져와서 보여준다.
        listGrades();

        addButton = new JButton("Add Grade");
        editButton = new JButton("Edit Grade");

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(addButton);
        buttonPanel.add(editButton);

        setLayout(new BorderLayout());
        add(new JScrollPane(table), BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        addButton.addActionListener(e -> {
            AddGradeDialog addDialog = new AddGradeDialog(this, this.tableModel);
            addDialog.setVisible(true);
        });

        editButton.addActionListener(e -> {
            int selectedRow = table.getSelectedRow();
            if (selectedRow >= 0) {
                EditGradeDialog editDialog = new EditGradeDialog(this, this.tableModel, selectedRow);
                editDialog.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(this, "등급을 선택하세요.");
            }
        });
    }

    private void clearTable() {
        tableModel.setRowCount(0);
    }

    private void listGrades() {
        // 현재 tableModel 을 정리하고
        clearTable();

        // DAO를 통해 데이터베이스에서 데이터를 가져온다
        List<Grade> gradeList = gradeDao.listGrade();

        for (Grade grade : gradeList) {
            tableModel.addRow(new Object[]{grade.getId(), grade.getName(), grade.getDiscountRate()});
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new GradeManager().setVisible(true);
        });
    }
}
