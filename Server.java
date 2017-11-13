import java.io.*;
import java.net.*;
import java.util.*;
@SuppressWarnings("unchecked")
public class Server extends Thread{
	static Vector ipLogger = new Vector();
	Socket s;
	static int clientCount=0;
	Server(Socket s){
		this.s = s;
	}
	public static void main(String args[]){
		try{
			int port = 8000;
			ServerSocket ss = new ServerSocket(port);
			while(true){
				System.out.println("Running...");
				Socket s = ss.accept();
				clientCount++;
				new Server(s).start();
				System.out.println("Connecting Number: " + clientCount);
			}
		}catch(Exception e){
			System.err.println("Exception: "+e);
			e.printStackTrace();
		}
	}
	@Override public void run(){
		String ipAddress = s.getInetAddress().getHostAddress();
		int connection_count=0;
		ipLogger.add(ipAddress);
		for(int i=0; i<ipLogger.size(); i++){
			if(ipAddress.equals(ipLogger.elementAt(i))){
				connection_count++;
			}
		}
		System.out.println("Host-IP-Address: " + ipAddress + "/ Connection-Count: " + connection_count);
		try{
			Thread.sleep(100);
			byte buf[] = new byte[160*120];
			BufferedInputStream biStream = new BufferedInputStream(new FileInputStream("bane.raw"));
			DataOutputStream dos=new DataOutputStream(s.getOutputStream());
			while(true){
				biStream.read(buf, 0, 160*120);
				dos.write(buf, 0, 160*120);
				if((int)buf[0] < 0){
					s.close();
					clientCount--;
					System.out.println("Connecting Number: " + clientCount);
					break;
				}
			}
		}catch(IOException e){
			System.err.println("IOException: "+e);
		}catch(Exception e){
			System.err.println("Exception: "+e);
		}
	}
}
