package com.sinictek.spm.model.utils;


import java.io.*;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.URLEncoder;

/**
 * @Author sinictek-pd
 * @Date 2020/11/11 17:09
 * @Version 1.0
 */
public class SoctekUtil {

    /**
            * 采用socket连接向服务器发送消息，接受响应信息 注意，如果是通过不同的ip port确定调用接口 这个方法要重写，将ip
     * port作为请求条件,现改为传固有参数进来
     *
             * @param text
     * @throws Exception
     */

    public static String sendBySocket (String text,String ip,int port)throws Exception{

        Socket socket = new Socket(ip,port);
        String resultString="";
        try
        {
            DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
            dos.write(text.getBytes());
            dos.flush();
            //InputStreamReader isr =;
            BufferedReader bReader  =new BufferedReader( new InputStreamReader(socket.getInputStream()));
            String responseLine="";
            while ((responseLine = bReader.readLine())!=null ) {
                resultString +=responseLine +"\n";
            }

            //System.out.println( resultString);
        }
        catch (Exception e)
        {
        }
        finally {
            if (socket != null) {
                try {
                    socket.close();
                } catch (IOException e) {
                    socket = null;
                }
            }
        }
        return  resultString;
    }

    public static String sendByCShapeSocket(String text, String ip, int port)
            throws Exception {
        //创建socket
        InetSocketAddress addr = new InetSocketAddress(ip, port);
        Socket sock = new Socket();
        sock.setSoTimeout(6000);   //6s超时
        //连接服务器
        sock.connect(addr);
        //获得输入输出流
        BufferedReader is = new BufferedReader(new InputStreamReader(sock.getInputStream()));
        PrintWriter os = new PrintWriter(sock.getOutputStream());
        //发送数据
        os.write(text);//"{\"type\": 0, \"taskid\":\"001\"}");
        os.flush();
        //关闭输出流
        sock.shutdownOutput();
        //获取接收到的数据
        String lineResult ="";
        String tmpRdLine="";
        //String sss= is.readLine();
        while( (tmpRdLine= is.readLine())!=null){
            lineResult+=tmpRdLine;
        }
        //关闭流
        os.close();
        is.close();
        sock.close();
        return  lineResult;
    }

}
