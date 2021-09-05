import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import java. util. Arrays;
import java.util.Collections;

public class session extends Thread{
	void rvereseArray(int[] arr) {
	   int start = 0; 
	    int end = arr.length - 1; 
	    while (start < end) {
	        int temp = arr[start];
	        arr[start] = arr[end];
	        arr[end] = temp;
	        start = start + 1;
	        end = end - 1;
	    }
	}
	Socket clientSocket;
	public session(Socket clientSocket) {
		this.clientSocket=clientSocket;
	}
	

	@Override
	public void run() {
		Scanner scanner = new Scanner(System.in);
		DataInputStream input;
		try {
			
		input = new DataInputStream(clientSocket.getInputStream());
		DataOutputStream output = new DataOutputStream(clientSocket.getOutputStream());

     		output.writeUTF("connected");
	
		while(true) {
			
            output.writeUTF("Please enter the list of numbers to be arranged or 'close' to close the connection \n");
			
			String request1 =input.readUTF(); 
			System.out.println("client   : "+request1);
			
			// split comma from input and convert to integer 
			String [] arrOfStr = request1.split(",");
			
			if(request1.equalsIgnoreCase("close"))
			{
				clientSocket.close();
				System.out.println("Connection  with this client is closed");
				break;
			}
			
			else if (!request1.equalsIgnoreCase("close")&&arrOfStr[0].matches("\\d"))
			{
				
			  while(true)
			   {
				
				
				output.writeUTF("Please choose:\n"+ "1. Ascending order\n"+ "2. Descending order.");
				
				String reply =input.readUTF();
				System.out.println("client   : "+reply);
				
				if(reply.equals("1")|| reply.equals("2"))
				{
					int size=arrOfStr.length;
					int [] arr=new int [size];
					for(int i=0;i<size;i++)
					{
						arr[i]=Integer.parseInt(arrOfStr[i]);
					}
					Arrays.sort(arr);
					if(reply.equals("1"))
					{
						StringBuilder stringBuilder = new StringBuilder();
						for (int i = 0; i < arr.length; i++) {
							if(i==0)
							{
								stringBuilder.append("[");
							}
						    stringBuilder.append(arr[i]);
						    if(i==(arr.length)-1)
						    {
						    	stringBuilder.append("]");
						    	break;
						    }
						    stringBuilder.append(",");
						}
						String arrS = stringBuilder.toString();
						output.writeUTF(arrS);
						break;
					}
					else if (reply.equals("2"))
					{
						rvereseArray(arr);
						StringBuilder stringBuilder = new StringBuilder();
						for (int i = 0; i < arr.length; i++) {
							if(i==0)
							{
								stringBuilder.append("[");
							}
						    stringBuilder.append(arr[i]);
						    if(i==(arr.length)-1)
						    {
						    	stringBuilder.append("]");
						    	break;
						    }
						    stringBuilder.append(",");
						}
						String arrS = stringBuilder.toString();
						output.writeUTF(arrS);
						break;
					}
				}
				else
				{
					output.writeUTF("wrong choice choose again 1 or 2 ");
				}
			  }
			}
			else 
			{
				output.writeUTF("wrong choice enter valid array ");
			}
		 }
	  } catch (IOException e) {
			e.printStackTrace();
		}
   }
}
