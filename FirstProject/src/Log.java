
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.ScrollPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;
import java.util.Calendar;
import java.util.Enumeration;

import javax.swing.AbstractButton;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.SpringLayout;
import javax.swing.SwingConstants;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.plaf.basic.BasicBorders.RadioButtonBorder;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import java.awt.Color;

public class Log extends JPanel implements ActionListener {

	private static JFrame frame;
	boolean fi = true;
	String sPath = new File("").getAbsolutePath();
	JRadioButton[] btns = new JRadioButton[5];
	/*
	 * Launch the application.
	 */
	Calendar calendar1 = Calendar.getInstance();
	int hour = calendar1.get(Calendar.HOUR_OF_DAY);
	int min = calendar1.get(Calendar.MINUTE);
	int sec = calendar1.get(Calendar.SECOND);

	javax.swing.Timer timer;
	JLabel lbPresent;
	JRadioButton btn_study;
	JRadioButton btn_eat;
	JRadioButton btn_sleep;
	JRadioButton btn_etc;
	JRadioButton btn_rest;
	JButton btn_end;
	int sum1_1, sum2_1, sum3_1, sum4_1, sum5_1;
	int sum1_2, sum2_2, sum3_2, sum4_2, sum5_2;
	public int sum_study;
	public int sum_rest;
	public int sum_eat;
	public int sum_sleep;
	public int sum_etc;
	String str = "";
	String radio[] = { "°øºÎ", "½Ä»ç", "ÈÞ½Ä", "¼ö¸é", "±âÅ¸" };
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;
	private JButton btn_close;

	public Log()

