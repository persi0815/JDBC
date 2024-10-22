import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class dbtest2 {
    public static void main(String[] args) throws SQLException, IOException {
        Scanner scanner = new Scanner(System.in);
        Connection conn = null;

        String username, password, dbname, ssn, lname;
        Double salary;

        System.out.println("Enter database username: ");
        username = scanner.nextLine();
        System.out.println("Enter password: ");
        password = scanner.nextLine();
        System.out.println("Enter database name: ");
        dbname = scanner.nextLine();

        String url = "jdbc:mysql://localhost:3306/" + dbname + "?serverTimeZone=UTC";
        conn = DriverManager.getConnection(url, username, password); // Connection 객체 생성

        String stml_n = "select Lname, Salary from EMPLOYEE where Ssn = ?";
        PreparedStatement ps = conn.prepareStatement(stml_n); // Prepared Statement 객체 생성
        // 미리 준비된 sql 쿼리를 사용하여, 쿼리에 매개변수를 바인딩 할 수 있음

        System.out.println("Enter a Social Security Number: ");
        ssn = scanner.nextLine();

        ps.clearParameters();
        ps.setString(1, ssn); // Ssn 설정
        ResultSet rs = ps.executeQuery(); // 결과 rs에 저장

        // 결과 출력
        while(rs.next()){
            lname = rs.getString(1);
            salary = rs.getDouble(2);
            System.out.println(lname + " " + salary);
        }

        // 연결 해제
        try{
            if(conn != null) conn.close(); // Connection 객체가 제대로 생성되지 않았다면
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }


    }
}
