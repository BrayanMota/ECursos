package br.unitins.ecursos.model;

public enum Ensino {
	EAD(1, "Ensino a Distância"),
	EP(2, "Ensino Presencial");
	
	private int id;
	private String label;
	
	private Ensino(int id, String label) {
		this.id = id;
		this.label = label;
	}

	public int getId() {
		return this.id;
	}

	public String getLabel() {
		return this.label;
	}

	public static Ensino valueOf(int id) {
		for (Ensino ens : values())
			if (ens.getId() == id)
				return ens;

		return null;
	}
}
