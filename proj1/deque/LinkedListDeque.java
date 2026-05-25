package deque;

import java.util.Iterator;

public class LinkedListDeque<T> implements Iterable<T>, Deque<T> {
    private class Node {
        private T item;
        private Node next;
        private Node prev;

        Node(T i, Node n, Node p) {
            item = i;
            next = n;
            prev = p;
        }
    }

    private Node sentinel;
    private int size;

    public LinkedListDeque() {
        size = 0;
        sentinel = new Node(null, null, null);
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
    }

    @Override
    public void addFirst(T item) {
        Node curFirst = sentinel.next;
        Node i = new Node(item, curFirst, sentinel);
        curFirst.prev = i;
        sentinel.next = i;
        size += 1;
    }

    @Override
    public void addLast(T item) {
        Node curLast = sentinel.prev;
        Node i = new Node(item, sentinel, curLast);
        sentinel.prev = i;
        curLast.next = i;
        size += 1;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void printDeque() {
        Node i = sentinel.next;
        while (i != sentinel) {
            System.out.print(i.item + " ");
            i = i.next;
        }
        System.out.println();
    }

    @Override
    public T removeFirst() {
        if (sentinel.next == sentinel) {
            return null;
        }
        T curFirstItem = sentinel.next.item;
        Node newFirst = sentinel.next.next;
        sentinel.next = newFirst;
        newFirst.prev = sentinel;
        size -= 1;
        return curFirstItem;
    }

    @Override
    public T removeLast() {
        if (sentinel.prev == sentinel) {
            return null;
        }
        T curLastItem = sentinel.prev.item;
        Node newLast = sentinel.prev.prev;
        sentinel.prev = newLast;
        newLast.next = sentinel;
        size -= 1;
        return curLastItem;
    }

    @Override
    public T get(int index) {
        if (index >= size) {
            return null;
        }
        Node i = sentinel.next;
        while (index > 0) {
            i = i.next;
            index -= 1;
        }
        return i.item;
    }

    private T getRecursive(int index, Node i) {
        if (index == 0) {
            return i.item;
        }
        return getRecursive(index - 1, i.next);
    }

    public T getRecursive(int index) {
        if (index >= size) {
            return null;
        }
        return getRecursive(index, sentinel.next);
    }

    public Iterator<T> iterator() {
        return new LinkedListDequeIterator();
    }

    private class LinkedListDequeIterator<T> implements Iterator<T> {
        private Node i;

        LinkedListDequeIterator() {
            i = sentinel.next;
        }

        public boolean hasNext() {
            return i != sentinel;
        }

        public T next() {
            T returnItem = (T) i.item;
            i = i.next;
            return returnItem;
        }
    }

    public boolean equals(Object o) {
        if (!(o instanceof Deque) || ((Deque<T>) o).size() != this.size()) {
            return false;
        }
        if (o == this) {
            return true;
        }
        for (int i = 0; i < this.size(); i++) {
            Object item = ((Deque<T>) o).get(i);
            if (!(this.get(i).equals(item))) {
                return false;
            }
        }
        return true;
    }

//    public boolean equals(Object o) {
//        if (this == o) {
//            return true;
//        }
//        if (o == null) {
//            return false;
//        }
//        Deque<T> other;
//        if (o.getClass() == LinkedListDeque.class) {
//            other = (LinkedListDeque<T>) o;
//        } else if (o.getClass() == ArrayDeque.class) {
//            other = (ArrayDeque<T>) o;
//        } else {
//            return false;
//        }
//
//        if (this.size() != other.size()) {
//            return false;
//        }
//
//        for (int start = 0; start < this.size(); start++) {
//            boolean match = true;
//            for (int i = 0; i < this.size(); i++) {
//                T a = this.get((start + i) % this.size());
//                T b = other.get(i);
//                if (!a.equals(b)) {
//                    match = false;
//                    break;
//                }
//            }
//            if (match) {
//                return true;
//            }
//        }
//        return false;
//    }
}
