/**********************************
 * IFPB - Curso Superior de Tec. em Sist. para Internet
 * POB - Persistencia de Objetos
 * Prof. Fausto Ayres
 *
 */

package appconsole;
import modelo.Atendimento;
import modelo.Paciente;
import regras_negocio.Fachada;

public class Consultar {

	public Consultar() {
		try {
			Fachada.inicializar();

			System.out.println("Consultas... \n");
			System.out.println("Consultar Pacientes com mais de 1 Atendimento agendados!");
			for(Paciente p : Fachada.pacientesComMaisDeNAtendimentos())
				System.out.println(p);

			System.out.println("Consultar Medicos de especialidade: Pediatria");
			for(Atendimento a : Fachada.atendimentosComMedicoDeEspecialidade("Pediatria"))
				System.out.println(a);


			System.out.println("Consultar Atendimentos marcados na data de 01/09/2023");
			for(Atendimento at : Fachada.atendimentosDataN("01/09/2023"))
				System.out.println(at);


		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		Fachada.finalizar();
		System.out.println("\nFim do programa!");
	}

	public static void main(String[] args) {
		new Consultar();
	}
}

