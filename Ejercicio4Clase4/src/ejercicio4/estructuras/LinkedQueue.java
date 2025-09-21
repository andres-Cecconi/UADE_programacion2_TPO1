package ejercicio4.estructuras;

public class LinkedQueue<E> implements Queue<E> {
    private Node<E> head, tail;
    private int size;

    public LinkedQueue() {
        head = tail = null;
        size = 0;
    }

    @Override
    public void enqueue(E item) {
        Node<E> nuevo = new Node<>(item, null);
        if (isEmpty()) {
            head = nuevo;
        } else {
            tail.setNext(nuevo);
        }
        tail = nuevo;
        size++;
    }

    @Override
    public E dequeue() {
        if (isEmpty()) {
            throw new MyException("Error: la cola está vacía");
        }
        E elem = head.getElement();
        head = head.getNext();
        size--;
        if (isEmpty()) tail = null;
        return elem;
    }

    @Override
    public E front() {
        if (isEmpty()) {
            throw new MyException("Error: la cola está vacía");
        }
        return head.getElement();
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
