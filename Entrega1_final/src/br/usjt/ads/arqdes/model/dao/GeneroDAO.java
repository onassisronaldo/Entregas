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
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import br.usjt.ads.arqdes.model.entity.Genero;
@Repository
public class GeneroDAO {
	@PersistenceContext
	EntityManager manager;

	public Genero buscarGenero(int id) throws IOException {
		return manager.find(Genero.class, id);
	}

	@SuppressWarnings("unchecked")
	public List<Genero> listarGeneros() throws IOException {
		return manager.createQuery("select g from Genero g").getResultList();
	}
}
