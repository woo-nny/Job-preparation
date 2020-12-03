import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SpringLayout;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Locale;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.JCheckBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;

public class Join {

	private JFrame frame;
	private JTextField tf_id;
	private JTextField tf_pw;
	private JTextField tf_name;
	private JLabel lb_suceess;
	JComboBox job_box;
	String select_job;
	String select_test;

	String sPath = new File("").getAbsolutePath();

	/**
	 * Launch the application.
	 */

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Join window = new Join();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Join() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame("회원가입");
		frame.getContentPane().setFont(new Font("배달의민족 주아", Font.PLAIN, 22));
		select_test = "";
		frame.getContentPane().setBackground(new Color(255, 255, 0));
		frame.setBounds(100, 100, 396, 544);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		SpringLayout springLayout = new SpringLayout();
		frame.getContentPane().setLayout(springLayout);

		JLabel lblNewLabel = new JLabel("\uC544\uC774\uB514");
		lblNewLabel.setFont(new Font("배달의민족 주아", Font.PLAIN, 22));
		lblNewLabel.setForeground(new Color(0, 0, 0));
		frame.getContentPane().add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("\uBE44\uBC00\uBC88\uD638");
		springLayout.putConstraint(SpringLayout.WEST, lblNewLabel_1, 0, SpringLayout.WEST, lblNewLabel);
		lblNewLabel_1.setFont(new Font("배달의민족 주아", Font.PLAIN, 22));
		lblNewLabel_1.setForeground(new Color(0, 0, 0));
		frame.getContentPane().add(lblNewLabel_1);

		tf_id = new JTextField();
		springLayout.putConstraint(SpringLayout.WEST, tf_id, 149, SpringLayout.WEST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, tf_id, -56, SpringLayout.EAST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.NORTH, lblNewLabel, -1, SpringLayout.NORTH, tf_id);
		springLayout.putConstraint(SpringLayout.EAST, lblNewLabel, -33, SpringLayout.WEST, tf_id);
		frame.getContentPane().add(tf_id);
		tf_id.setColumns(10);

		tf_pw = new JTextField();
		springLayout.putConstraint(SpringLayout.NORTH, lblNewLabel_1, 0, SpringLayout.NORTH, tf_pw);
		springLayout.putConstraint(SpringLayout.SOUTH, tf_id, -22, SpringLayout.NORTH, tf_pw);
		springLayout.putConstraint(SpringLayout.NORTH, tf_pw, 211, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, tf_pw, 149, SpringLayout.WEST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, tf_pw, 0, SpringLayout.EAST, tf_id);
		frame.getContentPane().add(tf_pw);
		tf_pw.setColumns(10);

		JLabel lblNewLabel_3 = new JLabel("\uC774\uB984");
		springLayout.putConstraint(SpringLayout.WEST, lblNewLabel_3, 64, SpringLayout.WEST, frame.getContentPane());
		lblNewLabel_3.setFont(new Font("배달의민족 주아", Font.PLAIN, 22));
		lblNewLabel_3.setForeground(new Color(0, 0, 0));
		frame.getContentPane().add(lblNewLabel_3);

		tf_name = new JTextField();
		springLayout.putConstraint(SpringLayout.SOUTH, tf_pw, -21, SpringLayout.NORTH, tf_name);
		springLayout.putConstraint(SpringLayout.EAST, lblNewLabel_3, -45, SpringLayout.WEST, tf_name);
		springLayout.putConstraint(SpringLayout.NORTH, tf_name, 4, SpringLayout.NORTH, lblNewLabel_3);
		springLayout.putConstraint(SpringLayout.WEST, tf_name, 149, SpringLayout.WEST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, tf_name, 0, SpringLayout.EAST, tf_id);
		frame.getContentPane().add(tf_name);
		tf_name.setColumns(10);

		JLabel lblNewLabel_5 = new JLabel("");
		lblNewLabel_5.setBackground(new Color(128, 128, 128));
		springLayout.putConstraint(SpringLayout.WEST, lblNewLabel_5, 131, SpringLayout.WEST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, lblNewLabel_5, -22, SpringLayout.SOUTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, lblNewLabel_5, -113, SpringLayout.EAST, frame.getContentPane());
		lblNewLabel_5.setForeground(Color.GRAY);
		lblNewLabel_5.setFont(new Font("배달의민족 주아", Font.BOLD, 20));
		frame.getContentPane().add(lblNewLabel_5);

