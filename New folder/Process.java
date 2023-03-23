//package tcp.thuc_hanh2;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Iterator;

public class Process extends Thread{
	public static final char SPACE = ' ';
	public static int i,count = 0;
	
	private Socket socket;

	public Process(Socket socket) {
		this.socket = socket;
	}
	
	public static int coutWords(String input) {
		char ch[]= new char[input.length()];
		for(i=0;i<input.length();i++)
        {
            ch[i]= input.charAt(i);
            if( ((i>0)&&(ch[i]!=' ')&&(ch[i-1]==' ')) || ((ch[0]!=' ')&&(i==0)) )
            count++;
        }
		return count;
	}
	
	public static String reverseString(String input) {
		return new StringBuffer(input).reverse().toString();
	}

	 public static String daoTuTrongChuoi(String input) {
        String[] words = input.split(" "); 
        String daoString = "";
        for (int i = 0; i < words.length; i++)
        {
            String word = words[i];
            String daoWord = "";
            for (int j = word.length()-1; j >= 0; j--)
            {
                daoWord = daoWord + word.charAt(j);
            }
            daoString = daoString + daoWord + " ";
        }
        return daoString;
    }
	public String inHoaTu(String input){
        //Chuyển đổi a thành một mảng kiểu char
        char[] charArray = input.toCharArray();
        boolean foundSpace = true;
        //Duyệt các phần tử trong charArray
        for(int i = 0; i < charArray.length; i++) {
        //Nếu mảng là 1 chữ cái
        if(Character.isLetter(charArray[i])) {
            //Kiểm tra khoảng trắng
            if(foundSpace) {
            //Đổi chữ cái thành chữ in hoa 
            charArray[i] = Character.toUpperCase(charArray[i]);
            foundSpace = false;
            }
        }
        else {
            foundSpace = true;
        }
        }
        input = String.valueOf(charArray);
            return input;
    }
	public String DemTu(String input){
		System.out.println("Nhập vào 1 số nguyên bất kỳ: ");
    int number = scanner.nextInt();
         
    for (int i = 0; i < n; i++) {
        if (A[i] == number) {
            count++;
        }
    }
         
    System.out.println("Số phần tử " + number + " có trong mảng = " + count);
	}
	public void run() {
		try {
			while (true) {
				InputStream is = socket.getInputStream();
				OutputStream os = socket.getOutputStream();
				DataInputStream dis = new DataInputStream(is);
				DataOutputStream dos = new DataOutputStream(os);
				
				String chon = dis.readUTF();
				String chuoi = " ";
				
				switch (chon) {
				case "1":
					chuoi = dis.readUTF();
					int tu = coutWords(chuoi);
					dos.write(tu);
					break;
				case "2":
					chuoi=dis.readUTF();
					String reverse = reverseString(chuoi);
                    dos.writeUTF(reverse);
				case "3":
					chuoi=dis.readUTF();
					String dao = daoTuTrongChuoi(chuoi);
                    dos.writeUTF(dao);
				case "4":
					chuoi=dis.readUTF();
					String in = inHoaTu(chuoi);
                    dos.writeUTF(in);
				default:
					break;
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}
