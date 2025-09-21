package ejercicio7;
import ejercicio7.estructuras.*;
import java.util.Scanner;

public class Main {
    public static boolean validarBalanceo(String cadena) {
        Stack<Character> pila = new LinkedStack<>();

        for (int i = 0; i < cadena.length(); i++) {
            char c = cadena.charAt(i);

            if (c == '(') {
                pila.push(c);
            } else if (c == ')') {
                if (pila.isEmpty()) {
                    return false; // hay un ")" sin "(" previo
                }
                pila.pop();
            }
        }

        return pila.isEmpty(); // true si no quedaron "(" sin cerrar
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Ingrese una cadena:");
        String entrada = sc.nextLine();

        boolean balanceado = validarBalanceo(entrada);
        System.out.println("¿Está balanceado? " + balanceado);
    }
}
