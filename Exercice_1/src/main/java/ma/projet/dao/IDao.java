package ma.projet.dao;

import java.util.List;

public interface IDao<T> {
    
    /**
     * Crée une nouvelle entité dans la base de données
     * @param o L'entité à créer
     * @return true si la création a réussi, false sinon
     */
    boolean create(T o);
    
    /**
     * Supprime une entité de la base de données
     * @param o L'entité à supprimer
     * @return true si la suppression a réussi, false sinon
     */
    boolean delete(T o);
    
    /**
     * Met à jour une entité dans la base de données
     * @param o L'entité à mettre à jour
     * @return true si la mise à jour a réussi, false sinon
     */
    boolean update(T o);
    
    /**
     * Récupère une entité par son identifiant
     * @param id L'identifiant de l'entité
     * @return L'entité trouvée ou null
     */
    T findById(int id);
    
    /**
     * Récupère toutes les entités
     * @return Liste de toutes les entités
     */
    List<T> findAll();
}

