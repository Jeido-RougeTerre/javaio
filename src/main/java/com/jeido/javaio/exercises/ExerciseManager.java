package com.jeido.javaio.exercises;

import java.io.File;
import java.util.*;

public class ExerciseManager {
    private static ExerciseManager instance;
    private final List<ExerciseBase> exercises;

    public static ExerciseManager getInstance() {
        if (instance == null) {
            instance = new ExerciseManager();
        }
        return instance;
    }

    private ExerciseManager() {
        exercises = new ArrayList<>();

        String p = ExerciseManager.class.getPackage().getName();

        //get the exercise packages count
        String path = ("src.main.java." + p).replace(".", File.separator);
        File file = new File(path);
        int count =  Arrays.stream(Objects.requireNonNull(file.list())).filter(s -> s.matches("^exercise[0-9]+$")).toList().size();

        for (int i = 1; i <= count; i++) {
            try {
                Class<?> exerciseClass = Class.forName( p +  ".exercise" + i + ".Exercise" + i);

                String exerciseName = "Exercise " + i;
                String readmePath = path + File.separator + "exercise" + i + File.separator + "README.md";
                File readme = new File(readmePath);
                if (readme.exists()) {
                    Scanner scanner = new Scanner(readme);
                    scanner.useDelimiter("\n");
                    String line1 = scanner.next();
                    scanner.close();
                    exerciseName = line1.replace("#", "").trim();
                }

                exercises.add(new ExerciseBase(i, exerciseName, exerciseClass.getDeclaredMethod("main", String[].class)));


            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public List<ExerciseBase> getExercises() {
        return exercises;
    }

    public void start(int number, String[] args) {
        if (exercises.isEmpty() || number < 0 || number >= exercises.size()) return;
        try {
            exercises.get(number).start(args);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
