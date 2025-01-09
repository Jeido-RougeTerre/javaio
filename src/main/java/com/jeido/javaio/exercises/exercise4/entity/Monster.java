package com.jeido.javaio.exercises.exercise4.entity;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Monster extends LivingEntity {
    private static final String FILE_NAME = "monster.txt";
    public Monster(String name, byte force, byte health) {
        super(name, force, health);
        filename = "monster.txt";
    }

    public static Monster parse(String entry) {
        String[] parts = entry.split(",");
        return new Monster(parts[0], Byte.parseByte(parts[1]), Byte.parseByte(parts[2]));
    }

    public static List<Monster> load() {
        List<Monster> entities = new ArrayList<>();
        File file = new File(PATH + FILE_NAME);
        if (!file.exists()) {
            return entities;
        }

        StringBuilder content = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(PATH + FILE_NAME))) {
            String line;
            while ((line = br.readLine()) != null) {
                content.append(line).append("\n");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        for (String monsterSTR : content.toString().split("\n")) {
            entities.add(parse(monsterSTR));
        }
        return entities;
    }

}
