package ejercicio8.estructuras;

public interface List<E> extends Iterable<E> {
    int size(); // número de elementos en la lista
    boolean isEmpty(); // ¿la lista está vacía?
    void addLast(E element); // añade un elemento al final de la lista
    E remove(E element); // elimina un elemento de la lista
}