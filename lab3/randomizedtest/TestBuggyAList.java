package randomizedtest;

import edu.princeton.cs.algs4.StdRandom;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Created by hug.
 */
public class TestBuggyAList {
  // YOUR TESTS HERE
    @Test
    public void testThreeAddThreeRemove() {
        AListNoResizing<Integer> lst = new AListNoResizing();
        lst.addLast(4);
        lst.addLast(5);
        lst.addLast(6);
        BuggyAList<Integer> buggyLst = new BuggyAList();
        buggyLst.addLast(4);
        buggyLst.addLast(5);
        buggyLst.addLast(6);
        assertEquals(6, (int)lst.removeLast());
        assertEquals(6, (int)buggyLst.removeLast());
        assertEquals(5, (int)lst.removeLast());
        assertEquals(5, (int)buggyLst.removeLast());
        assertEquals(4, (int)lst.removeLast());
        assertEquals(4, (int)buggyLst.removeLast());
    }

    @Test
    public void randomizedTest() {
        AListNoResizing<Integer> L = new AListNoResizing();
        BuggyAList<Integer> buggyL = new BuggyAList();
        int N = 5000;
        for (int i = 0; i < N; i += 1) {
            int operationNumber = StdRandom.uniform(0, 4);
            if (operationNumber == 0) {
                // addLast
                int randVal = StdRandom.uniform(0, 100);
                L.addLast(randVal);
                buggyL.addLast(randVal);
            } else if (operationNumber == 1) {
                // size
                int size = L.size();
                int buggySize = buggyL.size();
                assertEquals(size, buggySize);
            } else if (operationNumber == 2) {
                // getLast
                if (L.size() != 0) {
                    int last = L.getLast();
                    int buggyLast = buggyL.getLast();
                    assertEquals(last, buggyLast);
                }
            } else if (operationNumber == 3) {
                // removeLast
                if (L.size() != 0) {
                    int last = L.removeLast();
                    int buggyLast = buggyL.removeLast();
                    assertEquals(last, buggyLast);
                }
            }
        }
    }
}
