package Main;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;

import Utils.MyPanel;

public class Login extends JFrame implements ActionListener, FocusListener {
	private JButton b1, b2, b3;
	
	public Login() {
		setSize(500, 500);
		setLocationRelativeTo(null);						// 처음에 화면을 가운데에 나타내기
		setResizable(false);								// 화면 크기 조정 잠금
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel all=new MyPanel("login_background", 485, 460);
		all.setLayout(null);
		
		JPanel p1=new JPanel(new GridLayout(0, 1, 0, 10));	p1.setOpaque(false);
		p1.setBounds(40, 170, 400, 60);
		
		JPasswordField t1=new JPasswordField(20); t1.addFocusListener(this); t1.setName("ID"); t1.setOpaque(false);		// 패스워드필드 생성, 포커스 리스너 지정, 컴포넌트 이름 지정, 불투명도 false
		JPasswordField t2=new JPasswordField(20);	t2.addFocusListener(this); t2.setName("PW"); t2.setOpaque(false);	
		t1.setEchoChar((char)0); t2.setEchoChar((char)0);				// 문자 암호화 해제
		t1.setText(" " + t1.getName());		// 기본 텍스트값 지정
		t2.setText(" " + t2.getName());
		p1.add(t1); p1.add(t2);
		
		JPanel p2=new JPanel(new GridLayout(0, 1, 0, 10));
		p2.setBounds(40, 250, 400, 180);
		p2.setOpaque(false);
		b1 = new JButton("Login");	b1.addActionListener(this);
		b2 = new JButton("Sign Up"); b2.addActionListener(this);
		b3 = new JButton("Cancel"); b3.addActionListener(this);
		p2.add(b1); p2.add(b2); p2.add(b3);

		all.add(p1); all.add(p2);
		
		add(all);
		setVisible(true);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == b1) {
			
		} else if (e.getSource() == b2) {
			
		} else if (e.getSource() == b3) {
			System.exit(0);
		}
	}

	public void focusGained(FocusEvent e) {
		JPasswordField text = (JPasswordField) e.getSource();		// JPasswordField형태로 getSource 받기
		text.setBackground(new Color(79, 167, 255));				// 포커스를 얻으면 배경색 바꾸기
		text.setOpaque(true);										// 불투명도 true
		if (String.valueOf(text.getPassword()).equals(" " + text.getName())) {	// 기본 텍스트 값이 있으면
			text.setText("");													// 빈칸으로 만들기
			if (text.getName().equals("PW")) text.setEchoChar('●');				// 컴포넌트 이름이 PW이면 비밀번호 암호화 실행
		}
	}

	public void focusLost(FocusEvent e) {
		JPasswordField text = (JPasswordField) e.getSource();		// JPasswordField형태로 getSource 받기
		text.setBackground(new Color(0, 0, 0));						// 포커스 얻으면 배경색 없애기
		text.setOpaque(false);										// 불투명도 false
		if (String.valueOf(text.getPassword()).equals("")) {					// 텍스트 박스가 비어 있으면
			text.setText(" " + text.getName());									// 기본 텍스트 값으로 변경
			if (text.getName().equals("PW")) text.setEchoChar((char)0);			// 컴포넌트 이름이 PW이면 비밀번호 암호화 해제
		} 
	}
}
