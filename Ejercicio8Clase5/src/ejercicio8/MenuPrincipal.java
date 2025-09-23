package ejercicio8;

import ejercicio8.estructuras.Entry;
import ejercicio8.estructuras.List;
import java.util.Scanner;

public class MenuPrincipal {

    private final GestorDeNotas gestor = new GestorDeNotas();
    private final VistaConsola vista = new VistaConsola();
    private final Scanner scanner = new Scanner(System.in);

    public void iniciar() {
        int opcion = 0;
        do {
            vista.mostrarMenu();
            opcion = leerEntero("Opción: ");
            procesarOpcion(opcion);
        } while (opcion != 0);
        vista.mostrarMensaje("Fin del programa.");
        scanner.close();
    }

    private void procesarOpcion(int opcion) {
        switch (opcion) {
            case 1:
                cargarMapeo();
                break;
            case 2:
                mostrarUltimoMapeo();
                break;
            case 3:
                consolidarMapeo();
                break;
            case 4:
                agregarNota();
                break;
            case 5:
                quitarNota();
                break;
            case 6:
                quitarAlumno();
                break;
            case 7:
                mostrarNotas();
                break;
            case 8:
                mostrarAlumnos();
                break;
            case 9:
                mostrarPromedios();
                break;

            case 0:
                break;
            default:
                vista.mostrarMensaje("Opción inválida.");
        }
    }

    private void cargarMapeo() {
        gestor.iniciarCargaMapeo();
        vista.mostrarMensaje("Ingrese DNI y nota. (Para finalizar la carga, ingrese DNI -1)");

        int dni;
        while ((dni = leerDNIValido("DNI: ")) != -1) {
            double nota = leerNotaValidaDouble();
            gestor.agregarNotaEnMapeoTemporal(dni, nota);
        }

        vista.mostrarMensaje("Mapeo cargado: " + gestor.getUltimoMapeoSize() + " entradas.");
    }

    private void mostrarUltimoMapeo() {
        vista.mostrarMapeo(gestor.getUltimoMapeoEntries());
    }

    private void consolidarMapeo() {
        if (gestor.consolidarMapeo()) {
            vista.mostrarMensaje("Mapeo consolidado.");
        } else {
            vista.mostrarMensaje("No hay mapeo para consolidar.");
        }
    }

    private void agregarNota() {
        int dni = leerDNIValido("DNI: ");
        double nota = leerNotaValidaDouble();
        if (gestor.agregarNota(dni, nota)) {
            vista.mostrarMensaje("Nota agregada.");
        } else {
            vista.mostrarMensaje("Alumno no existe.");
        }
    }

    private void quitarNota() {
        int dni = leerDNIValido("DNI: ");
        double nota = leerDouble("Nota a quitar: ");
        int resultado = gestor.quitarNota(dni, nota);
        switch (resultado) {
            case 0:
                vista.mostrarMensaje("Nota eliminada.");
                break;
            case 1:
                vista.mostrarMensaje("Nota eliminada. El alumno se quedó sin notas y fue eliminado.");
                break;
            case -1:
                vista.mostrarMensaje("Alumno no existe o sin notas.");
                break;
            case -2:
                vista.mostrarMensaje("Nota no encontrada.");
                break;
        }
    }

    private void quitarAlumno() {
        int dni = leerDNIValido("DNI: ");
        if (gestor.quitarAlumno(dni)) {
            vista.mostrarMensaje("Alumno eliminado.");
        } else {
            vista.mostrarMensaje("Alumno no encontrado.");
        }
    }

    private void mostrarNotas() {
        int dni = leerDNIValido("DNI: ");
        List<Double> notas = gestor.getNotasDeAlumno(dni);
        vista.mostrarNotas(dni, notas);
    }

    private void mostrarAlumnos() {
        if (gestor.isDiccionarioVacio()) {
            vista.mostrarMensaje("Diccionario vacío.");
            return;
        }
        vista.mostrarAlumnos(gestor.getAlumnos());
    }

    private void mostrarPromedios() {
        if (gestor.isDiccionarioVacio()) {
            vista.mostrarMensaje("Diccionario vacío.");
            return;
        }
        vista.mostrarPromedios(gestor.getPromedios());
    }

    // Métodos de lectura de entrada

    private int leerEntero(String mensaje) {
        System.out.print(mensaje);
        while (!scanner.hasNextInt()) {
            System.out.print("Número inválido: ");
            scanner.next(); // descartar entrada incorrecta
        }
        int numero = scanner.nextInt();
        scanner.nextLine(); // utilizar newline
        return numero;
    }

    private int leerDNIValido(String mensaje) {
        int dni;
        while (true) {
            dni = leerEntero(mensaje);
            if (dni > 0 || dni == -1) { // Permitir -1 para salir
                return dni;
            }
            vista.mostrarMensaje("DNI inválido. Debe ser un número positivo.");
        }
    }

    private double leerDouble(String mensaje) {
        System.out.print(mensaje);
        while (!scanner.hasNextDouble()) {
            System.out.print("Número inválido: ");
            scanner.next(); // descartar entrada incorrecta
        }
        double numero = scanner.nextDouble();
        scanner.nextLine(); // consumir newline
        return numero;
    }

    private double leerNotaValidaDouble() {
        double nota;

        while (true) {
            nota = leerDouble("Nota (1.0-10.0): ");

            if (nota >= 1.0 && nota <= 10.0) {
                break; // Salir del bucle cuando la nota sea válida
            }

            vista.mostrarMensaje("Error: La nota debe estar entre 1.0 y 10.0. Intente de nuevo.");
        }

        return nota;
    }
}
