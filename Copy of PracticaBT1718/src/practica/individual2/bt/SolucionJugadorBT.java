package practica.individual2.bt;

import java.util.ArrayList;
import java.util.List;
import us.lsi.bt.SolucionBT;

//TODO ALUMNOS: OTRAS IMPORTACIONES.



public class SolucionJugadorBT implements SolucionBT{
	
	//TODO ALUMNOS: ATRIBUTOS DE LA CLASE.
	private List<Jugador> escogidos;


	
	@Override
	public Double getObjetivo() {
		//TODO ALUMNOS: EL CUERPO DEL MÉTODO.
		Double tirosTotales = 0.;
		for(Jugador a:escogidos){
			tirosTotales += (double) a.getTirosCortos();
			tirosTotales += (double) a.getTirosLargos();
		}
		return tirosTotales;
		
	}
	
	private SolucionJugadorBT () {
		//TODO ALUMNOS: EL CUERPO DEL MÉTODO.
		//absurdo
		escogidos = new ArrayList<Jugador>();
	}
	
	
	//TODO ALUMNOS: OTROS CONSTRUCTORES.	
	//###################################
	private SolucionJugadorBT (List<Jugador> escogidos) {
			this.escogidos = new ArrayList<Jugador>();
			this.escogidos.addAll(escogidos);
		}
	
	public static SolucionJugadorBT create () {
		//TODO ALUMNOS: EL CUERPO DEL MÉTODO.
		//absurdo
		return new SolucionJugadorBT();
	}

	//TODO ALUMNOS: OTROS MÉTODOS create.
	//#######################################
	public static SolucionJugadorBT create (List<Jugador> escogidos) {
		
		return new SolucionJugadorBT(escogidos);
	}
	
	
	public String toString () {
		String res = "";
		Integer numeroConvocados = 0;
		Integer pivots = 0;
		Integer aleros = 0;
		Integer base = 0;
		for(Jugador a: escogidos){
			res = res + a.toString();
			numeroConvocados++;
				if(a.getPosicion1().equals("Pivot")||a.getPosicion2().equals("Pivot")) pivots++;
				if(a.getPosicion1().equals("Alero")||a.getPosicion2().equals("Alero")) aleros++;
				if(a.getPosicion1().equals("Base")||a.getPosicion2().equals("Base")) base++;
			
		}
		res = res + "\n Pivots solución: " + pivots;
		res = res + "\n Aleros solución: " + aleros;
		res = res + "\n Bases solución: " + base;
		res = res + "\n Convocados: " + numeroConvocados;
		res = res + "\n Tiros: " + getObjetivo() + "\n";
		return res;
	}

}
