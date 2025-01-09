package com.jeido.javaio.exercises.exercise4.entity;

public record Event(String name) {
    @Override
    public String toString() {
        return "Event '" + name + '\'';
    }
}
