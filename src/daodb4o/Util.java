package daodb4o;

import java.util.List;

import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;
import com.db4o.config.EmbeddedConfiguration;
import com.db4o.query.Query;

import modelo.Atendimento;
import modelo.Medico;
import modelo.Paciente;

public class Util {
	private static ObjectContainer manager;
	
	public static ObjectContainer conectarBanco(){
		if (manager != null)
			return manager;		
		
		//---------------------------------------------------------------
		//Configurar, criar e conectar banco local na pasta do projeto
		//---------------------------------------------------------------
		
		EmbeddedConfiguration config =  Db4oEmbedded.newConfiguration(); 
		config.common().messageLevel(0);  
		
		// Cascata de remocao, alteracao e atualizacao
		config.common().objectClass(Medico.class).cascadeOnDelete(false);
		config.common().objectClass(Medico.class).cascadeOnUpdate(true);
		config.common().objectClass(Medico.class).cascadeOnActivate(true);
		
		config.common().objectClass(Atendimento.class).cascadeOnDelete(false);
		config.common().objectClass(Atendimento.class).cascadeOnUpdate(true);
		config.common().objectClass(Atendimento.class).cascadeOnActivate(true);
		
		config.common().objectClass(Paciente.class).cascadeOnDelete(false);
		config.common().objectClass(Paciente.class).cascadeOnUpdate(true);
		config.common().objectClass(Paciente.class).cascadeOnActivate(true);
		
		manager = Db4oEmbedded.openFile(config, "banco.db4o");
		return manager;
	}
	
	public static void desconectar() {
		if(manager!=null) {
			manager.close();
			manager=null;
		}
	}
	
	
	public static int gerarIdAtendimento() {
		if(manager.query(Atendimento.class).size()==0) 
			return 1;
		
		Query q = manager.query();
		q.constrain(Atendimento.class);
		q.descend("id").orderDescending();
		List<Atendimento> resultados = q.execute();

		if(resultados.size()>0) {
			Atendimento atendimento = resultados.get(0);    
			return atendimento.getId() + 1;
		}
		else
			return 1; 
	}

	public static ObjectContainer conectarDb4oLocal() {
		// TODO Auto-generated method stub
		return null;
	}

}