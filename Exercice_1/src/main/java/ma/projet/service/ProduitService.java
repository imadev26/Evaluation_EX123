package ma.projet.service;

import ma.projet.classes.Categorie;
import ma.projet.classes.Commande;
import ma.projet.classes.LigneCommandeProduit;
import ma.projet.classes.Produit;
import ma.projet.dao.IDao;
import ma.projet.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class ProduitService implements IDao<Produit> {
    
    @Override
    public boolean create(Produit o) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(o);
            transaction.commit();
            return true;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
            return false;
        }
    }
    
    @Override
    public boolean delete(Produit o) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.delete(o);
            transaction.commit();
            return true;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
            return false;
        }
    }
    
    @Override
    public boolean update(Produit o) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.update(o);
            transaction.commit();
            return true;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
            return false;
        }
    }
    
    @Override
    public Produit findById(int id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(Produit.class, id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    @Override
    public List<Produit> findAll() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("FROM Produit", Produit.class).list();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    /**
     * Affiche la liste des produits par catégorie
     * @param categorie La catégorie pour laquelle afficher les produits
     */
    public void afficherProduitsParCategorie(Categorie categorie) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "FROM Produit p WHERE p.categorie.id = :categorieId";
            Query<Produit> query = session.createQuery(hql, Produit.class);
            query.setParameter("categorieId", categorie.getId());
            List<Produit> produits = query.list();
            
            System.out.println("\n=== Produits de la catégorie : " + categorie.getLibelle() + " ===");
            if (produits.isEmpty()) {
                System.out.println("Aucun produit trouvé pour cette catégorie.");
            } else {
                System.out.println(String.format("%-15s %-20s %-10s", "Référence", "Catégorie", "Prix"));
                System.out.println("--------------------------------------------------");
                for (Produit p : produits) {
                    System.out.println(String.format("%-15s %-20s %-10.2f DH", 
                            p.getReference(), 
                            p.getCategorie().getLibelle(), 
                            p.getPrix()));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Affiche les produits commandés entre deux dates
     * @param dateDebut Date de début
     * @param dateFin Date de fin
     */
    public void afficherProduitsCommandesEntreDates(Date dateDebut, Date dateFin) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "SELECT DISTINCT p FROM Produit p " +
                        "JOIN p.ligneCommandeProduits lcp " +
                        "JOIN lcp.commande c " +
                        "WHERE c.date BETWEEN :dateDebut AND :dateFin";
            Query<Produit> query = session.createQuery(hql, Produit.class);
            query.setParameter("dateDebut", dateDebut);
            query.setParameter("dateFin", dateFin);
            List<Produit> produits = query.list();
            
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            System.out.println("\n=== Produits commandés entre le " + sdf.format(dateDebut) + 
                             " et le " + sdf.format(dateFin) + " ===");
            
            if (produits.isEmpty()) {
                System.out.println("Aucun produit commandé dans cette période.");
            } else {
                System.out.println(String.format("%-15s %-20s %-10s", "Référence", "Catégorie", "Prix"));
                System.out.println("--------------------------------------------------");
                for (Produit p : produits) {
                    System.out.println(String.format("%-15s %-20s %-10.2f DH", 
                            p.getReference(), 
                            p.getCategorie().getLibelle(), 
                            p.getPrix()));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Affiche les produits commandés dans une commande donnée
     * Format spécifique demandé dans l'énoncé
     * @param commande La commande pour laquelle afficher les produits
     */
    public void afficherProduitsParCommande(Commande commande) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // Récupérer la commande avec ses lignes
            Commande cmd = session.get(Commande.class, commande.getId());
            
            if (cmd == null) {
                System.out.println("Commande non trouvée.");
                return;
            }
            
            SimpleDateFormat sdf = new SimpleDateFormat("dd MMMM yyyy");
            System.out.println("\nCommande : " + cmd.getId() + "     Date : " + sdf.format(cmd.getDate()));
            System.out.println("Liste des produits :");
            
            // Récupérer les lignes de commande
            String hql = "FROM LigneCommandeProduit lcp WHERE lcp.commande.id = :commandeId";
            Query<LigneCommandeProduit> query = session.createQuery(hql, LigneCommandeProduit.class);
            query.setParameter("commandeId", cmd.getId());
            List<LigneCommandeProduit> lignes = query.list();
            
            if (lignes.isEmpty()) {
                System.out.println("Aucun produit dans cette commande.");
            } else {
                System.out.println(String.format("%-15s %-15s %-10s", "Référence", "Prix", "Quantité"));
                System.out.println("--------------------------------------------");
                for (LigneCommandeProduit ligne : lignes) {
                    System.out.println(String.format("%-15s %-15.0f DH %-10d", 
                            ligne.getProduit().getReference(), 
                            ligne.getProduit().getPrix(), 
                            ligne.getQuantite()));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Affiche la liste des produits dont le prix est supérieur à 100 DH
     * Utilise une requête nommée définie dans l'entité Produit
     */
    public void afficherProduitsAvecPrixSuperieurA100() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<Produit> query = session.createNamedQuery("findProduitsByPrixGreaterThan100", Produit.class);
            List<Produit> produits = query.list();
            
            System.out.println("\n=== Produits avec prix supérieur à 100 DH ===");
            if (produits.isEmpty()) {
                System.out.println("Aucun produit trouvé avec un prix supérieur à 100 DH.");
            } else {
                System.out.println(String.format("%-15s %-20s %-10s", "Référence", "Catégorie", "Prix"));
                System.out.println("--------------------------------------------------");
                for (Produit p : produits) {
                    System.out.println(String.format("%-15s %-20s %-10.2f DH", 
                            p.getReference(), 
                            p.getCategorie().getLibelle(), 
                            p.getPrix()));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

