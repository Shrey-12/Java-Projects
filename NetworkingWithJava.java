import java.net.URL;

public class NetworkingWithJava {
    public static void main(String[] args) {
        try{
            URL url=new URL("https://drive.google.com/drive/u/1/folders/1lqjmehqyzK5q0cNMaJlasE7JzGeutDYz");
            System.out.println("Protocol: "+url.getProtocol());
            System.out.println("Host Name: "+url.getHost());
            System.out.println("Port Number: "+url.getPort());
            System.out.println("File Name: "+url.getFile());
        }catch (Exception e){
            System.out.println(e);
        }
    }


}
