package ejercicio4.estructuras;

public interface Stack<E> {
    void push(E item);
    E pop();
    E top();
    boolean isEmpty();
    int size();
}

