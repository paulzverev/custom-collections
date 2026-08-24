package list;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class CustomLinkedList<E> implements CustomList<E> {

    private Node<E> head;
    private int size;

    private static class Node<E> {
        private E value;
        private Node<E> next;

        private Node(E value) {
            this.value = value;
        }
    }

    private class CustomLinkedListIterator implements Iterator<E> {
        private Node<E> current = head;

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public E next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }

            E value = current.value;
            current = current.next;

            return value;
        }
    }

    @Override
    public Iterator<E> iterator() {
        return new CustomLinkedListIterator();
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
        Node<E> current = head;

        while (current != null) {
            if (Objects.equals(current.value, o)) {
                return true;
            }

            current = current.next;
        }

        return false;
    }

    @Override
    public void add(E value) {
        Node<E> newNode = new Node<>(value);

        if (head == null) {
            head = newNode;
            size++;
            return;
        }

        Node<E> current = head;

        while (current.next != null) {
            current = current.next;
        }

        current.next = newNode;
        size++;

    }

    @Override
    public void add(int index, E element) {

    }

    @Override
    public E get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("out of bounds");
        }

        Node<E> current = head;

        for (int i = 0; i < index; i++) {
            current = current.next;
        }

        return current.value;
    }

    @Override
    public E set(int index, E element) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("out of bounds");
        }

        Node<E> current = head;

        for (int i = 0; i < index; i++) {
            current = current.next;
        }

        E old = current.value;
        current.value = element;

        return old;
    }

    @Override
    public E remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("index out of bounds");
        }

        Node<E> current = head;

        for (int i = 0; i < index; i++) {
            current = current.next;
        }

        E removed = current.value;
        current.value = null;
        size--;

        return removed;
    }

    @Override
    public boolean remove(Object o) {
        return false;
    }

    @Override
    public void clear() {
        head = null;
        size = 0;
    }
}