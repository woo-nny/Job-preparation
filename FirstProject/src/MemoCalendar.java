
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import org.w3c.dom.events.MouseEvent;

import javax.swing.SpringLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import javax.swing.JEditorPane;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JComboBox;
import javax.swing.BoxLayout;

class CalendarDataManager { // 6*7배열에 나타낼 달력 값을 구하는 class
	static final int CAL_WIDTH = 7;
	final static int CAL_HEIGHT = 6;
	int calDates[][] = new int[CAL_HEIGHT][CAL_WIDTH];
	int calYear;
	int calMonth;
	int calDayOfMon;
	final int calLastDateOfMonth[] = { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
	int calLastDate;
	Calendar today = Calendar.getInstance();
	Calendar cal;
	String sPath=new File("").getAbsolutePath();

	public CalendarDataManager() {
		setToday();
	}

	public void setToday() {
		calYear = today.get(Calendar.YEAR);
		calMonth = today.get(Calendar.MONTH);
		calDayOfMon = today.get(Calendar.DAY_OF_MONTH);
		makeCalData(today);
	}

	private void makeCalData(Calendar cal) {
		// 1일의 위치와 마지막 날짜를 구함
		int calStartingPos = (cal.get(Calendar.DAY_OF_WEEK) + 7 - (cal.get(Calendar.DAY_OF_MONTH)) % 7) % 7;
		if (calMonth == 1)
			calLastDate = calLastDateOfMonth[calMonth] + leapCheck(calYear);
		else
			calLastDate = calLastDateOfMonth[calMonth];
		// 달력 배열 초기화
		for (int i = 0; i < CAL_HEIGHT; i++) {
			for (int j = 0; j < CAL_WIDTH; j++) {
				calDates[i][j] = 0;
			}
		}
		// 달력 배열에 값 채워넣기
		for (int i = 0, num = 1, k = 0; i < CAL_HEIGHT; i++) {
			if (i == 0)
				k = calStartingPos;
			else
				k = 0;
			for (int j = k; j < CAL_WIDTH; j++) {
				if (num <= calLastDate)
					calDates[i][j] = num++;
			}
		}
	}

	private int leapCheck(int year) { // 윤년인지 확인하는 함수
		if (year % 4 == 0 && year % 100 != 0 || year % 400 == 0)
			return 1;
		else
			return 0;
	}

	public void moveMonth(int mon) { // 현재달로 부터 n달 전후를 받아 달력 배열을 만드는 함수(1년은 +12, -12달로 이동 가능)
		calMonth += mon;
		if (calMonth > 11)
			while (calMonth > 11) {
				calYear++;
				calMonth -= 12;
			}
		else if (calMonth < 0)
			while (calMonth < 0) {
				calYear--;
				calMonth += 12;
			}
		cal = new GregorianCalendar(calYear, calMonth, calDayOfMon);
		makeCalData(cal);
	}
}

public class MemoCalendar extends CalendarDataManager { // CalendarDataManager의 GUI + 메모기능 + 시계
	// 창 구성요소와 배치도
	JFrame mainFrame;

	JPanel calOpPanel;
	JButton todayBut;
	JButton lYearBut;
	JButton lMonBut;
	JLabel curMMYYYYLab;
	JButton nMonBut;
	JButton nYearBut;
	ListenForCalOpButtons lForCalOpButtons = new ListenForCalOpButtons();

	JPanel calPanel;
	JButton weekDaysName[];
	JButton dateButs[][] = new JButton[6][7];
	listenForDateButs lForDateButs = new listenForDateButs();

	JPanel infoPanel;
	JLabel infoClock;

	JPanel memoPanel;
	JLabel selectedDate;
	JTextArea memoArea;
	JScrollPane memoAreaSP;
	JPanel memoSubPanel;
	JButton saveBut;
	JButton delBut;
	JButton clearBut;

