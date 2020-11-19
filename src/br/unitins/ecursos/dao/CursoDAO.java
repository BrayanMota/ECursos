package br.unitins.ecursos.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.unitins.ecursos.application.Util;
import br.unitins.ecursos.model.Curso;
import br.unitins.ecursos.model.Ensino;
import br.unitins.ecursos.model.Nivel;

public class CursoDAO implements DAO<Curso> {

	@Override
	public void inserir(Curso obj) throws Exception {
		Exception exception = null;
		Connection conn = DAO.getConnection();
		
		StringBuffer sql = new StringBuffer();
		sql.append(" INSERT INTO ");
		sql.append("cursos ");
		sql.append("  (nome_curso, carga_horaria, data_curso, nome_professor, nivel_curso, tipo_ensino, valor_curso) ");
		sql.append("VALUES ");
		sql.append("  ( ?, ?, ?, ?, ?, ?, ?) ");
		PreparedStatement stat = null;

		try {
			stat = conn.prepareStatement(sql.toString());
			stat.setString(1, obj.getNomeCurso());
			stat.setInt(2, obj.getCargaHoraria());
			if (obj.getDataCurso() != null)
				stat.setDate(3, Date.valueOf(obj.getDataCurso()));
			else
				stat.setDate(3, null);
			stat.setString(4, obj.getNomeProfessor());
			stat.setObject(5, (obj.getNivelCurso() == null ? null : obj.getNivelCurso().getId()));
			stat.setObject(6, (obj.getEnsino() == null ? null : obj.getEnsino().getId()));
			stat.setDouble(7, obj.getValorCurso());

			stat.execute();
			conn.commit();

		} catch (SQLException e) {

			System.out.println("Erro ao realizar um comando sql de insert.");
			e.printStackTrace();
			try {
				conn.rollback();
			} catch (SQLException e1) {
				System.out.println("Erro ao realizar o rollback.");
				e1.printStackTrace();
			}
			exception = new Exception("Erro ao inserir");

		} finally {
			try {
				if (!stat.isClosed())
					stat.close();
			} catch (SQLException e) {
				System.out.println("Erro ao fechar o Statement");
				e.printStackTrace();
			}

			try {
				if (!conn.isClosed())
					conn.close();
			} catch (SQLException e) {
				System.out.println("Erro a o fechar a conexao com o banco.");
				e.printStackTrace();
			}
		}

		if (exception != null)
			throw exception;

	}

	@Override
	public void alterar(Curso obj) throws Exception {
		Exception exception = null;
		Connection conn = DAO.getConnection();

		StringBuffer sql = new StringBuffer();
		sql.append("UPDATE cursos SET ");
		sql.append("  nome_curso = ?, ");
		sql.append("  carga_horaria = ?, ");
		sql.append("  data_curso = ?, ");
		sql.append("  nome_professor = ?, ");
		sql.append("  nivel_curso = ?, ");
		sql.append("  tipo_ensino = ?, ");
		sql.append("  valor_curso = ? ");
		sql.append("WHERE ");
		sql.append("  id = ? ");

		PreparedStatement stat = null;

		try {
			stat = conn.prepareStatement(sql.toString());
			stat.setString(1, obj.getNomeCurso());
			stat.setInt(2, obj.getCargaHoraria());
			if (obj.getDataCurso() != null)
				stat.setDate(3, Date.valueOf(obj.getDataCurso()));
			else
				stat.setDate(3, null);
			stat.setString(4, obj.getNomeProfessor());
			stat.setObject(5, (obj.getNivelCurso() == null ? null : obj.getNivelCurso().getId()));
			stat.setObject(6, (obj.getEnsino() == null ? null : obj.getEnsino().getId()));
			stat.setDouble(7, obj.getValorCurso());

			stat.execute();
			conn.commit();

		} catch (SQLException e) {

			System.out.println("Erro ao realizar um comando sql de insert.");
			e.printStackTrace();
			try {
				conn.rollback();
			} catch (SQLException e1) {
				System.out.println("Erro ao realizar o rollback.");
				e1.printStackTrace();
			}
			exception = new Exception("Erro ao inserir");

		} finally {
			try {
				if (!stat.isClosed())
					stat.close();
			} catch (SQLException e) {
				System.out.println("Erro ao fechar o Statement");
				e.printStackTrace();
			}

			try {
				if (!conn.isClosed())
					conn.close();
			} catch (SQLException e) {
				System.out.println("Erro a o fechar a conexao com o banco.");
				e.printStackTrace();
			}
		}

		if (exception != null)
			throw exception;

	}

	@Override
	public void excluir(Curso obj) throws Exception {
		Exception exception = null;
		Connection conn = DAO.getConnection();

		StringBuffer sql = new StringBuffer();
		sql.append("DELETE FROM curso WHERE id = ?");

		PreparedStatement stat = null;

		try {
			stat = conn.prepareStatement(sql.toString());
			stat.setInt(1, obj.getId());
			stat.execute();
			conn.commit();

		} catch (SQLException e) {

			System.out.println("Erro ao realizar um comando sql de insert.");
			e.printStackTrace();
			try {
				conn.rollback();
			} catch (SQLException e1) {
				System.out.println("Erro ao realizar o rollback.");
				e1.printStackTrace();
			}
			exception = new Exception("Erro ao inserir");

		} finally {
			try {
				if (!stat.isClosed())
					stat.close();
			} catch (SQLException e) {
				System.out.println("Erro ao fechar o Statement");
				e.printStackTrace();
			}

			try {
				if (!conn.isClosed())
					conn.close();
			} catch (SQLException e) {
				System.out.println("Erro a o fechar a conexao com o banco.");
				e.printStackTrace();
			}
		}

		if (exception != null)
			throw exception;

	}

