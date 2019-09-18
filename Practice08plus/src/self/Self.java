package self;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import javax.swing.*;
import java.awt.*;

class ImgFolder {
	public static ArrayList<String> fileName = new ArrayList<>();
	public static int fileNum;
	public static String url;
	public static String[] str;
	public static void listDirectory(File dir) {
		File[] subFiles = dir.listFiles();
		for(int i=0;i<subFiles.length;i++) {
			File f = subFiles[i];
			ImgFolder.fileName.add(i,f.getPath());
			System.out.println(ImgFolder.fileName.get(i));
		}
		ImgFolder.fileNum = fileName.size();
		System.out.println(ImgFolder.fileNum);
	}
	public ImgFolder() {}
}


public class Self extends JFrame{
	private int index =0;
	private JButton btnLeft;
	private JButton btnRight;
	public ImageIcon[] image;
	private JLabel imageLabel = new JLabel();
	
	public Self() {
		File f1 = new File("c:/myPhoto");//원하는 폴더안의 파일을 가져오기위한 폴더 경로 설정
		ImgFolder.listDirectory(f1);	//ImgFolder 클래스의 listDirectory 메소드로 매개변수 f1을 넣어줌	
		image = new ImageIcon[ImgFolder.fileNum];//이미지 배열 개수는 ImgFolder클래스에서 구한 fileNum값을 넣어준다.
		
		setTitle("연습문제 08");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container c = getContentPane();
		c.setLayout(new FlowLayout(FlowLayout.CENTER,0,30));
		//image[]에 사진을 넣는 반복문. 경로는 ImgFolder클래스에서 fileName을 받아온다.
		for(int i=0;i<ImgFolder.fileNum;i++) {
			image[i] = new ImageIcon(ImgFolder.fileName.get(i));
		}
		imageLabel.setIcon(image[0]);//이미지 자리에 맨처음 index 0번 image 올려둔다.
		JPanel imgPanel = new JPanel();
		imgPanel.setLayout(new FlowLayout(FlowLayout.CENTER,0,15));
		imgPanel.setBackground(Color.WHITE);
		imgPanel.setPreferredSize(new Dimension(550,550));
		c.add(imgPanel);
		imgPanel.add(imageLabel);
		
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

	//액션리스너 상속받는 클래스  생성
	class MyItemListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			if(e.getSource() == btnLeft) {
				index --;
				if(index >= 0) {
					imageLabel.setIcon(image[index]);
				}
				else {
					index = image.length-1;
					imageLabel.setIcon(image[index]);	
				}
			}
			else {
				index ++;
				if(index <= image.length-1) {
					imageLabel.setIcon(image[index]);
				}
				else {
					index = 0;
					imageLabel.setIcon(image[index]);	
				}

			}
		}
	}
	public static void main(String[] args) {
		new Self();
	}

}
