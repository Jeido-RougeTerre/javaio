package com.jeido.javaio.exercises.exercise3;

import java.io.Serializable;

public record Book(String title, String author) implements Serializable {
    private static final long serialVersionUID = 1L;

    @Override
    public String toString() {
        return String.format("'%s' by %s", title, author);
    }
}
