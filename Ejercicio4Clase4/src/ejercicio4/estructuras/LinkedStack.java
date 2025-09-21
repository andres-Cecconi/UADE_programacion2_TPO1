package ejercicio4.estructuras;

public class LinkedStack<E> implements Stack<E> {
    private Node<E> top;
    private int size;

    public LinkedStack() {
        top = null;
        size = 0;
    }

    @Override
    public void push(E item) {
        top = new Node<>(item, top);
        size++;
    }

    @Override
    public E pop() {
        if (isEmpty()) {
            throw new MyException("Error: la pila está vacía");
        }
        E elem = top.getElement();
        top = top.getNext();
        size--;
        return elem;
    }

    @Override
    public E top() {
        if (isEmpty()) {
            throw new MyException("Error: la pila está vacía");
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
