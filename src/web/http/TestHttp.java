package web.http;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 简单模拟Http协议的实现原理
 * 充当Tomcat服务器，处理浏览器的请求
 * 浏览器--客户端
 * Created by Ivan Lu on 2018/9/2.
 */
public class TestHttp {
    public static void main(String[] args) throws IOException{
        ServerSocket serverSocket=new ServerSocket(8080);
        Socket socket = serverSocket.accept();
        //读取请求头消息
        Reader in= new InputStreamReader(socket.getInputStream());
        BufferedReader br=new BufferedReader(in);
        String line=null;
        //按行循环读取
        while ((line=br.readLine())!=null&&line.length()>0){
            System.out.println(line);
        }

        //做出响应,遵循HTTP协议的格式
        PrintWriter writer=new PrintWriter(socket.getOutputStream());
        writer.println("HTTP/1.1 200 OK");
        writer.println("Content-type:text/html;charset=utf-8");
        //注意空行
        writer.println();

        writer.println("<h1>Hello Ivan</h1>");
        writer.flush();
        writer.close();
        br.close();
        in.close();
        socket.close();
        serverSocket.close();





    }
}
