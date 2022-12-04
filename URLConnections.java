import java.io.*;
import java.net.URLConnection;
import java.net.URL;

public class URLConnections {
    public static void main(String[] args) {
        try {
            URL url = new URL("https://google.com");
            URLConnection urlcon = url.openConnection();
            InputStream stream = urlcon.getInputStream();
            int b;

            while ((b = stream.read()) != -1) {
                System.out.print((char) b);
            }
        }catch(Exception e){
            System.out.println(e);
        }
    }


}