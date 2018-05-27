package practica.individual2.bt;

public class Jugador {

	private Integer id;
	private String nombre;
	private String posicion1;
	private String posicion2;
	private Integer cache;
	private String nacion;
	private Integer minutosJugados;
	private Integer tirosCortos;
	private Integer tirosLargos;
	
	
	private Jugador(Integer id, String nombre, String posicion1, String posicion2, Integer cache, String nacion, 
			Integer minutosJugados, Integer tirosCortos, Integer tirosLargos){
		this.id = id;
		this.nombre = nombre;
		this.posicion1 = posicion1;
		this.posicion2 = posicion2;
		this.cache = cache;
		this.nacion = nacion;
		this.minutosJugados = minutosJugados;
		this.tirosCortos = tirosCortos;
		this.tirosLargos = tirosLargos;
	}
	
	
	
	public static Jugador create (Integer id, String nombre, String posicion1, String posicion2, Integer cache, String nacion, 
			Integer minutosJugados, Integer tirosCortos, Integer tirosLargos){
		return new Jugador(id, nombre, posicion1, posicion2, cache, nacion, minutosJugados, tirosCortos, tirosLargos);
	}



	public Integer getId() {
		return id;
	}



	public void setId(Integer id) {
		this.id = id;
	}



	public String getNombre() {
		return nombre;
	}



	public void setNombre(String nombre) {
		this.nombre = nombre;
	}



	public String getPosicion1() {
		return posicion1;
	}



	public void setPosicion1(String posicion1) {
		this.posicion1 = posicion1;
	}



	public String getPosicion2() {
		return posicion2;
	}



	public void setPosicion2(String posicion2) {
		this.posicion2 = posicion2;
	}



	public Integer getCache() {
		return cache;
	}



	public void setCache(Integer cache) {
		this.cache = cache;
	}



	public String getNacion() {
		return nacion;
	}



	public void setNacion(String nacion) {
		this.nacion = nacion;
	}



	public Integer getMinutosJugados() {
		return minutosJugados;
	}



	public void setMinutosJugados(Integer minutosJugados) {
		this.minutosJugados = minutosJugados;
	}



	public Integer getTirosCortos() {
		return tirosCortos;
	}



	public void setTirosCortos(Integer tirosCortos) {
		this.tirosCortos = tirosCortos;
	}



	public Integer getTirosLargos() {
		return tirosLargos;
	}



	public void setTirosLargos(Integer tirosLargos) {
		this.tirosLargos = tirosLargos;
	}



	@Override
	public String toString() {
		return "\nJugador [id=" + id + ", nombre=" + nombre + ", posicion1=" + posicion1 + ", posicion2=" + posicion2
				+ ", cache=" + cache + ", nacion=" + nacion + ", minutosJugados=" + minutosJugados + ", tirosCortos="
				+ tirosCortos + ", tirosLargos=" + tirosLargos + "]";
	}



	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
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
		Jugador other = (Jugador) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (nombre == null) {
			if (other.nombre != null)
				return false;
		} else if (!nombre.equals(other.nombre))
			return false;
		return true;
	}
	
	
	
	
	
	
	
	
}