		JButton btn_join = new JButton("\uD68C\uC6D0\uAC00\uC785");
		springLayout.putConstraint(SpringLayout.WEST, btn_join, 0, SpringLayout.WEST, lblNewLabel);
		springLayout.putConstraint(SpringLayout.SOUTH, btn_join, -69, SpringLayout.SOUTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, btn_join, -181, SpringLayout.EAST, frame.getContentPane());
		btn_join.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				String id = tf_id.getText();
				String pw = tf_pw.getText();
				String name = tf_name.getText();
				String job = job_box.getSelectedItem().toString();
				String test_list = select_test;
				int test_num = 0;
				if (test_list.equals("토익")) {
					test_num = 1;
				} else if (test_list.equals("토익NCS")) {
					test_num = 2;
				} else if (test_list.equals("토익NCS기사")) {
					test_num = 3;
				} else if (test_list.equals("NCS")) {
					test_num = 4;
				} else if (test_list.equals("NCS기사")) {
					test_num = 5;
				} else if (test_list.equals("기사")) {
					test_num = 6;
				}
//				System.out.println(test_num);
//			    System.out.println(job);
//				System.out.println(test_list);
//				MemberVO vo = new MemberVO(id, pw, name, job, test_list);
				MemberVO vo = new MemberVO(id, pw, name, job, test_num);
				MemberDAO dao = new MemberDAO();
				int cnt = dao.join(vo);

