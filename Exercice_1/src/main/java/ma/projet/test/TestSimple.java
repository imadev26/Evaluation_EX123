package ma.projet.test;

import ma.projet.classes.Categorie;
import ma.projet.classes.Commande;
import ma.projet.classes.LigneCommandeProduit;
import ma.projet.classes.Produit;
import ma.projet.service.CategorieService;
import ma.projet.service.CommandeService;
import ma.projet.service.LigneCommandeService;
import ma.projet.service.ProduitService;
import ma.projet.util.HibernateUtil;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Classe de test simple qui exécute toutes les fonctionnalités automatiquement
 */
public class TestSimple {
    
    /**
     * Méthode utilitaire pour répéter une chaîne (compatible Java 8)
     */
    private static String repeat(String str, int count) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < count; i++) {
            sb.append(str);
        }
        return sb.toString();
    }
    
    public static void main(String[] args) {
        try {
            // Initialiser Hibernate
            HibernateUtil.getSessionFactory();
            System.out.println("✓ Connexion à la base de données réussie !\n");
            
            // Services
            CategorieService categorieService = new CategorieService();
            ProduitService produitService = new ProduitService();
            CommandeService commandeService = new CommandeService();
            LigneCommandeService ligneCommandeService = new LigneCommandeService();
            
            // ==========================================
            // PHASE 1 : Création des données
            // ==========================================
            System.out.println("========================================");
            System.out.println("PHASE 1 : CRÉATION DES DONNÉES");
            System.out.println("========================================\n");
            
            // Créer des catégories
            Categorie cat1 = new Categorie("ORD", "Ordinateurs");
            Categorie cat2 = new Categorie("IMP", "Imprimantes");
            Categorie cat3 = new Categorie("ACC", "Accessoires");
            
            categorieService.create(cat1);
            categorieService.create(cat2);
            categorieService.create(cat3);
            System.out.println("✓ 3 catégories créées");
            
            // Créer des produits
            Produit p1 = new Produit("ES12", 120, cat1);
            Produit p2 = new Produit("ZR85", 100, cat1);
            Produit p3 = new Produit("EE85", 200, cat2);
            Produit p4 = new Produit("AA45", 150, cat2);
            Produit p5 = new Produit("BB12", 50, cat3);
            Produit p6 = new Produit("CC78", 75, cat3);
            Produit p7 = new Produit("DD99", 300, cat1);
            Produit p8 = new Produit("FF11", 95, cat3);
            
            produitService.create(p1);
            produitService.create(p2);
            produitService.create(p3);
            produitService.create(p4);
            produitService.create(p5);
            produitService.create(p6);
            produitService.create(p7);
            produitService.create(p8);
            System.out.println("✓ 8 produits créés");
            
            // Créer des commandes
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            
            Commande cmd1 = new Commande(sdf.parse("14/03/2013"));
            Commande cmd2 = new Commande(sdf.parse("20/03/2013"));
            Commande cmd3 = new Commande(sdf.parse("25/03/2013"));
            Commande cmd4 = new Commande(sdf.parse("10/04/2013"));
            
            commandeService.create(cmd1);
            commandeService.create(cmd2);
            commandeService.create(cmd3);
            commandeService.create(cmd4);
            System.out.println("✓ 4 commandes créées");
            
            // Créer des lignes de commande
            // Commande 1 (14 Mars 2013) - Correspond à l'exemple de l'énoncé
            ligneCommandeService.create(new LigneCommandeProduit(p1, cmd1, 7));   // ES12, 120 DH, 7
            ligneCommandeService.create(new LigneCommandeProduit(p2, cmd1, 14));  // ZR85, 100 DH, 14
            ligneCommandeService.create(new LigneCommandeProduit(p3, cmd1, 5));   // EE85, 200 DH, 5
            
            // Commande 2 (20 Mars 2013)
            ligneCommandeService.create(new LigneCommandeProduit(p4, cmd2, 3));   // AA45
            ligneCommandeService.create(new LigneCommandeProduit(p7, cmd2, 2));   // DD99
            
            // Commande 3 (25 Mars 2013)
            ligneCommandeService.create(new LigneCommandeProduit(p5, cmd3, 10));  // BB12
            ligneCommandeService.create(new LigneCommandeProduit(p6, cmd3, 8));   // CC78
            
            // Commande 4 (10 Avril 2013)
            ligneCommandeService.create(new LigneCommandeProduit(p1, cmd4, 5));   // ES12
            ligneCommandeService.create(new LigneCommandeProduit(p3, cmd4, 3));   // EE85
            
            System.out.println("✓ 9 lignes de commande créées\n");
            
            // ==========================================
            // PHASE 2 : Tests des fonctionnalités
            // ==========================================
            System.out.println("========================================");
            System.out.println("PHASE 2 : TESTS DES FONCTIONNALITÉS");
            System.out.println("========================================\n");
            
            // Test 1 : Afficher les produits par catégorie
            System.out.println("▶ TEST 1 : Affichage des produits par catégorie");
            produitService.afficherProduitsParCategorie(cat1);
            produitService.afficherProduitsParCategorie(cat2);
            
            System.out.println("\n" + repeat("=", 60) + "\n");
            
            // Test 2 : Afficher les produits commandés entre deux dates
            System.out.println("▶ TEST 2 : Produits commandés entre deux dates");
            Date dateDebut = sdf.parse("01/03/2013");
            Date dateFin = sdf.parse("31/03/2013");
            produitService.afficherProduitsCommandesEntreDates(dateDebut, dateFin);
            
            System.out.println("\n" + repeat("=", 60) + "\n");
            
            // Test 3 : Afficher les produits d'une commande (format spécifique)
            System.out.println("▶ TEST 3 : Produits d'une commande donnée (Format énoncé)");
            produitService.afficherProduitsParCommande(cmd1);
            
            System.out.println("\n" + repeat("=", 60) + "\n");
            
            // Test 4 : Afficher les produits avec prix > 100 DH (requête nommée)
            System.out.println("▶ TEST 4 : Produits avec prix > 100 DH (Requête nommée)");
            produitService.afficherProduitsAvecPrixSuperieurA100();
            
            System.out.println("\n" + repeat("=", 60) + "\n");
            
            // Test 5 : Afficher toutes les commandes
            System.out.println("▶ TEST 5 : Liste de toutes les commandes");
            List<Commande> commandes = commandeService.findAll();
            System.out.println("\n=== Toutes les commandes ===");
            System.out.println(String.format("%-5s %-15s", "ID", "Date"));
            System.out.println(repeat("-", 25));
            for (Commande c : commandes) {
                System.out.println(String.format("%-5d %-15s", 
                        c.getId(), 
                        sdf.format(c.getDate())));
            }
            
            System.out.println("\n" + repeat("=", 60) + "\n");
            System.out.println("✓ TOUS LES TESTS ONT ÉTÉ EXÉCUTÉS AVEC SUCCÈS !");
            System.out.println(repeat("=", 60) + "\n");
            
            // Fermeture
            HibernateUtil.shutdown();
            
        } catch (Exception e) {
            System.err.println("Erreur lors de l'exécution des tests : " + e.getMessage());
            e.printStackTrace();
        }
    }
}

