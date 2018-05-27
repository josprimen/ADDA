package practica.individual2.pd;

import java.util.ArrayList;
import java.util.List;

import practica.individual2.bt.Jugador;

public class SolucionJugador {

	//algun problema con ponerlo public??¿¿??
	public List<Jugador> jugadores;
	
	public static SolucionJugador create() {
		return new SolucionJugador();
	}

	public static SolucionJugador create(List<Jugador> elegidos) {
		return new SolucionJugador(elegidos);
	}

	

	public SolucionJugador() {
		this.jugadores = new ArrayList<Jugador>();

	}

	
	public SolucionJugador(List<Jugador> elegidos) {
		this.jugadores = new ArrayList<Jugador>();
		jugadores.addAll(elegidos);
	}
//Algun problema con autogenerar??¿¿??
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((jugadores == null) ? 0 : jugadores.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SolucionJugador other = (SolucionJugador) obj;
		if (jugadores == null) {
			if (other.jugadores != null)
				return false;
		} else if (!jugadores.equals(other.jugadores))
			return false;
		return true;
	}

	private Double getObjetivo() {
		//TODO ALUMNOS: EL CUERPO DEL MÉTODO.
		Double tirosTotales = 0.;
		for(Jugador a:jugadores){
			tirosTotales += (double) a.getTirosCortos();
			tirosTotales += (double) a.getTirosLargos();
		}
		return tirosTotales;
		
	}
	
	@Override
	public String toString() {
		//return "SolucionJugador [jugadores=" + jugadores + "]";
		String res = "";
		Integer numeroConvocados = 0;
		Integer pivots = 0;
		Integer aleros = 0;
		Integer base = 0;
		for(Jugador a: jugadores){
			res = res + a.toString();
			numeroConvocados++;
				if(a.getPosicion1().equals("Pivot")||a.getPosicion2().equals("Pivot")) pivots++;
				if(a.getPosicion1().equals("Alero")||a.getPosicion2().equals("Alero")) aleros++;
				if(a.getPosicion1().equals("Base")||a.getPosicion2().equals("Base")) base++;
			
		}
		res = res + "\n\n Pivots solución: " + pivots;
		res = res + "\n Aleros solución: " + aleros;
		res = res + "\n Bases solución: " + base;
		res = res + "\n Convocados: " + numeroConvocados + "\n";
		res = res + "\n Tiros: " + getObjetivo() + "\n";
		return res;
	}

	
	
	
	

}
