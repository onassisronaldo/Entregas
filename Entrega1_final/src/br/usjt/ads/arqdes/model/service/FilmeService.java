package br.usjt.ads.arqdes.model.service;

import java.io.IOException;
import java.util.ArrayList;

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
	public void atualizarFilme(Filme filme) throws IOException{
		dao.atualizar(filme);
	}
	
	public Filme inserirFilme(Filme filme) throws IOException {
		int id = dao.inserirFilme(filme);
		filme.setId(id);
		return filme;
	}

	public ArrayList<Filme> listarFilmes(String chave) throws IOException{
		return dao.listarFilmes(chave);
	}

	public ArrayList<Filme> listarFilmes() throws IOException{
		return dao.listarFilmes();
	}
	public void excluirFilme(int id) throws IOException{
		dao.excluir(id);
	}
}
