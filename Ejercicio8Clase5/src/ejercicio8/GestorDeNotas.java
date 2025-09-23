package ejercicio8;

import ejercicio8.estructuras.Entrada;
import ejercicio8.estructuras.Entry;
import ejercicio8.estructuras.LinkedMap;
import ejercicio8.estructuras.LinkedList;
import ejercicio8.estructuras.List;
import ejercicio8.estructuras.Map;

public class GestorDeNotas {

    private Map<Integer, Double> ultimoMapeo;
    private Map<Integer, List<Double>> diccionario = new LinkedMap<>();

    // Métodos para el mapeo temporal

    public void iniciarCargaMapeo() {
        this.ultimoMapeo = new LinkedMap<>();
    }

    public void agregarNotaEnMapeoTemporal(int dni, double nota) {
        if (this.ultimoMapeo != null) {
            this.ultimoMapeo.put(Integer.valueOf(dni), Double.valueOf(nota));
        }
    }

    public List<Entry<Integer, Double>> getUltimoMapeoEntries() {
        if (this.ultimoMapeo != null) {
            return this.ultimoMapeo.entries();
        } else {
            return new LinkedList<>();
        }
    }

    public int getUltimoMapeoSize() {
        if (this.ultimoMapeo != null) {
            return this.ultimoMapeo.size();
        } else {
            return 0;
        }
    }

    public boolean isMapeoTemporalVacio() {
        if (this.ultimoMapeo == null) {
            return true;
        }
        // Si no es nulo, entonces revisamos si está vacío
        if (this.ultimoMapeo.isEmpty()) {
            return true;
        }
        // Si no es nulo y no está vacío, entonces no es vacío
        return false;
    }

    // Métodos para el diccionario general

    public boolean consolidarMapeo() {
        // Verificar si hay datos para procesar
        if (isMapeoTemporalVacio()) {
            System.out.println("[INFO] No hay datos temporales para consolidar.");
            return false;
        }

        // Obtener todas las entradas del mapeo temporal
        List<Entry<Integer, Double>> entradas = this.ultimoMapeo.entries();
        int totalEntradasProcesadas = 0;

        // Procesar cada entrada del mapeo temporal
        for (int i = 0; i < entradas.size(); i++) {
            Entry<Integer, Double> entrada = entradas.get(i);
            int dni = entrada.getKey().intValue();
            double nota = entrada.getValue().doubleValue();

            // Buscar o crear la lista de notas para este DNI
            Integer claveDni = Integer.valueOf(dni);
            List<Double> listaNotas = this.diccionario.get(claveDni);

            if (listaNotas == null) {
                listaNotas = new LinkedList<>();
                this.diccionario.put(claveDni, listaNotas);
            }

            // Agregar la nota a la lista
            listaNotas.addLast(Double.valueOf(nota));
            totalEntradasProcesadas++;
        }

        // Limpiar el mapeo temporal después de la consolidación
        this.ultimoMapeo = null;
        System.out.println("[INFO] Consolidación completada: " + totalEntradasProcesadas + " entradas procesadas.");

        return true;
    }

    public boolean agregarNota(int dni, double nota) {
        List<Double> notas = this.diccionario.get(Integer.valueOf(dni));
        if (notas == null) {
            return false; // Alumno no existe
        }
        notas.addLast(Double.valueOf(nota));
        return true;
    }

    public int quitarNota(int dni, double nota) {
        List<Double> notas = this.diccionario.get(Integer.valueOf(dni));
        if (notas == null || notas.isEmpty()) {
            return -1; // Alumno no existe o no tiene notas
        }

        if (notas.remove(Double.valueOf(nota)) != null) {
            if (notas.isEmpty()) {
                this.diccionario.remove(Integer.valueOf(dni));
                return 1; // Nota eliminada y alumno también
            }
            return 0; // Nota eliminada
        } else {
            return -2; // Nota no encontrada
        }
    }

    public boolean quitarAlumno(int dni) {
        if (this.diccionario.remove(Integer.valueOf(dni)) != null) {
            return true; // Alumno eliminado
        } else {
            return false; // Alumno no encontrado
        }
    }

    public List<Double> getNotasDeAlumno(int dni) {
        return this.diccionario.get(Integer.valueOf(dni));
    }

    public List<Integer> getAlumnos() {
        return this.diccionario.keys();
    }

    public boolean isDiccionarioVacio() {
        return this.diccionario.isEmpty();
    }

    public List<Entry<Integer, Double>> getPromedios() {
        // Obtener todas las entradas del diccionario (cada entrada representa un alumno)
        List<Entry<Integer, List<Double>>> entradasAlumnos = this.diccionario.entries();

        // Crear lista para almacenar los promedios calculados
        List<Entry<Integer, Double>> listaPromedios = new LinkedList<>();

        // Procesar cada alumno para calcular su promedio
        for (int i = 0; i < entradasAlumnos.size(); i++) {
            Entry<Integer, List<Double>> entradaAlumno = entradasAlumnos.get(i);

            // Extraer datos del alumno actual
            int dni = entradaAlumno.getKey().intValue();
            List<Double> notasAlumno = entradaAlumno.getValue();

            // Calcular el promedio del alumno
            double promedioAlumno = calcularPromedio(notasAlumno);

            // Crear nueva entrada con el DNI y promedio calculado
            Entry<Integer, Double> entradaPromedio = new Entrada<>(Integer.valueOf(dni),
                    Double.valueOf(promedioAlumno));

            // Agregar a la lista de resultados
            listaPromedios.addLast(entradaPromedio);
        }

        return listaPromedios;
    }

    private double calcularPromedio(List<Double> notas) {
        if (notas.isEmpty())
            return 0.0;

        double suma = 0;
        for (int i = 0; i < notas.size(); i++) {
            suma += notas.get(i).doubleValue();
        }
        return suma / notas.size();
    }
}
