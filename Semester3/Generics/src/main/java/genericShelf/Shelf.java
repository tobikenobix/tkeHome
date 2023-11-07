package genericShelf;

import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class Shelf <T extends ShelfItem> implements Iterable<T>{
    private T upperLeft;
    private T upperRight;
    private T lowerLeft;
    private T lowerRight;



    public T getUpperLeft() {
        return upperLeft;
    }

    public T getUpperRight() {
        return upperRight;
    }

    public T getLowerLeft() {
        return lowerLeft;
    }

    public T getLowerRight() {
        return lowerRight;
    }

    public void setUpperLeft(T upperLeft) {
        this.upperLeft = upperLeft;
    }

    public void setUpperRight(T upperRight) {
        this.upperRight = upperRight;
    }

    public void setLowerLeft(T lowerLeft) {
        this.lowerLeft = lowerLeft;
    }

    public void setLowerRight(T lowerRight) {
        this.lowerRight = lowerRight;
    }

    /**
     * gets the Item stored at index
     * @param index value between 0 and 3. Where 0 represents upperLeft, 1 = UpperRight, 2 =LowerLeft , 3=LowerRight
     * @return Item sotred at index
     */
    public T get(int index){
        return switch (index) {
            case 0 -> getUpperLeft();
            case 1 -> getUpperRight();
            case 2 -> getLowerLeft();
            case 3 -> getLowerRight();
            default -> throw new IndexOutOfBoundsException("Index has to be between 0 and 3");
        };
    }

    /**
     * sets Itam at Index
     * @param index value between 0 and 3. Where 0 represents upperLeft, 1 = UpperRight, 2 =LowerLeft , 3=LowerRight
     * @param item Generic type item that can be set at given index
     */
    public void set(int index, T item){
        switch (index) {
            case 0 -> setUpperLeft(item);
            case 1 -> setUpperRight(item);
            case 2 -> setLowerLeft(item);
            case 3 -> setLowerRight(item);
            default -> throw new IndexOutOfBoundsException("Index has to be between 0 and 3");
        }
    }

    /**
     * takes the items from one Shelf and transfers them to the one which called the methode
     * @param other Shelf which items get copied
     */
    public void takeFrom(Shelf<? extends T> other){
        if(other == null)throw new IllegalArgumentException("Other can not be null");
        for(int i = 0; i<4; i++){
            this.set(i, other.get(i));
        }
    }

    /**
     * Gets the biggest Item
     * @param comparator defines what the compared attribute is
     * @return biggest Item in the shelf
     */
    public T max(Comparator<T> comparator){
        if (comparator == null) throw new IllegalArgumentException("Comperator can not be null");
        T maxItem = null;
        for(int i = 0; i<4; i++){
            if(get(i) != null && (maxItem == null || comparator.compare(get(i),maxItem)<0))
                maxItem = get(i);
        }
        return maxItem;
    }

    /**
     * Transfers all Items from one shelf to another. Gets ridd of null Items between Items
     * @param from Shelf to transfer from
     * @param to Shelf to transfer to
     */
    public static <S extends ShelfItem> void transferAndTrim(Shelf<? extends S> from, Shelf<? super S> to){
        if(from == null || to == null )throw new IllegalArgumentException("Shelf can not be null!");
        //empty to shelf
        for(int i = 0; i < 4; i++){
            to.set(i, null);
        }
        //index to transfer to
        int toIndex = 0;
        for(int i = 0; i<4; i++){
            if(from.get(i) != null) {
                to.set(toIndex, from.get(i));
                toIndex++;
            }
        }

    }

    //generate iterator
    @Override
    public Iterator<T> iterator(){
        return new ShelfIterator();
    }


    //inner class for iterator
    private class ShelfIterator implements Iterator<T>{
        private int nextIndex;
        private ShelfIterator(){
            this.nextIndex=0;
        }
        @Override
        public boolean hasNext(){
            return nextIndex < 4;
        }
        @Override
        public T next(){
            if(!hasNext()) throw new NoSuchElementException("End reached");
            return Shelf.this.get(nextIndex++);
        }
    }

}
