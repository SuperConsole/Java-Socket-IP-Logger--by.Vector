import java.io.*;
import java.net.*;
import java.util.*;
@SuppressWarnings("unchecked")
public class Server extends Thread{/*Default*/}
	@Override public void run(){
		//IP Logger in Vector
		String ipAddress = s.getInetAddress().getHostAddress();
		int connection_count=0;
		ipLogger.add(ipAddress);
		for(int i=0; i<ipLogger.size(); i++){
			if(ipAddress.equals(ipLogger.elementAt(i))){
				connection_count++;
			}
		}
		System.out.println("Host-IP-Address: " + ipAddress + "/ Connection-Count: " + connection_count);
	}
