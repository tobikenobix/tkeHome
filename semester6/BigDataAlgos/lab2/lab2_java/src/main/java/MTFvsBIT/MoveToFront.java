package MTFvsBIT;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;

public class MoveToFront {
    private LinkedList<Character> mtf = new LinkedList<>();
    private String requests;
    private char[] reqChars;
    private double sumCosts;

    public MoveToFront(String filename) throws IOException {
        this.requests = readFile(filename);
        this.reqChars = requests.toCharArray();
        this.sumCosts = 1;
    }

    private void addChar(char c){
        if(mtf.isEmpty()){
            mtf.addFirst(c);
        }
        else if(mtf.getFirst() == c){
            sumCosts += 1;
        }
        else{
            for(int i = 0; i < mtf.size(); i++){
                sumCosts += 1;
                if(mtf.get(i) == c){ //element in List but not first element
                    mtf.remove(i);
                    mtf.addFirst(c);
                    sumCosts += 1;
                    return;
                }
            }
            mtf.addFirst(c);// element not in the List, add first
            sumCosts += 1;
        }

    }
    private void handleRequests(){
        for(char c : reqChars){
            addChar(c);
        }
    }

    private void genList(){
        this.handleRequests();
    }

    public double getSumCosts(){
        genList();
        return sumCosts/requests.length();
    }

    public LinkedList<Character> getList(){
        return mtf;
    }


    public static void main(String[] args) throws IOException {
       // String requests = readFile("Requests_Toy.txt");
        MoveToFront moveToFront = new MoveToFront("Requests_TM.txt");
        System.out.println(moveToFront.getSumCosts());
        System.out.println(moveToFront.getList());
    }



    private  String readFile(String fileName) throws IOException {
        return BIT.getString(fileName);
    }
}