	JPanel frameBottomPanel;
	JLabel bottomInfo = new JLabel("Study Life");
	// 상수, 메세지
	final String WEEK_DAY_NAME[] = { "SUN", "MON", "TUE", "WED", "THR", "FRI", "SAT" };
	final String SaveButMsg1 = "를 MemoData폴더에 저장하였습니다.";
	final String SaveButMsg2 = "메모를 먼저 작성해 주세요.";
	final String SaveButMsg3 = "<html><font color=red>ERROR : 파일 쓰기 실패</html>";
	final String DelButMsg1 = "메모를 삭제하였습니다.";
	final String DelButMsg2 = "작성되지 않았거나 이미 삭제된 memo입니다.";
	final String ClrButMsg1 = "입력된 메모를 비웠습니다.";
	private JPanel panel_1;
	private JPanel panel_2;
	private JPanel panel_3;
	private JPanel panel_4;
	private JLabel lblNewLabel_7;
	private JButton btnNewButton_1;
	private JButton button;
	private JButton btnNewButton_2;
	private JLabel lblNewLabel_1;
	private JButton btnNewButton_3;
	private JButton btnNewButton_4;
	private JButton btnNewButton_5;
	JComboBox comboBox;
	String select_time;
	private JLabel lblNewLabel_2;
	private JButton btn_daylog;
	private JButton btn_score;
	private JLabel lblNewLabel_3;
	private JButton btnSqlD;
	private JLabel lblNewLabel;
	private JButton btnNewButton_6;
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				try {
					MemoCalendar calender=new MemoCalendar();
					calender.mainFrame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public MemoCalendar() { // 구성요소 순으로 정렬되어 있음. 각 판넬 사이에 빈줄로 구별

		mainFrame = new JFrame();
		mainFrame.getContentPane().setBackground(new Color(255, 182, 193));
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.setSize(925, 641);
		mainFrame.setLocationRelativeTo(null);
		mainFrame.setResizable(false);
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");// LookAndFeel Windows 스타일 적용
			SwingUtilities.updateComponentTreeUI(mainFrame);
		} catch (Exception e) {
			bottomInfo.setText("ERROR : LookAndFeel setting failed");
		}

		calPanel = new JPanel();
		weekDaysName = new JButton[7];
		for (int i = 0; i < CAL_WIDTH; i++) {
			weekDaysName[i] = new JButton(WEEK_DAY_NAME[i]);
			weekDaysName[i].setBorderPainted(false);
			weekDaysName[i].setContentAreaFilled(false);
			weekDaysName[i].setForeground(Color.WHITE);
			if (i == 0)
				weekDaysName[i].setBackground(new Color(200, 50, 50));
			else if (i == 6)
				weekDaysName[i].setBackground(new Color(50, 100, 200));
			else
				weekDaysName[i].setBackground(new Color(150, 150, 150));
			weekDaysName[i].setOpaque(true);
			weekDaysName[i].setFocusPainted(false);
			calPanel.add(weekDaysName[i]);
		}
		for (int i = 0; i < CAL_HEIGHT; i++) {
			for (int j = 0; j < CAL_WIDTH; j++) {
				dateButs[i][j] = new JButton();
				dateButs[i][j].setBorderPainted(false);
				dateButs[i][j].setContentAreaFilled(false);
				dateButs[i][j].setBackground(Color.WHITE);
				dateButs[i][j].setOpaque(true);
				dateButs[i][j].addActionListener(lForDateButs);
				calPanel.add(dateButs[i][j]);
			}
		}
		calPanel.setLayout(new GridLayout(0, 7, 2, 2));
		calPanel.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 5));
		showCal(); // 달력을 표시
		selectedDate = new JLabel((today.get(Calendar.YEAR) + "/" + (today.get(Calendar.MONTH) + 1) + "/"
				+ today.get(Calendar.DAY_OF_MONTH)));
		selectedDate.setFont(new Font("나눔스퀘어라운드 Light", Font.PLAIN, 15));
		selectedDate.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 0));

		memoPanel = new JPanel();
		memoPanel.setBackground(new Color(255, 182, 193));
		memoPanel.setBorder(BorderFactory.createTitledBorder("일정등록"));
		memoArea = new JTextArea();
		memoArea.setLineWrap(true);
		memoArea.setWrapStyleWord(true);
		memoAreaSP = new JScrollPane(memoArea, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		readMemo();

		memoSubPanel = new JPanel();
		memoSubPanel.setBackground(new Color(255, 182, 193));
		saveBut = new JButton("Save");
		saveBut.setFont(new Font("나눔스퀘어라운드 Bold", Font.PLAIN, 14));
		saveBut.setBackground(new Color(255, 182, 193));
		saveBut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					File f= new File("MemoData");
					if(!f.isDirectory()) f.mkdir();
					
					String memo = memoArea.getText();
					if(memo.length()>0){
						BufferedWriter out = new BufferedWriter(new FileWriter("MemoData/"+calYear+((calMonth+1)<10?"0":"")+(calMonth+1)+(calDayOfMon<10?"0":"")+calDayOfMon+".txt"));
						String str = memoArea.getText();
						out.write(str);  
						out.close();
						bottomInfo.setText(calYear+((calMonth+1)<10?"0":"")+(calMonth+1)+(calDayOfMon<10?"0":"")+calDayOfMon+".txt"+SaveButMsg1);
					}
					else 
						bottomInfo.setText(SaveButMsg2);
				} catch (IOException e) {
					bottomInfo.setText(SaveButMsg3);
				}
				showCal();
			}
		});
		delBut = new JButton("Delete");
		delBut.setFont(new Font("나눔스퀘어라운드 Bold", Font.PLAIN, 14));
		delBut.setBackground(new Color(255, 182, 193));
		delBut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				memoArea.setText("");
				File f = new File("MemoData/" + calYear + ((calMonth + 1) < 10 ? "0" : "") + (calMonth + 1)
						+ (calDayOfMon < 10 ? "0" : "") + calDayOfMon + ".txt");
				if (f.exists()) {
					f.delete();
					showCal();
					bottomInfo.setText(DelButMsg1);
				} else
					bottomInfo.setText(DelButMsg2);
			}
		});
		clearBut = new JButton("Clear");
		clearBut.setFont(new Font("나눔스퀘어 Bold", Font.PLAIN, 14));
		clearBut.setBackground(new Color(255, 182, 193));
		clearBut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				memoArea.setText(null);
				bottomInfo.setText(ClrButMsg1);
			}
		});
		memoSubPanel.add(saveBut);
		memoSubPanel.add(delBut);
		memoSubPanel.add(clearBut);
		memoPanel.setLayout(new BorderLayout());
		memoPanel.add(selectedDate, BorderLayout.NORTH);
		memoPanel.add(memoAreaSP, BorderLayout.CENTER);
		memoPanel.add(memoSubPanel, BorderLayout.SOUTH);

		// calOpPanel, calPanel을 frameSubPanelWest에 배치
		JPanel frameSubPanelWest = new JPanel();
		frameSubPanelWest.setBackground(new Color(255, 182, 193));
