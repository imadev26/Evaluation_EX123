package ma.projet.classes;

import javax.persistence.*;

@Entity
@Table(name = "ligne_commande_produit")
public class LigneCommandeProduit {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    @ManyToOne
    @JoinColumn(name = "produit_id", nullable = false)
    private Produit produit;
    
    @ManyToOne
    @JoinColumn(name = "commande_id", nullable = false)
    private Commande commande;
    
    @Column(name = "quantite", nullable = false)
    private int quantite;
    
    // Constructeurs
    public LigneCommandeProduit() {
    }
    
    public LigneCommandeProduit(Produit produit, Commande commande, int quantite) {
        this.produit = produit;
        this.commande = commande;
        this.quantite = quantite;
    }
    
    // Getters et Setters
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public Produit getProduit() {
        return produit;
    }
    
    public void setProduit(Produit produit) {
        this.produit = produit;
    }
    
    public Commande getCommande() {
        return commande;
    }
    
    public void setCommande(Commande commande) {
        this.commande = commande;
    }
    
    public int getQuantite() {
        return quantite;
    }
    
    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }
    
    @Override
    public String toString() {
        return "LigneCommandeProduit{" +
                "id=" + id +
                ", produit=" + (produit != null ? produit.getReference() : "null") +
                ", commande=" + (commande != null ? commande.getId() : "null") +
                ", quantite=" + quantite +
                '}';
    }
}

