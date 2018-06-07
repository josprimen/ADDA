package practica.individual2.pd;
import practica.individual2.bt.ProblemaJugador;
import us.lsi.algoritmos.AbstractAlgoritmo;
import us.lsi.algoritmos.Algoritmos;
import us.lsi.pd.AlgoritmoPD;
import us.lsi.pd.ProblemaPD;

public class TestJugador {

	public static void main(String[] args) {
		AlgoritmoPD.isRandomize = false;

		
		String separacion = "\n#########################################################\n";
		
		AbstractAlgoritmo.raiz="";
		ProblemaJugador.create("ficheros/jugadores.txt", "ficheros/restricciones.txt");
		System.out.println(separacion + "Jugadores: \n" + ProblemaJugador.jugadores.toString() + separacion);
		System.out.println("Numero suplentes: " + ProblemaJugador.numeroSuplentes + separacion);
		System.out.println("Presupuesto: " + ProblemaJugador.presupuesto + separacion);
		
		
		ProblemaPD<SolucionJugador, Integer> inicial = ProblemaJugador1PD2.create();
		System.out.println("Problema Inicial = " + inicial);
		AlgoritmoPD<SolucionJugador, Integer> a = Algoritmos.createPD(inicial);
		a.ejecuta();
		SolucionJugador s = a.getSolucion(inicial);
		//System.out.println(a.getSolucion(inicial).toString() + separacion);
		System.out.println("Solucion: " + s);
		System.out.println("Valor a optimizar: "+a.solucionesParciales.get(inicial).propiedad);

		
		a.showAllGraph("ficheros/matrices.gv", "Matriz", inicial);
	}
}
