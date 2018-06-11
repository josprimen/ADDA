package pi1.alimentos.pli;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import us.lsi.stream.Stream2;

public class ProblemaIngredientes {

	public static Map<Integer, List<Double>> ingredientesActivos;
	public static List<Integer> nutrientesMinimos;
	
	public ProblemaIngredientes(String file, String file2){
		super();
		leeAlimentos(file);
		leeNutrientes(file2);
	}
	
	
	public static void leeAlimentos(String file){	
		List<String> ls = Stream2.fromFile(file).toList();
		ingredientesActivos = new HashMap<Integer, List<Double>>();
		int index = 0;
		for(String s : ls){
			String[] at = Stream2.fromString(s, ",").toArray((int x)->new String[x]);
			ingredientesActivos.put(index, propiedades(at));
			index++;
		}
	}
	
	public static void leeNutrientes(String file){
		List<String> ls = Stream2.fromFile(file).toList();
		nutrientesMinimos = new ArrayList<Integer>();
		for(String s : ls){
			String[] at = Stream2.fromString(s, ",").toArray((int x)->new String[x]);
			for(int i=0; i<at.length; i++){
				nutrientesMinimos.add(new Integer (at[i]));
			}
		}

		
	}
	
	
	
	private static List<Double> propiedades(String[] fm){
		List<Double> b = new ArrayList<Double>();
		for(int i=0; i<fm.length; i++){
			b.add(new Double (fm[i]));
		}
		return b;
		
		
		/*b.add(new Double(fm[0]));
		b.add(new Double(fm[1]));
		b.add(new Double(fm[2]));
		b.add(new Double(fm[3]));
		b.add(new Double(fm[4]));
		return b;*/
	}
	
	public static ProblemaIngredientes create(String file, String file2){
		return new ProblemaIngredientes(file, file2);
	}
	
	
}
