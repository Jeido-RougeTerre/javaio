package com.jeido.javaio.exercises.exercise4.entity;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Monster extends LivingEntity {
    public Monster(String name, byte force, byte health) {
        super(name, force, health);
        filename = "monster.txt";
    }

    @Override
    public void save() {

        File file = new File(PATH + "monster.txt");
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

        try(BufferedWriter out = new BufferedWriter(new FileWriter(file, true))) {
            String entry = name + "," + force + "," + health + '\n';
            out.write(entry);
            System.out.println("Monster '"+ this +"' saved to " + file.getAbsolutePath());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
