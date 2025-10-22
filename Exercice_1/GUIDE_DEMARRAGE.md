# 🚀 Guide de Démarrage Rapide

## ✅ Prérequis

1. **Java 8 ou supérieur** installé
   ```bash
   java -version
   ```

2. **Maven** installé
   ```bash
   mvn -version
   ```

3. **MySQL** installé et **DÉMARRÉ** ⚠️ **IMPORTANT !**
   
   **Windows - Option 1 (Recommandé) :**
   ```bash
   # Exécutez en tant qu'administrateur (clic droit)
   .\start-mysql.bat
   ```
   
   **Windows - Option 2 :**
   ```bash
   # Dans PowerShell (en tant qu'administrateur)
   net start MySQL80
   ```
   
   **Linux/Mac :**
   ```bash
   sudo service mysql start
   # OU
   sudo systemctl start mysql
   ```

   > 💡 **Aide** : Si MySQL ne démarre pas, consultez `TROUBLESHOOTING.md`

## 📝 Configuration en 3 étapes

### Étape 1 : Configurer la base de données

Ouvrez `src/main/resources/application.properties` et modifiez :

```properties
db.username=root
db.password=VOTRE_MOT_DE_PASSE_MYSQL
```

**Important** : Remplacez `VOTRE_MOT_DE_PASSE_MYSQL` par votre mot de passe MySQL réel.

### Étape 2 : Installer les dépendances

Dans le terminal, à la racine du projet :

```bash
mvn clean install
```

### Étape 3 : Lancer l'application

**Option A - Test automatique (recommandé pour la première fois)** :

```bash
mvn exec:java -Dexec.mainClass="ma.projet.test.TestSimple"
```

Cette option va :
- ✅ Créer la base de données automatiquement
- ✅ Créer les tables
- ✅ Insérer des données de test
- ✅ Exécuter tous les tests
- ✅ Afficher les résultats

**Option B - Application interactive** :

```bash
mvn exec:java -Dexec.mainClass="ma.projet.test.TestApplication"
```

Cette option ouvre un menu interactif pour tester chaque fonctionnalité individuellement.

## 📊 Que va-t-il se passer ?

### Lors du premier lancement :

1. **Connexion à MySQL** ✓
2. **Création de la base de données `magasin_db`** ✓
3. **Création des tables** ✓
   - categorie
   - produit
   - commande
   - ligne_commande_produit

4. **Insertion de données de test** ✓
   - 3 catégories
   - 8 produits
   - 4 commandes
   - 9 lignes de commande

5. **Exécution des tests** ✓
   - Affichage des produits par catégorie
   - Affichage des produits commandés entre deux dates
   - Affichage d'une commande (format spécifique)
   - Affichage des produits > 100 DH (requête nommée)

## 📋 Menu de l'application interactive

```
========================================
    GESTION DE STOCK - MENU PRINCIPAL
========================================
1. Initialiser les données de test
2. Afficher les produits par catégorie
3. Afficher les produits commandés entre deux dates
4. Afficher les produits d'une commande
5. Afficher les produits avec prix > 100 DH
6. Afficher toutes les catégories
7. Afficher tous les produits
8. Afficher toutes les commandes
0. Quitter
========================================
```

## 🎯 Exemple de résultat attendu

### Commande : Afficher une commande

```
Commande : 1     Date : 14 mars 2013
Liste des produits :
Référence       Prix            Quantité
--------------------------------------------
ES12            120 DH          7
ZR85            100 DH          14
EE85            200 DH          5
```

## ❗ Problèmes courants

### 🔴 "Communications link failure" ou "Connection refused"

**Erreur complète :**
```
ERROR: Communications link failure
The last packet sent successfully to the server was 0 milliseconds ago.
Caused by: java.net.ConnectException: Connection refused: connect
```

**Cause** : MySQL n'est pas démarré !

**Solution rapide (Windows)** :

1. **Clic droit sur `start-mysql.bat`** → **Exécuter en tant qu'administrateur**

2. OU ouvrez PowerShell en tant qu'administrateur et tapez :
   ```powershell
   net start MySQL80
   ```

3. Vérifiez que MySQL est démarré :
   ```powershell
   sc query MySQL80
   ```

4. Relancez votre application Java

**📚 Guide complet** : Consultez `TROUBLESHOOTING.md` pour plus de détails

---

### "Access denied for user 'root'@'localhost'"
➡️ **Solution** : Vérifiez votre mot de passe MySQL dans `application.properties`

### "Table doesn't exist"
➡️ **Solution** : Relancez l'application, Hibernate créera les tables automatiquement

### "Port 3306 already in use"
➡️ **Solution** : MySQL utilise déjà ce port, c'est normal. Si vous avez une erreur, vérifiez qu'aucun autre service n'utilise ce port.

## 🔍 Vérifier que tout fonctionne

Après le lancement, vous devriez voir :

```
✓ Connexion à la base de données réussie !
✓ 3 catégories créées
✓ 8 produits créés
✓ 4 commandes créées
✓ 9 lignes de commande créées

✓ Données initialisées avec succès !
```

## 📚 Pour aller plus loin

- Consultez `README.md` pour la documentation complète
- Explorez le code dans `src/main/java/ma/projet/`
- Modifiez les données de test dans `TestSimple.java`

## 💡 Conseils

1. **Première exécution** : Utilisez `TestSimple` pour voir toutes les fonctionnalités
2. **Tests personnalisés** : Utilisez `TestApplication` pour le menu interactif
3. **Données propres** : Supprimez la base de données `magasin_db` pour repartir de zéro

## 🎓 Structure du projet

```
Exercice_1/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── ma/projet/
│   │   │       ├── classes/      → Entités (Produit, Categorie, etc.)
│   │   │       ├── dao/          → Interface IDao
│   │   │       ├── service/      → Services (CRUD + méthodes métier)
│   │   │       ├── util/         → HibernateUtil
│   │   │       └── test/         → Classes de test
│   │   └── resources/
│   │       └── application.properties  → Configuration DB
├── pom.xml                → Dépendances Maven
└── README.md              → Documentation complète
```

## ✨ Bon développement !

