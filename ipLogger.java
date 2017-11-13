//このファイルはダミー。見ればわかるがそのままでは使えない。
import java.util.*;//Vectorを使うためインポート
class ipLogger{
	//ここから下をコピ、Server.javaのrun()内のtry前にペ。
	//Serverクラスのフィールドに静的VectorクラスipLoggerを宣言(インスタンスを生成)して組み込む。
	String ipAddress = s.getInetAddress().getHostAddress();
	int connection_count=0;
	ipLogger.add(ipAddress);
	for(int i=0; i<ipLogger.size(); i++){
		if(ipAddress.equals(ipLogger.elementAt(i))){
			connection_count++;
		}
	}
	System.out.println("Host-IP-Address: " + ipAddress + "/ Connection-Count: " + connection_count);
	//ここまで
}
