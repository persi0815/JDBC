import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class dbtest3 {

    public static void main(String[] args) throws SQLException {
        Scanner scanner = new Scanner(System.in);
        Connection conn = null;

        // db 연결
        conn = DriverManager.getConnection(dbtest.url, dbtest.user, dbtest.password); // Connection 객체 생성

        // insert 문
        String stml_3 = "insert into DEPARTMENT values (?,?,?,?)";
        PreparedStatement ps = conn.prepareStatement(stml_3); // Prepared Statement 객체 생성

        // 입력받기
        System.out.println("Enter department name: ");
        String dname = scanner.nextLine();
        System.out.println("Enter department number: ");
        Long dnumber = scanner.nextLong();
        scanner.nextLine(); // 남아있는 엔터 제거
        System.out.println("Enter manager's ssn: ");
        String mgr_ssn = scanner.nextLine();
        System.out.println("Enter manager's start date: ");
        String mgr_sdate  = scanner.nextLine();

        // 값 저장
        ps.clearParameters();
        ps.setString(1, dname);
        ps.setLong(2, dnumber);
        ps.setString(3, mgr_ssn);
        ps.setString(4, mgr_sdate);
        ps.executeUpdate();

        // 연결 해제
        try {
            if (conn != null)
                conn.close(); // Connection 객체가 제대로 생성되지 않았다면
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }
}