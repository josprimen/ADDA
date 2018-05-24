package practicas.practica16;


public class SolucionMatriz {

	public static SolucionMatriz create() {
		return new SolucionMatriz();
	}

	public static SolucionMatriz create(String exp, Integer op) {
		return new SolucionMatriz(exp, op);
	}

	private Integer numeroDeOperaciones;
	private String expresionConParentesis;

	public SolucionMatriz() {
		this.numeroDeOperaciones = 0;
		this.expresionConParentesis = "";

	}

	
	public SolucionMatriz(String exp, Integer op) {
		this.numeroDeOperaciones = op;
		this.expresionConParentesis = exp;
	}

	public Integer getNumeroDeOperaciones() {
		return numeroDeOperaciones;
	}

	public String getExpresionConParentesis() {
		return expresionConParentesis;
	}

	public int hashCode() {
		final int prime = 31;
		int result = 1;

		result = prime
				* result
				+ ((getNumeroDeOperaciones() == null) ? 0
						: getNumeroDeOperaciones().hashCode());
		result = prime
				* result
				+ ((getExpresionConParentesis() == null) ? 0
						: getExpresionConParentesis().hashCode());

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

		SolucionMatriz other = (SolucionMatriz) obj;

		if (getNumeroDeOperaciones() == null) {
			if (other.getNumeroDeOperaciones() != null) {
				return false;
			}
		} else if (!getNumeroDeOperaciones().equals(
				other.getNumeroDeOperaciones())) {
			return false;
		}

		if (getExpresionConParentesis() == null) {
			if (other.getExpresionConParentesis() != null) {
				return false;
			}
		} else if (!getExpresionConParentesis().equals(
				other.getExpresionConParentesis())) {
			return false;
		}

		return true;
	}

	
	public String toString() {
		
		return "SolucionMatriz [numero de operaciones="
				+ getNumeroDeOperaciones() + ", expresion="
				+ getExpresionConParentesis() + "]";
	}



	public int compareTo(SolucionMatriz other) {
		int result;
		if (other == null) {
			result = -1;
		} else {
			result = getNumeroDeOperaciones().compareTo(
					other.getNumeroDeOperaciones());

			if (result == 0) {
				result = getExpresionConParentesis().compareTo(
						other.getExpresionConParentesis());
			}
		}

		return result;
	}

}
