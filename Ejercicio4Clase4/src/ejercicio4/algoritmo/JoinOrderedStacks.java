package ejercicio4.algoritmo;

import ejercicio4.estructuras.*;

public class JoinOrderedStacks {

    public static Stack<Queue<Integer>> unir(
            Stack<Queue<Integer>> P1,
            Stack<Queue<Integer>> P2
    ) {
        Stack<Queue<Integer>> Pout = new LinkedStack<>();

        // Paso 1: unir
        while (!P1.isEmpty() && !P2.isEmpty()) {
            Queue<Integer> q1 = P1.top();
            Queue<Integer> q2 = P2.top();

            if (q1.size() >= q2.size()) {
                Pout.push(P1.pop());
            } else {
                Pout.push(P2.pop());
            }
        }

        // Paso 2: volcar lo que queda
        while (!P1.isEmpty()) {
            Pout.push(P1.pop());
        }
        while (!P2.isEmpty()) {
            Pout.push(P2.pop());
        }

        // Paso 3: invertir usando una pila auxiliar
        Stack<Queue<Integer>> Paux = new LinkedStack<>();
        while (!Pout.isEmpty()) {
            Paux.push(Pout.pop());
        }

        // Paso 4: reasignar y devolver Pout ordenada
        Pout = Paux;
        return Pout;
    }
}
