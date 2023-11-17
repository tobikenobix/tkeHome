import java.util.Comparator;
import java.util.function.*;

public class Koans {
    public static void mapArray(int[] array, Function<Integer,Integer> f){
        for (int i = 0; i < array.length; i++)
            array[i] = f.apply(array[i]);
    }

    public static double[] fillArray(int length, Supplier<Double> s){
        double[] result = new double[length];
        for(int i = 0; i < length; i++) {
            result[i] = s.get();
        }
        return result;
    }

    public static int[] iterateFunction(int length, int first, Function<Integer, Integer> f){
        int[] result = new int[length];
        for(int i =1; i < length; i++){
            result[i] = f.apply(result[i-1]);
        }
        return result;
    }

    public static <T> T min(T[] elements, Comparator<T> c){
        T min = elements[0];
        for(int i = 1; i<elements.length; i++){
            if(c.compare(min, elements[i])>0)
                min = elements[i];
        }
        return min;
    }

    public static Function<Double, Double> createMultiplier(double d){
        return (x -> d*x);
    }

    public static void forEachArray(String[] strings, Consumer<String> c){
        for(String s : strings){
            c.accept(s);
        }
    }

    public static <T> Predicate<T> duplicateChecker(){

    }
}
