
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/*
1. JDBC 클래스 라이브러리 import
2. JDBC 드라이버 LOAD - 최신 버전에서는 load 불필요 (자동으로 load)
3. MYSQL 연결을 위한 Connection 객체 생성
4. Statement 객체를 생성하여 질의 수행
- 프로그램에서 조건을 입력받을 때는 PreparedStatement 객체 이용
5. 질의 결과가 있다면, ResultSet 객체를 생성하여 결과 저장
6. 추가 로직 실행 후, JDBC 연결 과정에서 필요했던 객체들을 close
 */
public class dbtest {
    private static final String url = "jdbc:mysql://localhost:3306/mydb?serverTimeZone=UTC";
    private static final String user = "root";
    private static final String password = System.getenv("DB_PASSWORD");

    public static void main(String[] args) {
        try (Connection conn = getConnection()){ // Connection 객체 생성
            runSQL(conn); // 실행시키기
            System.out.println("정상적으로 연결되었습니다.");
        } catch(SQLException e){
            System.out.println("연결할 수 없습니다.");
            e.printStackTrace();
        }

    }

    public static Connection getConnection() throws SQLException{
        return DriverManager.getConnection(url, user, password);
    }

    public static void runSQL(Connection conn) throws SQLException{
        try(Statement stmt = conn.createStatement()){ // Statement 객체를 생성
            getResult(stmt);
        }
    }

    public static void getResult(Statement stmt) throws SQLException{
        String sql = "SELECT Fname, Salary FROM EMPLOYEE WHERE sex = 'M'";
        String fname;
        Double salary;
        try (ResultSet rs = stmt.executeQuery(sql)){ // 쿼리 실행하고 저장
            while(rs.next()){
                fname = rs.getString(1);
                salary = rs.getDouble("Salary");
                System.out.printf("Fname : %s , Salary : %f\n", fname, salary);
            }
        }

    }

}
