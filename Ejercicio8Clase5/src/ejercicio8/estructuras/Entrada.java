package ejercicio8.estructuras;

public class Entrada<K, V> implements Entry<K, V> {
    private K key;
    private V value;

    public Entrada(K key, V value) {
        if (key == null) {
            throw new IllegalArgumentException("La clave no puede ser nula");
        }
        this.key = key;
        this.value = value; // value puede ser null
    }
    
    public K getKey() { return key; }
    public V getValue() { return value; }
    public void setValue(V value) { this.value = value; }
}