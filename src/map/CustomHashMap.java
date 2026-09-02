package map;

import java.util.Objects;

public class CustomHashMap<K, V> {

    private Entry<K, V>[] buckets;

    private static final int DEFAULT_INITIAL_CAPACITY = 16;
    private int capacity;
    private int size;

    @SuppressWarnings("unchecked")
    public CustomHashMap() {
        buckets = new Entry[DEFAULT_INITIAL_CAPACITY];
        capacity = DEFAULT_INITIAL_CAPACITY;
    }

    private static class Entry<K, V> {
        private final K key;
        private V value;
        private final int hashCode;
        private Entry<K, V> next;

        private Entry(K key, V value, int hashCode) {
            this.key = key;
            this.value = value;
            this.hashCode = hashCode;
        }
    }

    public void put(K key, V value) {
        int keyHashCode = toOptimizedHashCode(key);
        int bucketIndex = getBucketIndex(keyHashCode);

        Entry<K, V> current = buckets[bucketIndex];

        if (current == null) {
            buckets[bucketIndex] = new Entry<>(key, value, keyHashCode);
            size++;
            return;
        }

        while (true) {
            if (current.hashCode == keyHashCode && Objects.equals(current.key, key)) {
                current.value = value;
                return;
            }

            if (current.next == null) {
                current.next = new Entry<>(key, value, keyHashCode);
                size++;
                return;
            }

            current = current.next;
        }
    }

    private boolean contains(K key) {
        int keyHashCode = toOptimizedHashCode(key);
        int bucketIndex = getBucketIndex(keyHashCode);

        Entry<K, V> current = buckets[bucketIndex];

        while (current != null) {
            if (current.hashCode == keyHashCode && Objects.equals(current.key, key)) {
                return true;
            }

            current = current.next;
        }

        return false;
    }

    private V get(K key) {
        int keyHashCode = toOptimizedHashCode(key);
        int bucketIndex = getBucketIndex(keyHashCode);

        Entry<K, V> current = buckets[bucketIndex];

        while (current != null) {
            if (current.hashCode == keyHashCode && Objects.equals(current.key, key)) {
                return current.value;
            }

            current = current.next;
        }

        return null;
    }

    private V remove(K key) {
        int keyHashCode = toOptimizedHashCode(key);
        int bucketIndex = getBucketIndex(keyHashCode);

        Entry<K, V> prev = null;
        Entry<K, V> current = buckets[bucketIndex];

        while (current != null) {
            if (current.hashCode == keyHashCode && Objects.equals(current.key, key)) {
                if (prev != null) {
                    prev.next = current.next;
                } else {
                    buckets[bucketIndex] = current.next;
                }

                size--;
                return current.value;
            }

            prev = current;
            current = current.next;
        }

        return null;
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