	{
		setBackground(new Color(255, 160, 122));
		timer = new javax.swing.Timer(1000, this);
		timer.setInitialDelay(0);
		timer.start();
		SpringLayout springLayout = new SpringLayout();
		setLayout(springLayout);

		JTextArea txt_log = new JTextArea();
		txt_log.setFont(new Font("³ª´®½ºÄù¾î¶ó¿îµå ExtraBold", Font.PLAIN, 17));
		JScrollPane scroll_logstart = new JScrollPane(txt_log);

		
		lbPresent = new JLabel("ÇöÀç½Ã°£ : " + hour + "½Ã" + min + "ºÐ " + sec + "ÃÊ", SwingConstants.CENTER);
		springLayout.putConstraint(SpringLayout.WEST, lbPresent, 144, SpringLayout.WEST, this);
		springLayout.putConstraint(SpringLayout.EAST, lbPresent, 611, SpringLayout.WEST, this);
		lbPresent.setForeground(new Color(255, 255, 255));
		lbPresent.setFont(new Font("³ª´®½ºÄù¾î¶ó¿îµå Bold", Font.BOLD, 38));
		add(lbPresent);

		JPanel panel_start = new JPanel();
		springLayout.putConstraint(SpringLayout.NORTH, panel_start, 212, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.WEST, panel_start, 27, SpringLayout.WEST, this);
		springLayout.putConstraint(SpringLayout.SOUTH, panel_start, -100, SpringLayout.SOUTH, this);
		springLayout.putConstraint(SpringLayout.EAST, panel_start, -465, SpringLayout.EAST, this);
		add(panel_start);
		panel_start.setLayout(new BoxLayout(panel_start, BoxLayout.X_AXIS));
		
		JPanel panel_end = new JPanel();
		springLayout.putConstraint(SpringLayout.NORTH, panel_end, 0, SpringLayout.NORTH, panel_start);
		springLayout.putConstraint(SpringLayout.WEST, panel_end, 17, SpringLayout.EAST, panel_start);
		springLayout.putConstraint(SpringLayout.SOUTH, panel_end, 0, SpringLayout.SOUTH, panel_start);
		springLayout.putConstraint(SpringLayout.EAST, panel_end, 381, SpringLayout.EAST, panel_start);
		add(panel_end);
		panel_end.setLayout(new BoxLayout(panel_end, BoxLayout.X_AXIS));
		JTextArea txt_logend = new JTextArea();
		JScrollPane scroll_logend = new JScrollPane(txt_logend);
		

		
		txt_logend.setForeground(Color.RED);
		txt_logend.setFont(new Font("³ª´®½ºÄù¾î¶ó¿îµå ExtraBold", Font.PLAIN, 17));
		scroll_logend.setViewportView(txt_logend);

		JButton btn_start = new JButton("\uC2DC\uC791");
		springLayout.putConstraint(SpringLayout.SOUTH, lbPresent, -19, SpringLayout.NORTH, btn_start);
		springLayout.putConstraint(SpringLayout.EAST, btn_start, -133, SpringLayout.EAST, this);
		btn_start.setBackground(new Color(255, 160, 122));
		btn_start.setFont(new Font("³ª´®°íµñ ExtraBold", Font.PLAIN, 20));
		add(btn_start);

		btn_study = new JRadioButton("\uACF5\uBD80");
		springLayout.putConstraint(SpringLayout.SOUTH, btn_study, -6, SpringLayout.NORTH, panel_start);
		btn_study.setBackground(new Color(255, 160, 122));

		btn_study.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				for (int i = 0; i < btns.length; i++) {
					if (i != 0) {
						btns[i].setSelected(false);
					}
				}
			}
		});
		add(btn_study);
		btn_study.setFont(new Font("³ª´®½ºÄù¾î ExtraBold", Font.PLAIN, 20));

		btn_eat = new JRadioButton("\uC2DD\uC0AC");
		springLayout.putConstraint(SpringLayout.SOUTH, btn_eat, -6, SpringLayout.NORTH, panel_start);
		btn_eat.setBackground(new Color(255, 160, 122));
		springLayout.putConstraint(SpringLayout.WEST, btn_eat, 165, SpringLayout.WEST, this);
		springLayout.putConstraint(SpringLayout.EAST, btn_study, -38, SpringLayout.WEST, btn_eat);
		btn_eat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				for (int i = 0; i < btns.length; i++) {
					if (i != 1) {
						btns[i].setSelected(false);
					}
				}
			}
		});
		add(btn_eat);
		btn_eat.setFont(new Font("³ª´®½ºÄù¾î ExtraBold", Font.PLAIN, 20));

		btn_rest = new JRadioButton("\uD734\uC2DD");
		springLayout.putConstraint(SpringLayout.SOUTH, btn_rest, -6, SpringLayout.NORTH, panel_start);
		btn_rest.setBackground(new Color(255, 160, 122));
		springLayout.putConstraint(SpringLayout.WEST, btn_rest, 39, SpringLayout.EAST, btn_eat);
		btn_rest.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				for (int i = 0; i < btns.length; i++) {
					if (i != 2) {
						btns[i].setSelected(false);
					}
				}
			}
		});
		add(btn_rest);
		btn_rest.setFont(new Font("³ª´®½ºÄù¾î ExtraBold", Font.PLAIN, 20));

		btn_sleep = new JRadioButton("\uC218\uBA74");
		springLayout.putConstraint(SpringLayout.SOUTH, btn_sleep, -6, SpringLayout.NORTH, panel_start);
		btn_sleep.setBackground(new Color(255, 160, 122));
		springLayout.putConstraint(SpringLayout.WEST, btn_sleep, 37, SpringLayout.EAST, btn_rest);
		btn_sleep.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				for (int i = 0; i < btns.length; i++) {
					if (i != 3) {
						btns[i].setSelected(false);
					}
				}
			}
		});
		btn_sleep.setFont(new Font("³ª´®½ºÄù¾î ExtraBold", Font.PLAIN, 20));
		add(btn_sleep);
		btn_etc = new JRadioButton("\uAE30\uD0C0");
		springLayout.putConstraint(SpringLayout.SOUTH, btn_etc, -323, SpringLayout.SOUTH, this);
		btn_etc.setBackground(new Color(255, 160, 122));
		springLayout.putConstraint(SpringLayout.WEST, btn_etc, 39, SpringLayout.EAST, btn_sleep);
		btn_etc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				for (int i = 0; i < btns.length; i++) {
					if (i != 4) {
						btns[i].setSelected(false);
					}
				}
			}
		});
		btn_etc.setFont(new Font("³ª´®½ºÄù¾î ExtraBold", Font.PLAIN, 20));
		add(btn_etc);
		JButton btn_finally = new JButton("\uD558\uB8E8 \uC885\uB8CC!");
		springLayout.putConstraint(SpringLayout.NORTH, btn_finally, 453, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.EAST, btn_finally, -69, SpringLayout.EAST, this);
		springLayout.putConstraint(SpringLayout.SOUTH, btn_start, -247, SpringLayout.NORTH, btn_finally);
		btn_finally.setForeground(new Color(255, 255, 255));
		btn_finally.setBackground(new Color(65, 105, 225));
		btn_finally.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// window.frame.setVisible(true);
				// window.abc(sum_study, sum_rest, sum_sleep, sum_eat, sum_etc);
				// frame.dispose();
				// test.main(null);
				// test window = new test();
				// frame.dispose();
