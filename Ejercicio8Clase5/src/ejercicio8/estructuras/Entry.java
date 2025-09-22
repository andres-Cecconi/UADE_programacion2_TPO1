package ejercicio8.estructuras;

public interface Entry<K, V> {
    K getKey();
    V getValue();
    void setValue(V value);
}