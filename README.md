# Student Gotham Bat Database (SGBD)
Projet Intégratif - INSA Rouen Normandie

## Objectif du projet 
Le Student Gotham Bat Database (SGBD) est un moteur de base de données relationnelle développé en Java dans le cadre du projet intégratif.

L'objectif du projet est de concevoir et implémenter un système capable de :

- créer et supprimer des tables ;
- insérer et supprimer des données ;
- exécuter des requêtes SQL ;
- filtrer les résultats à l'aide de conditions ;
- sauvegarder et restaurer automatiquement l'état d'une base de données.

## Structure du projet

```text
Student-Gotham-Bat-Database/
├── src/fr/insarouen/iti/compilation/sgbd/
│   ├── analyseurs/
│   ├── conditions/
│   ├── exceptions/
│   ├── moteur/
│   ├── persistance/
│   └── Main.java
│
├── srctest/fr/insarouen/iti/compilation/sgbd/
│   ├── conditions/
│   ├── moteur/
│   ├── persistance/
│   └── AllTests.java
│
├── lib/
|   ├── junit4.jar
│   └── hamcrest-all.jar
│
├── makefile
├── README.md
└── .gitignore
```

## Fonctionnalités implémentées 

### Définition de données 

```sql
CREATE TABLE Heros ( id SERIAL, nom VARCHAR, age INT ); 

DROP TABLE Heros; 

TABLES;
```

### Manipulation de données 

```sql
INSERT INTO Heros (nom,age)
VALUES ("Bruce Wayne", 35);

DELETE FROM Heros
WHERE age < 18;
```

### Consultation de données 
```sql
SELECT * 
FROM Heros; 

SELECT nom, age 
FROM Heros
WHERE age > 18;
```

### Conditions supportées 

Comparateurs :

- `=`
- `<`
- `>`

Opérateurs logiques :

- `AND`
- `OR`

### Types supportés

Le moteur prend en charge les types de données suivants :

| Type      | Description                                   |
| --------- | --------------------------------------------- |
| `INT`     | Entier                                        |
| `VARCHAR` | Chaîne de caractères                          |
| `SERIAL`  | Entier auto-incrémenté généré automatiquement | 

## Compilation/ Execution 

### Exécution interactive

Pour lancer le SGBD :

```bash
make
```

Une fois lancé, l'utilisateur peut saisir directement ses commandes SQL :

```sql
CREATE TABLE Heros (
    id SERIAL,
    nom VARCHAR,
    age INT
);

INSERT INTO Heros VALUES ("Bruce Wayne", 35);

SELECT * FROM Heros;
```

### Exécution d'un fichier SQL

Il est également possible d'exécuter automatiquement un ensemble de commandes stockées dans un fichier SQL :

```bash
make < exemples/test_jointure.sql
```

Le contenu du fichier est alors lu comme s'il avait été saisi manuellement dans l'interpréteur.

## Tests

Le projet dispose de tests unitaires basés sur JUnit 4.

### Exécution des tests

```bash
make test
```

Les tests couvrent notamment :

* les opérations relationnelles ;
* l'évaluation des conditions ;
* la persistance ;
* les principales fonctionnalités du moteur.

## Persistance 

La base de données est automatiquement :

- chargée au démarrage du programme ;
- sauvegardée lors de sa fermeture.

Les données sont stockées à l'aide de la sérialisation Java.
