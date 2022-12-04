import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.Socket;

public class Client {
    public static void main(String[] args) {
        try{
            Socket s=new Socket("localhost",3334);
            DataInputStream din = new DataInputStream(s.getInputStream());
            DataOutputStream dout = new DataOutputStream(s.getOutputStream());
            BufferedReader br=new BufferedReader(new InputStreamReader(System.in));

            System.out.println("Enter file Name");
            String fileName=br.readLine();
            dout.writeUTF(fileName);
            String inputFile=din.readUTF();

            if(inputFile.equals(String.valueOf(-1))){
                System.out.println("File not found!");
                System.out.println("Similar files are: "+din.readUTF());
                System.out.println("Enter file number to choose particular file or -1 to quit");
                int choice=Integer.parseInt(br.readLine());
                br.close();
                if(choice==-1)
                    System.exit(0);
                else
                    dout.writeUTF(Integer.toString(choice));
                System.out.println(din.readUTF());
            }
            System.out.println(inputFile);
            din.close();
            dout.close();
            s.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
