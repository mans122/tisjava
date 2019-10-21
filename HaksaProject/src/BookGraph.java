import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.swing.*;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import org.jfree.util.Rotation;

//isFirst때문에 추가하고 새로 가져와도 값 갱신이안됌
public class BookGraph extends JPanel {
	static ChartPanel chartPanel;
	static JPanel jp = new JPanel();
	static ArrayList<String> deptName = new ArrayList<>();
	static ArrayList<Integer> deptCount = new ArrayList<>();
	static int sum;

	static ArrayList<String> studentId = new ArrayList<>();
	static ArrayList<String> studentName = new ArrayList<>();
	static ArrayList<Integer> studentCount = new ArrayList<>();
	static int sum2;

	static ArrayList<String> bookName = new ArrayList<>();
	static ArrayList<Integer> bookCount = new ArrayList<>();
	static int sum3;
	
	static ArrayList<String> dateYear = new ArrayList<>();
	static ArrayList<String> dateMonth = new ArrayList<>();
	static ArrayList<Integer> dateCount = new ArrayList<>();
	static int sum4;
	static int isFirst = 0;
	ResultSet rs = null;
	static Color[] color = new Color[10];
	JRadioButton  deptRb = new JRadioButton("학과별");
	JRadioButton  studentRb = new JRadioButton("학생별");
	JRadioButton  bookRb = new JRadioButton("도서별");
	JRadioButton  dateRb = new JRadioButton("월별");
	JRadioButton dept = new JRadioButton();
	JRadioButton student = new JRadioButton();
	JRadioButton book = new JRadioButton();
	JRadioButton date = new JRadioButton();
	CenterPanel cp = new CenterPanel(); // CenterPanel클래스로 만든 메인프레임 가운데 붙일 패널 cp생성
	NorthPanel np= new NorthPanel();	//NotrhPanel클래스로 만든 위에붙일 패널 np 생성
	String query;
	public BookGraph() {
		//처음 호출됐으면 컬러값을 넣어준다.
		if(isFirst==0) {
			color[0]= new Color(239,86,45);
			color[1]= new Color(246,210,88);
			color[2]= new Color(239,206,197);
			color[3]= new Color(151,213,224);
			color[4]= new Color(12,76,138);
			color[5]= new Color(85,135,162);
			color[6]= new Color(209,175,148);
			color[7]= new Color(136,177,75);
			color[8]= new Color(92,113,72);
			color[9]= new Color(209,48,118);
			isFirst++;
		}
		
		//ArrayList에 저장하기때문에 BookGraph가 호출될때마다 누적되어 쌓여서 코드가 꼬이기때문에 호출할때마다 ArrayList를 초기화해준다
		deptName.clear();
		deptCount.clear();

		studentId.clear();
		studentName.clear();
		studentCount.clear();

		bookName.clear();
		bookCount.clear();

		dateYear.clear();
		dateMonth.clear();
		dateCount.clear();
		//======================================================================================================================================================
		
		try{
			//학과별로 분류하기위한 내용
			rs = DBManager.stmt.executeQuery("select dept, count(*) as count from (select s.dept, br.rdate from student s, books2 b, bookRent2 br"
					+" where br.id=s.id and br.bookNo=b.no) group by dept order by count desc");
			int i=0;
			sum=0;
			//==========================================================================================================================================================

			//학과별로 BookRent 테이블에서 검색해서 나오는 학과,학과별 총합을 구해줌
			while(rs.next()) {
				deptName.add(i,rs.getString("dept"));
				deptCount.add(i,rs.getInt("count"));
				sum+=deptCount.get(i);
				i++;
			}
			//==========================================================================================================================================================
			
			//학생별 값 구해서 넣어주는코드,대여하는 학생이 수백 수천일수도 있으므로 상위 5명만 구해준다.
			rs = DBManager.stmt.executeQuery("select id,name, count(*) count"
					+" from(select br.id id,name,b.no no,b.title title, br.rdate from student s, books2 b, bookRent2 br where br.id=s.id and br.bookNo=b.no)"
					+" group by id,name order by count desc");
			int i2 = 0;
			sum2=0;
			while(rs.next()) {
				if(i2>4) {
					break;
				}
				studentId.add(i2,rs.getString("id"));
				studentName.add(i2,rs.getString("name"));
				studentCount.add(i2,rs.getInt("count"));
				sum2+=studentCount.get(i2);
				i2++;
			}
			//==========================================================================================================================================================================

			//도서별 상위 5종류만 구해준다.
			rs = DBManager.stmt.executeQuery("select title, count(*) count from(select br.id id,name,b.no no,b.title title, br.rdate from student s,"
					+" books2 b, bookRent2 br where br.id=s.id and br.bookNo=b.no) group by title order by count desc");
			int i3 = 0;
			sum3=0;
			while(rs.next()) {
				if(i3==5) {
					break;
				}
				bookName.add(i3,rs.getString("title"));
				bookCount.add(i3,rs.getInt("count"));
				sum3+=bookCount.get(i3);
				i3++;
			}
			//==========================================================================================================================================================================

			//대여기록이 많은 년월을 구해준다.
			rs = DBManager.stmt.executeQuery("select year, month, count(*) count from(select substr(br.rentno,0,4) year,substr(br.rentno,5,2) month from student s, books2 b, bookRent2 br where br.id=s.id(+) and br.bookNo=b.no)" + 
					" group by year,month order by count desc");
			int i4 = 0;
			sum4=0;
			while(rs.next()) {
				if(i4>4) {
					break;
				}
				dateYear.add(i4,rs.getString("year"));
				dateMonth.add(i4,rs.getString("month"));
				dateCount.add(i4,rs.getInt("count"));
				sum4+=dateCount.get(i4);
				i4++;
			}
			//============================================================================================================================================================================

		}catch(Exception e){
			e.printStackTrace();
		}
		setLayout(new BorderLayout()); // 패널 정렬을 BorderLayout으로
		np.setLayout(new FlowLayout(FlowLayout.CENTER,20,0));//np패널은 FlowLayout으로 정렬
		add(cp,BorderLayout.CENTER); //CENTER에 cp패널 올림
		add(np,BorderLayout.NORTH);//NORTH에 np패널 올림
		setBackground(Color.LIGHT_GRAY);
		setOpaque(false);
		setSize(600, 500); // 프레임 사이즈 지정
		setVisible(true); // 프레임을 보이게 함
	}	
	//======================================================================================================================================================
	//BorderLayout.NORTH 패널 생성 및 들어갈 내용 작성
	class NorthPanel extends JPanel{
		public NorthPanel() {
			MyActionListener ma = new MyActionListener();
			ButtonGroup bg = new ButtonGroup();
			JButton bt = new JButton("3D");
			JButton bt22 = new JButton("월별");
			JButton bt33 = new JButton("연도별");
			bt.setPreferredSize(new Dimension(50,25));
			bt22.setPreferredSize(new Dimension(50,25));
			bt33.setPreferredSize(new Dimension(100,25));
			//			라디오 버튼을 생성하고 버튼그룹 bg에 모두 올려서 이쁘게 정렬
			bg.add(studentRb);
			bg.add(deptRb);
			bg.add(bookRb);
			bg.add(dateRb);
			bg.add(student);
			bg.add(dept);
			bg.add(book);
			bg.add(date);
			add(deptRb);
			add(studentRb);
			add(bookRb);
			add(dateRb);
			//버튼 투명하게 해서 뒷배경 보이게
			deptRb.setOpaque(false);
			studentRb.setOpaque(false);
			bookRb.setOpaque(false);
			dateRb.setOpaque(false);
			//--------------------------

			//모두 아이템리스너에 올려줌
			deptRb.addItemListener(ma);
			studentRb.addItemListener(ma);
			bookRb.addItemListener(ma);
			dateRb.addItemListener(ma);

			ButtonListener bt2 = new ButtonListener();
			add(bt);
			bt.addActionListener(bt2);
			setOpaque(false); // NorthPanel 투명하게해서 뒷배경보이게

		}
	}
	//============================================================================================================================================================
	//CenterPanel 클래스 작성
	class CenterPanel extends JPanel{
		ArrayList<Integer> deptGak = new ArrayList<>();
		ArrayList<Integer> studentGak = new ArrayList<>();
		ArrayList<Integer> bookGak = new ArrayList<>();
		ArrayList<Integer> dateGak = new ArrayList<>();
		public void paintComponent(Graphics g) {
			//학과별 차트 생성코드
			//선택한 버튼에 대한 실행문을 여기서 작성
			if(deptRb.isSelected()) {//라디오버튼 deptRb가 선택됬을 경우
				super.paintComponent(g);
				deptGak.add(0,0);//처음 시작각은 0도부터이기에 0번 인덱스에 0을 넣음
				g.setFont(new Font("Gothic",Font.ITALIC,20));
				g.setColor(Color.BLACK);
				g.drawString("학과별 대출 비율", 400, 110);
				int deptGap=360;//과별 카운트의 각도 계산값의 합이 360으로 딱 떨어지지 않을수가 있음. 그래프가 이쁘게 안그려지므로 그때 모자란 수치를 더해주기위해 gap값을 구해주기위한 변수
				for(int k=0;k<deptName.size();k++	) {//deptName의 크기 = 학과 개수만큼 반복
					deptGap-=(int)Math.round(((float)deptCount.get(k)/sum)*360);//360에서 k번째 학과이름의 count값을 빼줌
					g.setFont(new Font("Gothic",Font.BOLD,15));
					g.setColor(color[k]);//미리저장해둔 랜덤색 사용
					g.drawString(deptName.get(k)+" - "+deptCount.get(k)+"권", 440, 140+(20*k));//어떤색이 어떤학과를 나타내는지 표시해주고 몇권을 빌려갔는지 표시
					g.fillRect(410, 130+(20*k), 20, 10); //작은 네모색칸임 무슨색인지 잘보여주기위한것
					if(k==deptName.size()-1)//마지막 학과를 그릴때 deptGap을 더해주기위한 코드
						g.fillArc(50, 50, 300, 300, deptGak.get(k),(int)Math.round((double)deptCount.get(k)/sum*360)+deptGap);
					else//마지막학과가 아니면 그냥 그림
						g.fillArc(50, 50, 300, 300, deptGak.get(k),(int)Math.round((double)deptCount.get(k)/sum*360));
					deptGak.add(k+1,deptGak.get(k)+(int)Math.round((double)deptCount.get(k)/sum*360));//k+1번째 각도의 시작은 k번째 각도가 끝난곳 부터임
				}

			}
			//==================================================================================================

			//학생별 상위5명 차트 생성
			if(studentRb.isSelected()) {
				int studentGap = 360;
				super.paintComponent(g);
				studentGak.add(0,0);
				g.setFont(new Font("Gothic",Font.ITALIC,20));
				g.setColor(Color.BLACK);
				g.drawString("상위 5명 대출비율", 400, 110);
				for(int k=0;k<studentName.size();k++	) {
					studentGap-=(int)Math.round(((float)studentCount.get(k)/sum2)*360);
					g.setFont(new Font("Gothic",Font.BOLD,15));
					g.setColor(color[k+2]);
					g.drawString(studentName.get(k)+" "+studentId.get(k)+" - "+studentCount.get(k)+"권", 430, 140+(20*k));
					g.fillRect(410, 130+(20*k), 20, 10);
					if(k==studentName.size()-1)
						g.fillArc(50, 50, 300, 300, studentGak.get(k),(int)Math.round((double)studentCount.get(k)/sum2*360)+studentGap);
					else
						g.fillArc(50, 50, 300, 300, studentGak.get(k),(int)Math.round((double)studentCount.get(k)/sum2*360));
					studentGak.add(k+1,studentGak.get(k)+(int)Math.round((double)studentCount.get(k)/sum2*360));
				}
			}
			//==================================================================================================

			//도서별 차트 생성
			if(bookRb.isSelected()) {
				int bookGap = 360;
				super.paintComponent(g);
				bookGak.add(0,0);
				g.setFont(new Font("Gothic",Font.ITALIC,20));
				g.setColor(Color.BLACK);
				g.drawString("상위 5권 대출비율", 400, 110);
				for(int k=0;k<bookName.size();k++	) {
					bookGap-=(int)Math.round(((float)bookCount.get(k)/sum3)*360);
					g.setFont(new Font("Gothic",Font.BOLD,15));
					g.setColor(color[k*2+1]);
					g.drawString(bookName.get(k)+" - "+bookCount.get(k)+"회", 440, 140+(20*k));
					g.fillRect(410, 130+(20*k), 20, 10);
					if(k==bookName.size()-1)
						g.fillArc(50, 50, 300, 300, bookGak.get(k),(int)Math.round((float)bookCount.get(k)/sum3*360)+bookGap);
					else
						g.fillArc(50, 50, 300, 300, bookGak.get(k),(int)Math.round((float)bookCount.get(k)/sum3*360));
					bookGak.add(k+1,bookGak.get(k)+(int)Math.round((float)bookCount.get(k)/sum3*360));
				}
			}
			//==================================================================================================

			//월별 차트 생성 
			if(dateRb.isSelected()) {
				int dateGap = 360;
				super.paintComponent(g);
				dateGak.add(0,0);
				g.setFont(new Font("Gothic",Font.ITALIC,20));
				g.setColor(Color.BLACK);
				g.drawString("상위 5개월 대출비율", 400, 110);
				for(int k=0;k<dateYear.size();k++	) {
					dateGap-=(int)Math.round(((float)dateCount.get(k)/sum4)*360);
					g.setFont(new Font("Gothic",Font.BOLD,15));
					g.setColor(color[k*2]);
					g.drawString(dateYear.get(k)+"년 "+dateMonth.get(k)+"월 - "+dateCount.get(k)+"권", 440, 140+(20*k));
					g.fillRect(410, 130+(20*k), 20, 10);
					if(k==dateYear.size()-1)
						g.fillArc(50, 50, 300, 300, dateGak.get(k),(int)Math.round((float)dateCount.get(k)/sum4*360)+dateGap);
					else
						g.fillArc(50, 50, 300, 300, dateGak.get(k),(int)Math.round((float)dateCount.get(k)/sum4*360));
					dateGak.add(k+1,dateGak.get(k)+(int)Math.round((float)dateCount.get(k)/sum4*360));
				}
			}
			//==================================================================================================
			setOpaque(false);
		}
	}
	//CenterPanel 끝======================================================================================================
	//아이템 리스너에 등록한 버튼들이 뭐가됐든 눌리면 cp.repaint()실행 (CenterPanel 다시그린다)
	class MyActionListener implements ItemListener{
		@Override
		public void itemStateChanged(ItemEvent e) {
			cp.repaint();
		}
	}
	//=========================================================================================================================================================
	//3D로 보기 버튼 눌렀을때 선택되어있던 radiobt을 기준으로 값을 받아와 출력
	class ButtonListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			cp.setSize(600,400);
			cp.removeAll();
			cp.revalidate();
			cp.repaint();

