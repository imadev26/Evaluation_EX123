package ma.projet.util;

import ma.projet.classes.*;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.io.InputStream;
import java.util.Properties;

public class HibernateUtil {
    private static SessionFactory sessionFactory;
    
    static {
        try {
            Configuration configuration = new Configuration();
            
            // Load properties from application.properties
            Properties properties = new Properties();
            InputStream inputStream = HibernateUtil.class.getClassLoader()
                    .getResourceAsStream("application.properties");
            
            if (inputStream != null) {
                properties.load(inputStream);
                inputStream.close();
            }
            
            // Set properties
            configuration.setProperties(properties);
            
            // Add annotated classes
            configuration.addAnnotatedClass(Employe.class);
            configuration.addAnnotatedClass(Projet.class);
            configuration.addAnnotatedClass(Tache.class);
            configuration.addAnnotatedClass(EmployeTache.class);
            
            // Build SessionFactory
            sessionFactory = configuration.buildSessionFactory();
            
        } catch (Exception e) {
            e.printStackTrace();
            throw new ExceptionInInitializerError("Initial SessionFactory creation failed: " + e);
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

