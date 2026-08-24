package map;

public class CustomHashMap<K, V> {

    private Node<K, V>[] buckets;
    private int capacity;
    private int size;
    private static final int DEFAULT_CAPACITY = 16;

    @SuppressWarnings("unchecked")
    public CustomHashMap() {
        buckets = new Node[DEFAULT_CAPACITY];
        capacity = DEFAULT_CAPACITY;
    }

    private static class Node<K, V> {

        private K key;
        private V value;
        private int hashCode;

        private Node(K key, V value, int hashCode) {
            this.key = key;
            this.value = value;
            this.hashCode = hashCode;
        }
    }

    public void put(K key, V value) {
        int optimizedHashCode = toOptimizedHashCode(key);
        int bucketIndex = getBucketIndex(optimizedHashCode);

        buckets[bucketIndex] = new Node<>(key, value, optimizedHashCode);
        size++;
    }

    private static int toOptimizedHashCode(Object o) {
        int objectHashCode = o.hashCode();
        return objectHashCode ^ (objectHashCode >>> 16);
    }

    private int getBucketIndex(int spreadHashCode) {
        return (capacity - 1) & spreadHashCode;
    }
}
