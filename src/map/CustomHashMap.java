package map;

import java.util.Objects;

public class CustomHashMap<K, V> {

    private Node<K, V>[] buckets;

    private static final int DEFAULT_CAPACITY = 16;
    private int capacity;
    private int size;

    @SuppressWarnings("unchecked")
    public CustomHashMap() {
        buckets = new Node[DEFAULT_CAPACITY];
        capacity = DEFAULT_CAPACITY;
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
        int incomingKeyHashCode = toOptimizedHashCode(key);
        int bucketIndex = getBucketIndex(incomingKeyHashCode);
        Node<K, V> existing = buckets[bucketIndex];

        if (existing == null) {
            buckets[bucketIndex] = new Node<>(key, value, incomingKeyHashCode);
            size++;
        } else {
            if (existing.hashCode == incomingKeyHashCode) {
                if (Objects.equals(existing.key, key)) {
                    existing.value = value;
                }
            } else {
                Node<K, V> current = buckets[bucketIndex];

                while (current.next != null) {
                    current = current.next;
                }

                current.next = new Node<>(key, value, incomingKeyHashCode);
                size++;
            }
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
