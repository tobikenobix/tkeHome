package suffixarrays;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;

public class SuffArrying {

    private static String readAFileIn(String fileName) throws IOException {
        Path filePath = Path.of(fileName);
        String input = Files.readString(filePath, StandardCharsets.ISO_8859_1);
        input = input.replace("\n", "");
        return input +"$";
    }
    private static int[] suffixIt(String input) {
        int[] suffixArray = new int[input.length()];
        return suffixArray;
    }

    public static void main(String[] args) throws IOException {
        String input = readAFileIn("Mann_Buddenbrooks.txt");
        System.out.println(suffixIt(input.substring(9000,20000)));
    }
}
