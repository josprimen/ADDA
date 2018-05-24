package practica.individual2.bt;

import us.lsi.algoritmos.Algoritmos;
import us.lsi.bt.AlgoritmoBT;

public class TestJugadorBT {

	public static void main(String[] args) {
	
		//TODO ALUMNOS: variar el n�mero de soluciones.
		AlgoritmoBT.numeroDeSoluciones = 1;

		//TODO ALUMNOS: probar qu� ocurre al establecer isRandomize a true.
		//AlgoritmoBT.isRandomize = true;
		
		
		ProblemaJugador.create("ficheros/jugadores.txt", "ficheros/restricciones.txt");
		System.out.println("Jugadores: \n"+ProblemaJugador.jugadores.toString());
		System.out.println("Numero suplentes: " + ProblemaJugador.numeroSuplentes);
		System.out.println("Presupuesto: " + ProblemaJugador.presupuesto);
		
		//TODO ALUMNOS: creaci�n del estado inicial y del AlgoritmoBT.
		EstadoJugadorBT p = EstadoJugadorBT.create();
		AlgoritmoBT<SolucionJugadorBT ,Integer> a = Algoritmos.createBT(p);


		
		a.ejecuta();

		if (a.getSoluciones().isEmpty()) 
			System.out.println("No se encuentra ninguna soluci�n.");
		else 
			for(SolucionJugadorBT s: a.getSoluciones())
				System.out.println(s);
		System.out.println(a.getSolucion());

	}
}
