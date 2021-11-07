package DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class DB {
	public static Connection con;
	public static Statement stmt;
	public DB() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");		
			//드라이버 로드(최신 버전은 위와같이 cj를 붙여야 함)
			con = DriverManager.getConnection("jdbc:mysql://localhost/", "user", "1234");
			// 계정 접속(user 계정에 접속하고 SSL문구를 표시 안함)
			stmt = con.createStatement();
			stmt.execute("use user_information");
			// user_information이라는 스키마를 사용
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
