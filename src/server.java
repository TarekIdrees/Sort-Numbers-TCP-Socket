import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner; 

public class server {

	public static void main(String[] args) {
		try {
			ServerSocket serverSocket = new ServerSocket(5092);
			System.out.println("System is booted up and waiting for clients to conect");
			
			while(true) {
				Socket clientSocket= serverSocket.accept();
			    System.out.println(" A New client is  now connected to the server ");
		
			    session clientSession = new session(clientSocket);
			    clientSession.start();
			} 
	}catch (IOException e) {
			e.printStackTrace();
		}

	}


}
