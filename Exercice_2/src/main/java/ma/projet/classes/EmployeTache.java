package ma.projet.classes;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "employe_tache")
public class EmployeTache {
    @EmbeddedId
    private EmployeTachePK pk;
    
    @ManyToOne
    @JoinColumn(name = "employe_id", insertable = false, updatable = false)
    private Employe employe;
    
    @ManyToOne
    @JoinColumn(name = "tache_id", insertable = false, updatable = false)
    private Tache tache;
    
    @Temporal(TemporalType.DATE)
    private Date dateDebutReelle;
    
    @Temporal(TemporalType.DATE)
    private Date dateFinReelle;
    
    public EmployeTache() {
    }
    
    public EmployeTache(Employe employe, Tache tache, Date dateDebutReelle, Date dateFinReelle) {
        this.pk = new EmployeTachePK(employe.getId(), tache.getId());
        this.employe = employe;
        this.tache = tache;
        this.dateDebutReelle = dateDebutReelle;
        this.dateFinReelle = dateFinReelle;
    }
    
    public EmployeTachePK getPk() {
        return pk;
    }
    
    public void setPk(EmployeTachePK pk) {
        this.pk = pk;
    }
    
    public Employe getEmploye() {
        return employe;
    }
    
    public void setEmploye(Employe employe) {
        this.employe = employe;
    }
    
    public Tache getTache() {
        return tache;
    }
    
    public void setTache(Tache tache) {
        this.tache = tache;
    }
    
    public Date getDateDebutReelle() {
        return dateDebutReelle;
    }
    
    public void setDateDebutReelle(Date dateDebutReelle) {
        this.dateDebutReelle = dateDebutReelle;
    }
    
    public Date getDateFinReelle() {
        return dateFinReelle;
    }
    
    public void setDateFinReelle(Date dateFinReelle) {
        this.dateFinReelle = dateFinReelle;
    }
}

