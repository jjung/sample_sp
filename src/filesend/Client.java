package filesend;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;

public class Client {
	public static void main(String[] args) throws IOException {

		File dir = new File("./INPUT");	
		File[] fileList = dir.listFiles();
		
		Socket soc = new Socket("127.0.0.1", 1234);
		OutputStream out = soc.getOutputStream();
		DataOutputStream datao = new DataOutputStream(out);
		
		for (File file : fileList){
			String filename = file.getName();
			
			InputStream fin = new FileInputStream(file); //inputstream은 filename, file 두가지 다 넣어서 쓸 수 있음
			
			byte[] buffer = new byte[1024];
			int len;
			
			datao.writeInt((int)file.length());
			datao.writeUTF(filename);
			
			while((len = fin.read(buffer)) != -1){
				out.write(buffer, 0 , len);
			}
			
			fin.close();
			
		}
		
		datao.close();
		out.close();
		soc.close();
		
		//readFile();
		
	}
	
	public static void readFile() throws IOException{
		
		InputStream input = new FileInputStream("./INPUT/INSP_006_20171123100000.TXT");
		
		BufferedReader fileReader = new BufferedReader (new InputStreamReader(input));
		
		String line;
		
		while ((line = fileReader.readLine()) != null){
			System.out.println("=====");
			String[] data = line.split("#");
			
			for(String str : data){
				System.out.println(str);
			}
		}
		
		
	}
}
