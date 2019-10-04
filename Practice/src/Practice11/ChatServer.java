package Practice11;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.*;
import java.io.*;
import java.net.*;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.border.BevelBorder;

//BorderLayout에서 Center에 받는메시지 출력창
//SOUTH에 패널하나 올리고 그위에 TextField2개로 닉네임,보낼메시지 입력
//CENTER에 
//JLabel 테두리설정
//BevelBorder border = new BevelBorder(BevelBorder.RAISED); 
//sText.get(sendCount).setBorder(border); 3차원
//eborder=new EtchedBorder(EtchedBorder.RAISED);//평면에 끌로 판듯이 외곽선 효과를 내는 것이고 양각의 효과를 준다.
//label.setBorder(eborder);평면에 끌로 판듯이 외곽선 효과
//JScrollPanel.getVerticalScrollBar().setValue(JScrollPanel.getVerticalScrollBar().getMaximum());
public class ChatServer extends JFrame implements ActionListener{
	public static ImageIcon[] image;
	private static int sendCount = 0;
	private static int receiveCount = 0;
	private static int imgCount = 0;
	private static int i,k;
	private JLabel imageLabel = new JLabel();
	private BufferedReader in = null;
	private BufferedWriter out = null;
	private ServerSocket listener = null;
	private Socket socket = null;
	private Receiver receiver = null;
	private JTextField sender = null;
	private JTextField nickname=null;
	private JScrollPane	csPanel = new JScrollPane();
	private static JPanel cPanel = new JPanel();
	private JPanel	sPanel = new JPanel();
	private static String mynick = "서버";
	private static String clientNick = "클라이언트";
	private static String[] array= new String[3];
	private static ArrayList<JLabel> rText = new ArrayList<>();
	private static ArrayList<JLabel> sText = new ArrayList<>();
	public ChatServer() {
		ImageIcon icon = new ImageIcon("img/bgi.jpg");

		setTitle("서버 채팅 창");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container c = getContentPane();
		c.setLayout(new BorderLayout());
		sPanel.setLayout(new BorderLayout());
		File f1 = new File("img");//원하는 폴더안의 파일을 가져오기위한 폴더 경로 설정
		ImgFolder.listDirectory(f1);	//ImgFolder 클래스의 listDirectory 메소드로 매개변수 f1을 넣어줌	
		image = new ImageIcon[ImgFolder.fileNum];
		for(int i=0;i<ImgFolder.fileNum;i++) {
			image[i] = new ImageIcon(ImgFolder.fileName.get(i));
		}
		cPanel = new JPanel(new FlowLayout(FlowLayout.LEFT,0,5)) {
			public void paintComponent(Graphics g) {
				Dimension d = getSize();
				g.drawImage(icon.getImage(), 0, 0, d.width, d.height, null);
				System.out.println("d"+(25*(sendCount+receiveCount)+(12*imgCount)));
				System.out.println("a"+cPanel.getPreferredSize().getSize().height);

				rText.add(receiveCount,new JLabel());
				rText.get(receiveCount).setFont(new Font("궁서", Font.BOLD, 15));
				rText.get(receiveCount).setPreferredSize(new Dimension(cPanel.getSize().width-10,20));

				sText.add(sendCount,new JLabel());
				sText.get(sendCount).setFont(new Font("맑은 고딕", Font.BOLD, 15));
				sText.get(sendCount).setHorizontalAlignment(JLabel.RIGHT);
				sText.get(sendCount).setPreferredSize(new Dimension(cPanel.getSize().width-10,20));
				this.setPreferredSize(new Dimension(csPanel.getSize().width-20,25*(2+sendCount+receiveCount)+(12*imgCount)));

				//csPanel.getVerticalScrollBar().setValue(csPanel.getVerticalScrollBar().getMaximum());
				this.setOpaque(false);
				super.paintComponent(g);
			}
		};


		//받는 메시지 보여주는 창
		receiver = new Receiver();


		//닉네임 출력 창
		nickname = new JTextField(5);
		nickname.setText("닉네임");
		nickname.addActionListener(this);
		sPanel.add(nickname,BorderLayout.WEST);
		//-------------------------------------------------------------------------------

		//보내는 메시지 입력창
		sender = new JTextField(20);
		sender.addActionListener(this);
		sPanel.add(sender,BorderLayout.CENTER);
		c.add(sPanel,BorderLayout.SOUTH);
		//-------------------------------------------------------------------------------

		//스크롤패널에 cPanel추가하고 csPanel추가
		//csPanel = new JScrollPane(cPanel, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		csPanel = new JScrollPane(cPanel);
		c.add(csPanel,BorderLayout.CENTER);
		setSize(400, 500);
		setResizable(false);
		setVisible(true);

		try {
			setupConnection();
		}catch(IOException e) {
			handleError(e.getMessage());
		}
		Thread th = new Thread(receiver);

		th.start();
	}
	private void setupConnection() throws IOException{
		listener = new ServerSocket(9999);
		socket = listener.accept();
		cPanel.add(rText.get(receiveCount));
		rText.get(receiveCount).setHorizontalAlignment(JLabel.CENTER);
		rText.get(receiveCount).setForeground(Color.CYAN);
		rText.get(receiveCount).setText("클라이언트와 연결되었습니다.");
		receiveCount++;
		in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
	}
	//------------------------------------------------핸들에러------------------------------------------------------
	private static void handleError(String string) {
		System.out.println(string);
		System.exit(1);
	}
	//-----------------------------------------------------------------------------------------------------------

