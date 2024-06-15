import java.util.Random;

public class HashFunctions {
    long[][] hTab;
    public HashFunctions() {
        this.hTab = fillHTab();
    }

    public long tabHash(long x) {
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
    private long[][] fillHTab(){
        long[][] hTab = new long[4][256];
        Random rand = new Random();
        for(int i=0; i<4; i++){
            for(int j=0; j<256; j++){
                hTab[i][j] = rand.nextLong();
            }
        }
        return hTab;
    }
}
