package ma.projet.util;

import ma.projet.beans.Femme;
import ma.projet.beans.Homme;
import ma.projet.beans.Mariage;
import ma.projet.beans.Personne;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class HibernateUtil {
    
    private static SessionFactory sessionFactory;
    
    static {
        try {
            // Load properties file
            Properties properties = new Properties();
            InputStream input = HibernateUtil.class.getClassLoader()
                    .getResourceAsStream("application.properties");
            
            if (input == null) {
                throw new RuntimeException("Unable to find application.properties");
            }
            
            properties.load(input);
            
            // Create configuration
            Configuration configuration = new Configuration();
            
            // Set properties
            configuration.setProperty("hibernate.connection.driver_class", 
                    properties.getProperty("hibernate.connection.driver_class"));
            configuration.setProperty("hibernate.connection.url", 
                    properties.getProperty("hibernate.connection.url"));
            configuration.setProperty("hibernate.connection.username", 
                    properties.getProperty("hibernate.connection.username"));
            configuration.setProperty("hibernate.connection.password", 
                    properties.getProperty("hibernate.connection.password"));
            configuration.setProperty("hibernate.dialect", 
                    properties.getProperty("hibernate.dialect"));
            configuration.setProperty("hibernate.hbm2ddl.auto", 
                    properties.getProperty("hibernate.hbm2ddl.auto"));
            configuration.setProperty("hibernate.show_sql", 
                    properties.getProperty("hibernate.show_sql"));
            configuration.setProperty("hibernate.format_sql", 
                    properties.getProperty("hibernate.format_sql"));
            
            // Add annotated classes
            configuration.addAnnotatedClass(Personne.class);
            configuration.addAnnotatedClass(Homme.class);
            configuration.addAnnotatedClass(Femme.class);
            configuration.addAnnotatedClass(Mariage.class);
            
            // Build session factory
            sessionFactory = configuration.buildSessionFactory();
            
        } catch (IOException ex) {
            throw new ExceptionInInitializerError("Failed to initialize Hibernate: " + ex.getMessage());
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

