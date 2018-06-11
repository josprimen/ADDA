package us.lsi.algoritmos;

import us.lsi.ag.AlgoritmoAG;
import us.lsi.ag.agchromosomes.ChromosomeFactory.ChromosomeType;
import us.lsi.ag.ProblemaAG;

public class Algoritmos {
	
	/**
	 *
	 * @param tipo El tipo del cromomosoma
	 * @param p Problema
	 * @return AlgoritmoAG
	 */
	
	public static AlgoritmoAG createAG(ChromosomeType tipo, ProblemaAG p) {
		return new AlgoritmoAG(tipo,p);
	}
		
	
}
