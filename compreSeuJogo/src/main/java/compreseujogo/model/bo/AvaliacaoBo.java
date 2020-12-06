package compreseujogo.model.bo;

import java.time.LocalDate;
import java.util.List;

import compreseujogo.model.dao.AvaliacaoDao;
import compreseujogo.model.dao.GenericDao;
import compreseujogo.model.entity.Avaliacao;

public class AvaliacaoBo {

	public String saveOrUpdate(Avaliacao avaliacao) throws Exception {
		validarDados(avaliacao);
		GenericDao<Avaliacao> tcDao = new GenericDao<Avaliacao>();
		avaliacao.setData(LocalDate.now());
		try {
			return tcDao.saveOrUpdate(avaliacao);
			
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}

	public String remove(Avaliacao avaliacao) throws Exception {
		GenericDao<Avaliacao> tcDao = new GenericDao<Avaliacao>();
		try {
			return tcDao.remove(Avaliacao.class, avaliacao.getId());
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}

	public List<Avaliacao> list(String parameter, Avaliacao avaliacao) throws Exception {
		try {
			if (parameter.equals("")) {
				GenericDao<Avaliacao> tcDao = new GenericDao<Avaliacao>();
				return tcDao.list(Avaliacao.class);
			} else {
				AvaliacaoDao avaliacaoDao = new AvaliacaoDao();
				return avaliacaoDao.list(parameter, avaliacao);
			}
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}

	public int calcularNota(Avaliacao avaliacao) throws Exception {
		try {
			double total = list("", null).stream().filter(a -> a.getProduto().equals(avaliacao.getProduto()))
					.mapToInt(Avaliacao::getPontos).average().getAsDouble();
			return ((total > 4) ? 5 : (total > 3) ? 4 : (total > 2) ? 3 : (total > 1) ? 2 : 1);
		} catch (Exception e) {
			throw new Exception("Erro ao calcular a nota do produto");
		}

	}
	

	private void validarDados(Avaliacao avaliacao) throws Exception {
		if (avaliacao.getId() < 0) {
			throw new Exception("Id n�o pode ser negativo!");
		} else if (avaliacao.getTitulo().equals("")) {
			throw new Exception("O t�tulo n�o pode ficar em branco");
		} else if (avaliacao.getDescricao().equals("")) {
			throw new Exception("O coment�rio n�o pode ficar em branco");
		} else if (avaliacao.getPontos() < 0) {
			throw new Exception("Os pontos n�o pode ser negativo");
		}
	}
}
