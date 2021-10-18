import javax.swing.JLabel;

public class Block extends JLabel {
	private String[] imageName = {"없음", "default_block", "iron_block"};
	private int block_num = 0;
	private int floor = 0;
	private int x = 0;
	public Block(int block_num, int floor, int x) {
		this.block_num = block_num;
		this.floor = floor;
		this.x = x;
		if (block_num != 0) {
			this.setIcon(MyImage.getImage("Block\\" + imageName[block_num], 30, 30));	// 블럭 폴더에 배열의 이름을 이용하여 이미지 삽입 사이즈는 30, 30으로 조정
			setSize(30, 30);
		}
	}
	public int getBlockNum() { return block_num; } 
	public int getFloor() { return floor; } 
	public int getXpoint() { return x; } 
}
