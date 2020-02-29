package com.jonasgroenbek;

import java.util.Comparator;

public class SelectionSort implements TextSorter {

    @Override
    public String[] sort(Comparator<String> comparator, String[] data) {
        for (int i = 0; i < data.length - 1; i++) {
            String smallest = data[i];
            int replacement = i;
            for (int j = i; j < data.length - 1; j++) {
                if (comparator.compare(smallest, data[j + 1]) == 1) {
                    smallest = data[j + 1];
                    replacement = j + 1;
                }
            }
            String temp = data[i];
            data[replacement] = temp;
            data[i] = smallest;
        }
        return data;
    }
}
