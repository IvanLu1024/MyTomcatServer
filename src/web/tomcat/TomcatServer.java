package web.tomcat;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Tomcat容器，循环接受用户请求，并作出响应
 *
 * Created by Ivan Lu on 2018/9/2.
 */
public class TomcatServer {

    public static void main(String[] args) {
        try {
            ServerSocket ss=new ServerSocket(8080);
            while (true){
                Socket socket=ss.accept();
                //开启一个线程，处理单次请求
                new Thread(new Handler(socket)).start();





            }
        }catch (IOException e){
            e.printStackTrace();
        }



    }
}
