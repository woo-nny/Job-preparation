

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.ImageObserver;
import java.text.AttributedCharacterIterator;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
   
class DrawingPiePanel extends JPanel {
   
   
   
   int num_study; // 공부 시간
   int num_eat; // 식사 시간
   int num_rest; // 휴식 시간
   int num_sleep; // 수면 시간
   int num_etc; // 기타
   
   void setNumbers(int num1, int num2, int num3, int num4, int num5) {
      num_study = num1;
      num_eat = num2;
      num_rest = num3;
      num_sleep = num4;
      num_etc = num5;
   }

public void paint(Graphics g) {
      g.clearRect(0, 0, getWidth(),getHeight());
      //값이 입력되지않았으면 return;
      if ((num_study < 0) || (num_eat < 0) || (num_rest < 0) || (num_sleep < 0) || (num_etc < 0))
         return;
      //전체 합을 구한다
      int total = num_study + num_eat + num_rest + num_sleep+num_etc;
      if (total == 0)
         return;
      // 전체에서의 비중을 구함.
      //arc5 = 전체 - (arc1+arc2+arc3+arc4)로 구함
      int arc1 = (int) 360.0 * num_study / total;
      int arc2 = (int) 360.0 * num_eat / total;
      int arc3 = (int) 360.0 * num_rest / total;
      int arc4 = (int) 360.0 * num_sleep / total;
      g.setColor(Color.YELLOW);//색상지정
      g.fillArc(50, 20, 200, 200, 0, arc1);//(x축,y축,반지름,반지름,시작각,끝각) - 원호를 그림
      g.setColor(Color.RED);//색상지정
      g.fillArc(50, 20, 200, 200, arc1,arc2);//(x축,y축,반지름,반지름,시작각,끝각) - 원호를 그림
      g.setColor(Color.BLUE);//색상지정
      g.fillArc(50, 20, 200, 200, arc1 + arc2, arc3);//(x축,y축,반지름,반지름,시작각,끝각) - 원호를 그림
      g.setColor(Color.GREEN);//색상지정
      g.fillArc(50, 20, 200, 200, arc1 + arc2+arc3, arc4);//(x축,y축,반지름,반지름,시작각,끝각) - 원호를 그림
      g.setColor(Color.CYAN);//색상지정
      g.fillArc(50, 20, 200, 200, arc1 + arc2 + arc3+arc4, 360 - (arc1 + arc2 + arc3+arc4));//(x축,y축,반지름,반지름,시작각,끝각) - 원호를 그림
      g.setColor(Color.BLACK);//색상지정
      g.setFont(new Font("배달의민족 주아", Font.PLAIN, 17));//폰트 지정
      g.drawString(" ====범례==== ", 300, 130);//범례(legend)
      g.drawString(" 공부 시간 : 노랑", 300, 150);//범례(legend)
      g.drawString(" 식사 시간 : 빨강", 300, 170);//범례(legend)
      g.drawString(" 휴식 시간 : 파랑", 300, 190);//범례(legend)
      g.drawString(" 수면 시간 : 하늘", 300, 210);//범례(legend)
      g.drawString(" 기타 : 초록", 300, 230);//범례(legend)
   }
   //숫자값 입력받는 메소드

}
public class Graph implements ActionListener {
   JTextField text1, text2, text3,text4;
   DrawingPiePanel drawingPanel;
   JFrame frame;
   private DailyVO vo;
   private JButton button;
   private JButton button1;
   public void setDaily(DailyVO vo) {
      this.vo = vo;
      int numa = vo.getSum_study();
      int numb = vo.getSum_eat();
      int numc = vo.getSum_rest();
      int numd = vo.getSum_sleep();
      int nume = vo.getSum_etc();
//      System.out.println(vo.toString());
//      test listener
//        = new test(drawingPanel);
//      
//      
//      drawingPanel = new DrawingPiePanel();
//      drawingPanel.setNumbers(numa,numb, numc, numd, nume);
//      drawingPanel.repaint();
//      
//      //리스너 부착
//      button.addActionListener(listener);
//      frame.pack();
//      frame.setVisible(true);
//      System.out.println("호호호호출1");
//      System.out.println("test gui 실행합니다.");
//      System.out.println(vo.toString());
   }
   
   
   
   /**
    * Launch the application.
    */
//   public static void main(String[] args) {
//      EventQueue.invokeLater(new Runnable() {
//         public void run() {
//            try {
//               test window = new test();
//               window.frame.setVisible(true);
//            } catch (Exception e) {
//               e.printStackTrace();
//            }
//         }
//      });
//   }

