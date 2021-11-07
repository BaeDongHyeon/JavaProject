package Main;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import Maps.MapView;
import Utils.MyButton;
import Utils.MyPanel;

public class MainMenu extends JFrame implements ActionListener {
	private JPanel p1;
	private JButton b1, b2;
	private JPanel panel = new JPanel();
	private JButton play, ranking, back, setting;
	private JButton[] map = new JButton[8];
	private JScrollPane scroll = new JScrollPane(panel, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
	
	public MainMenu(String title, int width, int height) {
		setSize(width, height);							// 크기
		setTitle(title);								// 제목
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	// 프로세스 종료
		setLocationRelativeTo(null);					 // 가운데 배치
		setResizable(false); 							// 화면 크기 고정
		
		p1=new MyPanel("main_background", 1385, 760);					// 그리기 패널
		p1.setLayout(null);
		
		play = new MyButton("Button\\play_Button", 300, 100);	play.setLocation(470, 300);
		ranking = new MyButton("Button\\RanKing_Button", 300, 100); ranking.setLocation(70, 300);
		setting = new MyButton("Button\\setting_Button", 300, 100); setting.setLocation(900, 300);
		back = new MyButton("Button\\back_Button", 100, 50); back.setLocation(50, 50);
		back.setVisible(false); scroll.setVisible(false);
		scroll.getVerticalScrollBar().setUnitIncrement(30); //스크롤 속도
		
		p1.add(play); p1.add(ranking);
		p1.add(back); p1.add(scroll);
		p1.add(setting);
		add(p1);
		
		play.addActionListener(this);
		ranking.addActionListener(this);
		back.addActionListener(this);
				
		// 맵 선택 스크롤
		scroll.setBounds(400, 200, 500, 400);
		panel.setLayout(new GridLayout(map.length, 1));
		for(int i=0;i<map.length;i++) {
			map[i] = new MyButton("Button\\map" + i + "_Button", 450, 400);
			panel.add(map[i]);
			map[i].addActionListener(this);
		}
		
		setVisible(true);
	}

	public static void main(String[] args) {
		new Login();
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == play) {
			remove(p1);			
			p1=new MyPanel("select_background", 1385, 760);
			getContentPane().add(p1);	
			revalidate();				
			repaint();					
			p1.setLayout(null);
			p1.add(back); p1.add(scroll);			
			back.setVisible(true); scroll.setVisible(true);
		} 
		else if (e.getSource() == ranking) {
			remove(p1);
			p1=new MyPanel("ranking_background", 1385, 760);
			getContentPane().add(p1);	
			revalidate();				
			repaint();
			p1.setLayout(null);
			p1.add(back);
			back.setVisible(true);
		}
		else if(e.getSource() == back) {
			remove(p1);			
			p1=new MyPanel("main_background", 1385, 760);
			getContentPane().add(p1);	
			revalidate(); repaint();					
			p1.setLayout(null);
			p1.add(play); p1.add(ranking); 
			p1.add(setting);
		}
		else if(e.getSource() == map[0]) {
			remove(p1);					
			p1=new MapView();
			getContentPane().add(p1);		
			revalidate(); repaint();					
			p1.setLayout(null);			
		}
		else if(e.getSource() == map[1]) {
			remove(p1);					
			p1=new MapView();
			getContentPane().add(p1);		
			revalidate(); repaint();					
			p1.setLayout(null);			
		}
	}
}
