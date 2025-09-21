package ejercicio7.estructuras;

public class LinkedStack<E> implements Stack<E> {
    private Node<E> top;   // referencia al tope de la pila
    private int size;

    public LinkedStack() {
        top = null;
        size = 0;
    }

    @Override
    public void push(E item) {
        Node<E> nuevo = new Node<>(item, top);
        top = nuevo;
        size++;
    }

    @Override
    public E pop() {
        if (isEmpty()) {
            throw new RuntimeException("Pila vacía");
        }
        E elem = top.getElement();
        top = top.getNext();
        size--;
        return elem;
    }

    @Override
    public E top() {
        if (isEmpty()) {
            throw new RuntimeException("Pila vacía");
        }
        return top.getElement();
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }
}
