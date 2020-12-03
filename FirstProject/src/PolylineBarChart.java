import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.data.category.DefaultCategoryDataset;
import javax.swing.ImageIcon;
import java.awt.SystemColor;

public class PolylineBarChart {

	private JFrame frame;
	private JTextField score;
	private JTextField date;
	String sPath=new File("").getAbsolutePath();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PolylineBarChart window = new PolylineBarChart();
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
	public PolylineBarChart() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(173, 255, 47));
		frame.setBounds(100, 100, 708, 510);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		SpringLayout springLayout = new SpringLayout();
		frame.getContentPane().setLayout(springLayout);

		JPanel panel = new JPanel();
		springLayout.putConstraint(SpringLayout.WEST, panel, 22, SpringLayout.WEST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, panel, 654, SpringLayout.WEST, frame.getContentPane());
		panel.setBackground(new Color(211, 211, 211));
		frame.getContentPane().add(panel);
		panel.setLayout(new BorderLayout(0, 0));

		JLabel Statistic_4 = new JLabel("\uC810\uC218\uC785\uB825");
		springLayout.putConstraint(SpringLayout.NORTH, Statistic_4, 386, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, Statistic_4, -53, SpringLayout.SOUTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, Statistic_4, 338, SpringLayout.WEST, frame.getContentPane());
		Statistic_4.setForeground(new Color(255, 255, 255));
		Statistic_4.setFont(new Font("³ª´®½ºÄù¾î¶ó¿îµå ExtraBold", Font.BOLD, 20));
		frame.getContentPane().add(Statistic_4);

		score = new JTextField();
		springLayout.putConstraint(SpringLayout.NORTH, score, 30, SpringLayout.SOUTH, panel);
		springLayout.putConstraint(SpringLayout.WEST, score, 423, SpringLayout.WEST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, Statistic_4, -13, SpringLayout.WEST, score);
		frame.getContentPane().add(score);
		score.setColumns(10);

		JButton save = new JButton("\uC800\uC7A5");
		springLayout.putConstraint(SpringLayout.SOUTH, panel, -27, SpringLayout.NORTH, save);
		springLayout.putConstraint(SpringLayout.NORTH, save, -3, SpringLayout.NORTH, Statistic_4);
		springLayout.putConstraint(SpringLayout.WEST, save, 32, SpringLayout.EAST, score);
		springLayout.putConstraint(SpringLayout.SOUTH, save, 43, SpringLayout.NORTH, Statistic_4);
		save.setForeground(new Color(255, 255, 255));
		save.setBackground(SystemColor.textHighlight);
		save.setFont(new Font("³ª´®½ºÄù¾î¶ó¿îµå ExtraBold", Font.BOLD, 18));
		save.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				String t_score = score.getText();
				String day_date = date.getText();
				String id_number = "";
				String n_score = "";
				String k_score = "";

				TestScoreVO vo = new TestScoreVO(id_number, day_date, t_score, n_score, k_score);
				TestScoreDAO dao = new TestScoreDAO();
				dao.add(vo);

				ArrayList<TestScoreVO> list = dao.select();

