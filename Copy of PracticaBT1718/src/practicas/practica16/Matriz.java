package practicas.practica16;

public class Matriz implements Comparable<Matriz> {

	public static Matriz create(String s) {
		return new Matriz(s);
	}

	public static Matriz create(Integer i, Integer j) {
		return new Matriz(i, j);
	}

	private Integer numeroFilas;
	private Integer numeroColumnas;

	public Matriz(String s) {
		super();

		String[] v = s.split("[ ,]");
		Integer ne = v.length;
		if (ne != 2)
			throw new IllegalArgumentException("Formato no adecuado en línea  "
					+ s);

		numeroFilas = new Integer(v[0]);
		numeroColumnas = new Integer(v[1]);

	}

	public Matriz(Integer i, Integer j) {
		super();

		this.numeroFilas = i;
		this.numeroColumnas = j;

	}

	public Integer getNumeroFilas() {
		return numeroFilas;
	}

	public Integer getNumeroColumnas() {
		return numeroColumnas;
	}

	public String toString() {
		String s;

		s = "<" + getNumeroFilas() + " x " + getNumeroColumnas() + ">";

		return s;
	}

	public int hashCode() {
		final int prime = 31;
		int result = 1;

		result = prime
				* result
				+ ((getNumeroFilas() == null) ? 0 : getNumeroFilas().hashCode());
		result = prime
				* result
				+ ((getNumeroColumnas() == null) ? 0 : getNumeroColumnas()
						.hashCode());

		return result;
	}

	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (obj == null) {
			return false;
		}

		if (getClass() != obj.getClass()) {
			return false;
		}

		Matriz other = (Matriz) obj;

		if (getNumeroFilas() == null) {
			if (other.getNumeroFilas() != null) {
				return false;
			}
		} else if (!getNumeroFilas().equals(other.getNumeroFilas())) {
			return false;
		}

		if (getNumeroColumnas() == null) {
			if (other.getNumeroColumnas() != null) {
				return false;
			}
		} else if (!getNumeroColumnas().equals(other.getNumeroColumnas())) {
			return false;
		}

		return true;
	}

	public int compareTo(Matriz other) {
		int result;
		result = getNumeroFilas().compareTo(other.getNumeroFilas());
		if (result == 0) {
			result = getNumeroColumnas().compareTo(
					other.getNumeroColumnas());
		}
		return result;
	}



}
