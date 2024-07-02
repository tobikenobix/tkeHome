import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class Bucketing {

    /**
     * Implements the Bucketing algorithm from lecture Online Algos page 111
     * @param data Input Stream, in this case a file with numbers - Data Stream S
     * @param b bucket size
     * @param k number of hash functions
     * @return Estimate E of cardinality F0 of S
     */
    public static long bucketIt(List<Long> data, int b, int k){

            // create 1 to k hash functions, buckets and levels
            long[] median = new long[k];
            HashFunctions[] hashFunctions = new HashFunctions[k];
            Set<Long>[] buckets = new HashSet[k]; //HashSet does not allow duplicates
            int[] m = new int[k];
            for(int i =0; i<k; i++){
                hashFunctions[i] = new HashFunctions();
                buckets[i] = new HashSet<>();
                m[i] = 1; //level
            }
            for(long x : data) {
                for (int i = 0; i < k; i++) {
                    long v = hashFunctions[i].tabHash(x);
                    int z = Long.numberOfLeadingZeros(v);
                    if(z >= m[i]){
                        //System.out.println(z);
                        buckets[i].add(v);
                        if(buckets[i].size() > b){
                            m[i] += 1;
                            Set<Long> set = new HashSet<>();
                            for(long j : buckets[i]){
                                if(Long.numberOfLeadingZeros(j)>m[i])
                                    set.add(j);
                            }
                            buckets[i] = set;
                        }
                    }
                }
            }
            for(int i =0; i<k; i++){
                median[i] = (long) (buckets[i].size() * Math.pow(2, m[i]));
            }
            Arrays.sort(median);
            return  median[median.length/2];
    }


    public static void main(String[] args) throws IOException {

        List<Long> data = readData("set_2M.txt");
        long start = System.currentTimeMillis();
        for(int i =0; i<10; i++){
            System.out.println(bucketIt(data, 400, 51));
        }
        long end = System.currentTimeMillis();
        System.out.println("It took: " + (end - start)+" ms");
    }

    private static List<Long> readData(String filename) throws IOException {
        List<Long> data = new ArrayList<>();
        BufferedReader br = new BufferedReader(new FileReader(filename));
        String line;
        while ((line = br.readLine()) != null) {
            data.add(Long.parseLong(line));
        }
        br.close();
        return data;
    }



}
