import java.util.Random;

public class HashFunctions {
    long[][] hTab;
    static Random rand = new Random();
    public HashFunctions() {
        this.hTab = fillHTab();
    }


    public int tabHash(long x) {
        int hash = 0;
        long c;
        for (int i=0; i<4; i++) {

            c = x & 0xff;
            hash ^= hTab[i][(int) c];
            x = x >> 8;
        }

        return hash;
    }
    private long[][] fillHTab(){
        long[][] hTab = new long[4][256];
        for(int i=0; i<4; i++){
            for(int j=0; j<256; j++){
                hTab[i][j] = rand.nextLong();
            }
        }
        return hTab;
    }
}
