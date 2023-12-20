package daodb4o;

import java.util.List;

import com.db4o.query.Candidate;
import com.db4o.query.Evaluation;
import com.db4o.query.Query;

import modelo.Paciente;


public class DAOPaciente extends DAO<Paciente> {

    public Paciente read(Object chave) {
        String cpf = (String) chave;
        Query q = manager.query();
        q.constrain(Paciente.class);
        q.descend("cpf").constrain(cpf);
        List<Paciente> pacientes = q.execute();
        if (pacientes.size() > 0) {
            return pacientes.get(0);
        } else {
        	return null;
        }
    }
    
    public void create(Paciente obj) {
    	manager.store(obj);
    }
    
    public List<Paciente> pacientesComMaisDeNAtendimentos() {
        Query q = manager.query();
        q.constrain(Paciente.class);
        q.constrain(new FiltroPacientes());
        
        List<Paciente> pacientes =q.execute();
        return pacientes;
    }
    @SuppressWarnings("serial")
	private  class FiltroPacientes implements Evaluation {
		public void evaluate(Candidate candidate) {
			Paciente p = (Paciente) candidate.getObject(); 
			
			if(p.getAtendimentos().size()>1) 
				candidate.include(true); 	
			else		
				candidate.include(false);	
		}
    }
}


       


