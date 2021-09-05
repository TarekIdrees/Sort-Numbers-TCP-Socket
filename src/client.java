import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
public class client {
	
	public static void main(String[] args) {
		InetAddress ip;
		try {
			ip = InetAddress.getByName("localhost");
			Socket  clientSocket=new Socket(ip,5092);
			
			
			Scanner scanner = new Scanner(System.in);
			DataInputStream input =  new DataInputStream(clientSocket.getInputStream());
			DataOutputStream output = new DataOutputStream(clientSocket.getOutputStream());
			
			String 	conn = input.readUTF();
			System.out.println("Server  : "+conn); 
			
			while(true) {
				String ask =input.readUTF();
				System.out.println("Server : "+ask);
				
				String request1 = scanner.nextLine();
				output.writeUTF(request1);
				
				// split comma from input and convert to integer 
				String [] arrOfStr = request1.split(",");
				
				
				if(request1.equalsIgnoreCase("close"))
				{
					clientSocket.close();
					System.out.println("Connection is closed");
					break;
				}
				else if(!request1.equals("close")&& arrOfStr[0].matches("\\d"))
				{
					String reply =input.readUTF();
					System.out.println("Server : "+reply); 
					
					
					String request2=scanner.nextLine();
					output.writeUTF(request2);
					
					if(request2.equalsIgnoreCase("1"))
					{
						String replay2=input.readUTF();
						System.out.println("Server  : "+replay2);
					
					}
					else
					{
						String replay2=input.readUTF();
						System.out.println("Server : "+replay2);
					}
				
				}
				else
				{
					String replay2=input.readUTF();
					System.out.println("Server : "+replay2);
				}
			}
			
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} 

	}
}