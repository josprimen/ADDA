package pi1.alimentos.pli;

import java.util.List;
import java.util.Map;

public class AlimentosPLI {

		public static String getConstraints( Map<Integer, List<Double>> ingredientes, List<Integer> nutrientes){ 
		//Recibe lista de nutrientes mínimos y un map de alimento activos
		String ret = "min: ";
		int ningredientes = ingredientes.size();
		int precio = ingredientes.get(0).size()-1; //El precio es el ultimo elemento de la lista de los values del map
		
		for(int i = 0; i<ningredientes; i++){ //Minimización del coste
			if(i!=0) ret = ret+" + ";
			ret = ret + ingredientes.get(i).get(precio) + "x" + i;
			
		}
		ret = ret + ";" + "\n\n";
		
		int count = 0;
		for(int i=0; i< nutrientes.size() ; i++){ //Restricciones de nutrientes
			ret = ret + nutrientes.get(i) + "<=";
			for(int j = 0; j<ingredientes.size();j++){
				if(j!=0) ret = ret+" + ";
				ret = ret + ingredientes.get(j).get(count) +"x"+ j;
			}
			ret = ret + ";\n\n";
			count++;
		}
		
		
		ret = ret + "int ";
		
		for(int i=0; i<ingredientes.size(); i++){ //Para indicar que las x0,x1...son de tipo integer
			if(i != 0) ret = ret + ", ";
			ret = ret + "x" + i ;
		}
		
		ret = ret + ";\n";
		return ret;
	}
	
	
	
}
