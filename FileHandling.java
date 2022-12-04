package FileHandling;

import java.io.*;
import java.util.ArrayList;


public class FileHandling {

    public static ArrayList<Word> load(File file) throws IOException {
        FileReader fileReader1=new FileReader(file);
        BufferedReader br=new BufferedReader(fileReader1);

        ArrayList<Word> out=new ArrayList<>();
        int lineNumber=0;


        while(br.ready()){
            lineNumber++;
            String temp=br.readLine();
            String[] sts=temp.split(" ");

            Word[] arr=new Word[sts.length];
            for(int i=0;i<sts.length;i++){
                arr[i]=new Word(sts[i],lineNumber);
            }

            for (int i = 0;i<sts.length;i++)
            {
                if(sts[i] != "" && sts[i] != " " && sts[i] != "\n")
                    out.add(arr[i]);
            }
        }
        return out;
    }

    public static void DisplayCommonWords (File file1, File file2) throws IOException{
        ArrayList<Word> list1=load(file1);
        ArrayList<Word> list2=load(file2);

        ArrayList<String> out=new ArrayList<>();

        for(int i=0;i<list1.size();i++){
            Word word1=list1.get(i);

            for(int z=0; z<list2.size();z++){
                if(word1.word.equalsIgnoreCase(list2.get(z).word)){
                    Word word2=list2.get(z);
                    System.out.println(word1.word+": length "+word1.word.length()+", appeared in "+word1.lineNumber+" th line of "+file1.getName()+" and " +word2.lineNumber+ " in "+ file2.getName());
                }
            }


        }




    }

    public static void main(String[] args) throws IOException {
        String name1=args[0];
        String name2=args[1];

        File file1=new File(name1);
        File file2=new File(name2);
        DisplayCommonWords(file1,file2);
    }
}


class Word{
    String word;
    int lineNumber;

    public Word(){

    }
    public Word(String word,int lineNumber){
        this.word=word;
        this.lineNumber=lineNumber;
    }
}