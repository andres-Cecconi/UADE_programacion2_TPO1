package ejercicio8.estructuras;

// Implementación de una entrada (par clave-valor) para un mapa.
public class Entrada<K, V> implements Entry<K, V> {
    private K key; // La clave de la entrada, no puede ser nula.
    private V value; // El valor de la entrada.

    public Entrada(K key, V value) {
        if (key == null) {
            throw new IllegalArgumentException("La clave no puede ser nula");
        }
        this.key = key;
        this.value = value;
    }
    
    @Override
    public K getKey() { 
        return this.key; 
    }
    
    @Override
    public V getValue() { 
        return this.value; 
    }
    
    @Override
    public void setValue(V value) { 
        this.value = value; 
    }
}