# Guide d'Exécution - Application État Civil

## ⚙️ Configuration Préalable

### 1. Vérifier MySQL
Assurez-vous que MySQL est installé et en cours d'exécution :
```bash
# Vérifier le statut de MySQL
mysql --version
```

### 2. Configurer les Identifiants
Modifiez le fichier `src/main/resources/application.properties` :
```properties
hibernate.connection.username=root
hibernate.connection.password=VOTRE_MOT_DE_PASSE
```

**Note** : La base de données `etat_civil` sera créée automatiquement.

## 🚀 Compilation et Exécution

### Méthode 1 : Ligne de Commande Maven
```bash
# 1. Nettoyer et compiler
mvn clean compile

# 2. Exécuter l'application
mvn exec:java -Dexec.mainClass="org.example.App"
```

### Méthode 2 : Depuis IntelliJ IDEA
1. Ouvrir le projet dans IntelliJ IDEA
2. Attendre que Maven télécharge les dépendances
3. Clic droit sur `App.java` → "Run 'App.main()'"

### Méthode 3 : Depuis Eclipse
1. Importer le projet Maven
2. Maven → Update Project
3. Clic droit sur `App.java` → Run As → Java Application

## 📊 Résultats Attendus

L'application va :
1. ✅ Créer 10 femmes et 5 hommes
2. ✅ Créer plusieurs mariages
3. ✅ Afficher la liste complète des femmes
4. ✅ Identifier la femme la plus âgée
5. ✅ Lister les épouses d'un homme entre deux dates
6. ✅ Calculer le nombre d'enfants d'une femme
7. ✅ Trouver les femmes mariées plusieurs fois
8. ✅ Compter les hommes mariés à 4 femmes
9. ✅ Afficher les détails complets des mariages

## 📝 Exemple de Sortie Console

```
=== Création de 10 femmes ===
10 femmes créées avec succès!

=== Création de 5 hommes ===
5 hommes créés avec succès!

=== Création des mariages ===
Mariages créés avec succès!

======================================================================
=== TESTS DE L'APPLICATION ===
======================================================================

1. Liste de toutes les femmes :
----------------------------------------------------------------------
ID: 1 | RAMI SALIMA | Née le: 15/05/1970 | Tél: 0601234567 | Ville: Casablanca
ID: 2 | ALI AMAL | Née le: 20/08/1975 | Tél: 0612345678 | Ville: Rabat
...

2. Femme la plus âgée :
----------------------------------------------------------------------
La femme la plus âgée est : CHAKIR NAWAL (née le 12/02/1965)

7. Détails des mariages de SAFI SAID :
----------------------------------------------------------------------

Nom : SAFI SAID

Mariages En Cours :
1. Femme : SALIMA RAMI   Date Début : 03/09/1990    Nbr Enfants : 4
2. Femme : AMAL ALI      Date Début : 03/09/1995    Nbr Enfants : 2
3. Femme : WAFA ALAOUI   Date Début : 04/11/2000    Nbr Enfants : 3

Mariages échoués :
1. Femme : KARIMA ALAMI  Date Début : 03/09/1989    
   Date Fin : 03/09/1990    Nbr Enfants : 0
```

## 🔍 Vérification dans MySQL

Après exécution, vérifiez les données :

```sql
-- Se connecter à MySQL
mysql -u root -p

-- Utiliser la base de données
USE etat_civil;

-- Voir les tables créées
SHOW TABLES;

-- Compter les enregistrements
SELECT COUNT(*) FROM personne;
SELECT COUNT(*) FROM homme;
SELECT COUNT(*) FROM femme;
SELECT COUNT(*) FROM mariage;

-- Voir quelques données
SELECT * FROM personne LIMIT 5;
SELECT * FROM mariage;
```

## ❗ Résolution de Problèmes

### Erreur : "Access denied for user"
- Vérifiez le nom d'utilisateur et mot de passe dans `application.properties`
- Assurez-vous que MySQL est démarré

### Erreur : "Communications link failure"
- Vérifiez que MySQL est en cours d'exécution
- Vérifiez le port (par défaut 3306)

### Erreur : "No suitable driver found"
- Exécutez `mvn clean install` pour télécharger les dépendances
- Vérifiez que `mysql-connector-java` est dans le pom.xml

### Base de données ne se crée pas
- Vérifiez que `createDatabaseIfNotExist=true` est dans l'URL de connexion
- Créez manuellement la base : `CREATE DATABASE etat_civil;`

## 📦 Dépendances Maven

Les dépendances sont automatiquement téléchargées par Maven :
- Hibernate Core 5.6.15.Final
- MySQL Connector 8.0.33
- JUnit 4.13.2

## 🎯 Fonctionnalités Testées

| # | Fonctionnalité | Méthode Utilisée |
|---|----------------|------------------|
| 1 | CRUD Entités | JPA/Hibernate standard |
| 2 | Requête HQL | `getEpousesBetweenDates()` |
| 3 | Requête Native Nommée | `getNombreEnfantsBetweenDates()` |
| 4 | Requête JPQL Nommée | `getFemmesMarieesDeuxFoisOuPlus()` |
| 5 | API Criteria | `getNombreHommesMarierQuatreFemmes()` |
| 6 | Relations bidirectionnelles | Homme ↔ Mariage ↔ Femme |
| 7 | Héritage JPA | Personne → Homme/Femme |
| 8 | Clé composite | MariagePK |

## 📧 Support

En cas de problème, vérifiez :
1. Versions Java (≥ 8) et Maven (≥ 3.x)
2. Configuration MySQL
3. Fichier `application.properties`
4. Logs Hibernate dans la console

---
**Note** : La première exécution peut prendre plus de temps pour télécharger les dépendances Maven.

