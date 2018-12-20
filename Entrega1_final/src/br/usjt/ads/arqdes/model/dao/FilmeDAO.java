package br.usjt.ads.arqdes.model.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.usjt.ads.arqdes.model.entity.Filme;
import br.usjt.ads.arqdes.model.entity.Genero;

public class FilmeDAO {
	
	public int inserirFilme(Filme filme) throws IOException {
		int id = -1;
		String sql = "insert into Filme (titulo, descricao, diretor, posterpath, "
				+ "popularidade, data_lancamento, id_genero) values (?,?,?,?,?,?,?)";
		
		try(Connection conn = ConnectionFactory.getConnection();
			PreparedStatement pst = conn.prepareStatement(sql);){
			
			pst.setString(1, filme.getTitulo());
			pst.setString(2, filme.getDescricao());
			pst.setString(3, filme.getDiretor());
			pst.setString(4, filme.getPosterPath());
			pst.setDouble(5, filme.getPopularidade());
			pst.setDate(6, new java.sql.Date(filme.getDataLancamento().getTime()));
			pst.setInt(7, filme.getGenero().getId());			
			pst.execute();
			
			//obter o id criado
			String query = "select LAST_INSERT_ID()";
			try(PreparedStatement pst1 = conn.prepareStatement(query);
				ResultSet rs = pst1.executeQuery();){

				if (rs.next()) {
					id = rs.getInt(1);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new IOException(e);
		}
		return id;
	}

	public Filme buscarFilme(int id) throws IOException{
		String sql = "SELECT titulo, descricao, diretor, posterpath, popularidade, data_lancamento, id_genero FROM FILME WHERE id = ?";
		Filme filme = new Filme();
		Genero genero = new Genero();
		try(Connection conn = ConnectionFactory.getConnection();
				PreparedStatement pst = conn.prepareStatement(sql);){
			pst.setInt(1, id);
			filme.setId(id);
			try(ResultSet rs = pst.executeQuery();){
				if(rs.next()) {
				filme.setTitulo(rs.getString("titulo"));
				filme.setDescricao(rs.getString("descricao"));
				filme.setDiretor(rs.getString("diretor"));
				filme.setPosterPath(rs.getString("posterpath"));
				filme.setPopularidade(rs.getDouble("popularidade"));
				filme.setDataLancamento(rs.getDate("data_lancamento"));
				genero.setId(rs.getInt("id_genero"));
				} else {
					filme.setTitulo(null);
					filme.setDescricao(null);
					filme.setDiretor(null);
					filme.setPosterPath(null);
					filme.setPopularidade(-1);
					filme.setDataLancamento(null);
					genero.setId(-1);
				}				
			}catch (SQLException e) {
				e.printStackTrace();
			}
		} catch(SQLException e) {
			System.out.print(e.getStackTrace());
		}
		filme.setGenero(genero);
		return filme;
	}
	public void alterarFilme(Filme filme) {
		String sqlUpdate = "UPDATE FILME SET titulo = ?, descricao = ?, diretor = ?, posterpath = ?, popularidade = ?, data_lancamento = ?, id_genero = ? WHERE id = ?";
		
		try (Connection conn = ConnectionFactory.getConnection();
				PreparedStatement stm = conn.prepareStatement(sqlUpdate);) {
			
			stm.setString(1, filme.getTitulo());
			stm.setString(2, filme.getDescricao());
			stm.setString(3, filme.getDiretor());
			stm.setString(4, filme.getPosterPath());
			stm.setDouble(5, filme.getPopularidade());
			if(filme.getDataLancamento() != null) {
				stm.setDate(6, new java.sql.Date(filme.getDataLancamento().getTime()));
			} else {
				stm.setDate(6,  null);
			}
			stm.setInt(7, filme.getGenero().getId());
			stm.setInt(8, filme.getId());
			stm.execute();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void excluirFilme(int id) throws IOException{
		String sql = "DELETE FROM filme WHERE id = ?";
		try(Connection conn = ConnectionFactory.getConnection();
					PreparedStatement pst = conn.prepareStatement(sql);){
			pst.setInt(1, id);
			pst.execute();
		} catch(SQLException e){
			e.printStackTrace();
		}
	}
}
