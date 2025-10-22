package ma.projet.service;

import ma.projet.beans.Femme;
import ma.projet.dao.IDao;
import ma.projet.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;

public class FemmeService implements IDao<Femme> {
    
    @Override
    public boolean create(Femme o) {
        Session session = null;
        Transaction tx = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();
            session.save(o);
            tx.commit();
            return true;
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
            return false;
        } finally {
            if (session != null) session.close();
        }
    }
    
    @Override
    public boolean update(Femme o) {
        Session session = null;
        Transaction tx = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();
            session.update(o);
            tx.commit();
            return true;
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
            return false;
        } finally {
            if (session != null) session.close();
        }
    }
    
    @Override
    public boolean delete(Femme o) {
        Session session = null;
        Transaction tx = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();
            session.delete(o);
            tx.commit();
            return true;
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
            return false;
        } finally {
            if (session != null) session.close();
        }
    }
    
    @Override
    public Femme findById(int id) {
        Session session = null;
        Femme femme = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            femme = (Femme) session.get(Femme.class, id);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null) session.close();
        }
        return femme;
    }
    
    @Override
    public List<Femme> findAll() {
        Session session = null;
        List<Femme> femmes = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            femmes = session.createQuery("FROM Femme").list();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null) session.close();
        }
        return femmes;
    }
    
    /**
     * Retourner le nombre d'enfants d'une femme entre deux dates
     * Utilise une requête native nommée
     */
    public int getNombreEnfantsBetweenDates(Femme femme, Date dateDebut, Date dateFin) {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            
            Query query = session.createNamedQuery("Femme.countChildrenBetweenDates");
            query.setParameter("femmeId", femme.getId());
            query.setParameter("dateDebut", dateDebut);
            query.setParameter("dateFin", dateFin);
            
            Object result = query.getSingleResult();
            
            if (result instanceof BigInteger) {
                return ((BigInteger) result).intValue();
            } else if (result instanceof Long) {
                return ((Long) result).intValue();
            } else if (result instanceof Integer) {
                return (Integer) result;
            }
            
            return 0;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        } finally {
            if (session != null) session.close();
        }
    }
    
    /**
     * Retourner les femmes mariées au moins deux fois
     * Utilise une requête nommée
     */
    public List<Femme> getFemmesMarieesDeuxFoisOuPlus() {
        Session session = null;
        List<Femme> femmes = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            femmes = session.createNamedQuery("Femme.findMarriedTwiceOrMore", Femme.class).list();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null) session.close();
        }
        return femmes;
    }
    
    /**
     * Trouver la femme la plus âgée
     */
    public Femme getFemmePlusAgee() {
        Session session = null;
        Femme femme = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            String hql = "FROM Femme f ORDER BY f.dateNaissance ASC";
            List<Femme> femmes = session.createQuery(hql).setMaxResults(1).list();
            if (femmes != null && !femmes.isEmpty()) {
                femme = femmes.get(0);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null) session.close();
        }
        return femme;
    }
}

