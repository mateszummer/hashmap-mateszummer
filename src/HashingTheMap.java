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
        KeyValue newKeyValue = new KeyValue();
        newKeyValue.key = key;
        newKeyValue.value = value;
        if (elements[position] != null) {
            for (KeyValue keyValue : elements[position]) {
                if (keyValue.key.equals(key)) {
                    throw new IllegalArgumentException();
                } else {
                    elements[position].add(newKeyValue);
                    return;
                }
            }
        } else {
            LinkedList<KeyValue<K, V>> list = new LinkedList<>();
            list.add(newKeyValue);
            elements[position] = list;
        }
    }

    public void remove (K key) {
        int position = getHash(key);
        if (elements[position] == null) {
            throw new IllegalArgumentException();
        }
        for (KeyValue keyValue : elements[position]) {
            if (keyValue.key.equals(key)) {
                elements[position].remove(keyValue);
                return;
            }
        }
        throw new IllegalArgumentException();
    }

    public Object getValue(K key) {
        int position = getHash(key);
        for (KeyValue keyValue: elements[position]) {
            if (keyValue.key.equals(key)) {
                return keyValue.value;
            }
        }
        throw new IllegalArgumentException();
    }

    private int getHash(K key) {
        return key.hashCode() % bucketSize;
    }

    public void clearAll() {
        elements = new LinkedList[bucketSize];
    }
}
