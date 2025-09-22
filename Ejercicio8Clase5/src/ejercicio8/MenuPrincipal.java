package ejercicio8;

import ejercicio8.estructuras.*;
import java.util.Iterator;
import java.util.Scanner;

public class MenuPrincipal {

    private Map<Integer, Double> ultimoMapeo;
    private Map<Integer, List<Double>> diccionario = new LinkedMap<>();
    private Scanner scanner = new Scanner(System.in);

    public void iniciar() {
        int opcion;
        do {
            mostrarMenu();
            opcion = leerEntero("Opción: ");
            System.out.println("[DEBUG] Opción seleccionada: " + opcion);
            procesarOpcion(opcion);
        } while (opcion != 0);
        System.out.println("¡Hasta luego!");
    }

    private void mostrarMenu() {
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
        System.out.println("10. Cargar datos de prueba");
        System.out.println("0. Salir");
    }

    private void procesarOpcion(int opcion) {
        System.out.println("[DEBUG] Entrando a procesarOpcion con la opción: " + opcion);
        switch (opcion) {
            case 1: cargarMapeo(); break;
            case 2: mostrarUltimoMapeo(); break;
            case 3: consolidarMapeo(); break;
            case 4: agregarNota(); break;
            case 5: quitarNota(); break;
            case 6: quitarAlumno(); break;
            case 7: mostrarNotas(); break;
            case 8: mostrarAlumnos(); break;
            case 9: mostrarPromedios(); break;
            case 10: cargarDatosDePrueba(); break;
            case 0: System.out.println("[DEBUG] Saliendo del programa."); break;
            default: System.out.println("Opción inválida.");
        }
        System.out.println("[DEBUG] Saliendo de procesarOpcion.");
    }

    private void cargarMapeo() {
        System.out.println("[DEBUG] Entrando a cargarMapeo.");
        ultimoMapeo = new LinkedMap<>();
        System.out.println("Ingrese DNI y nota. (Para finalizar la carga, ingrese DNI -1)");

        int dni;
        while (true) {
            dni = leerEntero("DNI: ");
            if (dni == -1) {
                break;
            }
            if (dni <= 0) {
                System.out.println("DNI inválido. Debe ser un número positivo.");
                continue;
            }

            double nota = leerNotaValidaDouble();
            System.out.println("[DEBUG] Cargando en mapeo temporal: DNI=" + dni + ", Nota=" + nota);
            ultimoMapeo.put(dni, nota);
        }

        System.out.println("[DEBUG] Saliendo del bucle de carga de mapeo.");
        System.out.println("Mapeo cargado: " + ultimoMapeo.size() + " entradas.");
        System.out.println("[DEBUG] Saliendo de cargarMapeo.");
    }

    private void mostrarUltimoMapeo() {
        System.out.println("[DEBUG] Entrando a mostrarUltimoMapeo.");
        if (ultimoMapeo == null || ultimoMapeo.isEmpty()) {
            System.out.println("[DEBUG] No hay mapeo temporal para mostrar.");
            System.out.println("No hay mapeo cargado.");
            return;
        }
        System.out.println("Último mapeo:");
        Iterator<Entry<Integer, Double>> it = ultimoMapeo.entries();
        while (it.hasNext()) {
            Entry<Integer, Double> entrada = it.next();
            System.out.println("DNI: " + entrada.getKey() + " -> Nota: " + entrada.getValue());
        }
        System.out.println("[DEBUG] Saliendo de mostrarUltimoMapeo.");
    }

    private void consolidarMapeo() {
        System.out.println("[DEBUG] Entrando a consolidarMapeo.");
        if (ultimoMapeo == null || ultimoMapeo.isEmpty()) {
            System.out.println("[DEBUG] No hay mapeo temporal para consolidar.");
            System.out.println("No hay mapeo para consolidar.");
            return;
        }
        Iterator<Entry<Integer, Double>> it = ultimoMapeo.entries();
        while (it.hasNext()) {
            Entry<Integer, Double> entrada = it.next();
            int dni = entrada.getKey();
            double nota = entrada.getValue();
            System.out.println("[DEBUG] Consolidando: DNI=" + dni + ", Nota=" + nota);
            List<Double> notas = diccionario.get(dni);
            
            if (notas == null) {
                System.out.println("[DEBUG] El DNI " + dni + " es nuevo. Creando nueva lista de notas.");
                notas = new LinkedList<>();
                diccionario.put(dni, notas);
            } else {
                System.out.println("[DEBUG] El DNI " + dni + " ya existe. Añadiendo a su lista.");
            }
            notas.addLast(nota);
        }
        System.out.println("Mapeo consolidado.");
        ultimoMapeo = null;
        System.out.println("[DEBUG] Mapeo temporal limpiado (puesto a null).");
        System.out.println("[DEBUG] Saliendo de consolidarMapeo.");
    }

    private void agregarNota() {
        System.out.println("[DEBUG] Entrando a agregarNota.");
        int dni = leerDNIValido("DNI: ");
        List<Double> notas = diccionario.get(dni);
        if (notas == null) {
            System.out.println("Alumno no existe.");
            return;
        }
        double nota = leerNotaValidaDouble();
        System.out.println("[DEBUG] Agregando nota " + nota + " al DNI " + dni);
        notas.addLast(nota);
        System.out.println("Nota agregada.");
        System.out.println("[DEBUG] Saliendo de agregarNota.");
    }


