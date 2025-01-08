package com.jeido.javaio.exercises.exercise3;

import java.util.Scanner;

public class Exercise3 {

    private static final Scanner in = new Scanner(System.in);
    private static final SerializeBookService bookService = new SerializeBookService();
    public static void main(String[] args) {
        while (true) {
            System.out.print("""
                    --- Menu ---
                    1. create a book
                    2. deserialize book
                    0. QUIT
                    >\s""");
            String input = in.nextLine();
            switch (input.toLowerCase()) {
                case "1" -> serialize();
                case "2" -> deserialize();
                case "0", "quit" -> {
                    return;
                }
                default -> System.out.println("Invalid input");
            }
        }
    }

    private static void serialize() {
        System.out.println("-- Create a new book --");
        System.out.print("Enter title: ");
        String title = in.nextLine();
        System.out.print("Enter author: ");
        String author = in.nextLine();
        Book book = new Book(title, author);

        bookService.serialize(book);
    }

    private static void deserialize() {
        System.out.println("-- Deserialize a book --");
        System.out.print("Enter title: ");
        String title = in.nextLine();
        Book book = bookService.deserialize(title);
        if (book != null) {
            System.out.println(book);
        } else {
            System.out.println("Book '" + title + "' not found");
        }
    }
}
