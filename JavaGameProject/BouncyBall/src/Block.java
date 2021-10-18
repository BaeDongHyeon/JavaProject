import javax.swing.JLabel;

public class Block extends JLabel {
	private String[] imageName = {"����", "default_block", "iron_block"};
	private int block_num = 0;
	private int floor = 0;
	private int x = 0;
	public Block(int block_num, int floor, int x) {
		this.block_num = block_num;
		this.floor = floor;
		this.x = x;
		if (block_num != 0) {
			this.setIcon(MyImage.getImage("Block\\" + imageName[block_num], 30, 30));	// ���� ������ �迭�� �̸��� �̿��Ͽ� �̹��� ���� ������� 30, 30���� ����
			setSize(30, 30);
		}
	}
	public int getBlockNum() { return block_num; } 
	public int getFloor() { return floor; } 
	public int getXpoint() { return x; } 
}