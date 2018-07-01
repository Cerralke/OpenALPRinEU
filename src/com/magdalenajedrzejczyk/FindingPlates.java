package com.magdalenajedrzejczyk;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class FindingPlates {

    private static String myPath;
    private static File myDirectory;
    private static Scanner scanner;
    private static String[] myFiles;

    public void findingPlates() {
        myPath = getPhotosPath();
        myDirectory = new File(myPath);
        myFiles = myDirectory.list();

        findPlates();
    }
    private String getPhotosPath() {
        scanner = new Scanner(System.in);
        System.out.println("Podaj ścieżkę do folderu ze zdjęciami: ");
        return scanner.nextLine();
    }

    private void findPlates() {
        for (int i = 0; i < myFiles.length; i++) {
            String[] arguments = getArguments(i);
            printFileName(i);
            showFoundPlates(arguments);
        }
    }

    private String[] getArguments(int i) {
        return new String[] {"/home/cerro/IdeaProjects/Opsididitagain/myscript.sh", myPath + "/" + myFiles[i]};
    }

    private void printFileName(int i){
        System.out.println("Result for " + myFiles[i]);
    }

    private void showFoundPlates(String[] arguments) {
        try {
            Process proc = new ProcessBuilder(arguments).start();
            BufferedReader reader = new BufferedReader(new InputStreamReader(proc.getInputStream()));
            String s;
            while ((s = reader.readLine()) != null) {
                System.out.println(s);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
