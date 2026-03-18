import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

import java.io.PrintStream;
import java.io.ByteArrayOutputStream;

class EvaluadorTest {
   
	@Test
	@DisplayName("Test constructor válido")
	public void testConstructorValido() {
		Evaluador evaluador = new Evaluador();
		assertNotNull(evaluador, "El constructor devuelve null.");
	}

	@Test
	@DisplayName("Test salida correcta - ENTORNOS DE DESARROLLO")
	public void testSalidaCorrectaEEDD() {
		PrintStream salidaConsola = System.out;

		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		PrintStream salidaTest = new PrintStream(baos);
		System.setOut(salidaTest);
		try{	
			String[] args = {"datos/entornos_desarrollo.csv"};
			Evaluador.main(args);
		}
		catch(Exception e){
			e.printStackTrace();
			fail("Excepción inesperada.");
		}
		System.setOut(salidaConsola);
		String salida = baos.toString();
		assertTrue(salida.contains("Informe de calificaciones para la asignatura: ENTORNOS DE DESARROLLO"), "La salida no contiene la cabecera esperada.");
		assertTrue(salida.contains("Alejandro González: 5,63"), "La salida no contiene la información correcta del alumno.");
		assertTrue(salida.contains("Resumen:"), "La salida no contiene la sección de resumen.");
		assertTrue(salida.contains("Aprobados: 14"), "La salida no contiene la información correcta de aprobados.");
		assertTrue(salida.contains("Suspensos: 7"), "La salida no contiene la información correcta de suspensos.");
	}
	@Test
	@DisplayName("Test salida correcta - PROGRAMACIÓN")
	public void testSalidaCorrectaPROG() {
		PrintStream salidaConsola = System.out;

		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		PrintStream salidaTest = new PrintStream(baos);
		System.setOut(salidaTest);
		try{	
			String[] args = {"datos/programacion.csv"};
			Evaluador.main(args);
		}
		catch(Exception e){
			e.printStackTrace();
			fail("Excepción inesperada.");
		}
		System.setOut(salidaConsola);
		String salida = baos.toString();
		assertTrue(salida.contains("Informe de calificaciones para la asignatura: PROGRAMACIÓN"), "La salida no contiene la cabecera esperada.");
		assertTrue(salida.contains("Alejandro González: 5,85"), "La salida no contiene la información correcta del alumno.");
		assertTrue(salida.contains("Resumen:"), "La salida no contiene la sección de resumen.");
		assertTrue(salida.contains("Aprobados: 18"), "La salida no contiene la información correcta de aprobados.");
		assertTrue(salida.contains("Suspensos: 2"), "La salida no contiene la información correcta de suspensos.");
	}
}
