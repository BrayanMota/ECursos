package br.unitins.ecursos.controller;

import java.util.ArrayList;
import java.util.List;

import br.unitins.ecursos.application.Util;
import br.unitins.ecursos.dao.DAO;

public abstract class ControllerGeral<T> {

	protected T entity;
	private DAO<T> dao = null;
	private List<T> listaEntity;
	
	public ControllerGeral(DAO<T> dao) {
		super();
		this.dao = dao;
	}

	public void incluir() {
		try {
			dao.inserir(getEntity());
			Util.mensagemInfo("Inclus�o realizada com sucesso.");
			limpar();
		} catch (Exception e) {
			Util.mensagemError("N�o � possivel fazer uma inclus�o.");
			e.printStackTrace();
		}
	}
	
	public void alterar() {
		try {
			dao.alterar(getEntity());
			Util.mensagemInfo("Altera��o realizada com sucesso.");
			limpar();
		} catch (Exception e) {
			Util.mensagemError("N�o � possivel fazer uma altera��o.");
			e.printStackTrace();
		}
	}

	public void excluir() {
		excluir(getEntity());
	}

	public void excluir(T entity) {
		try {
			dao.excluir(entity);
			Util.mensagemInfo("Exclus�o realizada com sucesso.");
			limpar();
		} catch (Exception e) {
			Util.mensagemError("N�o � possivel fazer uma exclus�o.");
			e.printStackTrace();
		}
	}
	
	public void editar(T entity) {
		try {
			setEntity(dao.obterUm(entity));
		} catch (Exception e) {
			Util.mensagemError("Problema ao editar.");
			e.printStackTrace();
		}
	}
	
	public List<T> getListaEntity() {
		if (listaEntity == null) {
			try {
				listaEntity = dao.obterTodos();
			} catch (Exception e) {
				e.printStackTrace();
				listaEntity = new ArrayList<T>();
			}
		}	
		return listaEntity;
	}
	
	public void limpar() {
		entity = null;
		listaEntity = null;
	}

	public abstract T getEntity();

	public void setEntity(T entity) {
		this.entity = entity;
	}
	
}
