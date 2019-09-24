package Ex_2;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.*;
import java.io.*;
import java.net.*;
import javax.swing.*;

//BorderLayout에서 Center에 받는메시지 출력창
//SOUTH에 패널하나 올리고 그위에 TextField2개로 닉네임,보낼메시지 입력
//CENTER에 
public class ChatServer extends JFrame implements ActionListener{
	public static ImageIcon[] image;
	private JLabel imageLabel = new JLabel();
	private BufferedReader in = null;
	private BufferedWriter out = null;
	private ServerSocket listener = null;
	private Socket socket = null;
	private Receiver receiver;
	private JTextField sender;
	private JTextField nickname=null;
	private JScrollPane	csPanel = new JScrollPane();
	private JPanel	cPanel = new JPanel();
	private JPanel	sPanel = new JPanel();
	private static String mynick = "서버";
	private static String clientNick = "클라이언트";
	private static String[] array= new String[3];
	public ChatServer() {
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
		cPanel.setLayout(new GridLayout(4,1,100,0));
		cPanel.setBackground(Color.GREEN);
		JButton bt1 = new JButton("테스트");
		JLabel text1 = new JLabel();
		JLabel text2 = new JLabel();
		JLabel text3 = new JLabel();
		System.out.println(bt1.getPreferredSize().width);
		int x = bt1.getPreferredSize().width;
		System.out.println(bt1.getPreferredSize().height);
		int y = bt1.getPreferredSize().height;
		bt1.setPreferredSize(new Dimension(x, y));
		bt1.setSize(new Dimension(x, y));
		bt1.setSize(x, y);
		bt1.setSize(200, 200);
		
		text1.setText("가나다라마바사");
		text2.setText("테스트2");
		text3.setText("테스트3");
		text1.setFont(new Font("Gothic",Font.ITALIC,15));
		text2.setFont(new Font("Gothic",Font.ITALIC,15));
		cPanel.add(bt1);
		cPanel.add(text1);
		cPanel.add(text2);
		cPanel.add(text3);
		
		//c.add(cPanel,BorderLayout.CENTER);
		
		
		//받는 메시지 보여주는 창
		receiver = new Receiver();
		receiver.setEditable(false);
		
		
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
		
		csPanel = new JScrollPane(cPanel);
		c.add(csPanel,BorderLayout.CENTER);
		setSize(400, 200);
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
		receiver.append("클라이언트로부터 연결 완료");
		int pos = receiver.getText().length();
		receiver.setCaretPosition(pos);

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
	private class Receiver extends JTextArea implements Runnable{
		@Override
		public void run() {
			String msg = null;
			while(true) {
				try {
					msg = in.readLine();
					if(msg.contains("상대방의 닉네임이")) {
						array = msg.split("'");
						clientNick = array[1];
					}
				}catch(IOException e) {
					handleError(e.getMessage());
				}
				this.append("\n"+clientNick+" : "+msg);
				int pos = this.getText().length();
				this.setCaretPosition(pos);
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
					//System.out.println(getIcon);
					for(int i=0;i<ImgFolder.fileNum;i++) {
						if(ImgFolder.fileName.get(i).contains(getIcon)) {
							//System.out.println(ImgFolder.fileName.get(i));
							imageLabel.setIcon(new ImageIcon(ImgFolder.fileName.get(i)));
							System.out.println(ImgFolder.fileName.get(i));
							int pos = receiver.getText().length();
							receiver.setCaretPosition(pos);
							sender.setText(null);
						}
					}
					
				}
				else{
				out.write(msg+"\n");
				out.flush();

				receiver.append("\n"+mynick+": "+msg);
				int pos = receiver.getText().length();
				receiver.setCaretPosition(pos);
				sender.setText(null);
				}
			}catch(IOException e1) {
				handleError(e1.getMessage());
			}
		}else if(e.getSource() == nickname) {
			mynick = nickname.getText();
			try {
				receiver.append("\n닉네임이 '"+mynick+"'으로 변경되었습니다.");
				int pos = receiver.getText().length();
				receiver.setCaretPosition(pos);
				out.write("상대방의 닉네임이 \'"+mynick+"\'(으)로 변경되었습니다.\n");
				out.flush();
			}catch(Exception e2) {
				handleError(e2.getMessage());
			}
		}
	}
//------------------------------------------------------------------------------------------------------
	public static void main(String[] args) {
		new ChatServer();
	}
}
