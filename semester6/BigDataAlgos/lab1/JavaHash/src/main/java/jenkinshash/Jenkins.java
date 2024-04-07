package jenkinshash;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;


public class Jenkins {

    public static int jenkinsHash(byte[] key, int length){
        int i = 0;
        int hash = 0;
        while (i != length){
            hash += key[i++] & 0xFF;
            hash += hash << 10;
            hash ^= hash >>> 6;
        }
        hash += hash << 3;
        hash ^= hash >>> 11;
        hash += hash << 15;
        return  hash;
    }


    public static void main(String[] args) throws IOException {
        //System.out.printf("%x", jenkinsHash("BigData".getBytes(),7));
        Path mannbudde = Paths.get("Mann_Buddenbrooks.txt");
        String text = Files.readString(mannbudde, StandardCharsets.ISO_8859_1);
        String input = text.replace(System.lineSeparator(),"");
        //something is wrong with the size of the file here - even tho no line breaks seem to exist anymore
        System.out.println(input.length());
        long start = System.currentTimeMillis();
        int jhmb= jenkinsHash(input.getBytes(), input.length());
        long end = System.currentTimeMillis();
        System.out.println("Jenkins Hash Took " + (end - start) + "ms");

    }
}
