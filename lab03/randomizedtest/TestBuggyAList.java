package randomizedtest;

import edu.princeton.cs.algs4.StdRandom;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Created by hug.
 */
public class TestBuggyAList {
  @Test
    public void testThreeAddThreeRemove() {
    AListNoResizing<Integer> aList = new AListNoResizing<>();
    BuggyAList<Integer> aListBuggy = new BuggyAList<>();
    aList.addLast(4);
    aList.addLast(5);
    aList.addLast(6);
    aListBuggy.addLast(4);
    aListBuggy.addLast(5);
    aListBuggy.addLast(6);

    assertEquals(aList.size(), aListBuggy.size());

    assertEquals(aList.removeLast(), aListBuggy.removeLast());
    assertEquals(aList.removeLast(), aListBuggy.removeLast());
    assertEquals(aList.removeLast(), aListBuggy.removeLast());
  }

  @Test
  public void randomizedTest() {
    AListNoResizing<Integer> L = new AListNoResizing<>();
    BuggyAList<Integer> BuggyL = new BuggyAList<>();

    int N = 5000;
    for (int i = 0; i < N; i += 1) {
      int operationNumber = StdRandom.uniform(0, 4);
      if (operationNumber == 0) {
        // addLast
        int randVal = StdRandom.uniform(0, 100);
        L.addLast(randVal);
        BuggyL.addLast(randVal);
        System.out.println("addLast(" + randVal + ")");
      } else if (operationNumber == 1) {
        // size
        int size = L.size();
        int sizeB = BuggyL.size();
        System.out.println("size: " + size);
        assertEquals(size, sizeB);
      } else if (operationNumber == 2) {
        // getLast
        if (L.size() == 0 || BuggyL.size() == 0) {continue;}
        int last = L.getLast();
        int lastB = BuggyL.getLast();
        System.out.println("last: " + last);
        assertEquals(last, lastB);
      } else {
        // removeLast
        if (L.size() == 0  || BuggyL.size() == 0) {continue;}
        int removeLast = L.removeLast();
        int removeLastB = BuggyL.removeLast();
        System.out.println("remove Last: " + removeLast);
        assertEquals(removeLast, removeLastB);
      }
    }
  }
}
