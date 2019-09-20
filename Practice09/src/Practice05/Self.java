package Practice05;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import javax.swing.*;
import java.awt.*;
class ImgFolder {
	public static ArrayList<String> fileName = new ArrayList<>();
	public static int fileNum;
	
	//img폴더에서 경로를 fileName에,파일 개수를 fileNum에 입력
	public static void listDirectory(File dir) {
		File[] subFiles = dir.listFiles();
		for(int i=0;i<subFiles.length;i++) {
			File f = subFiles[i];
			fileName.add(i,f.getPath());
			System.out.println(fileName.get(i));//파일경로가 잘 들어갔는지 확인하기위한 코드
		}
		fileNum = fileName.size();
		System.out.println(fileNum); //폴더안의 파일 총개수가 잘 들어갔는지 확인하기위한 코드
	}
}


public class Self extends JFrame{
	private int index =0;
	private JButton btnLeft;
	private JButton btnRight;
	public ImageIcon[] image;
	public static JLabel imageLabel = new JLabel();
	//public static ImageIcon[] fruitImage = new ImageIcon[4];
	public Self() {
		File f1 = new File("c:/myPhoto");//원하는 폴더안의 파일을 가져오기위한 폴더 경로 설정
		ImgFolder.listDirectory(f1);	//ImgFolder 클래스의 listDirectory 메소드로 매개변수 f1을 넣어줌	
		image = new ImageIcon[ImgFolder.fileNum];//이미지 배열 개수는 ImgFolder클래스에서 구한 fileNum값을 넣어준다.

		setTitle("연습문제 09 5번문제");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container c = getContentPane();
		c.setLayout(new FlowLayout(FlowLayout.CENTER,0,30));
		createMenu();//메뉴 만드는 메소드 호출
		//image[]에 사진을 넣는 반복문. 경로는 ImgFolder클래스에서 fileName을 받아온다.
		for(int i=0;i<ImgFolder.fileNum;i++) {
			image[i] = new ImageIcon(ImgFolder.fileName.get(i));
		}
		imageLabel.setIcon(image[0]);//이미지 자리에 맨처음 index 0번 image 올려둔다.
		//이미지를 넣을 패널 생성하고 imgPanel을 메인프레임에 올리고 imgPanel에 이미지를 올린다
		JPanel imgPanel = new JPanel();
		imgPanel.setLayout(new FlowLayout(FlowLayout.CENTER,0,15));
		imgPanel.setBackground(Color.WHITE);
		imgPanel.setPreferredSize(new Dimension(550,550));
		c.add(imgPanel);
		imgPanel.add(imageLabel);
		
		//좌우버튼을 넣을 패널 생성
		JPanel iconPanel = new JPanel();
		iconPanel.setLayout(new FlowLayout(FlowLayout.CENTER,0,30));
		iconPanel.setBackground(Color.GREEN);
		iconPanel.setPreferredSize(new Dimension(550,200));
		c.add(iconPanel);
		
		//좌우 화살표 이미지 설정
		ImageIcon previous = new ImageIcon("img/previous.png");
		ImageIcon next = new ImageIcon("img/next.png");
		this.btnLeft = new JButton(previous);
		this.btnRight = new JButton(next);
		
		//iconPanel에 좌우 버튼 등록
		iconPanel.add(btnLeft);
		iconPanel.add(btnRight);
		
		//좌우버튼 ActionListener에 등록
		btnLeft.addActionListener(new MyItemListener());
		btnRight.addActionListener(new MyItemListener());

		setSize(550,850);
		setVisible(true);
	}
	//메뉴바 만드는 메소드
	private void createMenu() {
		JMenuBar mb = new JMenuBar();
		JMenuItem[] menuItem = new JMenuItem[4];
		String[] itemTitle = {"apple","banana","kiwi","mango"};
		JMenu screenMenu = new JMenu("File");

		MenuActionListener listener = new MenuActionListener();
		for(int i=0;i<menuItem.length;i++) {
			menuItem[i] = new JMenuItem(itemTitle[i]);
			menuItem[i].addActionListener(listener);
			screenMenu.add(menuItem[i]);
		}
		mb.add(screenMenu);
		setJMenuBar(mb);
	}

	//액션리스너 상속받는 클래스  생성
	class MyItemListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			if(e.getSource() == btnLeft) {
				index --;//왼쪽화살표 버튼 누를 시 index값을 감소
				if(index >= 0) {//index가 0보다 크거나 같으면 그대로 넣어줌
					imageLabel.setIcon(image[index]);
				}
				else {
					index = image.length-1;//index가 0보다 작으면 image의 개수-1로 값을 변경해줌, 즉 맨뒤로 돌아감
					imageLabel.setIcon(image[index]);	
				}
			}
			else {
				index ++;
				if(index <= image.length-1) {
					imageLabel.setIcon(image[index]);
				}
				else {
					index = 0;//index가 image의 개수보다 커지면 값을 0으로변경,즉 맨앞으로 돌아감
					imageLabel.setIcon(image[index]);	
				}
			}
		}
	}
	public static void main(String[] args) {
		new Self();
	}

}
