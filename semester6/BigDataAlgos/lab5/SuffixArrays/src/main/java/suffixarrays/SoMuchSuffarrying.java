package suffixarrays;

import java.io.IOException;
import java.util.Arrays;
//inspired by https://algs4.cs.princeton.edu/code/edu/princeton/cs/algs4/SuffixArray.java.html
public class SoMuchSuffarrying {
    private final Suffix[] suffixes;
    public SoMuchSuffarrying(String text){
        int n = text.length();
        this.suffixes = new Suffix[n];
        for (int i = 0; i < n; i++) {
            suffixes[i] = new Suffix(text,i);
        }
        Arrays.sort(suffixes);
    }

    public void printArr(int n){
        for(int i = 0; i < n; i++){
            int a = suffixes[i].getIndex();
            System.out.println(a);
        }
    }

    public int length(){
        return suffixes.length;
    }
    public int[] genlcp(SoMuchSuffarrying s, String text){
        int[] lcp = new int[s.length()];
        lcp[0] = 0;
        for(int i = 1; i < s.length(); i++){
            int counter = 0;
            for(int j =0; j < i; j++){
                int startPoint1 = suffixes[i].charAt(j);
                int startPoint2 = suffixes[i-1].charAt(j);
                if(startPoint1 == startPoint2){
                    counter++;
                }
                else{
                    break;
                }
            }
            lcp[i] = counter;
        }
        return lcp;
    }

    public static void main(String[] args) throws IOException {
        String text = SuffArrying.readAFileIn("Mann_Buddenbrooks.txt");
        SoMuchSuffarrying s = new SoMuchSuffarrying(text);
        s.printArr(3);
        //System.out.println(text.substring(164131));
        int[] lcp = s.genlcp(s, text);
        double avgLcp = Arrays.stream(lcp).average().getAsDouble();
        System.out.println("Average lcp: "+avgLcp);
        int index = 0;
        int maxLcp = 0;
        for(int i  = 0; i<lcp.length; i++){
            if(lcp[i] > maxLcp){
                maxLcp = lcp[i];
                index = i;
            }
        }
        System.out.println("Max lcp: "+maxLcp);
        System.out.println("Max lcp index: "+ index);
        System.out.println("Max lcp string: "+ text.substring(index, index+maxLcp));
    }
}
