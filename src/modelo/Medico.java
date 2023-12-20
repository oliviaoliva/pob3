package modelo;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Medico {
	
	 @Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)// Gera ID automaticamente
	 Integer id;
	 String crm;
	 String nome;
	 String especialidade;
	 @OneToMany(mappedBy = "medico")
	 List<Atendimento> lista = new ArrayList<Atendimento>();
	 
	 public Medico() {
		 
	 }

	public Medico(String nome, String especialidade, String crm) {
		this.nome = nome;
		this.especialidade = especialidade;
		this.crm = crm;
	}

	public String getNome() {
		return this.nome;
	}
	
	public void setNome(String nome) {
		this.nome=nome;
	}
	
	public void adicionar(Atendimento a){
		lista.add(a);
	}
	public String getCrm() {
		return crm;
	}

	public void setCrm(String crm) {
		this.crm = crm;
	}

	public String getEspecialidade() {
		return especialidade;
	}

	public void setEspecialidade(String especialidade) {
		this.especialidade = especialidade;
	}

	public void remover(Atendimento a) throws Exception {
		this.lista.remove(a);
}
	
	public List<Atendimento> getAtendimentos(){
		return this.lista;
	}
	@Override
	public String toString() {
		return "[nome=" + nome + ", crm=" + crm + ", especialidade= " + especialidade + ", lista=" + lista.toString() + "]";
	}
	
	public String ToStringPattern() {
		return String.format("M�dico[ Nome=%s ]", this.nome);
	}
}