   /**
    * Create the application.
    */
   public Graph(DailyVO vo) {
      this.vo = vo;
      initialize();
   }
   

   // 생성자
   public Graph(DrawingPiePanel drawingPanel, DailyVO vo) {
//      this.text1 = text1;
//      this.text2 = text2;
//      this.text3 = text3;
//      this.text4 = text4;
      this.vo = vo;
      this.drawingPanel = drawingPanel;
   }
   // 메소드


   /**
    * Initialize the contents of the frame.
    */
   private void initialize() {
      //System.out.println("didldjfsaldf "+ vo.toString());
      frame = new JFrame("하루일과 그래프 확인");
      frame.setLocation(400, 200);
      frame.setBounds(100, 100, 500, 355);
      frame.setPreferredSize(new Dimension(600, 400));
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      SpringLayout springLayout = new SpringLayout();
      frame.getContentPane().setLayout(springLayout);
      DrawingPiePanel drawingPanel = new DrawingPiePanel();
      springLayout.putConstraint(SpringLayout.NORTH, drawingPanel, 0, SpringLayout.NORTH, frame.getContentPane());
      springLayout.putConstraint(SpringLayout.WEST, drawingPanel, 0, SpringLayout.WEST, frame.getContentPane());
      springLayout.putConstraint(SpringLayout.SOUTH, drawingPanel, -49, SpringLayout.SOUTH, frame.getContentPane());
      springLayout.putConstraint(SpringLayout.EAST, drawingPanel, 574, SpringLayout.WEST, frame.getContentPane());
      Container contentPane = frame.getContentPane();

      contentPane.add(drawingPanel, BorderLayout.CENTER);
      JPanel controlPanel = new JPanel();
      springLayout.putConstraint(SpringLayout.NORTH, controlPanel, 6, SpringLayout.SOUTH, drawingPanel);
      springLayout.putConstraint(SpringLayout.WEST, controlPanel, 46, SpringLayout.WEST, frame.getContentPane());
//      JTextField text1 = new JTextField(3);
//      JTextField text2 = new JTextField(3);
//      JTextField text3 = new JTextField(3);
//      JTextField text4 = new JTextField(3);
      button = new JButton("하루일과 그래프 확인");
      button1 = new JButton("나가기");
//      controlPanel.add(new JLabel("공부(분)"));
//      controlPanel.add(text1);
//      controlPanel.add(new JLabel("잠 시간(분)"));
//      controlPanel.add(text2);
//      controlPanel.add(new JLabel("휴식 시간(분)"));
//      controlPanel.add(text3);
//      controlPanel.add(new JLabel("기타"));
//      controlPanel.add(text4);
      controlPanel.add(button);
      controlPanel.add(button1);
      
      
      //컨테이너에 컴포넌트 그룹 부착
      contentPane.add(controlPanel,BorderLayout.CENTER);
      frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
      
       //리스너 객체 생성
      Graph listener
        = new Graph(drawingPanel, vo);
      
      
      
//      drawingPanel = new DrawingPiePanel();
//      drawingPanel.setNumbers(numa,numb, numc, numd, nume);
//      
//      drawingPanel.repaint();
//      
//      //리스너 부착
      button.addActionListener(listener);
      frame.pack();
      frame.setVisible(true);
      
      button1.addActionListener(new ActionListener() {

          public void actionPerformed(ActionEvent e) {
    			MemoCalendar.main(null);
    			frame.dispose();
        	  
        	  
          }
      });
   }
   
   
   @Override
   public void actionPerformed(ActionEvent e) {
//      try {
//         int typeO = Integer.parseInt(text1.getText());// 숫자형문자열 ->숫자
//         int typeA = Integer.parseInt(text2.getText());// 숫자형문자열 ->숫자
//         int typeB = Integer.parseInt(text3.getText());// 숫자형문자열 ->숫자
//         int typeAB = Integer.parseInt(text4.getText());// 숫자형문자열 ->숫자
         int numa = vo.getSum_study();
         int numb = vo.getSum_eat();
         int numc = vo.getSum_rest();
         int numd = vo.getSum_sleep();
         int nume = vo.getSum_etc();
//         System.out.println("후");
         drawingPanel.setNumbers(numa,numb, numc, numd, nume);
         
         drawingPanel.repaint();
         // 호출시 마다 paint()메소드를 호출하여 그림을 다시 그리는 메소드
//      } catch (NumberFormatException nfe) {
//         JOptionPane.showMessageDialog(drawingPanel,
//                "잘못된 숫자 표맷입니다", 
//                "에러 메세지", 
//                JOptionPane.ERROR_MESSAGE);
//      }
   }

}