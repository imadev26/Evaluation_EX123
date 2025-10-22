package ma.projet.beans;

import java.util.Date;
import java.util.List;
import javax.persistence.*;

@Entity
@Table(name = "femme")
@NamedQueries({
    @NamedQuery(
        name = "Femme.findMarriedTwiceOrMore",
        query = "SELECT f FROM Femme f WHERE (SELECT COUNT(m) FROM Mariage m WHERE m.femme = f) >= 2"
    )
})
@NamedNativeQueries({
    @NamedNativeQuery(
        name = "Femme.countChildrenBetweenDates",
        query = "SELECT COALESCE(SUM(m.nbrEnfant), 0) FROM mariage m " +
                "WHERE m.femme = :femmeId " +
                "AND m.dateDebut BETWEEN :dateDebut AND :dateFin"
    )
})
public class Femme extends Personne {
    
    @OneToMany(mappedBy = "femme", fetch = FetchType.EAGER)
    private List<Mariage> mariages;
    
    public Femme() {
        super();
    }
    
    public Femme(String nom, String prenom, String telephone, String adresse, Date dateNaissance) {
        super(nom, prenom, telephone, adresse, dateNaissance);
    }
    
    public List<Mariage> getMariages() {
        return mariages;
    }
    
    public void setMariages(List<Mariage> mariages) {
        this.mariages = mariages;
    }
}

