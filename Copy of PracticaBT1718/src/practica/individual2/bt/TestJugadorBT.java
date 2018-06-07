package practica.individual2.bt;

import us.lsi.algoritmos.AbstractAlgoritmo;
import us.lsi.algoritmos.Algoritmos;
import us.lsi.bt.AlgoritmoBT;

public class TestJugadorBT {

	public static void main(String[] args) {
	
		AlgoritmoBT.numeroDeSoluciones = 1;

		//Para activar el filtro
		AbstractAlgoritmo.calculaMetricas();
		AlgoritmoBT.conFiltro = true;
		
		ProblemaJugador.create("ficheros/jugadores.txt", "ficheros/restricciones.txt");
		System.out.println("Jugadores: \n"+ProblemaJugador.jugadores.toString());
		System.out.println("Numero suplentes: " + ProblemaJugador.numeroSuplentes);
		System.out.println("Presupuesto: " + ProblemaJugador.presupuesto);
		
		//TODO ALUMNOS: creación del estado inicial y del AlgoritmoBT.
		EstadoJugadorBT p = EstadoJugadorBT.create();
		AlgoritmoBT<SolucionJugadorBT ,Integer> a = Algoritmos.createBT(p);

		a.ejecuta();

		if (a.getSoluciones().isEmpty()) 
			System.out.println("No se encuentra ninguna solución.");
		else 
			//for(SolucionJugadorBT s: a.getSoluciones())
				//System.out.println(s);
				System.out.println("\n###############Solución##############\n" + a.getSolucion());
				System.out.println("Cota: " + p.getObjetivoEstimado(1));

	}
}
