# 🔧 Guide de Dépannage

## Erreur : "Communications link failure" ou "Connection refused"

### 🔍 Diagnostic

Cette erreur signifie que l'application ne peut pas se connecter à MySQL. Causes possibles :
1. **MySQL n'est pas démarré** ← Cause la plus fréquente
2. MySQL n'est pas installé
3. Le port 3306 est bloqué ou utilisé par un autre service
4. Mauvais identifiants de connexion

---

## ✅ Solution 1 : Démarrer MySQL (Windows)

### Méthode A : Via les Services Windows

1. Appuyez sur `Windows + R`
2. Tapez `services.msc` et appuyez sur **Entrée**
3. Dans la liste, cherchez :
   - **MySQL**
   - **MySQL80** (ou MySQL57, MySQL81, selon votre version)
4. Clic droit sur le service MySQL → **Démarrer**
5. ✅ Attendez que le statut devienne **"Démarré"**

### Méthode B : Via l'invite de commandes (Administrateur)

1. Ouvrez **PowerShell** ou **CMD** en tant qu'administrateur
2. Exécutez :

```powershell
# Pour MySQL 8.0
net start MySQL80

# OU pour d'autres versions
net start MySQL
```

3. Vous devriez voir :
```
Le service MySQL80 démarre.
Le service MySQL80 a démarré avec succès.
```

### Méthode C : Via MySQL Workbench

1. Ouvrez **MySQL Workbench**
2. Cliquez sur votre connexion locale
3. Si MySQL n'est pas démarré, Workbench proposera de le démarrer

---

## ✅ Solution 2 : Vérifier si MySQL est installé

### Windows

Ouvrez PowerShell et tapez :

```powershell
# Vérifier si MySQL est installé
Get-Service -Name MySQL* | Format-Table -Property Name, Status, DisplayName

# OU
mysql --version
```

### Si MySQL n'est pas installé :

**Téléchargez et installez MySQL :**
1. Allez sur : https://dev.mysql.com/downloads/installer/
2. Téléchargez **MySQL Installer for Windows**
3. Installez **MySQL Server** avec les paramètres par défaut
4. **Important** : Notez le mot de passe root que vous définissez !

---

## ✅ Solution 3 : Vérifier les identifiants de connexion

Ouvrez `src/main/resources/application.properties` et vérifiez :

```properties
db.url=jdbc:mysql://localhost:3306/magasin_db?createDatabaseIfNotExist=true&useSSL=false&serverTimezone=UTC
db.username=root
db.password=VOTRE_MOT_DE_PASSE_ICI  ← Vérifiez ce mot de passe !
```

**Test de connexion :**

```bash
mysql -u root -p
```

Si ça fonctionne, MySQL est bien démarré !

---

## ✅ Solution 4 : Vérifier que le port 3306 est libre

### Windows PowerShell :

```powershell
# Vérifier quel processus utilise le port 3306
netstat -ano | findstr :3306
```

Si un processus autre que MySQL utilise ce port, vous devez :
- Arrêter ce processus
- OU changer le port MySQL dans `application.properties`

---

## 🎯 Après avoir démarré MySQL

1. **Redémarrez votre application** :
   ```bash
   mvn exec:java -Dexec.mainClass="ma.projet.test.TestApplication"
   ```

2. Vous devriez voir :
   ```
   ✓ Connexion à la base de données réussie !
   ```

---

## 🚨 Erreurs fréquentes et solutions

### Erreur : "Access denied for user 'root'@'localhost'"

**Cause** : Mauvais mot de passe MySQL

**Solution** : Modifiez le mot de passe dans `application.properties`

```properties
db.password=votre_vrai_mot_de_passe
```

---

### Erreur : "Unknown database 'magasin_db'"

**Cause** : La base de données n'existe pas et ne peut pas être créée automatiquement

**Solution** : Créez la base manuellement

```sql
mysql -u root -p
CREATE DATABASE magasin_db CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
EXIT;
```

---

### Erreur : "Table doesn't exist"

**Cause** : Les tables n'ont pas été créées

**Solution** : Hibernate les créera automatiquement au premier lancement. Assurez-vous que :

```properties
hibernate.hbm2ddl.auto=update
```

est bien dans `application.properties`.

---

## 📋 Checklist de vérification

Avant de lancer l'application, vérifiez :

- [ ] MySQL est installé
- [ ] MySQL est démarré (visible dans services.msc)
- [ ] Le port 3306 est accessible
- [ ] Les identifiants dans `application.properties` sont corrects
- [ ] Vous pouvez vous connecter avec `mysql -u root -p`

---

## 🆘 Commandes utiles MySQL (Windows)

```powershell
# Démarrer MySQL
net start MySQL80

# Arrêter MySQL
net stop MySQL80

# Redémarrer MySQL
net stop MySQL80 && net start MySQL80

# Vérifier le statut
sc query MySQL80

# Se connecter à MySQL
mysql -u root -p

# Afficher les bases de données
mysql -u root -p -e "SHOW DATABASES;"

# Créer la base de données manuellement
mysql -u root -p -e "CREATE DATABASE magasin_db;"
```

---

## 💡 Configuration MySQL pour démarrage automatique

Pour que MySQL démarre automatiquement avec Windows :

1. Ouvrez `services.msc`
2. Trouvez **MySQL80**
3. Clic droit → **Propriétés**
4. **Type de démarrage** → **Automatique**
5. Cliquez **OK**

Maintenant MySQL démarrera automatiquement à chaque démarrage de Windows !

---

## 🔗 Ressources utiles

- [Documentation MySQL](https://dev.mysql.com/doc/)
- [MySQL Workbench](https://www.mysql.com/products/workbench/)
- [Télécharger MySQL](https://dev.mysql.com/downloads/mysql/)

---

## ❓ Toujours bloqué ?

Vérifiez les logs MySQL :
- Windows : `C:\ProgramData\MySQL\MySQL Server 8.0\Data\*.err`

Ou contactez votre administrateur système.

