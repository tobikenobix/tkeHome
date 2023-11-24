package Koans;

import java.util.Collection;

public class Koans {
    public static<T> long distinctEntries(Collection<T> entries){
        return entries.stream().distinct().count();
    }
}
