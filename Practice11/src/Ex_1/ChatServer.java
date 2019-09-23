package Ex_1;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.*;
import java.io.*;
import java.net.*;
import javax.swing.*;
public class ChatServer extends JFrame implements ActionListener{
	private BufferedReader in = null;
	private BufferedWriter out = null;
	private ServerSocket listener = null;
	private Socket socket = null;
	private Receiver receiver;
	private JTextField sender;
	private JTextField nickname=null;
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

		receiver = new Receiver();
		receiver.setEditable(false);

		nickname = new JTextField(5);
		nickname.setText("닉네임");
		nickname.addActionListener(this);
		sPanel.add(nickname,BorderLayout.WEST);

		sender = new JTextField(20);
		sender.addActionListener(this);
		sPanel.add(sender,BorderLayout.CENTER);

		c.add(new JScrollPane(receiver),BorderLayout.CENTER);
		c.add(sPanel,BorderLayout.SOUTH);

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
					}
				}catch(IOException e) {
					handleError(e.getMessage());
				}
				this.append("\n"+clientNick+" : "+msg);
				int pos = this.getText().length();
				this.setCaretPosition(pos);
				clientNick = array[1];
			}
		}
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == sender) {
			String msg = sender.getText();
			try {
				out.write(msg+"\n");
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
		new ChatServer();
	}
}
