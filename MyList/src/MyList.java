public class MyList<T> {
    private Object[] array;
    private int size = 0;

    public MyList() {
        this.array = new Object[10];
    }

    public MyList(int capacity) {
        this.array = new Object[capacity];
    }

    public int size() {
        return this.size;
    }

    public int getCapacity() {
        return this.array.length;
    }

    public void add(T data) {
        if (this.size == this.array.length) {
            ensureCapacity();
        }
        this.array[this.size++] = data;
    }

    @SuppressWarnings("unchecked")
    public T get(int index) {
        if (index >= 0 && index < this.size) {
            return (T) array[index];
        }
        return null;
    }

    @SuppressWarnings("unchecked")
    public T remove(int index) {
        if (index < 0 && index >= this.size) {
            return null;
        }
        T oldValue = (T) array[index];

        int numMoved = this.size - index - 1;
        if (numMoved > 0) {
            System.arraycopy(array, index + 1, array, index, numMoved);
        }
        this.array[--this.size] = null;

        return oldValue;
    }

    @SuppressWarnings("unchecked")
    public T set(int index, T data) {
        if (index >= 0 && index < this.size) {
            T oldValue = (T) this.array[index];
            this.array[index] = data;
            return oldValue;
        }
        return null;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append('[');
        for (int i = 0; i < this.size; i++) {
            sb.append(this.array[i].toString());
            if (i < size - 1) {
                sb.append(", ");
            }
        }
        sb.append(']');
        return sb.toString();
    }

    public int indexOf(T data) {
        for (int i = 0; i < this.size; i++) {
            if (data.equals(this.array[i])) {
                return i;
            }
        }
        return -1;
    }

    public int lastIndexOf(T data) {
        for (int i = this.size - 1; i >= 0; i--) {
            if (data.equals(this.array[i])) {
                return i;
            }
        }
        return -1;
    }

    public boolean isEmpty() {
        return this.size == 0;
    }

    @SuppressWarnings("unchecked")
    public T[] toArray() {
        return (T[]) java.util.Arrays.copyOf(this.array, this.size, this.array.getClass());
    }

    public void clear() {
        for (int i = 0; i < size; i++) {
            this.array[i] = null;
        }
        this.size = 0;
    }

    public MyList<T> subList(int start, int finish) {
        MyList<T> sublist = new MyList<>(finish - start + 1);
        for (int i = start; i <= finish; i++) {
            sublist.add((T) this.array[i]);
        }
        return sublist;
    }

    public boolean contains(T data) {
        return indexOf(data) >= 0;
    }

    private void ensureCapacity() {
        int newSize = array.length * 2;
        array = java.util.Arrays.copyOf(array, newSize);
    }
}
