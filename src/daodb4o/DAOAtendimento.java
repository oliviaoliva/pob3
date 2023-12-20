package daodb4o;

import java.util.List;
import com.db4o.query.Query;
import modelo.Atendimento;

public class DAOAtendimento extends DAO<Atendimento> {
    public Atendimento read(Object chave) {
        int id = (int) chave;
        Query q = manager.query();
        q.constrain(Atendimento.class);
        q.descend("id").constrain(id);
        
        List<Atendimento> atendimentos = q.execute();
        if (atendimentos.size() > 0) {
            return atendimentos.get(0);
        }else {
        	return null;
        }
    }
    
    public void create (Atendimento obj) {
    	int newId = super.gerarId();
    	obj.setId(newId);
    	manager.store(obj);
    }
    public List<Atendimento> atendimentosDataN(String data) {
    	Query q1 = manager.query();
		q1.constrain(Atendimento.class);  
		q1.descend("data").constrain(data);
		return q1.execute();
	}
    
    public List<Atendimento> atendimentosComMedicoDeEspecialidade(String especialidade) {
        Query q2 = manager.query();
        q2.constrain(Atendimento.class);
        q2.descend("medico").descend("especialidade").constrain(especialidade);
        return q2.execute();
    }

}

