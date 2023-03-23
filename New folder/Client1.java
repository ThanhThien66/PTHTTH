package Bai_TH3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class Client {
    public final static int serverPort = 5678;

    public static void main(String[] args) throws Exception {
        try {
            DatagramSocket clientSocket = new DatagramSocket();
            InetAddress server = InetAddress.getByName("localhost");
            System.out.println("Client duoc ket noi den Server thanh cong tai port " + serverPort + " va " + server);

            // chua du lieu gui
            byte[] readBuffer = new byte[4096];
            // chua du lieu nhan
            byte[] writeBuffer = null;

            while (true) {
                System.out.print("Nhap vao mot phuong thuc: ");
                BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
                String bString = read.readLine();

                writeBuffer = bString.getBytes();
                DatagramPacket InFormServer = new DatagramPacket(writeBuffer, writeBuffer.length, server, serverPort);
                clientSocket.send(InFormServer);

                DatagramPacket outFromServer = new DatagramPacket(readBuffer, readBuffer.length);
                clientSocket.receive(outFromServer);

                // lay yeu cau nhap n
                String doi = new String(outFromServer.getData(), 0, outFromServer.getLength());
                System.out.println(doi);

                // gui n den server
                String aString = read.readLine();
                writeBuffer = aString.getBytes();
                InFormServer = new DatagramPacket(writeBuffer, writeBuffer.length, server, serverPort);
                clientSocket.send(InFormServer);
                // nhan kq tu server
                outFromServer = new DatagramPacket(readBuffer, readBuffer.length);
                clientSocket.receive(outFromServer);
                String result = new String(outFromServer.getData(), 0, outFromServer.getLength());
                System.out.println("Ket qua nhan duoc: " + result);
            }
            // if (bString.compareTo("0") != 0) {
            //     System.out.println("Client da dong!");
            //     clientSocket.close();
            //     break;
            // }
        } catch (IOException e) {
            // TODO: handle exception
            System.out.println("Error: " + e);
        }
    }
}
