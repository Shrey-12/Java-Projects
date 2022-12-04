import java.net.ServerSocket;
import java.net.Socket;
import java.io.*;

public class ServerSockets {
    public static void main(String[] args) {
        try{
            ServerSocket ss=new ServerSocket(6666);
            Socket s=ss.accept(); //establishes a connection
            DataInputStream dis=new DataInputStream(s.getInputStream());
            String str=(String) dis.readUTF();
            System.out.println("message= "+str);
            ss.close();
        }catch(Exception e){
            System.out.println(e);
        }


    }
}
