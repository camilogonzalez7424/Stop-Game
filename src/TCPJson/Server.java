package TCPJson;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import model.GameRules;

public class Server {


	public static void main(String[] args) throws IOException {
		Server server = Server.getInstace();
		server.StartServer();
	}

	private ArrayList<Session> sessions;
	private static Server instance;

	private Server() throws IOException {
	}
	
	public void StartServer() {
		sessions = new ArrayList<>();
		ServerSocket server;
		try {
			server = new ServerSocket(6000);
			while (true) {
				System.out.println("Esperando clientes...");
				Socket socket = server.accept();
				
				Session session = new Session(socket);
				//session.setListener(this);
				session.start();
				sessions.add(session);
				
				if(sessions.size()%2==0) {
					new Thread(() -> {
						GameRules game = new GameRules(sessions.get(sessions.size()-2),sessions.get(sessions.size()-1));
					}).start();	
				
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	public static Server getInstace() {
		if(instance == null) {

			try {
				instance = new Server();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return instance;
	}
	

	
}
