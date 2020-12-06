package compreseujogo.model.entity;

public enum Estado {

	AC ("Acre"),
	AL ("Alagoas"),
	AM ("Amazonas"),
	AP ("Amapá"),
	BA ("Bahia"),
	CE ("Ceará"),
	DF ("Distrito Federal"),
	ES ("Espirito Santo"),
	GO ("Goiás"),
	MA ("Maranhão"),
	MG ("Minas Gerais"),
	MS ("Mato Grosso do Sul"),
	MT ("Mato Grosso"),
	PA ("Pará"),
	PB ("Paraíba"),
	PE ("Pernambuco"),
	PI ("Piauí"),
	PR ("Paraná"),
	RJ ("Rio de Janeiro"),
	RN ("Rio Grande do Norte"),
	RO ("Rondônia"),
	RR ("Roraima"),
	RS ("Rio Grande do Sul"),
	SC ("Santa Catarina"),
	SE ("Sergipe"),
	SP ("São Paulo"),
	TO ("Tocantis");

	private String abreviacao;
	
	public String getAbreviacao() {
		return abreviacao;
	}

	public void setAbreviacao(String abreviacao) {
		this.abreviacao = abreviacao;
	}

	private Estado(String abreviacao) {
		this.abreviacao = abreviacao;
	}
	
}
