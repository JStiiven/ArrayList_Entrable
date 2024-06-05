import java.util.ArrayList;
import javax.swing.JOptionPane;

public class ProfesoresEstudiantes {
		ArrayList<String> listaProfesores;
		ArrayList<ArrayList<String>> listaGeneralEstudiantes;

    public ProfesoresEstudiantes() {
        listaProfesores = new ArrayList<>();
        listaGeneralEstudiantes = new ArrayList<>();
    }

    public void iniciar() {
        int opcion;
        // Este ciclo do-while permite hacer el menu de opciones.
        do {
            String menu = "MENU:\n"
                    + "1. Registrar profesores\n"
                    + "2. Registrar estudiantes asociados al profesor\n"
                    + "3. Consultar lista total de profesores y sus estudiantes asociados\n"
                    + "4. Consultar un profesor e imprimir la lista de estudiantes asociados\n"
                    + "5. Consultar un profesor e indicar la cantidad de estudiantes asociados\n"
                    + "6. Consultar un estudiante y a que profesor pertenece\n"
                    + "7. Salir\n"
                    + "Seleccione una opción: ";
            opcion = Integer.parseInt(JOptionPane.showInputDialog(menu));
            
            // Este siwtch permite llamar a los metodos segun la opcion seleccionada y se repite hasta que el usuario indique la opcion 7.
            switch (opcion) {
                case 1:
                    registrarProfesores();
                    break;
                case 2:
                    registrarEstudiantes();
                    break;
                case 3:
                    consultarListaProfesoresYEstudiantes();
                    break;
                case 4:
                    consultaProfesorPorNombre();
                    break;
                case 5:
                    consultaCantidadEstudiantesPorProfesor();
                    break;
                case 6:
                    consultarEstudiante();
                    break;
                case 7:
                    JOptionPane.showMessageDialog(null, "Saliendo...");
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Opción no válida. Intente de nuevo.");
            }
        } while (opcion != 7);
    }
    
    // Este metodo se usa para regristar los profesores y tambien se le indica si el nombre ingresado ya existe o no
    public void registrarProfesores() {
        int cantidad = Integer.parseInt(JOptionPane.showInputDialog("Ingrese la cantidad de profesores a registrar:"));

        for (int i = 0; i < cantidad; i++) {
            String nombreProfesor = JOptionPane.showInputDialog("Ingrese el nombre del profesor " + (i + 1) + ":");
            if (listaProfesores.contains(nombreProfesor)) {
                JOptionPane.showMessageDialog(null, "El nombre del profesor ya existe. Intente nuevamente.");
                i--;
            } else {
                listaProfesores.add(nombreProfesor);
                listaGeneralEstudiantes.add(new ArrayList<>());
            }
        }
    }
    
    /*Este metodo se usa para registrar estudiantes, hace la validacion si hay algun profesor registrado y si no retorna para que vuelva
    *	y registre un profesor
    */
    public void registrarEstudiantes() {
        if (listaProfesores.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Primero debe registrar profesores.");
            return;
        }

        for (int i = 0; i < listaProfesores.size(); i++) {
            String profesor = listaProfesores.get(i);
            ArrayList<String> listEstudiantes = listaGeneralEstudiantes.get(i);

            int cantidadEstudiantes = Integer.parseInt(JOptionPane.showInputDialog("Ingrese la cantidad de estudiantes para el profesor " + profesor + ":"));

            for (int j = 0; j < cantidadEstudiantes; j++) {
                String nombreEstudiante = JOptionPane.showInputDialog("Ingrese el nombre del estudiante " + (j + 1) + ":");
                if (listEstudiantes.contains(nombreEstudiante)) {
                    JOptionPane.showMessageDialog(null, "El nombre del estudiante ya existe. Intente nuevamente.");
                    j--;
                } else {
                    listEstudiantes.add(nombreEstudiante);
                }
            }
        }
    }

    // Este metodo se usa para consultar lista de profesores y estudiantes además de hacer una validacion si hay profesores registrados.
    public void consultarListaProfesoresYEstudiantes() {
        if (listaProfesores.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No hay profesores registrados.");
            return;
        }

        StringBuilder resultado = new StringBuilder();
        for (int i = 0; i < listaProfesores.size(); i++) {
            resultado.append("Profesor: ").append(listaProfesores.get(i)).append("\n")
                     .append("Estudiantes: ").append(String.join(", ", listaGeneralEstudiantes.get(i))).append("\n\n");
        }
        JOptionPane.showMessageDialog(null, resultado.toString());
    }

    /* Este metodo permite consultar los profesores por nombre, verifica si hay profesores registrados
     * Pide el nombre del profesor a consultar y, si está registrado, muestra sus estudiantes si no, 
     * muestra un mensaje indicando que no se encuentra registrado.
     */
    public void consultaProfesorPorNombre() {
        if (listaProfesores.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No hay profesores registrados.");
            return;
        }

        String nombreProfesor = JOptionPane.showInputDialog("Ingrese el nombre del profesor a consultar:");

        if (listaProfesores.contains(nombreProfesor)) {
            int index = listaProfesores.indexOf(nombreProfesor);
            JOptionPane.showMessageDialog(null, "Estudiantes del profesor " + nombreProfesor + ": " + String.join(", ", listaGeneralEstudiantes.get(index)));
        } else {
            JOptionPane.showMessageDialog(null, "El profesor no se encuentra registrado.");
        }
    }

    /* Este metodo verifica si hay profesores registrados y pide el nombre del profesor a consultar 
     * y, si está registrado, muestra la cantidad de estudiantes asociados si no, muestra un mensaje indicando que 
     * no se encuentra registrado. 
     */
    public void consultaCantidadEstudiantesPorProfesor() {
        if (listaProfesores.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No hay profesores registrados.");
            return;
        }

        String nombreProfesor = JOptionPane.showInputDialog("Ingrese el nombre del profesor a consultar:");

        if (listaProfesores.contains(nombreProfesor)) {
            int index = listaProfesores.indexOf(nombreProfesor);
            JOptionPane.showMessageDialog(null, "Cantidad de estudiantes del profesor " + nombreProfesor + ": " + listaGeneralEstudiantes.get(index).size());
        } else {
            JOptionPane.showMessageDialog(null, "El profesor no se encuentra registrado.");
        }
    }
    
    // Este metodo verifica si hay profesores o estudiantes registrados.
    public void consultarEstudiante() {
        if (listaProfesores.isEmpty() || listaGeneralEstudiantes.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No hay profesores o estudiantes registrados.");
            return;
        }

        String nombreEstudiante = JOptionPane.showInputDialog("Ingrese el nombre del estudiante a consultar:");
        boolean encontrado = false;

        for (int i = 0; i < listaProfesores.size(); i++) {
            if (listaGeneralEstudiantes.get(i).contains(nombreEstudiante)) {
                JOptionPane.showMessageDialog(null, "El estudiante " + nombreEstudiante + " pertenece al profesor " + listaProfesores.get(i));
                encontrado = true;
                break;
            }
        }

        if (!encontrado) {
            JOptionPane.showMessageDialog(null, "El estudiante no se encuentra registrado.");
        }
    }
}
