package Main;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;

import Utils.MyPanel;

public class Login extends JFrame implements ActionListener, FocusListener {
	private JButton b1, b2, b3;
	
	public Login() {
		setSize(500, 500);
		setLocationRelativeTo(null);						// ó���� ȭ���� ����� ��Ÿ����
		setResizable(false);								// ȭ�� ũ�� ���� ���
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel all=new MyPanel("login_background", 485, 460);
		all.setLayout(null);
		
		JPanel p1=new JPanel(new GridLayout(0, 1, 0, 10));	p1.setOpaque(false);
		p1.setBounds(40, 170, 400, 60);
		
		JPasswordField t1=new JPasswordField(20); t1.addFocusListener(this); t1.setName("ID"); t1.setOpaque(false);		// �н������ʵ� ����, ��Ŀ�� ������ ����, ������Ʈ �̸� ����, �������� false
		JPasswordField t2=new JPasswordField(20);	t2.addFocusListener(this); t2.setName("PW"); t2.setOpaque(false);	
		t1.setEchoChar((char)0); t2.setEchoChar((char)0);				// ���� ��ȣȭ ����
		t1.setText(" " + t1.getName());		// �⺻ �ؽ�Ʈ�� ����
		t2.setText(" " + t2.getName());
		p1.add(t1); p1.add(t2);
		
		JPanel p2=new JPanel(new GridLayout(0, 1, 0, 10));
		p2.setBounds(40, 250, 400, 180);
		p2.setOpaque(false);
		b1 = new JButton("Login");	b1.addActionListener(this);
		b2 = new JButton("Sign Up"); b2.addActionListener(this);
		b3 = new JButton("Cancel"); b3.addActionListener(this);
		p2.add(b1); p2.add(b2); p2.add(b3);

		all.add(p1); all.add(p2);
		
		add(all);
		setVisible(true);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == b1) {
			
		} else if (e.getSource() == b2) {
			
		} else if (e.getSource() == b3) {
			System.exit(0);
		}
	}

	public void focusGained(FocusEvent e) {
		JPasswordField text = (JPasswordField) e.getSource();		// JPasswordField���·� getSource �ޱ�
		text.setBackground(new Color(79, 167, 255));				// ��Ŀ���� ������ ���� �ٲٱ�
		text.setOpaque(true);										// �������� true
		if (String.valueOf(text.getPassword()).equals(" " + text.getName())) {	// �⺻ �ؽ�Ʈ ���� ������
			text.setText("");													// ��ĭ���� �����
			if (text.getName().equals("PW")) text.setEchoChar('��');				// ������Ʈ �̸��� PW�̸� ��й�ȣ ��ȣȭ ����
		}
	}

	public void focusLost(FocusEvent e) {
		JPasswordField text = (JPasswordField) e.getSource();		// JPasswordField���·� getSource �ޱ�
		text.setBackground(new Color(0, 0, 0));						// ��Ŀ�� ������ ���� ���ֱ�
		text.setOpaque(false);										// �������� false
		if (String.valueOf(text.getPassword()).equals("")) {					// �ؽ�Ʈ �ڽ��� ��� ������
			text.setText(" " + text.getName());									// �⺻ �ؽ�Ʈ ������ ����
			if (text.getName().equals("PW")) text.setEchoChar((char)0);			// ������Ʈ �̸��� PW�̸� ��й�ȣ ��ȣȭ ����
		} 
	}
}