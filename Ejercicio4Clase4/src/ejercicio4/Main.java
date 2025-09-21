package ejercicio4;

import ejercicio4.estructuras.*;
import ejercicio4.algoritmo.JoinOrderedStacks;

public class Main {
    public static void main(String[] args) {
        Stack<Queue<Integer>> P1 = new LinkedStack<>();
        Stack<Queue<Integer>> P2 = new LinkedStack<>();

        Queue<Integer> q1 = new LinkedQueue<>();
        q1.enqueue(1); q1.enqueue(2);
        Queue<Integer> q2 = new LinkedQueue<>();
        q2.enqueue(3); q2.enqueue(4); q2.enqueue(5);
        Queue<Integer> q3 = new LinkedQueue<>();
        q3.enqueue(1); q3.enqueue(1); q3.enqueue(2); q3.enqueue(3);

        P1.push(q1);
        P1.push(q2);
        P1.push(q3);

        Queue<Integer> q4 = new LinkedQueue<>();
        q4.enqueue(9);
        Queue<Integer> q5 = new LinkedQueue<>();
        q5.enqueue(7); q5.enqueue(8);

        P2.push(q4);
        P2.push(q5);

        Stack<Queue<Integer>> Pout = JoinOrderedStacks.unir(P1, P2);

        System.out.println("Pout size = " + Pout.size());
        while (!Pout.isEmpty()) {
            Queue<Integer> q = Pout.pop();
            System.out.println("Cola con tamaño: " + q.size());
        }
    }
}