	//------------------------------------------------받은 메시지 보여주는 Receiver------------------------------------------------------
	private class Receiver extends JFrame implements Runnable{
		@Override
		public void run() {
			String msg = null;
			while(true) {
				try {
					msg = in.readLine();
					if(msg.contains("닉네임")) {
						rText.get(receiveCount).setHorizontalAlignment(JLabel.CENTER);
					}
					cPanel.add(rText.get(receiveCount));
					rText.get(receiveCount).setLayout(new FlowLayout(FlowLayout.RIGHT));
					rText.get(receiveCount).setText("\n"+msg);
					csPanel.getVerticalScrollBar().setValue(csPanel.getVerticalScrollBar().getMaximum());
					receiveCount++;
				}catch(IOException e) {
					handleError(e.getMessage());
				}
			}
		}
	}
	//-----------------------------------------------------------------------------------------------------------

	//------------------------------------------------메시지 보내기------------------------------------------------------
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == sender) {
			String msg = sender.getText();
			try {
				if(msg.contains("@@")) {
					int index = msg.indexOf("@@");
					String getIcon = msg.substring(index+2,index+7);
					for(int i=0;i<ImgFolder.fileNum;i++) {
						if(ImgFolder.fileName.get(i).contains(getIcon)) {
							//System.out.println(ImgFolder.fileName.get(i));
							//imageLabel.setIcon(new ImageIcon(ImgFolder.fileName.get(i)));
							cPanel.add(sText.get(sendCount));
							sText.get(sendCount).setIcon(new ImageIcon(ImgFolder.fileName.get(i)));
							sText.get(sendCount).setHorizontalAlignment(JLabel.RIGHT);
							sText.get(sendCount).setPreferredSize(new Dimension(cPanel.getSize().width-10,32));
							imgCount++;
							sendCount++;
							sender.setText(null);
						}
					}
				}
				else{
					out.write(mynick+" >> "+msg+"\n");
					out.flush();
					cPanel.add(sText.get(sendCount));
					sText.get(sendCount).setText("\n"+msg);
					csPanel.getVerticalScrollBar().setValue(csPanel.getVerticalScrollBar().getMaximum());
					sendCount++;
					sender.setText(null);
				}
			}catch(IOException e1) {
				handleError(e1.getMessage());
			}
		}else if(e.getSource() == nickname) {
			mynick = nickname.getText();
			try {
				cPanel.add(sText.get(sendCount));
				sText.get(sendCount).setHorizontalAlignment(JLabel.CENTER);
				sText.get(sendCount).setText("\n닉네임이 '"+mynick+"'으로 변경되었습니다.");
				sendCount++;
				out.write("상대방의 닉네임이 \'"+mynick+"\'(으)로 변경되었습니다.\n");
				out.flush();
			}catch(Exception e2) {
				handleError(e2.getMessage());
			}
			finally {csPanel.getVerticalScrollBar().setValue(csPanel.getVerticalScrollBar().getMaximum());}
		}
	}
	//------------------------------------------------------------------------------------------------------
	public static void main(String[] args) {
		new ChatServer();
	}
}
