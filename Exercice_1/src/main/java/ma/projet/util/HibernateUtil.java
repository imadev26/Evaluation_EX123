package ma.projet.util;

import ma.projet.classes.Categorie;
import ma.projet.classes.Commande;
import ma.projet.classes.LigneCommandeProduit;
import ma.projet.classes.Produit;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class HibernateUtil {
    
    private static SessionFactory sessionFactory;
    
    static {
        try {
            // Charger les propriétés depuis application.properties
            Properties properties = new Properties();
            try {
                properties.load(HibernateUtil.class.getClassLoader()
                        .getResourceAsStream("application.properties"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            
            // Créer la configuration Hibernate
            Configuration configuration = new Configuration();
            
            // Configurer les propriétés de la base de données
            configuration.setProperty("hibernate.connection.driver_class", 
                    properties.getProperty("db.driver"));
            configuration.setProperty("hibernate.connection.url", 
                    properties.getProperty("db.url"));
            configuration.setProperty("hibernate.connection.username", 
                    properties.getProperty("db.username"));
            configuration.setProperty("hibernate.connection.password", 
                    properties.getProperty("db.password"));
            
            // Configurer les propriétés Hibernate
            configuration.setProperty("hibernate.dialect", 
                    properties.getProperty("hibernate.dialect"));
            configuration.setProperty("hibernate.show_sql", 
                    properties.getProperty("hibernate.show_sql"));
            configuration.setProperty("hibernate.format_sql", 
                    properties.getProperty("hibernate.format_sql"));
            configuration.setProperty("hibernate.hbm2ddl.auto", 
                    properties.getProperty("hibernate.hbm2ddl.auto"));
            
            // Pool de connexions
            configuration.setProperty("hibernate.c3p0.min_size", "5");
            configuration.setProperty("hibernate.c3p0.max_size", "20");
            configuration.setProperty("hibernate.c3p0.timeout", "300");
            configuration.setProperty("hibernate.c3p0.max_statements", "50");
            configuration.setProperty("hibernate.c3p0.idle_test_period", "3000");
            
            // Ajouter les classes annotées
            configuration.addAnnotatedClass(Categorie.class);
            configuration.addAnnotatedClass(Produit.class);
            configuration.addAnnotatedClass(Commande.class);
            configuration.addAnnotatedClass(LigneCommandeProduit.class);
            
            // Construire la SessionFactory
            sessionFactory = configuration.buildSessionFactory();
            
        } catch (Throwable ex) {
            System.err.println("Erreur lors de la création de la SessionFactory : " + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }
    
    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
    
    public static void shutdown() {
        if (sessionFactory != null) {
            sessionFactory.close();
        }
    }
}

