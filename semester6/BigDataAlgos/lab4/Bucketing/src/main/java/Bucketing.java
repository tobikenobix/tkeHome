import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class Bucketing {

    /**
     * Implements the Bucketing algorithm from lecture Online Algos page 111
     * @param filepath Input Stream, in this case a file with numbers - Data Stream S
     * @param b bucket size
     * @param k number of hash functions
     * @return Estimate E of cardinality F0 of S
     */
    public static int bucketIt(String filepath, int b, int k){
        try{
            //#### file handling block
            BufferedReader br = new BufferedReader(new FileReader(filepath));
            Scanner sc = new Scanner(br);
            // actual start of the algorithm
            // create 1 to k hash functions, buckets and levels
            int[] median = new int[k];
            HashFunctions[] hashFunctions = new HashFunctions[k];
            Set<Long>[] buckets = new HashSet[k];
            int[] m = new int[k];
            for(int i =0; i<k; i++){
                hashFunctions[i] = new HashFunctions();
                buckets[i] = new HashSet<>();
                m[i] = 1; //level
            }
            while(sc.hasNextLine()){
                long x = Long.parseLong(sc.nextLine().trim());
                for(int i =0; i<k; i++){
                    long v = hashFunctions[i].tabHash(x);
                    int z = Long.numberOfLeadingZeros(v);
                    if((z >= m[i])&& !buckets[i].contains(v)){
                        buckets[i].add(v);
                        if(buckets[i].size() > b){
                            m[i] += 1;
                            //filter out the elements that dont have at least m leading zeros
                            int finalI = i;
                            buckets[i] = buckets[i].stream().filter(y-> Long.numberOfLeadingZeros(y) > m[finalI]).parallel()
                            .collect(Collectors.toCollection(HashSet::new));
                        }
                    }
                    median[i] = (int) (buckets[i].size()* Math.pow(2,m[i]));
                }
            }
            br.close();
            sc.close();
            Arrays.sort(median);
            return median[median.length/2];
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 5;
    }
    public static void main(String[] args) {
        bucketIt("set_2M.txt",400,51);
    }

}
