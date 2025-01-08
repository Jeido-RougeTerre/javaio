package com.jeido.javaio.exercises.exercise4.entity;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public abstract class LivingEntity {
    protected static final String PATH = "src/main/resources/exercise4/";
    protected final String name;
    protected byte force;
    protected byte health;
    protected boolean isAlive;
    protected String filename;

    public LivingEntity(String name, byte force, byte health) {
        this.name = name;
        this.force = force;
        this.health = health;
        this.isAlive = true;
    }

    public String getName() {
        return name;
    }

    public byte getForce() {
        return force;
    }

    public void setForce(byte force) {
        this.force = force;
    }

    public byte getHealth() {
        return health;
    }

    public void setHealth(byte health) {
        this.health = health;
    }

    public boolean checkIsAlive() {
        if (isAlive) {
            if (health == 0) {
                isAlive = false;
                return false;
            }
            return true;
        }
        return false;
    }

    public byte fight(LivingEntity target) {
        if (!checkIsAlive()) return 0;
        if (!target.checkIsAlive()) return 0;

        short forceDelta = (short) (this.getForce() - target.getForce());
        int maxDealt;
        int maxTaken;
        if (forceDelta >= 0) { // target is weaker
            maxDealt = 10;
            maxTaken = 4;
        } else {
            maxDealt = 4;
            maxTaken = 10;
        }

        this.health += (byte) (Math.random() * maxTaken);
        checkIsAlive();
        target.health += (byte) (Math.random() * maxDealt);
        target.checkIsAlive();

        return this.health;
    }

    @Override
    public String toString() {
        return String.format("'%s' %dhp (FOR : %d)", name, health, force);
    }

    public static void save(LivingEntity entity) {
        File file = new File(PATH + entity.filename);
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

        String entry = entity.name + "," + entity.force + "," + entity.health + '\n';
        if (entity.filename.endsWith(".dat")) {
            try (BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(file, true))) {
                out.write(entry.getBytes());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else {
            try (BufferedWriter out = new BufferedWriter(new FileWriter(file, true))) {
                out.write(entry);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }


        System.out.println("saved " + entity + " to " + file.getAbsolutePath());

    }

}
