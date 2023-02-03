package bstmap;

import java.util.Iterator;
import java.util.Set;

public class BSTMap<K extends Comparable, V> implements Map61B<K, V> {
    public BSTNode root;
    public int size;
    private class BSTNode {
        public K key;
        public V value;
        BSTNode left;
        BSTNode right;
        public BSTNode(K key, V value) {
            this.key = key;
            this.value = value;
            left = null;
            right = null;
        }

    }

    public BSTMap() {
        root = null;
        size = 0;
    }
    /** Removes all the mappings from this map. */
    @Override
    public void clear() {
        root = null;
        size = 0;
    }

    /* Returns true if this map contains a mapping for the specified key. */
    @Override
    public boolean containsKey(K key) {
        return containsKey(root, key);
    }
    public boolean containsKey(BSTNode b, K key) {
        if (b == null) {
            return false;
        }
        if (key.compareTo(b.key) > 0) {
            return containsKey(b.right, key);
        } else if (key.compareTo(b.key) < 0){
            return containsKey(b.left, key);
        }
        return true;
    }

    /* Returns the value to which the specified key is mapped, or null if this
     * map contains no mapping for the key.
     */
    @Override
    public V get(K key) {
        return get(root, key);
    }

    public V get(BSTNode b, K key) {
        if (b == null) {
            return null;
        }
        if (key.compareTo(b.key) > 0) {
            return get(b.right, key);
        } else if (key.compareTo(b.key) < 0){
           return get(b.left, key);
        }
        return b.value;
    }

    /* Returns the number of key-value mappings in this map. */
    @Override
    public int size() {
        return size;
    }

    /* Associates the specified value with the specified key in this map. */
    @Override
    public void put(K key, V value) {
        if (!containsKey(key)) {
            size += 1;
        }
        root = put(root, key, value);
    }

    public BSTNode put(BSTNode b, K key, V value) {
        if (b == null) {
            return new BSTNode(key, value);
        }
        if (key.compareTo(b.key) < 0) {
            b.left = put(b.left, key, value);
        } else if (key.compareTo(b.key) > 0) {
            b.right = put(b.right, key, value);
        } else {
            b.value = value;
        }
        return b;
    }

    /* Returns a Set view of the keys contained in this map. Not required for Lab 7.
     * If you don't implement this, throw an UnsupportedOperationException. */
    @Override
    public Set<K> keySet() {
        throw new UnsupportedOperationException();
    }

    /* Removes the mapping for the specified key from this map if present.
     * Not required for Lab 7. If you don't implement this, throw an
     * UnsupportedOperationException. */
    @Override
    public V remove(K key) {
        if (!containsKey(key)) {
            return null;
        }
        V removedV;

        // find key's node
        BSTNode node = root;
        BSTNode parent = null;
        String dir = "";
        while(node != null) {
            int compare = key.compareTo(node.key);
            if (compare > 0) {
                parent = node;
                dir = "right";
                node = node.right;
            } else if (compare < 0) {
                parent = node;
                dir = "left";
                node = node.left;
            } else {
                break;
            }
        }
        BSTNode left = node.left;
        BSTNode right = node.right;
        removedV = node.value;

        // 1. no children
        if (left == null && right == null) {
            removeNoChild(parent, dir);
        }

        // 2. one child
        if (left == null && right != null) {
            removeRightChild(node, parent, dir);
        }
        if (left != null && right == null) {
            removeLeftChild(node, parent, dir);
        }

        // 3. two children
        if (left != null && right != null) {
            BSTNode leftMax = node.left;
            BSTNode lMParent = node;
            String lMDir = "left";
            while(leftMax.right != null) {
                lMParent = leftMax;
                leftMax = leftMax.right;
                lMDir = "right";
            }
            // remove leftMax
            if (leftMax.left == null) {
                removeNoChild(lMParent, lMDir);
            } else {
                removeLeftChild(leftMax, lMParent, lMDir);
            }
            // replace node with leftMax
            leftMax.left = node.left;
            leftMax.right = node.right;
            if (dir.equals("left")) {
                parent.left = leftMax;
            } else if (dir.equals("right")) {
                parent.right = leftMax;
            } else {
                root = leftMax;
            }
        }

        size -= 1;
        return removedV;
    }

    public void removeNoChild(BSTNode parent, String dir) {
        if (dir.equals("left")) {
            parent.left = null;
        } else if (dir.equals("right")) {
            parent.right = null;
        } else {
            root = null;
        }
    }

    public void removeLeftChild(BSTNode node, BSTNode parent, String dir) {
        if (dir.equals("left")) {
            parent.left = node.left;
        } else if (dir.equals("right")) {
            parent.right = node.left;
        } else {
            root = node.left;
        }
    }

    public void removeRightChild(BSTNode node, BSTNode parent, String dir) {
        if (dir.equals("left")) {
            parent.left = node.right;
        } else if (dir.equals("right")) {
            parent.right = node.right;
        } else {
            root = node.right;
        }
    }

    /* Removes the entry for the specified key only if it is currently mapped to
     * the specified value. Not required for Lab 7. If you don't implement this,
     * throw an UnsupportedOperationException.*/
    @Override
    public V remove(K key, V value) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Iterator<K> iterator() {
        return null;
    }

    //prints BSTMap in order of increasing Key
    public void printInOrder() {
        printInOrder(root);
    }
    public void printInOrder(BSTNode b) {
        if (b == null) {
            return;
        }
        printInOrder(b.left);
        System.out.println(b.key);
        printInOrder(b.right);
    }
}
