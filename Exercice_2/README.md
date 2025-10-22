# Exercice 2 - Application de Gestion de Projets

Application Java utilisant Hibernate pour gérer des projets, tâches et employés dans un bureau d'études.

## Structure du Projet

### Couche Persistance (ma.projet.classes)
- **Employe** : Entité représentant un employé
- **Projet** : Entité représentant un projet
- **Tache** : Entité représentant une tâche
- **EmployeTache** : Table d'association avec dates réelles
- **EmployeTachePK** : Clé primaire composite pour EmployeTache

### Couche Service (ma.projet.service)
- **IDao** : Interface générique pour les opérations CRUD
- **EmployeService** : Gestion des employés
  - Afficher les tâches réalisées par un employé
  - Afficher les projets gérés par un employé
- **ProjetService** : Gestion des projets
  - Afficher les tâches planifiées pour un projet
  - Afficher les tâches réalisées avec dates réelles
- **TacheService** : Gestion des tâches
  - Afficher les tâches dont le prix > 1000 DH (requête nommée)
  - Afficher les tâches réalisées entre deux dates
- **EmployeTacheService** : Gestion des associations employés-tâches

### Utilitaires (ma.projet.util)
- **HibernateUtil** : Configuration et gestion de la SessionFactory Hibernate

## Configuration

### Base de données (application.properties)
```properties
hibernate.connection.url=jdbc:mysql://localhost:3306/gestion_projets
hibernate.connection.username=root
hibernate.connection.password=
```

⚠️ **Important** : Modifiez le mot de passe dans `src/main/resources/application.properties` selon votre configuration MySQL.

## Prérequis

1. Java 8 ou supérieur
2. MySQL 8.0 ou supérieur
3. Maven 3.x

## Installation et Exécution

### 1. Configurer la base de données

```bash
# Créer la base de données (optionnel, elle sera créée automatiquement)
mysql -u root -p
CREATE DATABASE gestion_projets;
```

### 2. Modifier application.properties

Ajustez le mot de passe MySQL dans `src/main/resources/application.properties` :
```properties
hibernate.connection.password=VOTRE_MOT_DE_PASSE
```

### 3. Compiler le projet

```bash
mvn clean compile
```

### 4. Exécuter les tests

**Test complet de l'application :**
```bash
mvn exec:java -Dexec.mainClass="ma.projet.test.TestApplication"
```

**Test des opérations CRUD :**
```bash
mvn exec:java -Dexec.mainClass="ma.projet.test.TestCRUD"
```

## Fonctionnalités Testées

### TestApplication

1. ✅ Création des entités (Employés, Projets, Tâches)
2. ✅ Association employés-tâches avec dates réelles
3. ✅ Affichage des tâches réalisées par un employé
4. ✅ Affichage des projets gérés par un employé
5. ✅ Affichage des tâches planifiées pour un projet
6. ✅ Affichage des tâches réalisées avec format personnalisé
7. ✅ Recherche des tâches dont le prix > 1000 DH (requête nommée)
8. ✅ Recherche des tâches réalisées entre deux dates

### TestCRUD

1. ✅ Test CREATE (création d'entités)
2. ✅ Test READ (lecture d'entités)
3. ✅ Test UPDATE (modification d'entités)
4. ✅ Test DELETE (suppression d'entités)

## Exemple de Sortie

```
Projet : 4      Nom : Gestion de stock     Date début : 14 Janvier 2013
Liste des tâches:
Num Nom            Date Début Réelle   Date Fin Réelle
12  Analyse        10/02/2013          20/02/2013
13  Conception     10/03/2013          15/03/2013
14  Développement  10/04/2013          25/04/2013
```

## Technologies Utilisées

- **Java 8**
- **Hibernate 5.6.15** (ORM)
- **MySQL 8.0** (SGBD)
- **Maven** (Gestion de dépendances)
- **JPA Annotations** (Mapping objet-relationnel)

## Structure de la Base de Données

### Tables Créées Automatiquement

- `employe` : Informations sur les employés
- `projet` : Informations sur les projets
- `tache` : Informations sur les tâches
- `employe_tache` : Table d'association avec dates réelles

### Relations

- Un employé peut gérer plusieurs projets (1:N)
- Un projet contient plusieurs tâches (1:N)
- Un employé peut réaliser plusieurs tâches (N:N via EmployeTache)
- Une tâche peut être réalisée par plusieurs employés (N:N via EmployeTache)

## Auteur

Projet académique - Exercice 2 : Application de Gestion de Projets

