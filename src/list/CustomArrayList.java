package list;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class CustomArrayList<E> implements CustomList<E> {

    private Object[] arr;
    private int size;
    private static final int DEFAULT_CAPACITY = 10;
    private static final double DEFAULT_CAPACITY_INCREMENT = 1.5;

    private class CustomArrayListIterator implements Iterator<E> {

        private int cursor = 0;

        @Override
        public boolean hasNext() {
            return cursor < size;
        }

        @Override
        @SuppressWarnings("unchecked")
        public E next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }

            return (E) arr[cursor++];
        }

    }

    public CustomArrayList() {
        arr = new Object[DEFAULT_CAPACITY];
    }

    @Override
    public Iterator<E> iterator() {
        return new CustomArrayListIterator();
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean contains(Object o) {
        for (int i = 0; i < size; i++) {
            if (Objects.equals(arr[i], o)) {
                return true;
            }
        }

        return false;
    }

    @Override
    public void add(E o) {
        if (size == arr.length) {
            arr = Arrays.copyOf(arr, (int) (arr.length * DEFAULT_CAPACITY_INCREMENT));
        }

        arr[size] = o;
        size++;
    }

    @Override
    public void add(int index, E element) {

    }

    @Override
    @SuppressWarnings("unchecked")
    public E get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }

        return (E) arr[index];
    }

    @Override
    public E set(int index, E element) {
        E old = get(index);
        arr[index] = element;
        return old;
    }

    @Override
    public E remove(int index) {
        E old = get(index);

        for (int i = index; i < size - 1; i++) {
            arr[i] = arr[i + 1];
        }

        arr[size - 1] = null;
        size--;

        return old;
    }

    @Override
    public boolean remove(Object o) {
        int removingIndex = indexOf(o);

        if (removingIndex == -1) {
            throw new IllegalStateException("element not found");
        }

        for (int i = removingIndex; i < size - 1; i++) {
            arr[i] = arr[i + 1];
        }

        arr[size - 1] = null;
        size--;

        return true;
    }

    @Override
    public void clear() {
        arr = new Object[DEFAULT_CAPACITY];
        size = 0;
    }

    public void trimToSize() {
        int newCapacity = arr.length == 0 ? DEFAULT_CAPACITY : (int) (arr.length * DEFAULT_CAPACITY_INCREMENT);
        arr = Arrays.copyOf(arr, newCapacity);
    }

    public int indexOf(Object o) {
        for (int i = 0; i < size; i++) {
            if (Objects.equals(arr[i], o)) {
                return i;
            }
        }

        return -1;
    }
}