//				int s1 = Integer.parseInt(score.getText());
//				int s2 = Integer.parseInt(list.get(1).getT_score());

				DefaultCategoryDataset dataset = new DefaultCategoryDataset();
				System.out.println(list.size());
				int i = 1;

				for (TestScoreVO a : list) {
					i++;
					dataset.setValue(Integer.parseInt(a.getT_score()), "score" + i, a.getDay_date());
					System.out.println(Integer.parseInt(a.getT_score()));
				}

				JFreeChart chart = ChartFactory.createBarChart("TOEIC SCORE DATA", "DATE", "TOEIC SCORE", dataset);
				CategoryPlot catPlot = chart.getCategoryPlot();
				catPlot.setRangeGridlinePaint(Color.black);

				ChartPanel chartPanel = new ChartPanel(chart);

				panel.removeAll();
				panel.add(chartPanel, BorderLayout.CENTER);
				panel.validate();
				ChartPanel chartPanel1 = new ChartPanel(chart);

				panel.removeAll();
				panel.add(chartPanel1, BorderLayout.CENTER);
				panel.validate();

			}
		});
		frame.getContentPane().add(save);

		JLabel lblNewLabel = new JLabel("\uC751\uC2DC \uC2DC\uD5D8");
		lblNewLabel.setBackground(new Color(255, 255, 255));
		lblNewLabel.setFont(new Font("³ª´®½ºÄù¾î¶ó¿îµå ExtraBold", Font.BOLD, 19));
		frame.getContentPane().add(lblNewLabel);

		JComboBox comboBox = new JComboBox();
		springLayout.putConstraint(SpringLayout.NORTH, panel, 13, SpringLayout.SOUTH, comboBox);
		springLayout.putConstraint(SpringLayout.SOUTH, comboBox, -373, SpringLayout.SOUTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.NORTH, lblNewLabel, 0, SpringLayout.NORTH, comboBox);
		springLayout.putConstraint(SpringLayout.EAST, lblNewLabel, -17, SpringLayout.WEST, comboBox);
		springLayout.putConstraint(SpringLayout.EAST, comboBox, 0, SpringLayout.EAST, save);
		comboBox.setFont(new Font("³ª´®½ºÄù¾î¶ó¿îµå Light", Font.PLAIN, 17));
		comboBox.setModel(new DefaultComboBoxModel(new String[] { "\uD1A0\uC775", "\uAE30\uC0AC", "NCS" }));
		frame.getContentPane().add(comboBox);

		JLabel lblNewLabel_1 = new JLabel("\uB0A0\uC9DC\uC785\uB825");
		springLayout.putConstraint(SpringLayout.NORTH, lblNewLabel_1, 30, SpringLayout.SOUTH, panel);
		springLayout.putConstraint(SpringLayout.WEST, lblNewLabel_1, 105, SpringLayout.WEST, frame.getContentPane());
		lblNewLabel_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_1.setFont(new Font("³ª´®½ºÄù¾î¶ó¿îµå ExtraBold", Font.BOLD, 20));
		frame.getContentPane().add(lblNewLabel_1);

		date = new JTextField();
		springLayout.putConstraint(SpringLayout.NORTH, date, 30, SpringLayout.SOUTH, panel);
		springLayout.putConstraint(SpringLayout.WEST, date, 191, SpringLayout.WEST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, lblNewLabel_1, -14, SpringLayout.WEST, date);
		frame.getContentPane().add(date);
		date.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("");
		springLayout.putConstraint(SpringLayout.WEST, lblNewLabel_2, 33, SpringLayout.WEST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, lblNewLabel_2, -14, SpringLayout.NORTH, panel);
		lblNewLabel_2.setIcon(new ImageIcon(sPath+"\\img\\organize.png"));
		frame.getContentPane().add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("\uC131\uC801 \uAD00\uB9AC");
		springLayout.putConstraint(SpringLayout.WEST, lblNewLabel_3, 0, SpringLayout.WEST, lblNewLabel_1);
		springLayout.putConstraint(SpringLayout.SOUTH, lblNewLabel_3, -27, SpringLayout.NORTH, panel);
		lblNewLabel_3.setFont(new Font("³ª´®½ºÄù¾î ExtraBold", Font.PLAIN, 28));
		frame.getContentPane().add(lblNewLabel_3);
		
		JButton btnNewButton = new JButton("");
		springLayout.putConstraint(SpringLayout.WEST, btnNewButton, -50, SpringLayout.EAST, frame.getContentPane());
		btnNewButton.setForeground(new Color(173, 255, 47));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MemoCalendar.main(null);
				frame.dispose();
			}
		});
		btnNewButton.setBackground(new Color(173, 255, 47));
		springLayout.putConstraint(SpringLayout.NORTH, btnNewButton, 10, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, btnNewButton, -22, SpringLayout.EAST, frame.getContentPane());
		btnNewButton.setIcon(new ImageIcon(sPath+"\\img\\close.png"));
		btnNewButton.setBorderPainted(false);
		btnNewButton.setContentAreaFilled(false);
		btnNewButton.setFocusPainted(false);
		frame.getContentPane().add(btnNewButton);
	}
}
