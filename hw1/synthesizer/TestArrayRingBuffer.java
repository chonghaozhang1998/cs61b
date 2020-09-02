package synthesizer;
import org.junit.Test;
import static org.junit.Assert.*;

/** Tests the ArrayRingBuffer class.
 *  @author Josh Hug
 */

public class TestArrayRingBuffer {
    @Test
    public void someTest() {
        //ArrayRingBuffer arb = new ArrayRingBuffer(10);
        ArrayRingBuffer<Integer> arb = new ArrayRingBuffer<>(5);
        arb.enqueue(1);
        arb.enqueue(2);
        arb.enqueue(3);
        for (int i = 0; i < 3; i++) {
            System.out.println(arb.dequeue());
        }
        for (int i = 0; i < 5; i++) {
            arb.enqueue(i * 2);
        }
        for (int i = 0; i < 5; i++) {
            System.out.println(arb.dequeue());
        }
        for (int i = 0; i < 4; i++) {
            arb.enqueue(i * 3);
        }
        for (int i = 0; i < 4; i++) {
            System.out.println(arb.dequeue());
        }


    }

    /** Calls tests for ArrayRingBuffer. */
    public static void main(String[] args) {
        jh61b.junit.textui.runClasses(TestArrayRingBuffer.class);
    }
} 
