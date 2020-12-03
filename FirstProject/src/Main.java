import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.JPanel;

public class Main {

	private JFrame frame;
	private JTextField tf_id;
	private JTextField tf_pw;

	String sPath = new File("").getAbsolutePath();

	/**
	 * Launch the application.
	 */

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main window = new Main();
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
	public Main() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame("STUDY LIFE");
		frame.getContentPane().setBackground(new Color(30, 144, 255));
		frame.setBounds(100, 100, 673, 436);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		SpringLayout springLayout = new SpringLayout();
		frame.getContentPane().setLayout(springLayout);

		JLabel lblNewLabel = new JLabel(new ImageIcon(sPath + "\\img\\img1.jpg"));
		springLayout.putConstraint(SpringLayout.NORTH, lblNewLabel, 36, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, lblNewLabel, 26, SpringLayout.WEST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, lblNewLabel, -61, SpringLayout.SOUTH, frame.getContentPane());
		frame.getContentPane().add(lblNewLabel);

		JButton btn_join = new JButton("\uD68C\uC6D0\uAC00\uC785");
		springLayout.putConstraint(SpringLayout.EAST, btn_join, -83, SpringLayout.EAST, frame.getContentPane());
		btn_join.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				Join join = new Join();
				join.main(null);

				// 전체 창 끄기 System.exit(0);
				// 첫번째 창 끄기
				frame.dispose();

			}
		});
		btn_join.setBackground(new Color(255, 255, 255));
		btn_join.setForeground(new Color(30, 144, 255));
		btn_join.setFont(new Font("배달의민족 주아", Font.PLAIN, 23));
		frame.getContentPane().add(btn_join);
		btn_join.setBorderPainted(false);

		tf_id = new JTextField();
		springLayout.putConstraint(SpringLayout.NORTH, tf_id, 136, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, tf_id, -81, SpringLayout.EAST, frame.getContentPane());
		frame.getContentPane().add(tf_id);
		tf_id.setColumns(10);

		tf_pw = new JTextField();
		springLayout.putConstraint(SpringLayout.EAST, tf_pw, -81, SpringLayout.EAST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, tf_id, 0, SpringLayout.WEST, tf_pw);
		springLayout.putConstraint(SpringLayout.NORTH, tf_pw, 183, SpringLayout.NORTH, frame.getContentPane());
		frame.getContentPane().add(tf_pw);
		tf_pw.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("\uC544\uC774\uB514");
		springLayout.putConstraint(SpringLayout.WEST, lblNewLabel_1, 317, SpringLayout.WEST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, lblNewLabel, -14, SpringLayout.WEST, lblNewLabel_1);
		springLayout.putConstraint(SpringLayout.SOUTH, tf_id, 0, SpringLayout.SOUTH, lblNewLabel_1);
		lblNewLabel_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_1.setFont(new Font("배달의민족 주아", Font.PLAIN, 25));
		frame.getContentPane().add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("\uBE44\uBC00\uBC88\uD638");
		springLayout.putConstraint(SpringLayout.WEST, lblNewLabel_2, 14, SpringLayout.EAST, lblNewLabel);
		springLayout.putConstraint(SpringLayout.SOUTH, lblNewLabel_2, -173, SpringLayout.SOUTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, tf_pw, 36, SpringLayout.EAST, lblNewLabel_2);
		springLayout.putConstraint(SpringLayout.SOUTH, tf_pw, 0, SpringLayout.SOUTH, lblNewLabel_2);
		springLayout.putConstraint(SpringLayout.SOUTH, lblNewLabel_1, -19, SpringLayout.NORTH, lblNewLabel_2);
		lblNewLabel_2.setFont(new Font("배달의민족 주아", Font.PLAIN, 25));
		lblNewLabel_2.setForeground(new Color(255, 255, 255));
		frame.getContentPane().add(lblNewLabel_2);

		JButton btn_login = new JButton("\uB85C\uADF8\uC778");
		springLayout.putConstraint(SpringLayout.NORTH, btn_join, 0, SpringLayout.NORTH, btn_login);
		springLayout.putConstraint(SpringLayout.WEST, btn_join, 35, SpringLayout.EAST, btn_login);
		springLayout.putConstraint(SpringLayout.NORTH, btn_login, 40, SpringLayout.SOUTH, lblNewLabel_2);
		springLayout.putConstraint(SpringLayout.WEST, btn_login, 0, SpringLayout.WEST, lblNewLabel_1);
		springLayout.putConstraint(SpringLayout.EAST, btn_login, -239, SpringLayout.EAST, frame.getContentPane());
		btn_login.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String id = tf_id.getText();
				String pw = tf_pw.getText();

				MemberVO vo = new MemberVO(id, pw);
				MemberDAO dao = new MemberDAO();

				String name = dao.login(vo);

				if (name != null) {

					MemoCalendar.main(null);
					frame.dispose();

				} else {

					tf_id.setText("");
					tf_pw.setText("");
				}

			}
		});
		btn_login.setBackground(new Color(255, 255, 255));
		btn_login.setForeground(new Color(30, 144, 255));
		btn_login.setFont(new Font("배달의민족 주아", Font.PLAIN, 23));
		frame.getContentPane().add(btn_login);
		btn_login.setBorderPainted(false);

		JLabel lblNewLabel_3 = new JLabel("STUDY LIFE");
		springLayout.putConstraint(SpringLayout.WEST, lblNewLabel_3, 13, SpringLayout.EAST, lblNewLabel);
		springLayout.putConstraint(SpringLayout.SOUTH, lblNewLabel_3, -20, SpringLayout.NORTH, tf_id);
		lblNewLabel_3.setForeground(new Color(255, 255, 255));
		lblNewLabel_3.setFont(new Font("나눔스퀘어 ExtraBold", Font.PLAIN, 40));
		frame.getContentPane().add(lblNewLabel_3);

		JButton btn_close = new JButton("");
		springLayout.putConstraint(SpringLayout.NORTH, btn_close, 10, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, btn_close, -46, SpringLayout.EAST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, btn_close, -10, SpringLayout.EAST, frame.getContentPane());
		btn_close.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				System.exit(0);
			}
		});

		btn_close.setIcon(new ImageIcon(sPath + "\\img\\close.png"));
		btn_close.setBorderPainted(false);
		btn_close.setContentAreaFilled(false);
		btn_close.setFocusPainted(false);
		frame.getContentPane().add(btn_close);
	}
}