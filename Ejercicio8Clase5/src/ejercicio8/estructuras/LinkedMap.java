package ejercicio8.estructuras;

import java.util.NoSuchElementException;

// Implementación de un Mapa utilizando una lista enlazada simple.
public class LinkedMap<K, V> implements Map<K, V> {
    private Node<Entry<K, V>> head; // Puntero al primer nodo de la lista.
    private int size; // Número de pares clave-valor en el mapa.

    public int size() { 
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    // Método de ayuda para encontrar un nodo por su clave.
    private Node<Entry<K, V>> findNode(K key) {
        System.out.println("[DEBUG][LinkedMap] Buscando nodo con clave: " + key);
        Node<Entry<K, V>> actual = head;
        while (actual != null) {
            if (actual.getElement().getKey().equals(key)) {
                System.out.println("[DEBUG][LinkedMap] Nodo encontrado para la clave: " + key);
                return actual; // Nodo encontrado
            }
            actual = actual.getNext();
        }
        System.out.println("[DEBUG][LinkedMap] Nodo NO encontrado para la clave: " + key);
        return null; // Nodo no encontrado
    }

    // Busca un valor a partir de su clave.
    @Override
    public V get(K key) {
        System.out.println("[DEBUG][LinkedMap] GET para la clave: " + key);
        if (key == null) {
            throw new IllegalArgumentException("La clave no puede ser nula");
        }
        Node<Entry<K, V>> nodo = findNode(key);
        V value = (nodo != null) ? nodo.getElement().getValue() : null;
        System.out.println("[DEBUG][LinkedMap] GET devolvió valor: " + value);
        return value;
    }

    // Inserta o actualiza un par clave-valor.
    @Override
    public V put(K key, V value) {
        System.out.println("[DEBUG][LinkedMap] PUT para clave: " + key + ", valor: " + value);
        if (key == null) {
            throw new IllegalArgumentException("La clave no puede ser nula");
        }
        Node<Entry<K, V>> nodo = findNode(key);
        
        if (nodo != null) { // La clave ya existe, se actualiza el valor.
            System.out.println("[DEBUG][LinkedMap] La clave ya existe. Actualizando valor.");
            V oldValue = nodo.getElement().getValue();
            nodo.getElement().setValue(value);
            return oldValue;
        } else { // La clave no existe, se añade un nuevo nodo al principio.
            System.out.println("[DEBUG][LinkedMap] La clave es nueva. Añadiendo nuevo nodo al principio.");
            head = new Node<>(new Entrada<>(key, value), head);
            size++;
            return null;
        }
    }

    // Elimina un par clave-valor a partir de su clave.
    @Override
    public V remove(K key) {
        System.out.println("[DEBUG][LinkedMap] REMOVE para la clave: " + key);
        if (key == null) {
            throw new IllegalArgumentException("La clave no puede ser nula");
        }
        if (head == null) {
            System.out.println("[DEBUG][LinkedMap] REMOVE falló. El mapa está vacío.");
            return null; // El mapa está vacío.
        }
        
        // Caso especial: el elemento a eliminar es el primero de la lista.
        if (head.getElement().getKey().equals(key)) {
            System.out.println("[DEBUG][LinkedMap] REMOVE: La clave está en la cabeza de la lista. Eliminando.");
            V value = head.getElement().getValue();
            head = head.getNext();
            size--;
            return value;
        }
        
        // Busca el nodo anterior al que se va a eliminar.
        Node<Entry<K, V>> actual = head;
        while (actual.getNext() != null && !actual.getNext().getElement().getKey().equals(key)) {
            actual = actual.getNext();
        }
        
        // Si se encontró el nodo a eliminar.
        if (actual.getNext() != null) {
            System.out.println("[DEBUG][LinkedMap] REMOVE: Clave encontrada en el cuerpo de la lista. Eliminando.");
            V value = actual.getNext().getElement().getValue();
            actual.setNext(actual.getNext().getNext());
            size--;
            return value;
        }
        
        System.out.println("[DEBUG][LinkedMap] REMOVE falló. La clave no fue encontrada.");
        return null; // La clave no fue encontrada.
    }

    @Override
    public java.util.Iterator<K> keys() {
        return new java.util.Iterator<K>() {
            private Node<Entry<K, V>> current = head;
            @Override
            public boolean hasNext() { return current != null; }
            @Override
            public K next() {
                if (!hasNext()) { throw new NoSuchElementException("No hay más claves"); }
                K key = current.getElement().getKey();
                current = current.getNext();
                return key;
            }
        };
    }

    @Override
    public java.util.Iterator<V> values() {
        return new java.util.Iterator<V>() {
            private Node<Entry<K, V>> current = head;
            @Override
            public boolean hasNext() { return current != null; }
            @Override
            public V next() {
                if (!hasNext()) { throw new NoSuchElementException("No hay más valores"); }
                V value = current.getElement().getValue();
                current = current.getNext();
                return value;
            }
        };
    }

    @Override
    public java.util.Iterator<Entry<K, V>> entries() {
        return new java.util.Iterator<Entry<K, V>>() {
            private Node<Entry<K, V>> current = head;
            @Override
            public boolean hasNext() { return current != null; }
            @Override
            public Entry<K, V> next() {
                if (!hasNext()) { throw new NoSuchElementException("No hay más entradas"); }
                Entry<K, V> entry = current.getElement();
                current = current.getNext();
                return entry;
            }
        };
    }
}
