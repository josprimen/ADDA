package practicas.practica16;
import us.lsi.algoritmos.Algoritmos;
import us.lsi.pd.AlgoritmoPD;
import us.lsi.pd.ProblemaPD;

public class TestMatriz {

	public static void main(String[] args) {
		AlgoritmoPD.isRandomize = false;
		ProblemaMatriz problemaOriginal= ProblemaMatriz.create("ficheros/matrices.txt");
		System.out.println(problemaOriginal.getMatrices());
		ProblemaPD<SolucionMatriz, Integer> inicial = ProblemaMatrizPD.create(problemaOriginal);
		System.out.println("Problema Inicial = " + inicial);
		AlgoritmoPD<SolucionMatriz, Integer> a = Algoritmos.createPD(inicial);
		a.ejecuta();
		SolucionMatriz s = a.getSolucion(inicial);
		Integer n = s.getNumeroDeOperaciones();
		System.out.println("Solución con " + n + " operaciones.");
		System.out.println(a.getSolucion(inicial));
		if (!n.equals(2856)){
			System.out.println("Deberían haberse realizado 2856 operaciones.");
		}
		a.showAllGraph("ficheros/matrices.gv", "Matriz", inicial);
	}
}
