# 📚 Evaluation EX123 - Applications Java Hibernate

[![Java](https://img.shields.io/badge/Java-8+-orange.svg)](https://www.oracle.com/java/)
[![Hibernate](https://img.shields.io/badge/Hibernate-5.6.15-green.svg)](https://hibernate.org/)
[![MySQL](https://img.shields.io/badge/MySQL-8.0-blue.svg)](https://www.mysql.com/)
[![Maven](https://img.shields.io/badge/Maven-3.x-red.svg)](https://maven.apache.org/)

Collection de trois applications Java utilisant **Hibernate/JPA** pour démontrer différentes architectures et patterns de persistance de données.

---

## 📋 Vue d'ensemble

Ce repository contient trois exercices pratiques développés dans le cadre d'une évaluation sur les composants logiciels et l'utilisation de frameworks ORM (Object-Relational Mapping).

| Exercice | Description | Technologies Clés |
|----------|-------------|-------------------|
| **[Exercice 1](#-exercice-1---gestion-de-stock)** | Gestion de stock pour magasin informatique | Hibernate, Relations Many-to-Many, Requêtes nommées |
| **[Exercice 2](#-exercice-2---gestion-de-projets)** | Gestion de projets et tâches d'employés | API Criteria, Associations complexes |
| **[Exercice 3](#-exercice-3---gestion-de-létat-civil)** | Gestion de l'état civil (mariages) | Héritage JPA, Clés composites, Requêtes natives |

---

## 🎯 Exercice 1 - Gestion de Stock

### Description
Application complète de gestion de stock pour un magasin de produits informatiques avec système de commandes.

### 🎬 Démonstration

https://github.com/imadev26/Evaluation_EX123/raw/main/EX1.mp4

*[📥 Télécharger la vidéo](EX1.mp4) ou [▶️ Voir en ligne](https://github.com/imadev26/Evaluation_EX123/blob/main/EX1.mp4)*

### Fonctionnalités principales
- ✅ **CRUD complet** sur Catégories, Produits, Commandes
- ✅ **Filtrage avancé** : Produits par catégorie, par date de commande
- ✅ **Relations Many-to-Many** : Produits ↔ Commandes via table d'association
- ✅ **Requêtes nommées** : Recherche des produits avec prix > 100 DH
- ✅ **Rapports** : Affichage détaillé des commandes avec format personnalisé

### Structure
```
Exercice_1/
├── classes/          # Entités (Categorie, Produit, Commande, LigneCommandeProduit)
├── service/          # Services métier avec logique business
├── test/             # Tests et démonstrations
└── README.md         # Documentation détaillée
```

### Démarrage rapide
```bash
cd Exercice_1
mvn clean install
mvn exec:java -Dexec.mainClass="ma.projet.test.TestApplication"
```

### Technologies
- **Hibernate 5.6.15**
- **MySQL 8.0**
- **JPA Annotations**
- **Named Queries**

📖 [Documentation complète →](Exercice_1/README.md)

---

## 🎯 Exercice 2 - Gestion de Projets

### Description
Système de gestion de projets pour bureau d'études avec suivi des tâches et affectations des employés.

### 🎬 Démonstration

https://github.com/imadev26/Evaluation_EX123/raw/main/EX2.mp4

*[📥 Télécharger la vidéo](EX2.mp4) ou [▶️ Voir en ligne](https://github.com/imadev26/Evaluation_EX123/blob/main/EX2.mp4)*

### Fonctionnalités principales
- ✅ **Gestion des projets** : Création, suivi, planification
- ✅ **Gestion des tâches** : Attribution, prix, dates planifiées
- ✅ **Gestion des employés** : Profils, responsabilités, historique
- ✅ **API Criteria** : Requêtes dynamiques complexes
- ✅ **Associations avancées** : Table d'association avec attributs (dates réelles)
- ✅ **Rapports** : Tâches réalisées, projets gérés, analyses temporelles

### Structure
```
Exercice_2/
├── classes/          # Entités (Employe, Projet, Tache, EmployeTache)
├── service/          # Services avec requêtes Criteria
├── test/             # Tests CRUD et fonctionnels
└── README.md         # Documentation détaillée
```

### Démarrage rapide
```bash
cd Exercice_2
mvn clean install
mvn exec:java -Dexec.mainClass="ma.projet.test.TestApplication"
```

### Technologies
- **Hibernate Criteria API**
- **Relations Many-to-Many avec attributs**
- **Named Queries JPQL**
- **Date/Time handling**

📖 [Documentation complète →](Exercice_2/README.md)

---

## 🎯 Exercice 3 - Gestion de l'État Civil

### Description
Application sophistiquée pour gérer les citoyens et leurs relations matrimoniales avec gestion des héritages et clés composites.

### 🎬 Démonstration

https://github.com/imadev26/Evaluation_EX123/raw/main/EX3.mp4

*[📥 Télécharger la vidéo](EX3.mp4) ou [▶️ Voir en ligne](https://github.com/imadev26/Evaluation_EX123/blob/main/EX3.mp4)*

### Fonctionnalités principales
- ✅ **Héritage JPA** : Stratégie JOINED (Personne → Homme/Femme)
- ✅ **Clés composites** : Gestion des mariages avec MariagePK
- ✅ **Relations bidirectionnelles** : Homme ↔ Mariage ↔ Femme
- ✅ **Requêtes natives** : Comptage d'enfants avec SQL natif
- ✅ **Requêtes JPQL nommées** : Femmes mariées plusieurs fois
- ✅ **API Criteria avancée** : Hommes mariés à 4 femmes
- ✅ **Rapports détaillés** : Mariages en cours et échoués

### Structure
```
Exercice_3/
├── beans/            # Entités avec héritage (Personne, Homme, Femme, Mariage)
├── service/          # Services avec requêtes natives et JPQL
├── database_schema.sql # Schéma SQL de référence
└── README.md         # Documentation détaillée
```

### Démarrage rapide
```bash
cd Exercice_3
mvn clean install
mvn exec:java -Dexec.mainClass="org.example.App"
```

### Technologies
- **JPA Inheritance (JOINED strategy)**
- **Composite Primary Keys**
- **Native SQL Queries**
- **JPQL Named Queries**
- **Bidirectional Relations**

📖 [Documentation complète →](Exercice_3/README.md)

---

## 🛠️ Prérequis Généraux

Avant d'exécuter n'importe quel exercice, assurez-vous d'avoir installé :

| Outil | Version Minimale | Vérification |
|-------|------------------|--------------|
| **Java JDK** | 8+ | `java -version` |
| **Maven** | 3.x | `mvn -version` |
| **MySQL** | 5.7+ | `mysql --version` |
| **Git** | 2.x | `git --version` |

---

## 🚀 Installation et Configuration

### 1. Cloner le repository
```bash
git clone https://github.com/imadev26/Evaluation_EX123.git
cd Evaluation_EX123
```

### 2. Configurer MySQL

**Démarrer le serveur MySQL** :
```bash
# Windows
net start MySQL80

# Linux/Mac
sudo systemctl start mysql
# ou
sudo service mysql start
```

**Créer les bases de données** (optionnel, création automatique activée) :
```sql
CREATE DATABASE magasin_db;      -- Pour Exercice_1
CREATE DATABASE gestion_projets; -- Pour Exercice_2
CREATE DATABASE etat_civil;      -- Pour Exercice_3
```

### 3. Configurer les identifiants

Pour chaque exercice, modifiez le fichier `src/main/resources/application.properties` :

```properties
hibernate.connection.username=root
hibernate.connection.password=VOTRE_MOT_DE_PASSE
```

### 4. Compiler et exécuter

Chaque exercice est indépendant :

```bash
# Exercice 1
cd Exercice_1
mvn clean install
mvn exec:java -Dexec.mainClass="ma.projet.test.TestApplication"

# Exercice 2
cd Exercice_2
mvn clean install
mvn exec:java -Dexec.mainClass="ma.projet.test.TestApplication"

# Exercice 3
cd Exercice_3
mvn clean install
mvn exec:java -Dexec.mainClass="org.example.App"
```

---

## 📚 Concepts Hibernate/JPA Démontrés

### Patterns et Techniques

| Concept | Exercice 1 | Exercice 2 | Exercice 3 |
|---------|:----------:|:----------:|:----------:|
| **Entités JPA** | ✅ | ✅ | ✅ |
| **Relations One-to-Many** | ✅ | ✅ | ✅ |
| **Relations Many-to-Many** | ✅ | ✅ | ✅ |
| **Table d'association avec attributs** | ✅ | ✅ | - |
| **Héritage JPA (JOINED)** | - | - | ✅ |
| **Clés composites** | ✅ | ✅ | ✅ |
| **Named Queries (JPQL)** | ✅ | ✅ | ✅ |
| **Native SQL Queries** | - | - | ✅ |
| **Criteria API** | - | ✅ | ✅ |
| **Relations bidirectionnelles** | ✅ | ✅ | ✅ |
| **Cascade operations** | ✅ | ✅ | ✅ |
| **FetchType strategies** | ✅ | ✅ | ✅ |

### Architecture Générale

```
┌─────────────────────────────────────────┐
│           Couche Présentation           │
│        (Tests / Applications)           │
└─────────────────┬───────────────────────┘
                  │
┌─────────────────▼───────────────────────┐
│           Couche Service                │
│    (Logique métier + Requêtes)          │
└─────────────────┬───────────────────────┘
                  │
┌─────────────────▼───────────────────────┐
│              IDao<T>                    │
│      (Interface générique CRUD)         │
└─────────────────┬───────────────────────┘
                  │
┌─────────────────▼───────────────────────┐
│            Hibernate                    │
│         (HibernateUtil)                 │
└─────────────────┬───────────────────────┘
                  │
┌─────────────────▼───────────────────────┐
│              MySQL                      │
│         (Base de données)               │
└─────────────────────────────────────────┘
```

---

## 🔍 Points Techniques Importants

### 1. Gestion des Sessions Hibernate
Tous les exercices utilisent un pattern de gestion de session cohérent :
```java
Session session = HibernateUtil.getSessionFactory().openSession();
Transaction tx = session.beginTransaction();
try {
    // Opérations
    tx.commit();
} catch (Exception e) {
    if (tx != null) tx.rollback();
    throw e;
} finally {
    session.close();
}
```

### 2. Configuration Hibernate
Configuration centralisée dans `application.properties` :
- Auto-création des tables : `hibernate.hbm2ddl.auto=update`
- Affichage SQL : `hibernate.show_sql=true`
- Formatage SQL : `hibernate.format_sql=true`
- Dialecte MySQL : `hibernate.dialect=org.hibernate.dialect.MySQL8Dialect`

### 3. Pattern DAO Générique
Interface `IDao<T>` implémentée par tous les services :
```java
T create(T o);
T findById(int id);
List<T> findAll();
T update(T o);
void delete(T o);
```

---

## 📊 Statistiques du Projet

- **Nombre de classes** : 45+
- **Lignes de code** : ~3500
- **Entités JPA** : 12
- **Services métier** : 11
- **Tests fonctionnels** : 8+
- **Requêtes nommées** : 5
- **Relations** : 15+

---

## 🎓 Objectifs Pédagogiques

Ce projet démontre la maîtrise de :
1. ✅ **ORM Hibernate/JPA** : Mapping objet-relationnel complet
2. ✅ **Persistence** : CRUD, transactions, gestion de sessions
3. ✅ **Relations** : One-to-Many, Many-to-Many, bidirectionnelles
4. ✅ **Requêtes** : JPQL, SQL natif, Criteria API
5. ✅ **Patterns** : DAO, Service Layer, Dependency Management
6. ✅ **Architecture** : Séparation des couches, modularité
7. ✅ **Bonnes pratiques** : Gestion d'erreurs, transactions, nettoyage

---

## 📝 Notes Importantes

### Sécurité
⚠️ **Important** : Ces applications sont à usage éducatif. Pour un environnement de production :
- Utiliser des variables d'environnement pour les credentials
- Implémenter un système de validation
- Ajouter la gestion des exceptions personnalisées
- Implémenter la sécurité (authentification/autorisation)

### Performance
💡 **Optimisations** :
- Utiliser `@BatchSize` pour les collections
- Configurer `FetchType.LAZY` pour les relations lourdes
- Implémenter un cache de second niveau (Ehcache)
- Utiliser des projections pour les requêtes lourdes

### Tests
🧪 **Tests** :
- Tests fonctionnels inclus dans chaque exercice
- Données de test automatiquement générées
- Scénarios d'utilisation réels

---

## 🤝 Contribution

Ce projet est réalisé dans un cadre académique. Les suggestions d'amélioration sont les bienvenues !

---

## 👨‍💻 Auteur

**Imad ADAOUMOUM**
- GitHub: [@imadev26](https://github.com/imadev26)
- Repository: [Evaluation_EX123](https://github.com/imadev26/Evaluation_EX123)

---

## 📄 Licence

Projet académique - À usage éducatif uniquement

---

## 🔗 Liens Utiles

- [Documentation Hibernate](https://hibernate.org/orm/documentation/)
- [JPA Specification](https://jakarta.ee/specifications/persistence/)
- [Maven Guide](https://maven.apache.org/guides/)
- [MySQL Documentation](https://dev.mysql.com/doc/)

---

## 📞 Support

Pour toute question ou problème :
1. Consultez le README de chaque exercice
2. Vérifiez les fichiers `TROUBLESHOOTING.md` (Exercice 1)
3. Ouvrez une issue sur GitHub

---

<div align="center">
  
**⭐ Si ce projet vous a été utile, n'hésitez pas à lui donner une étoile ! ⭐**

Made with ❤️ using Java, Hibernate & MySQL

</div>

