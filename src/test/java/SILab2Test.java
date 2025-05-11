import org.example.Item;
import org.example.SILab2;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class SILab2Test {

    @Test
    void EveryStatementMinimal() {
        // Test 1: Null items list
        assertThrows(RuntimeException.class, () -> SILab2.checkCart(null, "any"));
        System.out.println("test 1 successful");

        // Test 2: Item with null name
        assertThrows(RuntimeException.class, () -> SILab2.checkCart(List.of(new Item(null, 1, 100, 0)), "any"));
        System.out.println("test 2 successful");

        // Test 3: Valid items with:
        // - price > 300
        // - discount > 0
        // - quantity > 10
        // - valid card
        List<Item> items = List.of(new Item("Item1", 15, 350, 0.1));
        double expected = (350 * 0.9 * 15) - 30; // (price*(1-disc)*qty) - penalty
        assertEquals(expected, SILab2.checkCart(items, "1234567890123456"), 0.001);
        System.out.println("test 3 successful");


        // Test 4: Invalid card number
        assertThrows(RuntimeException.class,() -> SILab2.checkCart(List.of(new Item("Item", 1, 100, 0)), "invalid"));
        System.out.println("test 4 successful");
    }

    @Test
    void MultipleCondition() {
        // TC1: TXX (само price > 300)
        List<Item> tff = List.of(new Item("Item1", 5, 350, 0));
        assertEquals(350*5 - 30, SILab2.checkCart(tff, "1234567890123456"), 0.001);
        System.out.println("TXX test successful");

        // TC2: FTX (само discount > 0)
        List<Item> ftf = List.of(new Item("Item2", 5, 200, 0.1));
        assertEquals(200*0.9*5 - 30, SILab2.checkCart(ftf, "1234567890123456"), 0.001);
        System.out.println("FTX test successful");

        // TC3: FFT (само quantity > 10)
        List<Item> fft = List.of(new Item("Item3", 15, 200, 0));
        assertEquals(200*15 - 30, SILab2.checkCart(fft, "1234567890123456"), 0.001);
        System.out.println("FFT test successful");

        // TC4: FFF (сите false)
        List<Item> fff = List.of(new Item("Item4", 5, 200, 0));
        assertEquals(200*5, SILab2.checkCart(fff, "1234567890123456"), 0.001);
        System.out.println("FFF test successful");
    }

}