	@Override
	public List<Curso> obterTodos() throws Exception {
		Exception exception = null;
		Connection conn = DAO.getConnection();
		List<Curso> listaCurso = new ArrayList<Curso>();

		StringBuffer sql = new StringBuffer();
		sql.append("SELECT ");
		sql.append("  u.id, ");
		sql.append("  u.nome_curso, ");
		sql.append("  u.carga_horaria, ");
		sql.append("  u.data_curso, ");
		sql.append("  u.nome_professor, ");
		sql.append("  u.nivel_curso, ");
		sql.append("  u.tipo_ensino, ");
		sql.append("  u.valor_curso ");
		sql.append("FROM  ");
		sql.append("  cursos u ");
		sql.append("ORDER BY u.id ");

		PreparedStatement stat = null;
		try {

			stat = conn.prepareStatement(sql.toString());

			ResultSet rs = stat.executeQuery();

			while (rs.next()) {
				Curso curso = new Curso();
				curso.setId(rs.getInt("id"));
				Date data = rs.getDate("data_curso");
				curso.setNomeCurso(rs.getString("nome_curso"));
				curso.setCargaHoraria(rs.getInt("carga_horaria"));
				curso.setDataCurso(data == null ? null : data.toLocalDate());
				curso.setNomeProfessor(rs.getString("nome_professor"));
				curso.setNivelCurso(Nivel.valueOf(rs.getInt("nivel_curso")));
				curso.setEnsino(Ensino.valueOf(rs.getInt("tipo_ensino")));
				curso.setValorCurso(rs.getDouble("valor_curso"));

				listaCurso.add(curso);
			}

		} catch (SQLException e) {
			Util.mensagemError("Não foi possivel buscar os dados do usuario.");
			e.printStackTrace();
			exception = new Exception("Erro ao executar um sql em UsuarioDAO.");
		} finally {
			try {
				if (!stat.isClosed())
					stat.close();
			} catch (SQLException e) {
				System.out.println("Erro ao fechar o Statement");
				e.printStackTrace();
			}

			try {
				if (!conn.isClosed())
					conn.close();
			} catch (SQLException e) {
				System.out.println("Erro a o fechar a conexao com o banco.");
				e.printStackTrace();
			}
		}

		if (exception != null)
			throw exception;

		return listaCurso;
	}
	
	@Override
	public Curso obterUm(Curso obj) throws Exception {
		Exception exception = null;
		Connection conn = DAO.getConnection();
		
		Curso curso = null;

		StringBuffer sql = new StringBuffer();
		sql.append("SELECT ");
		sql.append("  u.id, ");
		sql.append("  u.nome_curso, ");
		sql.append("  u.carga_horaria, ");
		sql.append("  u.data_curso, ");
		sql.append("  u.nome_professor, ");
		sql.append("  u.nivel_curso, ");
		sql.append("  u.tipo_ensino, ");
		sql.append("  u.valor_curso ");
		sql.append("FROM  ");
		sql.append("  cursos u ");
		sql.append("ORDER BY u.id ");

		PreparedStatement stat = null;
		try {

			stat = conn.prepareStatement(sql.toString());
			stat.setInt(1, obj.getId());

			ResultSet rs = stat.executeQuery();

			if (rs.next()) {
				curso = new Curso();
				curso.setId(rs.getInt("id"));
				Date data = rs.getDate("data_curso");
				curso.setNomeCurso(rs.getString("nome_curso"));
				curso.setCargaHoraria(rs.getInt("carga_horaria"));
				curso.setDataCurso(data == null ? null : data.toLocalDate());
				curso.setNomeProfessor(rs.getString("nome_professor"));
				curso.setNivelCurso(Nivel.valueOf(rs.getInt("nivel_curso")));
				curso.setEnsino(Ensino.valueOf(rs.getInt("tipo_ensino")));
				curso.setValorCurso(rs.getDouble("valor_curso"));
			}

		} catch (SQLException e) {
			Util.mensagemError("Não foi possivel buscar os dados do usuario.");
			e.printStackTrace();
			exception = new Exception("Erro ao executar um sql em UsuarioDAO.");
		} finally {
			try {
				if (!stat.isClosed())
					stat.close();
			} catch (SQLException e) {
				System.out.println("Erro ao fechar o Statement");
				e.printStackTrace();
			}

			try {
				if (!conn.isClosed())
					conn.close();
			} catch (SQLException e) {
				System.out.println("Erro a o fechar a conexao com o banco.");
				e.printStackTrace();
			}
		}

		if (exception != null)
			throw exception;

		return curso;
	}
}
