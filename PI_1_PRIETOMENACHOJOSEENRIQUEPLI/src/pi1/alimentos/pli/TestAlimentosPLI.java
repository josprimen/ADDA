package pi1.alimentos.pli;

import us.lsi.pl.AlgoritmoPLI;

public class TestAlimentosPLI {

	
	public static void main(String[] args) {
		
		//Map<Integer, List<Double>> alimentos = new HashMap<Integer,List<Double>>();
		//List<Integer> nutrientes = new ArrayList<Integer>();
		//List<Double> cero = new ArrayList<Double>();
		//List<Double> uno = new ArrayList<Double>();
		//cero = Arrays.asList(0.1,0.08,0.04,0.01,0.04);
		//uno = Arrays.asList(0.2,0.15,0.02,0.0,0.06);
		//alimentos.put(0, cero);
		//alimentos.put(1, uno);	
		//nutrientes = Arrays.asList(90,50,20,2);
		
		ProblemaIngredientes.create("ficheros/ingredientes.txt","ficheros/nutrientesMinimos.txt");
		//ProblemaIngredientes.nutrientesMinimos = Arrays.asList(90,50,20,2);
		String r = AlimentosPLI.getConstraints(ProblemaIngredientes.ingredientesActivos,ProblemaIngredientes.nutrientesMinimos);
		System.out.println("Ingredientes activos: "+ProblemaIngredientes.ingredientesActivos);
		System.out.println("Nutrientes minimos: "+ProblemaIngredientes.nutrientesMinimos);
		System.out.println("Especificación LPSolve (fichero creado): \n\n" + r);
		System.out.println("###################################################################");
		
		
		// TODO
		
		//System.out.println(r); //lo de abajo igual
		AlgoritmoPLI a = AlgoritmoPLI.create();
		a.setConstraints(r);
		a.ejecuta();
		System.out.println("Sol:");
		
		//Nosotros le pasamos el fichero generado con el getConstraints, que contiene todas las cosas a cumplir
		//Y el LPsolve nos lo resuelve mediante (a.) que tiene metodos para obtener sol., obtener objetivo, etc.
			
		
		System.out.println("Coste Solución Óptima = " + a.getObjetivo()); //coste
		System.out.println("Solución Óptima: "); //El for de abajo

		for (int i = 0; i<a.getNumVar(); i++){
		System.out.println("Gramos de ingrediente " + a.getName(i) + " -> " + (int)a.getSolucion()[i]);	
			
			
		}
	}
	
}
