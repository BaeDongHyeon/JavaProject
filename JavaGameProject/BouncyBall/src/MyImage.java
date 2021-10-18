import java.awt.Image;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class MyImage {			// 이름에 맞게 이미지를 출력해줌
	public static ImageIcon getImage(String name, int x, int y) {
		ImageIcon icon = null;
		try {
			Image img = ImageIO.read(new File("ImageFile\\" + name + ".png"));
			Image re = img.getScaledInstance(x, y, Image.SCALE_SMOOTH);
			icon = new ImageIcon(re);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return icon;
	}
}
