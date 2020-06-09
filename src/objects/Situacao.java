package objects;

public class Situacao {
	
	private String pais;
	private long casosConfirmados,casosDiarios,mortes,mortesDiarias,recuperados,
		emTratamento,estadoCritico,casosPorUmMilhao,mortesPorUmMilhao,testesFeitos,
		testesPorUmMilhao;
	
	public Situacao() {
		
	}

	public String getPais() {
		return pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}

	public long getCasosConfirmados() {
		return casosConfirmados;
	}

	public void setCasosConfirmados(long casosConfirmados) {
		this.casosConfirmados = casosConfirmados;
	}

	public long getCasosDiarios() {
		return casosDiarios;
	}

	public void setCasosDiarios(long casosDiarios) {
		this.casosDiarios = casosDiarios;
	}

	public long getMortes() {
		return mortes;
	}

	public void setMortes(long mortes) {
		this.mortes = mortes;
	}

	public long getMortesDiarias() {
		return mortesDiarias;
	}

	public void setMortesDiarias(long mortesDiarias) {
		this.mortesDiarias = mortesDiarias;
	}

	public long getRecuperados() {
		return recuperados;
	}

	public void setRecuperados(long recuperados) {
		this.recuperados = recuperados;
	}

	public long getEmTratamento() {
		return emTratamento;
	}

	public void setEmTratamento(long emTratamento) {
		this.emTratamento = emTratamento;
	}

	public long getEstadoCritico() {
		return estadoCritico;
	}

	public void setEstadoCritico(long estadoCritico) {
		this.estadoCritico = estadoCritico;
	}

	public long getCasosPorUmMilhao() {
		return casosPorUmMilhao;
	}

	public void setCasosPorUmMilhao(long casosPorUmMilhao) {
		this.casosPorUmMilhao = casosPorUmMilhao;
	}

	public long getMortesPorUmMilhao() {
		return mortesPorUmMilhao;
	}

	public void setMortesPorUmMilhao(long mortesPorUmMilhao) {
		this.mortesPorUmMilhao = mortesPorUmMilhao;
	}

	public long getTestesFeitos() {
		return testesFeitos;
	}

	public void setTestesFeitos(long testesFeitos) {
		this.testesFeitos = testesFeitos;
	}

	public long getTestesPorUmMilhao() {
		return testesPorUmMilhao;
	}

	public void setTestesPorUmMilhao(long testesPorUmMilhao) {
		this.testesPorUmMilhao = testesPorUmMilhao;
	}
}
