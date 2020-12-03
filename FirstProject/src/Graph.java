

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
   
   
   
   int num_study; // ���� �ð�
   int num_eat; // �Ļ� �ð�
   int num_rest; // �޽� �ð�
   int num_sleep; // ���� �ð�
   int num_etc; // ��Ÿ
   
   void setNumbers(int num1, int num2, int num3, int num4, int num5) {
      num_study = num1;
      num_eat = num2;
      num_rest = num3;
      num_sleep = num4;
      num_etc = num5;
   }

public void paint(Graphics g) {
      g.clearRect(0, 0, getWidth(),getHeight());
      //���� �Էµ����ʾ����� return;
      if ((num_study < 0) || (num_eat < 0) || (num_rest < 0) || (num_sleep < 0) || (num_etc < 0))
         return;
      //��ü ���� ���Ѵ�
      int total = num_study + num_eat + num_rest + num_sleep+num_etc;
      if (total == 0)
         return;
      // ��ü������ ������ ����.
      //arc5 = ��ü - (arc1+arc2+arc3+arc4)�� ����
      int arc1 = (int) 360.0 * num_study / total;
      int arc2 = (int) 360.0 * num_eat / total;
      int arc3 = (int) 360.0 * num_rest / total;
      int arc4 = (int) 360.0 * num_sleep / total;
      g.setColor(Color.YELLOW);//��������
      g.fillArc(50, 20, 200, 200, 0, arc1);//(x��,y��,������,������,���۰�,����) - ��ȣ�� �׸�
      g.setColor(Color.RED);//��������
      g.fillArc(50, 20, 200, 200, arc1,arc2);//(x��,y��,������,������,���۰�,����) - ��ȣ�� �׸�
      g.setColor(Color.BLUE);//��������
      g.fillArc(50, 20, 200, 200, arc1 + arc2, arc3);//(x��,y��,������,������,���۰�,����) - ��ȣ�� �׸�
      g.setColor(Color.GREEN);//��������
      g.fillArc(50, 20, 200, 200, arc1 + arc2+arc3, arc4);//(x��,y��,������,������,���۰�,����) - ��ȣ�� �׸�
      g.setColor(Color.CYAN);//��������
      g.fillArc(50, 20, 200, 200, arc1 + arc2 + arc3+arc4, 360 - (arc1 + arc2 + arc3+arc4));//(x��,y��,������,������,���۰�,����) - ��ȣ�� �׸�
      g.setColor(Color.BLACK);//��������
      g.setFont(new Font("����ǹ��� �־�", Font.PLAIN, 17));//��Ʈ ����
      g.drawString(" ====����==== ", 300, 130);//����(legend)
      g.drawString(" ���� �ð� : ���", 300, 150);//����(legend)
      g.drawString(" �Ļ� �ð� : ����", 300, 170);//����(legend)
      g.drawString(" �޽� �ð� : �Ķ�", 300, 190);//����(legend)
      g.drawString(" ���� �ð� : �ϴ�", 300, 210);//����(legend)
      g.drawString(" ��Ÿ : �ʷ�", 300, 230);//����(legend)
   }
   //���ڰ� �Է¹޴� �޼ҵ�

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
//      //������ ����
//      button.addActionListener(listener);
//      frame.pack();
//      frame.setVisible(true);
//      System.out.println("ȣȣȣȣ��1");
//      System.out.println("test gui �����մϴ�.");
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
   

   // ������
   public Graph(DrawingPiePanel drawingPanel, DailyVO vo) {
//      this.text1 = text1;
//      this.text2 = text2;
//      this.text3 = text3;
//      this.text4 = text4;
      this.vo = vo;
      this.drawingPanel = drawingPanel;
   }
   // �޼ҵ�


   /**
    * Initialize the contents of the frame.
    */
   private void initialize() {
      //System.out.println("didldjfsaldf "+ vo.toString());
      frame = new JFrame("�Ϸ��ϰ� �׷��� Ȯ��");
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
      button = new JButton("�Ϸ��ϰ� �׷��� Ȯ��");
      button1 = new JButton("������");
//      controlPanel.add(new JLabel("����(��)"));
//      controlPanel.add(text1);
//      controlPanel.add(new JLabel("�� �ð�(��)"));
//      controlPanel.add(text2);
//      controlPanel.add(new JLabel("�޽� �ð�(��)"));
//      controlPanel.add(text3);
//      controlPanel.add(new JLabel("��Ÿ"));
//      controlPanel.add(text4);
      controlPanel.add(button);
      controlPanel.add(button1);
      
      
      //�����̳ʿ� ������Ʈ �׷� ����
      contentPane.add(controlPanel,BorderLayout.CENTER);
      frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
      
       //������ ��ü ����
      Graph listener
        = new Graph(drawingPanel, vo);
      
      
      
//      drawingPanel = new DrawingPiePanel();
//      drawingPanel.setNumbers(numa,numb, numc, numd, nume);
//      
//      drawingPanel.repaint();
//      
//      //������ ����
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
//         int typeO = Integer.parseInt(text1.getText());// ���������ڿ� ->����
//         int typeA = Integer.parseInt(text2.getText());// ���������ڿ� ->����
//         int typeB = Integer.parseInt(text3.getText());// ���������ڿ� ->����
//         int typeAB = Integer.parseInt(text4.getText());// ���������ڿ� ->����
         int numa = vo.getSum_study();
         int numb = vo.getSum_eat();
         int numc = vo.getSum_rest();
         int numd = vo.getSum_sleep();
         int nume = vo.getSum_etc();
//         System.out.println("��");
         drawingPanel.setNumbers(numa,numb, numc, numd, nume);
         
         drawingPanel.repaint();
         // ȣ��� ���� paint()�޼ҵ带 ȣ���Ͽ� �׸��� �ٽ� �׸��� �޼ҵ�
//      } catch (NumberFormatException nfe) {
//         JOptionPane.showMessageDialog(drawingPanel,
//                "�߸��� ���� ǥ���Դϴ�", 
//                "���� �޼���", 
//                JOptionPane.ERROR_MESSAGE);
//      }
   }

}