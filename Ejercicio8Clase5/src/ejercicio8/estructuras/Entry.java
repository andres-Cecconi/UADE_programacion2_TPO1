package ejercicio8.estructuras;

// Interfaz para una entrada (par clave-valor) de un mapa.
public interface Entry<K, V> {
    K getKey(); // Devuelve la clave de la entrada.
    V getValue(); // Devuelve el valor de la entrada.
    void setValue(V value); // Modifica el valor de la entrada.
}
