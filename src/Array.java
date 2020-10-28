import java.util.Arrays;

public class Array<E extends Comparable<? super E>> {

    private static final int DEFAULT_CAPACITY = 8;

    protected E[] data;
    protected int size;

    public Array() {
        this(DEFAULT_CAPACITY);
    }

    @SuppressWarnings("unchecked")
    public Array(int initialCapacity) {
        this.data = (E[]) new Comparable[initialCapacity];
    }

    public Array(Array<E> array) {
        this.data = Arrays.copyOf(array.data, array.data.length);
        this.size = array.data.length;
    }

    public void add(E value) {
        checkAndGrow();
        data[size++] = value;
    }

    public void insert(E value, int index) {
        doInsert(value, index);
    }

    protected void doInsert(E value, int index) {
        checkAndGrow();
        if (index == size) {
            add(value);
        }
        else {
            checkIndex(index);
            if (size - index >= 0)  {
                System.arraycopy(data, index, data, index + 1, size - index);
            }
            data[index] = value;
            size++;
        }
    }

    public E get(int index) {
        checkIndex(index);
        return data[index];
    }

    public boolean remove(E value) {
        int index = indexOf(value);
        return index != -1 && (remove(index) != null);
    }

    public E remove(int index) {
        checkIndex(index);
        E removedValue = data[index];
        if (size - 1 - index >= 0) {
            System.arraycopy(data, index + 1, data, index, size - 1 - index);
        }
        data[size - 1] = null;
        size--;
        return removedValue;
    }

    public int indexOf(E value) {
        for (int i = 0; i < size; i++) {
            if (data[i].equals(value)) {
                return i;
            }
        }
        return -1;
    }

    public int size() {
        return size;
    }

    public void display() {
        System.out.println(toString());
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < size - 1; i++) {
            sb.append(data[i]);
            sb.append(", ");
        }
        if (size > 0) {
            sb.append(data[size - 1]);
        }
        sb.append("]");
        return sb.toString();
    }

    public void trimToSize() {
        data = Arrays.copyOf(data, size);
    }

    public void sortBubble() {
        E temp;
        for (int i = size - 1; i >= 0; i--) {
            for (int j = 0; j < i; j++) {
                if (data[j].compareTo(data[j + 1]) > 0) {
                    swap(j, j + 1);
                }
            }
        }
    }

    private void swap(int indexA, int indexB) {
        E temp = data[indexA];
        data[indexA] = data[indexB];
        data[indexB] = temp;
    }

    public void sortSelect() {
        for (int i = 0; i < size - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < size; j++) {
                if (data[j].compareTo(data[minIndex]) < 0) {
                    minIndex = j;
                }
            }
            if (minIndex !=  i) {
                swap(minIndex, i);
            }
        }
    }

    public void sortInsert() {
        for (int i = 1; i < size; i++) {
            E temp = data[i];
            int in = i;
            while (in > 0 && data[in - 1].compareTo(temp) >= 0) {
                data[in] = data[in - 1];
                in--;
            }
            data[in] = temp;
        }
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new MyCustomArrayIndexOutOfBoundsException(index, size);
        }
    }

    protected void checkAndGrow() {
        if (data.length == size) {
            data = Arrays.copyOf(data, calculateNewLength());
        }
    }

    private int calculateNewLength() {
        return size > 0 ? size * 2 : 1;
    }
}