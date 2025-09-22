package ejercicio8.estructuras;

import java.util.NoSuchElementException;

// Implementación de una Lista utilizando una estructura enlazada simple.
public class LinkedList<E> implements List<E> {
    private Node<E> head; // Puntero al primer nodo de la lista.
    private int size; // Número de elementos en la lista.

    public int size() { return size; }
    public boolean isEmpty() { return size == 0; }

    // Añade un elemento al final de la lista.
    public void addLast(E element) {
        System.out.println("[DEBUG][LinkedList] ADDLAST: Añadiendo elemento: " + element);
        if (element == null) {
            throw new IllegalArgumentException("No se puede añadir un elemento nulo");
        }
        
        Node<E> nuevo = new Node<>(element, null);
        // Si la lista está vacía, el nuevo nodo es la cabeza.
        if (head == null) {
            System.out.println("[DEBUG][LinkedList] ADDLAST: La lista estaba vacía. Nuevo elemento es la cabeza.");
            head = nuevo;
        } else {
            // Si no, recorre la lista hasta el final y enlaza el nuevo nodo.
            Node<E> actual = head;
            while (actual.getNext() != null) {
                actual = actual.getNext();
            }
            actual.setNext(nuevo);
            System.out.println("[DEBUG][LinkedList] ADDLAST: Elemento añadido al final de la lista.");
        }
        size++;
    }

    // Elimina la primera ocurrencia de un elemento en la lista.
    public E remove(E element) {
        System.out.println("[DEBUG][LinkedList] REMOVE: Buscando elemento: " + element);
        if (element == null) {
            throw new IllegalArgumentException("No se puede eliminar un elemento nulo");
        }
        if (head == null) {
            System.out.println("[DEBUG][LinkedList] REMOVE falló. La lista está vacía.");
            return null; // La lista está vacía, no hay nada que eliminar.
        }
        
        // Caso especial: el elemento a eliminar es la cabeza de la lista.
        if (head.getElement().equals(element)) {
            System.out.println("[DEBUG][LinkedList] REMOVE: Elemento encontrado en la cabeza. Eliminando.");
            E removedElement = head.getElement();
            head = head.getNext();
            size--;
            return removedElement;
        }
        
        // Busca el nodo anterior al que se va a eliminar.
        Node<E> actual = head;
        while (actual.getNext() != null && !actual.getNext().getElement().equals(element)) {
            actual = actual.getNext();
        }
        
        // Si se encontró el nodo a eliminar (actual.getNext() no es null).
        if (actual.getNext() != null) {
            System.out.println("[DEBUG][LinkedList] REMOVE: Elemento encontrado en el cuerpo de la lista. Eliminando.");
            E removedElement = actual.getNext().getElement();
            actual.setNext(actual.getNext().getNext()); // "Saltamos" el nodo eliminado.
            size--;
            return removedElement;
        }
        
        System.out.println("[DEBUG][LinkedList] REMOVE falló. Elemento no encontrado.");
        return null; // El elemento no fue encontrado en la lista.
    }

    // Devuelve un iterador para poder recorrer la lista de forma sencilla (for-each).
    // Este método es requerido por la interfaz Iterable<E> que List<E> extiende.
    @Override
    public java.util.Iterator<E> iterator() {
        return new java.util.Iterator<E>() {
            private Node<E> current = head;

            public boolean hasNext() {
                return current != null;
            }

            public E next() {
                if (!hasNext()) {
                    throw new java.util.NoSuchElementException("No hay más elementos en la lista");
                }
                E element = current.getElement();
                current = current.getNext();
                return element;
            }
        };
    }
}