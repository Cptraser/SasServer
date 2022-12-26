import util.Password;

import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;


/**
 * @author SwordFlame
 */
public class SocketServer {
    public static void main(String[] args) {
        Password.insert("abc", "abc");
        try {
            ServerSocket serverSocket = new ServerSocket(8088);
            Socket socket = new Socket();

            while(true){
                socket = serverSocket.accept();
                ServerThread thread = new ServerThread(socket);
                thread.start();

                InetAddress address = socket.getInetAddress();
                System.out.println("当前客户端的IP："+address.getHostAddress());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
