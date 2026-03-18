import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Evaluador {
    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Error: Debe proporcionar el nombre del archivo a procesar.");
            return;
        }

        String nombreArchivo = args[0];
        String[] lineas = new String[100]; // Asumimos un máximo de 100 líneas
        int numLineas = 0;

        try (BufferedReader br = new BufferedReader(new FileReader(nombreArchivo))) {
            String linea;
            while ((linea = br.readLine()) != null && numLineas < lineas.length) {
                lineas[numLineas++] = linea;
            }
        } catch (IOException e) {
            System.out.println("Error al leer el archivo: " + e.getMessage());
            return;
        }

        if (numLineas == 0) {
            System.out.println("Error: El archivo está vacío.");
            return;
        }

        String asignatura = lineas[0];
        Integer aprobados = 0;
        Integer suspensos = 0;

        System.out.println("Informe de calificaciones para la asignatura: " + asignatura);
        System.out.println("--------------------------------------------------");

        for (int i = 1; i < numLineas; i++) {
            String[] partes = lineas[i].split(",");
            String nombreAlumno = partes[0];
            Double[] calificaciones = new Double[partes.length - 1];

            for (int j = 1; j < partes.length; j++) {
                calificaciones[j - 1] = Double.parseDouble(partes[j]);
            }

            Double calificacionFinal = 0.0;

            if (asignatura.equals("ENTORNOS DE DESARROLLO")) {
                // Media ponderada para ENTORNOS DE DESARROLLO
                Double[] pesos = {1.0, 1.0, 2.0, 2.0, 2.0, 3.0, 3.0, 3.0, 4.0, 5.0};
                Double sumaPonderada = 0.0;
                Double sumaPesos = 0.0;

                for (int k = 0; k < calificaciones.length; k++) {
                    sumaPonderada += calificaciones[k] * pesos[k];
                    sumaPesos += pesos[k];
                }

                calificacionFinal = sumaPonderada / sumaPesos;
            } else if (asignatura.equals("PROGRAMACIÓN")) {
                // Media entre la calificación más alta y la más baja para PROGRAMACIÓN
                Double max = calificaciones[0];
                Double min = calificaciones[0];

                for (Double cal : calificaciones) {
                    if (cal > max) max = cal;
                    if (cal < min) min = cal;
                }

                calificacionFinal = (max + min) / 2;
            } else {
                // Media aritmética para otras asignaturas
                Double suma = 0.0;
                for (Double cal : calificaciones) {
                    suma += cal;
                }
                calificacionFinal = suma / calificaciones.length;
            }

            System.out.printf("%s: %.2f%n", nombreAlumno, calificacionFinal);

            if (calificacionFinal >= 5.0) {
                aprobados++;
            } else {
                suspensos++;
            }
        }

        System.out.println("--------------------------------------------------");
        System.out.println("Resumen:");
        System.out.println("Aprobados: " + aprobados);
        System.out.println("Suspensos: " + suspensos);
    }
}

