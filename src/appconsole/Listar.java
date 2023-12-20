/**********************************
 * IFPB - Curso Superior de Tec. em Sist. para Internet
 * POB - Persistencia de Objetos
 * Prof. Fausto Ayres
 *
 */

package appconsole;

import daodb4o.Util;
import regras_negocio.Fachada;
import modelo.*;

public class Listar {

	public Listar() {
		try {
			Fachada.inicializar();
			System.out.println("\n---listagem de Atendimentos:");
			for(Atendimento a: Fachada.listarAtendimentos())
				System.out.println(a);

			System.out.println("\n---listagem de Pacientes:");
			for(Paciente p: Fachada.listarPacientes())
				System.out.println(p);
			
			System.out.println("\n---listagem de Medicos:");
			for(Medico ml: Fachada.listarMedicos())
				System.out.println(ml);
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		Util.desconectar();
		System.out.println("\nfim do programa !");
	}

	public static void main(String[] args) {
		new Listar();
	}
}