package mina;

import java.util.Date;
import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;

public class TimeServerHandler extends IoHandlerAdapter{
    //1. 设置一个变量
    public static int count=0;

    //2. 当捕获到异常时会触发（这里不做任何处理，仅仅打印一下）
    public void execptionCaught(IoSession session,Throwable cause) throws Exception {
        cause.printStackTrace();
    }
    //3. 当接收到数据时触发
    public void messageReceived(IoSession session, Object message) {
        String mes=message.toString();
        //当接受到的数据是quit时或者用户请求大于2次关闭连接
        if(mes.trim().equalsIgnoreCase("quit")||count>2) {
            System.out.println("正在退出时间服务器");
            session.write("正在退出时间服务器。。。。");
            session.close();
            return;
        }
        //向客户端回复一个当前时间
        Date date = new Date();
        session.write(date.toString());
        System.out.println("message writen："+mes);
        count++;
    }

}