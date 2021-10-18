package 테스트용;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Test extends JFrame {
	public static ImageIcon getImage(String name, int x, int y) {
		ImageIcon icon = null;
		try {
			Image img = ImageIO.read(new File(name));
			Image re = img.getScaledInstance(x, y, Image.SCALE_SMOOTH);
			icon = new ImageIcon(re);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return icon;
	}
	
	public Test() {
		setSize(500, 500);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("");
		setLayout(null);

		JPanel p = new MyPanel();
		
		add(p);
		setVisible(true);
	}
	class MyPanel extends JPanel {
		public void paintComponent(Graphics g) {
			
		}
	}
	public static void main(String[] args) {
		Test t = new Test();
	}
}
