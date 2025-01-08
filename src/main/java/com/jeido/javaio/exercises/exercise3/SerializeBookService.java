package com.jeido.javaio.exercises.exercise3;

import java.io.*;

public class SerializeBookService {
    private static final String PATH = "src/main/resources/exercise3/";

    public void serialize(Book book) {
        final String FILENAME = "book." + book.title().toLowerCase().replace(" ", "_") + ".ser";
        File file = new File(PATH + FILENAME);
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(PATH + FILENAME))) {
            oos.writeObject(book);
            System.out.println("Book : " + book + " serialized successfully to " + PATH + FILENAME);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Book deserialize(String filename) {
        if (!filename.endsWith(".ser")) {
            filename = filename + ".ser";
        }

        if (!filename.startsWith("book.")) {
            filename = "book." + filename;
        }

        Book book = null;
         try (ObjectInputStream is = new ObjectInputStream(new FileInputStream(PATH + filename))) {
             book = (Book) is.readObject();
             System.out.println("Book : " + book + " deserialized successfully from " + PATH + filename);
         } catch (IOException | ClassNotFoundException e) {
             e.printStackTrace();
         }
         return book;
    }
}
