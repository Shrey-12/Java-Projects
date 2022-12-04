
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class server {
    public static Files load(File file) throws IOException {
        FileReader fileReader1=new FileReader(file);
        BufferedReader br=new BufferedReader(fileReader1);

        int lineNumber=0;
        int wordCount=0;

        while(br.ready()){
            lineNumber++;
            String temp=br.readLine();
            String[] sts=temp.split(" ");
            wordCount=wordCount+ sts.length;
        }
        return new Files(file.getName(),file.getPath(),wordCount,lineNumber);
    }


    public static void main(String[] args) throws IOException, EOFException {
        ServerSocket ss = new ServerSocket(3334);
        Socket s = ss.accept();
        DataInputStream din = new DataInputStream(s.getInputStream());
        DataOutputStream dout = new DataOutputStream(s.getOutputStream());
        String fileName = din.readUTF();

        try {
            Files returnedFile = load(new File(fileName));
            dout.writeUTF(returnedFile.toString());
            dout.flush();
        } catch (FileNotFoundException fnfe) {
            dout.writeUTF(String.valueOf(-1));
            String[] directoryContents = (new File(".").list());
            ArrayList<Files> fileList = new ArrayList<>();

                for (String name : directoryContents) {
                    if ((new File(name)).isFile()) {
                        if (name.substring(0, 3).equals(fileName.substring(0, 3))) {
                            File myFile = new File(name);
                            Files temp = load(myFile);
                            fileList.add(temp);
                        }
                    }
                }
            dout.writeUTF("1." + fileList.get(0).fileName + " 2." + fileList.get(1).fileName + " 3." + fileList.get(2).fileName );
            int choice = Integer.parseInt(din.readUTF());
            dout.writeUTF(fileList.get(choice).toString());
            dout.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
            din.close();
            s.close();
            ss.close();
        }

}

class Files{
    String fileName;
    String path;
    int wordCount;
    int numberOfLines;

    public Files(String fileName,String path,int wordCount,int numberOfLines){
        this.fileName=fileName;
        this.path=path;
        this.wordCount=wordCount;
        this.numberOfLines=numberOfLines;
    }
    public String toString(){
        return "File Name: "+fileName+" "+"File path: "+path+" "+"Word count: "+Integer.toString(wordCount)+" "+"line Count: "+Integer.toString(numberOfLines)+" ";
    }
}

