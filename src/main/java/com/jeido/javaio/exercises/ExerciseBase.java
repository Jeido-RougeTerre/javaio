package com.jeido.javaio.exercises;

import com.jeido.javaio.ui.Ansi;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public record ExerciseBase(int id, String title, Method method) {
    @Override
    public String toString() {
        return id + ". " + processedTitle();
    }

    public void start(String[] args) {
        try {
            method.invoke(null, (Object) args);
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }


    }

    public String processedTitle() {
        boolean isReset = false;
        String[] split = title.split("`");
        StringBuilder processedStr = new StringBuilder();
        for (int i = 0; i < split.length; i++) {
            processedStr.append(split[i]).append(isReset ? " " + Ansi.RESET.getCode() : Ansi.BLACK_BACKGROUND.getCode() + (i + 1 == split.length ? Ansi.RESET.getCode() : " "));
            isReset = !isReset;
        }
        if (isReset) {
            processedStr.append(" ").append(Ansi.RESET.getCode());
        }

        return processedStr.toString();
    }
}
