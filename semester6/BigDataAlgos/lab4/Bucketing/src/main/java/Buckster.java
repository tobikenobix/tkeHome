import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Buckster {
    private static List<Long> fileWalker(String fileName) throws IOException {
        List<Long> data = new ArrayList<>();
        BufferedReader br = new BufferedReader(new FileReader(fileName));
        String line;
        while ((line = br.readLine()) != null) {
            data.add(Long.parseLong(line));
        }
        br.close();
        return data;
    }

    /**
     * implements the bucketing algorithm from lecture online algorithms page 111.
     * Naming is according to the lecture sliedes
      * @param S Stream of input data
     * @param b bucket size
     * @param k number of hash function
     * @return estimation E of cardinality F0 in S (distinct items)
     */
    public static long bucketing(List<Long> S, int b, int k) {
        HashFunctions[] hashFunction = new HashFunctions[k]; // we need k hash functions
        int[] m = new int[k]; //levels for each hash function
        List<Set<Integer>> buckets = new ArrayList<>(); //one bucket for each hash function, we use a Set, since one elem can only occur once in a bucket
        long[] median = new long[k]; //needed for getting the median later on

        //initialize
        for (int i = 0; i < k; i++) {
            hashFunction[i] = new HashFunctions(); //creates a new "random" table for the tab hash
            m[i] = 1; //start with 0 leading zero
            buckets.add(i, new HashSet<>()); //create empty buckets for each hash function
        }
        //handle the stream
        for(Long x : S){
            //hash every item with every hash function and "store" it in the corresponding bucket
            for(int i = 0; i < k; i++){
                int v = hashFunction[i].tabHash(x); //hash each value
                int z = Integer.numberOfLeadingZeros(v); //get all leading zeros
                if(z>=m[i]){ //check if the item has at least the amount of zeros as our level
                    buckets.get(i).add((v)); //checks passed, add it to the bucket of the hash function
                    if(buckets.get(i).size() > b){ //check if our bucket size is still in range for this hash function
                        m[i] += 1; // increase minimum of leading zeros by one
                        HashSet<Integer> newBucket = new HashSet<>(); //HashSet that gets all the hashes that pass the new level
                        for(int hash : buckets.get(i)){
                            if(Integer.numberOfLeadingZeros(hash)>m[i])
                                newBucket.add(hash);//remove all hashes from the bucket that are not passing our new level
                        }
                        buckets.set(i, newBucket); //replace the bucket with the updated version
                    }
                }
            }
        }
        for (int i = 0; i < k; i++) {
            median[i] = buckets.get(i).size() * (1L << m[i]);
        }
        Arrays.sort(median);
        return median[median.length/2];
    }


    public static void main(String[] args) throws IOException {
        //System.out.println(bucketing(fileWalker("set_2M.txt"),9600,151));
        //System.out.println(fileWalker("set_2M.txt").stream().distinct().toArray().length);
        List<Long> test  = new ArrayList<>();
        for(long i = 0; i<5000000; i++){
            test.add(i);
            test.add(i*(-1));
        }
        //ystem.out.println(bucketing(fileWalker("short_set.txt"),9600,151));
        System.out.println(bucketing(test,9600,151));
        //System.out.println(calcError(fileWalker("set_2M.txt"), 400, 51,10, 2000000));
    }

    private static double calcError(List<Long> S, int b, int k, int m, long F) {
        double estimates = 0;
        for(int i =0; i<m; i++){
            estimates +=  100* ((double) (bucketing(S, b, k) - F) /F);
        }
        return ((double) 1 /m) * estimates;
    }
}
