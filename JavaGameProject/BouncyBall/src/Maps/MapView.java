package Maps;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import BAB.BlockAndBall;

public class MapView extends JPanel implements KeyListener{
	private ArrayList<BlockAndBall> l = new ArrayList<>();			
	private int[][] maps = MyMaps.MapList(0);				// 맵의 인덱스 설정
	private int startPx = 0, startPy = 620;					// 시작 지점
	private boolean isLeft = false, isRight = false;
	private JLabel lb;
	private final float GRAVITY = -9.8f;
	private int myW = 1400, myH = 800;

	public MapView() {
//		setSize(myW, myH);
//		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		setLocationRelativeTo(null); // 가운데 배치
//		setResizable(false); // 화면 크기 고정
//		setTitle("In Game");
		setLayout(null);
		setBackground(Color.white);
		addKeyListener(this);

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
		
		lb = new BlockAndBall(0);
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
		if (isLeft) startPx -= 1;
		if (isRight) startPx += 1;
		lb.setLocation(startPx, startPy);
		try {
			Thread.sleep(2);
		} catch(Exception e) {
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
}
