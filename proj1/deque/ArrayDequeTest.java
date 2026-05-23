package deque;

import org.junit.Test;
import static org.junit.Assert.*;

public class ArrayDequeTest {
    @Test
    public void addTest() {

        ArrayDeque<Integer> lld1 = new ArrayDeque<Integer>();

        assertTrue("A newly initialized ArrayDeque should be empty", lld1.isEmpty());
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
    }
}
