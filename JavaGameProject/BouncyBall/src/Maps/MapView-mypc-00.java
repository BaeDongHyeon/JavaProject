package Maps;

import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.text.StyledEditorKit.BoldAction;

import BAB.BlockAndBall;
import Main.MainMenu;
import Utils.MyButton;
import Utils.MyPanel;

public class MapView extends JFrame implements KeyListener, ActionListener {
	private ArrayList<BlockAndBall> l = new ArrayList<>();
	private int[][] maps = MyMaps.MapList(0);				// 맵의 인덱스 설정
	private int angle = 130, power = 0, angle_vel=1, power_vel=1;				// 시작 지점
	private double startPx = 0, startPy = 620, blly=0, ballvelx=0, ballvely=0, ballx=0, bally=0,  gravity = 3;//중력  
	private boolean isLeft=false, isRight=false, isJump=false, down=false, jump=false;
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
//		setBackground(Color.white);
		isJump = true;
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
		/*
		if(isJump) { // 중력 가속도 
	        try {
	            bally = 620; //공의 초기위치
	    		angle += angle_vel; 
	    		if (angle > 80 || angle < 5) angle_vel *= -1;				
	    		power += power_vel;  
	            if (power > 99 || power < 1) power_vel *= -1;
//				ballvelx = (power * Math.cos(angle * (Math.PI / 180)))*0.07; // 수평 도달 거리 
	            ballvely = -(power * Math.sin(angle * (Math.PI / 180)))*0.8; // 높이
	            bally += ballvely; 
	            ballvely += gravity; //중력 값을 더해줌
				Thread.sleep(1);
	            lb.setLocation((int)ballx, (int)bally);
//	            System.out.printf("\nX:%f, Y:%f", ballvelx, ballvely);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		*/

		if(isJump) { // 좌표 이동 
//			for(int y=0;y<=10;y++) { // Up => +55
			int count=0;
			while(count<=60) {
				try {
					startPy-=1;
					Thread.sleep(2);
					lb.setLocation((int)startPx, (int)startPy);
					count++;
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			
			int i = lb.getY()/30; 
			int j = lb.getX()/30;
			while(maps[i+1][j]!=2) { // 기본 블럭이 아닐 경우 
				try {
					System.out.println("I:" + i + "J:" + j);
					System.out.println("Maps:" + maps[i+1][j]);
					startPy+=1;
					Thread.sleep(4);
					lb.setLocation((int)startPx, (int)startPy);
					i = lb.getY()/30;
					j = lb.getX()/30;
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			
			/*
			for(int y=0;y<=10;y++) { // Down
				try {
					startPy += y;
					Thread.sleep(40);
					boolean col = Collision((int)startPx, (int)startPy);  
					if(col) lb.setLocation((int)startPx, (int)startPy);
					else {
						System.out.println("충돌!");
						startPx=0; startPy=620; // 리스폰 
					}
					jump = Jumping((int)startPx, (int)startPy);
					Jump(jump);
				
					boolean Down = DownCheck(lb.getX(), lb.getY());
					while(y==10 && !Down) {
						startPy += 1;
						Thread.sleep(1);
						lb.setLocation((int)startPx, (int)startPy);
						Down = DownCheck(lb.getX(), lb.getY());
					}
			
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			*/
		}
		try {
			Thread.sleep(2);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}	
	
	public boolean Collision(int x, int y) {
		int i = y/30; // 630/30 => 21
		int j = x/30; // 180/30 => 6 
		if(maps[i+1][j]==3 || maps[i+1][j]==7) { // 가시 or 전기 블럭  
			int w = l.get(i*42+j).getY() - (lb.getY()); // 장애물 블럭 Y좌표 - (공 Y좌표+공 세로 크기)  
			if(w<=0) return false; // 충돌   			
		}
		return true; 
	}
	
	public boolean Jumping(int x, int y) { // Jumping block
		int i = y/30;
		int j = x/30;
		if(maps[i+1][j]==5) {
			int w = l.get(i*42+j).getY() - (lb.getY());
			if(w<=0) return true;
		}
		return false;
	}
	
	public void Jump(boolean jump) {
	int i=0;
		while(jump) {
			try {
				if(i>=16) jump=false;
				else {
					startPy-=i;
					Thread.sleep(20);
					lb.setLocation((int)startPx, (int)startPy);
					i++;
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public int Block(int x, int y, int s) {
		int i = y/30; 
		int j = x/30;  
		
		switch(maps[i+1][j]) {
			case 3: // 가시
				int a = l.get(i*42+j).getY() - (lb.getY()); // 장애물 블럭 Y좌표 - (공 Y좌표+공 세로 크기)
				if(a<=0) return 3;
				break;
				
			case 5: // Jumping 
				int b = l.get(i*42+j).getY() - (lb.getY());
				if(b<=0) return 5;
				break;

			case 7:	// 전기
				int c = l.get(i*42+j).getY() - (lb.getY());
				if(c<=0) return 7;
				break;
		}
		return 0;
	}

	public void keyTyped(KeyEvent e) {}
	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {
			case KeyEvent.VK_LEFT:
				if(startPx>=2) {
					ballx -= 4;
					startPx -= 4;					
				} break;
			case KeyEvent.VK_RIGHT:
				if(startPx<=1230) {
					ballx += 4;
					startPx += 4;				
				} break;
		}
	}
	public void keyReleased(KeyEvent e) {
		switch (e.getKeyCode()) {
			case KeyEvent.VK_LEFT:
				break;
			case KeyEvent.VK_RIGHT:
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
