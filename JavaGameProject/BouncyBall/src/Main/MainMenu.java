package Main;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import Utils.MyButton;
import Utils.MyPanel;

public class MainMenu extends JFrame implements ActionListener {
	private JPanel p1;
	private JButton b1, b2;
	
	public MainMenu(String title, int width, int height) {
		setSize(width, height);							// 크기
		setTitle(title);								// 제목
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	// 프로세스 종료
		setLocationRelativeTo(null);					 // 가운데 배치
		setResizable(false); 							// 화면 크기 고정
		
		p1=new MyPanel("main_background", 1385, 760);					// 그리기 패널
		p1.setLayout(null);
		
		b1 = new MyButton("Button\\play_Button", 300, 100);	b1.setLocation(0, 300);
		b2 = new MyButton("Button\\RanKing_Button", 300, 100); b2.setLocation(0, 430);
		p1.add(b1); p1.add(b2);
		b1.addActionListener(this);
		
		add(p1);
		setVisible(true);
	}

	public static void main(String[] args) {
		new Login();
		//new MainMenu("Bouncy Ball", 1400, 800);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == b1) {
			remove(p1);
			repaint();
		} else if (e.getSource() == b2) {
			System.out.println("아직 아무 이벤트 없음");
		}
	}
}
