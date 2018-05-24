package practicas.practica14.caballo;

import us.lsi.algoritmos.Algoritmos;
import us.lsi.bt.AlgoritmoBT;

public class TestCaballoBT {

	public static void main(String[] args) {
	
		//TODO ALUMNOS: variar el número de soluciones.
		AlgoritmoBT.numeroDeSoluciones = 2;

		//TODO ALUMNOS: probar qué ocurre al establecer isRandomize a true.
		//AlgoritmoBT.isRandomize = true;
		
		
		ProblemaCaballo.setLadoTablero(6);
		ProblemaCaballo.setCasillaInicialX(1);
		ProblemaCaballo.setCasillaInicialY(1);
		
		//TODO ALUMNOS: creación del estado inicial y del AlgoritmoBT.
		EstadoCaballoBT p = EstadoCaballoBT.create();
		AlgoritmoBT<SolucionCaballoBT ,Integer> a = Algoritmos.createBT(p);


		
		a.ejecuta();

		if (a.getSoluciones().isEmpty()) 
			System.out.println("No se encuentra ninguna solución.");
		else 
			for(SolucionCaballoBT s: a.getSoluciones())
				System.out.println(s);
	}
}
