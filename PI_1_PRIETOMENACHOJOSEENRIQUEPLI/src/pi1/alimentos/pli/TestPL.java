package pi1.alimentos.pli;
import us.lsi.algoritmos.Algoritmos;
import us.lsi.pl.*;

public class TestPL {

	public static void main(String[] args) {
		
		AlgoritmoPLI a = Algoritmos.createPLI("ficheros/Ejercicio1.txt");
		
		a.ejecuta();
		
		int nV = a.getNumVar();
		
		for (int pos = 0; pos < nV; pos++){
			double x = a.getSolucion()[pos];
			System.out.println(a.getName(pos) + " = " + x + " ");
		}
		
		System.out.println("------");
		System.out.println("Objetivo: " + a.getObjetivo());
		System.out.println("------");
	}
}

