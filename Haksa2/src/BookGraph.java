import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.swing.*;

public class BookGraph extends JPanel {
	static ArrayList<String> deptName = new ArrayList<>();
	static ArrayList<Integer> deptCount = new ArrayList<>();
	static int sum=0;

	static ArrayList<String> studentId = new ArrayList<>();
	static ArrayList<String> studentName = new ArrayList<>();
	static ArrayList<Integer> studentCount = new ArrayList<>();
	static int sum2=0;

	static ArrayList<String> bookName = new ArrayList<>();
	static ArrayList<Integer> bookCount = new ArrayList<>();
	static int sum3=0;

	static ArrayList<String> dateYear = new ArrayList<>();
	static ArrayList<String> dateMonth = new ArrayList<>();
	static ArrayList<Integer> dateCount = new ArrayList<>();
	static int sum4=0;
	static Color[] deptColor;
	static Color[] studentColor;
	static Color[] bookColor;
	static Color[] dateColor;
	static int isFirst = 0;
	ResultSet rs = null;
	JRadioButton  deptRb = new JRadioButton("학과별");
	JRadioButton  studentRb = new JRadioButton("학생별");
	JRadioButton  bookRb = new JRadioButton("도서별");
	JRadioButton  dateRb = new JRadioButton("월별");
	CenterPanel cp = new CenterPanel(); // CenterPanel클래스로 만든 메인프레임 가운데 붙일 패널 cp생성
	NorthPanel np= new NorthPanel();	//NotrhPanel클래스로 만든 위에붙일 패널 np 생성
	String query;
	public BookGraph() {
		if(isFirst==0) {//만약 다른 메뉴로 들어갔다 다시 BookGraph를 호출했을때  쿼리를 다시돌리고 값을 
			//다시저장하면 꼬이는경우가 발생 BookGraph메서드 마지막에 isFirst 값을 증가시켜 다음에 또 찾아올경우 이부분을 넘어가도록
			try{
				//학과별로 분류하기위한 내용
				rs = DBManager.stmt.executeQuery("select dept, count(*) as count from (select s.dept, br.rdate from student s, books2 b, bookRent2 br"
						+" where br.id=s.id and br.bookNo=b.no) group by dept order by count desc");
				int i=0;
				//====================================================
				
				//학과별로 BookRent 테이블에서 검색해서 나오는 학과,학과별 총합을 구해줌
				while(rs.next()) {
					deptName.add(i,rs.getString("dept"));
					deptCount.add(i,rs.getInt("count"));
					sum+=deptCount.get(i);
					i++;
				}
				//====================================================
				//학생별 값 구해서 넣어주는코드,대여하는 학생이 수백 수천일수도 있으므로 상위 5명만 구해준다.
				rs = DBManager.stmt.executeQuery("select id,name, count(*) count"
						+" from(select br.id id,name,b.no no,b.title title, br.rdate from student s, books2 b, bookRent2 br where br.id=s.id and br.bookNo=b.no)"
						+" group by id,name order by count desc");
				int i2 = 0;
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
				//=======================================================================================================================

				//도서별 상위 5종류만 구해준다.
				rs = DBManager.stmt.executeQuery("select title, count(*) count from(select br.id id,name,b.no no,b.title title, br.rdate from student s,"
						+" books2 b, bookRent2 br where br.id=s.id and br.bookNo=b.no) group by title order by count desc");
				int i3 = 0;
				while(rs.next()) {
					if(i3>4) {
						break;
					}
					bookName.add(i3,rs.getString("title"));
					bookCount.add(i3,rs.getInt("count"));
					sum3+=bookCount.get(i3);
					i3++;
				}
				//=======================================================================================================================

				//대여기록이 많은 년월을 구해준다.
				rs = DBManager.stmt.executeQuery("select year, month, count(*) count from(select substr(br.rentno,0,4) year,substr(br.rentno,5,2) month from student s, books2 b, bookRent2 br where br.id=s.id(+) and br.bookNo=b.no)" + 
						" group by year,month order by count desc");
				int i4 = 0;
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

				//=========================================================================================================================

				//선택할떄마다 색이 바뀌지 않게 미리 색을 리스트에 넣어둔다.
				deptColor = new Color[deptName.size()];
				for(i=0;i<deptName.size();i++) {deptColor[i] =  new Color((int)(Math.random()*255.0),(int)(Math.random()*255.0),(int)(Math.random()*255.0));}
				studentColor =new Color[studentName.size()];
				for(i=0;i<studentName.size();i++) {	studentColor[i] =  new Color((int)(Math.random()*255.0),(int)(Math.random()*255.0),(int)(Math.random()*255.0));}
				bookColor =new Color[studentName.size()];
				for(i=0;i<bookName.size();i++) {bookColor[i] =  new Color((int)(Math.random()*255.0),(int)(Math.random()*255.0),(int)(Math.random()*255.0));			}
				dateColor =new Color[dateYear.size()];
				for(i=0;i<dateYear.size();i++) {dateColor[i] =  new Color((int)(Math.random()*255.0),(int)(Math.random()*255.0),(int)(Math.random()*255.0));			}
				//=======================================================================================================================
			}catch(Exception e){
				e.printStackTrace();
			}
			isFirst++;//if부분을 종료하기전 isFirst를 증가시켜 다음에 또 호출시 넘어가도록
		}
		setLayout(new BorderLayout()); // 패널 정렬을 BorderLayout으로
		np.setLayout(new FlowLayout());//np패널은 FlowLayout으로 정렬
		add(cp,BorderLayout.CENTER); //CENTER에 cp패널 올림
		add(np,BorderLayout.NORTH);//NORTH에 np패널 올림
		setSize(650, 500); // 프레임 사이즈 지정
		setVisible(true); // 프레임을 보이게 함
	}	
	
