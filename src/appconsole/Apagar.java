/**********************************
 * IFPB - Curso Superior de Tec. em Sist. para Internet
 * POB - Persistencia de Objetos
 * Prof. Fausto Ayres
 *
 */

package appconsole;


import regras_negocio.Fachada;

public class Apagar {

	public Apagar() {
		try {
			Fachada.inicializar();
			Fachada.excluirPaciente("11111111111");		 
			System.out.println("paciente excluido");
			

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		Fachada.finalizar();
		System.out.println("\nFim do programa!");
	}

	public static void main(String[] args) {
		new Apagar();
	}
}