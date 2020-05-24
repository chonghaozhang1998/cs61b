public class LinkedListDeque<T> {
    private class Node<T> {
        public T item;
        public Node<T> prev;
        public Node<T> next;

        public Node(T item, Node<T> prev, Node<T> next) {
            this.item = item;
            this.prev = prev;
            this.next = next;
        }
    }

    private int size;
    private Node<T> sentFront;
    private Node<T> sentBack;

    public LinkedListDeque() {
        size = 0;
        sentFront = new Node<>(null, null, null);
        sentBack = new Node<>(null, null, null);
        sentBack.prev = sentFront;
        sentFront.next = sentBack;
    }


    /**
     * Adds an item of type T to the front of the deque
     *
     * @param item add
     */
    public void addFirst(T item) {
        size += 1;
        Node<T> temp = sentFront.next;
        sentFront.next = new Node<>(item, sentFront, temp);
    }

    /**
     * adds an item of type T to the back of the deque
     *
     * @param item add
     */
    public void addLast(T item) {
        size += 1;
        Node<T> temp = sentBack.prev;
        sentBack.prev = new Node<>(item, temp, sentBack);

    }


    /**
     * Returns true of deque is empty, otherwise return false
     *
     * @return boolean
     */
    public boolean isEmpty() {
        if (size == 0) {
            return true;
        }
        return false;
    }

    /**
     * Returns the number of the items in the deque
     *
     * @return int
     */
    public int size() {
        return size;
    }

    /**
     * Prints the items in the deque from first to last, separated by a space
     */
    public void printDeque() {
        Node<T> temp = sentFront;
        while (temp.next != sentBack) {
            System.out.print(temp.next.item);
            System.out.print(" ");
        }
    }

    /**
     * Removes and returns the item at the front of the deque. If no such item exists, returns null
     *
     * @return T
     */
    public T removeFirst() {
        if (size == 0) {
            return null;
        }
        size -= 1;
        T res = sentFront.next.item;
        sentFront.next = sentFront.next.next;
        return res;
    }


    /**
     * Removes and returns the item at the back of the deque. If no such item exists, returns null
     *
     * @return T
     */

    public T removeLast() {
        if (size == 0) {
            return null;
        }
        size -= 1;
        T res = sentBack.prev.item;
        sentBack.prev = sentBack.prev.prev;
        return res;
    }

    /**
     * Gets the item at the given index, where 0 is the front, 1 is the next item
     * , and so forth. If no such item exists, returns null. Must not alter the deque!
     *
     * @param index position
     * @return T
     */
    public T get(int index) {
        if (index > size - 1) {
            return null;
        }
        Node<T> temp = sentFront;
        while (index != 0) {
            temp = temp.next;
            index--;
        }
        return temp.item;
    }

    public T getRecursive(int index) {
        return getRecursiveHelper(index, sentFront);
    }

    private T getRecursiveHelper(int index, Node<T> cur) {
        if (cur.next != null) {
            if (index == 0) {
                return cur.next.item;
            }
            return getRecursiveHelper(index--, cur.next);
        }
        return null;

    }
}