	//BorderLayout.NORTH 패널 생성 및 들어갈 내용 작성
	class NorthPanel extends JPanel{
		public NorthPanel() {
			//this.setLayout(new FlowLayout(FlowLayout.CENTER,50,0));
			MyActionListener ma = new MyActionListener();
			ButtonGroup bg = new ButtonGroup();
			//라디오 버튼을 생성하고 버튼그룹 bg에 모두 올려서 이쁘게 정렬
			bg.add(studentRb);
			bg.add(deptRb);
			bg.add(bookRb);
			bg.add(dateRb);
			add(deptRb);
			add(studentRb);
			add(bookRb);
			add(dateRb);
			
			//모두 아이템리스너에 올려줌
			deptRb.addItemListener(ma);
			studentRb.addItemListener(ma);
			bookRb.addItemListener(ma);
			dateRb.addItemListener(ma);

		}
	}
//NorthPanel 끝 ==========================================================================================================
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
					g.setFont(new Font("Gothic",Font.ITALIC,15));
					g.setColor(deptColor[k]);//미리저장해둔 랜덤색 사용
					g.drawString(deptName.get(k)+" - "+deptCount.get(k)+"권", 450, 140+(20*k));//어떤색이 어떤학과를 나타내는지 표시해주고 몇권을 빌려갔는지 표시
					g.fillRect(420, 130+(20*k), 20, 10); //작은 네모색칸임 무슨색인지 잘보여주기위한것
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
					g.setFont(new Font("Gothic",Font.ITALIC,15));
					g.setColor(studentColor[k]);
					g.drawString(studentName.get(k)+" "+studentId.get(k)+" - "+studentCount.get(k)+"권", 440, 140+(20*k));
					g.fillRect(420, 130+(20*k), 20, 10);
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
					g.setFont(new Font("Gothic",Font.ITALIC,15));
					g.setColor(bookColor[k]);
					g.drawString(bookName.get(k)+" - "+bookCount.get(k)+"회", 450, 140+(20*k));
					g.fillRect(420, 130+(20*k), 20, 10);
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
					g.setFont(new Font("Gothic",Font.ITALIC,15));
					g.setColor(dateColor[k]);
					g.drawString(dateYear.get(k)+"년 "+dateMonth.get(k)+"월 - "+dateCount.get(k)+"권", 450, 140+(20*k));
					g.fillRect(420, 130+(20*k), 20, 10);
					if(k==dateYear.size()-1)
						g.fillArc(50, 50, 300, 300, dateGak.get(k),(int)Math.round((float)dateCount.get(k)/sum4*360)+dateGap);
					else
						g.fillArc(50, 50, 300, 300, dateGak.get(k),(int)Math.round((float)dateCount.get(k)/sum4*360));
					dateGak.add(k+1,dateGak.get(k)+(int)Math.round((float)dateCount.get(k)/sum4*360));
				}
			}
			//==================================================================================================
		}
	}
//CenterPanel 끝======================================================================================================
	class MyActionListener implements ItemListener{
		@Override
		public void itemStateChanged(ItemEvent e) {
			cp.repaint();//아이템 리스너에 등록한 버튼들이 뭐가됐든 눌리면 cp.repaint()실행
		}
	}

	public static void main(String args[]) {
	}
}