package practicas.practica14.caballo;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;

import us.lsi.bt.SolucionBT;

//TODO ALUMNOS: OTRAS IMPORTACIONES.



public class SolucionCaballoBT implements SolucionBT{
	
	//TODO ALUMNOS: ATRIBUTOS DE LA CLASE.
	private Table<Integer, Integer, Integer> tablero; //es como un mapa con dos claves y un valor


	
	@Override
	public Double getObjetivo() {
		//TODO ALUMNOS: EL CUERPO DEL MÉTODO.
		//al no ser este problema de maximizacion ni minimizacion este metodo devolvemos cualquier cosa
		return 0.;
	}
	
	private SolucionCaballoBT () {
		//TODO ALUMNOS: EL CUERPO DEL MÉTODO.
		//absurdo
		tablero = HashBasedTable.create();
	}
	
	
	//TODO ALUMNOS: OTROS CONSTRUCTORES.	
	//###################################
		private SolucionCaballoBT (Table<Integer, Integer, Integer> tablero) {
			tablero = HashBasedTable.create(tablero);
		}
	
	public static SolucionCaballoBT create () {
		//TODO ALUMNOS: EL CUERPO DEL MÉTODO.
		//absurdo
		return new SolucionCaballoBT();
	}

	//TODO ALUMNOS: OTROS MÉTODOS create.
	//#######################################
	public static SolucionCaballoBT create (Table<Integer, Integer, Integer> tablero) {
		
		return new SolucionCaballoBT(tablero);
	}
	
	
	public String toString () {
		//TODO ALUMNOS: EL CUERPO DEL MÉTODO.
		//en vd podemos poner return tablero.toString(); pero vamos a hacer algo mejor
		int tam = ProblemaCaballo.getLadoTablero();
		String s = "";
		for (int i = 0; i<tam;i++){
			for (int j = 0; j<tam;j++){
				if(j>0){
					s = s + " ";
				}
				Integer v = tablero.get(i, j);
				if(v!=null){
					if(v<10){
						s = s + " " + v;
					}
					else{
						s = s + v;
					}
				}else{
					s = s + " X";
				}
			}
			s = s + "\n";
		}
		return s;
	}

}
