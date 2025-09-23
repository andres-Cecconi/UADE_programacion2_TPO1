package ejercicio8;

import ejercicio8.estructuras.Entry;
import ejercicio8.estructuras.List;

public class VistaConsola {

    public void mostrarMenu() {
        System.out.println("\n--- MENÚ DICCIONARIO DE NOTAS ---");
        System.out.println("1. Cargar mapeo de notas");
        System.out.println("2. Mostrar último mapeo");
        System.out.println("3. Consolidar en diccionario general");
        System.out.println("4. Agregar nota a DNI");
        System.out.println("5. Quitar nota de DNI");
        System.out.println("6. Quitar alumno");
        System.out.println("7. Mostrar notas de alumno");
        System.out.println("8. Mostrar todos los alumnos");
        System.out.println("9. Mostrar alumnos con promedio");

        System.out.println("0. Salir");
    }

    public void mostrarMapeo(List<Entry<Integer, Double>> entries) {
        System.out.println("Último mapeo:");
        if (entries == null || entries.isEmpty()) {
            System.out.println("No hay mapeo cargado.");
            return;
        }
        for (int i = 0; i < entries.size(); i++) {
            Entry<Integer, Double> entrada = entries.get(i);
            System.out.println("DNI: " + entrada.getKey().intValue() + " -> Nota: " + entrada.getValue().doubleValue());
        }
    }

    public void mostrarNotas(int dni, List<Double> notas) {
        if (notas == null || notas.isEmpty()) {
            System.out.println("Sin notas para DNI " + dni);
            return;
        }
        System.out.print("Notas DNI " + dni + ": [");
        for (int i = 0; i < notas.size(); i++) {
            System.out.print(notas.get(i).doubleValue());
            if (i < notas.size() - 1) {
                System.out.print(", ");
            }
        }
        System.out.println("]");
    }

    public void mostrarAlumnos(List<Integer> keys) {
        System.out.println("Alumnos:");
        if (keys.isEmpty()) {
            System.out.println("Diccionario vacío.");
            return;
        }
        for (int i = 0; i < keys.size(); i++) {
            System.out.println("- " + keys.get(i).intValue());
        }
    }

    public void mostrarPromedios(List<Entry<Integer, Double>> promedios) {
        System.out.println("Alumnos y promedios:");
        if (promedios.isEmpty()) {
            System.out.println("Diccionario vacío.");
            return;
        }
        for (int i = 0; i < promedios.size(); i++) {
            Entry<Integer, Double> entrada = promedios.get(i);
            int dni = entrada.getKey().intValue();
            double promedio = entrada.getValue().doubleValue();
            System.out.println("DNI: " + dni + ", Promedio: " + promedio);
        }
    }
    
    public void mostrarMensaje(String mensaje) {
        System.out.println(mensaje);
    }
}
