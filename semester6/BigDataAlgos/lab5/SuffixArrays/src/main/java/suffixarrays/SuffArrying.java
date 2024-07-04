package suffixarrays;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;


public class SuffArrying {

    public static String readAFileIn(String fileName) throws IOException {
        Path filePath = Path.of(fileName);
        String input = Files.readString(filePath, StandardCharsets.ISO_8859_1);
        input = input.replace("\n", "");
        return input +"$";
    }

    //ide was to create a substring array and then copy it as a look-up for index, this is obviously not working
    public static String[] buildStringArr(String input){
        String[] arr = new String[input.length()];
        for(int i=0; i<input.length(); i++){
            arr[i] = input.substring(i);
            System.out.println(i);
        }
        return arr;
    }
    //this works but takes too long
    public static List<Integer> dummyCompare(String input){
        List<Integer> list = new LinkedList<>();
        list.addFirst(1);

        for(int i=1; i<input.length(); i++){
            //input.substring(i);
            int insertPos = 0;
            boolean found = false;
            for(int x : list){
                if(input.substring(i).compareTo(input.substring(x-1))<0){
                    list.add(insertPos, i+1);
                    found = true;
                    break;
                }
                insertPos++;
            }
            if(!found) list.add(i+1);
        }
        return list;
    }

    static int binarySearch(List<Integer> list, int left, int right, int key, String input) {
        if (left > right) {
            return left; // Insertion point
        }
        int mid = left + (right - left) / 2;
        String midVal = input.substring(list.get(mid));
        String keyVal = input.substring(key);

        if (midVal.compareTo(keyVal) < 0) {
            return binarySearch(list, mid + 1, right, key, input); // Search right half
        } else if (midVal.compareTo(keyVal) > 0) {
            return binarySearch(list, left, mid - 1, key, input); // Search left half
        } else {
            return mid + 1; // For sorting, insert after all equal elements
        }
    }
    //new idea: do same as above but don't compare to everything, instead use binary search to find the position
    public static List<Integer> releaseMeFromSuffarrying(String input){
        List<Integer> list = new LinkedList<>();
        list.addFirst(0);
        for(int i=1; i<input.length(); i++){
            int insertPos = binarySearch(list, 0,list.size()-1, i, input);
            list.add(insertPos,i);
        }
        return list;
    }

    public static void main(String[] args) throws IOException {
        String input = readAFileIn("Mann_Buddenbrooks.txt");
        String dummyInput = "mississippi$";
        System.out.println(dummyCompare(dummyInput));
        System.out.println(releaseMeFromSuffarrying(input));



    }
}
