package Bai_TH3;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class Server {
    public final static int serverPort = 5678;

    public static void main(String[] args) {
        try {
            DatagramSocket serverSocket = new DatagramSocket(serverPort);
            System.out.println("Server da duoc tao ! Dang cho Client ket noi vao ");

            // chua du lieu gui den
            byte[] readBuffer = new byte[4096];
            // chua du lieu di
            byte[] writeBuffer = null;
            do {
                System.out.println("\n=================MENU================");
                //System.out.println("\t1. Pig Latin");
                System.out.println("\t2. In hoa chu cai dau tien");
                System.out.println("\t3. Dao chuoi");
                System.out.println("\t4. Dem so luong tu trong chuoi");
                System.out.println("\t5. Dem so luong tu xuat hien trong chuoi - chua duoc");
                System.out.println("\t6. Dao tu trong chuoi");
                System.out.println("\t0. Exit de ket thuc\n");
                System.out.println("\n=====================================");

                DatagramPacket in = new DatagramPacket(readBuffer, readBuffer.length);
                serverSocket.receive(in);

                String bString = new String(in.getData(), 0, in.getLength());
                System.out.println("Client chon phuong thuc: " + bString);
                switch (bString) {
                    /*case "1": // piglatin
                        // gui goi tin den client
                        String yc = "Ban hay nhap vao mot chuoi hoac tu";
                        writeBuffer = yc.getBytes();
                        DatagramPacket outFromServer = new DatagramPacket(writeBuffer, writeBuffer.length,
                                in.getAddress(), in.getPort());
                        serverSocket.send(outFromServer);

                        // nhan tu client xu ly
                        DatagramPacket inFormClient = new DatagramPacket(readBuffer, readBuffer.length);
                        serverSocket.receive(inFormClient);

                        InetAddress host = inFormClient.getAddress();
                        int port = inFormClient.getPort();

                        String str = new String(inFormClient.getData(), 0, inFormClient.getLength());
                        System.out.println("Received : " + str);

                        char[] charArray = str.toCharArray();

                        // xu ly
                        int charArrayLength = charArray.length;
                        String result = "";

                        for (int i = 0; i < charArrayLength; i++) {
                            // 3. Nếu từ không chứa nguyên âm, giữ nguyên từ đó.
                            if (charArray[i] != 'a' && charArray[i] != 'e' && charArray[i] != 'o' && charArray[i] != 'i'
                                    && charArray[i] != 'u') {
                                result = str;
                            }
                        }

                        // 2. Nếu một từ bắt đầu bằng một nguyên âm, thêm cụm từ “way” vào cuối từ.
                        if (charArray[0] == 'a' || charArray[0] == 'e' || charArray[0] == 'o' || charArray[0] == 'i'
                                || charArray[0] == 'u') {
                            result = str + "way";
                        }
                        for (int i = 0; i < charArrayLength; i++) {
                            if (charArray[0] == 'b') {
                                result = str + charArray[0] + "ay";
                                result = result.substring(1);

                            } else {
                                // neu co them cac phu am se xu ly khac
                                if (charArray[0] == 'g' && charArray[1] == 'r') {
                                    result = str + charArray[0] + charArray[1] + "ay";
                                    result = result.substring(2);
                                }
                            }
                        }

                        // ket qua nhan duoc khi xu ly
                        System.out.println("Trans : " + result);

                        DatagramPacket out = new DatagramPacket(result.getBytes(), result.length(), host, port);
                        serverSocket.send(out);
                        break;*/
                    case "2": // in hoa chu cai dau tien
                        // gui goi tin den client
                        String y = "Nhap vao mot chuoi: ";
                        writeBuffer = y.getBytes();
                        outFromServer = new DatagramPacket(writeBuffer, writeBuffer.length,
                                in.getAddress(), in.getPort());
                        serverSocket.send(outFromServer);

                        // nhan tu client xu ly
                        inFormClient = new DatagramPacket(readBuffer, readBuffer.length);
                        serverSocket.receive(inFormClient);

                        host = inFormClient.getAddress();
                        port = inFormClient.getPort();

                        str = new String(inFormClient.getData(), 0, inFormClient.getLength());
                        System.out.println("Received : " + str);

                        // xu ly
                        if (str.charAt(0) != ' ') {
                            str = str.substring(0, 1).toUpperCase().concat(str.substring(1));
                        }
                        for (int i = 0; i < str.length(); i++) {
                            if (str.charAt(i) == ' ') {
                                int vt = i + 1;
                                String s1 = str.substring(0, vt);
                                String s2 = str.substring(vt, vt + 1).toUpperCase();
                                String s3 = str.substring(vt + 1);
                                str = s1.concat(s2.concat(s3));
                            }
                        }
                        // ket qua nhan duoc khi xu ly
                        System.out.println("Ket qua sau khi xu ly : " + str);

                        out = new DatagramPacket(str.getBytes(), str.length(), host, port);
                        serverSocket.send(out);

                        break;
                    case "3": // dao chuoi - dao tu
                        // gui goi tin den client
                        String c = "Nhap vao mot chuoi: ";
                        writeBuffer = c.getBytes();
                        outFromServer = new DatagramPacket(writeBuffer, writeBuffer.length,
                                in.getAddress(), in.getPort());
                        serverSocket.send(outFromServer);

                        // nhan tu client xu ly
                        inFormClient = new DatagramPacket(readBuffer, readBuffer.length);
                        serverSocket.receive(inFormClient);

                        host = inFormClient.getAddress();
                        port = inFormClient.getPort();

                        str = new String(inFormClient.getData(), 0, inFormClient.getLength());
                        System.out.println("Received : " + str);

                        // xu ly
                        StringBuffer temp = new StringBuffer(str);
                        String str2 = temp.reverse().toString();
                        // ket qua nhan duoc khi xu ly
                        System.out.println("Ket qua sau khi xu ly : " + str2);

                        out = new DatagramPacket(str2.getBytes(), str2.length(), host, port);
                        serverSocket.send(out);
                        break;
                    case "4":
                        // gui goi tin den client
                        String ch = "Nhap vao mot chuoi: ";
                        writeBuffer = ch.getBytes();
                        outFromServer = new DatagramPacket(writeBuffer, writeBuffer.length,
                                in.getAddress(), in.getPort());
                        serverSocket.send(outFromServer);

                        // nhan tu client xu ly
                        inFormClient = new DatagramPacket(readBuffer, readBuffer.length);
                        serverSocket.receive(inFormClient);

                        host = inFormClient.getAddress();
                        port = inFormClient.getPort();

                        str = new String(inFormClient.getData(), 0, inFormClient.getLength());
                        System.out.println("Received : " + str);

                        // xu ly
                        int count = 0;
                        char tempp[] = new char[str.length()];
                        for (int i = 0; i < str.length(); i++) {
                            tempp[i] = str.charAt(i);
                            if (((i > 0) && (tempp[i] != ' ') && (tempp[i - 1] == ' ')) || ((tempp[0] != ' ') && (i == 0)))
                                count++;
                        }
                        // ket qua nhan duoc khi xu ly
                        System.out.println("Ket qua sau khi xu ly : " + count);

                        String s = String.valueOf(count);

                        out = new DatagramPacket(s.getBytes(), s.length() , host, port);
                        serverSocket.send(out);
                        break;
                    // case "5":
                    //     byte[] writeBuffer2 = null;
                    //     // gui goi tin den client
                    //     String input  = "Nhap vao mot chuoi: ";
                    //     writeBuffer = input.getBytes();
                    //     String kytu = "Nhap tu can dem";
                    //     writeBuffer2 = kytu.getBytes();
                    //     outFromServer = new DatagramPacket(writeBuffer, writeBuffer.length,
                    //             in.getAddress(), in.getPort());
                    //     DatagramPacket outFromServer2 = new DatagramPacket(writeBuffer2, writeBuffer2.length,
                    //             in.getAddress(), in.getPort());
                    //     serverSocket.send(outFromServer);
                    //     serverSocket.send(outFromServer2);

                    //     // nhan tu client xu ly
                    //     inFormClient = new DatagramPacket(readBuffer, readBuffer.length);
                    //     serverSocket.receive(inFormClient);

                    //     host = inFormClient.getAddress();
                    //     port = inFormClient.getPort();

                    //     str = new String(inFormClient.getData(), 0, inFormClient.getLength());
                    //     System.out.println("Received : " + str);

                    //     //xu ly
                    //     int dem = 0;
                    //     for (int i = 0; i < str.length(); i++) {
                    //         if(str.charAt(i) == kytu.charAt(i)){
                    //             dem++;
                    //         }
                    //     }

                    //     String a[] = str.split("regex");

                    //     for (int i = 0; i < a.length; i++) {
                    //         if (kytu.equals(a[i])) {
                    //             dem++;
                    //         }
                    //     }

                    //     System.out.println("Ket qua sau khi xu ly : " + dem);

                    //     String modified = String.valueOf(dem);

                    //     out = new DatagramPacket(modified.getBytes(), modified.length() , host, port);
                    //     serverSocket.send(out);

                    //     break;
                    case "6":
                        // gui goi tin den client
                        String chuoi = "Nhap vao mot chuoi: ";
                        writeBuffer = chuoi.getBytes();
                        outFromServer = new DatagramPacket(writeBuffer, writeBuffer.length,
                                in.getAddress(), in.getPort());
                        serverSocket.send(outFromServer);

                        // nhan tu client xu ly
                        inFormClient = new DatagramPacket(readBuffer, readBuffer.length);
                        serverSocket.receive(inFormClient);

                        host = inFormClient.getAddress();
                        port = inFormClient.getPort();

                        str = new String(inFormClient.getData(), 0, inFormClient.getLength());
                        System.out.println("Received : " + str);

                        // xu ly
                        String[] words = str.split(" ");
                        String daoString = "";
                        for (int i = 0; i < words.length; i++) {
                            String word = words[i];
                            String daoWord = "";
                            for (int j = word.length() - 1; j >= 0; j--) {
                                daoWord = daoWord + word.charAt(j);
                            }
                            daoString = daoString + daoWord + " ";
                        }
                        // ket qua nhan duoc khi xu ly
                        System.out.println("Ket qua sau khi xu ly : " + daoString);

                        out = new DatagramPacket(daoString.getBytes(), daoString.length() , host, port);
                        serverSocket.send(out);
                        break;
                    case "0":
                        System.out.println("Server da dong!");
                        serverSocket.close();
                        System.exit(0);
                        break;
                    default:
                        System.exit(0);
                        break;
                }
            } while (true);
        } catch (IOException e) {
            // TODO: handle exception
            e.printStackTrace();
        }
    }
}
