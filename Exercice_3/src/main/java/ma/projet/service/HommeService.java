package ma.projet.service;

import ma.projet.beans.Homme;
import ma.projet.beans.Femme;
import ma.projet.beans.Mariage;
import ma.projet.dao.IDao;
import ma.projet.util.HibernateUtil;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class HommeService implements IDao<Homme> {
    
    @Override
    public boolean create(Homme o) {
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
    public boolean update(Homme o) {
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
    public boolean delete(Homme o) {
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
    public Homme findById(int id) {
        Session session = null;
        Homme homme = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            homme = (Homme) session.get(Homme.class, id);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null) session.close();
        }
        return homme;
    }
    
    @Override
    public List<Homme> findAll() {
        Session session = null;
        List<Homme> hommes = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            hommes = session.createQuery("FROM Homme").list();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null) session.close();
        }
        return hommes;
    }
    
    /**
     * Afficher les épouses d'un homme entre deux dates
     */
    public List<Femme> getEpousesBetweenDates(Homme homme, Date dateDebut, Date dateFin) {
        Session session = null;
        List<Femme> femmes = new ArrayList<>();
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            String hql = "SELECT m.femme FROM Mariage m WHERE m.homme.id = :hommeId " +
                        "AND m.dateDebut BETWEEN :dateDebut AND :dateFin";
            femmes = session.createQuery(hql)
                    .setParameter("hommeId", homme.getId())
                    .setParameter("dateDebut", dateDebut)
                    .setParameter("dateFin", dateFin)
                    .list();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null) session.close();
        }
        return femmes;
    }
    
    /**
     * Afficher le nombre d'hommes mariés à quatre femmes entre deux dates
     * Utilisant l'API Criteria
     */
    public int getNombreHommesMarierQuatreFemmes(Date dateDebut, Date dateFin) {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            
            Criteria criteria = session.createCriteria(Mariage.class, "m");
            criteria.add(Restrictions.between("m.dateDebut", dateDebut, dateFin));
            criteria.setProjection(Projections.projectionList()
                    .add(Projections.groupProperty("m.homme.id"))
                    .add(Projections.count("m.femme.id")));
            
            List<Object[]> results = criteria.list();
            int count = 0;
            
            for (Object[] result : results) {
                Long nombreFemmes = (Long) result[1];
                if (nombreFemmes >= 4) {
                    count++;
                }
            }
            
            return count;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        } finally {
            if (session != null) session.close();
        }
    }
    
    /**
     * Afficher les mariages d'un homme avec tous les détails
     */
    public void afficherMariagesHomme(Homme homme) {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            
            Homme h = (Homme) session.get(Homme.class, homme.getId());
            
            if (h == null) {
                System.out.println("Homme non trouvé");
                return;
            }
            
            System.out.println("\nNom : " + h.getNom() + " " + h.getPrenom());
            
            // Mariages en cours
            System.out.println("\nMariages En Cours :");
            int numeroEnCours = 1;
            boolean hasMariagesEnCours = false;
            
            for (Mariage m : h.getMariages()) {
                if (m.getDateFin() == null) {
                    hasMariagesEnCours = true;
                    System.out.printf("%d. Femme : %s %s   Date Début : %td/%<tm/%<tY    Nbr Enfants : %d%n",
                            numeroEnCours++,
                            m.getFemme().getNom(),
                            m.getFemme().getPrenom(),
                            m.getDateDebut(),
                            m.getNbrEnfant());
                }
            }
            
            if (!hasMariagesEnCours) {
                System.out.println("Aucun mariage en cours");
            }
            
            // Mariages échoués
            System.out.println("\nMariages échoués :");
            int numeroEchoues = 1;
            boolean hasMariagesEchoues = false;
            
            for (Mariage m : h.getMariages()) {
                if (m.getDateFin() != null) {
                    hasMariagesEchoues = true;
                    System.out.printf("%d. Femme : %s %s  Date Début : %td/%<tm/%<tY    %n",
                            numeroEchoues++,
                            m.getFemme().getNom(),
                            m.getFemme().getPrenom(),
                            m.getDateDebut());
                    System.out.printf("   Date Fin : %td/%<tm/%<tY    Nbr Enfants : %d%n",
                            m.getDateFin(),
                            m.getNbrEnfant());
                }
            }
            
            if (!hasMariagesEchoues) {
                System.out.println("Aucun mariage échoué");
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null) session.close();
        }
    }
}

