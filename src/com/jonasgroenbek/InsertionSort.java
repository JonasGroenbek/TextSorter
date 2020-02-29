package com.jonasgroenbek;

import java.util.Comparator;

public class InsertionSort implements TextSorter {

    @Override
    public String[] sort(Comparator<String> comparator, String[] data) {
        for (int i = 1; i < data.length - 1; i++) {

            String sortSubject = data[i];

            for (int j = i - 1; j != 0; --j) {
                if (comparator.compare(sortSubject, data[j]) == -1) {
                    String temp = data[j];
                    data[j] = sortSubject;
                    data[j + 1] = temp;
                }
            }
        }
        return data;
    }
}