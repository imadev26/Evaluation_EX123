package ma.projet.test;

import ma.projet.classes.*;
import ma.projet.service.*;
import ma.projet.util.HibernateUtil;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class TestApplication {
    private static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    public static void main(String[] args) {
        try {
            // Initialize services
            EmployeService employeService = new EmployeService();
            ProjetService projetService = new ProjetService();
            TacheService tacheService = new TacheService();
            EmployeTacheService employeTacheService = new EmployeTacheService();

            System.out.println("=== Création des données de test ===\n");
            
            // Créer des employés
            Employe emp1 = new Employe("ALAMI", "Ahmed", "0612345678");
            Employe emp2 = new Employe("BENANI", "Fatima", "0623456789");
            Employe emp3 = new Employe("CHAKIR", "Mohammed", "0634567890");
            
            employeService.create(emp1);
            employeService.create(emp2);
            employeService.create(emp3);
            System.out.println("✓ Employés créés avec succès");

            // Créer des projets
            Projet projet1 = new Projet("Gestion de stock", 
                                       sdf.parse("14/01/2013"), 
                                       sdf.parse("30/06/2013"), 
                                       emp1);
            Projet projet2 = new Projet("Système de facturation", 
                                       sdf.parse("01/03/2013"), 
                                       sdf.parse("30/09/2013"), 
                                       emp2);
            
            projetService.create(projet1);
            projetService.create(projet2);
            System.out.println("✓ Projets créés avec succès");

            // Créer des tâches
            Tache tache1 = new Tache("Analyse", 
                                    sdf.parse("20/01/2013"), 
                                    sdf.parse("28/02/2013"), 
                                    1200.0, 
                                    projet1);
            Tache tache2 = new Tache("Conception", 
                                    sdf.parse("01/03/2013"), 
                                    sdf.parse("20/03/2013"), 
                                    1500.0, 
                                    projet1);
            Tache tache3 = new Tache("Développement", 
                                    sdf.parse("25/03/2013"), 
                                    sdf.parse("30/05/2013"), 
                                    3000.0, 
                                    projet1);
            Tache tache4 = new Tache("Test", 
                                    sdf.parse("01/06/2013"), 
                                    sdf.parse("15/06/2013"), 
                                    800.0, 
                                    projet1);
            Tache tache5 = new Tache("Documentation", 
                                    sdf.parse("10/03/2013"), 
                                    sdf.parse("20/03/2013"), 
                                    500.0, 
                                    projet2);
            
            tacheService.create(tache1);
            tacheService.create(tache2);
            tacheService.create(tache3);
            tacheService.create(tache4);
            tacheService.create(tache5);
            System.out.println("✓ Tâches créées avec succès");

            // Associer des employés aux tâches avec dates réelles
            EmployeTache et1 = new EmployeTache(emp1, tache1, 
                                               sdf.parse("10/02/2013"), 
                                               sdf.parse("20/02/2013"));
            EmployeTache et2 = new EmployeTache(emp2, tache2, 
                                               sdf.parse("10/03/2013"), 
                                               sdf.parse("15/03/2013"));
            EmployeTache et3 = new EmployeTache(emp3, tache3, 
                                               sdf.parse("10/04/2013"), 
                                               sdf.parse("25/04/2013"));
            EmployeTache et4 = new EmployeTache(emp1, tache4, 
                                               sdf.parse("01/06/2013"), 
                                               sdf.parse("10/06/2013"));
            EmployeTache et5 = new EmployeTache(emp2, tache5, 
                                               sdf.parse("12/03/2013"), 
                                               sdf.parse("18/03/2013"));
            
            employeTacheService.create(et1);
            employeTacheService.create(et2);
            employeTacheService.create(et3);
            employeTacheService.create(et4);
            employeTacheService.create(et5);
            System.out.println("✓ Associations employés-tâches créées avec succès\n");

            // Tests des fonctionnalités
            System.out.println("\n=== TESTS DES FONCTIONNALITÉS ===\n");

            // Test 1: Afficher la liste des tâches réalisées par un employé
            System.out.println("1. Liste des tâches réalisées par " + emp1.getPrenom() + " " + emp1.getNom() + ":");
            System.out.println("   " + "-".repeat(60));
            List<Tache> tachesEmp1 = employeService.findTachesByEmploye(emp1.getId());
            for (Tache t : tachesEmp1) {
                System.out.println("   - " + t.getNom() + " (Prix: " + t.getPrix() + " DH)");
            }

            // Test 2: Afficher la liste des projets gérés par un employé
            System.out.println("\n2. Liste des projets gérés par " + emp1.getPrenom() + " " + emp1.getNom() + ":");
            System.out.println("   " + "-".repeat(60));
            List<Projet> projetsEmp1 = employeService.findProjetsByEmploye(emp1.getId());
            for (Projet p : projetsEmp1) {
                System.out.println("   - " + p.getNom() + " (Début: " + sdf.format(p.getDateDebut()) + ")");
            }

            // Test 3: Afficher la liste des tâches planifiées pour un projet
            System.out.println("\n3. Liste des tâches planifiées pour le projet '" + projet1.getNom() + "':");
            System.out.println("   " + "-".repeat(60));
            List<Tache> tachesProjet1 = projetService.findTachesByProjet(projet1.getId());
            for (Tache t : tachesProjet1) {
                System.out.println("   - " + t.getNom() + " (Du " + sdf.format(t.getDateDebut()) + 
                                 " au " + sdf.format(t.getDateFin()) + ")");
            }

            // Test 4: Afficher les tâches réalisées avec les dates réelles (format demandé)
            System.out.println("\n4. Tâches réalisées avec dates réelles (format demandé):");
            System.out.println("   " + "-".repeat(60));
            projetService.afficherTachesRealisees(projet1.getId());

            // Test 5: Tâches dont le prix est supérieur à 1000 DH
            System.out.println("\n5. Tâches dont le prix est supérieur à 1000 DH (requête nommée):");
            System.out.println("   " + "-".repeat(60));
            List<Tache> tachesCoutEleve = tacheService.findTachesByPrixSuperieur(1000.0);
            for (Tache t : tachesCoutEleve) {
                System.out.println("   - " + t.getNom() + " : " + t.getPrix() + " DH");
            }

            // Test 6: Tâches réalisées entre deux dates
            System.out.println("\n6. Tâches réalisées entre le 01/03/2013 et le 30/04/2013:");
            System.out.println("   " + "-".repeat(60));
            Date dateDebut = sdf.parse("01/03/2013");
            Date dateFin = sdf.parse("30/04/2013");
            List<Tache> tachesEntreDeuxDates = tacheService.findTachesEntreDeuxDates(dateDebut, dateFin);
            for (Tache t : tachesEntreDeuxDates) {
                System.out.println("   - " + t.getNom() + " (Projet: " + t.getProjet().getNom() + ")");
            }

            // Test 7: Afficher tous les employés
            System.out.println("\n7. Liste de tous les employés:");
            System.out.println("   " + "-".repeat(60));
            List<Employe> tousEmployes = employeService.findAll();
            for (Employe e : tousEmployes) {
                System.out.println("   - " + e.getPrenom() + " " + e.getNom() + 
                                 " (Tél: " + e.getTelephone() + ")");
            }

            System.out.println("\n=== Tests terminés avec succès! ===");

        } catch (ParseException e) {
            System.err.println("Erreur de parsing de date: " + e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            System.err.println("Erreur lors de l'exécution: " + e.getMessage());
            e.printStackTrace();
        } finally {
            // Fermer la SessionFactory
            HibernateUtil.shutdown();
        }
    }
}

