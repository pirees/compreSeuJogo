package compreseujogo.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.Part;

import compreseujogo.facade.Facade;
import compreseujogo.model.entity.Categoria;
import compreseujogo.model.entity.Fornecedor;
import compreseujogo.model.entity.Marca;
import compreseujogo.model.entity.Plataforma;
import compreseujogo.model.entity.Produto;

@RequestScoped
@ManagedBean(name = "produtoBean")
public class ProdutoController implements Serializable {

	private static final long serialVersionUID = 1L;

	private Produto produto;
	private List<Produto> lista;
	private List<Categoria> categorias;
	private List<Fornecedor> fornecedores;
	private List<Marca> marcas;
	private List<Plataforma> plataformas;
	private Part arquivo;
	private String filtro;
	private String destino;

	public ProdutoController() {
		this.produto = new Produto();
		this.lista = new ArrayList<Produto>();
		this.categorias = new ArrayList<Categoria>();
		this.fornecedores = new ArrayList<Fornecedor>();
		this.marcas = new ArrayList<Marca>();
		this.plataformas = new ArrayList<Plataforma>();
		this.filtro = "";
		// this.destino =
		// "C:\\\\temp\\\\WS-eclipse\\\\compreseujogo_3.0\\\\src\\\\main\\\\webapp\\\\resources\\\\imagem\\\\";
		this.destino = "C:\\Users\\leona\\git\\compreseujogo_3.0\\src\\main\\webapp\\resources\\imagem\\";
	}

