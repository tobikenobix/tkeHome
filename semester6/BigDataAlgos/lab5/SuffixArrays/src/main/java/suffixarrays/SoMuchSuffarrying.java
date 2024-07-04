package suffixarrays;

import java.io.IOException;
import java.util.Arrays;

public class SoMuchSuffarrying {
    private Suffix[] suffixes;
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

    public static void main(String[] args) throws IOException {
        String text = SuffArrying.readAFileIn("Mann_Buddenbrooks.txt");
        SoMuchSuffarrying s = new SoMuchSuffarrying(text);
        s.printArr(3);
        //System.out.println(text.substring(164131));
    }
}
