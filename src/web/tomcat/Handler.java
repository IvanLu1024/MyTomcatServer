package web.tomcat;

import java.io.*;
import java.net.Socket;

/**
 * 用于处理HTTP请求的一个线程
 *
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
            //读取请求头
            //1.从socket中获取输入流
            Reader reader=new InputStreamReader(socket.getInputStream());
            //2.从输入流中按行获取
            BufferedReader bufferedReader=new BufferedReader(reader);
            String line=null;

            //3.按行循环读取，并加入到缓存中
            StringBuffer requestHeader=new StringBuffer();
            while ((line = bufferedReader.readLine())!=null&&line.length()>0){
                requestHeader.append(line);
            }
            System.out.println(requestHeader);

            //作出响应
            //1.从socket中获取输出流
            OutputStream outputStream = socket.getOutputStream();
            Writer writer=new OutputStreamWriter(outputStream);
            PrintWriter out=new PrintWriter(writer);
            //2.按照HTTP协议的格式，写出响应
            out.println("HTTP/1.1 200 OK");
            out.println("Content-type:text/html;charset:utf-8");
            out.println();
            out.flush();

            //3.读取自定义的webapps的目录下的文件
            String webRoot="D:\\webapps\\demo01";
            String[] subString = requestHeader.toString().split(" ");
            if (subString.length>2){
                File file = new File(webRoot+subString[1]);
                FileInputStream fis=new FileInputStream(file);
                byte[]  b=new byte[(int)file.length()];
                fis.read(b);
                outputStream.write(b);
                outputStream.close();
                fis.close();

            }



        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
