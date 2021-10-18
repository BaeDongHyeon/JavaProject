import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;


class ballBouns extends JFrame {
	private ArrayList<Block> l=new ArrayList<>();
	private int[][] maps = {
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0,0,0,0}
	};
	public ballBouns() {
		Dimension myscreen = Toolkit.getDefaultToolkit().getScreenSize();		// ȭ�� �ػ� ��������
		int myW = myscreen.width-100, myH = myscreen.height-100;
		setSize(myW, myH);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);					// ��� ��ġ
		setResizable(false);							// ȭ�� ũ�� ����
		setTitle("�ٿ ��");
		setLayout(null);
		
		int f = 0, x = 0;
		for (int i=0; i<maps.length; i++) {
			for (int j=0; j<maps[i].length; j++) {
				l.add(new Block(maps[i][j], f, x));
				x++;
			}
			x = 0;
			f++;
		}
		
		for (int i=0; i<l.size(); i++) {
			JLabel label = l.get(i);
			int floor = (maps.length - l.get(i).getFloor()) * 30;			// ���� �� ����
			int w = l.get(i).getXpoint() * 30;				// x�� ����
			label.setLocation(w, myH-100 - floor);
			add(label);
		}
		setVisible(true);
	}
}

public class Test {
	public static void main(String[] args) {
		new ballBouns();
	}
}