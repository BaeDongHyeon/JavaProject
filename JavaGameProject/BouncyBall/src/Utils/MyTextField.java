package Utils;

import java.awt.Color;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.sql.ResultSet;

import javax.swing.JPasswordField;
import javax.swing.border.LineBorder;

public class MyTextField extends JPasswordField implements FocusListener {
	public MyTextField(int size, String name) {
		setColumns(size);				// 크기
		setOpaque(false);				// 불투명도 해제
		addFocusListener(this);			// 포커스 리스너
		setEchoChar((char)0);			// 처음에는 글자 표시
		setName(name);					// 이름 지정
		setText(" " + getName());		// 기본 텍스트 지정
		setBorder(new LineBorder(new Color(0, 0, 0)));	// 검은색 테두리 지정
	}

	public void focusGained(FocusEvent e) {
		JPasswordField text = (JPasswordField) e.getSource();		// JPasswordField형태로 getSource 받기
		text.setBorder(new LineBorder(new Color(0, 0, 0)));			// 포커스 얻으면 테두리 검은색으로
		text.setBackground(new Color(79, 167, 255));				// 포커스를 얻으면 배경색 바꾸기
		text.setOpaque(true);										// 불투명도 true
		if (String.valueOf(text.getPassword()).equals(" " + text.getName())) {	// 기본 텍스트 값이 있으면
			text.setText("");													// 빈칸으로 만들기
			if (text.getName().equals("PW") || text.getName().equals("PW Check")) text.setEchoChar('●');				// 컴포넌트 이름이 PW이면 비밀번호 암호화 실행
		}
	}

	public void focusLost(FocusEvent e) {
		boolean[] checkList = Main.Login.getCheckB();
		JPasswordField text = (JPasswordField) e.getSource();		// JPasswordField형태로 getSource 받기
		text.setBackground(new Color(0, 0, 0));						// 포커스 잃으면 배경색 없애기
		text.setOpaque(false);										// 불투명도 false
		if (String.valueOf(text.getPassword()).equals("")) {					// 텍스트 박스가 비어 있으면
			text.setText(" " + text.getName());									// 기본 텍스트 값으로 변경
			for (int i=2; i<Main.Login.getTxtSize(); i++) {
				if (String.valueOf(text.getPassword()).equals(" " + text.getName())) {
					checkList[i-2] = false; 
				}
			}
			if (text.getName().equals("PW") || text.getName().equals("PW Check")) text.setEchoChar((char)0);			// 컴포넌트 이름이 PW이면 비밀번호 암호화 해제
		}
		
		ResultSet rs;
		try {
			if (!String.valueOf(text.getPassword()).equals(" " + text.getName())) {
				if (text.getName().equals("NickName")) {
					rs=DB.DB.stmt.executeQuery("select * from user_info where NickName = '" + String.valueOf(text.getPassword()) + "'");
					rs.next();
					System.out.println(rs.getRow());
					if (rs.getRow() == 0) checkList[0] = true;			// 사용 가능한 닉네임
					else checkList[0] = false;			// 이미 존재하는 닉네임
					rs.close();
				} else if (text.getName().equals("Phone")) {
					rs=DB.DB.stmt.executeQuery("select * from user_info where Phone = '" + String.valueOf(text.getPassword()) + "'");
					rs.next();
					if (rs.getRow() == 0) checkList[1] = true;			// 사용 가능한 전화번호
					else checkList[1] = false;			// 이미 존재하는 전화번호
					rs.close();
				} else if (text.getName().equals("ID")) {
					rs=DB.DB.stmt.executeQuery("select * from user_info where ID = '" + String.valueOf(text.getPassword()) + "'");
					rs.next();
					if (rs.getRow() == 0) checkList[2] = true;			// 사용 가능한 아이디
					else checkList[2] = false;			// 이미 존재하는 아이디
					rs.close();
				} else if (text.getName().equals("PW")) {
					if (text.getPassword().length > 4) {
						checkList[3] = true;
					} else {
						checkList[3] = false;
					}
				} else if (text.getName().equals("PW Check")) {
					if (String.valueOf(text.getPassword()).equals(String.valueOf(Main.Login.getPw().getPassword()))) {
						checkList[4] = true;
					} else {
						checkList[4] = false;
					}
				}
			}
			Main.Login.updateCheck();
		} catch (Exception e1) {e1.printStackTrace();}
	}
}
