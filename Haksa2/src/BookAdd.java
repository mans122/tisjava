import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.ResultSet;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class BookAdd extends JFrame {
	DefaultTableModel model;
	JTable table;
	static JTextField[] tf_add = new JTextField[4];
	public BookAdd() {
		JFrame f = new JFrame();
		f.setTitle("도서 추가");
		f.setLayout(null);
		//f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JLabel title = new JLabel("도서 관리");
		title.setFont(new Font("Gothic",Font.BOLD,30));
		title.setBounds(50,0, 200, 40);

		JLabel bookNo = new JLabel("도서번호");
		bookNo.setFont(new Font("Gothic",Font.BOLD,15));
		bookNo.setBounds(20, 60, 80, 30);

		JLabel bookName = new JLabel("도서명");
		bookName.setFont(new Font("Gothic",Font.BOLD,15));
		bookName.setBounds(20, 100, 80, 30);

		JLabel bookWriter = new JLabel("지은이");
		bookWriter.setFont(new Font("Gothic",Font.BOLD,15));
		bookWriter.setBounds(20, 140, 80, 30);

		JLabel author = new JLabel("출판사");
		author.setFont(new Font("Gothic",Font.BOLD,15));
		author.setBounds(20, 180, 80, 30);

		for(int i=0;i<4;i++) {
			tf_add[i] = new JTextField();
			tf_add[i].setBounds(90, 60+(i*40), 120, 28);
			f.add(tf_add[i]);			
		}

		JButton submit = new JButton("등록");
		submit.setBounds(10, 230, 60, 30);
		JButton update = new JButton("수정");
		update.setBounds(80, 230, 60, 30);
		JButton delete = new JButton("삭제");
		delete.setBounds(150, 230, 60, 30);
		MyActionListener ma = new MyActionListener();
		submit.addActionListener(ma);
		update.addActionListener(ma);
		delete.addActionListener(ma);

		String colName[]={"도서 번호","도서명","저자","출판사"};
		model=new DefaultTableModel(colName,0);
		table = new JTable(model);
		table.setPreferredScrollableViewportSize(new Dimension(480,200));
		f.add(table);
		JScrollPane sp=new JScrollPane(table);
		sp.setBounds(230, 10, 340, 250);
		f.add(sp); 
		table.addMouseListener(new MouseListener() {
			public void mouseClicked(MouseEvent e) {
				table = (JTable)e.getComponent();
				model=(DefaultTableModel)table.getModel();
				//model.getValueAt의 값이 object라 String으로 변환해준다
				String[] value=new String[4];
				for(int i=0;i<4;i++) {
					value[i] = (String)model.getValueAt(table.getSelectedRow(), i);
					tf_add[i].setText(value[i]);
				}
			}
			public void mouseReleased(MouseEvent e) {}
			public void mousePressed(MouseEvent e) {}
			public void mouseExited(MouseEvent e) {}
			public void mouseEntered(MouseEvent e) {}

		});
		bookList();

		f.add(author);
		f.add(submit);
		f.add(update);
		f.add(delete);
		f.add(bookWriter);
		f.add(bookName);
		f.add(bookNo);
		f.add(title);
		f.setSize(600, 320);
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		f.setLocation((screenSize.width-f.getPreferredSize().width)/2,(screenSize.height-f.getPreferredSize().height)/2);
		f.setVisible(true);
	}

	class MyActionListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			String no = tf_add[0].getText();
			String name =tf_add[1].getText();
			String writer =tf_add[2].getText();
			String author =tf_add[3].getText();
			boolean isNumber = false;
			String cmd = e.getActionCommand();
			switch(cmd) {
			case "등록":
				if(no.length()==0) {
					System.out.println(no.length());
					JOptionPane.showMessageDialog(null,"책 번호를 입력하세요","경고",JOptionPane.WARNING_MESSAGE);
				}
				else {
					System.out.println("dd");
					if (isStringInt(no))
						isNumber=true;
					else
						isNumber=false;

					if(no.length()==6 && isNumber==true) {
						try {
							DBManager.stmt.executeUpdate("insert into books2 values('"+no+"','"+name+"','"+writer+"','"+author+"')");
							JOptionPane.showMessageDialog(null,"등록되었습니다.","알림",JOptionPane.INFORMATION_MESSAGE);
							for(int i=0;i<4;i++) {
								tf_add[i].setText("");
							}
							bookList();
						}
						catch(Exception ee) {
							JOptionPane.showMessageDialog(null,"이미 존재하는 책입니다.","경고",JOptionPane.WARNING_MESSAGE);
							ee.printStackTrace();
						}
					}
					else if(isNumber == false) {
						JOptionPane.showMessageDialog(null,"책번호는 숫자만 입력해주세요","경고",JOptionPane.WARNING_MESSAGE);
					}
					else {
						JOptionPane.showMessageDialog(null,"책 번호는 6자리로 입력해주세요","경고",JOptionPane.WARNING_MESSAGE);}
				}
				break;
			case "수정":
				try {
					if(no.length()!=0) {
						//해당학번으로 조회를해서 rowcount에 조회된 행 수를 조회
						ResultSet rs = DBManager.stmt.executeQuery("select * from books2 where no = '"+no+"'");
						rs.next();

						if(rs.getRow() == 1) {
							//이름,학과,주소를 적었으면 수정
							if(name.length()!=0) {
								DBManager.stmt.executeUpdate("update books2 set title='"+name+"' where no = '"+no+"'");	}
							if(writer.length()!=0) {
								DBManager.stmt.executeUpdate("update books2 set writer='"+writer+"' where no = '"+no+"'");	}
							if(author.length()!=0) {
								DBManager.stmt.executeUpdate("update books2 set author='"+author+"' where no = '"+no+"'");	}
							JOptionPane.showMessageDialog(null,"수정이 완료되었습니다.","알림",JOptionPane.INFORMATION_MESSAGE);
							for(int i=0;i<4;i++) {
								tf_add[i].setText("");
							}
							bookList();
						}
						else {
							JOptionPane.showMessageDialog(null,"해당 도서가 존재하지 않습니다.","알림",JOptionPane.INFORMATION_MESSAGE);
							break;
						}
						//수정버튼을 눌렀을때 학번에 아무것도 안써있으면 경고출력
					}else {
						JOptionPane.showMessageDialog(null,"수정할 도서번호를 입력해주세요","경고",JOptionPane.WARNING_MESSAGE);
						break;
					}
					break;
				}
				catch(Exception inputE) {
					inputE.printStackTrace();
				}
				for(int i=0;i<4;i++) {
					tf_add[i].setText("");
				}
				bookList();
				break;
			case "삭제":
				if(JOptionPane.showConfirmDialog(null, "정말삭제하시겠습니까?", "삭제", JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION) {
					if(no.length()!=0) {
						try {
							DBManager.stmt.executeUpdate("delete from books2 where no = '"+no+"'");
							list();
							for(int i=0;i<4;i++) {
								tf_add[i].setText("");
							}
						}
						catch(Exception deleteE) {
							deleteE.printStackTrace();
						}
					}
					else {
						JOptionPane.showMessageDialog(null,"삭제할 학번을 입력해주세요","경고",JOptionPane.WARNING_MESSAGE);
					}
				}
				bookList();
				break;
			}

		}

	}
	public static boolean isStringInt(String s) {
		try {
			Integer.parseInt(s);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}

	public void bookList(){
		try{
			// Select문 실행     
			ResultSet rs= DBManager.stmt.executeQuery("select * from books2 order by no");
			model.setNumRows(0);

			while(rs.next()){
				String[] row=new String[4];//컬럼의 갯수가 4
				row[0]=rs.getString("no");
				row[1]=rs.getString("title");
				row[2]=rs.getString("writer");
				row[3]=rs.getString("author");
				model.addRow(row);
			}
			rs.close();
		}
		catch(Exception e1){
			System.out.println(e1.getMessage());
		}
	}

	public static void main(String[] args) {
		new BookAdd();
	}

}
