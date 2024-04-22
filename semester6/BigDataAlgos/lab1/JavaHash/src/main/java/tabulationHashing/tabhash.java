package tabulationHashing;

import java.util.Random;

public class tabhash {

    public static long[][] fillhTab(int column, int row){
        Random rand = new Random();
        long[][] htab = new long[column][row];
        for (int i = 0; i < column; i++) {
            for (int j = 0; j < row; j++) {
                htab[i][j] = rand.nextLong();
            }
        }
        return htab;
    }

    public static long simptabhash(long x, long[][] hTab){
        long hash = 0;
        long c;

        for (int i = 0; i < 4; i++){
            c = x & 0xff;
            hash ^= hTab[i][(int) c];
            x = x >> 8;
        }
        return hash & 0xffffffffL;
    }

    public static long mixedtabhash(long x, long[][] hTab32, long[][] hTab64){
        long hash = 0;
        for (int i = 0; i < 4; i++){
            hash ^= hTab64[i][(int)(x & 0xff)];
            x = x >>> 8;
        }
        long drv = hash >>> 32;
        for (int i = 0; i < 4; i++){
            hash ^= hTab32[i][(int)(drv & 0xff)];
            drv = drv >>> 8;
        }
        return hash;
    }

    public static long getAvgBitFlip(boolean mixed){
        //prerequisites
        long flippedBits = 0;
        long amountNumbers = 10000001;
        long[][] hTab1 = fillhTab(4,256);

        if(mixed){
            long[][] hTab2 = fillhTab(4,256);
            for(long l = 0; l < amountNumbers; l++){
                long ogNumb = mixedtabhash(l, hTab1, hTab2);
                for(int k = 0; k < 32; k++){
                    long shift = l^(1<<k);
                    long shiftNumb = mixedtabhash(shift, hTab1, hTab2);
                    flippedBits = Long.bitCount(shiftNumb ^ogNumb);
                }
            }

        } else{
            for(long i = 0; i < amountNumbers; i++){
                //create first hash
                long ogNumb = simptabhash(i, hTab1);
                //shift every bit and then do the hashing
                for(int j = 0; j < 32; j++){
                    long shift = i^(1<<j);
                    long shiftNumb = simptabhash(shift, hTab1);
                    //count flipped bits
                    flippedBits += Long.bitCount(shiftNumb ^ ogNumb);
                }
            }
        }
        return  flippedBits  ;
    }

    public static void main(String[] args) {
       // just playing around with it to see some results for early success
//        long[][] hTabSh = fillhTab(4,256);
//        long res = simptabhash(1001299999999933400L, hTabSh);
//        System.out.println(res);
//
//        long[][] hTabMth = fillhTab(4,256);
//        long resMt = mixedtabhash(500000034L, hTabMth, hTabSh);
//        System.out.println(resMt);

        //starting the bit shifting stuff with simple tabulation hashing
        System.out.println(getAvgBitFlip(true));


    }
}
