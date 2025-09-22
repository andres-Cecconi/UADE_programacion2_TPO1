package ejercicio8.estructuras;

public class Node<E> {
    private E element;
    private Node<E> next;

    public Node(E element, Node<E> next) {
        if (element == null) {
            throw new IllegalArgumentException("El elemento no puede ser nulo");
        }
        this.element = element;
        this.next = next;
    }

    public E getElement() { return element; }
    public Node<E> getNext() { return next; }
    public void setNext(Node<E> next) { this.next = next; }
}