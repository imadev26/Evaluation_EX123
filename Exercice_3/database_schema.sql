-- Base de données générée automatiquement par Hibernate
-- Ce fichier est fourni à titre de référence uniquement

-- Création de la base de données
CREATE DATABASE IF NOT EXISTS etat_civil;
USE etat_civil;

-- Table Personne (parent)
CREATE TABLE personne (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nom VARCHAR(255),
    prenom VARCHAR(255),
    telephone VARCHAR(255),
    adresse VARCHAR(255),
    dateNaissance DATE
);

-- Table Homme (hérite de Personne)
CREATE TABLE homme (
    id INT PRIMARY KEY,
    FOREIGN KEY (id) REFERENCES personne(id)
);

-- Table Femme (hérite de Personne)
CREATE TABLE femme (
    id INT PRIMARY KEY,
    FOREIGN KEY (id) REFERENCES personne(id)
);

-- Table Mariage (relation Many-to-Many avec clé composite)
CREATE TABLE mariage (
    homme INT NOT NULL,
    femme INT NOT NULL,
    dateDebut DATE,
    dateFin DATE,
    nbrEnfant INT DEFAULT 0,
    PRIMARY KEY (homme, femme),
    FOREIGN KEY (homme) REFERENCES homme(id),
    FOREIGN KEY (femme) REFERENCES femme(id)
);

-- Exemples de requêtes utiles

-- 1. Lister toutes les femmes
SELECT p.id, p.nom, p.prenom, p.dateNaissance, p.telephone, p.adresse
FROM femme f
JOIN personne p ON f.id = p.id;

-- 2. Trouver la femme la plus âgée
SELECT p.nom, p.prenom, p.dateNaissance
FROM femme f
JOIN personne p ON f.id = p.id
ORDER BY p.dateNaissance ASC
LIMIT 1;

-- 3. Lister les épouses d'un homme donné
SELECT p.nom, p.prenom
FROM mariage m
JOIN femme f ON m.femme = f.id
JOIN personne p ON f.id = p.id
WHERE m.homme = 1;  -- Remplacer par l'ID de l'homme

-- 4. Compter les enfants d'une femme entre deux dates
SELECT COALESCE(SUM(m.nbrEnfant), 0) as total_enfants
FROM mariage m
WHERE m.femme = 1  -- Remplacer par l'ID de la femme
AND m.dateDebut BETWEEN '1990-01-01' AND '2000-12-31';

-- 5. Trouver les femmes mariées au moins 2 fois
SELECT p.nom, p.prenom, COUNT(m.homme) as nombre_mariages
FROM femme f
JOIN personne p ON f.id = p.id
LEFT JOIN mariage m ON f.id = m.femme
GROUP BY f.id, p.nom, p.prenom
HAVING COUNT(m.homme) >= 2;

-- 6. Trouver les hommes mariés à au moins 4 femmes entre deux dates
SELECT p.nom, p.prenom, COUNT(m.femme) as nombre_epouses
FROM homme h
JOIN personne p ON h.id = p.id
JOIN mariage m ON h.id = m.homme
WHERE m.dateDebut BETWEEN '1989-01-01' AND '2001-12-31'
GROUP BY h.id, p.nom, p.prenom
HAVING COUNT(m.femme) >= 4;

-- 7. Afficher tous les mariages d'un homme avec détails
SELECT 
    p.nom as nom_homme,
    p.prenom as prenom_homme,
    p2.nom as nom_femme,
    p2.prenom as prenom_femme,
    m.dateDebut,
    m.dateFin,
    m.nbrEnfant,
    CASE 
        WHEN m.dateFin IS NULL THEN 'En cours'
        ELSE 'Terminé'
    END as statut
FROM homme h
JOIN personne p ON h.id = p.id
JOIN mariage m ON h.id = m.homme
JOIN femme f ON m.femme = f.id
JOIN personne p2 ON f.id = p2.id
WHERE h.id = 1  -- Remplacer par l'ID de l'homme
ORDER BY m.dateDebut;

