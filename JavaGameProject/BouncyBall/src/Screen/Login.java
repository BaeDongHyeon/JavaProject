package Screen;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.LineBorder;

import DB.DB;
import Utils.MyPanel;
import Utils.MyTextField;

public class Login extends JFrame implements ActionListener, KeyListener {
	private DB db=new DB();	// DB 연결 시도
	private final int BTN_SIZE = 8;
	private final int TXT_SIZE = 11;
	private final int LBL_SIZE = 11;
	private JButton[] b = new JButton[BTN_SIZE];
	private JButton nick_check = new JButton("중복 확인");
	private JButton id_check = new JButton("중복 확인");
	private JButton forgot_check = new JButton("아이디 확인");
	private JButton question_check = new JButton("질문 확인");
	private JPasswordField[] t = new JPasswordField[TXT_SIZE];
	private String[] check_btn = {"중복 확인", "아이디 확인"};
	private String[] btn_name = {"Login", "Sign Up", "Forgot Password", "close", "Insert", "Go Login", "Reset", "Go Login"};
	private String[] txt_name = {"ID", "PW", "NickName", "ID", "PW", "PW Check", "비밀번호 찾기 답변", "ID", "비밀번호 찾기 답변", "PW", "PW Check"};
	private String[] lbl_name = {"닉네임 사용 가능 : ", "아이디 사용 가능 : ", "비밀번호(5자 이상, 영어 숫자 조합) 사용 가능 : ", "비밀번호 일치 : ", "비밀번호 찾기 질문", "사용 가능: ", "아이디 확인 : ", "질문 확인", "답변 확인 : ", "비밀번호(5자 이상, 영어 숫자 조합) 사용 가농 : ", "비밀번호 일치 : "};
	private String[] question_list = {"내 이메일 주소는?", "나의 보물 1호는?", "나의 출신 초등학교는?", "나의 출신 고향은?", "어머니 성함은?", "아버지 성함은?", "좋아하는 음식은?", "좋아하는 색깔은?"};
	private JComboBox question_box1 = new JComboBox(question_list);
	private JComboBox question_box2 = new JComboBox(question_list);
	private JPanel login_all, sign_all, forgot_all;
	private JLabel[] l = new JLabel[LBL_SIZE];
	private boolean[] l_check = {false, false, false, false, true, false};
	private boolean[] f_check = {false, false, false, false, false};
	// static이 붙은 변수들은 모두 MyTextField에서 사용하기 위함
	
