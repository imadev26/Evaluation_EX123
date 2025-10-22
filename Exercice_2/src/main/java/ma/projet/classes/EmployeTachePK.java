package ma.projet.classes;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class EmployeTachePK implements Serializable {
    private int employe_id;
    private int tache_id;
    
    public EmployeTachePK() {
    }
    
    public EmployeTachePK(int employe_id, int tache_id) {
        this.employe_id = employe_id;
        this.tache_id = tache_id;
    }
    
    public int getEmploye_id() {
        return employe_id;
    }
    
    public void setEmploye_id(int employe_id) {
        this.employe_id = employe_id;
    }
    
    public int getTache_id() {
        return tache_id;
    }
    
    public void setTache_id(int tache_id) {
        this.tache_id = tache_id;
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EmployeTachePK that = (EmployeTachePK) o;
        return employe_id == that.employe_id && tache_id == that.tache_id;
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(employe_id, tache_id);
    }
}

