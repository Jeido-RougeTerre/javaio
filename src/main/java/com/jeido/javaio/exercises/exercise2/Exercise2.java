package com.jeido.javaio.exercises.exercise2;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Exercise2 {
    private static final Scanner sc = new Scanner(System.in);
    private static final String EXO_PATH = "src/main/resources/exercise2";
    private static final String LOG_PATH = EXO_PATH + "/journal.txt";
    private static final String BACKUP_PATH = EXO_PATH + "/journal_backup.dat";
    private static int nbLines;
    public static void main(String[] args) {
        while (true) {
            System.out.print("""
                    --- Menu ---
                    1. Ajouter une activité
                    2. Afficher les activités
                    3. Sauvegarder en binaire
                    4. Lire le fichier binaire
                    5. Quitter
                    Choisissez une option :\s""");
            String choix = sc.nextLine();
            switch (choix) {
                case "1" -> addActivity();
                case "2" -> printActivities();
                case "3" -> backup();
                case "4" -> readBackup();
                case "5" -> { return; }
                default -> System.out.println("Invalid option");
            }
        }
    }

    private static void addActivity() {
        System.out.print("Entrez une description de l'activité : ");
        String description = sc.nextLine();

        LocalDateTime time = LocalDateTime.now();

        String activity = time.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")) + " - " + description;

        File log = new File(LOG_PATH);

        if(!log.getParentFile().exists()) {
            log.getParentFile().mkdirs();
        }

        if (!log.exists()) {
            try {
                log.createNewFile();
                nbLines = 0;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else if (nbLines == 0) {
            try (BufferedReader br = new BufferedReader(new FileReader(log))) {
                String line;
                while ((line = br.readLine()) != null) {
                    nbLines++;
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            if (nbLines >= 5) {
                backup();
            }
        }

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(log, true))) {

            bw.write(activity + '\n');
            System.out.println("Activité ajoutée avec succès !");
            nbLines++;
            if (nbLines % 5 == 0) {
                backup();
            }

        } catch (IOException e) {
            System.err.println(e.getMessage());
        }

    }
    private static void printActivities() {
        System.out.println("--- Journal des Activités ---");
        File log = new File(LOG_PATH);
        if (log.exists()) {
            try(BufferedReader br = new BufferedReader(new FileReader(log))) {
                String line;
                while ((line = br.readLine()) != null) {
                    System.out.println(line);
                }
            } catch (IOException e) {
                System.err.println(e.getMessage());
            }
        } else {
            System.out.println("Le journal n'existe pas !");
        }
    }

    private static void backup() {
        File log = new File(LOG_PATH);
        if (!log.exists()) {
            System.out.println("Le journal n'existe pas !");
            return;
        }
        File backup = new File(BACKUP_PATH);
        if (!backup.exists()) {
            try {
                backup.createNewFile();
            } catch (IOException e) {
                System.err.println(e.getMessage());
                return;
            }
        }
        StringBuilder list = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(log))) {
            String line;
            while ((line = br.readLine()) != null) {
                list.append(line).append('\n');
            }
        } catch (IOException e) {
            System.err.println(e.getMessage());
            return;
        }

        try (OutputStream out = new BufferedOutputStream(new FileOutputStream(backup))) {
            out.write(list.toString().getBytes());
            System.out.println("Journal sauvegardé en binaire dans : " + BACKUP_PATH);
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    private static void readBackup() {
        System.out.println("--- Contenu du Fichier Binaire ---");
        File backup = new File(BACKUP_PATH);
        if (!backup.exists()) {
            System.out.println("Le journal backup n'existe pas !");
            return;
        }

        try (InputStream in = new BufferedInputStream(new FileInputStream(backup))) {
            byte[] buffer = new byte[1024];
            int bytesRead;
            StringBuilder content = new StringBuilder();
            while ((bytesRead = in.read(buffer)) != -1) {
                content.append(new String(buffer, 0, bytesRead));
            }
            System.out.println(content);
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }

    }




}
