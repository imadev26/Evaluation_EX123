package ma.projet.classes;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "projet")
public class Projet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    private String nom;
    
    @Temporal(TemporalType.DATE)
    private Date dateDebut;
    
    @Temporal(TemporalType.DATE)
    private Date dateFin;
    
    @ManyToOne
    @JoinColumn(name = "employe_id")
    private Employe chefDeProjet;
    
    @OneToMany(mappedBy = "projet", fetch = FetchType.LAZY)
    private List<Tache> taches;
    
    public Projet() {
    }
    
    public Projet(String nom, Date dateDebut, Date dateFin, Employe chefDeProjet) {
        this.nom = nom;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.chefDeProjet = chefDeProjet;
    }
    
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public String getNom() {
        return nom;
    }
    
    public void setNom(String nom) {
        this.nom = nom;
    }
    
    public Date getDateDebut() {
        return dateDebut;
    }
    
    public void setDateDebut(Date dateDebut) {
        this.dateDebut = dateDebut;
    }
    
    public Date getDateFin() {
        return dateFin;
    }
    
    public void setDateFin(Date dateFin) {
        this.dateFin = dateFin;
    }
    
    public Employe getChefDeProjet() {
        return chefDeProjet;
    }
    
    public void setChefDeProjet(Employe chefDeProjet) {
        this.chefDeProjet = chefDeProjet;
    }
    
    public List<Tache> getTaches() {
        return taches;
    }
    
    public void setTaches(List<Tache> taches) {
        this.taches = taches;
    }
}

