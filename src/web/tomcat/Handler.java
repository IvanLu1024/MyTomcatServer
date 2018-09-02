package web.tomcat;

import java.io.*;
import java.net.Socket;

/**
 * 用于处理HTTP请求的一个线程
 * Created by Ivan Lu on 2018/9/2.
 */
public class Handler implements Runnable {
    private Socket socket;

    public Handler(Socket socket) {
        this.socket = socket;
    }


    @Override
    public void run() {
        try {
            //读取请求头消息
            Reader in= new InputStreamReader(socket.getInputStream());
            BufferedReader br=new BufferedReader(in);
            String line=null;
            StringBuffer requestHeader=new StringBuffer();
            //按行循环读取
            while ((line=br.readLine())!=null&&line.length()>0){
                requestHeader.append(line);
            }
            System.out.println(requestHeader);


            //做出响应,遵循HTTP协议的格式
            OutputStream out = socket.getOutputStream();
            PrintWriter writer=new PrintWriter(out);
            writer.println("HTTP/1.1 200 OK");
            writer.println("Content-type:text/html;charset=utf-8");
            //注意空行
            writer.println();
            writer.flush();

            //自定义webapps的路径并利用文件IO流读取
            String webRoot="D:\\webapps\\demo01";
            String[] subString = requestHeader.toString().split(" ");
            if (subString.length>2){
                File file=new File(webRoot+subString[1]);
                FileInputStream fileInputStream=new FileInputStream(file);
                byte[] b=new byte[(int) file.length()];
                fileInputStream.read(b);
                out.write(b);
                out.close();
                fileInputStream.close();

            }
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
