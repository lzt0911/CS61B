package tester;

import static org.junit.Assert.*;

import edu.princeton.cs.introcs.StdRandom;
import org.junit.Test;
import student.StudentArrayDeque;

public class TestArrayDequeEC {
    @Test
    public void test() {
        StudentArrayDeque<Integer> sad1 = new StudentArrayDeque<>();
        ArrayDequeSolution<Integer> aad1 = new ArrayDequeSolution<>();
        StringBuilder message = new StringBuilder();
        for (int i = 0; i < 100; i += 1) {
            double numberBetweenZeroAndOne = StdRandom.uniform();

            if (numberBetweenZeroAndOne < 0.5) {
                sad1.addLast(i);
                aad1.addLast(i);
                String s = "addLast(" + i + ")\n";
                message.append(s);
            } else {
                sad1.addFirst(i);
                aad1.addFirst(i);
                String s = "addFirst(" + i + ")\n";
                message.append(s);
            }
        }

        for (int i = 0; i < 100; i += 1) {
            double numberBetweenZeroAndOne = StdRandom.uniform();

            if (numberBetweenZeroAndOne < 0.5) {
                Integer actual = sad1.removeLast();
                Integer expected = aad1.removeLast();
                message.append("removeLast()\n");
                assertEquals(message.toString(), expected, actual);
            } else {
                Integer actual = sad1.removeFirst();
                Integer expected = aad1.removeFirst();
                message.append("removeFirst()\n");
                assertEquals(message.toString(), expected, actual);
            }
        }
    }
}
