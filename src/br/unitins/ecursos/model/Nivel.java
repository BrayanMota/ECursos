package br.unitins.ecursos.model;

public enum Nivel {
	
		INICIANTE(1,"Iniciante"),
		INTERMEDIARIO(2,"Intermediário"),
		AVANCADO(3,"Avançado");

		private int id;
		private String label;

		private Nivel(int id, String label) {
			this.id = id;
			this.label = label;
		}

		public int getId() {
			return this.id;
		}

		public String getLabel() {
			return this.label;
		}

		public static Nivel valueOf(int id) {
			for (Nivel niv : values())
				if (niv.getId() == id)
					return niv;

			return null;
		}
	}
