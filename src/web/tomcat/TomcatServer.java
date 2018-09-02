package web.tomcat;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Tomcat容器，循环接受用户请求，并作出响应
 *
 *
 * Created by Ivan Lu on 2018/9/2.
 */
public class TomcatServer {

    public static void main(String[] args) {
        try {
            /*
            开启一个端口号为8080，
            服务器套接字等待通过网络进入的请求。 它根据该请求执行一些操作，然后可能将结果返回给请求者。
             */
            ServerSocket ss=new ServerSocket(8080);
            //死循环，不断接受请求
            while (true){
                Socket socket=ss.accept();
                //开启一个线程，处理一次请求
                new Thread(new Handler(socket)).start();
            }
        }catch (IOException e){
            e.printStackTrace();
        }



    }
}
