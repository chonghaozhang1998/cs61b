public class ArrayDeque<T> {
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

    private void resize() {
        T[] temp = null;
        if (this.size >= this.capacity) {
            temp = (T[]) new Object[this.size * 2];
            int tempNextFirst = this.nextFirst;
            int i = this.size;
            int position = 0;
            while (i >= 0) {
                temp[position] = array[(tempNextFirst + 1)];
                tempNextFirst = (tempNextFirst + 1) % this.capacity;
                i--;
                position++;
            }
            this.capacity = this.size * 2;
            this.nextFirst = this.capacity - 1;
            this.nextLast = this.size;
            this.array = temp;
        } else if (this.size < this.capacity / 2) {
            temp = (T[]) new Object[this.capacity / 2];
            int tempNextFirst = this.nextFirst;
            int i = this.size;
            int position = 0;
            while (i >= 0) {
                temp[position] = array[(tempNextFirst + 1)];
                tempNextFirst = (tempNextFirst + 1) % this.capacity;
                i--;
                position++;
            }
            this.capacity = this.capacity / 2;
            this.nextFirst = this.capacity - 1;
            this.nextLast = this.size;
            this.array = temp;
        }
    }

    public void addFirst(T item) {
        this.size += 1;
        resize();
        this.array[nextFirst] = item;
        this.nextFirst = this.nextFirst - 1 < 0 ? this.capacity - 1 : this.nextFirst - 1;
    }

    public void addLast(T item) {
        this.size += 1;
        resize();
        this.array[nextLast] = item;
        this.nextLast = this.nextLast + 1 > this.capacity - 1 ? (this.nextLast + 1) % (this.capacity - 1) : this.nextLast + 1;
    }

    public boolean isEmpty() {
        return this.size == 0;
    }

    public int size() {
        return this.size;
    }

    public void printDeque() {
        int tempNextFirst = this.nextFirst;
        int i = this.size;
        while (i >= 0) {
            System.out.print(this.array[tempNextFirst]);
            System.out.print(" ");
            tempNextFirst = (tempNextFirst + 1) % this.capacity;
            i--;
        }
    }

    public T removeFirst() {
        if (this.size == 0) {
            return null;
        }
        this.size -= 1;
        T res = null;
        int resIndex = 0;
        if (this.nextFirst + 1 > this.capacity) {
            resIndex = 0;
        } else {
            resIndex = this.nextFirst + 1;
        }
        res = this.array[resIndex];
        this.nextFirst = resIndex;
        this.array[nextFirst] = null;
        resize();
        return res;
    }


    public T removeLast() {
        if (this.size == 0) {
            return null;
        }
        this.size -= 1;
        T res = null;
        int resIndex = 0;
        if (this.nextLast - 1 < 0) {
            resIndex = this.capacity - 1;
        } else {
            resIndex = this.nextLast - 1;
        }
        this.nextLast = resIndex;
        res = this.array[resIndex];
        this.array[resIndex] = null;
        resize();
        return res;
    }

    public T get(int index) {
        if (index > this.size) {
            return null;
        }
        int tempNextFirst = this.nextFirst;
        int i = this.size;
        while (i > index) {
            tempNextFirst = (tempNextFirst + 1) % this.capacity;
            i--;
        }
        return this.array[tempNextFirst];
    }

}
