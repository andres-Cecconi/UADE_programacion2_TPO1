package ejercicio7.estructuras;

public interface Stack<E> {
    public void push(E item);    // apila
    public E pop();              // desapila
    public E top();              // mira el tope
    public boolean isEmpty();    // está vacía?
    public int size();           // tamaño
}

