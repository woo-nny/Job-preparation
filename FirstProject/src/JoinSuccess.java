import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.SpringLayout;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Font;

public class JoinSuccess {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JoinSuccess window = new JoinSuccess();
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
	public JoinSuccess() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(Color.WHITE);
		frame.setBounds(100, 100, 363, 184);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		SpringLayout springLayout = new SpringLayout();
		frame.getContentPane().setLayout(springLayout);
		
		JLabel lb_main = new JLabel("\uD68C\uC6D0\uAC00\uC785 \uC131\uACF5 !");
		springLayout.putConstraint(SpringLayout.NORTH, lb_main, 50, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, lb_main, 78, SpringLayout.WEST, frame.getContentPane());
		lb_main.setForeground(new Color(0, 0, 128));
		lb_main.setFont(new Font("배달의민족 주아", Font.PLAIN, 35));
		lb_main.setBackground(new Color(0, 0, 128));
		frame.getContentPane().add(lb_main);
	}
}
	
