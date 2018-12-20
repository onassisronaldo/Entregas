package br.usjt.ads.arqdes.model.service;

import java.io.IOException;

import br.usjt.ads.arqdes.model.dao.FilmeDAO;
import br.usjt.ads.arqdes.model.entity.Filme;

public class FilmeService {
	private FilmeDAO dao;
	
	public FilmeService() {
		dao = new FilmeDAO();
	}
	
	public Filme buscarFilme(int id) throws IOException{
		return dao.buscarFilme(id);
	}
	
	public Filme inserirFilme(Filme filme) throws IOException {
		int id = dao.inserirFilme(filme);
		filme.setId(id);
		return filme;
	}
	public void excluirFilme(int id) throws IOException{
		dao.excluirFilme(id);
	}
	public void alterarFilme(Filme filme) throws IOException{
		dao.alterarFilme(filme);
	}
}
