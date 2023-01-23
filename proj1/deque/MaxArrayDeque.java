package deque;

import java.util.Comparator;

public class MaxArrayDeque<T> extends ArrayDeque<T> {
    Comparator<T> dComparator;
    public MaxArrayDeque(Comparator<T> c) {
        super();
        dComparator = c;
    }

    /** returns the maximum element in the deque as governed by the previously given Comparator.
    If the MaxArrayDeque is empty, simply return null.
     */
    public T max() {
        if (size() == 0) {
            return null;
        }
        int maxIndex = 0;
        T max = get(maxIndex);
        for (int i = 0; i < size(); i++) {
            if (dComparator.compare(get(i), max) > 0) {
                maxIndex = i;
                max = get(maxIndex);
            }
        }
        return max;
    }

    /** returns the maximum element in the deque as governed by the parameter Comparator c.
    If the MaxArrayDeque is empty, simply return null.
     */
    public T max(Comparator<T> c) {
        if (size() == 0) {
            return null;
        }
        int maxIndex = 0;
        T max = get(maxIndex);
        for (int i = 0; i < size(); i++) {
            if (c.compare(get(i), max) > 0) {
                maxIndex = i;
                max = get(maxIndex);
            }
        }
        return max;
    }

}
