package Utils;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;

public class MyButton extends JButton implements MouseListener {
	private String ImageName=null;
	private int width = 0, height = 0;
	public MyButton(String ImageName, int width, int height) {	// �̹�����, ������ width, height
		this.ImageName = ImageName;
		this.width = width;
		this.height = height;
		setSize(width, height);
		setIcon(MyImage.getImage(ImageName, width, height));
		setBorderPainted(false);		// ��ư �׵θ� ����
		setContentAreaFilled(false);	// ��ư ���� ��� ǥ�� ����
		setFocusPainted(false);			// ��Ŀ�� ǥ�� ����
		setOpaque(false);
		setBackground(Color.white);
		
		addMouseListener(this);
	}

	public void mouseClicked(MouseEvent e) {
		
	}

	public void mousePressed(MouseEvent e) {
		
	}

	public void mouseReleased(MouseEvent e) {
		
	}

	public void mouseEntered(MouseEvent e) {
		setIcon(MyImage.getImage(ImageName + "_click", width, height));
	}

	public void mouseExited(MouseEvent e) {
		setIcon(MyImage.getImage(ImageName, width, height));
	}
	
}