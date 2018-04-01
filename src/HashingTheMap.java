import java.util.LinkedList;

class KeyValue<K, V> {

    public K key;
    public V value;
}


public class HashingTheMap<K, V> {

    private int bucketSize = 16;

    private LinkedList<KeyValue<K, V>>[] elements = new LinkedList[bucketSize];

    public void add(K key, V value) {
        int position = getHash(key);
        if (elements[position] != null) {
            throw new IllegalArgumentException();
        }
        LinkedList<KeyValue<K, V>> list = new LinkedList<>();
        KeyValue keyValue = new KeyValue();
        keyValue.key = key;
        keyValue.value = value;
        list.add(keyValue);
        elements[position] =list;
    }

    public void remove (K key) {
        int position = getHash(key);
        if (elements[position] == null) {
            throw new IllegalArgumentException();
        }
        elements[position] = null;
    }

    public V getValue(K key) {
        int position = getHash(key);
        return elements[position].get(0).value;
    }

    private int getHash(K key) {
        return key.hashCode() % bucketSize;
    }

    public void clearAll() {
        elements = new LinkedList[bucketSize];
    }
}
