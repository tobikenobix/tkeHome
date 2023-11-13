import java.util.function.Function;
import java.util.function.IntUnaryOperator;

public class Koans {
    public static void mapArray(int[] array, Function<Integer,Integer> f){
        for (int i = 0; i < array.length; i++)
            array[i] = f.apply(array[i]);
    }
}
