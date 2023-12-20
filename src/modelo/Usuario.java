package modelo;

import daojpa.LowerToUpperConverter;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Usuario {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Convert(converter = LowerToUpperConverter.class)
	private String nome;
	private String senha;
	
	public Usuario() {
		
	}
	
	public Usuario(String nome, String senha) {
		this.nome = nome;
		this.senha = senha;
	}
	
	public String getNome() {
		return this.nome;
	}
	
	public void setNome(String novoNome) {
		this.nome = novoNome;
	}
	
	public String getSenha() {
		return this.senha;
	}
	
	public void setSenha(String novaSenha) {
		this.senha = novaSenha;
	}
	
	@Override
	public String toString() {
		return "Usuario [nome = " + nome + "]" + "[senha = " + senha + "]";
	}
}