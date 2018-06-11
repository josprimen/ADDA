package pi3.alimentos.ag;

import us.lsi.ag.AlgoritmoAG;
import us.lsi.ag.agchromosomes.ChromosomeFactory;
import us.lsi.ag.agchromosomes.IndexChromosome;
import us.lsi.ag.agchromosomes.ChromosomeFactory.ChromosomeType;
import us.lsi.ag.agstopping.StoppingConditionFactory;
import us.lsi.ag.agstopping.StoppingConditionFactory.StoppingConditionType;
import us.lsi.algoritmos.Algoritmos;

public class TestAlimentosAG {

	public static void main(String[] args){

		AlgoritmoAG.ELITISM_RATE  = 0.3;
		AlgoritmoAG.CROSSOVER_RATE = 0.8;
		AlgoritmoAG.MUTATION_RATE = 0.7;
		AlgoritmoAG.POPULATION_SIZE = 100;
		
		StoppingConditionFactory.NUM_GENERATIONS = 10000;
		StoppingConditionFactory.SOLUTIONS_NUMBER_MIN = 1;
		StoppingConditionFactory.FITNESS_MIN = 623.;
		StoppingConditionFactory.stoppingConditionType = StoppingConditionType.SolutionsNumber;
		
		ProblemaIngredientes.create("ficheros/ingredientes.txt","ficheros/nutrientesMinimos.txt");
		System.out.println("Ingredientes activos: "+ProblemaIngredientes.ingredientesActivos);
		System.out.println("Nutrientes minimos: "+ProblemaIngredientes.nutrientesMinimos);
		ProblemaAlimentosAG p = new ProblemaAlimentosAG();
		//
		AlgoritmoAG ap = Algoritmos.createAG(ChromosomeType.IndexRange, p); //Aqui pues hacemos esto que es tipo que vamos a usar y tal
		ap.ejecuta();
		
		System.out.println("================================");
		IndexChromosome cr = ChromosomeFactory.asIndex(ap.getBestFinal());
		System.out.println("Solución: " + p.getSolucion(cr));
		System.out.println("Coste de la solucion: " + p.getCoste(cr));
		System.out.println("Función fitness: " + p.fitnessFunction(cr));
		
		System.out.println("================================");
		//System.out.println("Hay " + AlgoritmoAG.bestChromosomes.size()+" mejores cromosomas");
		
	}	
	
}
