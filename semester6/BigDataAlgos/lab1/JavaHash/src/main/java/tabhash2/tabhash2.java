package tabhash2;

import java.util.Arrays;
import java.util.Random;

public class tabhash2 {
    private final static long iterations = 10000000L *32;
    private static final long[] flipCountsSimple = new long[32];

    public static long tabula32(long x, long[][] hTab)
        //function copied from lecture slides
    {
        long hash = 0;
        long c;
        for (int i=0; i<4; i++)
        {
            c = x & 0xff;
            hash ^= hTab[i][(int) c];
            x = x >> 8;
        }
        return hash & 0xffffffffL;
    }

    public static long tabulaMix32(int x, long[][] hTab_32, long[][] hTab_64) {
        long hash = 0;
        for (int i=0; i<4; i++) {
            hash ^= hTab_64[i][x & 0xFF];
            x = x >> 8;
        }
        long drv =  (hash >>> 32);
        for (int i=0; i<4; i++) {
            hash ^= hTab_32[i][(int)drv & 0xFF];
            drv = drv >> 8;
        }
        return hash & 0xffffffffL;
    }


    //note, if there is time left, try to refactor those two functions, so there is only a getAvgBitFlipped methode necessary which takes a function as input
    public static int[] getAvgBitFlippedSimpleTab(){
        long[][] hTab = fillHTab();
        int[] help = new int[33];
        long bitsFlipped = 0;
        // range 0 to 10 000 000
        for(int i = 0; i<=  10000000; i++ ){
            long hashBeforeFlip = tabula32(i,hTab);
            long hashAfterFlip;

            //flip all 32 bits and compare them
            for(int k = 0; k<=31; k++){
                int xx = i ^(1<<k);
                hashAfterFlip = tabula32(xx,hTab);
                long flippedBits = Long.bitCount(hashBeforeFlip ^hashAfterFlip);
                help[(int)flippedBits]++;
                //flipCountsSimple[k] += flippedBits;
                bitsFlipped += flippedBits;
            }

        }
       System.out.println("Simple Bits flipped " +(double) bitsFlipped / iterations);
        return help;

    }
    public static int[] getAvgBitFlippedMixedTab(){
        long [][] hTab_32 = fillHTab();
        long[][] hTab_64 = fillHTab();
        double[] avarage = new double[10000000 +1];
        int[] help = new int[33];

        long bitsFlipped = 0;
        for(int i = 0; i<=  10000000; i++ ){
            long hashBeforeFlip = tabulaMix32(i,hTab_32,hTab_64);
            long hashAfterFlip;

            for(int k = 0; k<=31; k++){
                int xx = i ^(1<<k);
                hashAfterFlip = tabulaMix32(xx,hTab_32,hTab_64);
                bitsFlipped += Integer.bitCount((int)(hashBeforeFlip ^ hashAfterFlip));
                help[Integer.bitCount((int)(hashBeforeFlip^hashAfterFlip))]++;

            }
            avarage[i] = (double)bitsFlipped / 32.0;
            bitsFlipped = 0;
        }
       // return  (double) bitsFlipped / iterations;
        double x = Arrays.stream(avarage).average().getAsDouble();
        System.out.println("Mixed Flipped bits " + x);
        return help;
    }

    public static double chiSquare(int[] help){
        double test = 0;
        double sum = 0;
        int number = 32 * 10_000_001;
        for (int i=0; i<32; i++){
            test = binominalCo(32,i)*Math.pow(0.5,32);
            test *= number;
            sum += (Math.pow(help[i]-test,2 )/test);
        }
        return sum;
    }






    public static void main(String[] args) {

      //  System.out.println("Simple Hash Bits flipped " + getAvgBitFlippedSimpleTab());
      //  System.out.println("Mixed Hash Bits flipped " +getAvgBitFlippedMixedTab());
       // getAvgBitFlippedSimpleTab();
        System.out.println(chiSquare(getAvgBitFlippedSimpleTab()));
        System.out.println(chiSquare(getAvgBitFlippedMixedTab()));



    }

    //helper methods block
    private static long[][] fillHTab(){
        long[][] hTab = new long[4][256];
        Random rand = new Random();
        for(int i=0; i<4; i++){
            for(int j=0; j<256; j++){
                hTab[i][j] = rand.nextLong();
            }
        }
        return hTab;
    }

    private static long binominalCo(int n, int k){
        long res = 1;
        if(k > n-k){
            k = n-k;
        }

        for(int i=1, m = n; i<=k; i++, m--){
            res = res*m/i;
        }
        return res;
    }




}
