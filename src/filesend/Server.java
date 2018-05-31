package filesend;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	public static void main(String[] args) throws IOException {
		
		ServerSocket server = new ServerSocket(1234);
		
		File outputDir = new File("./OUTPUT");
		if (!outputDir.exists()){
			outputDir.mkdirs();
		}
		
		Socket listener = server.accept();
		InputStream input = listener.getInputStream();
		DataInputStream datain = new DataInputStream(input);
		
		int data = 0; // 읽어들이는 횟수
		while(true){
			byte[] buffer = new byte[1024];
			
			int filesize = datain.readInt();
			String filename = datain.readUTF();
			
			
			FileOutputStream out = new FileOutputStream("./OUTPUT" + filename);
			
			while (filesize > 0) {
				data = input.read(buffer, 0, Math.min(filesize, buffer.length));
				filesize -= data;
				out.write(buffer, 0, data);
			}
			
			out.flush();
			out.close();
		}

	}
}
