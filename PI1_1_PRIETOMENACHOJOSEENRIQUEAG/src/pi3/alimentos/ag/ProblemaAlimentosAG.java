package pi3.alimentos.ag;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import us.lsi.ag.ProblemaAGIndex;
import us.lsi.ag.agchromosomes.IndexChromosome;


public class ProblemaAlimentosAG implements ProblemaAGIndex <Map<Integer, Integer>>{

	
	
	public Map<Integer, List<Double>> ingredientes = ProblemaIngredientes.ingredientesActivos;
	public List<Integer> minNutrientes = ProblemaIngredientes.nutrientesMinimos;

	
	public static ProblemaAlimentosAG create(){
		return new ProblemaAlimentosAG();
	}

	
	@Override
	public Map<Integer, Integer> getSolucion(IndexChromosome cr) {
		Map <Integer, Integer> m = new HashMap<Integer, Integer>();
		List<Integer> lc = cr.decode();
		for(int i = 0 ; i<lc.size(); i++){
			m.put(i, lc.get(i)); 
		}
		
		return m;
	}

	@Override
	public Integer getObjectsNumber() {
		// TODO;
		return ingredientes.size();
	}
	

	@Override
	public Double fitnessFunction(IndexChromosome cr) {
		int precio = ingredientes.get(0).size()-1; //El precio es el ultimo elemento de la lista de los values del map
		Map<Integer,Integer> res = getSolucion(cr);
		int coste = 0;;
		int penalizacion =0;
		
		//penalizamos soluciones que no cumplan el minimo de nutrientes
		
		for (int i = 0; i<precio; i++){
			Double nutrientex = 0.;
			for (Integer ingrediente : res.keySet()){
				Double cantidad = (double) res.get(ingrediente);
				nutrientex += (cantidad * ingredientes.get(ingrediente).get(i));				
			}
			if (nutrientex<minNutrientes.get(i)) penalizacion += minNutrientes.get(i)-nutrientex;
		}
		
	
		

		//Calculamos coste de la solucion
		for(Integer ingrediente : res.keySet()){
			Double cantidad = (double) res.get(ingrediente);
			coste += cantidad * ingredientes.get(ingrediente).get(precio);
		}
		
		return (double) (-coste-(1000000L*penalizacion));
	}

	
	
	public Integer getCoste(IndexChromosome cr) { //Hemos creado metodo para coste pillando el codigo de fitness
		Map<Integer, Integer> res = getSolucion(cr);
		int coste = 0;
		for(Integer ingrediente : res.keySet()){
			Double cantidad = (double) res.get(ingrediente);
			coste += cantidad * ingredientes.get(ingrediente).get(ingredientes.get(0).size()-1);
		}
		return coste;
	}
	
	
	public Integer getMax(int index){
		return 1000;//Es gramos por kilo, por eso el máximo es mil.
	}
	
}
