public class LinkedListDeque<T> {
    public class Node<T> {
        public T item;
        public Node<T> prev;
        public Node<T> next;

        public Node(T item, Node<T> prev, Node<T> next) {

            this.item = item;
            this.prev = prev;
            this.next = next;
        }
    }

    private int size = 0;
    private Node<T> sentinel = new Node<T>(null, null, null);
    private Node<T> last = sentinel;

    /**
     * Adds an item of type T to the front of the deque
     * @param item add
     */
    public void addFirst(T item) {
        size += 1;

        Node<T> temp = sentinel;

        if(temp.next == null) {
            temp.next = new Node<T>(item, null, temp);
            last = temp.next;
        }else {
            while(temp.next != null) {
                temp = temp.next;
            }
            temp.next = new Node<T>(item, null, temp);
        }

    }

    /**
     * adds an item of type T to the back of the deque
     * @param item add
     */
    public void addLast(T item) {
        size += 1;

        last.next = new Node<>(item, last, null);
        last = last.next;

    }


    /**
     * Returns true of deque is empty, otherwise return false
     * @return boolean
     */
    public boolean isEmpty() {
        if(size == 0) {
            return true;
        }
        return false;
    }

    /**
     * Returns the number of the items in the deque
     * @return int
     */
    public int size() {
        return size;
    }

    /**
     * Prints the items in the deque from first to last, separated by a space
     */
    public void printDeque() {
        Node<T> temp = sentinel;
        while(temp.next != null) {
            System.out.print(temp.next.item);
            System.out.print(" ");
            temp = temp.next;
        }
    }

    /**
     * Removes and returns the item at the front of the deque. If no such item exists, returns null
     * @return T
     */
    public T removeFirst() {
        if(size == 0) {
            return null;
        }
        size -= 1;
        Node<T> temp = sentinel;
        T res = temp.next.item;
        if(temp.next.next == null) {
            temp.next = null;
            last = sentinel;
        }else {
            temp.next = temp.next.next;
        }
        return res;
    }


    /**
     * Removes and returns the item at the back of the deque. If no such item exists, returns null
     * @return T
     */

    public T removeLase() {
        if(size == 0) {
            return null;
        }
        size -= 1;
        T res = last.item;
        last.prev.next = null;
        return res;
    }

    /**
     * Gets the item at the given index, where 0 is the front, 1 is the next item
     * , and so forth. If no such item exists, returns null. Must not alter the deque!
     * @param index position
     * @return T
     */
    public T get(int index) {
        if(index > size - 1) {
            return null;
        }
        Node<T> temp = sentinel;
        while(index != 0) {
            temp = temp.next;
            index --;
        }
        return temp.item;
    }
}
