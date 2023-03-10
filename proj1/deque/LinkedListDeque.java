package deque;

import java.util.Iterator;

public class LinkedListDeque<T> implements Iterable<T>, Deque<T>{
    public class Node {
        public T item;
        public Node next;
        public Node pre;
        public Node(T i, Node p, Node n) {
            item = i;
            next = n;
            pre =p;
        }
    }

    Node sentinel;
    Node last;
    int size;

    public LinkedListDeque() {
        sentinel = new Node(null, null, null);
        sentinel.next = sentinel;
        sentinel.pre = sentinel;
        last = sentinel.pre;
        size = 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void addFirst(T item) {
        Node newNode = new Node(item, sentinel, sentinel.next);
        sentinel.next.pre = newNode;
        sentinel.next = newNode;
        last = sentinel.pre;
        size += 1;
    }
    @Override
    public void addLast(T item) {
        Node newNode = new Node(item, last, sentinel);
        last.next = newNode;
        sentinel.pre = newNode;
        last = newNode;
        size += 1;
    }

    @Override
    public void printDeque() {
        Node p = sentinel.next;
        while (p != sentinel) {
            System.out.print(p.item);
            System.out.print(' ');
            p = p.next;
        }
        System.out.println(' ');
    }

    // Removes and returns the item at the front of the deque. If no such item exists, returns null.
    @Override
    public T removeFirst() {
        if (size == 0) {
            return null;
        }
        T item = sentinel.next.item;
        sentinel.next.next.pre = sentinel;
        sentinel.next = sentinel.next.next;
        size -= 1;
        last = sentinel.pre;
        return item;
    }

    @Override
    public T removeLast() {
        if (size == 0) {
            return null;
        }
        T item = last.item;
        last.pre.next = sentinel;
        sentinel.pre = last.pre;
        size -= 1;
        last = sentinel.pre;
        return item;
    }

    // Gets the item at the given index, where 0 is the front, 1 is the next item, and so forth.
    // If no such item exists, returns null. Must not alter the deque!
    @Override
    public T get(int index) {
        if (index >= size) {
            return null;
        }
        Node p = sentinel;
        for(int i = 0; i <= index; i++) {
            p = p.next;
        }
        return p.item;
    }

    private class LLDIterator implements Iterator<T> {
        private Node wizPoz;
        public LLDIterator() {
            wizPoz = sentinel.next;
        }

        @Override
        public boolean hasNext() {
            return wizPoz != sentinel;
        }

        @Override
        public T next() {
            T item = wizPoz.item;
            wizPoz = wizPoz.next;
            return item;
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new LLDIterator();

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o instanceof Deque oLLD) {
            if (size != oLLD.size()) {
                return false;
            }
            int i = 0;
            for (T x: this) {
                if (!x.equals(get(i))) {
                    return false;
                }
                i++;
            }
            return true;
        }
        return false;
    }
}
