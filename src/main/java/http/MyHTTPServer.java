package http;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MyHTTPServer {

    public static void main(String[] args) throws Exception {

        int port = 8000;
        ServerSocket serverSocket = new ServerSocket(port);
        System.out.println("启动服务，绑定端口： " + port);

        // 创建一个线程池
        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(30);  // 5：线程池。

        while (true) {  // 6：这个循环不停监听socket连接，使用SocketHandler处理连入的socket，而这个处理是放在线程池中的。

            Socket clientSocket = serverSocket.accept();
            System.out.println("新的连接" + clientSocket.getInetAddress() + ":" + clientSocket.getPort());

            try {
                fixedThreadPool.execute(new SocketHandler(clientSocket));
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }
}