	@PostConstruct
	public void carregar() {
		FacesContext context = FacesContext.getCurrentInstance();
		Facade facade = new Facade();
		try {
			lista = facade.listaProduto("", "", produto);
		} catch (Exception e) {
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), ""));
		}
	}

	public void salvar() {
		FacesContext context = FacesContext.getCurrentInstance();
		Facade facade = new Facade();
		try {

			importa();
			facade.salvarProduto(this.produto);
			context.addMessage(null,
					new FacesMessage("O " + produto.getNome() + " foi salvo com sucesso", FacesMessage.FACES_MESSAGES));
			produto = new Produto();
		} catch (Exception e) {
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), ""));
		}

	}

	public String telaPesquisa() {
		return "pesquisaProduto.xhtml?p=" + filtro + "&faces-redirect=true";
	}
	
	public String telaPesquisaPlay() {
		this.filtro = "PLAYSTATION 5";
		return "pesquisaProduto.xhtml?p=" + this.filtro + "&faces-redirect=true";
	}
	
	public String telaPesquisaPlay4() {
		this.filtro = "PLAYSTATION 4";
		return "pesquisaProduto.xhtml?p=" + this.filtro + "&faces-redirect=true";
	}
	
	public String telaPesquisaPlay3() {
		this.filtro = "PLAYSTATION 3";
		return "pesquisaProduto.xhtml?p=" + this.filtro + "&faces-redirect=true";
	}
	
	public String telaPesquisaXBoxOne() {
		this.filtro = "XBOX ONE";
		return "pesquisaProduto.xhtml?p=" + this.filtro + "&faces-redirect=true";
	}
	
	public String telaPesquisaXBox360() {
		this.filtro = "XBOX 360";
		return "pesquisaProduto.xhtml?p=" + this.filtro + "&faces-redirect=true";
	}
	
	public String telaPesquisaNintendo() {
		this.filtro = "NINTENDO";
		return "pesquisaProduto.xhtml?p=" + this.filtro + "&faces-redirect=true";
	}

	public String selecionar(Produto produto) {
		return "visualizarProduto.xhtml?id=" + produto.getId() + "&faces-redirect=true";
	}

	public List<Produto> buscarMenu(String filtro) {
		FacesContext context = FacesContext.getCurrentInstance();
		Facade facade = new Facade();
		try {
			return facade.listaProduto("pesquisa", filtro, null);
		} catch (Exception e) {
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), ""));
		}
		return null;
	}

	public List<Produto> buscar() {
		FacesContext context = FacesContext.getCurrentInstance();
		Facade facade = new Facade();
		try {
			return facade.listaProduto("pesquisa", filtro, null);
		} catch (Exception e) {
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), ""));
		}
		return null;
	}

	public List<Produto> carouselVP() {
		FacesContext context = FacesContext.getCurrentInstance();
		Facade facade = new Facade();
		try {
			return facade.listaProduto("", "carousel", this.produto);
		} catch (Exception e) {
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), ""));
		}
		return null;
	}

	public void encontrar() {
		Facade facade = new Facade();
		this.produto = facade.encontrarProduto(this.produto.getId());
	}

	public List<String> complete12(String busca) {
		List<String> resultados = new ArrayList<String>();
		for (Produto produtos : lista) {
			if (produtos.getNome().toUpperCase().contains(busca.toUpperCase())) {
				resultados.add(produtos.getNome());
			}
		}
		return resultados;
	}

	public List<Produto> complete(String query) {
		FacesContext context = FacesContext.getCurrentInstance();
		Facade facade = new Facade();
		String queryLowerCase = query.toLowerCase();
		List<Produto> allThemes = null;
		try {
			produto.setNome(queryLowerCase);
			allThemes = facade.listaProduto("", "", produto);
		} catch (Exception e) {
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), ""));
		}
		return allThemes.stream().filter(t -> t.getNome().toLowerCase().contains(queryLowerCase))
				.collect(Collectors.toList());
	}

	public void importa() {
		FacesContext context = FacesContext.getCurrentInstance();

		String conteudo = getFileName(arquivo.getName() + ".jpg");
		produto.setImagem(conteudo);
		try {
			copyFile(conteudo, arquivo.getInputStream());
		} catch (IOException e) {
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), ""));
		}
	}

	public void copyFile(String fileName, InputStream in) {
		try {
			// write the inputStream to a FileOutputStream
			OutputStream out = new FileOutputStream(new File(destino + fileName));
			int read = 0;
			byte[] bytes = new byte[1024];

			while ((read = in.read(bytes)) != -1) {
				out.write(bytes, 0, read);
			}

			in.close();
			out.flush();
			out.close();

			System.out.println("Novo arquivo criado '" + fileName + "'!");
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}

	public String getExtensao(String nomeArquivo) {
		int posicaoPonto = nomeArquivo.lastIndexOf(".");
		int tamanhoString = nomeArquivo.length();
		return nomeArquivo.substring(posicaoPonto + 1, tamanhoString);
	}

	public String getFileName(String nomeArquivo) {
		String data = "yyyy-MM-dd";
		String hora = "HH-mm-ss-SSS";
		String data1, hora1;

		java.util.Date agora = new java.util.Date();
		SimpleDateFormat formata = new SimpleDateFormat(data);
		data1 = formata.format(agora);
		formata = new SimpleDateFormat(hora);
		hora1 = formata.format(agora);
		return data1 + "-" + hora1 + "." + getExtensao(nomeArquivo);
	}

	public String crud() {
		return "listaProduto?faces-redirect=true";
	}

	public String alterar(Produto produto) {
		this.produto = produto;
		return "atualizarProduto.xhtml";
	}

	public String carregar(Produto produto) {
		this.produto = produto;
		return "carrinhoCliente.xhtml";
	}

	public void excluir(Produto produto) {
		this.produto = produto;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public List<Produto> getLista() {
		return lista;
	}

	public void setLista(List<Produto> lista) {
		this.lista = lista;
	}

	public List<Categoria> getCategorias() {
		FacesContext context = FacesContext.getCurrentInstance();
		Facade facade = new Facade();
		try {
			categorias = facade.listaCategoria(null);
		} catch (Exception e) {
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), ""));
		}
		return categorias;
	}

	public void setCategorias(List<Categoria> categorias) {
		this.categorias = categorias;
	}

	public List<Fornecedor> getFornecedores() {
		FacesContext context = FacesContext.getCurrentInstance();
		Facade facade = new Facade();
		try {
			fornecedores = facade.listarFornecedorNome(null);
		} catch (Exception e) {
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), ""));
		}
		return fornecedores;
	}

	public void setFornecedores(List<Fornecedor> fornecedores) {
		this.fornecedores = fornecedores;
	}

	public List<Marca> getMarcas() {
		FacesContext context = FacesContext.getCurrentInstance();
		Facade facade = new Facade();
		try {
			marcas = facade.listaMarca(null);
		} catch (Exception e) {
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), ""));
		}
		return marcas;
	}

	public void setMarcas(List<Marca> marcas) {
		this.marcas = marcas;
	}

	public List<Plataforma> getPlataformas() {
		FacesContext context = FacesContext.getCurrentInstance();
		Facade facade = new Facade();
		try {
			plataformas = facade.listaPlataforma(null);
		} catch (Exception e) {
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), ""));
		}
		return plataformas;
	}

	public void setPlataformas(List<Plataforma> plataformas) {
		this.plataformas = plataformas;
	}

	public Part getArquivo() {
		return arquivo;
	}

	public void setArquivo(Part arquivo) {
		this.arquivo = arquivo;
	}

	public String getFiltro() {
		return filtro;
	}

	public void setFiltro(String filtro) {
		this.filtro = filtro;
	}

}
