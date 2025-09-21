package ejercicio4.estructuras;

public interface Queue<E> {
    void enqueue(E item);
    E dequeue();
    E front();
    boolean isEmpty();
    int size();
}

