//package Bai3;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public final static String SERVER_IP = "127.0.0.1";
    public final static int SERVER_PORT = 6789;

    

    public static void main(String[] args) {
        // Scanner sc = new Scanner(System.in);
		// System.out.println("Nhap dia chi host");
		// String SERVER_IP = sc.nextLine();
		// System.out.println("Nhap so port");
		// int SERVER_PORT = sc.nextInt();
        try {
            Socket s = new Socket(SERVER_IP, SERVER_PORT);
            System.out.println("Client created");

            DataInputStream dis = new DataInputStream(s.getInputStream());
            DataOutputStream dos = new DataOutputStream(s.getOutputStream());

            Scanner sc = new Scanner(System.in);

            while (true) {
                //Nhan menu tu Server
                String inputMenu = dis.readUTF();
                System.out.println("Server: " + inputMenu);

                //Chon phuong thuc
                System.out.print("Client: ");
                String x = sc.nextLine();
                dos.writeUTF(x);

                //Yeu cau nhap chuoi tu Server
                String str = dis.readUTF();
                System.out.println("Server: " + str);

                //Nhap chuoi
                System.out.print("->Input String: ");
                String inputStr = sc.nextLine();
                dos.writeUTF(inputStr);

                //Ket qua tu Server
                String kq = dis.readUTF();
                System.out.println("Server: " + kq);

            }

        } catch (IOException ie) {
            // TODO: handle exception
            System.out.println("Errorrr: " + ie);
        }
    }
}
