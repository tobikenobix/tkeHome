package MTFvsBIT;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;

public class BIT {
    private LinkedHashMap<Character, Integer> map;
    private String requests;
    private char[] reqChars;
    private double sumCosts;

    public BIT(String filename) throws IOException {
        map = new LinkedHashMap<>();
        this.requests = readFile(filename);
        this.reqChars = requests.toCharArray();
        this.sumCosts = 0;
    }
    private void add(char c){
        // if no element in the list, then put it to first and assign 0 or 1
        if(map.isEmpty()){
            map.putFirst(c, (int) Math.round(Math.random()));
            //map.putFirst(c,1);
            sumCosts +=1;
        }
        // if element is first then just flip the bit
        else if(map.firstEntry().getKey() == c){
            map.replace(c, 1 - map.get(c));
            sumCosts +=1;
        }
        else{
            for(char key : map.keySet()){
                sumCosts += 1;
                if(key == c) { //see if it already is in list
                   if(map.get(c) == 0){
                       map.remove(c);
                       map.putFirst(c, 1);
                       sumCosts +=1;
                   }
                   else{
                       map.replace(c, 0);
                       sumCosts +=1;
                   }
                   return;
                }

            }
            map.putFirst(c, (int) Math.round(Math.random()));
            //map.putFirst(c, 1);
            sumCosts +=1;
        }

    }

    private void handleRequests(){
        for(char c : reqChars){
            this.add(c);
            //System.out.println(map);
        }
    }
    public double getSumCosts(){
        this.handleRequests();
        return sumCosts / requests.length();
    }
    public LinkedHashMap<Character, Integer> getBitList(){
        return map;
    }

    public static void main(String[] args) throws IOException {
        BIT test = new BIT("Requests_Exp.txt");
        System.out.println(test.getSumCosts());
        System.out.println(test.getBitList());
    }




    private  String readFile(String fileName) throws IOException {
        return getString(fileName);
    }

    public static String getString(String fileName) throws IOException {
        BufferedReader br = Files.newBufferedReader(Paths.get(fileName), StandardCharsets.US_ASCII);
        List<String> lines = br.lines().toList();
        br.close();
        StringBuilder sb = new StringBuilder();
        for (String line : lines) {
            sb.append(line);
        }
        return sb.toString();
    }
}
