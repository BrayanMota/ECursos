package br.unitins.ecursos.model;

import java.time.LocalDate;

public class Curso {
	
	private Integer id;
	private String nomeCurso;
	private Integer cargaHoraria;
	private LocalDate dataCurso;
	private String nomeProfessor;
	private Nivel nivelCurso;
	private Ensino ensino;
	private Double valorCurso;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNomeCurso() {
		return nomeCurso;
	}
	public void setNomeCurso(String nomeCurso) {
		this.nomeCurso = nomeCurso;
	}
	public Integer getCargaHoraria() {
		return cargaHoraria;
	}
	public void setCargaHoraria(Integer cargaHoraria) {
		this.cargaHoraria = cargaHoraria;
	}
	public LocalDate getDataCurso() {
		return dataCurso;
	}
	public void setDataCurso(LocalDate dataCurso) {
		this.dataCurso = dataCurso;
	}
	public String getNomeProfessor() {
		return nomeProfessor;
	}
	public void setNomeProfessor(String nomeProfessor) {
		this.nomeProfessor = nomeProfessor;
	}
	public Nivel getNivelCurso() {
		return nivelCurso;
	}
	public void setNivelCurso(Nivel nivelCurso) {
		this.nivelCurso = nivelCurso;
	}
	public Ensino getEnsino() {
		return ensino;
	}
	public void setEnsino(Ensino ensino) {
		this.ensino = ensino;
	}
	public Double getValorCurso() {
		return valorCurso;
	}
	public void setValorCurso(Double valorCurso) {
		this.valorCurso = valorCurso;
		
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		Curso other = (Curso) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
}
