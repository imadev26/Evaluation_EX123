package ma.projet.beans;

import java.util.Date;
import java.util.List;
import javax.persistence.*;

@Entity
@Table(name = "homme")
public class Homme extends Personne {
    
    @OneToMany(mappedBy = "homme", fetch = FetchType.EAGER)
    private List<Mariage> mariages;
    
    public Homme() {
        super();
    }
    
    public Homme(String nom, String prenom, String telephone, String adresse, Date dateNaissance) {
        super(nom, prenom, telephone, adresse, dateNaissance);
    }
    
    public List<Mariage> getMariages() {
        return mariages;
    }
    
    public void setMariages(List<Mariage> mariages) {
        this.mariages = mariages;
    }
}

