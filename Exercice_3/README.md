# Exercice 3 - Application de Gestion de l'État Civil

## Description
Application Java utilisant Hibernate/JPA pour gérer les citoyens et leurs relations matrimoniales dans une province.

## Structure du Projet

### 1. Couche Persistance (`ma.projet.beans`)
Les entités JPA créées avec les annotations appropriées :

- **Personne** : Classe parent avec attributs communs (id, nom, prenom, telephone, adresse, dateNaissance)
- **Homme** : Hérite de Personne, contient une liste de mariages
- **Femme** : Hérite de Personne, contient une liste de mariages
- **Mariage** : Entité représentant un mariage avec clé composite (MariagePK)
- **MariagePK** : Clé composite pour l'entité Mariage

#### Stratégies Utilisées
- **Héritage** : Strategy JOINED pour Personne/Homme/Femme
- **Relations** : @OneToMany bidirectionnelles entre Homme/Femme et Mariage
- **Requêtes Nommées** : 
  - `Femme.findMarriedTwiceOrMore` : Trouve les femmes mariées ≥2 fois
  - `Femme.countChildrenBetweenDates` : Compte les enfants entre deux dates (native)

### 2. Couche Utilitaire (`ma.projet.util`)
- **HibernateUtil** : Gestion de la SessionFactory Hibernate

### 3. Couche DAO (`ma.projet.dao`)
- **IDao<T>** : Interface générique avec CRUD de base

### 4. Couche Service (`ma.projet.service`)

#### HommeService
- CRUD de base
- `getEpousesBetweenDates()` : Retourne les épouses d'un homme entre deux dates
- `getNombreHommesMarierQuatreFemmes()` : Utilise l'API Criteria pour compter les hommes mariés à 4 femmes
- `afficherMariagesHomme()` : Affiche les détails des mariages (en cours et échoués)

#### FemmeService
- CRUD de base
- `getNombreEnfantsBetweenDates()` : Utilise une requête native nommée
- `getFemmesMarieesDeuxFoisOuPlus()` : Utilise une requête JPQL nommée
- `getFemmePlusAgee()` : Trouve la femme la plus âgée

#### MariageService
- CRUD de base pour l'entité Mariage

### 5. Programme de Test (`org.example.App`)

Le programme de test réalise les opérations suivantes :
1. ✅ Création de 10 femmes
2. ✅ Création de 5 hommes
3. ✅ Création de plusieurs mariages
4. ✅ Affichage de la liste des femmes
5. ✅ Affichage de la femme la plus âgée
6. ✅ Affichage des épouses d'un homme entre deux dates
7. ✅ Calcul du nombre d'enfants d'une femme entre deux dates
8. ✅ Liste des femmes mariées au moins deux fois
9. ✅ Nombre d'hommes mariés à 4 femmes entre deux dates
10. ✅ Détails complets des mariages d'un homme

## Configuration

### Base de Données
Fichier : `src/main/resources/application.properties`

```properties
hibernate.connection.url=jdbc:mysql://localhost:3306/etat_civil?createDatabaseIfNotExist=true
hibernate.connection.username=root
hibernate.connection.password=
```

⚠️ **Important** : Modifiez le mot de passe MySQL selon votre configuration.

### Dépendances Maven
- Hibernate Core 5.6.15.Final
- MySQL Connector 8.0.33
- JUnit 4.13.2

## Installation et Exécution

### Prérequis
- Java 8 ou supérieur
- Maven 3.x
- MySQL Server 5.7+
- Serveur MySQL en cours d'exécution

### Étapes

1. **Cloner ou extraire le projet**

2. **Configurer MySQL**
   - Démarrer le serveur MySQL
   - Modifier `application.properties` si nécessaire

3. **Compiler le projet**
   ```bash
   mvn clean compile
   ```

4. **Exécuter l'application**
   ```bash
   mvn exec:java -Dexec.mainClass="org.example.App"
   ```

## Schéma de Base de Données

La base de données `etat_civil` sera créée automatiquement avec les tables suivantes :
- `personne` : Table parent
- `homme` : Table pour les hommes
- `femme` : Table pour les femmes
- `mariage` : Table des mariages avec clé composite (homme, femme)

## Fonctionnalités Avancées

### 1. Requêtes Nommées
- **JPQL Named Query** : Pour trouver les femmes mariées plusieurs fois
- **Native Named Query** : Pour compter les enfants avec SQL natif

### 2. API Criteria
Utilisée dans `HommeService.getNombreHommesMarierQuatreFemmes()` pour construire des requêtes dynamiques complexes.

### 3. Héritage JPA
Utilisation de la stratégie JOINED pour optimiser le stockage des entités Personne/Homme/Femme.

### 4. Clés Composites
MariagePK implémente Serializable avec equals() et hashCode() pour garantir l'unicité.

## Exemple de Sortie

```
Nom : SAFI SAID

Mariages En Cours :
1. Femme : SALIMA RAMI   Date Début : 03/09/1990    Nbr Enfants : 4
2. Femme : AMAL ALI      Date Début : 03/09/1995    Nbr Enfants : 2
3. Femme : WAFA ALAOUI   Date Début : 04/11/2000    Nbr Enfants : 3

Mariages échoués :
1. Femme : KARIMA ALAMI  Date Début : 03/09/1989    
   Date Fin : 03/09/1990    Nbr Enfants : 0
```

## Architecture

```
Exercice_3/
├── pom.xml
├── README.md
└── src/
    ├── main/
    │   ├── java/
    │   │   ├── ma/projet/
    │   │   │   ├── beans/       # Entités JPA
    │   │   │   ├── dao/         # Interfaces DAO
    │   │   │   ├── service/     # Services métier
    │   │   │   └── util/        # Utilitaires (HibernateUtil)
    │   │   └── org/example/     # Application principale
    │   └── resources/
    │       └── application.properties
    └── test/
        └── java/
```

## Auteur
Exercice réalisé dans le cadre du cours de Composants Logiciels

## Licence
Projet académique