			if(deptRb.isSelected()) {
				dept.setSelected(true);
				cp.add(new PieChart3D());
			}
			if(studentRb.isSelected()) {
				student.setSelected(true);
				cp.add(new PieChart3D());
			}
			if(bookRb.isSelected()) {
				book.setSelected(true);
				cp.add(new PieChart3D());
			}
			if(dateRb.isSelected()) {
				date.setSelected(true);
				//				cp.setSize(600,400);
				//				cp.removeAll();
				//				cp.revalidate();
				//				cp.repaint();
				//				cp.setLayout(null);
				cp.add(new PieChart3D());
			}
		}
	}
	//=========================================================================================================================================================
	public static void main(String args[]) {
	}
	//===============================================================================================================================
	//=======3D 파이차트 만들어주는 코드=================================================================================================
	class PieChart3D extends JPanel {
		String titleName = null;
		public PieChart3D() {
			final PieDataset dataset = createSampleDataset();
			final JFreeChart chart = createChart(dataset);
			chartPanel = new ChartPanel(chart);
			setSize(600,450);
			add(chartPanel);
			setVisible(true);
		}

		private PieDataset createSampleDataset() {
			final DefaultPieDataset result = new DefaultPieDataset();
			if(dept.isSelected()) {
				for(int i=0;i<deptName.size();i++) {
					result.setValue(deptName.get(i)+" - "+deptCount.get(i)+"권", new Double((100/sum)*deptCount.get(i)));
				}
				titleName = "상위5개 학과 3D차트";
			}

			if(student.isSelected()) {
				for(int i=0;i<studentName.size();i++) {
					result.setValue(studentName.get(i)+" "+studentId.get(i)+" - "+studentCount.get(i)+"권", new Double((100/sum2)*studentCount.get(i)));
				}
				titleName = "상위5명 학생 3D차트";
			} 

			if(book.isSelected()) {
				for(int i=0;i<bookName.size();i++) {
					result.setValue(bookName.get(i)+" - "+bookCount.get(i)+"회", new Double((100/sum3)*bookCount.get(i)));
				}
				titleName = "상위5개 도서 3D차트";
			}

			if(date.isSelected()) {
				for(int i=0;i<dateYear.size();i++) {
					result.setValue(dateYear.get(i)+"년 "+dateMonth.get(i)+"월 - "+dateCount.get(i)+"권", new Double((100/sum4)*dateCount.get(i)));
				}
				titleName = "상위5개월 3D차트";
			}
			return result;
		}

		private JFreeChart createChart(final PieDataset dataset) {
			final JFreeChart chart = ChartFactory.createPieChart3D(
					titleName, dataset, true, true, false
					);

			final PiePlot3D plot = (PiePlot3D) chart.getPlot();
			plot.setStartAngle(290);
			plot.setDirection(Rotation.CLOCKWISE);
			plot.setForegroundAlpha(0.5f);
			plot.setNoDataMessage("No data to display");
			chart.getTitle().setFont(new Font("고딕", Font.BOLD, 15));
			plot.setLabelFont(new Font("고딕", Font.PLAIN, 10));
			chart.getLegend().setItemFont(new Font("고딕", Font.PLAIN, 10));
			return chart;
		}
	}
}