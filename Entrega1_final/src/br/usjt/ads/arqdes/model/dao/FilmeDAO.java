package br.usjt.ads.arqdes.model.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import br.usjt.ads.arqdes.model.entity.Filme;
import br.usjt.ads.arqdes.model.entity.Genero;
import br.usjt.ads.arqdes.model.service.GeneroService;

@Repository
public class FilmeDAO {
	
	Connection conn;
	
	@Autowired
	public FilmeDAO(DataSource data) throws IOException {
		try {
			conn = data.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new IOException(e);
		}
	}
	
	public int inserirFilme(Filme filme) throws IOException {
		int id = -1;
		String sql = "insert into Filme (titulo, descricao, diretor, posterpath, "
				+ "popularidade, data_lancamento, id_genero) values (?,?,?,?,?,?,?)";
		
		try(PreparedStatement pst = conn.prepareStatement(sql);){
			
			pst.setString(1, filme.getTitulo());
			pst.setString(2, filme.getDescricao());
			pst.setString(3, filme.getDiretor());
			pst.setString(4, filme.getPosterPath());
			pst.setDouble(5, filme.getPopularidade());
			if(filme.getDataLancamento() != null) {
				pst.setDate(6, new java.sql.Date(filme.getDataLancamento().getTime()));
			} else {
				pst.setDate(6,  null);
			}
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
	public ArrayList<Filme> buscarFilmePopularidade() throws IOException {
		ArrayList<Filme> filmes = new ArrayList<>();
		String sql = "SELECT id, titulo, descricao, diretor, posterpath, popularidade, data_lancamento, id_genero from FILME order by popularidade DESC";

		try (PreparedStatement pst = conn.prepareStatement(sql);
				ResultSet rs = pst.executeQuery();) {

			while (rs.next()) {
				GeneroService gs = new GeneroService();
				int idGenero;
				Filme filme = new Filme();
				Genero genero = new Genero();
				filme.setId(rs.getInt("id"));
				filme.setTitulo(rs.getString("titulo"));
				filme.setDescricao(rs.getString("descricao"));
				filme.setDiretor(rs.getString("diretor"));
				filme.setPosterPath(rs.getString("posterpath"));
				filme.setPopularidade(rs.getDouble("popularidade"));
				filme.setDataLancamento(rs.getDate("data_lancamento"));
				genero.setId(rs.getInt("id_genero"));
				idGenero = genero.getId();
				genero = gs.buscarGenero(idGenero);
				filme.setGenero(genero);
				
				filmes.add(filme);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new IOException(e);
		}
		return filmes;
	}
	public Filme buscarFilme(int id) throws IOException{
		String sql = "SELECT id, titulo, descricao, diretor, posterpath, popularidade, data_lancamento, id_genero FROM FILME WHERE id = ?";
		Filme filme = new Filme();
		Genero genero = new Genero();
		int idGenero;
		GeneroService gs = new GeneroService();
		try (PreparedStatement pst = conn.prepareStatement(sql);) {

			pst.setInt(1, id);
			try (ResultSet rs = pst.executeQuery();) {

				if (rs.next()) {
					filme = new Filme();
					genero = new Genero();
					filme.setId(rs.getInt("id"));
					filme.setTitulo(rs.getString("titulo"));
					filme.setDescricao(rs.getString("descricao"));
					filme.setDiretor(rs.getString("diretor"));
					filme.setPosterPath(rs.getString("posterpath"));
					filme.setPopularidade(rs.getDouble("popularidade"));
					filme.setDataLancamento(rs.getDate("data_lancamento"));
					genero.setId(rs.getInt("id_genero"));
					
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new IOException(e);
		}
		
		idGenero = genero.getId();
		genero = gs.buscarGenero(idGenero);
		filme.setGenero(genero);
		return filme;
	}

	public ArrayList<Filme> listarFilmes(String chave) throws IOException {
		ArrayList<Filme> lista = new ArrayList<>();
		String sql = "select f.id, f.titulo, f.descricao, f.diretor, f.posterpath, "
				+ "f. popularidade, f.data_lancamento, f.id_genero, g.nome "
				+ "from filme f, genero g "
				+ "where f.id_genero = g.id and upper(f.titulo) like ?";
		try(PreparedStatement pst = conn.prepareStatement(sql);){
			
			pst.setString(1, "%" + chave.toUpperCase() + "%");
		
			try(ResultSet rs = pst.executeQuery();){
			
				Filme filme;
				Genero genero;
				while(rs.next()) {
					filme = new Filme();
					filme.setId(rs.getInt("f.id"));
					filme.setTitulo(rs.getString("f.titulo"));
					filme.setDescricao(rs.getString("f.descricao"));
					filme.setDiretor(rs.getString("f.diretor"));
					filme.setPosterPath(rs.getString("f.posterpath"));
					filme.setDataLancamento(rs.getDate("f.data_lancamento"));
					genero = new Genero();
					genero.setId(rs.getInt("f.id_genero"));
					genero.setNome(rs.getString("g.nome"));
					filme.setGenero(genero);
					lista.add(filme);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new IOException(e);
		}
				
		return lista;
	}
	
	public ArrayList<Filme> listarFilmes() throws IOException {
		ArrayList<Filme> lista = new ArrayList<>();
		String sql = "select f.id, f.titulo, f.descricao, f.diretor, f.posterpath, "
				+ "f. popularidade, f.data_lancamento, f.id_genero, g.nome "
				+ "from filme f, genero g "
				+ "where f.id_genero = g.id";
		try(PreparedStatement pst = conn.prepareStatement(sql);
				ResultSet rs = pst.executeQuery();){
			
			Filme filme;
			Genero genero;
			while(rs.next()) {
				filme = new Filme();
				filme.setId(rs.getInt("f.id"));
				filme.setTitulo(rs.getString("f.titulo"));
				filme.setDescricao(rs.getString("f.descricao"));
				filme.setDiretor(rs.getString("f.diretor"));
				filme.setPosterPath(rs.getString("f.posterpath"));
				filme.setDataLancamento(rs.getDate("f.data_lancamento"));
				genero = new Genero();
				genero.setId(rs.getInt("f.id_genero"));
				genero.setNome(rs.getString("g.nome"));
				filme.setGenero(genero);
				lista.add(filme);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new IOException(e);
		}				
		return lista;
	}
	
	public void alterarFilme(Filme filme) {
		String sqlUpdate = "UPDATE FILME SET titulo = ?, descricao = ?, diretor = ?, posterpath = ?, popularidade = ?, data_lancamento = ?, id_genero = ? WHERE id = ?";
		
		try (PreparedStatement stm = conn.prepareStatement(sqlUpdate);) {
			
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
		try(PreparedStatement pst = conn.prepareStatement(sql);){
			pst.setInt(1, id);
			pst.execute();
		} catch(SQLException e){
			e.printStackTrace();
		}
	}

}
