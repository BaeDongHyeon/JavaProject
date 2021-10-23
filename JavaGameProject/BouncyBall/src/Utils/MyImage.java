package Utils;
import java.awt.Image;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class MyImage {			// �̸��� �°� �̹����� �������
	public static ImageIcon getImage(String name, int x, int y) {
		ImageIcon icon = null;
		
		try {
			Image img = ImageIO.read(new File("ImageFile\\" + name + ".png"));			// �ش� ������ �����̸��� �̹��� ��ü�� ����
			Image re = img.getScaledInstance(x, y, Image.SCALE_SMOOTH);					// ũ��� xy, ǰ���� �������� �����ϸ�
			icon = new ImageIcon(re);													// ���������� ����
		} catch (Exception e) {e.printStackTrace();}
		
		return icon;
	}
}