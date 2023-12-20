/**********************************
 * IFPB - SI
 * POB - Persistencia de Objetos
 * Prof. Fausto Ayres
 **********************************/
package daojpa;

import java.util.List;

import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;
import modelo.Medico;

public class DAOMedico extends DAO<Medico>{

	public Medico read (Object chave){
		try{
			String nome = (String) chave;
			TypedQuery<Medico> q = manager.createQuery("select a from Medico a where a.crm=:crm", Medico.class);
			q.setParameter("crm", nome);
			return q.getSingleResult();

		}catch(NoResultException e){
			return null;
		}
	}

	//  //pode-se sobrescrever o metodo readAll da classe DAO para ordenar o resultado 
	public List<Medico> readAll(){
		TypedQuery<Medico> q = manager.createQuery("select a from Medico a order by a.nome", Medico.class);
		return  q.getResultList();
	}

}

