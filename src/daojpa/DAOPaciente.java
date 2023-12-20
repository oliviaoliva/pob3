/**********************************
 * IFPB - SI
 * POB - Persistencia de Objetos
 * Prof. Fausto Ayres
 **********************************/

package daojpa;

import java.util.List;

import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;
import modelo.Paciente;

public class DAOPaciente extends DAO<Paciente>{


	public Paciente read (Object chave){
		try{
			String cpf = (String) chave;
			cpf = cpf.toUpperCase();
			TypedQuery<Paciente> q = manager.createQuery("select p from Paciente p where p.cpf=:cpf",Paciente.class);
			q.setParameter("cpf", cpf);
			return q.getSingleResult();
			
		}catch(NoResultException e){
			return null;
		}
	}

	public List<Paciente> pacientesComMaisDeNAtendimentos() {
		TypedQuery<Paciente> q = manager.createQuery("select p from Paciente p JOIN FETCH p.lista a WHERE size(a) > 1", Paciente.class);
		return  q.getResultList();
	}


}

