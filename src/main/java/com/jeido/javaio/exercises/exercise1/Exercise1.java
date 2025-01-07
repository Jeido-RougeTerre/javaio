package com.jeido.javaio.exercises.exercise1;

import java.io.*;

public class Exercise1 {
    public static void main(String[] args) {
        System.out.println("Hello World");

        String inputFilePath = "src/main/resources/exercise1/read.txt";
        String outputFilePath = "src/main/resources/exercise1/result.txt";

        int counter = 0;
        try (BufferedReader br = new BufferedReader(new FileReader(inputFilePath))) {
            String line;
            while ( (line = br.readLine()) != null ) {
                if (line.contains("Java")) {
                    counter++;
                }
            }
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(outputFilePath))) {
            bw.write("The word Java was found " + counter + " time" + ((counter > 1)? "s" : ""));
            System.out.println("The Java word count was written to '" + outputFilePath + "'");
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
}
