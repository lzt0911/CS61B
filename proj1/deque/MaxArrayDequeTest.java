package deque;

import org.junit.Test;
import static org.junit.Assert.*;
import java.util.Comparator;

public class MaxArrayDequeTest {
    @Test
    public void Test1() {
        Comparator<Integer> cmp = new Comparator<Integer>() {
            public int compare(Integer s1, Integer s2) {
                return s1 - s2;
            }
        };
        MaxArrayDeque<Integer> lld1 = new MaxArrayDeque<Integer>(cmp);

        assertTrue("A newly initialized MaxArrayDeque should be empty", lld1.isEmpty());
        lld1.addFirst(1);
        lld1.addFirst(2);
        lld1.addFirst(3);
        lld1.addLast(4);
        lld1.addLast(5);
        lld1.addLast(6);
        lld1.addFirst(7);
        lld1.addFirst(8);
        lld1.addLast(9);
        lld1.addLast(10);

        System.out.println("Printing out deque: ");
        lld1.printDeque();

        System.out.printf("Max item: %d", lld1.max());
    }
}
