package com.jeido.javaio.exercises.exercise4.entity;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Character extends LivingEntity {
    private static final String FILE_NAME = "character.dat";
    public Character(String name, byte force, byte health) {
        super(name, force, health);
        filename = FILE_NAME;
    }


    public static Character parse(String entry) {
        String[] parts = entry.split(",");
        return new Character(parts[0], Byte.parseByte(parts[1]), Byte.parseByte(parts[2]));
    }

    public static List<Character> load() {

        List<Character> entities = new ArrayList<>();
        File file = new File(PATH + FILE_NAME);
        if (!file.exists()) {
            return entities;
        }

        StringBuilder content = new StringBuilder();
        try (BufferedInputStream in = new BufferedInputStream(new FileInputStream(file))) {
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = in.read()) != -1) {
                content.append(new String(buffer, 0, bytesRead));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        for (String characterSTR : content.toString().split("\n")) {
            entities.add(parse(characterSTR));
        }
        return entities;
    }

}