    private void quitarNota() {
        System.out.println("[DEBUG] Entrando a quitarNota.");
        int dni = leerDNIValido("DNI: ");
        List<Double> notas = diccionario.get(dni);
        if (notas == null || notas.isEmpty()) {
            System.out.println("Alumno no existe o sin notas.");
            return;
        }
        double nota = leerDouble("Nota a quitar: ");
        System.out.println("[DEBUG] Intentando quitar nota " + nota + " del DNI " + dni);
        try {
            if (notas.remove(nota) != null) {
                System.out.println("Nota eliminada.");
                if (notas.isEmpty()) {
                    System.out.println("[DEBUG] El alumno " + dni + " se quedó sin notas. Eliminando del diccionario.");
                    diccionario.remove(dni);
                    System.out.println("Alumno eliminado (sin notas).");
                }
            } else {
                System.out.println("[DEBUG] La nota " + nota + " no fue encontrada para el DNI " + dni);
                System.out.println("Nota no encontrada.");
            }
        } catch (MyException e) {
            System.out.println("Error: " + e.getMessage());
        }
        System.out.println("[DEBUG] Saliendo de quitarNota.");
    }

    private void quitarAlumno() {
        System.out.println("[DEBUG] Entrando a quitarAlumno.");
        int dni = leerDNIValido("DNI: ");
        System.out.println("[DEBUG] Intentando eliminar al alumno con DNI " + dni);
        if (diccionario.remove(dni) != null) {
            System.out.println("[DEBUG] Alumno " + dni + " encontrado y eliminado.");
            System.out.println("Alumno eliminado.");
        } else {
            System.out.println("[DEBUG] Alumno " + dni + " no encontrado en el diccionario.");
            System.out.println("Alumno no encontrado.");
        }
        System.out.println("[DEBUG] Saliendo de quitarAlumno.");
    }


    private void mostrarNotas() {
        System.out.println("[DEBUG] Entrando a mostrarNotas.");
        int dni = leerDNIValido("DNI: ");
        List<Double> notas = diccionario.get(dni);
        if (notas == null || notas.isEmpty()) {
            System.out.println("Sin notas para DNI " + dni);
            return;
        }
        System.out.print("Notas DNI " + dni + ": [");
        boolean primero = true;
        for (Double nota : notas) {
            if (!primero) System.out.print(", ");
            System.out.print(nota);
            primero = false;
        }
        System.out.println("]");
    }

    private void mostrarAlumnos() {
        System.out.println("[DEBUG] Entrando a mostrarAlumnos.");
        if (diccionario.isEmpty()) {
            System.out.println("Diccionario vacío.");
            return;
        }
        System.out.println("Alumnos:");
        Iterator<Integer> it = diccionario.keys();
        while (it.hasNext()) {
            System.out.println("- " + it.next());
        }
    }


    private void mostrarPromedios() {
        System.out.println("[DEBUG] Entrando a mostrarPromedios.");
        if (diccionario.isEmpty()) {
            System.out.println("Diccionario vacío.");
            return;
        }
        System.out.println("Alumnos y promedios:");
        Iterator<Entry<Integer, List<Double>>> it = diccionario.entries();
        while (it.hasNext()) {
            Entry<Integer, List<Double>> entrada = it.next();
            int dni = entrada.getKey();
            List<Double> notas = entrada.getValue();
            System.out.println("[DEBUG] Calculando promedio para DNI: " + dni);
            double promedio = calcularPromedio(notas);
            System.out.printf("DNI: %d, Promedio: %.2f (%d notas)\n", 
                            dni, promedio, notas.size());
        }
    }

    private double calcularPromedio(List<Double> notas) {
        if (notas.isEmpty()) return 0.0;
        
        double suma = 0;
        for (Double nota : notas) {
            suma += nota;
        }
        return suma / notas.size();
    }

    private void cargarDatosDePrueba() {
        System.out.println("[DEBUG] Entrando a cargarDatosDePrueba.");
        CargadorDatosDePrueba.cargarEn(this.diccionario);
        System.out.println("Datos de prueba cargados en el diccionario general.");
    }

    private int leerEntero(String mensaje) {
        System.out.print(mensaje);
        while (!scanner.hasNextInt()) {
            System.out.print("Número inválido: ");
            scanner.next();
        }
        return scanner.nextInt();
    }

    private int leerDNIValido(String mensaje) {
        int dni;
        while (true) {
            dni = leerEntero(mensaje);
            if (dni > 0) {
                return dni;
            }
            System.out.println("DNI inválido. Debe ser un número positivo.");
        }
    }

    private double leerDouble(String mensaje) {
        System.out.print(mensaje);
        while (!scanner.hasNextDouble()) {
            System.out.print("Número inválido: ");
            scanner.next();
        }
        return scanner.nextDouble();
    }


    private double leerNotaValidaDouble() {
        double nota = leerDouble("Nota (1.0-10.0): ");

        while (nota < 1.0 || nota > 10.0) {
            System.out.println("Error: La nota debe estar entre 1.0 y 10.0. Intente de nuevo.");
            nota = leerDouble("Nota (1.0-10.0): ");
        }
        return nota;
    }
}