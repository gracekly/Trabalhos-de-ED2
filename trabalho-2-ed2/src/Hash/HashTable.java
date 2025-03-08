package Hash;

import java.util.ArrayList;
import java.util.List;

public class HashTable<K, V> {

    private int capacity = 16; // capacidade inicial do hash
    private static final double RESIZE_FACTOR = 0.7;
    private Entry<K, V>[] table;
    private int size; //tamanho tabela
    private ProbingType probing_type;

    private static final int C1 = 1;
    private static final int C2 = 1;

    public HashTable(ProbingType probing_type) {
        this.probing_type = probing_type;
        table = new Entry[capacity];
        size = 0;
    }


    //retorna um cod hash
    private int hash(K key) {
        return Math.abs(key.hashCode()) % table.length;
    }


    public void put(K key, V value) {
        if (size >= table.length * RESIZE_FACTOR) {
            resize();
        }

        int index = hash(key);
        int i = 0;
        while (table[index] != null && !table[index].deleted) {
            if (table[index].key.equals(key)) {
                table[index].value = value;
                return;
            }
            i++;
            index = nextIndex(index, i);
        }

        table[index] = new Entry<>(key, value);
        size++;
    }


    public V get(K key) {
        int index = hash(key);
        int i = 0;
        while (table[index] != null) {
            if (!table[index].deleted && table[index].key.equals(key)) {
                return table[index].value;
            }
            i++;
            index = nextIndex(index, i);
        }
        return null;
    }

    //busca contando os passos
    public V getPassos(K key) {
        int index = hash(key);
        int i = 0;
        int passos = 0;
        while (table[index] != null) {
            passos++;
            System.out.println("Número de passos necessários em busca da chave '" + key + "': " + passos);
            if (!table[index].deleted && table[index].key.equals(key)) {
                return table[index].value;
            }
            i++;
            index = nextIndex(index, i);

        }
        System.out.println("Número de passos total em busca da chave '" + key + "': " + passos);
        return null;
    }


    public void remove(K key) {
        int index = hash(key);
        int i = 0;
        while (table[index] != null) {
            if (!table[index].deleted && table[index].key.equals(key)) {
                table[index].deleted = true;
                size--;
                return;
            }
            i++;
            index = nextIndex(index, i);
        }
    }

    // calcula o proximo indice por linear ou quadratica
    private int nextIndex(int currentIndex, int i) {
        if (probing_type == ProbingType.LINEAR) {
            return (currentIndex + i) % table.length;
        } else {
            return (currentIndex + C1 * i + C2 * i * i) % table.length;
        }
    }

    //dobra o tamanho da tabela
    private void resize() {
        Entry<K, V>[] oldTable = table;
        table = new Entry[oldTable.length * 2];
        size = 0;

        for (Entry<K, V> entry : oldTable) {
            if (entry != null && !entry.deleted) {
                put(entry.key, entry.value);
            }
        }
    }

    //retorna todas as chaves da tabela
    public List<K> keys() {
        List<K> keys = new ArrayList<>();
        for (Entry<K, V> entry : table) {
            if (entry != null && !entry.deleted) {
                keys.add(entry.key);
            }
        }
        return keys;
    }



}

