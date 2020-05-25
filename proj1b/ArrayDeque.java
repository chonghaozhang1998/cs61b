import org.junit.Test;

public class ArrayDeque<T> implements Deque<T>{
    private int size;
    private int capacity;
    private int nextFirst;
    private int nextLast;
    private T[] array;


    public ArrayDeque() {
        this.size = 0;
        this.capacity = 8;
        this.nextFirst = 0;
        this.nextLast = 1;
        this.array = (T[]) new Object[8];
    }


    private void resizeLarge() {
        T[] temp = null;
        if (this.size == this.capacity) {
            temp = (T[]) new Object[this.size * 2];
            int tempNextFirst = this.nextFirst;
            int i = this.size;
            int position = 0;
            while (i > 0) {
                tempNextFirst = (tempNextFirst + 1) == this.capacity ? 0 : tempNextFirst + 1;
                temp[position] = array[tempNextFirst];
                i--;
                position++;
            }
            this.capacity = this.size * 2;
            this.nextFirst = this.capacity - 1;
            this.nextLast = this.size;
            this.array = temp;
        }
    }

    private void resizeSmall() {
        T[] temp = null;
        if (this.size < (this.capacity) / 2) {
            temp = (T[]) new Object[this.capacity / 2];
            int tempNextFirst = this.nextFirst;
            int i = this.size;
            int position = 0;
            while (i >= 0) {
                tempNextFirst = (tempNextFirst + 1) == this.capacity ? 0 : tempNextFirst + 1;
                temp[position] = array[tempNextFirst];
                i--;
                position++;
            }
            this.capacity = this.capacity / 2;
            this.nextFirst = this.capacity - 1;
            this.nextLast = this.size;
            this.array = temp;
        }
    }
    @Override
    public void addFirst(T item) {
        this.size += 1;
        this.array[nextFirst] = item;
        this.nextFirst = this.nextFirst - 1 == -1 ? this.capacity - 1 : this.nextFirst - 1;
        resizeLarge();
    }

    @Override
    public void addLast(T item) {
        this.size += 1;
        this.array[nextLast] = item;
        this.nextLast = this.nextLast + 1 == this.capacity  ? 0 : this.nextLast + 1;
        resizeLarge();
    }

    @Override
    public boolean isEmpty() {
        return this.size == 0;
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public void printDeque() {
        int tempNextFirst = this.nextFirst;
        int i = this.size;
        while (i > 0) {
            tempNextFirst = (tempNextFirst + 1) == this.capacity ? 0 : tempNextFirst + 1;
            System.out.print(array[tempNextFirst]);
            System.out.print(" ");
            i--;
        }
    }

    @Override
    public T removeFirst() {
        if (this.size == 0) {
            return null;
        }
        this.size -= 1;
        T res = null;
        int resIndex = 0;
        if (this.nextFirst + 1 >= this.capacity) {
            resIndex = 0;
        } else {
            resIndex = this.nextFirst + 1;
        }
        res = this.array[resIndex];
        this.nextFirst = resIndex;
        this.array[nextFirst] = null;
        resizeSmall();
        return res;
    }

    @Override
    public T removeLast() {
        if (this.size == 0) {
            return null;
        }
        this.size -= 1;
        T res = null;
        int resIndex = 0;
        if (this.nextLast == 0) {
            resIndex = this.capacity - 1;
        } else {
            resIndex = this.nextLast - 1;
        }
        this.nextLast = resIndex;
        res = (T) this.array[resIndex];
        this.array[resIndex] = null;
        resizeSmall();
        return res;
    }

    @Override
    public T get(int index) { // index 相当于到达目标position，需要走的步数
        if (index > this.size) {
            return null;
        }
        int tempNextFirst = this.nextFirst;
        int i = 0;
        while (i <= index) {
            tempNextFirst = (tempNextFirst + 1) % this.capacity;
            i++;
        }
        return this.array[tempNextFirst];
    }

}
