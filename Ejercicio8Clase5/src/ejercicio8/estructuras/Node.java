package ejercicio8.estructuras;

// Nodo para una estructura enlazada simple.
public class Node<E> {
    private E element; // El elemento almacenado en el nodo.
    private Node<E> next; // Referencia al siguiente nodo en la secuencia.

    public Node(E element, Node<E> next) {
        if (element == null) {
            throw new IllegalArgumentException("El elemento no puede ser nulo");
        }
        this.element = element;
        this.next = next;
    }

    public E getElement() { 
        return this.element; 
    }

    public Node<E> getNext() { 
        return this.next; 
    }
    
    public void setNext(Node<E> next) { 
        this.next = next; 
    }
}
