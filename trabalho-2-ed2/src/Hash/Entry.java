package Hash;

public class Entry<K, V> {
    K key;
    V value;
    boolean deleted; //controla ocupação de espaço

    public Entry(K key, V value) {
        this.key = key;
        this.value = value;
        this.deleted = false;
    }
}
