package ejercicio8.estructuras;

// Interfaz para un Diccionario.
public interface Map<K, V> {
    int size(); // Número de pares clave-valor en el diccionario.
    boolean isEmpty(); // True si el diccionario está vacío.
    V get(K key); // Devuelve el valor asociado a la clave, o null si no existe.
    V put(K key, V value); // Asocia la clave con el valor, devolviendo el valor previo o null.
    V remove(K key); // Elimina la clave y devuelve su valor, o null si no existía.

    // Cambiado para devolver un Iterador directamente para mayor simplicidad.
    java.util.Iterator<K> keys();
    java.util.Iterator<V> values();
    java.util.Iterator<Entry<K, V>> entries();
}
