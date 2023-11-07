package genericShelf;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
public class ShelfTest {
     Shelf<Book> bookShelf;
    @BeforeEach
    public  void initialize(){
        bookShelf = new Shelf<>();
        bookShelf.setUpperRight(new Book("How to", "Randell",300));
        bookShelf.setUpperLeft(new Book("What if", "Randell", 270));
        bookShelf.setLowerLeft(new Book("Star Wars Stories", "Many", 500));
        bookShelf.setLowerRight(new Book("Another Star Wars book","Me", 100));
    }
    @Test
    public void testConstructor(){
        Shelf<ShelfItem> shelf = new Shelf<>();
        assertNull(shelf.getLowerLeft());
        assertNull(shelf.getLowerRight());
        assertNull(shelf.getUpperLeft());
        assertNull(shelf.getUpperRight());
    }

    @Test
    public void testSetter(){
        Book b = new Book("Hello", "World",3);
        bookShelf.setLowerLeft(b);
        assertEquals(bookShelf.getLowerLeft(), b);
    }
    @Test
    public void testSetNGetValid(){
        Book b = new Book("Hello", "World", 7);
        for(int i =0; i < 4; i++){
            bookShelf.set(i, b);
            assertEquals(b, bookShelf.get(i));
        }
    }
    @Test
    public void testSetNGetInvalid(){
        Book b = new Book("Hello", "World", 7);
        assertThrows(IndexOutOfBoundsException.class, ()->bookShelf.set(7,b));
        assertThrows(IndexOutOfBoundsException.class, ()->bookShelf.set(-1,b));
        assertThrows(IndexOutOfBoundsException.class,()->bookShelf.get(5));
        assertThrows(IndexOutOfBoundsException.class, ()->bookShelf.get(-3));
    }
    @Test
    public void testIterator(){
        Book b = new Book("Hello", "World",7);
        bookShelf.set(0, b);
        assertEquals(b, bookShelf.iterator().next());
    }
    @Test
    public void testTakeFrom(){
        Shelf<Book> bs = new Shelf<>();
        bs.takeFrom(bookShelf);
        assertEquals(bs.get(0), bookShelf.get(0));
    }

    @Test
    public void testMax(){

        Book max = bookShelf.max((b1, b2) -> Integer.compare(b1.getPages(), b2.getPages()));
        assertSame(max, bookShelf.get(3));
    }


}
