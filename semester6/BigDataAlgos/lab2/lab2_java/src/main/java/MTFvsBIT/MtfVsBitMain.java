package MTFvsBIT;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class MtfVsBitMain {

    public static TreeMap<String, Double> computeProbabilityStatic(String filename)  {
        TreeMap<String, Double> result = new TreeMap<>();
        try {
            List<String> lines = readFile(filename);
            int linesCount = lines.size();
            for (String line : lines) {
                if(!result.containsKey(line)) {
                    result.put(line, 1.0);
                }
                else{
                    result.put(line, result.get(line) + 1);
                }
            }
            result.replaceAll((k, v) -> result.get(k) / linesCount);

        }
        catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return result;
    }

    public static double computeAverageCostStatic(List<Double> probs){
        double sum = 0.0;
        int n = 1;
        for (double prob : probs) {
            sum +=  n*prob;
            n++;
        }
        return sum;
    }

    public static double computeC (List<Double> probs){
        double sum = 0.0;
        for(int i = 0; i < probs.size()-1; i++){
            for(int j = i+1; j < probs.size(); j++){
                sum += (probs.get(i)*probs.get(j))/(probs.get(i)+probs.get(j));
            }
        }
        return 2*sum+1;
    }

//    public static Set<String> MTFSet(String filename) {
//        ArrayList<String> input = null;
//        LinkedHashSet<String> mtf = new LinkedHashSet<>();
//        try{
//            input = new ArrayList<>(readFile(filename));
//        }
//        catch (IOException e) {
//            System.out.println(e.getMessage());
//        }
//        mtf.addFirst(input.get(0));
//        for(String str : input) {
//            if(!Objects.equals(mtf.getFirst(), str)) {
//                mtf.addFirst(str);
//
//            }
//        }
//        //System.out.println((double)count/(double)mtf.size());
//        return mtf;
//    }


//    public static Set<String> BITSet(String filename) {
//        ArrayList<String> input = null;
//        LinkedHashMap<String, Integer> temp = new LinkedHashMap<>();
//
//        try{
//            input = new ArrayList<>(readFile(filename));
//        }
//        catch (IOException e) {
//            System.out.println(e.getMessage());
//        }
//        // we only want distinct items
//        HashSet<String> items = new HashSet<>(input);
//        //assign 0 or 1 pseudo-random to each request
//        for (String item : items){
//            temp.put(item, (int) Math.round(Math.random()));
//        }
//        //process the requests
//        for(String req : input){
//            if(temp.get(req) == 0){
//                //move to front
//                temp.remove(req);
//                temp.putFirst(req, 0);
//            }
//            temp.replace(req, 0);
//        }
//
//        return new LinkedHashSet<>(temp.keySet());
//    }

//    public static double computeCostsMTF(TreeMap<String, Double> probVals, Set<String> mtf) {
//        double sum = 0.0;
//        int n = 1;
//        for(String str : mtf) {
//            sum += n*probVals.get(str);
//            n++;
//        }
//        return sum;
//    }

    public static double calculateDelta(double bit, double mtf){
        return 100* ((bit - mtf)/mtf);
    }


    public static void main(String[] args) throws IOException {
        printCosts("Requests_Toy.txt");
        printCosts("Requests_Exp.txt");
        printCosts("Requests_TM.txt");
    }

    public static void printCosts(String filename) throws IOException {
        MoveToFront mtf = new MoveToFront(filename);
        BIT bitTest = new BIT(filename);
        double bitCosts = bitTest.getSumCosts();
        double mtfCosts = mtf.getSumCosts();
        TreeMap<String, Double> probaVals = computeProbabilityStatic(filename);
        List<Double>probsOnly=new ArrayList<>(probaVals.values());
        List<Double>probsSorted=new ArrayList<>(probaVals.values());
        probsSorted.sort(Collections.reverseOrder());
        List<Double>inputValueAlphabetical = new ArrayList<>(probaVals.values());
        System.out.println("Showing Data for "+ filename);
        System.out.println("Costs average static alphabetical: " + computeAverageCostStatic(probsOnly));
        System.out.println("Costs avarage static probabilities: " + computeAverageCostStatic(probsSorted));
        System.out.println("Excpected average cost: "+computeC(inputValueAlphabetical));
        System.out.println("Real costs MTF: "+mtfCosts);
        System.out.println("Real costs BIT: "+ bitCosts);
        System.out.println("Delta between BIT and MTF in % "+ calculateDelta(bitCosts, mtfCosts));
        System.out.println("#####################################");
    }
    private static  List<String> readFile(String fileName) throws IOException {
        BufferedReader br = Files.newBufferedReader(Paths.get(fileName), StandardCharsets.US_ASCII);
        List<String> lines = br.lines().toList();
        br.close();
        return lines;
    }
}
