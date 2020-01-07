package socket;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) {
        try {

            // 1.服务器实例化一个 ServerSocket 对象，表示通过服务器上的端口通信。
            ServerSocket ss = new ServerSocket(8885);
            System.out.println("启动服务器....");

            // 2.服务器调用 ServerSocket 类的 accept() 方法，该方法将一直等待，直到客户端连接到服务器上给定的端口。
            // 5.在服务器端，accept() 方法返回服务器上一个新的 socket 引用，该 socket 连接到客户端的 socket。
            Socket s = ss.accept();

            BufferedReader br = new BufferedReader(new InputStreamReader(s.getInputStream()));
            //读取客户端发送来的消息
            String mess = br.readLine();
            System.out.println("我收到了客户端的东西" + mess);
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(s.getOutputStream()));

            String jsonParams = "服务端----专属，给你一份吧";
            bw.write(jsonParams + "\n");
            bw.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
