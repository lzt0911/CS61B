package deque;

import java.util.Comparator;
import java.util.Iterator;

public class MaxArrayDeque<T> extends ArrayDeque<T> {
    private Comparator<T> cmp;

    public MaxArrayDeque(Comparator<T> c) {
        cmp = c;
    }

    public T max() {
        if (size() == 0) {
            return null;
        }
        T maxItem = get(0);
        Iterator<T> curIterator = this.iterator();
        while (curIterator.hasNext()) {
            T curItem = curIterator.next();
            if (cmp.compare(curItem, maxItem) > 0) {
                maxItem = curItem;
            }
        }
        return maxItem;
    }

    public T max(Comparator<T> c) {
        if (size() == 0) {
            return null;
        }
        T maxItem = get(0);
        Iterator<T> curIterator = this.iterator();
        while (curIterator.hasNext()) {
            T curItem = curIterator.next();
            if (c.compare(curItem, maxItem) > 0) {
                maxItem = curItem;
            }
        }
        return maxItem;
    }
}
