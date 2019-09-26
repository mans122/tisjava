package Ex_2;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;
import javax.swing.*;
import javax.swing.border.Border;
public class ChatClient extends JFrame implements ActionListener {
	private BufferedReader in = null;
	private BufferedWriter out = null;
	private Socket socket = null;
	private Receiver receiver = null;
	private JTextField sender = null;
	private JPanel	sPanel = new JPanel();
	private JTextField nickname = null;
	private static String mynick = "클라이언트";
	private static String serverNick = "서버";
	private static String[] array= new String[3];
	public ChatClient() {
		setTitle("클라이언트 채팅창");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container c = getContentPane();

		//c.setLayout(new BorderLayout());
		c.setLayout(new BorderLayout());
		receiver = new Receiver();
		receiver.setEditable(false);

		sPanel.setLayout(new BorderLayout());

		//보내는곳
		sender = new JTextField(20);
		sender.addActionListener(this);
		sPanel.add(sender,BorderLayout.CENTER);

		//닉네임
		nickname = new JTextField(5);
		nickname.setText("닉네임");
		nickname.addActionListener(this);
		sPanel.add(nickname,BorderLayout.WEST);


		c.add(new JScrollPane(receiver),BorderLayout.CENTER);
		c.add(sPanel,BorderLayout.SOUTH);
		//스티커 버튼 만들기 -------------------------------------------
		
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

	public void setupConnection() throws IOException{
		socket = new Socket("localhost",9999);
		receiver.append("서버에 연결 완료");
		int pos = receiver.getText().length();
		receiver.setCaretPosition(pos);

		in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
	}

	private static void handleError(String string) {
		System.out.println(string);
		System.exit(1);
	}

	private class Receiver extends JTextArea implements Runnable{
		@Override
		public void run() {
			String msg = null;
			while(true) {
				try {
					msg = in.readLine();
					if(msg.contains("상대방의 닉네임이")) {
						array = msg.split("'");
						serverNick = array[1];
					}
				}catch(IOException e) {
					handleError(e.getMessage());
				}
				this.append("\n"+msg);
				int pos = this.getText().length();
				this.setCaretPosition(pos);
			}
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == sender) {
			String msg = sender.getText();
			try {
				out.write(mynick+" >> "+msg+"\n");
				out.flush();

				receiver.append("\n"+mynick+": "+msg);
				int pos = receiver.getText().length();
				receiver.setCaretPosition(pos);
				sender.setText(null);
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
	public static void main(String[] args) {
		new ChatClient();
	}
}
