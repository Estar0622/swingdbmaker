package app.telecom.dao;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import app.telecom.dto.Grade;

public class GradeDao {
    private Connection getConnection() throws SQLException {
        // 데이터베이스 연결 설정 (실제 데이터베이스 정보에 맞게 수정 필요)
        String url = "jdbc:mysql://localhost:3307/telecomdb";
        String username = "root";
        String password = "root0622";
        return DriverManager.getConnection(url, username, password);
    }

    public List<Grade> listGrade() {
        List<Grade> gradeList = new ArrayList<>();
        String sql = "SELECT * FROM Grade";

        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                int id = rs.getInt("Id");
                String name = rs.getString("Name");
                BigDecimal discountRate = rs.getBigDecimal("Discountrate");
                Grade grade = new Grade(id, name, discountRate);
                gradeList.add(grade);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return gradeList;
    }

    public int addGrade(Grade grade) {
        String sql = "INSERT INTO Grade (Id, Name, Discountrate) VALUES (?, ?, ?)";

        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, grade.getId());
            pstmt.setString(2, grade.getName());
            pstmt.setBigDecimal(3, grade.getDiscountRate());

            return pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return 0; // 실패 시 0 반환
        }
    }

    public int updateGrade(Grade grade) {
        String sql = "UPDATE Grade SET Name = ?, Discountrate = ? WHERE Id = ?";

        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, grade.getName());
            pstmt.setBigDecimal(2, grade.getDiscountRate());
            pstmt.setInt(3, grade.getId());

            return pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return 0; // 실패 시 0 반환
        }
    }

    public int deleteGrade(int id) {
        String sql = "DELETE FROM Grade WHERE Id = ?";

        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);

            return pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return 0; // 실패 시 0 반환
        }
    }
}
