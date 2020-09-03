import static org.junit.Assert.*;

import edu.princeton.cs.algs4.StdRandom;
import org.junit.Test;

public class TestArrayDequeGold {

    @Test
    public void someTest() {
        StudentArrayDeque<Integer> sad = new StudentArrayDeque<>();
        ArrayDequeSolution<Integer> ads = new ArrayDequeSolution<>();
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < 10; i++) {
            double tag = StdRandom.uniform();
            if (tag > 0.5) {
                sad.addFirst(i);
                ads.addFirst(i);
                sb.append("addFirst(" + i + ")");
            } else {
                sad.addLast(i);
                ads.addLast(i);
                sb.append("addLast(" + i + ")");
            }
            if (i != 9) {
                sb.append("\n");
            }
        }

        for (int i = 0; i < 10; i++) {
            double tag = StdRandom.uniform();
            if (tag > 0.5) {
                Integer expected = ads.removeLast();
                Integer actual = sad.removeLast();
                sb.append("\nremoveLast()");
                assertEquals(sb.toString(), expected, actual);
            } else {
                Integer expected = ads.removeFirst();
                Integer actual = sad.removeFirst();
                sb.append("\nremoveFirst()");
                assertEquals(sb.toString(), expected, actual);
            }
        }

    }

    public static void main(String[] args) {
        jh61b.junit.textui.runClasses(TestArrayDequeGold.class);
    }

}
