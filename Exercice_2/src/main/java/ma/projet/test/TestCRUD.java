package ma.projet.test;

import ma.projet.classes.*;
import ma.projet.service.*;
import ma.projet.util.HibernateUtil;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Classe de test pour les opérations CRUD de base
 */
public class TestCRUD {
    
    public static void main(String[] args) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            
            // Services
            EmployeService employeService = new EmployeService();
            ProjetService projetService = new ProjetService();
            TacheService tacheService = new TacheService();
            EmployeTacheService employeTacheService = new EmployeTacheService();
            
            System.out.println("=== TEST DES OPÉRATIONS CRUD ===\n");
            
            // Test CREATE
            System.out.println("1. Test CREATE:");
            System.out.println("   " + "-".repeat(50));
            
            Employe employe = new Employe("TEST", "User", "0600000000");
            if (employeService.create(employe)) {
                System.out.println("   ✓ Employé créé avec ID: " + employe.getId());
            }
            
            Projet projet = new Projet("Projet Test", 
                                      new Date(), 
                                      sdf.parse("31/12/2024"), 
                                      employe);
            if (projetService.create(projet)) {
                System.out.println("   ✓ Projet créé avec ID: " + projet.getId());
            }
            
            Tache tache = new Tache("Tâche Test", 
                                   new Date(), 
                                   sdf.parse("31/12/2024"), 
                                   2500.0, 
                                   projet);
            if (tacheService.create(tache)) {
                System.out.println("   ✓ Tâche créée avec ID: " + tache.getId());
            }
            
            // Test READ
            System.out.println("\n2. Test READ:");
            System.out.println("   " + "-".repeat(50));
            
            Employe employeRecupere = employeService.findById(employe.getId());
            if (employeRecupere != null) {
                System.out.println("   ✓ Employé récupéré: " + employeRecupere.getPrenom() + 
                                 " " + employeRecupere.getNom());
            }
            
            Projet projetRecupere = projetService.findById(projet.getId());
            if (projetRecupere != null) {
                System.out.println("   ✓ Projet récupéré: " + projetRecupere.getNom());
            }
            
            Tache tacheRecuperee = tacheService.findById(tache.getId());
            if (tacheRecuperee != null) {
                System.out.println("   ✓ Tâche récupérée: " + tacheRecuperee.getNom() + 
                                 " (Prix: " + tacheRecuperee.getPrix() + " DH)");
            }
            
            // Test UPDATE
            System.out.println("\n3. Test UPDATE:");
            System.out.println("   " + "-".repeat(50));
            
            employeRecupere.setTelephone("0611111111");
            if (employeService.update(employeRecupere)) {
                System.out.println("   ✓ Téléphone de l'employé mis à jour: " + 
                                 employeService.findById(employe.getId()).getTelephone());
            }
            
            tacheRecuperee.setPrix(3000.0);
            if (tacheService.update(tacheRecuperee)) {
                System.out.println("   ✓ Prix de la tâche mis à jour: " + 
                                 tacheService.findById(tache.getId()).getPrix() + " DH");
            }
            
            // Test DELETE
            System.out.println("\n4. Test DELETE:");
            System.out.println("   " + "-".repeat(50));
            
            if (tacheService.delete(tacheRecuperee)) {
                System.out.println("   ✓ Tâche supprimée");
                Tache verificationTache = tacheService.findById(tache.getId());
                System.out.println("   ✓ Vérification: Tâche existe après suppression? " + 
                                 (verificationTache != null ? "Oui" : "Non"));
            }
            
            if (projetService.delete(projetRecupere)) {
                System.out.println("   ✓ Projet supprimé");
            }
            
            if (employeService.delete(employeRecupere)) {
                System.out.println("   ✓ Employé supprimé");
            }
            
            System.out.println("\n=== Tests CRUD terminés avec succès! ===");
            
        } catch (Exception e) {
            System.err.println("Erreur lors des tests CRUD: " + e.getMessage());
            e.printStackTrace();
        } finally {
            HibernateUtil.shutdown();
        }
    }
}

