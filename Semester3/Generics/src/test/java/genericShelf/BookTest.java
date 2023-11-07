package genericShelf;

//import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

public class BookTest {

    @Test
    public void testConstructorValid() {
        Book b = new Book("Hallo", "Welt", 2);
        assertEquals("Hallo", b.getTitle());
        assertEquals("Welt", b.getAuthor());
        assertEquals(2, b.getPages());
    }

    @Test
    public void testConstructorInvalid() {
        assertThrows(IllegalArgumentException.class, () -> {
            Book b = new Book(" ", "Hallo", 2);
        });
        assertThrows(IllegalArgumentException.class,() ->{Book b = new Book("Hallo"," ",3);
        });
        assertThrows(IllegalArgumentException.class, ()->{Book b = new Book("Hallo","Welt",0);
        });
    }

    @Test
    public void testToString(){
        Book b = new Book("Servus Hervus","MRX",10);
        assertEquals("Title: Servus Hervus. Author: MRX. Pages: 10", b.toString());
    }
}
