package regras_negocio;
/**********************************
 * IFPB - Curso Superior de Tec. em Sist. para Internet
 * POB - Persistencia de Objetos
 * Prof. Fausto Ayres
 *
 */

import java.util.List;

import daojpa.DAOAtendimento;
import daojpa.DAOUsuario;
import daojpa.DAO;
import daojpa.DAOMedico;
import daojpa.DAOPaciente;
import modelo.Atendimento;
import modelo.Medico;
import modelo.Paciente;
import modelo.Usuario;

public class Fachada {
	private Fachada() {}
	private static DAOUsuario daousuario = new DAOUsuario();
	private static DAOMedico daomedico = new DAOMedico();
	private static DAOPaciente daopaciente = new DAOPaciente();
	private static DAOAtendimento daoatendimento = new DAOAtendimento();
	public static Usuario logado;	

	public static void inicializar(){
		DAO.open();
	}
	public static void finalizar(){
		DAO.close();
	}


	public static Paciente cadastrarPaciente(String nome, String cpf) throws Exception{
		DAO.begin();
		Paciente paciente = daopaciente.read(cpf);
		if (paciente!=null) {
			throw new Exception("paciente ja cadastrado:" + nome);
		}
		paciente = new Paciente(nome, cpf);

		daopaciente.create(paciente);
		DAO.commit();
		return paciente;
	}

	public static Atendimento CriarAtendimento(String data, String cpf, String crm) throws Exception{
		DAO.begin();
		Paciente paciente= daopaciente.read(cpf);
		Medico medico= daomedico.read(crm);
		if(paciente==null) 
			throw new Exception ("paciente n�o existe no sistema!");
		if(medico==null) 
			throw new Exception ("M�dico n�o existe no sistema!");
		if(!paciente.getAtendimentos().isEmpty()) {
		for(Atendimento atendimento: paciente.getAtendimentos()) {
			if(atendimento!=null) {
			if(atendimento.getData().equals(data)) {
				throw new Exception("Paciente n�o pode ter mais de 1 consulta no dia");
					}
				}
			}
		}
		Atendimento atendimento = new Atendimento(data, paciente, medico);
		medico.adicionar(atendimento);;
		paciente.adicionar(atendimento);

		daoatendimento.create(atendimento);
		daopaciente.update(paciente);
		daomedico.update(medico);
		DAO.commit();
		return atendimento;
	} //paciente so pode ter 1 consulta por dia

	public static void CancelarAtendimento(int id) throws Exception{
		DAO.begin();
		Atendimento atendimento =  daoatendimento.read(id);
		if(atendimento==null) 
			throw new Exception ("Atendimento n�o existe para ser cancelado!");
		if(atendimento.getPaciente()==null) 
			throw new Exception ("Atendimento n�o possui atendimento relacionado");
		if(atendimento.getMedico()==null) 
			throw new Exception ("Atendimento n�o possui Medico relacionado");
	
		Paciente paciente = daopaciente.read(atendimento.getPaciente().getCPF());
		Medico Medico= daomedico.read(atendimento.getMedico().getNome());
		
		paciente.remover(atendimento);
		Medico.remover(atendimento);
		
		
		daoatendimento.delete(atendimento);
		daopaciente.update(paciente);
		daomedico.update(Medico);
		DAO.commit();
	}

	public static void excluirPaciente(String CPF) throws Exception{
		DAO.begin();
		Paciente paciente =daopaciente.read(CPF);
		if(paciente==null) 
			throw new Exception ("Paciente desse cpf requerido n�o existe :" + CPF);
		
		if(paciente.getAtendimentos().isEmpty()) { 
			daopaciente.delete(paciente);
			DAO.commit();
		}
		else {
			for(Atendimento atendimento : paciente.getAtendimentos()) {
				
				   daoatendimento.delete(atendimento);
				   atendimento.getMedico().remover(atendimento);
				   daomedico.update(atendimento.getMedico());
				   daoatendimento.update(atendimento);
			}
			daopaciente.delete(paciente);
			DAO.commit();
		}
	}
	
	
	public static void RemoverdoMedico(Medico Medico ,int id) {
		try{
			Atendimento atendimento= daoatendimento.read(id);
			Medico.remover(atendimento);
			daomedico.update(Medico);
			}
		catch(Exception e){
			System.out.println(e.getMessage());
		}
		
	}

