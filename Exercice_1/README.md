# Application de Gestion de Stock - Magasin Informatique

Application Java/Hibernate pour gérer le stock d'un magasin de produits informatiques.

## 📋 Description

Cette application permet de gérer :
- **Catégories** : Classification des produits (Ordinateurs, Imprimantes, Accessoires, etc.)
- **Produits** : Articles en stock avec référence, prix et catégorie
- **Commandes** : Commandes clients avec date
- **Lignes de commande** : Détails des produits commandés avec quantités

## 🏗️ Architecture

```
src/
├── main/
│   ├── java/
│   │   └── ma/
│   │       └── projet/
│   │           ├── classes/           # Entités JPA
│   │           │   ├── Categorie.java
│   │           │   ├── Produit.java
│   │           │   ├── Commande.java
│   │           │   └── LigneCommandeProduit.java
│   │           ├── dao/               # Interface générique
│   │           │   └── IDao.java
│   │           ├── service/           # Services métier
│   │           │   ├── CategorieService.java
│   │           │   ├── ProduitService.java
│   │           │   ├── CommandeService.java
│   │           │   └── LigneCommandeService.java
│   │           ├── util/              # Utilitaires
│   │           │   └── HibernateUtil.java
│   │           └── test/              # Classes de test
│   │               ├── TestApplication.java
│   │               └── TestSimple.java
│   └── resources/
│       └── application.properties     # Configuration DB
```

## 🔧 Technologies

- **Java 8+**
- **Hibernate 5.6.15**
- **MySQL 8.0**
- **Maven** (gestion des dépendances)

## ⚙️ Configuration

### 1. Base de données

Modifiez le fichier `src/main/resources/application.properties` :

```properties
db.url=jdbc:mysql://localhost:3306/magasin_db?createDatabaseIfNotExist=true&useSSL=false&serverTimezone=UTC
db.username=root
db.password=votre_mot_de_passe
```

**Note** : La base de données `magasin_db` sera créée automatiquement si elle n'existe pas.

### 2. Installation des dépendances

```bash
mvn clean install
```

## 🚀 Exécution

### Option 1 : Test automatique (recommandé)

Exécute tous les tests automatiquement et affiche les résultats :

```bash
mvn exec:java -Dexec.mainClass="ma.projet.test.TestSimple"
```

### Option 2 : Application interactive

Application avec menu pour tester les fonctionnalités :

```bash
mvn exec:java -Dexec.mainClass="ma.projet.test.TestApplication"
```

## 📊 Fonctionnalités

### 1. Opérations CRUD (Create, Read, Update, Delete)

Toutes les entités disposent des opérations de base :
- `create(T o)` : Créer une entité
- `findById(int id)` : Récupérer une entité par ID
- `findAll()` : Récupérer toutes les entités
- `update(T o)` : Mettre à jour une entité
- `delete(T o)` : Supprimer une entité

### 2. Fonctionnalités spécifiques (ProduitService)

#### a) Afficher les produits par catégorie
```java
produitService.afficherProduitsParCategorie(categorie);
```

#### b) Afficher les produits commandés entre deux dates
```java
Date dateDebut = sdf.parse("01/03/2013");
Date dateFin = sdf.parse("31/03/2013");
produitService.afficherProduitsCommandesEntreDates(dateDebut, dateFin);
```

#### c) Afficher les produits d'une commande
Format spécifique :
```
Commande : 4     Date : 14 Mars 2013
Liste des produits :
Référence   Prix    Quantité
ES12        120 DH  7
ZR85        100 DH  14
EE85        200 DH  5
```

```java
produitService.afficherProduitsParCommande(commande);
```

#### d) Afficher les produits avec prix > 100 DH (Requête nommée)
```java
produitService.afficherProduitsAvecPrixSuperieurA100();
```

## 📝 Exemple de données de test

L'application initialise automatiquement des données de test :

### Catégories
- ORD - Ordinateurs
- IMP - Imprimantes
- ACC - Accessoires

### Produits
- ES12 (120 DH) - Ordinateurs
- ZR85 (100 DH) - Ordinateurs
- EE85 (200 DH) - Imprimantes
- AA45 (150 DH) - Imprimantes
- BB12 (50 DH) - Accessoires
- CC78 (75 DH) - Accessoires
- DD99 (300 DH) - Ordinateurs
- FF11 (95 DH) - Accessoires

### Commandes
- Commande 1 : 14/03/2013 (ES12 x7, ZR85 x14, EE85 x5)
- Commande 2 : 20/03/2013 (AA45 x3, DD99 x2)
- Commande 3 : 25/03/2013 (BB12 x10, CC78 x8)
- Commande 4 : 10/04/2013 (ES12 x5, EE85 x3)

## 🗄️ Modèle de données

### Relations
- **Categorie** (1) → (*) **Produit** : Une catégorie contient plusieurs produits
- **Produit** (*) ↔ (*) **Commande** : Relation many-to-many via **LigneCommandeProduit**
- **LigneCommandeProduit** : Table d'association avec attribut `quantite`

## 📦 Dépendances Maven

```xml
<!-- Hibernate Core -->
<dependency>
    <groupId>org.hibernate</groupId>
    <artifactId>hibernate-core</artifactId>
    <version>5.6.15.Final</version>
</dependency>

<!-- MySQL Connector -->
<dependency>
    <groupId>mysql</groupId>
    <artifactId>mysql-connector-java</artifactId>
    <version>8.0.33</version>
</dependency>
```

## ⚠️ Notes importantes

1. **MySQL doit être installé et en cours d'exécution** sur votre machine
2. Les tables seront créées automatiquement grâce à `hibernate.hbm2ddl.auto=update`
3. Modifiez les identifiants de connexion dans `application.properties`
4. La base de données `magasin_db` sera créée automatiquement

## 🐛 Résolution de problèmes

### Erreur de connexion MySQL
```
Vérifiez que :
- MySQL est démarré
- Les identifiants sont corrects
- Le port 3306 est disponible
```

### Exception Hibernate
```
Vérifiez que :
- Les dépendances Maven sont installées
- La configuration dans application.properties est correcte
```

## 👨‍💻 Auteur

Projet développé dans le cadre d'un exercice de gestion de stock.

## 📄 Licence

Ce projet est à usage éducatif.

