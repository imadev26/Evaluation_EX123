package ma.projet.beans;

import java.io.Serializable;
import javax.persistence.Embeddable;

@Embeddable
public class MariagePK implements Serializable {
    
    private int homme;
    private int femme;
    
    public MariagePK() {
    }
    
    public MariagePK(int homme, int femme) {
        this.homme = homme;
        this.femme = femme;
    }
    
    public int getHomme() {
        return homme;
    }
    
    public void setHomme(int homme) {
        this.homme = homme;
    }
    
    public int getFemme() {
        return femme;
    }
    
    public void setFemme(int femme) {
        this.femme = femme;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        MariagePK that = (MariagePK) obj;
        return homme == that.homme && femme == that.femme;
    }
    
    @Override
    public int hashCode() {
        int result = homme;
        result = 31 * result + femme;
        return result;
    }
}

