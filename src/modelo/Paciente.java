package modelo;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Paciente {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	String cpf;
	String nome;
	@OneToMany(mappedBy = "paciente")
	List<Atendimento> lista = new ArrayList<>();

	public Paciente() {	

	}

	public Paciente(String nome, String cpf) {
		this.nome = nome;
		this.cpf = cpf;
	}

	public String getCPF() {
		return cpf;
	}

	public void setCPF(String cpf) {
		this.cpf = cpf;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String Nome) {
		this.nome = Nome;
	}

	public void adicionar(Atendimento a) {
		this.lista.add(a);
	}

	public void remover(Atendimento atendimento) throws Exception {
		lista.remove(atendimento);
	}

	public List<Atendimento> getAtendimentos() {
		return this.lista;
	}

	public void SetAtendimentos(ArrayList<Atendimento> lista) {
		this.lista = lista;
	}

	@Override
	public String toString() {
		return "[CPF=" + cpf + ", Nome=" + nome + ", lista=" + lista.toString() + "]";
	}

	public String ToStringPattern() {
		return String.format("Paciente[ Nome=%s ]", this.nome);
	}
}