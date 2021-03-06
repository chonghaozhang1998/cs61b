public class LinkedListDeque<T> implements Deque<T> {
    private class Node<T> {
        private T item;
        private Node<T> prev;
        private Node<T> next;

        Node(T item, Node<T> prev, Node<T> next) {
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
    @Override
    public void addFirst(T item) {
        this.size += 1;
        Node<T> tempNext = this.sentFront.next;
        this.sentFront.next = new Node<>(item, this.sentFront, tempNext);
        tempNext.prev = this.sentFront.next;
    }

    /**
     * adds an item of type T to the back of the deque
     *
     * @param item add
     */
    @Override
    public void addLast(T item) {
        this.size += 1;
        Node<T> tempPrev = this.sentBack.prev;
        this.sentBack.prev = new Node<>(item, tempPrev, this.sentBack);
        tempPrev.next = this.sentBack.prev;
    }


    /**
     * Returns true of deque is empty, otherwise return false
     *
     * @return boolean
     */
    @Override
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
    @Override
    public int size() {
        return size;
    }

    /**
     * Prints the items in the deque from first to last, separated by a space
     */
    @Override
    public void printDeque() {
        Node<T> temp = sentFront;
        while (temp.next != sentBack) {
            System.out.print(temp.next.item);
            System.out.print(" ");
            temp = temp.next;
        }
    }

    /**
     * Removes and returns the item at the front of the deque. If no such item exists, returns null
     *
     * @return T
     */
    @Override
    public T removeFirst() {
        if (size == 0) {
            return null;
        }
        size -= 1;
        T res = sentFront.next.item;
        sentFront.next = sentFront.next.next;
        sentFront.next.prev = sentFront;
        return res;
    }


    /**
     * Removes and returns the item at the back of the deque. If no such item exists, returns null
     *
     * @return T
     */
    @Override
    public T removeLast() {
        if (size == 0) {
            return null;
        }
        size -= 1;
        T res = sentBack.prev.item;
        sentBack.prev = sentBack.prev.prev;
        sentBack.prev.next = sentBack;
        return res;
    }

    /**
     * Gets the item at the given index, where 0 is the front, 1 is the next item
     * , and so forth. If no such item exists, returns null. Must not alter the deque!
     *
     * @param index position
     * @return T
     */
    @Override
    public T get(int index) {
        if (index > size - 1) {
            return null;
        }
        Node<T> temp = sentFront.next;
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
            return getRecursiveHelper(index - 1, cur.next);
        }
        return null;
    }
}