				if (cnt > 0) {

					lblNewLabel_5.setText("회원가입 성공");

				} else {

					tf_id.setText("");
					tf_pw.setText("");
					tf_name.setText("");

				}
			}
		});
		btn_join.setBackground(new Color(100, 149, 237));
		btn_join.setForeground(new Color(255, 255, 255));
		btn_join.setFont(new Font("배달의민족 주아", Font.PLAIN, 22));
		frame.getContentPane().add(btn_join);
		btn_join.setBorderPainted(false);

		JButton btn_cancle = new JButton("\uCDE8\uC18C");
		springLayout.putConstraint(SpringLayout.NORTH, btn_cancle, 0, SpringLayout.NORTH, btn_join);
		springLayout.putConstraint(SpringLayout.WEST, btn_cancle, 4, SpringLayout.EAST, btn_join);
		springLayout.putConstraint(SpringLayout.EAST, btn_cancle, 0, SpringLayout.EAST, tf_id);
		btn_cancle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				tf_id.setText("");
				tf_pw.setText("");
				tf_name.setText("");
			}
		});
		btn_cancle.setForeground(new Color(255, 255, 255));
		btn_cancle.setBackground(new Color(100, 149, 237));
		btn_cancle.setFont(new Font("배달의민족 주아", Font.PLAIN, 22));
		frame.getContentPane().add(btn_cancle);
		btn_cancle.setBorderPainted(false);
		String[] job = { "IT", "행정직" };

		job_box = new JComboBox(job);
		springLayout.putConstraint(SpringLayout.EAST, job_box, -144, SpringLayout.EAST, frame.getContentPane());
		job_box.setBackground(new Color(255, 255, 0));
		job_box.setFont(new Font("배달의민족 주아", Font.PLAIN, 17));

		job_box.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				select_job = job_box.getSelectedItem().toString();
				System.out.println(select_job);
			}
		});
		frame.getContentPane().add(job_box);

		JCheckBox check_toeic = new JCheckBox("\uD1A0\uC775");
		springLayout.putConstraint(SpringLayout.NORTH, btn_join, 28, SpringLayout.SOUTH, check_toeic);
		springLayout.putConstraint(SpringLayout.EAST, check_toeic, -169, SpringLayout.EAST, frame.getContentPane());
		check_toeic.setBackground(new Color(255, 255, 0));
		check_toeic.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (check_toeic.isSelected())
					select_test += check_toeic.getText();

			}
		});
		check_toeic.setFont(new Font("배달의민족 주아", Font.PLAIN, 19));
		frame.getContentPane().add(check_toeic);

		JLabel label = new JLabel("\uAD00\uC2EC\uC9C1\uC885");
		springLayout.putConstraint(SpringLayout.WEST, job_box, 16, SpringLayout.EAST, label);
		springLayout.putConstraint(SpringLayout.WEST, label, 64, SpringLayout.WEST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, lblNewLabel_3, -21, SpringLayout.NORTH, label);
		springLayout.putConstraint(SpringLayout.SOUTH, job_box, 0, SpringLayout.SOUTH, label);
		label.setForeground(new Color(0, 0, 0));
		label.setFont(new Font("배달의민족 주아", Font.PLAIN, 22));
		frame.getContentPane().add(label);

		JLabel lblNewLabel_4 = new JLabel("\uC751\uC2DC\uC2DC\uD5D8");
		springLayout.putConstraint(SpringLayout.EAST, lblNewLabel_4, -244, SpringLayout.EAST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, label, -20, SpringLayout.NORTH, lblNewLabel_4);
		springLayout.putConstraint(SpringLayout.WEST, check_toeic, 11, SpringLayout.EAST, lblNewLabel_4);
		springLayout.putConstraint(SpringLayout.SOUTH, lblNewLabel_4, -132, SpringLayout.SOUTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.NORTH, check_toeic, -2, SpringLayout.NORTH, lblNewLabel_4);
		lblNewLabel_4.setForeground(new Color(0, 0, 0));
		lblNewLabel_4.setFont(new Font("배달의민족 주아", Font.PLAIN, 22));
		frame.getContentPane().add(lblNewLabel_4);

		JCheckBox check_ncs = new JCheckBox("NCS");
		springLayout.putConstraint(SpringLayout.NORTH, check_ncs, 0, SpringLayout.NORTH, check_toeic);
		springLayout.putConstraint(SpringLayout.WEST, check_ncs, 9, SpringLayout.EAST, check_toeic);
		check_ncs.setBackground(new Color(255, 255, 0));
		check_ncs.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (check_ncs.isSelected())
					select_test += check_ncs.getText();
			}
		});
		check_ncs.setFont(new Font("배달의민족 주아", Font.PLAIN, 19));
		frame.getContentPane().add(check_ncs);

		JCheckBox check_kisa = new JCheckBox("\uAE30\uC0AC");
		springLayout.putConstraint(SpringLayout.WEST, check_kisa, 288, SpringLayout.WEST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, check_kisa, -26, SpringLayout.EAST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, check_ncs, -6, SpringLayout.WEST, check_kisa);
		springLayout.putConstraint(SpringLayout.NORTH, check_kisa, 0, SpringLayout.NORTH, check_toeic);
		check_kisa.setBackground(new Color(255, 255, 0));
		check_kisa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (check_kisa.isSelected()) {
					select_test += check_kisa.getText();
				}
			}
		});
		check_kisa.setFont(new Font("배달의민족 주아", Font.PLAIN, 19));
		frame.getContentPane().add(check_kisa);

		JLabel lblNewLabel_2 = new JLabel("");
		springLayout.putConstraint(SpringLayout.NORTH, tf_id, 39, SpringLayout.SOUTH, lblNewLabel_2);
		springLayout.putConstraint(SpringLayout.SOUTH, lblNewLabel_2, -371, SpringLayout.SOUTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, lblNewLabel_2, 124, SpringLayout.WEST, frame.getContentPane());
//		lblNewLabel_2.setIcon(new ImageIcon("C:\\Users\\smhrc26\\Desktop\\join.png"));
		lblNewLabel_2.setIcon(new ImageIcon(sPath + "\\img\\join.png"));
		frame.getContentPane().add(lblNewLabel_2);

		JButton btn_close = new JButton("");
		springLayout.putConstraint(SpringLayout.WEST, btn_close, 71, SpringLayout.EAST, lblNewLabel_2);
		springLayout.putConstraint(SpringLayout.SOUTH, btn_close, 62, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, btn_close, -36, SpringLayout.EAST, frame.getContentPane());
		btn_close.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Main.main(null);
				frame.dispose();
			}
		});
		springLayout.putConstraint(SpringLayout.NORTH, btn_close, 28, SpringLayout.NORTH, frame.getContentPane());
		btn_close.setIcon(new ImageIcon(sPath + "\\img\\x.png"));
		frame.getContentPane().add(btn_close);
		btn_close.setBorderPainted(false);
		btn_close.setContentAreaFilled(false);
		btn_close.setFocusPainted(false);

	}
}
