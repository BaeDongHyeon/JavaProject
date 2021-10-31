package Utils;

import java.awt.Color;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JPasswordField;
import javax.swing.border.LineBorder;

public class MyTextField extends JPasswordField implements FocusListener {
	public MyTextField(int size, String name) {
		setColumns(size);				// ũ��
		setOpaque(false);				// �������� ����
		addFocusListener(this);			// ��Ŀ�� ������
		setEchoChar((char)0);			// ó������ ���� ǥ��
		setName(name);					// �̸� ����
		setText(" " + getName());		// �⺻ �ؽ�Ʈ ����
		setBorder(new LineBorder(new Color(0, 0, 0)));	// ������ �׵θ� ����
	}

	public void focusGained(FocusEvent e) {
		JPasswordField text = (JPasswordField) e.getSource();		// JPasswordField���·� getSource �ޱ�
		text.setBorder(new LineBorder(new Color(0, 0, 0)));			// ��Ŀ�� ������ �׵θ� ����������
		text.setBackground(new Color(79, 167, 255));				// ��Ŀ���� ������ ���� �ٲٱ�
		text.setOpaque(true);										// �������� true
		if (String.valueOf(text.getPassword()).equals(" " + text.getName())) {	// �⺻ �ؽ�Ʈ ���� ������
			text.setText("");													// ��ĭ���� �����
			if (text.getName().equals("PW") || text.getName().equals("PW Check")) text.setEchoChar('��');				// ������Ʈ �̸��� PW�̸� ��й�ȣ ��ȣȭ ����
		}
	}

	public void focusLost(FocusEvent e) {
		JPasswordField text = (JPasswordField) e.getSource();		// JPasswordField���·� getSource �ޱ�
		text.setBackground(new Color(0, 0, 0));						// ��Ŀ�� ������ ���� ���ֱ�
		text.setOpaque(false);										// �������� false
		if (String.valueOf(text.getPassword()).equals("")) {					// �ؽ�Ʈ �ڽ��� ��� ������
			text.setText(" " + text.getName());									// �⺻ �ؽ�Ʈ ������ ����
			if (text.getName().equals("PW") || text.getName().equals("PW Check")) text.setEchoChar((char)0);			// ������Ʈ �̸��� PW�̸� ��й�ȣ ��ȣȭ ����
		} 
	}
}