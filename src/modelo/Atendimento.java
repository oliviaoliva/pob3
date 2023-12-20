package modelo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Atendimento {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int id;
	String data;
	@ManyToOne
	Paciente paciente;
	@ManyToOne
	Medico medico;

	public Atendimento() {

	}

	public Atendimento(String data, Paciente paciente, Medico medico) {
		this.data = data;
		this.paciente = paciente;
		paciente.adicionar(this);
		this.medico = medico;
		medico.adicionar(this);
	}

	@Override
	public String toString() {
		return "[id=" + id + ", data=" + data + ", paciente=" + paciente.ToStringPattern() + ", medico="
				+ medico.ToStringPattern() + "]";
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public Paciente getPaciente() {
		return paciente;
	}

	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
		paciente.adicionar(this);
	}

	public Medico getMedico() {
		return medico;
	}

	public void setMedico(Medico medico) {
		this.medico = medico;
	}

}
