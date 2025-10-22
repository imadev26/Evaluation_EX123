package ma.projet.service;

import ma.projet.classes.EmployeTache;
import ma.projet.classes.Projet;
import ma.projet.classes.Tache;
import ma.projet.dao.IDao;
import ma.projet.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class ProjetService implements IDao<Projet> {

    @Override
    public boolean create(Projet projet) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(projet);
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
    public boolean update(Projet projet) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.update(projet);
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
    public boolean delete(Projet projet) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.delete(projet);
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
    public Projet findById(int id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(Projet.class, id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Projet> findAll() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("from Projet", Projet.class).list();
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    /**
     * Afficher la liste des tâches planifiées pour un projet
     */
    public List<Tache> findTachesByProjet(int projetId) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "FROM Tache t WHERE t.projet.id = :projetId";
            return session.createQuery(hql, Tache.class)
                         .setParameter("projetId", projetId)
                         .list();
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    /**
     * Afficher la liste des tâches réalisées avec les dates réelles
     * Format attendu dans l'énoncé
     */
    public void afficherTachesRealisees(int projetId) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Projet projet = session.get(Projet.class, projetId);
            if (projet != null) {
                SimpleDateFormat sdf = new SimpleDateFormat("dd MMMM yyyy");
                SimpleDateFormat sdfShort = new SimpleDateFormat("dd/MM/yyyy");
                
                System.out.println("Projet : " + projet.getId() + "\t\tNom : " + projet.getNom() + 
                                   "\t\tDate début : " + sdf.format(projet.getDateDebut()));
                System.out.println("Liste des tâches:");
                System.out.println("Num\tNom\t\t\tDate Début Réelle\tDate Fin Réelle");
                
                // Use HQL to fetch tasks with their employee tasks
                String hql = "SELECT et FROM EmployeTache et " +
                            "JOIN et.tache t " +
                            "WHERE t.projet.id = :projetId " +
                            "ORDER BY t.id";
                List<EmployeTache> employeTaches = session.createQuery(hql, EmployeTache.class)
                                                          .setParameter("projetId", projetId)
                                                          .list();
                
                for (EmployeTache et : employeTaches) {
                    String dateDebut = et.getDateDebutReelle() != null ? 
                                       sdfShort.format(et.getDateDebutReelle()) : "N/A";
                    String dateFin = et.getDateFinReelle() != null ? 
                                     sdfShort.format(et.getDateFinReelle()) : "N/A";
                    System.out.printf("%d\t%-15s\t%s\t\t%s%n", 
                                      et.getTache().getId(), et.getTache().getNom(), dateDebut, dateFin);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

