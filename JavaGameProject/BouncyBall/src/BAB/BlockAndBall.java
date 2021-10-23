package BAB;

import javax.swing.JLabel;

import Utils.MyImage;

public class BlockAndBall extends JLabel {
	private String[][] imageName = {{"����", "default_block", "iron_block"}, {"Circle"}};
	private int floor = 0;						// �� ���� ��ġ�ϴ��� ǥ���� �ε���
	private int x = 0;							// x��ǥ�� �ε��� ����
	private float weight = 0;					// ������ ���� ����
	private float accel = 0;					// ���ӵ� ����
	
	
	
	public BlockAndBall(int block_num, int floor, int x) {			// ���� ��������
		this.floor = floor;
		this.x = x;
		if (block_num != 0) {		// ���� �ε����� 0�� �ƴҶ�
			this.setIcon(MyImage.getImage("Block\\" + imageName[0][block_num], 30, 30));	// ���� ������ �迭�� �̸��� �̿��Ͽ� �̹��� ���� ������� 30, 30���� ����
			setSize(30, 30);															// ���̺��� ũ�� 30, 30���� ����
		}
	}
	
	
	
	
	public BlockAndBall(int ball_num) {								// �� ��������
		this.setIcon(MyImage.getImage("Ball\\" + imageName[1][ball_num], 20, 20));	// �� ������ �迭�� �̸��� �̿��Ͽ� �̹��� ���� ������� 20, 20���� ����
		setSize(20, 20);															// ���̺��� ũ�� 20, 20���� ����
		setWeight(50);																// ���� 50 ����
	}
	
	
	
	
	public int getFloor() { return floor; } 
	public int getXpoint() { return x; } 
	public void setWeight(float weight) { this.weight = weight; }
	public float getWeight() { return weight; }
	public void setAccel(float accel) { this.accel= accel; }
	public float getAccel() { return accel; }
}