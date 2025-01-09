package com.jeido.javaio.exercises.exercise4;

import java.util.Scanner;

public class Exercise4 {
    private static final Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.print("""
                    --- Bienvenue dans le jeu d'aventure textuelle ! ---
                    1. Créer un nouveau personnage
                    2. Charger un personnage existant
                    0. Quitter
                    Choisissez une option :\s""");

            String choice = sc.nextLine();
            switch (choice) {
                case "1" -> CreateChararcter();
                case "2" -> LoadAndPlay();
                case "0" -> {
                    return;
                }
                default -> System.out.println("Choisissez une option valide !");
            }
        }
    }

    private static void CreateChararcter() {
        System.out.println("-- Création d'un personnage --");
        System.out.print("Entrez le nom de votre héros : ");
        String name = sc.nextLine();
        System.out.print("Entrez la force (1-100) : ");
        byte force = (byte)Math.clamp(sc.nextInt(), 1, 100);
        sc.nextLine();
        System.out.print("Entrez la santé (1-100) : ");
        byte hp = (byte)Math.clamp(sc.nextInt(), 1, 100);
    }
}
