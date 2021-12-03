package DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

import Screen.MainMenu;

public class DB {
	private Connection con;
	private Statement stmt;
	public DB() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");		
			//드라이버 로드(최신 버전은 위와같이 cj를 붙여야 함)
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/", "user", "1234");
			// 계정 접속(user 계정에 접속하고 SSL문구를 표시 안함)
			stmt = con.createStatement();
			stmt.execute("use user_information");
			// user_information이라는 스키마를 사용
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public int login(String id, String pw) {
		try {
			ResultSet rs=stmt.executeQuery("SELECT * FROM userlist where ID = '" + id + "'");	// ID를 찾음
			rs.next();		// 다음 레코드를 가르킴
			
			if (rs.getRow() == 0) {		// 가르키는 레코드의 인덱스를 가져옴 0 = 데이터 없음, 1이상은 데이터 존재
				JOptionPane.showMessageDialog(null, "존재하지 않는 아이디 입니다.");
				return -1;
			}
			if (rs.getString("PW").equals(pw)) {
				JOptionPane.showMessageDialog(null, rs.getString("NickName") + "님 환영합니다.");
				return 1;
			}
			rs.close(); 		// 항상 사용 후 닫아주는게 원칙
		} catch (Exception e1) {e1.printStackTrace();}
		
		JOptionPane.showMessageDialog(null, "비밀번호가 틀립니다.");
		return 0;
	}
	
	public void SignUp(String nickname, String id, String pw, String pw_question, String pw_answer) {
		try {
			PreparedStatement pstmt = con.prepareStatement("insert into userlist values (?,?,?,?,?)");
			pstmt.setString(1, nickname);
			pstmt.setString(2, id);
			pstmt.setString(3, pw);
			pstmt.setString(4, pw_question);
			pstmt.setString(5, pw_answer);
			
			int result = pstmt.executeUpdate();
			
			if (result == 1) JOptionPane.showMessageDialog(null, "데이터 입력이 완료되었습니다.");
			else JOptionPane.showMessageDialog(null, "데이터 입력에 실패하였습니다.");
			pstmt.close();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "입력에 실패했습니다.");
			e.printStackTrace();
		}
	}
	
	public boolean overlap_check(String column, String value, boolean empty) {
		if (!empty) return false;		// 입력 값이 없을 경우
		
		try {
			ResultSet rs = stmt.executeQuery("select * from userlist where " + column + " = '" + value + "'");		// 컬럼 체크
			rs.next();
			if (rs.getRow() == 0) return true;		// 데이터가 없다면 사용 가능 true
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;			// 사용 불가 리턴
	}
	
	public ResultSet search(String column, String value) {
		try {
			ResultSet rs=stmt.executeQuery("select * from userlist where " + column + " = '" + value + "'");
			rs.next();
			if (rs.getRow() != 0) return rs;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}
