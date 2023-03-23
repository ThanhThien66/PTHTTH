//package Bai3;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Server1 {
    public final static int SERVER_PORT = 6789;

    public static void main(String[] args) {

        try {
            ServerSocket ss = new ServerSocket(SERVER_PORT);
            System.out.println("Server created...");

            Scanner sc = new Scanner(System.in);

            while (true) {
                Socket s = ss.accept();
                System.out.println("Client connected");

                DataInputStream dis = new DataInputStream(s.getInputStream());
                DataOutputStream dos = new DataOutputStream(s.getOutputStream());

                do {
                    // Gửi menu khi có Client kết nối

                    String menu = "\t---------------MENU---------------" +
                            "\n\t\t1. Dem so luong tu trong chuoi ki tu" +
                            "\n\t\t2. Dao nguoc chuoi ki tu" +
                            "\n\t\t----------------------------------";
                    dos.writeUTF(menu);
                    String inputMenuFromClient = dis.readUTF();
                    int luachon = Integer.parseInt(inputMenuFromClient);// client gui lua chon

                    switch (luachon) {
                        case 1:

                            if (inputMenuFromClient.equals("Exit")) {
                                System.out.println("Client send \"exit\"");
                                s.close();
                            } else {
                                System.out.println("Client da chon phuong thuc: " + luachon);
                                // dos.println("Running 1...");
                                String strDem = "Hay nhap chuoi ki tu can dem";
                                dos.writeUTF(strDem);
                                // Nhận chuỗi kí tự cần xử lý
                                String strInputDem = dis.readUTF();

                                dos.writeUTF("Do dai chuoi la: " + strInputDem.length());
                                break;
                            }

                        case 2:

                            if (inputMenuFromClient.equals("Exit")) {
                                s.close();
                            } else {
                                System.out.println("Client da chon phuong thuc: " + luachon);
                                dos.writeUTF("Hay nhap chuoi ki tu can dao nguoc");

                                String str2 = dis.readUTF();
                                StringBuilder sb = new StringBuilder(str2);
                                dos.writeUTF("Chuoi sau khi duoc dao: " + sb.reverse().toString());
                                break;
                            }
                    }

                } while (true);
                /*
                 * ss.close();
                 * s.close();
                 * dis.close();
                 * dos.close();
                 */
            }

        } catch (IOException ie) {
            // TODO: handle exception
            System.out.println("Error: " + ie);
        }
    }
}
