package com.jonasgroenbek;

import org.jetbrains.annotations.NotNull;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.List;

public class Main {

    private Comparator<String> compare;

    /**
     * if the first argument is to be sorted before the second argument, then return -1 if equals 0 else 1
     * @param str1
     * @param str2
     * @return int
     */
    public static int compare(@NotNull String str1, @NotNull String str2){
        for(int i = 0; i < Math.min(str1.length(), str2.length()); i++){
            if( (int) str1.charAt(i) != (int) str2.charAt(i) ){
                return (int) str1.charAt(i) < (int) str2.charAt(i) ? -1 : 1;
            }
        }
        if(str1.length() == str2.length()) return 0;
        return str1.length() < str2.length() ? -1 : 1;
    }


    public static void main(String[] args) {
        if(args.length < 1) {
            System.out.println("Not provided command line arguments. \n" +
                    "IDE - Configure it to pass a program argument with your files absolute path\n" +
                    "CL - java myFile.java {absolute path of txt file}");
            System.exit(0);
        }
        Main x = new Main();
        Path path = Paths.get(args[0]);
        List words = FileReader.readFile(path);
        String[] data = (String[]) words.toArray(new String[words.size()]);
        Comparator<String> compare = Main::compare;

        InsertionSort is = new InsertionSort();
        SelectionSort ss = new SelectionSort();
        MergeSort ms = new MergeSort();

        String[] ssResult = is.sort(compare, data);
        String[] siResult = ss.sort(compare, data);
        String[] msResult = ms.sort(compare, data);


        for(int i = 0; i < ssResult.length - 1; i++) {
            System.out.println(ssResult[i]);
        }

    }
}
