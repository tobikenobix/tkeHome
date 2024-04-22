package MTFvsBIT;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class MtfVsBitMain {

    public static TreeMap<String, Double> computeProbability(String filename)  {
        TreeMap<String, Double> result = new TreeMap<>();
        try {
            BufferedReader br = Files.newBufferedReader(Paths.get(filename), StandardCharsets.US_ASCII);
            List<String> lines = br.lines().toList();
            br.close();
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

    public static double computeAverageCostAlphabetic(TreeMap<String, Double> probability) {
        double sum = 0.0;
        int n = 1;
        for (String key : probability.keySet()) {
            sum +=  n* probability.get(key);
            n++;
        }

        return sum;
    }
    public static double computeAverageCostProbability(List<Double> probability) {
        double sum = 0.0;
        int n = 1;
        for (double prob : probability) {
            sum +=  n* prob;
            n++;
        }
        return sum;
    }

    public static void main(String[] args) {
        TreeMap<String, Double> probability = computeProbability("Requests_Toy.txt");
        List<Double> inputValueOrder = new ArrayList<>(probability.values());
        inputValueOrder.sort(Collections.reverseOrder());
        System.out.println("Alphabetical order: " + computeAverageCostAlphabetic(probability));
        System.out.println("Probability order: " + computeAverageCostProbability(inputValueOrder));

    }
}
