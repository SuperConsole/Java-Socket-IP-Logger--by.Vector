import java.io.*;
import java.net.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
class AnimClient{
  public static void main(String[] args){
		try{
			AppFrame2 f = new AppFrame2(args[0]);
			f.setSize(360,480);
			f.addWindowListener(new WindowAdapter(){
			@Override public void windowClosing(WindowEvent e){
				System.exit(0);
			}});
			f.setVisible(true);
		}
		catch(Exception e){
			System.out.println("Exception: " + e);
		}
	}
}

class AppFrame2 extends Frame{
	ImageSocket imgsocket;
	AppFrame2(String hostname){
		imgsocket = new ImageSocket(hostname);
	}
	@Override public void update(Graphics g){
		paint(g);
	}
	@Override public void paint(Graphics g){
		Image img = imgsocket.loadNextFrame();
		if(img != null){
			g.drawImage(img,100,100,160,120,this);
			repaint(1);
		}
	}
}

class ImageSocket{
	BufferedInputStream biStream;
	BufferedImage bImage;
	byte buf[];
	int port = 8004;
	ImageSocket(String hostname){
	buf = new byte[160*120];
	bImage = new BufferedImage(160,120,BufferedImage.TYPE_BYTE_GRAY);
	try{
		Socket s = new Socket(hostname,port);
		InputStream is = s.getInputStream();
		biStream = new BufferedInputStream(is);
	}
	catch(IOException e){
		System.err.println("Exception:"+e);
	}
}
  Image loadNextFrame(){
	try{
		Thread.sleep(30);
		int b = 0;
		while(b<160*120){
		b+= biStream.read(buf,b,160*120 - b);
		//System.out.println("size: " + b);
		}
		int x,y,pixel;
		for(y=0;y<120;y++){
			for(x=0;x<160;x++){
				pixel = (int)buf[y*160+x]*2;
				if(pixel<0){
					biStream.close();
					System.out.println("Done");
					System.exit(0);
					return null;
				}
				pixel = new Color(pixel,pixel,pixel).getRGB();
				bImage.setRGB(x,y,pixel);
			}
		}
	}
	catch(Exception e){
		System.out.println("exception:"+e);
	}
		return bImage;
	}
}
