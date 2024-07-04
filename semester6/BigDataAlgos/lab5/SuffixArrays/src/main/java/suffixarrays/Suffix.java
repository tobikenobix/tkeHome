package suffixarrays;

public class Suffix implements Comparable<Suffix> {
    private final String text;
    private final int index;

    public Suffix(String text, int index) {
        this.text = text;
        this.index = index;
    }
    public int length() {
        return text.length()-index;
    }
    public char charAt(int i) {
        return text.charAt(index + i);
    }
    public int compareTo(Suffix other) {
        if(this == other) return 0;
        int n = Math.min(this.length(), other.length());
        for(int i = 0; i < n; i++) {
            if(this.charAt(i) < other.charAt(i)) return -1;
            if(this.charAt(i) > other.charAt(i)) return 1;
        }
        return this.length() - other.length();
    }
    public int getIndex(){
        return this.index;
    }
    @Override
    public String toString() {
        return text.substring(index);
    }
}
