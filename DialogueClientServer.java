import java.net.*;
import java.io.*;


public class DialogueClientServer {
    public static void main(String[] args) throws Exception {
        Socket s=new Socket("192.168.194.207",3334);
        DataInputStream din=new DataInputStream(s.getInputStream());
        DataOutputStream dout=new DataOutputStream(s.getOutputStream());
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        System.out.println( InetAddress.getLocalHost().getHostAddress());

        String str1="",str2="";
        while (!str1.equals("stop")){
            str1=br.readLine();
            dout.writeUTF(str1);
            dout.flush();
            str2=din.readUTF();
            System.out.println("Server says: "+str2);
        }
        dout.close();
        s.close();
    }
}
