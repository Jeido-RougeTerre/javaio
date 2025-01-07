package com.jeido.javaio;

import com.jeido.javaio.ui.ExerciseLogger;

public class Main {
    public static void main(String[] args) {
        ExerciseLogger el = ExerciseLogger.getInstance();
        el.menu();
    }
}