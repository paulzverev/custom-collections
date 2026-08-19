public interface CustomList<E> extends DefaultCustomList<E> {

    int size();

    boolean isEmpty();

    boolean contains(Object o);

    void add(E element);

    void add(int index, E element);

    E get(int index);

    E set(int index, E element);

    E remove(int index);

    boolean remove(Object o);

    void clear();
}
