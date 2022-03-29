package TCPJson;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.UUID;

import action.OnMessageListener;
import action.OnMessageSend;



public class Session extends Thread implements OnMessageListener,OnMessageSend{
	
	private Socket socket;
	
	private BufferedWriter bw;
	private BufferedReader br;
	

	public Session(Socket socket) {
		this.socket = socket;
	}

	@Override
	public void run() {
		try {
			bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
			br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
	
	

	@Override
	public void messageSend(String message) {
		new Thread(() -> {
			try {
				bw.write(message+"\n");
				bw.flush();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}).start();
	
	}

	@Override
	public String messageListener() {
		String message= ""; //String message = new String();
		try {
		message = br.readLine();
		System.out.println(message);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return message;
	}
}