//            System.out.println(sum_study);
//            System.out.println(sum_rest);
//            System.out.println(sum_sleep);
//            System.out.println(sum_eat);
//            System.out.println(sum_etc);

				DailyVO vo = new DailyVO(sum_study, sum_rest, sum_sleep, sum_eat, sum_etc);
				Graph tt = new Graph(vo);
				tt.setDaily(vo);
				tt.frame.setVisible(true);

				frame.dispose();

			}
		});
		btn_finally.setFont(new Font("³ª´®½ºÄù¾î¶ó¿îµå ExtraBold", Font.PLAIN, 20));
		btn_end = new JButton("\uB05D");
		springLayout.putConstraint(SpringLayout.NORTH, btn_end, 169, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.WEST, btn_end, 6, SpringLayout.EAST, btn_start);
		springLayout.putConstraint(SpringLayout.SOUTH, btn_end, -247, SpringLayout.NORTH, btn_finally);
		springLayout.putConstraint(SpringLayout.EAST, btn_end, -68, SpringLayout.EAST, this);
		btn_end.setFont(new Font("³ª´®½ºÄù¾î¶ó¿îµå ExtraBold", Font.PLAIN, 20));
		btn_end.setBackground(new Color(255, 160, 122));

		add(btn_end);
		// panel.add(txt_log);

		scroll_logstart.setSize(200, 150);
		scroll_logend.setSize(200, 150);
		scroll_logstart.setLocation(10, 40);
		panel_start.add(scroll_logstart);
		panel_end.add(scroll_logend);
		add(btn_finally);

		btn_start.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				if (btn_study.isSelected()) { // °øºÎ
					str += btn_study.getText();
					sum1_1 = hour * 60 + min;
					// sum1_1 = min * 60 + sec;
					// System.out.println(sum1);
				}

				if (btn_rest.isSelected()) {
					str += btn_rest.getText();
					sum2_1 = hour * 60 + min;
					// sum2_1 = min * 60 + sec;
				}
				if (btn_sleep.isSelected()) {
					str += btn_sleep.getText();
					sum3_1 = hour * 60 + min;
					// sum3_1 = min * 60 + sec;
				}
				if (btn_eat.isSelected()) {
					str += btn_eat.getText();
					sum4_1 = hour * 60 + min;
					// sum4_1 = min * 60 + sec;
				}
				if (btn_etc.isSelected()) {
					str += btn_etc.getText();
					sum5_1 = hour * 60 + min;
					// sum5_1 = min * 60 + sec;
				}
				str += " ";
				str += "\t" + hour + "½Ã" + min + "ºÐ\n";
				// str += "½ÃÀÛ" + min + "½Ã" + sec + "ºÐ\n";
				txt_log.setText(str);
			}
		});
		btn_end.addActionListener(new ActionListener() {
			String str1 = "";

			public void actionPerformed(ActionEvent e) {
				if (btn_study.isSelected()) { // °øºÎ
					str1 += btn_study.getText();
					sum1_2 = hour * 60 + min;
					// sum1_2 = min * 60 + sec;
					sum_study += (sum1_2 - sum1_1);
				}

				if (btn_rest.isSelected()) {
					str1 += btn_rest.getText();
					sum2_2 = hour * 60 + min;
					// sum2_2 = min * 60 + sec;
					sum_rest += (sum2_2 - sum2_1);
				}
				if (btn_sleep.isSelected()) {
					str1 += btn_sleep.getText();
					sum3_2 = hour * 60 + min;
					// sum3_2 = min * 60 + sec;
					sum_sleep += (sum3_2 - sum3_1);
				}
				if (btn_eat.isSelected()) {
					str1 += btn_eat.getText();
					sum4_2 = hour * 60 + min;
					// sum4_2 = min * 60 + sec;
					sum_eat += (sum4_2 - sum4_1);
				}
				if (btn_etc.isSelected()) {
					str1 += btn_etc.getText();
					sum5_2 = hour * 60 + min;
					// sum5_2 = min * 60 + sec;
					sum_etc += (sum5_2 - sum5_1);
				}
				// str1 += " ";
				// str += "½ÃÀÛ" + hour + "½Ã" + min + "ºÐ"+
				// sum_study+sum_rest+sum_sleep+sum_eat+sum_etc;
				 str1 += " ³¡"+"\t" + hour + "½Ã" + min + "ºÐ\n";
				 txt_logend.setText(str1);
			}
		});
		btns[0] = btn_study;
		btns[3] = btn_sleep;
		btns[4] = btn_etc;
		btns[1] = btn_eat;
		btns[2] = btn_rest;

		lblNewLabel = new JLabel("");
		springLayout.putConstraint(SpringLayout.NORTH, lblNewLabel, 23, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.WEST, lblNewLabel, 37, SpringLayout.WEST, this);
		lblNewLabel.setIcon(new ImageIcon(sPath + "\\img\\student.png"));
		add(lblNewLabel);

		lblNewLabel_1 = new JLabel("DAY LOG");
		springLayout.putConstraint(SpringLayout.NORTH, lblNewLabel_1, 35, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.WEST, lblNewLabel_1, 13, SpringLayout.EAST, lblNewLabel);
		lblNewLabel_1.setFont(new Font("³ª´®½ºÄù¾î ExtraBold", Font.PLAIN, 32));
		add(lblNewLabel_1);

		btn_close = new JButton("");
		btn_close.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MemoCalendar.main(null);
				frame.dispose();
			}
		});
		springLayout.putConstraint(SpringLayout.WEST, btn_close, -59, SpringLayout.EAST, this);
		springLayout.putConstraint(SpringLayout.SOUTH, btn_close, 50, SpringLayout.NORTH, this);
		btn_close.setBackground(new Color(255, 160, 122));
		springLayout.putConstraint(SpringLayout.NORTH, btn_close, 23, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.EAST, btn_close, -34, SpringLayout.EAST, this);
		btn_close.setIcon(new ImageIcon(sPath + "\\img\\close.png"));
		btn_close.setBorderPainted(false);
		btn_close.setContentAreaFilled(false);
		btn_close.setFocusPainted(false);
		// btn_close.setIcon(new ImageIcon("C:\\Users\\smhrc26\\Desktop\\close.png"));
		add(btn_close);

	}

	public void actionPerformed(ActionEvent e)

	{
		++sec;
		Calendar calendar2 = Calendar.getInstance();
		hour = calendar2.get(Calendar.HOUR_OF_DAY);
		min = calendar2.get(Calendar.MINUTE);
		sec = calendar2.get(Calendar.SECOND);
		lbPresent.setText("ÇöÀç : " + hour + "½Ã" + min + "ºÐ " + sec + "ÃÊ");

		String s = e.getActionCommand();

		// System.out.println(s);

	}

	private static void createAndShowGUI()

	{
		// JFrame.setDefaultLookAndFeelDecorated(true);

		frame = new JFrame("DAY LOG");
		frame.setBounds(100, 100, 450, 300);
		frame.setPreferredSize(new Dimension(800, 600));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Log test2 = new Log();
		test2.setOpaque(true);
		frame.setContentPane(test2);
		frame.pack();
		frame.setVisible(true);
	}

	public static void main(String[] args)

	{

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Log window = new Log();
					// window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

		javax.swing.SwingUtilities.invokeLater(new Runnable()

		{
			public void run() {
				createAndShowGUI();
			}
		});
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		// frame = new JFrame();
		// frame.setBounds(100, 100, 450, 300);
		// frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// frame.setPreferredSize(new Dimension(600, 400));
	}
}
