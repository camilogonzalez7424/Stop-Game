package TCPJson;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Emisor {
	private static Socket socket;
	private static Scanner sc;
	private static BufferedWriter bw;
	public static void main(String[] args) {
		
		
		sc = new Scanner(System.in);
		while(true) {
			String line = sc.nextLine();
			try {
				socket = new Socket("127.0.0.1",6000);
				bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
				new BufferedReader(new InputStreamReader(socket.getInputStream()));
				bw.write(line+"\n");
				bw.flush();
			} catch (UnknownHostException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
