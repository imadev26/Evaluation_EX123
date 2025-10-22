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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class TestApplication {
    
    private static CategorieService categorieService = new CategorieService();
    private static ProduitService produitService = new ProduitService();
    private static CommandeService commandeService = new CommandeService();
    private static LigneCommandeService ligneCommandeService = new LigneCommandeService();
    private static Scanner scanner = new Scanner(System.in);
    
    public static void main(String[] args) {
        try {
            // Initialiser Hibernate
            HibernateUtil.getSessionFactory();
            System.out.println("Connexion à la base de données réussie !\n");
            
            boolean continuer = true;
            while (continuer) {
                afficherMenu();
                int choix = scanner.nextInt();
                scanner.nextLine(); // Consommer la nouvelle ligne
                
                switch (choix) {
                    case 1:
                        initialiserDonnees();
                        break;
                    case 2:
                        testerAffichageProduitsParCategorie();
                        break;
                    case 3:
                        testerAffichageProduitsEntreDates();
                        break;
                    case 4:
                        testerAffichageProduitsParCommande();
                        break;
                    case 5:
                        testerAffichageProduitsPrixSuperieur100();
                        break;
                    case 6:
                        afficherToutesLesCategories();
                        break;
                    case 7:
                        afficherTousLesProduits();
                        break;
                    case 8:
                        afficherToutesLesCommandes();
                        break;
                    case 0:
                        continuer = false;
                        break;
                    default:
                        System.out.println("Choix invalide !");
                }
                
                if (continuer) {
                    System.out.println("\nAppuyez sur Entrée pour continuer...");
                    scanner.nextLine();
                }
            }
            
            System.out.println("Au revoir !");
            HibernateUtil.shutdown();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private static void afficherMenu() {
        System.out.println("\n========================================");
        System.out.println("    GESTION DE STOCK - MENU PRINCIPAL");
        System.out.println("========================================");
        System.out.println("1. Initialiser les données de test");
        System.out.println("2. Afficher les produits par catégorie");
        System.out.println("3. Afficher les produits commandés entre deux dates");
        System.out.println("4. Afficher les produits d'une commande");
        System.out.println("5. Afficher les produits avec prix > 100 DH");
        System.out.println("6. Afficher toutes les catégories");
        System.out.println("7. Afficher tous les produits");
        System.out.println("8. Afficher toutes les commandes");
        System.out.println("0. Quitter");
        System.out.println("========================================");
        System.out.print("Votre choix : ");
    }
    
    private static void initialiserDonnees() {
        try {
            System.out.println("\n=== Initialisation des données ===");
            
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
            // Commande 1 (14 Mars 2013)
            LigneCommandeProduit lcp1 = new LigneCommandeProduit(p1, cmd1, 7);  // ES12
            LigneCommandeProduit lcp2 = new LigneCommandeProduit(p2, cmd1, 14); // ZR85
            LigneCommandeProduit lcp3 = new LigneCommandeProduit(p3, cmd1, 5);  // EE85
            
            // Commande 2 (20 Mars 2013)
            LigneCommandeProduit lcp4 = new LigneCommandeProduit(p4, cmd2, 3);  // AA45
            LigneCommandeProduit lcp5 = new LigneCommandeProduit(p7, cmd2, 2);  // DD99
            
            // Commande 3 (25 Mars 2013)
            LigneCommandeProduit lcp6 = new LigneCommandeProduit(p5, cmd3, 10); // BB12
            LigneCommandeProduit lcp7 = new LigneCommandeProduit(p6, cmd3, 8);  // CC78
            
            // Commande 4 (10 Avril 2013)
            LigneCommandeProduit lcp8 = new LigneCommandeProduit(p1, cmd4, 5);  // ES12
            LigneCommandeProduit lcp9 = new LigneCommandeProduit(p3, cmd4, 3);  // EE85
            
            ligneCommandeService.create(lcp1);
            ligneCommandeService.create(lcp2);
            ligneCommandeService.create(lcp3);
            ligneCommandeService.create(lcp4);
            ligneCommandeService.create(lcp5);
            ligneCommandeService.create(lcp6);
            ligneCommandeService.create(lcp7);
            ligneCommandeService.create(lcp8);
            ligneCommandeService.create(lcp9);
            System.out.println("✓ 9 lignes de commande créées");
            
            System.out.println("\n✓ Données initialisées avec succès !");
            
        } catch (ParseException e) {
            System.err.println("Erreur de format de date : " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Erreur lors de l'initialisation : " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    private static void testerAffichageProduitsParCategorie() {
        System.out.println("\n=== Test : Afficher les produits par catégorie ===");
        System.out.print("Entrez l'ID de la catégorie : ");
        int categorieId = scanner.nextInt();
        scanner.nextLine();
        
        Categorie categorie = categorieService.findById(categorieId);
        if (categorie != null) {
            produitService.afficherProduitsParCategorie(categorie);
        } else {
            System.out.println("Catégorie non trouvée !");
        }
    }
    
    private static void testerAffichageProduitsEntreDates() {
        try {
            System.out.println("\n=== Test : Afficher les produits commandés entre deux dates ===");
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            
            System.out.print("Date de début (dd/MM/yyyy) : ");
            String dateDebutStr = scanner.nextLine();
            Date dateDebut = sdf.parse(dateDebutStr);
            
            System.out.print("Date de fin (dd/MM/yyyy) : ");
            String dateFinStr = scanner.nextLine();
            Date dateFin = sdf.parse(dateFinStr);
            
            produitService.afficherProduitsCommandesEntreDates(dateDebut, dateFin);
            
        } catch (ParseException e) {
            System.err.println("Format de date invalide. Utilisez dd/MM/yyyy");
        }
    }
    
    private static void testerAffichageProduitsParCommande() {
        System.out.println("\n=== Test : Afficher les produits d'une commande ===");
        System.out.print("Entrez l'ID de la commande : ");
        int commandeId = scanner.nextInt();
        scanner.nextLine();
        
        Commande commande = commandeService.findById(commandeId);
        if (commande != null) {
            produitService.afficherProduitsParCommande(commande);
        } else {
            System.out.println("Commande non trouvée !");
        }
    }
    
    private static void testerAffichageProduitsPrixSuperieur100() {
        System.out.println("\n=== Test : Afficher les produits avec prix > 100 DH (Requête nommée) ===");
        produitService.afficherProduitsAvecPrixSuperieurA100();
    }
    
    private static void afficherToutesLesCategories() {
        System.out.println("\n=== Liste de toutes les catégories ===");
        List<Categorie> categories = categorieService.findAll();
        
        if (categories == null || categories.isEmpty()) {
            System.out.println("Aucune catégorie trouvée.");
        } else {
            System.out.println(String.format("%-5s %-10s %-20s", "ID", "Code", "Libellé"));
            System.out.println("----------------------------------------");
            for (Categorie c : categories) {
                System.out.println(String.format("%-5d %-10s %-20s", 
                        c.getId(), c.getCode(), c.getLibelle()));
            }
        }
    }
    
    private static void afficherTousLesProduits() {
        System.out.println("\n=== Liste de tous les produits ===");
        List<Produit> produits = produitService.findAll();
        
        if (produits == null || produits.isEmpty()) {
            System.out.println("Aucun produit trouvé.");
        } else {
            System.out.println(String.format("%-5s %-15s %-10s %-20s", "ID", "Référence", "Prix", "Catégorie"));
            System.out.println("--------------------------------------------------------");
            for (Produit p : produits) {
                System.out.println(String.format("%-5d %-15s %-10.2f %-20s", 
                        p.getId(), 
                        p.getReference(), 
                        p.getPrix(),
                        p.getCategorie().getLibelle()));
            }
        }
    }
    
    private static void afficherToutesLesCommandes() {
        System.out.println("\n=== Liste de toutes les commandes ===");
        List<Commande> commandes = commandeService.findAll();
        
        if (commandes == null || commandes.isEmpty()) {
            System.out.println("Aucune commande trouvée.");
        } else {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            System.out.println(String.format("%-5s %-15s", "ID", "Date"));
            System.out.println("-------------------------");
            for (Commande c : commandes) {
                System.out.println(String.format("%-5d %-15s", 
                        c.getId(), 
                        sdf.format(c.getDate())));
            }
        }
    }
}

