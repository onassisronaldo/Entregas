package br.usjt.ads.arqdes.model.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import br.usjt.ads.arqdes.model.entity.Filme;
import br.usjt.ads.arqdes.model.entity.Genero;
import br.usjt.ads.arqdes.model.service.GeneroService;

@Repository
public class FilmeDAO {
	@PersistenceContext
	EntityManager manager;
	
	public int inserirFilme(Filme filme) throws IOException {
		manager.persist(filme);
		return filme.getId();
	}
	@SuppressWarnings("unchecked")
	public List<Filme> buscarFilmePopularidade() throws IOException {
		Query query = manager.createQuery("select f from Filme f order by popularidade desc");
		return query.getResultList();
	}
	public Filme buscarFilme(int id) throws IOException{
		return manager.find(Filme.class, id);
	}

	@SuppressWarnings("unchecked")
	public List<Filme> listarFilmes(String chave) throws IOException {
		Query query = manager.createQuery("select f from Filme f where f.titulo like :chave");
		query.setParameter("query", "%"+chave+"%");
		return query.getResultList();		
	}
	
	@SuppressWarnings("unchecked")
	public List<Filme> listarFilmes() throws IOException {
		return manager.createQuery("select f from Filme f").getResultList();
	}
	
	public void alterarFilme(Filme filme) throws IOException{
		manager.merge(filme);
	}
	public void excluirFilme(int id) throws IOException{
		manager.remove(manager.find(Filme.class, id));
	}

}
