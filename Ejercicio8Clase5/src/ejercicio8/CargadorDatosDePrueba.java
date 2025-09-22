package ejercicio8;

import ejercicio8.estructuras.LinkedList;
import ejercicio8.estructuras.List;
import ejercicio8.estructuras.Map;

// Clase dedicada exclusivamente a cargar datos de prueba en el diccionario.
public class CargadorDatosDePrueba {

    /**
     * Método estático que recibe un diccionario y lo puebla con 10 registros de ejemplo.
     * @param diccionario El mapa de DNI -> Lista de notas a poblar.
     */
    public static void cargarEn(Map<Integer, List<Double>> diccionario) {
        System.out.println("[DEBUG] Iniciando carga de datos de prueba...");

        // Limpiamos el diccionario por si ya tenía datos
        if (!diccionario.isEmpty()) {
            java.util.Iterator<Integer> keysIt = diccionario.keys();
            List<Integer> keysToRemove = new LinkedList<>();
            while(keysIt.hasNext()) {
                keysToRemove.addLast(keysIt.next());
            }
            for(Integer key : keysToRemove) {
                diccionario.remove(key);
            }
            System.out.println("[DEBUG] Diccionario existente limpiado antes de la carga.");
        }

        // Registro 1
        List<Double> notas1 = new LinkedList<>();
        notas1.addLast(7.5);
        notas1.addLast(8.0);
        notas1.addLast(9.5);
        diccionario.put(11111111, notas1);

        // Registro 2
        List<Double> notas2 = new LinkedList<>();
        notas2.addLast(4.0);
        notas2.addLast(5.5);
        diccionario.put(22222222, notas2);

        // Registro 3
        List<Double> notas3 = new LinkedList<>();
        notas3.addLast(10.0);
        notas3.addLast(10.0);
        notas3.addLast(10.0);
        diccionario.put(33333333, notas3);

        // Registro 4
        List<Double> notas4 = new LinkedList<>();
        notas4.addLast(6.0);
        diccionario.put(44444444, notas4);

        // Registro 5
        List<Double> notas5 = new LinkedList<>();
        notas5.addLast(8.5);
        notas5.addLast(5.0);
        notas5.addLast(9.5);
        notas5.addLast(7.0);
        diccionario.put(55555555, notas5);

        // Registro 6
        List<Double> notas6 = new LinkedList<>();
        notas6.addLast(2.0);
        notas6.addLast(3.5);
        diccionario.put(66666666, notas6);

        // Registro 7
        List<Double> notas7 = new LinkedList<>();
        notas7.addLast(9.0);
        notas7.addLast(9.5);
        diccionario.put(77777777, notas7);

        // Registro 8
        List<Double> notas8 = new LinkedList<>();
        notas8.addLast(1.0);
        diccionario.put(88888888, notas8);

        // Registro 9
        List<Double> notas9 = new LinkedList<>();
        notas9.addLast(5.5);
        notas9.addLast(6.5);
        notas9.addLast(7.0);
        diccionario.put(99999999, notas9);

        // Registro 10
        List<Double> notas10 = new LinkedList<>();
        notas10.addLast(8.0);
        diccionario.put(10101010, notas10);

        System.out.println("[DEBUG] Carga de 10 registros de prueba completada.");
    }
}