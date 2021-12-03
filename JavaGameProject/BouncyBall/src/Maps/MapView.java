package Maps;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import BAB.BlockAndBall;
import Screen.MainMenu;
import Utils.MyButton;

public class MapView extends JFrame implements KeyListener, ActionListener {
	private ArrayList<BlockAndBall> l = new ArrayList<>();
	private int[][] maps = MyMaps.MapList(0);				// 맵의 인덱스 설정
	private int angle = 130, power = 0, angle_vel=1, power_vel=1;				// 시작 지점
	private int startPx = 0, startPy = 620, blly=0, ballvelx=0, ballvely=0, ballx=0, bally=0,  gravity = 3;//중력  
	private boolean isLeft=false, isRight=false, isJump=false, down=false, jump=false, Left=false;
	private JLabel lb;
	private JButton game_back;
	private final float GRAVITY = -9.8f;
	private int myW = 1400, myH = 800;	
	private BufferedImage image;
	
	public MapView() {
		setSize(myW, myH);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null); // 가운데 배치
		setResizable(false); // 화면 크기 고정
		setTitle("In Game");
		game_back = new MyButton("Button\\game_back_Button", 30, 30); game_back.setLocation(20, 20);
		add(game_back);
		game_back.addActionListener(this);
		this.setFocusable(true);
		
		setLayout(null);
		addKeyListener(this);
		
		ballx = 0; // 중력 가속도 공 시작 X좌표
		
		int f = 0, x = 0;
		for (int i = 0; i < maps.length; i++) {
			for (int j = 0; j < maps[i].length; j++) {
				l.add(new BlockAndBall(maps[i][j], f, x)); 
				x++;
			}
			x = 0;
			f++;
		}

		for (int i = 0; i < l.size(); i++) {
			JLabel label = l.get(i);				// 한 블럭 한 블럭 가져오기
			int floor = (maps.length - l.get(i).getFloor()) * 30; // 현재 층 저장 
			int w = l.get(i).getXpoint() * 30; // x값 저장
			label.setLocation(w, myH - 100 - floor);
			add(label);
		}
		
		lb = new BlockAndBall(0); // 공
		lb.setLocation(startPx, startPy);
		add(lb);

		setVisible(true);
		Thread t1=new Thread(new gravity());
		Thread t2=new Thread(new moving());
		t1.start();
		t2.start();
	}
	
	private class gravity implements Runnable {
		public void run() {
			try {
				while (true) {
					
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	private class moving implements Runnable {
		public void run() { while (true) move(); }		// 무한 루프로 움직임 입력 체크
	}
	
	private void move() {
		try {
			if (startPx>0 && isLeft) startPx -= 5;
			if (startPx<1230 && isRight) startPx += 5;
			lb.setLocation(startPx, startPy);
			Thread.sleep(10);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void keyTyped(KeyEvent e) {}
	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {
			case KeyEvent.VK_LEFT:
				isLeft = true;
				break;
			case KeyEvent.VK_RIGHT:
				isRight = true;
				break;
		}
	}
	public void keyReleased(KeyEvent e) {
		switch (e.getKeyCode()) {
			case KeyEvent.VK_LEFT:
				isLeft = false;
				break;
			case KeyEvent.VK_RIGHT:
				isRight = false;
				break;
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == game_back) {
			this.dispose();
			new MainMenu("Bouncy Ball", 1280, 720); 
		}
	}
}
