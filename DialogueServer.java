import java.net.*;
import java.io.*;


public class DialogueServer {
    public static void main(String[] args) throws Exception{
        ServerSocket ss=new ServerSocket(3334,2,InetAddress.getByName("192.168.194.207"));
        Socket s=ss.accept();
        DataInputStream din=new DataInputStream(s.getInputStream());
        DataOutputStream dout=new DataOutputStream(s.getOutputStream());
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        String str1="",str2="";
        while(!str1.equals("stop")){
            str1=din.readUTF();
            System.out.println("Client says: "+str1);
            str2=br.readLine();
            dout.writeUTF(str2);
            dout.flush();
        }
        din.close();
        s.close();
        ss.close();
    }
}
