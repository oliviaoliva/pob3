package appconsole;

import regras_negocio.Fachada;

public class Cadastrar {
	
	public Cadastrar() {
		try {
			Fachada.inicializar();
			System.out.println("cadastrando medico...");
			Fachada.CriarMedico("Fausto", "Cardiologia", "001");
			Fachada.CriarMedico("Olivia", "Odontologia", "002");
			Fachada.CriarMedico("Kamila", "Pediatria", "003");
			Fachada.CriarMedico("Darla", "Pediatria", "004");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		try {
			System.out.println("cadastrando paciente...");
			Fachada.cadastrarPaciente("Leticia", "11111111111");
			Fachada.cadastrarPaciente("Artur", "22222222222");
			Fachada.cadastrarPaciente("Caio", "33333333333");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		try {
			System.out.println("cadastrando atendimento...");
			Fachada.CriarAtendimento("01/09/2023","11111111111" , "001");
			Fachada.CriarAtendimento("03/09/2023","22222222222","002");
			Fachada.CriarAtendimento("02/09/2023","33333333333","003");
			Fachada.CriarAtendimento("03/09/2023","33333333333","004");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		Fachada.finalizar();
		System.out.println("\nfim do programa !");
	}


	public static void main(String[] args) {
		new Cadastrar();
	}
}

