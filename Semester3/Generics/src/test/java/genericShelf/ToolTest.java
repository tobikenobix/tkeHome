package genericShelf;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
public class ToolTest {
    static Tool t;
    @BeforeAll
    public static void initialize(){
        t = new Tool("Hammer");
    }
    @Test
    public void testConstructorValid(){
        assertEquals("Hammer", t.getName());
    }
    @Test
    public void testConstructorInvalid(){
        assertThrows(IllegalArgumentException.class, ()->{Tool t1 = new Tool(" ");});
    }
    @Test
    public void testToString(){
        assertEquals("Name: Hammer", t.toString());
    }
}
