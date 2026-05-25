package deque;

import java.util.Iterator;

public class ArrayDeque<T> implements Iterable<T>, Deque<T> {
    private int frontPos;
    private int lastPos;
    private int size;
    private T[] items;
    private int EXPAND_RFACTOR = 2;
    private int SHRINK_RFACTOR = 4;
    private int DEFAULT_CAPACITY = 8;

    public ArrayDeque() {
        items = (T[]) new Object[DEFAULT_CAPACITY];
        frontPos = DEFAULT_CAPACITY - 1;
        lastPos = 0;
        size = 0;
    }

    private void resize(int capacity) {
        T[] a = (T[]) new Object[capacity];
        int frontBegin = (frontPos + 1 + items.length) % items.length;
        int lastEnd = (lastPos - 1 + items.length) % items.length;
        int j = 0;
        if (frontBegin < lastEnd) {
            for (int i = frontBegin; i <= lastEnd; i++, j++) {
                a[j] = items[i];
            }
        } else {
            for (int i = frontBegin; i <= items.length - 1; i++, j++) {
                a[j] = items[i];
            }
            for (int i = 0; i <= lastEnd; i++, j++) {
                a[j] = items[i];
            }
        }
        frontPos = capacity - 1;
        lastPos = j;

        items = a;
    }

    @Override
    public void addFirst(T item) {
        if (size == items.length) {
            resize(size * EXPAND_RFACTOR);
        }
        frontPos = (frontPos + items.length) % items.length;
        items[frontPos] = item;
        frontPos = frontPos - 1;
        size += 1;
    }

    @Override
    public void addLast(T item) {
        if (size == items.length) {
            resize(size * EXPAND_RFACTOR);
        }
        lastPos = (lastPos + items.length) % items.length;
        items[lastPos] = item;
        lastPos = lastPos + 1;
        size += 1;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void printDeque() {
        int i = frontPos + 1;
        if (frontPos < lastPos) {
            System.out.print(items[i] + " ");
        } else {
            while (i < items.length) {
                System.out.print(items[i] + " ");
                i += 1;
            }
            i = 0;
            while (i < lastPos) {
                System.out.print(items[i] + " ");
                i += 1;
            }
        }

        System.out.println();
    }

    @Override
    public T removeFirst() {
        if (size == 0) {
            return null;
        }
        if (items.length >= 16 && items.length / size > SHRINK_RFACTOR) {
            resize(items.length / SHRINK_RFACTOR);
        }
        frontPos = (frontPos + 1 + items.length) % items.length;
        T returnItem = items[frontPos];
        size -= 1;
        return returnItem;
    }

    @Override
    public T removeLast() {
        if (size == 0) {
            return null;
        }
        if (items.length >= 16 && items.length / size > SHRINK_RFACTOR) {
            resize(items.length / SHRINK_RFACTOR);
        }
        lastPos = (lastPos - 1 + items.length) % items.length;
        T returnItem = items[lastPos];
        size -= 1;
        return returnItem;
    }

    @Override
    public T get(int index) {
        return items[(frontPos + 1 + index + items.length) % items.length];
    }

    public Iterator<T> iterator() {
        return new ArrayDequeIterator();
    }

    private class ArrayDequeIterator<T> implements Iterator<T> {
        private int wizPos;
        private int currentNum = 0;

        ArrayDequeIterator() {
            wizPos = (frontPos + 1 + items.length) % items.length;
        }

        public boolean hasNext() {
            return currentNum < size;
        }

        public T next() {
            T returnItem = (T) items[wizPos];
            wizPos = (wizPos + 1 + items.length) % items.length;
            currentNum += 1;
            return returnItem;
        }
    }

    private boolean equalsWithIterable(Iterable<T> thisIterable, Iterable<T> otherIterable,
                                       int thisSize, int otherSize) {
        if (thisSize != otherSize) {
            return false;
        }
        Iterator<T> thisIterator = thisIterable.iterator();
        Iterator<T> otherIterator = otherIterable.iterator();
        while (thisIterator.hasNext()) {
            if (!thisIterator.next().equals(otherIterator.next())) {
                return false;
            }
        }
        return true;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null) {
            return false;
        }
        if (o.getClass() == LinkedListDeque.class) {
            LinkedListDeque<T> other = (LinkedListDeque<T>) o;
            return equalsWithIterable(this, other, this.size(), other.size());
        } else if (o.getClass() == ArrayDeque.class) {
            ArrayDeque<T> other = (ArrayDeque<T>) o;
            return equalsWithIterable(this, other, this.size(), other.size());
        }

        return false;
    }
}