//      calOpPanelSize.height = 100;
		frameSubPanelWest.setLayout(new BorderLayout());
		frameSubPanelWest.add(calPanel, BorderLayout.SOUTH);

		// infoPanel, memoPanel을 frameSubPanelEast에 배치
		JPanel frameSubPanelEast = new JPanel();
		// infoPanelSize.height = 100;
		frameSubPanelEast.setLayout(new BorderLayout());
		frameSubPanelEast.add(memoPanel, BorderLayout.CENTER);

		Dimension frameSubPanelWestSize = frameSubPanelWest.getPreferredSize();
		frameSubPanelWestSize.width = 500;
		frameSubPanelWest.setPreferredSize(frameSubPanelWestSize);

		// 뒤늦게 추가된 bottom Panel..
		frameBottomPanel = new JPanel();
		frameBottomPanel.setBackground(new Color(255, 182, 193));
		bottomInfo.setFont(new Font("나눔스퀘어라운드 Bold", Font.PLAIN, 16));
		frameBottomPanel.add(bottomInfo);
		SpringLayout springLayout = new SpringLayout();
		springLayout.putConstraint(SpringLayout.WEST, frameSubPanelWest, 0, SpringLayout.WEST, frameBottomPanel);
		springLayout.putConstraint(SpringLayout.SOUTH, frameSubPanelWest, -6, SpringLayout.NORTH, frameBottomPanel);
		springLayout.putConstraint(SpringLayout.SOUTH, frameSubPanelEast, -6, SpringLayout.NORTH, frameBottomPanel);
		springLayout.putConstraint(SpringLayout.NORTH, frameBottomPanel, 372, SpringLayout.NORTH,
				mainFrame.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, frameBottomPanel, 0, SpringLayout.EAST, frameSubPanelEast);
		springLayout.putConstraint(SpringLayout.EAST, frameSubPanelEast, -24, SpringLayout.EAST,
				mainFrame.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, frameBottomPanel, 10, SpringLayout.WEST,
				mainFrame.getContentPane());
		mainFrame.getContentPane().setLayout(springLayout);
		mainFrame.getContentPane().add(frameSubPanelWest);
		mainFrame.getContentPane().add(frameSubPanelEast);
		mainFrame.getContentPane().add(frameBottomPanel);

		infoPanel = new JPanel();
		springLayout.putConstraint(SpringLayout.NORTH, infoPanel, 10, SpringLayout.NORTH, mainFrame.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, infoPanel, -237, SpringLayout.EAST, mainFrame.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, infoPanel, 0, SpringLayout.EAST, mainFrame.getContentPane());
		mainFrame.getContentPane().add(infoPanel);
		infoPanel.setLayout(new BorderLayout());
		Dimension infoPanelSize = infoPanel.getPreferredSize();
		infoPanel.setPreferredSize(infoPanelSize);
		infoClock = new JLabel("", SwingConstants.RIGHT);
		infoClock.setFont(new Font("나눔스퀘어", Font.PLAIN, 17));
		springLayout.putConstraint(SpringLayout.NORTH, infoClock, 34, SpringLayout.SOUTH, infoPanel);
		springLayout.putConstraint(SpringLayout.WEST, infoClock, 598, SpringLayout.WEST, mainFrame.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, infoClock, -24, SpringLayout.EAST, mainFrame.getContentPane());
		mainFrame.getContentPane().add(infoClock);
		infoClock.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

		calOpPanel = new JPanel();
		springLayout.putConstraint(SpringLayout.NORTH, calOpPanel, 0, SpringLayout.NORTH, infoPanel);
		springLayout.putConstraint(SpringLayout.EAST, calOpPanel, -353, SpringLayout.EAST, mainFrame.getContentPane());
		mainFrame.getContentPane().add(calOpPanel);
		calOpPanel.setLayout(new GridBagLayout());
		Dimension calOpPanelSize = calOpPanel.getPreferredSize();
		calOpPanel.setPreferredSize(calOpPanelSize);

		JPanel panel = new JPanel();
		springLayout.putConstraint(SpringLayout.NORTH, frameSubPanelWest, 13, SpringLayout.SOUTH, panel);
		springLayout.putConstraint(SpringLayout.EAST, frameSubPanelWest, 0, SpringLayout.EAST,
				panel);
		springLayout.putConstraint(SpringLayout.EAST, panel, 628, SpringLayout.WEST, mainFrame.getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, panel, -455, SpringLayout.SOUTH, mainFrame.getContentPane());
		panel.setBackground(new Color(255, 182, 193));
		springLayout.putConstraint(SpringLayout.WEST, panel, 10, SpringLayout.WEST, mainFrame.getContentPane());
		mainFrame.getContentPane().add(panel);
		SpringLayout sl_panel = new SpringLayout();
		panel.setLayout(sl_panel);
		lYearBut = new JButton("<<");
		lYearBut.setBorderPainted(false);
		sl_panel.putConstraint(SpringLayout.NORTH, lYearBut, 10, SpringLayout.NORTH, panel);
		lYearBut.setForeground(UIManager.getColor("Button.background"));
		lYearBut.setBackground(new Color(255, 182, 193));
		lYearBut.setFont(new Font("나눔스퀘어 ExtraBold", Font.PLAIN, 17));
		panel.add(lYearBut);
		lYearBut.setToolTipText("Previous Year");
		lMonBut = new JButton("<");
		lMonBut.setBorderPainted(false);
		sl_panel.putConstraint(SpringLayout.EAST, lMonBut, -425, SpringLayout.EAST, panel);
		sl_panel.putConstraint(SpringLayout.EAST, lYearBut, -39, SpringLayout.WEST, lMonBut);
		sl_panel.putConstraint(SpringLayout.NORTH, lMonBut, 0, SpringLayout.NORTH, lYearBut);
		sl_panel.putConstraint(SpringLayout.SOUTH, lMonBut, -7, SpringLayout.SOUTH, panel);
		lMonBut.setForeground(UIManager.getColor("Button.background"));
		lMonBut.setBackground(new Color(255, 182, 193));
		lMonBut.setFont(new Font("나눔스퀘어 ExtraBold", Font.PLAIN, 17));
		panel.add(lMonBut);
		lMonBut.setToolTipText("Previous Month");
		curMMYYYYLab = new JLabel("<html><table width=100><tr><th><font size=5>" + ((calMonth + 1) < 10 ? "&nbsp;" : "")
				+ (calMonth + 1) + " / " + calYear + "</th></tr></table></html>");
		sl_panel.putConstraint(SpringLayout.NORTH, curMMYYYYLab, -2, SpringLayout.NORTH, lYearBut);
		sl_panel.putConstraint(SpringLayout.WEST, curMMYYYYLab, 29, SpringLayout.EAST, lMonBut);
		curMMYYYYLab.setFont(new Font("나눔스퀘어라운드 ExtraBold", Font.PLAIN, 35));
		panel.add(curMMYYYYLab);
		nMonBut = new JButton(">");
		nMonBut.setBorderPainted(false);
		sl_panel.putConstraint(SpringLayout.NORTH, nMonBut, 0, SpringLayout.NORTH, lYearBut);
		sl_panel.putConstraint(SpringLayout.WEST, nMonBut, 31, SpringLayout.EAST, curMMYYYYLab);
		nMonBut.setForeground(UIManager.getColor("Button.background"));
		nMonBut.setBackground(new Color(255, 182, 193));
		nMonBut.setFont(new Font("나눔스퀘어 ExtraBold", Font.PLAIN, 17));
		panel.add(nMonBut);
		nMonBut.setToolTipText("Next Month");
		nYearBut = new JButton(">>");
		nYearBut.setBorderPainted(false);
		sl_panel.putConstraint(SpringLayout.NORTH, nYearBut, 0, SpringLayout.NORTH, lYearBut);
		nYearBut.setForeground(UIManager.getColor("Button.background"));
		nYearBut.setBackground(new Color(255, 182, 193));
		nYearBut.setFont(new Font("나눔스퀘어 ExtraBold", Font.PLAIN, 17));
		panel.add(nYearBut);
		nYearBut.setToolTipText("Next Year");
		todayBut = new JButton("Today");
		sl_panel.putConstraint(SpringLayout.EAST, nYearBut, -29, SpringLayout.WEST, todayBut);
		sl_panel.putConstraint(SpringLayout.NORTH, todayBut, 10, SpringLayout.NORTH, panel);
		todayBut.setBackground(new Color(255, 182, 193));
		sl_panel.putConstraint(SpringLayout.EAST, todayBut, -23, SpringLayout.EAST, panel);
		todayBut.setFont(new Font("나눔스퀘어라운드 ExtraBold", Font.PLAIN, 17));
		panel.add(todayBut);
		todayBut.setToolTipText("Today");
		todayBut.setBorderPainted(false);

		panel_1 = new JPanel();
		panel_1.setBackground(new Color(255, 182, 193));
		springLayout.putConstraint(SpringLayout.NORTH, panel_1, 6, SpringLayout.SOUTH, frameBottomPanel);
		springLayout.putConstraint(SpringLayout.WEST, panel_1, 10, SpringLayout.WEST, mainFrame.getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, panel_1, 183, SpringLayout.SOUTH, frameBottomPanel);
		springLayout.putConstraint(SpringLayout.EAST, panel_1, 0, SpringLayout.EAST, frameSubPanelEast);
		mainFrame.getContentPane().add(panel_1);
		SpringLayout sl_panel_1 = new SpringLayout();
		panel_1.setLayout(sl_panel_1);

		panel_2 = new JPanel();
		panel_2.setBackground(new Color(255, 182, 193));
		sl_panel_1.putConstraint(SpringLayout.NORTH, panel_2, 0, SpringLayout.NORTH, panel_1);
		sl_panel_1.putConstraint(SpringLayout.WEST, panel_2, 0, SpringLayout.WEST, panel_1);
		sl_panel_1.putConstraint(SpringLayout.SOUTH, panel_2, 0, SpringLayout.SOUTH, panel_1);
		sl_panel_1.putConstraint(SpringLayout.EAST, panel_2, -586, SpringLayout.EAST, panel_1);
		panel_2.setForeground(new Color(0, 0, 0));
		panel_1.add(panel_2);

		panel_3 = new JPanel();
		sl_panel_1.putConstraint(SpringLayout.EAST, panel_3, -271, SpringLayout.EAST, panel_1);
		panel_3.setBackground(new Color(255, 182, 193));
		sl_panel_1.putConstraint(SpringLayout.NORTH, panel_3, 0, SpringLayout.NORTH, panel_2);
		sl_panel_1.putConstraint(SpringLayout.WEST, panel_3, 6, SpringLayout.EAST, panel_2);
		sl_panel_1.putConstraint(SpringLayout.SOUTH, panel_3, 0, SpringLayout.SOUTH, panel_2);
		panel_2.setLayout(new GridLayout(0, 1, 0, 0));

		JLabel lblNewLabel_5 = new JLabel("  <\uACF5\uCC44 \uC77C\uC815>  ");
		lblNewLabel_5.setBackground(new Color(255, 255, 255));
		lblNewLabel_5.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_5.setForeground(new Color(255, 69, 0));
		lblNewLabel_5.setFont(new Font("배달의민족 주아", Font.PLAIN, 22));
		panel_2.add(lblNewLabel_5);

		JButton btnNewButton = new JButton("9/11~\uC0C1\uC2DC\uBAA8\uC9D1  \uC6B0\uC544\uD55C\uD615\uC81C");
		btnNewButton.setFont(new Font("배달의민족 주아", Font.PLAIN, 18));
		btnNewButton.setForeground(new Color(0, 0, 0));
		btnNewButton.setBackground(new Color(255, 182, 193));
		panel_2.add(btnNewButton);

		btnNewButton_1 = new JButton("9/10~9/19  KIS\uC815\uBCF4\uD1B5\uC2E0");
		btnNewButton_1.setBackground(new Color(255, 182, 193));
		btnNewButton_1.setFont(new Font("배달의민족 주아", Font.PLAIN, 18));
		panel_2.add(btnNewButton_1);

		button = new JButton("9/6~10/6  (\uC8FC)\uC704\uBA54\uD504");
		button.setBackground(new Color(255, 182, 193));
		button.setFont(new Font("배달의민족 주아", Font.PLAIN, 18));
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {
					String url = "http://www.saramin.co.kr/zf_user/jobs/relay/pop-view?t_ref=calendar&rec_idx=36877825&view_type=public-recruit";
					java.awt.Desktop.getDesktop().browse(java.net.URI.create(url));
				} catch (java.io.IOException e1) {
					System.out.println(e1.getMessage());
				}
			}
		});
		panel_2.add(button);

		btnNewButton_2 = new JButton("9/4~9/17   \uB77C\uC778\uD50C\uB7EC\uC2A4(\uC8FC)");
		btnNewButton_2.setBackground(new Color(255, 182, 193));
		btnNewButton_2.setFont(new Font("배달의민족 주아", Font.PLAIN, 18));
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {
					String url = "http://www.saramin.co.kr/zf_user/jobs/relay/pop-view?t_ref=calendar&rec_idx=36858346&view_type=public-recruit";
					java.awt.Desktop.getDesktop().browse(java.net.URI.create(url));
				} catch (java.io.IOException e1) {
					System.out.println(e1.getMessage());
				}
			}
		});
		panel_2.add(btnNewButton_2);
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {
					String url = "http://www.saramin.co.kr/zf_user/jobs/relay/pop-view?t_ref=calendar&rec_idx=36894905&view_type=public-recruit";
					java.awt.Desktop.getDesktop().browse(java.net.URI.create(url));
				} catch (java.io.IOException e1) {
					System.out.println(e1.getMessage());
				}
			}
		});
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {
					String url = "http://www.saramin.co.kr/zf_user/jobs/relay/pop-view?t_ref=calendar&rec_idx=36896498&view_type=public-recruit";
					java.awt.Desktop.getDesktop().browse(java.net.URI.create(url));
				} catch (java.io.IOException e1) {
					System.out.println(e1.getMessage());
				}
			}

		});
		panel_1.add(panel_3);

		panel_4 = new JPanel();
		sl_panel_1.putConstraint(SpringLayout.WEST, panel_4, 34, SpringLayout.EAST, panel_3);
		sl_panel_1.putConstraint(SpringLayout.EAST, panel_4, 0, SpringLayout.EAST, panel_1);
		panel_4.setBackground(new Color(255, 182, 193));
		sl_panel_1.putConstraint(SpringLayout.NORTH, panel_4, 0, SpringLayout.NORTH, panel_2);
		sl_panel_1.putConstraint(SpringLayout.SOUTH, panel_4, 0, SpringLayout.SOUTH, panel_2);
		

		lblNewLabel_7 = new JLabel("");
		
		btnNewButton_4 = new JButton("\uC0AC\uBB34\uC790\uB3D9\uD654");
		btnNewButton_4.setBackground(new Color(255, 182, 193));
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					String url = "http://www.q-net.or.kr/crf005.do?id=crf00503&jmCd=2193&gSite=Q&gId";
					java.awt.Desktop.getDesktop().browse(java.net.URI.create(url));
				} catch (java.io.IOException e1) {
					System.out.println(e1.getMessage());
				}
			}
		});
		panel_3.setLayout(new GridLayout(0, 2, 0, 0));
		
		lblNewLabel_1 = new JLabel("<\uAD6D\uAC00 \uC2DC");
		lblNewLabel_1.setBackground(new Color(255, 255, 255));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1.setForeground(new Color(30, 144, 255));
		lblNewLabel_1.setFont(new Font("배달의민족 주아", Font.PLAIN, 22));
		panel_3.add(lblNewLabel_1);
		
		lblNewLabel = new JLabel("\uD5D8 \uC77C\uC815>");
		lblNewLabel.setFont(new Font("배달의민족 주아", Font.PLAIN, 22));
		lblNewLabel.setForeground(new Color(30, 144, 255));
		panel_3.add(lblNewLabel);
		btnNewButton_4.setFont(new Font("배달의민족 주아", Font.PLAIN, 19));
		panel_3.add(btnNewButton_4);
		
		btnNewButton_3 = new JButton("\uC815\uBCF4\uCC98\uB9AC\uAE30\uC0AC");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					String url = "http://www.q-net.or.kr/crf005.do?id=crf00503&jmCd=1320";
					java.awt.Desktop.getDesktop().browse(java.net.URI.create(url));
				} catch (java.io.IOException e1) {
					System.out.println(e1.getMessage());
				}
				
				
			}
		});
		btnNewButton_3.setBackground(new Color(255, 182, 193));
		btnNewButton_3.setFont(new Font("배달의민족 주아", Font.PLAIN, 19));
		panel_3.add(btnNewButton_3);
		
		btnNewButton_5 = new JButton("ADSP");
		btnNewButton_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				try {
					String url = "http://www.dbguide.net/da.db?cmd=snb_adsp_1";
					java.awt.Desktop.getDesktop().browse(java.net.URI.create(url));
				} catch (java.io.IOException e1) {
					System.out.println(e1.getMessage());
				}
				
			}
		});
		btnNewButton_5.setBackground(new Color(255, 182, 193));
		btnNewButton_5.setFont(new Font("배달의민족 주아", Font.PLAIN, 19));
		panel_3.add(btnNewButton_5);
		
		btnSqlD = new JButton("SQL D");
		btnSqlD.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					String url = "http://www.dbguide.net/da.db?cmd=snb_sqld_1";
					java.awt.Desktop.getDesktop().browse(java.net.URI.create(url));
				} catch (java.io.IOException e1) {
					System.out.println(e1.getMessage());
				}
			}
		});
		btnSqlD.setBackground(new Color(255, 182, 193));
		btnSqlD.setFont(new Font("배달의민족 주아", Font.PLAIN, 19));
		panel_3.add(btnSqlD);
		panel_1.add(panel_4);
		
		btn_daylog = new JButton("DAY LOG");
		btn_daylog.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Log.main(null);
				mainFrame.dispose();
			}
		});
		btn_daylog.setBorderPainted(false);
		btn_daylog.setBackground(new Color(255, 255, 255));
		btn_daylog.setIcon(new ImageIcon(sPath+"\\img\\student.png"));
		btn_daylog.setFont(new Font("배달의민족 주아", Font.PLAIN, 25));
		
		btn_score = new JButton(" \uC131\uC801 \uAD00\uB9AC");
		btn_score.setBackground(new Color(255, 255, 255));
		btn_score.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PolylineBarChart.main(null);
				mainFrame.dispose();
			}
		});
		btn_score.setBorderPainted(false);
		btn_score.setIcon(new ImageIcon(sPath+"\\img\\organize.png"));
		btn_score.setFont(new Font("배달의민족 주아", Font.PLAIN, 26));
		btn_score.setBorderPainted(false);
		
		panel_4.setLayout(new GridLayout(0, 1, 0, 0));
		panel_4.add(btn_daylog);
		panel_4.add(btn_score);
		
		String[] time = {"24:00 ~ 01:00","01:00 ~ 02:00","02:00 ~ 03:00","03:00 ~ 04:00","04:00 ~ 05:00","05:00 ~ 06:00","06:00 ~ 07:00","07:00 ~ 08:00","08:00 ~ 09:00","09:00 ~ 10:00","10:00 ~ 11:00",
	            "11:00 ~ 12:00","12:00 ~ 13:00","13:00 ~ 14:00","14:00 ~ 15:00","15:00 ~ 16:00","16:00 ~ 17:00","17:00 ~ 18:00","18:00 ~ 19:00","19:00 ~ 20:00","20:00 ~ 21:00","21:00 ~ 22:00","22:00 ~ 23:00"
	,"23:00 ~ 24:00"};
	      comboBox = new JComboBox(time);
	      comboBox.setFont(new Font("나눔스퀘어라운드 Light", Font.PLAIN, 15));
	      comboBox.setBackground(new Color(255, 255, 255));
	      springLayout.putConstraint(SpringLayout.SOUTH, comboBox, -494, SpringLayout.SOUTH, mainFrame.getContentPane());
	      springLayout.putConstraint(SpringLayout.NORTH, frameSubPanelEast, 6, SpringLayout.SOUTH, comboBox);
	      comboBox.addActionListener(new ActionListener() {
	         public void actionPerformed(ActionEvent arg0) {
	            select_time = comboBox.getSelectedItem().toString();
	               memoArea.setText(select_time);
	            //System.out.println(select_time);
	            
	         }
	         
	      });
		springLayout.putConstraint(SpringLayout.WEST, comboBox, 0, SpringLayout.WEST, frameSubPanelEast);
		springLayout.putConstraint(SpringLayout.EAST, comboBox, 0, SpringLayout.EAST, frameSubPanelEast);
		mainFrame.getContentPane().add(comboBox);
		
		lblNewLabel_2 = new JLabel("STUDY LIFE");
		springLayout.putConstraint(SpringLayout.NORTH, panel, 19, SpringLayout.SOUTH, lblNewLabel_2);
		springLayout.putConstraint(SpringLayout.SOUTH, lblNewLabel_2, -520, SpringLayout.SOUTH, mainFrame.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, lblNewLabel_2, 27, SpringLayout.WEST, mainFrame.getContentPane());
		lblNewLabel_2.setFont(new Font("나눔스퀘어 ExtraBold", Font.PLAIN, 40));
		mainFrame.getContentPane().add(lblNewLabel_2);
		
		lblNewLabel_3 = new JLabel("");
		springLayout.putConstraint(SpringLayout.WEST, lblNewLabel_3, 6, SpringLayout.EAST, lblNewLabel_2);
		springLayout.putConstraint(SpringLayout.SOUTH, lblNewLabel_3, 0, SpringLayout.SOUTH, lblNewLabel_2);
		lblNewLabel_3.setIcon(new ImageIcon(sPath+"\\img\\cardiogram.png"));
		mainFrame.getContentPane().add(lblNewLabel_3);
		
		btnNewButton_6 = new JButton("");
		btnNewButton_6.setForeground(new Color(255, 182, 193));
		btnNewButton_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Main.main(null);
				mainFrame.dispose();
			}
		});
		springLayout.putConstraint(SpringLayout.NORTH, btnNewButton_6, -32, SpringLayout.NORTH, infoClock);
		springLayout.putConstraint(SpringLayout.WEST, btnNewButton_6, -53, SpringLayout.EAST, mainFrame.getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, btnNewButton_6, -5, SpringLayout.NORTH, infoClock);
		springLayout.putConstraint(SpringLayout.EAST, btnNewButton_6, -24, SpringLayout.EAST, mainFrame.getContentPane());
		btnNewButton_6.setBackground(new Color(255, 182, 193));
		btnNewButton_6.setIcon(new ImageIcon(sPath+"\\img\\close.png"));
		btnNewButton_6.setBorderPainted(false);
		btnNewButton_6.setContentAreaFilled(false);
		btnNewButton_6.setFocusPainted(false);
		mainFrame.getContentPane().add(btnNewButton_6);
		todayBut.addActionListener(lForCalOpButtons);
		nYearBut.addActionListener(lForCalOpButtons);
		nMonBut.addActionListener(lForCalOpButtons);
		lMonBut.addActionListener(lForCalOpButtons);
		lYearBut.addActionListener(lForCalOpButtons);
		mainFrame.setVisible(true);

		focusToday(); // 현재 날짜에 focus를 줌 (mainFrame.setVisible(true) 이후에 배치해야함)

		// Thread 작동(시계, bottomMsg 일정시간후 삭제)
		ThreadConrol threadCnl = new ThreadConrol();
		threadCnl.start();
	}

	private void focusToday() {
		if (today.get(Calendar.DAY_OF_WEEK) == 1)
			dateButs[today.get(Calendar.WEEK_OF_MONTH)][today.get(Calendar.DAY_OF_WEEK) - 1].requestFocusInWindow();
		else
			dateButs[today.get(Calendar.WEEK_OF_MONTH) - 1][today.get(Calendar.DAY_OF_WEEK) - 1].requestFocusInWindow();
	}

	private void readMemo() {
		try {
			File f = new File("MemoData/" + calYear + ((calMonth + 1) < 10 ? "0" : "") + (calMonth + 1)
					+ (calDayOfMon < 10 ? "0" : "") + calDayOfMon + ".txt");
			if (f.exists()) {
				BufferedReader in = new BufferedReader(
						new FileReader("MemoData/" + calYear + ((calMonth + 1) < 10 ? "0" : "") + (calMonth + 1)
								+ (calDayOfMon < 10 ? "0" : "") + calDayOfMon + ".txt"));
				String memoAreaText = new String();
				while (true) {
					String tempStr = in.readLine();
					if (tempStr == null)
						break;
					memoAreaText = memoAreaText + tempStr + System.getProperty("line.separator");
				}
				memoArea.setText(memoAreaText);
				in.close();
			} else
				memoArea.setText("");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void showCal() {
		for (int i = 0; i < CAL_HEIGHT; i++) {
			for (int j = 0; j < CAL_WIDTH; j++) {
				String fontColor = "black";
				if (j == 0)
					fontColor = "red";
				else if (j == 6)
					fontColor = "blue";

				File f = new File("MemoData/" + calYear + ((calMonth + 1) < 10 ? "0" : "") + (calMonth + 1)
						+ (calDates[i][j] < 10 ? "0" : "") + calDates[i][j] + ".txt");
				if (f.exists()) {
					dateButs[i][j]
							.setText("<html><b><font color=" + fontColor + ">" + calDates[i][j] + "</font></b></html>");
				} else
					dateButs[i][j].setText("<html><font color=" + fontColor + ">" + calDates[i][j] + "</font></html>");

				JLabel todayMark = new JLabel("<html><font color=green>*</html>");
				dateButs[i][j].removeAll();
				if (calMonth == today.get(Calendar.MONTH) && calYear == today.get(Calendar.YEAR)
						&& calDates[i][j] == today.get(Calendar.DAY_OF_MONTH)) {
					dateButs[i][j].add(todayMark);
					dateButs[i][j].setToolTipText("Today");
				}

				if (calDates[i][j] == 0)
					dateButs[i][j].setVisible(false);
				else
					dateButs[i][j].setVisible(true);
			}
		}
	}

	private class ListenForCalOpButtons implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == todayBut) {
				setToday();
				lForDateButs.actionPerformed(e);
				focusToday();
			} else if (e.getSource() == lYearBut)
				moveMonth(-12);
			else if (e.getSource() == lMonBut)
				moveMonth(-1);
			else if (e.getSource() == nMonBut)
				moveMonth(1);
			else if (e.getSource() == nYearBut)
				moveMonth(12);

			curMMYYYYLab.setText("<html><table width=100><tr><th><font size=5>" + ((calMonth + 1) < 10 ? "&nbsp;" : "")
					+ (calMonth + 1) + " / " + calYear + "</th></tr></table></html>");
			showCal();
		}
	}

	private class listenForDateButs implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			int k = 0, l = 0;
			for (int i = 0; i < CAL_HEIGHT; i++) {
				for (int j = 0; j < CAL_WIDTH; j++) {
					if (e.getSource() == dateButs[i][j]) {
						k = i;
						l = j;
					}
				}
			}

			if (!(k == 0 && l == 0))
				calDayOfMon = calDates[k][l]; // today버튼을 눌렀을때도 이 actionPerformed함수가 실행되기 때문에 넣은 부분

			cal = new GregorianCalendar(calYear, calMonth, calDayOfMon);

			selectedDate.setText(calYear + "/" + (calMonth + 1) + "/" + calDayOfMon);

			readMemo();
		}
	}

	private class ThreadConrol extends Thread {
		public void run() {
			boolean msgCntFlag = false;
			int num = 0;
			String curStr = new String();
			while (true) {
				try {
					today = Calendar.getInstance();
					String amPm = (today.get(Calendar.AM_PM) == 0 ? "AM" : "PM");
					String hour;
					if (today.get(Calendar.HOUR) == 0)
						hour = "12";
					else if (today.get(Calendar.HOUR) == 12)
						hour = " 0";
					else
						hour = (today.get(Calendar.HOUR) < 10 ? " " : "") + today.get(Calendar.HOUR);
					String min = (today.get(Calendar.MINUTE) < 10 ? "0" : "") + today.get(Calendar.MINUTE);
					String sec = (today.get(Calendar.SECOND) < 10 ? "0" : "") + today.get(Calendar.SECOND);
					infoClock.setText(amPm + " " + hour + ":" + min + ":" + sec);

					sleep(1000);
					String infoStr = bottomInfo.getText();

					if (infoStr != " " && (msgCntFlag == false || curStr != infoStr)) {
						num = 5;
						msgCntFlag = true;
						curStr = infoStr;
					} else if (infoStr != " " && msgCntFlag == true) {
						if (num > 0)
							num--;
						else {
							msgCntFlag = false;
							bottomInfo.setText(" ");
						}
					}
				} catch (InterruptedException e) {
					System.out.println("Thread:Error");
				}
			}
		}
	}
}