	public Login() {
		setSize(500, 500);
		setLocationRelativeTo(null);						// 처음에 화면을 가운데에 나타내기
		setResizable(false);								// 화면 크기 조정 잠금
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		login_all=new MyPanel("login_background", 485, 460); login_all.setLayout(null);
		sign_all=new JPanel(); sign_all.setLayout(null);
		sign_all.setBackground(new Color(189, 215, 238));
		forgot_all=new JPanel(); forgot_all.setLayout(null);
		forgot_all.setBackground(new Color(189, 215, 238));
		
		JPanel login_p1=new JPanel(new GridLayout(0, 1, 0, 10)); login_p1.setOpaque(false);
		JPanel login_p2=new JPanel(new GridLayout(0, 1, 0, 10)); login_p2.setOpaque(false);
		JPanel sign_p1 = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 25)); sign_p1.setOpaque(false);	//gridlayout은 컴포넌트 개별 크기 조절안되서 flowlayout으로 수정함
		JPanel sign_p2 = new JPanel(new GridLayout(1, 0, 10, 0)); sign_p2.setOpaque(false);
		JPanel sign_p3 = new JPanel(new GridLayout(0, 1, 0, 25)); sign_p3.setOpaque(false);
		JPanel forgot_p1 = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 30)); forgot_p1.setOpaque(false);	//gridlayout은 컴포넌트 개별 크기 조절안되서 flowlayout으로 수정함
		JPanel forgot_p2 = new JPanel(new GridLayout(1, 0, 10, 0)); forgot_p2.setOpaque(false);
		JPanel forgot_p3 = new JPanel(new GridLayout(0, 1, 0, 10)); forgot_p3.setOpaque(false);
		login_p1.setBounds(40, 170, 400, 60);
		login_p2.setBounds(40, 250, 400, 180);
		sign_p1.setBounds(30, 50, 430, 500);
		sign_p2.setBounds(145, 400, 200, 40);
		sign_p3.setBounds(30, 95, 430, 305);
		forgot_p1.setBounds(30, 60, 430, 500);
		forgot_p2.setBounds(145, 400, 200, 40);
		forgot_p3.setBounds(30, 105, 430, 300);
		
		JLabel l_label = new JLabel("회원가입");
		l_label.setFont(new Font(null, Font.BOLD, 28));
		l_label.setOpaque(false);
		l_label.setBounds(30, 20, 200, 30);
		sign_all.add(l_label);
		
		JLabel f_label = new JLabel("비밀번호 재설정");
		f_label.setFont(new Font(null, Font.BOLD, 28));
		f_label.setOpaque(false);
		f_label.setBounds(30, 20, 250, 30);
		forgot_all.add(f_label);
		
		for (int i=0; i<TXT_SIZE; i++) {		// 입력란, 설명란
			if (i==6 || i==8) {
				if (i==6) {
					t[i] = new MyTextField(38, txt_name[i]);
					t[i].setPreferredSize(new Dimension(0, 30));
					t[i].addKeyListener(this);
					question_box1.setPreferredSize(new Dimension(420,30));
					sign_p1.add(question_box1);
					sign_p1.add(t[i]);
				} else {
					t[i] = new MyTextField(29,txt_name[i]);
					t[i].setPreferredSize(new Dimension(0, 30));
					question_box2.setPreferredSize(new Dimension(420,30));
					forgot_p1.add(question_box2);
					forgot_p1.add(t[i]);
					question_check.setPreferredSize(new Dimension(100,30));
					question_check.addActionListener(this);
					forgot_p1.add(t[i]);
					forgot_p1.add(question_check);
				}
			} else if (i==2 || i==3 || i==7) {
				t[i] = new MyTextField(29,txt_name[i]);
				t[i].setPreferredSize(new Dimension(0, 30));
				if (i==2) {
					nick_check.setPreferredSize(new Dimension(100,30));
					nick_check.addActionListener(this);
					sign_p1.add(t[i]);
					sign_p1.add(nick_check);
				} else if (i==3) {
					id_check.setPreferredSize(new Dimension(100,30));
					id_check.addActionListener(this);
					sign_p1.add(t[i]);
					sign_p1.add(id_check);
				} else {
					forgot_check.setPreferredSize(new Dimension(100,30));
					forgot_check.addActionListener(this);
					forgot_p1.add(t[i]);
					forgot_p1.add(forgot_check);
				}
			} else {
				t[i] = new MyTextField(38, txt_name[i]);
				t[i].setPreferredSize(new Dimension(0, 30));
				if (i==4 || i==5 || i==9 || i==10)
					t[i].addKeyListener(this);
				if (i>6)
					forgot_p1.add(t[i]);
				else
					sign_p1.add(t[i]);
			}
			
			if (i < 2) login_p1.add(t[i]);		// 로그인 텍스트
		}
		
		for (int i=0; i<BTN_SIZE; i++) {
			b[i] = new JButton(btn_name[i]);
			b[i].addActionListener(this);
			
			if (i > 5) forgot_p2.add(b[i]);
			else if (i > 3) sign_p2.add(b[i]);		// 로그인 버튼
			else login_p2.add(b[i]);				// 회원가입 버튼
		}
		
		for (int i=0; i<LBL_SIZE; i++) {
			if (i==4 || i==7)
				l[i] = new JLabel(lbl_name[i]);
			else
				if(i<6)
					l[i] = new JLabel(lbl_name[i] + l_check[i]);
				else
					l[i] = new JLabel(lbl_name[i] + f_check[i-6]);
			if(i<6)
				sign_p3.add(l[i]);
			else
				forgot_p3.add(l[i]);
		}

		login_all.add(login_p1); login_all.add(login_p2);
		sign_all.add(sign_p1); sign_all.add(sign_p2); sign_all.add(sign_p3);
		forgot_all.add(forgot_p1); forgot_all.add(forgot_p2); forgot_all.add(forgot_p3);
		
		add(login_all);
		setVisible(true);
	}
	
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == nick_check) {			// 닉네임 중복확인
			l_check[0]=db.overlap_check("NickName", String.valueOf(t[2].getPassword()), !String.valueOf(t[2].getPassword()).equals(" "+t[2].getName()));	// 사용 가능 여부 체크
			l[0].setText(lbl_name[0] + l_check[0]);			// 사용 가능 여부 닉네임
		}
		else if (e.getSource() == id_check) {		// id 중복확인
			l_check[1]=db.overlap_check("ID", String.valueOf(t[3].getPassword()), !String.valueOf(t[3].getPassword()).equals(" "+t[3].getName()));	// 사용 가능 여부 체크
			l[1].setText(lbl_name[1] + l_check[1]);			// 사용 가능 여부 아이디
		}
		else if (e.getSource() == forgot_check) {		// 비밀번호 찾기 시 id 확인
				if (!db.overlap_check("ID", String.valueOf(t[7].getPassword()), !String.valueOf(t[7].getPassword()).equals(" "+t[7].getName())))	// 사용 가능 여부 체크
					f_check[0] = true;
				else
					f_check[0] = false;
				l[6].setText(lbl_name[6] + f_check[0]);			// 사용 가능한 아이디
		}
		else if (e.getSource() == question_check) {		// 비밀번호 찾기 시 질문, 답변 확인
//			try {
//				ResultSet rs=db.stmt.executeQuery("select * from user_info where ID = '" + String.valueOf(t[3].getPassword()) + "'");
//				rs.next();
//				if (rs.getRow() == 0 && !String.valueOf(t[3].getPassword()).equals(" "+t[3].getName())) {
//					f_check[1]=true;
//					l[6].setText(lbl_name[6] + f_check[0]);			// 사용 가능한 아이디
//				}
//				else {
//					f_check[1]=false;
//					l[6].setText(lbl_name[6] + f_check[1]);			// 이미 존재하는 아이디
//				}
//				rs.close();
//			} catch (SQLException e1) {
//				e1.printStackTrace();
//			}
			try {
				ResultSet rs = db.search("ID", String.valueOf(t[7].getPassword()));
				if (rs == null) {
					JOptionPane.showMessageDialog(this, "ID 확인을 다시 해주십시오");
					return;
				}	// 검색되는 아이디가 없을경우
				
				if (question_box2.getSelectedItem().equals(rs.getString("PW_question"))) {
					if (rs.getString("PW_answer").equals(String.valueOf(t[8].getPassword()))) {
						
					}
				}
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
		else if (e.getSource() == b[0]) {			// 로그인
			String id = String.valueOf(t[0].getPassword());
			String pw = String.valueOf(t[1].getPassword());
			int login_code = db.login(id, pw);
			if (login_code == -1) {
				setLinebd(t[0]);
			} else if (login_code == 0) {
				setLinebd(t[1]);
			} else {
				this.dispose();
				new MainMenu("Bouncy Ball", 1280, 720);
			}
		} else if (e.getSource() == b[1]) {		// Sign Up
			pageChange(login_all, sign_all);
		} else if (e.getSource() == b[2]) {
			pageChange(login_all, forgot_all);
		} else if (e.getSource() == b[3]) {		// Close
			System.exit(0);
		} else if (e.getSource() == b[4]) {		// Insert
			for (int i=2; i<7; i++) {
				if (String.valueOf(t[i].getPassword()).equals(" " + t[i].getName()) ) {
					JOptionPane.showMessageDialog(this, "빈칸이 있습니다.");
					setLinebd(t[i]);
					return;
				}
			}
			for (int i=1; i<7; i++) {
				if (l_check[i-1] == false) {
					JOptionPane.showMessageDialog(this, "조건이 일치하지 않는 정보가 있습니다.");
					setLinebd(t[i]);
					return;
				}
			}
			
			JOptionPane.showMessageDialog(this, "입력이 완료되었습니다.");
			pageChange(sign_all, login_all);
		} else if (e.getSource() == b[6]) {
			for (int i=7; i<TXT_SIZE; i++) {
				if (String.valueOf(t[i].getPassword()).equals(" " + t[i].getName()) ) {
					JOptionPane.showMessageDialog(this, "빈칸이 있습니다.");
					setLinebd(t[i]);
					return;
				}
			}
			for (int i=0; i<f_check.length; i++) {
				if (f_check[i] == false) {
					JOptionPane.showMessageDialog(this, "조건이 일치하지 않는 정보가 있습니다.");
					setLinebd(t[i+7]);
					return;
				}
			}
			
			JOptionPane.showMessageDialog(this, "입력이 완료되었습니다.");
			pageChange(sign_all, login_all);
		} else if (e.getSource() == b[5]) {		// Go Login
			pageChange(sign_all, login_all);
		} else if (e.getSource() == b[7]) {
			pageChange(forgot_all, login_all);
		}
	}
	

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void keyReleased(KeyEvent e) {
		if(e.getSource()==t[4] || e.getSource()==t[9]) {					// 비밀번호 입력란
			int p;
			if(e.getSource()==t[4])
				p=4;
			else
				p=9;
			if(t[p].getPassword().length>4) {		
				boolean num_check=false;
				boolean eng_check=false;
				String compare;
				for(int i=0;i<10;i++) {
					if(String.valueOf(t[p].getPassword()).contains(String.valueOf(i)))
						num_check=true;							
				}
				for(int i=65;i<=90;i++) {			// 65~90 = A~Z
					compare=String.valueOf((char)i);
					if(String.valueOf(t[p].getPassword()).contains(compare))
						eng_check=true;
				}
				for(int k=97;k<=122;k++) {			// 97~122 = a~z
					compare=String.valueOf((char)k);
					if(String.valueOf(t[p].getPassword()).contains(compare))
						eng_check=true;
				}
				if(num_check==true && eng_check==true) {
					if (p==4) {
						l_check[2]=true;
						l[2].setText(lbl_name[2] + l_check[2]);
					} else {
						f_check[2]=true;
						l[9].setText(lbl_name[9] + f_check[2]);
					}
				}
				else {
					if (p==4) {
						l_check[2]=false;
						l[2].setText(lbl_name[2] + l_check[2]);
					} else {
						f_check[2]=false;
						l[9].setText(lbl_name[9] + f_check[2]);
					}
				}
			}
			else {
				if (p==4) {
					l_check[2]=false;
					l[2].setText(lbl_name[2] + l_check[2]);
				} else {
					f_check[2]=false;
					l[9].setText(lbl_name[9] + f_check[2]);
				}
			}
		}
		
		if(e.getSource()==t[5] || e.getSource()==t[10]) {					// 비밀번호 확인란
			int p;
			if(e.getSource()==t[5])
				p=5;
			else
				p=10;
			if(String.valueOf(t[p-1].getPassword()).equals(String.valueOf(t[p].getPassword()))) {
				if (p==5) {
					l_check[3]=true;
					l[3].setText(lbl_name[3] + l_check[3]);
				}
				else {
					f_check[3]=true;
					l[10].setText(lbl_name[10] + f_check[3]);
				}
			}
			else {
				if (p==5) {
					l_check[3]=false;
					l[3].setText(lbl_name[3] + l_check[3]);
				}
				else {
					f_check[3]=false;
					l[10].setText(lbl_name[10] + f_check[3]);
				}
			}
		}
		
		if(e.getSource()==t[6]) {
			if(t[6].getPassword().length>0) {
				l_check[5]=true;
				l[5].setText(lbl_name[5] + l_check[5]);
			}
			else {
				l_check[5]=false;
				l[5].setText(lbl_name[5] + l_check[5]);
			}
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

}