	public static Atendimento MudarPaciente(int id,String cpf) throws Exception{
		DAO.begin();
		Atendimento atendimento = (Atendimento) daoatendimento.read(id);
		Paciente paciente = (Paciente) daopaciente.read(cpf);
		if (atendimento==null)
			throw new Exception("O Atendimento  n�o existe : " + id);
		if(paciente==null) 
			throw new Exception("O Paciente n�o existe!" );
		if(atendimento.getPaciente().equals(paciente))
			throw new Exception("O paciente j� �: "+ paciente.getNome());
		
		try {
		Paciente pacienteanterior = atendimento.getPaciente();
		atendimento.setPaciente(paciente);
		if(!paciente.getAtendimentos().isEmpty()) {
		pacienteanterior.remover(atendimento);
		daopaciente.update(pacienteanterior);
		}
		
		paciente.adicionar(atendimento);
		daopaciente.update(paciente);
		daoatendimento.update(atendimento);
		DAO.commit();
		}
		catch(Exception e){
			System.out.println(e.getMessage());
		}
		
		return atendimento;
	}
	
	public static Medico CriarMedico(String nome, String especialidade, String crm) throws Exception{
		DAO.begin();
		Medico Medico = daomedico.read(nome);
		
		if (Medico == null) {
			Medico = new Medico(nome, especialidade, crm);
			daomedico.create(Medico);
		} else {
			throw new Exception ("Medico j� criado: " + nome);
		}
		
		DAO.commit();
		return Medico;
	}

	public static void excluirMedico(String crm, String nome) throws Exception{
		DAO.begin();
		Medico Medico= daomedico.read(crm);
		if(Medico==null)
			throw new Exception("Medico n�o existe :" + nome);
		
		for(Atendimento atendimento : Medico.getAtendimentos() ) {
			daoatendimento.delete(atendimento);
			atendimento.getPaciente().remover(atendimento);
			daopaciente.update(atendimento.getPaciente());
			daoatendimento.update(atendimento);
		}
		daomedico.delete(Medico);
		DAO.commit();
	}

	public static List<Medico>  listarMedicos(){
		DAO.begin();
		List<Medico> resultados=  daomedico.readAll();
		DAO.commit();
		return resultados;
	} 

	public static List<Paciente>  listarPacientes(){
		DAO.begin();
		List<Paciente> resultados =  daopaciente.readAll();
		DAO.commit();
		return resultados;
	}

	public static List<Atendimento> listarAtendimentos(){
		DAO.begin();
		List<Atendimento> resultados =  daoatendimento.readAll();
		DAO.commit();
		return resultados;
	}

	public static List<Usuario>  listarUsuarios(){
		DAO.begin();
		List<Usuario> resultados =  daousuario.readAll();
		DAO.commit();
		return resultados;
	} 

	/////////////// CONSULTAS ESPECIFICAS ////////////////
	
	public static List<Paciente> pacientesComMaisDeNAtendimentos(){ //pacientes com nenhum atendimento agendado
		DAO.begin();
		List<Paciente> pacientes =daopaciente.pacientesComMaisDeNAtendimentos();
		DAO.commit();
		return pacientes;
	}
	
	
	public static List<Atendimento> atendimentosComMedicoDeEspecialidade(String especialidade){
		DAO.begin();
		List<Atendimento> atendimentos= daoatendimento.atendimentosComMedicoDeEspecialidade(especialidade);
		DAO.commit();
		return atendimentos;
	}
	
	public static List<Atendimento> atendimentosDataN(String data){
		DAO.begin();
		List<Atendimento> atendimentos= daoatendimento.atendimentosDataN(data);
		DAO.commit();
		return atendimentos;
	}
	
	
	////////////////////////////////////////////////////////////////////////////

	public static Paciente localizarPaciente(String cpf){
		return daopaciente.read(cpf);
	}
	public static Atendimento localizarAtendimento(int id){
		return daoatendimento.read(id);
	}
	public static Medico localizarMedico(String crm) {
		return daomedico.read(crm);
	}
	
	
	//---------------Usuario---------------
	public static Usuario cadastrarUsuario(String nome, String senha) throws Exception{
		DAO.begin();
		Usuario usu = daousuario.read(nome);
		if (usu!=null)
			throw new Exception("Usuario ja cadastrado:" + nome);
		usu = new Usuario(nome, senha);

		daousuario.create(usu);
		DAO.commit();
		return usu;
	}
	public static Usuario localizarUsuario(String nome, String senha) {
		Usuario usu = daousuario.read(nome);
		if (usu==null)
			return null;
		if (! usu.getSenha().equals(senha))
			return null;
		return usu;
	}
}
