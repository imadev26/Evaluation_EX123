package org.example;

import ma.projet.beans.Femme;
import ma.projet.beans.Homme;
import ma.projet.beans.Mariage;
import ma.projet.service.FemmeService;
import ma.projet.service.HommeService;
import ma.projet.service.MariageService;
import ma.projet.util.HibernateUtil;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class App {
    
    // Méthode helper pour Java 8 (repeat() n'existe que depuis Java 11)
    private static String repeat(String str, int count) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < count; i++) {
            sb.append(str);
        }
        return sb.toString();
    }
    
    public static void main(String[] args) {
        // Initialiser les services
        HommeService hommeService = new HommeService();
        FemmeService femmeService = new FemmeService();
        MariageService mariageService = new MariageService();
        
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        
        try {
            // 1. Créer 10 femmes
            System.out.println("=== Création de 10 femmes ===");
            Femme f1 = new Femme("RAMI", "SALIMA", "0601234567", "Casablanca", sdf.parse("15/05/1970"));
            Femme f2 = new Femme("ALI", "AMAL", "0612345678", "Rabat", sdf.parse("20/08/1975"));
            Femme f3 = new Femme("ALAOUI", "WAFA", "0623456789", "Fès", sdf.parse("10/03/1980"));
            Femme f4 = new Femme("ALAMI", "KARIMA", "0634567890", "Marrakech", sdf.parse("25/11/1968"));
            Femme f5 = new Femme("BENNANI", "LAILA", "0645678901", "Tanger", sdf.parse("05/07/1985"));
            Femme f6 = new Femme("IDRISSI", "FATIMA", "0656789012", "Meknès", sdf.parse("30/12/1972"));
            Femme f7 = new Femme("TAHIRI", "SOUMIA", "0667890123", "Agadir", sdf.parse("18/09/1978"));
            Femme f8 = new Femme("CHAKIR", "NAWAL", "0678901234", "Oujda", sdf.parse("12/02/1965"));
            Femme f9 = new Femme("BERRADA", "LATIFA", "0689012345", "Tétouan", sdf.parse("22/06/1990"));
            Femme f10 = new Femme("SEMLALI", "AMINA", "0690123456", "Kenitra", sdf.parse("08/04/1982"));
            
            femmeService.create(f1);
            femmeService.create(f2);
            femmeService.create(f3);
            femmeService.create(f4);
            femmeService.create(f5);
            femmeService.create(f6);
            femmeService.create(f7);
            femmeService.create(f8);
            femmeService.create(f9);
            femmeService.create(f10);
            
            System.out.println("10 femmes créées avec succès!");
            
            // 2. Créer 5 hommes
            System.out.println("\n=== Création de 5 hommes ===");
            Homme h1 = new Homme("SAFI", "SAID", "0701234567", "Casablanca", sdf.parse("01/01/1960"));
            Homme h2 = new Homme("AMRANI", "OMAR", "0712345678", "Rabat", sdf.parse("15/03/1965"));
            Homme h3 = new Homme("FILALI", "AHMED", "0723456789", "Fès", sdf.parse("20/07/1970"));
            Homme h4 = new Homme("BENALI", "YOUSSEF", "0734567890", "Marrakech", sdf.parse("10/11/1975"));
            Homme h5 = new Homme("LAHLOU", "KARIM", "0745678901", "Tanger", sdf.parse("25/05/1968"));
            
            hommeService.create(h1);
            hommeService.create(h2);
            hommeService.create(h3);
            hommeService.create(h4);
            hommeService.create(h5);
            
            System.out.println("5 hommes créés avec succès!");
            
            // 3. Créer des mariages
            System.out.println("\n=== Création des mariages ===");
            
            // Mariages pour SAFI SAID (h1) - 4 mariages dont 3 en cours et 1 échoué
            Mariage m1 = new Mariage(h1, f4, sdf.parse("03/09/1989"), sdf.parse("03/09/1990"), 0);
            mariageService.create(m1);
            
            Mariage m2 = new Mariage(h1, f1, sdf.parse("03/09/1990"), null, 4);
            mariageService.create(m2);
            
            Mariage m3 = new Mariage(h1, f2, sdf.parse("03/09/1995"), null, 2);
            mariageService.create(m3);
            
            Mariage m4 = new Mariage(h1, f3, sdf.parse("04/11/2000"), null, 3);
            mariageService.create(m4);
            
            // Mariages pour d'autres hommes
            Mariage m5 = new Mariage(h2, f5, sdf.parse("10/05/1990"), null, 2);
            mariageService.create(m5);
            
            Mariage m6 = new Mariage(h2, f6, sdf.parse("15/06/1995"), sdf.parse("20/08/2005"), 3);
            mariageService.create(m6);
            
            Mariage m7 = new Mariage(h3, f7, sdf.parse("01/01/2000"), null, 1);
            mariageService.create(m7);
            
            Mariage m8 = new Mariage(h3, f8, sdf.parse("05/03/1992"), sdf.parse("10/07/1998"), 2);
            mariageService.create(m8);
            
            // Créer un homme marié à 4 femmes entre deux dates pour le test
            Mariage m9 = new Mariage(h4, f9, sdf.parse("01/01/1998"), null, 1);
            mariageService.create(m9);
            
            Mariage m10 = new Mariage(h4, f10, sdf.parse("15/03/1998"), null, 2);
            mariageService.create(m10);
            
            Mariage m11 = new Mariage(h5, f5, sdf.parse("20/05/1995"), sdf.parse("30/12/2000"), 1);
            mariageService.create(m11);
            
            // Ajouter plus de mariages pour f6 pour qu'elle soit mariée 2 fois
            Mariage m12 = new Mariage(h5, f6, sdf.parse("10/01/2008"), null, 2);
            mariageService.create(m12);
            
            System.out.println("Mariages créés avec succès!");
            
            // Pause pour que Hibernate puisse mettre à jour les relations
            Thread.sleep(1000);
            
            // Tests selon les exigences
            System.out.println("\n" + repeat("=", 70));
            System.out.println("=== TESTS DE L'APPLICATION ===");
            System.out.println(repeat("=", 70));
            
            // Test 1: Afficher la liste des femmes
            System.out.println("\n1. Liste de toutes les femmes :");
            System.out.println(repeat("-", 70));
            List<Femme> femmes = femmeService.findAll();
            for (Femme f : femmes) {
                System.out.printf("ID: %d | %s %s | Née le: %td/%<tm/%<tY | Tél: %s | Ville: %s%n",
                        f.getId(), f.getNom(), f.getPrenom(), f.getDateNaissance(), 
                        f.getTelephone(), f.getAdresse());
            }
            
            // Test 2: Afficher la femme la plus âgée
            System.out.println("\n2. Femme la plus âgée :");
            System.out.println(repeat("-", 70));
            Femme femmePlusAgee = femmeService.getFemmePlusAgee();
            if (femmePlusAgee != null) {
                System.out.printf("La femme la plus âgée est : %s %s (née le %td/%<tm/%<tY)%n",
                        femmePlusAgee.getNom(), femmePlusAgee.getPrenom(), 
                        femmePlusAgee.getDateNaissance());
            }
            
            // Test 3: Afficher les épouses d'un homme donné
            System.out.println("\n3. Épouses de SAFI SAID entre 1990 et 2000 :");
            System.out.println(repeat("-", 70));
            Date debut = sdf.parse("01/01/1990");
            Date fin = sdf.parse("31/12/2000");
            List<Femme> epouses = hommeService.getEpousesBetweenDates(h1, debut, fin);
            for (Femme epouse : epouses) {
                System.out.printf("- %s %s%n", epouse.getNom(), epouse.getPrenom());
            }
            
            // Test 4: Afficher le nombre d'enfants d'une femme entre deux dates
            System.out.println("\n4. Nombre d'enfants de RAMI SALIMA entre 1990 et 2000 :");
            System.out.println(repeat("-", 70));
            int nbrEnfants = femmeService.getNombreEnfantsBetweenDates(f1, debut, fin);
            System.out.printf("RAMI SALIMA a eu %d enfant(s) entre 1990 et 2000%n", nbrEnfants);
            
            // Test 5: Afficher les femmes mariées deux fois ou plus
            System.out.println("\n5. Femmes mariées au moins deux fois :");
            System.out.println(repeat("-", 70));
            List<Femme> femmesMarieesDeuxFois = femmeService.getFemmesMarieesDeuxFoisOuPlus();
            if (femmesMarieesDeuxFois.isEmpty()) {
                System.out.println("Aucune femme mariée deux fois ou plus.");
            } else {
                for (Femme f : femmesMarieesDeuxFois) {
                    System.out.printf("- %s %s (mariée %d fois)%n", 
                            f.getNom(), f.getPrenom(), f.getMariages().size());
                }
            }
            
            // Test 6: Afficher les hommes mariés à quatre femmes entre deux dates
            System.out.println("\n6. Nombre d'hommes mariés à 4 femmes entre 1989 et 2001 :");
            System.out.println(repeat("-", 70));
            Date debutPeriode = sdf.parse("01/01/1989");
            Date finPeriode = sdf.parse("31/12/2001");
            int nbrHommes = hommeService.getNombreHommesMarierQuatreFemmes(debutPeriode, finPeriode);
            System.out.printf("Nombre d'hommes mariés à au moins 4 femmes : %d%n", nbrHommes);
            
            // Test 7: Afficher les mariages d'un homme avec tous les détails
            System.out.println("\n7. Détails des mariages de SAFI SAID :");
            System.out.println(repeat("-", 70));
            hommeService.afficherMariagesHomme(h1);
            
            System.out.println("\n" + repeat("=", 70));
            System.out.println("=== FIN DES TESTS ===");
            System.out.println(repeat("=", 70));
            
        } catch (ParseException e) {
            System.err.println("Erreur de format de date : " + e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            System.err.println("Erreur : " + e.getMessage());
            e.printStackTrace();
        } finally {
            // Fermer la SessionFactory
            HibernateUtil.shutdown();
        }
    }
}
