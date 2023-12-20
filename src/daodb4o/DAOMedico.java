package daodb4o;

import com.db4o.query.Query;
import java.util.List;
import modelo.Medico;

public class DAOMedico extends DAO<Medico> {

    public Medico read(Object chave) {
        String crm = (String) chave;
        Query q = manager.query();
        q.constrain(Medico.class);
        q.descend("crm").constrain(crm);
        
        List<Medico> medicos = q.execute();
        if (medicos.size() > 0) {
            return medicos.get(0);
        } else {
        return null;
        }
    }
    public void create(Medico obj) {
    	manager.store(obj);
    }
}
