import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.swing.*;
//360에 모자른 값을 채워줘야함
//Math.round
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

	static ArrayList<String> date = new ArrayList<>();
	static ArrayList<Integer> dateCount = new ArrayList<>();
	static int sum4=0;
	static Color[] deptColor;
	static Color[] studentColor;
	static Color[] bookColor;
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
		DBManager db = new DBManager();
		db.Connection();
		if(isFirst==0) {
			try{
				//학과별로 분류하기위한 내용
				rs = DBManager.stmt.executeQuery("select dept, count(*) as count" 
						+" from (select s.dept, br.rdate"
						+" from student s, books b, bookRent br"
						+" where br.id=s.id"
						+" and br.bookNo=b.no)"
						+" group by dept ");
				int i=0;
				//학과별로 BookRent 테이블에서 검색해서 나오는 학과,학과별 총합을 구해줌
				while(rs.next()) {
					deptName.add(i,rs.getString("dept"));
					deptCount.add(i,rs.getInt("count"));
					sum+=deptCount.get(i);
					i++;
				}
				//학생별 값 구해서 넣어주는코드,대여하는 학생이 수백 수천일수도 있으므로 상위 5명만 구해준다.
				rs = DBManager.stmt.executeQuery("select id,name, count(*) count"
						+" from(select br.id id,name,b.no no,b.title title, br.rdate from student s, books b, bookRent br where br.id=s.id and br.bookNo=b.no)"
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
				//도서별 상위 5종류만 구해준다.
				rs = DBManager.stmt.executeQuery("select title, count(*) count"
						+" from(select br.id id,name,b.no no,b.title title, br.rdate from student s, books b, bookRent br where br.id=s.id and br.bookNo=b.no)"
						+" group by title order by count desc");
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
				//선택할떄마다 색이 바뀌지 않게 미리 색을 리스트에 넣어둔다.
				deptColor = new Color[deptName.size()];
				for(i=0;i<deptName.size();i++) {deptColor[i] =  new Color((int)(Math.random()*255.0),(int)(Math.random()*255.0),(int)(Math.random()*255.0));}
				studentColor =new Color[studentName.size()];
				for(i=0;i<studentName.size();i++) {	studentColor[i] =  new Color((int)(Math.random()*255.0),(int)(Math.random()*255.0),(int)(Math.random()*255.0));}
				bookColor =new Color[studentName.size()];
				for(i=0;i<bookName.size();i++) {bookColor[i] =  new Color((int)(Math.random()*255.0),(int)(Math.random()*255.0),(int)(Math.random()*255.0));			}
			}catch(Exception e){
				e.printStackTrace();
			}
			isFirst++;
		}
		setLayout(new BorderLayout());
		np.setLayout(new FlowLayout());//np패널은 FlowLayout으로 정렬
		add(cp,BorderLayout.CENTER); //CENTER에 cp패널 올림
		add(np,BorderLayout.NORTH);//NORTH에 np패널 올림
		setSize(650, 500); // 프레임 사이즈 지정
		setVisible(true); // 프레임을 보이게 함
	}	
	//BorderLayout.NORTH 패널 생성 및 들어갈 내용 작성
	class NorthPanel extends JPanel{
		public NorthPanel() {
			this.setLayout(new FlowLayout(FlowLayout.CENTER,50,0));
			MyActionListener ma = new MyActionListener();
			ButtonGroup bg = new ButtonGroup();

			bg.add(studentRb);
			bg.add(deptRb);
			bg.add(bookRb);
			add(deptRb);
			add(studentRb);
			add(bookRb);

			deptRb.addItemListener(ma);
			studentRb.addItemListener(ma);
			bookRb.addItemListener(ma);

		}
	}
	//CenterPanel 클래스 작성
	class CenterPanel extends JPanel{
		ArrayList<Integer> deptGak = new ArrayList<>();
		ArrayList<Integer> studentGak = new ArrayList<>();
		ArrayList<Integer> bookGak = new ArrayList<>();
		public void paintComponent(Graphics g) {
			if(deptRb.isSelected()) {
				super.paintComponent(g);
				deptGak.add(0,0);
				g.setFont(new Font("Gothic",Font.ITALIC,20));
				g.setColor(Color.BLACK);
				g.drawString("학과별 대출 비율", 400, 110);
				for(int k=0;k<deptName.size();k++	) {
					//color = new Color((int)(Math.random()*255.0),(int)(Math.random()*255.0),(int)(Math.random()*255.0));
					g.setFont(new Font("Gothic",Font.ITALIC,15));
					//g.setColor(color);
					g.setColor(deptColor[k]);
					g.drawString(deptName.get(k), 450, 140+(20*k));
					g.fillRect(420, 130+(20*k), 20, 10);
					g.fillArc(50, 50, 300, 300, deptGak.get(k),(360/sum)*deptCount.get(k));
					deptGak.add(k+1,deptGak.get(k)+(360/sum)*deptCount.get(k));
					System.out.println(Math.round(360/sum)*deptCount.get(k));
					System.out.println(deptGak.get(k)+(360/sum)*deptCount.get(k));
				}
			}

			if(studentRb.isSelected()) {
				super.paintComponent(g);
				studentGak.add(0,0);
				g.setFont(new Font("Gothic",Font.ITALIC,20));
				g.setColor(Color.BLACK);
				g.drawString("상위 5명 대출비율", 400, 110);
				for(int k=0;k<studentName.size();k++	) {
					g.setFont(new Font("Gothic",Font.ITALIC,15));
					g.setColor(studentColor[k]);
					g.drawString(studentName.get(k), 450, 140+(20*k));
					g.drawString(studentId.get(k), 510, 140+(20*k));
					g.fillRect(420, 130+(20*k), 20, 10);
					g.fillArc(50, 50, 300, 300, studentGak.get(k),(360/sum2)*studentCount.get(k));
					studentGak.add(k+1,studentGak.get(k)+(360/sum2)*studentCount.get(k));
					System.out.println((360/sum2)*studentCount.get(k));
					System.out.println(studentGak.get(k)+(360/sum2)*studentCount.get(k));
				}
			}

			if(bookRb.isSelected()) {
				super.paintComponent(g);
				bookGak.add(0,0);
				g.setFont(new Font("Gothic",Font.ITALIC,20));
				g.setColor(Color.BLACK);
				g.drawString("상위 5권 대출비율", 400, 110);
				for(int k=0;k<bookName.size();k++	) {
					g.setFont(new Font("Gothic",Font.ITALIC,15));
					g.setColor(bookColor[k]);
					g.drawString(bookName.get(k), 450, 140+(20*k));
					g.fillRect(420, 130+(20*k), 20, 10);
					g.fillArc(50, 50, 300, 300, bookGak.get(k),(360/sum3)*bookCount.get(k));
					bookGak.add(k+1,bookGak.get(k)+(360/sum3)*bookCount.get(k));
				}
			}


		}
	}

	class MyActionListener implements ItemListener{
		@Override
		public void itemStateChanged(ItemEvent e) {
			cp.repaint();
		}
	}

	public static void main(String args[]) {
		new BookGraph();
	}
}