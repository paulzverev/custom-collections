package map;

import java.util.Objects;

public class CustomHashMap<K, V> {

    private Node<K, V>[] buckets;

    private static final int DEFAULT_INITIAL_CAPACITY = 16;
    private int capacity;
    private int size;

    @SuppressWarnings("unchecked")
    public CustomHashMap() {
        buckets = new Node[DEFAULT_INITIAL_CAPACITY];
        capacity = DEFAULT_INITIAL_CAPACITY;
    }

    private static class Node<K, V> {

        private final K key;
        private V value;
        private int hashCode;
        private Node<K, V> next;

        private Node(K key, V value, int hashCode) {
            this.key = key;
            this.value = value;
            this.hashCode = hashCode;
        }
    }

    public void put(K key, V value) {
        int keyHashCode = toOptimizedHashCode(key);
        int bucketIndex = getBucketIndex(keyHashCode);

        Node<K, V> current = buckets[bucketIndex];

        if (current == null) {
            buckets[bucketIndex] = new Node<>(key, value, keyHashCode);
            size++;
            return;
        }

        while (true) {
            if (current.hashCode == keyHashCode && Objects.equals(current.key, key)) {
                current.value = value;
                return;
            }

            if (current.next == null) {
                current.next = new Node<>(key, value, keyHashCode);
                size++;
                return;
            }

            current = current.next;
        }
    }

    private static int toOptimizedHashCode(Object o) {
        if (o == null) {
            return 0;
        }

        int objectHashCode = o.hashCode();
        return objectHashCode ^ (objectHashCode >>> 16);
    }

    private int getBucketIndex(int spreadHashCode) {
        return (capacity - 1) & spreadHashCode;
    }
}
