# Exercice : Gestion d'un Journal des Activités

## Objectif
Créer un programme Java pour gérer un journal des activités en permettant d'ajouter, d'afficher et de sauvegarder des activités. Ce programme utilisera la manipulation de fichiers texte et binaires pour gérer les données.

---

## Enoncé
Vous devez implémenter un programme qui réalise les fonctionnalités suivantes :
1. **Initialisation** :
   - Vérifiez si le fichier texte `journal.txt` existe.
   - Si le fichier n'existe pas, créez-le.
2. **Ajout d'activités** :
   - Permettez à l'utilisateur d'ajouter une activité au journal.
   - Chaque activité doit être horodatée au format `yyyy-MM-dd HH:mm:ss`.
3. **Affichage des activités** :
   - Affichez toutes les activités enregistrées dans le fichier `journal.txt`.
4. **Sauvegarde binaire** :
   - Copiez le contenu de `journal.txt` dans un fichier binaire nommé `journal_backup.dat`.
5. **Lecture binaire** :
   - Lisez et affichez le contenu du fichier binaire pour vérifier la sauvegarde.

---

## Résultat attendu dans la console
Voici un exemple de ce que l'utilisateur peut voir en exécutant le programme :

```plaintext
--- Menu ---
1. Ajouter une activité
2. Afficher les activités
3. Sauvegarder en binaire
4. Lire le fichier binaire
5. Quitter
Choisissez une option : 1

Entrez une description de l'activité : Réunion avec l'équipe
Activité ajoutée avec succès !

--- Menu ---
1. Ajouter une activité
2. Afficher les activités
3. Sauvegarder en binaire
4. Lire le fichier binaire
5. Quitter
Choisissez une option : 2

--- Journal des Activités ---
2025-01-03 14:30:45 - Réunion avec l'équipe

--- Menu ---
1. Ajouter une activité
2. Afficher les activités
3. Sauvegarder en binaire
4. Lire le fichier binaire
5. Quitter
Choisissez une option : 3

Journal sauvegardé en binaire dans : journal_backup.dat

--- Menu ---
1. Ajouter une activité
2. Afficher les activités
3. Sauvegarder en binaire
4. Lire le fichier binaire
5. Quitter
Choisissez une option : 4

--- Contenu du Fichier Binaire ---
2025-01-03 14:30:45 - Réunion avec l'équipe
```

---

## Instructions supplémentaires
- Utilisez les classes `BufferedReader` et `BufferedWriter` pour manipuler les fichiers texte.
- Utilisez les classes `BufferedInputStream` et `BufferedOutputStream` pour manipuler les fichiers binaires.
- Gérer les exceptions pour assurer la robustesse du programme.
- Implémentez un menu interactif pour permettre à l'utilisateur de tester les différentes fonctionnalités.

---
