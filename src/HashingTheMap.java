import java.util.LinkedList;

class KeyValue<K, V> {

    public K key;
    public V value;
}


public class HashingTheMap<K, V> {

    private int bucketSize = 16;
    private int itemCounter = 0;

    private LinkedList<KeyValue<K, V>>[] elements = new LinkedList[bucketSize];

    public void add(K key, V value) {
        int position = getPosByHash(key);
        KeyValue newKeyValue = new KeyValue();
        newKeyValue.key = key;
        newKeyValue.value = value;
        if (elements[position] != null) {
            for (KeyValue keyValue : elements[position]) {
                if (keyValue.key.equals(key)) {
                    throw new DuplicateKeyException();
                } else {
                    elements[position].add(newKeyValue);
                    itemCounter++;
                    resizeIfNeeded();
                    return;
                }
            }
        } else {
            LinkedList<KeyValue<K, V>> list = new LinkedList<>();
            list.add(newKeyValue);
            elements[position] = list;
            itemCounter++;
            resizeIfNeeded();
        }
    }

    public void remove (K key) {
        int position = getPosByHash(key);
        if (elements[position] == null) {
            throw new KeyNotFoundException();
        }
        for (KeyValue keyValue : elements[position]) {
            if (keyValue.key.equals(key)) {
                elements[position].remove(keyValue);
                itemCounter--;
                resizeIfNeeded();
                return;
            }
        }
        throw new KeyNotFoundException();
    }

    public Object getValue(K key) {
        int position = getPosByHash(key);
        for (KeyValue keyValue: elements[position]) {
            if (keyValue.key.equals(key)) {
                return keyValue.value;
            }
        }
        throw new KeyNotFoundException();
    }

    private int getPosByHash(K key) {
        return key.hashCode() % bucketSize;
    }

    public void clearAll() {
        elements = new LinkedList[bucketSize];
    }

    private void resizeIfNeeded() {
        if (bucketSize*2 < itemCounter) {
            bucketSize = bucketSize * 2;
            resizeIt();
        } else if (bucketSize/2 > itemCounter) {
            bucketSize = bucketSize/2;
            resizeIt();
        }
    }

    private void resizeIt() {
        LinkedList<KeyValue<K, V>>[] newElements = new LinkedList[bucketSize];
        for (LinkedList<KeyValue<K,V>> linkedList: elements) {
            if (linkedList != null) {
                for (KeyValue keyValue: linkedList) {
                    if (newElements[keyValue.key.hashCode() % bucketSize] == null){
                        LinkedList<KeyValue<K,V>> list = new LinkedList<>();
                        newElements[keyValue.key.hashCode() % bucketSize] = list;
                    }
                    newElements[keyValue.key.hashCode() % bucketSize].add(keyValue);
                }
            }
        }
        elements = newElements;
    }
}
