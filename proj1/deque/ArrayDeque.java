package deque;

import java.util.Iterator;

public class ArrayDeque<T> implements Iterable<T>, Deque<T>{
    private T[] items;
    private int size;
    private int nextFirst;
    private int nextLast;

    /** Creates an empty array deque. */
    public ArrayDeque() {
        items = (T[]) new Object[8];
        size = 0;
        nextFirst = 7;
        nextLast = 0;
    }

    /** Resizes the underlying array to the target capacity. */
    private void resize(int capacity) {
        T[] a = (T[]) new Object[capacity];
        int length = items.length;
        int first = (nextFirst + 1) % length;
        for (int i = 0; i < size; i++) {
            a[i] = items[(first + i) % length];
        }
        items = a;
        nextFirst = capacity - 1;
        nextLast = size;
    }

    @Override
    public void addFirst(T item) {
        if (size == items.length) {
            resize(size * 2);
        }
        items[nextFirst] = item;

        int length = items.length;
        nextFirst = (nextFirst - 1 + length) % length;
        size = size + 1;
    }

    /** Inserts X into the back of the list. */
    @Override
    public void addLast(T item) {
        if (size == items.length) {
            resize(size * 2);
        }
        items[nextLast] = item;

        nextLast = (nextLast + 1) % items.length;
        size = size + 1;
    }

    /** Gets the ith item in the list (0 is the front). */
    @Override
    public T get(int index) {
        return items[(nextFirst + 1 + index) % items.length];
    }

    /** Returns the number of items in the list. */
    @Override
    public int size() {
        return size;
    }

    // Removes and returns the item at the front of the deque. If no such item exists, returns null.
    @Override
    public T removeFirst() {
        if (size == 0) {
            return null;
        }
        int length =  items.length;
        if (length >= 16 && (size - 1) / (double)length < 0.25) {
            resize(length / 2);
        }

        int firstIndex = (nextFirst + 1) % items.length;
        T item = items[firstIndex];
        items[firstIndex] = null;

        size -= 1;
        nextFirst = firstIndex;
        return item;
    }

    /** Deletes item from back of the list and
     * returns deleted item. */
    @Override
    public T removeLast() {
        if (size == 0) {
            return null;
        }
        int length =  items.length;
        if (length >= 16 && (size - 1) / (double)length < 0.25) {
            resize(length / 2);
        }

        int lengthNew = items.length;
        int lastIndex = (nextLast - 1 + lengthNew) % lengthNew;
        T item = items[lastIndex];
        items[lastIndex] = null;

        size -= 1;
        nextLast = lastIndex;
        return item;
    }

    /*
    public boolean equals(Object o) {

    }
*/
    @Override
    public void printDeque() {
        int length = items.length;
        int first = (nextFirst + 1) % length;
        for (int i = 0; i < size; i++) {
            System.out.print(items[(first + i) % length]);
            System.out.print(' ');
        }
        System.out.println(' ');
    }

    private class ADIterator implements Iterator<T> {
        private int wizPoz;
        public ADIterator() {
            wizPoz = (nextFirst + 1) % items.length;
        }

        @Override
        public boolean hasNext() {
            return items[wizPoz] != null;
        }

        @Override
        public T next() {
            T item = items[wizPoz];
            wizPoz = (wizPoz + 1) % items.length;
            return item;
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new ArrayDeque.ADIterator();

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o instanceof Deque oAD) {
            if (size != oAD.size()) {
                return false;
            }
            int i = 0;
            for (T x: this) {
                if (!x.equals(get(i))){
                    return false;
                }
                i++;
            }
            return true;
        }
        return false;
    }
}
