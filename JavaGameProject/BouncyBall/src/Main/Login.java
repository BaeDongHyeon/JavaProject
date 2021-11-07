package Main;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.LineBorder;

import DB.DB;
import Utils.MyPanel;
import Utils.MyTextField;

public class Login extends JFrame implements ActionListener {
	private DB db=new DB();	// DB 연결 시도
	private final int BTN_SIZE = 5;
	private static final int TXT_SIZE = 7;
	private static final int LBL_SIZE = 5;
	private JButton[] b = new JButton[BTN_SIZE];
	private static JPasswordField[] t = new JPasswordField[TXT_SIZE];
	private String[] btn_name = {"Login", "Sign Up", "close", "Insert", "Go Login"};
	private String[] txt_name = {"ID", "PW", "NickName", "Phone", "ID", "PW", "PW Check"};
	private static String[] lbl_name = {"닉네임 사용 가능 : ", "전화번호('-' 없음) 사용 가능 : ", "아이디 사용 가능 : ", "비밀번호(5자 이상) 사용 가능 : ", "비밀번호 일치 : "};
	private JPanel login_all, sign_all;
	private static JLabel[] l = new JLabel[LBL_SIZE];
	private static boolean[] l_check = {false, false, false, false, false};
	// static이 붙은 변수들은 모두 MyTextField에서 사용하기 위함
	
	public Login() {
		setSize(500, 500);
		setLocationRelativeTo(null);						// 처음에 화면을 가운데에 나타내기
		setResizable(false);								// 화면 크기 조정 잠금
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		login_all=new MyPanel("login_background", 485, 460); login_all.setLayout(null);
		sign_all=new JPanel(); sign_all.setLayout(null);
		sign_all.setBackground(new Color(189, 215, 238));
		
		JPanel login_p1=new JPanel(new GridLayout(0, 1, 0, 10)); login_p1.setOpaque(false);
		JPanel login_p2=new JPanel(new GridLayout(0, 1, 0, 10)); login_p2.setOpaque(false);
		JPanel sign_p1 = new JPanel(new GridLayout(0, 1, 0, 30)); sign_p1.setOpaque(false);
		JPanel sign_p2 = new JPanel(new GridLayout(1, 0, 10, 0)); sign_p2.setOpaque(false);
		JPanel sign_p3 = new JPanel(new GridLayout(0, 1, 0, 30)); sign_p3.setOpaque(false);
		login_p1.setBounds(40, 170, 400, 60);
		login_p2.setBounds(40, 250, 400, 180);
		sign_p1.setBounds(30, 60, 430, 300);
		sign_p2.setBounds(145, 400, 200, 40);
		sign_p3.setBounds(30, 85, 430, 300);
		
		JLabel label = new JLabel("회원가입");
		label.setFont(new Font(null, Font.BOLD, 28));
		label.setOpaque(false);
		label.setBounds(30, 20, 200, 40);
		sign_all.add(label);
		
		for (int i=0; i<TXT_SIZE; i++) {
			t[i] = new MyTextField(20, txt_name[i]);
			
			if (i < 2) login_p1.add(t[i]);		// 로그인 텍스트
			else sign_p1.add(t[i]);				// 회원가입 텍스트
		}
		
		for (int i=0; i<BTN_SIZE; i++) {
			b[i] = new JButton(btn_name[i]);
			b[i].addActionListener(this);
			
			if (i < 3) login_p2.add(b[i]);		// 로그인 버튼
			else sign_p2.add(b[i]);				// 회원가입 버튼
		}
		
		for (int i=0; i<LBL_SIZE; i++) {
			l[i] = new JLabel(lbl_name[i] + l_check[i]);
			sign_p3.add(l[i]);
		}

		login_all.add(login_p1); login_all.add(login_p2);
		sign_all.add(sign_p1); sign_all.add(sign_p2); sign_all.add(sign_p3);
		
		add(login_all);
		setVisible(true);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == b[0]) {			// 로그인
			try {
				ResultSet rs=DB.stmt.executeQuery("SELECT * FROM user_info where ID = '" + String.valueOf(t[0].getPassword()) + "';");
				rs.next();
				
				if (rs.getRow() == 0) {
					JOptionPane.showMessageDialog(this, "존재하지 않는 아이디 입니다.");
					setLinebd(t[0]);
				} else {
					if (rs.getString("PW").equals(String.valueOf(t[1].getPassword()))) {
						JOptionPane.showMessageDialog(this, rs.getString("NickName") + "님 환영합니다.");
						// 로그인이 성공하면 게임화면으로 넘어가도록
						this.dispose();
						new MainMenu("Bouncy Ball", 1400, 800);
					} else {
						JOptionPane.showMessageDialog(this, "비밀번호가 틀립니다.");
						setLinebd(t[1]);
					}
				}
				rs.close(); 		// 항상 사용 후 닫아주는게 원칙
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		} else if (e.getSource() == b[1]) {		// Sign Up
			pageChange(login_all, sign_all);
		} else if (e.getSource() == b[2]) {		// Cancel
			System.exit(0);
		} else if (e.getSource() == b[3]) {		// Insert
			for (int i=2; i<TXT_SIZE; i++) {
				if ( String.valueOf(t[i].getPassword()).equals(" " + t[i].getName()) ) {
					JOptionPane.showMessageDialog(this, "빈칸이 있습니다.");
					setLinebd(t[i]);
					return;
				}
			}
			for (int i=2; i<TXT_SIZE; i++) {
				if (l_check[i-2] == false) {
					JOptionPane.showMessageDialog(this, "조건이 일치하지 않는 정보가 있습니다.");
					setLinebd(t[i]);
					return;
				}
			}
			
			JOptionPane.showMessageDialog(this, "입력이 완료되었습니다.");
			pageChange(sign_all, login_all);
		} else if (e.getSource() == b[4]) {		// Go Login
			pageChange(sign_all, login_all);
		}
	}
	
	public void pageChange(JPanel prev, JPanel next) {
		remove(prev);					// 이전 패널 제거
		getContentPane().add(next);		// 다음 패널 붙이기
		revalidate();					// 새로고침
		repaint();						// 새로고침
	}
	
	public void setLinebd(JPasswordField t) {
		t.setBorder(new LineBorder(Color.red, 2));
	}
	
	public static boolean[] getCheckB() {
		return l_check;
	}
	public static void updateCheck() {
		for (int i=0; i<LBL_SIZE; i++) {
			l[i].setText(lbl_name[i] + l_check[i]);
		}
	}
	public static JPasswordField getPw() {
		return t[5];
	}
	public static int getTxtSize() {
		return TXT_SIZE;
	}
}