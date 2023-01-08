package deque;

public class LinkedListDeque<T> {
    private class Node {
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

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void addFirst(T item) {
        Node newNode = new Node(item, sentinel, sentinel.next);
        sentinel.next.pre = newNode;
        sentinel.next = newNode;
        last = sentinel.pre;
        size += 1;
    }
    public void addLast(T item) {
        Node newNode = new Node(item, last, sentinel);
        last.next = newNode;
        sentinel.pre = newNode;
        last = newNode;
        size += 1;
    }

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
}
