package br.unitins.ecursos.controller;

import java.io.Serializable;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import br.unitins.ecursos.dao.CursoDAO;
import br.unitins.ecursos.model.Curso;
import br.unitins.ecursos.model.Ensino;
import br.unitins.ecursos.model.Nivel;

@Named
@ViewScoped
public class CursoController extends ControllerGeral<Curso> implements Serializable {
	static final long serialVersionUID = -6924299224954193606L;

	public CursoController() {
		super(new CursoDAO());
	}

	@Override
	public Curso getEntity() {
		if (entity == null)
			entity = new Curso();
		return entity;
	}
	
	public Nivel[] getListaNivel() {
		return Nivel.values();
	}
	
	public Ensino[] getListaEnsino() {
		return Ensino.values();
	}

}
