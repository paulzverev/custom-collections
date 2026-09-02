package set;

import java.util.Objects;

public class CustomHashSet<K> {

    private Node<K>[] buckets;

    private static final int DEFAULT_INITIAL_CAPACITY = 16;
    private int capacity;
    private int size;

    @SuppressWarnings("unchecked")
    public CustomHashSet() {
        buckets = new Node[DEFAULT_INITIAL_CAPACITY];
        capacity = DEFAULT_INITIAL_CAPACITY;
    }

    private static class Node<K> {
        private final K key;
        private final int hashCode;
        private Node<K> next;

        public Node(K key, int hashCode) {
            this.key = key;
            this.hashCode = hashCode;
        }
    }

    public void add(K key) {
        int keyHashCode = toOptimizedHashCode(key);
        int bucketIndex = getBucketIndex(keyHashCode);

        Node<K> current = buckets[bucketIndex];

        if (current == null) {
            buckets[bucketIndex] = new Node<>(key, keyHashCode);
            size++;
            return;
        }

        while (true) {
            if (current.hashCode == keyHashCode && Objects.equals(current.key, key)) {
                return;
            }

            if (current.next == null) {
                current.next = new Node<>(key, keyHashCode);
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
