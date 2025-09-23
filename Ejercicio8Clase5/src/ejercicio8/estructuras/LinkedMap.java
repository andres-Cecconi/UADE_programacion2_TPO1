package ejercicio8.estructuras;

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

        // Iniciar desde el primer nodo de la lista
        Node<Entry<K, V>> nodoActual = head;

        // Recorrer todos los nodos hasta encontrar la clave
        while (nodoActual != null) {
            // Obtener la clave del nodo actual
            K claveActual = nodoActual.getElement().getKey();

            // Comparar con la clave buscada
            if (claveActual.equals(key)) {
                System.out.println("[DEBUG][LinkedMap] Nodo encontrado para la clave: " + key);
                return nodoActual; // Nodo encontrado
            }

            // Avanzar al siguiente nodo
            nodoActual = nodoActual.getNext();
        }

        // Si se recorrió toda la lista sin encontrar la clave
        System.out.println("[DEBUG][LinkedMap] Nodo NO encontrado para la clave: " + key);
        return null; // Nodo no encontrado
    }

    // Busca un valor a partir de su clave.
    @Override
    public V get(K key) {
        System.out.println("[DEBUG][LinkedMap] GET para la clave: " + key);

        // Validar que la clave no sea nula
        if (key == null) {
            throw new IllegalArgumentException("La clave no puede ser nula");
        }

        // Buscar el nodo que contiene la entrada para esta clave
        Node<Entry<K, V>> nodo = findNode(key);

        // Determinar el valor a retornar
        V value;
        if (nodo != null) {
            // Si se encontró el nodo, obtener el valor de la entrada
            value = nodo.getElement().getValue();
        } else {
            // Si no se encontró el nodo, retornar null
            value = null;
        }

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
    public List<K> keys() {
        // Crear una nueva lista para almacenar las claves
        List<K> listaClaves = new LinkedList<>();

        // Comenzar desde el primer nodo de la lista
        Node<Entry<K, V>> nodoActual = head;

        // Recorrer todos los nodos de la lista
        while (nodoActual != null) {
            // Obtener la entrada (key-value) del nodo actual
            Entry<K, V> entradaActual = nodoActual.getElement();

            // Extraer la clave de la entrada actual
            K claveActual = entradaActual.getKey();

            // Agregar la clave a la lista
            listaClaves.addLast(claveActual);

            // Avanzar al siguiente nodo
            nodoActual = nodoActual.getNext();
        }

        // Retornar la lista completa de claves
        return listaClaves;
    }

    @Override
    public List<V> values() {
        // Crear una nueva lista para almacenar los valores
        List<V> listaValores = new LinkedList<>();

        // Comenzar desde el primer nodo de la lista
        Node<Entry<K, V>> nodoActual = head;

        // Recorrer todos los nodos de la lista
        while (nodoActual != null) {
            // Obtener la entrada (key-value) del nodo actual
            Entry<K, V> entradaActual = nodoActual.getElement();

            // Extraer el valor de la entrada actual
            V valorActual = entradaActual.getValue();

            // Agregar el valor a la lista
            listaValores.addLast(valorActual);

            // Avanzar al siguiente nodo
            nodoActual = nodoActual.getNext();
        }

        // Retornar la lista completa de valores
        return listaValores;
    }

    @Override
    public List<Entry<K, V>> entries() {
        // Crear una nueva lista para almacenar las entradas
        List<Entry<K, V>> listaEntradas = new LinkedList<>();

        // Comenzar desde el primer nodo de la lista
        Node<Entry<K, V>> nodoActual = head;

        // Recorrer todos los nodos de la lista
        while (nodoActual != null) {
            // Obtener la entrada completa (key-value) del nodo actual
            Entry<K, V> entradaActual = nodoActual.getElement();

            // Agregar la entrada completa a la lista
            listaEntradas.addLast(entradaActual);

            // Avanzar al siguiente nodo
            nodoActual = nodoActual.getNext();
        }

        // Retornar la lista completa de entradas
        return listaEntradas;
    }

    @Override
    public void clear() {
        head = null;
        size = 0;
        System.out.println("[DEBUG][LinkedMap] El mapa ha sido limpiado.");
    }
}
