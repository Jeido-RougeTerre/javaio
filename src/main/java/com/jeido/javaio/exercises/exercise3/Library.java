package com.jeido.javaio.exercises.exercise3;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Library {
    private static final String PATH = "src/main/resources/exercise3/library.ser";
    private static final Scanner sc = new Scanner(System.in);
    private List<Book> books;

    public Library() {
        books = new ArrayList<Book>();
    }

    private void loadBooks() {
        File file = new File(PATH);
        if (file.exists()) {
            try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(file))) {
                books = (List<Book>) in.readObject();
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    private void addBook() {
        System.out.println("-- Create a new book --");
        System.out.print("Enter title: ");
        String title = sc.nextLine();
        System.out.print("Enter author: ");
        String author = sc.nextLine();
        books.add(new Book(title, author));
    }

    private void displayBooks() {
        if (books.isEmpty()) {
            System.out.println("-- No books found --");
        } else {
            for (Book book : books) {
                System.out.println(book);
            }
        }

    }

    private void saveLibrary() {
        File file = new File(PATH);
        if (!file.exists()) {
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }
            try {
                file.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(PATH))) {
            out.writeObject(books);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void start() {
        while (true) {
            System.out.print("""
                    -- Library --
                    1. Add a book
                    2. Display Book
                    0. QUIT
                    > \s""");
            String choice = sc.nextLine();
            switch (choice) {
                case "1" -> addBook();
                case "2" -> displayBooks();
                case "0" -> {
                    saveLibrary();
                    return;
                }
                default -> System.out.println("Invalid choice");
            }
        }
    }
}
