package br.unitins.ecursos.controller;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import br.unitins.ecursos.application.Session;
import br.unitins.ecursos.application.Util;
import br.unitins.ecursos.dao.UsuarioDAO;
import br.unitins.ecursos.model.Usuario;

@Named
@RequestScoped
public class LoginController {

	private Usuario usuario;

	public String paginaLogin() {
		Util.redirecionar("login.xhtml");
		return "";
	}

	public void logar() {

		UsuarioDAO dao = new UsuarioDAO();
		try {
			Usuario usuarioLogado = dao.obterUsuario(getUsuario().getEmail(),
					Util.hash(getUsuario().getEmail()) + getUsuario().getSenha());
			if (usuarioLogado == null)
				Util.mensagemError("Usuário ou senha inválidos.");
			else {
				Session.getInstance().setAttribute("usuarioLogado", usuarioLogado);
				Util.redirecionar("cadastroCursos.xhtml");
			}

		} catch (Exception e) {
			e.printStackTrace();
			Util.mensagemError("Problema não identificado, aguarde o contato do suporte.");
		}
	}

	public Usuario getUsuario() {
		if (usuario == null)
			usuario = new Usuario();
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
}
