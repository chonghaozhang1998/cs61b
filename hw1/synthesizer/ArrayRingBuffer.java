package synthesizer;

import java.util.Iterator;

//Make sure to make this class and all of its methods public
//Make sure to make this class extend AbstractBoundedQueue<t>
public class ArrayRingBuffer<T> extends AbstractBoundedQueue<T> {
    /* Index for the next dequeue or peek. */
    private int first;            // index for the next dequeue or peek
    /* Index for the next enqueue. */
    private int last;
    /* Array for storing the buffer data. */
    private T[] rb;

    /**
     * Create a new ArrayRingBuffer with the given capacity.
     */
    public ArrayRingBuffer(int capacity) {
        //       Create new array with capacity elements.
        //       first, last, and fillCount should all be set to 0.
        //       this.capacity should be set appropriately. Note that the local variable
        //       here shadows the field we inherit from AbstractBoundedQueue, so
        //       you'll need to use this.capacity to set the capacity.
        this.capacity = capacity;
        this.fillCount = 0;
        this.first = 0;
        this.last = 0;
        rb = (T[]) (new Object[capacity]);
    }

    /**
     * Adds x to the end of the ring buffer. If there is no room, then
     * throw new RuntimeException("Ring buffer overflow"). Exceptions
     * covered Monday.
     *
     * @param x
     */
    public void enqueue(T x) {
        // Enqueue the item. Don't forget to increase fillCount and update last.
        if (!this.isFull()) {
            rb[this.last] = (T) x;
            this.last = (this.last + 1) % this.capacity;
            this.fillCount++;
        } else {
            throw new RuntimeException("Ring Buffer Overflow");
        }

    }

    /**
     * Dequeue oldest item in the ring buffer. If the buffer is empty, then
     * throw new RuntimeException("Ring buffer underflow"). Exceptions
     * covered Monday.
     */
    public T dequeue() {
        //Dequeue the first item. Don't forget to decrease fillCount and update
        Object res = null;
        if (!this.isEmpty()) {
            res = rb[this.first];
            rb[this.first] = null;
            this.first = (this.first + 1) % this.capacity;
            fillCount--;
        } else {
            throw new RuntimeException("Ring Buffer Underflow");
        }
        return (T) res;
    }

    /**
     * Return oldest item, but don't remove it.
     */
    public T peek() {
        // Return the first item. None of your instance variables should change.
        if (this.isEmpty()) {
            throw new RuntimeException();
        }
        return rb[this.first];
    }

    // When you get to part 5, implement the needed code to support iteration.
    private class ArrayRingIterator implements Iterator<T> {
        public boolean hasNext() {
            return first != last;
        }

        public T next() {
            return dequeue();
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new ArrayRingIterator();
    }
}
