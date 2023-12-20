package daojpa;

import java.util.List;

import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;
import modelo.Atendimento;

public class DAOAtendimento extends DAO<Atendimento> {
    public Atendimento read(Object chave) {
    	try{
			Integer id = (Integer) chave;
			TypedQuery<Atendimento> q = manager.createQuery("select a from Atendimento a where a.id=:id",Atendimento.class);
			q.setParameter("id", id);
			return q.getSingleResult();
			
		}catch(NoResultException e){
			return null;
		}
    }
    
    public List<Atendimento> atendimentosDataN(String data) {
    	TypedQuery<Atendimento> q = manager.createQuery("select a from Atendimento a WHERE a.data = :data order by a.id", Atendimento.class);
    	q.setParameter("data", data);
		return  q.getResultList();
	}
    
    public List<Atendimento> atendimentosComMedicoDeEspecialidade(String especialidade) {
      	TypedQuery<Atendimento> q = manager.createQuery("select a from Atendimento a WHERE a.medico.especialidade = :especialidade order by a.id", Atendimento.class);
    	q.setParameter("especialidade", especialidade);
		return  q.getResultList();
    }

}

