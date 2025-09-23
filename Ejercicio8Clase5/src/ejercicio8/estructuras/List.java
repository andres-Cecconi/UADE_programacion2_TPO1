package ejercicio8.estructuras;

// Interfaz para una Lista.
public interface List<E> {
    int size(); // número de elementos en la lista
    boolean isEmpty(); // ¿la lista está vacía?
    E get(int index); // devuelve el elemento en la posición especificada
    void addLast(E element); // añade un elemento al final de la lista
    E remove(E element); // elimina un elemento de la lista
}