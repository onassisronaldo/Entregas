package br.usjt.ads.arqdes.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import br.usjt.ads.arqdes.model.entity.Filme;
import br.usjt.ads.arqdes.model.entity.Genero;
import br.usjt.ads.arqdes.model.service.FilmeService;
import br.usjt.ads.arqdes.model.service.GeneroService;

@Controller
public class ManterFilmesController {
	@Autowired
	private FilmeService fService;
	@Autowired
	private GeneroService gService;
	
	
	@RequestMapping("/")
	public String inicio() {
		return "index";
	}
	
	@RequestMapping("/inicio")
	public String inicio1() {
		return "index";
	}
	
	@RequestMapping("/listar_filmes")
	public String listarFilmes(HttpSession session){
		session.setAttribute("lista", null);
		return "ListarFilmes";
	}
	
	@RequestMapping("/novo_filme")
	public String novoFilme(HttpSession session){
		try {
			ArrayList<Genero> generos = gService.listarGeneros();
			session.setAttribute("generos", generos);
			 return "CriarFilme";
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "index";
	}	
	
	@RequestMapping("/excluir_filme")
	public String excluirFilme(Filme filme, Model model, @RequestParam int id) {
		try {
			fService.excluirFilme(filme.getId());
			return "index";
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "Erro";
	}
	@RequestMapping("/alterar_filme")
	public String alterarFilme(Filme filme, Model model, @RequestParam int id) {
		System.out.println(filme);
		try {
			Genero genero = gService.buscarGenero(filme.getGenero().getId());
			filme.setGenero(genero);
			System.out.println(filme);
			model.addAttribute("filme", filme);
			fService.alterarFilme(filme);
			return "VisualizarFilme";

		} catch (IOException e) {
			e.printStackTrace();
		}
		return "index";
	}
	@RequestMapping("/catalogo_popularidade")
	public String catalogoPopularidade(Model model) {
		try {
			ArrayList<Filme> lista;
			lista = fService.buscarFilmePopularidade();
			model.addAttribute("lista", lista);
			return "CatalogoPopularidade";
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "Erro";
	}
	
	@RequestMapping("/tela_alterar")
	public String telaAlterar(Filme filme, Model model, @RequestParam int id, HttpSession session) {
		try {
			ArrayList<Genero> generos = gService.listarGeneros();
			filme = fService.buscarFilme(filme.getId());
			model.addAttribute("filme", filme);
			model.addAttribute("generos", generos);
			session.setAttribute("generos", generos);
			return "AlterarFilme";
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "Erro";
	}
	
	
	@RequestMapping("/inserir_filme")
	public String inserirFilme(@Valid Filme filme, BindingResult result, Model model) {
		try {
			if(!result.hasFieldErrors("titulo")) {
				Genero genero = gService.buscarGenero(filme.getGenero().getId());
				filme.setGenero(genero);
				model.addAttribute("filme", filme);
				fService.inserirFilme(filme);
				return "VisualizarFilme";
			} else {
				return "CriarFilme";
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "index";
	}
	
	@RequestMapping("/visualizar_filme")
	public String visualizarFilme(Filme filme, Model model, @RequestParam int id) {
		try {
			filme = fService.buscarFilme(id);
			Genero genero = gService.buscarGenero(filme.getGenero().getId());
			filme.setGenero(genero);
			model.addAttribute("filme", filme);
			return "VisualizarFilme";
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "index";
	}
	
	@RequestMapping("/buscar_filmes")
	public String buscarFilmes(HttpSession session, @RequestParam String chave) {
		
		try {
			ArrayList<Filme> lista;
			
			if (chave != null && chave.length() > 0) {
				lista = fService.listarFilmes(chave);
			} else {
				lista = fService.listarFilmes();
			}
			session.setAttribute("lista", lista);
			return "ListarFilmes";
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "Erro";
	}

}

















