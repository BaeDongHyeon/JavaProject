package Main;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import Utils.MyButton;
import Utils.MyImage;

public class MainMenu extends JFrame implements ActionListener {
	private JPanel p1;
	private JButton b1, b2;
	
	public MainMenu(String title, int width, int height) {
		setSize(width, height);							// ũ��
		setTitle(title);								// ����
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	// ���μ��� ����
		setLocationRelativeTo(null);					 // ��� ��ġ
		setResizable(false); 							// ȭ�� ũ�� ����
		
		p1=new MainScreen();					// �׸��� �г�
		p1.setLayout(null);
		
		b1 = new MyButton("Button\\play_Button", 500, 100);	b1.setLocation(0, 300);
		b2 = new MyButton("Button\\RanKing_Button", 500, 100); b2.setLocation(0, 430);
		p1.add(b1); p1.add(b2);
		b1.addActionListener(this);
		
		add(p1);
		setVisible(true);
	}

	public static void main(String[] args) {
		new MainMenu("Bouncy Ball", 1400, 800);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == b1) {
			remove(p1);
			repaint();
		}
	}
}

class MainScreen extends JPanel {				// ���� ȭ��
	private Image img = MyImage.getImage("Background\\main_background", 1385, 760).getImage();

	public void paintComponent(Graphics g) {
		g.drawImage(img, 0, 0, null);
		setOpaque(false);
		super.paintComponent(g);
	}
}