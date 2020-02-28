package com.jonasgroenbek;

import java.util.Comparator;
import java.util.List;

public class InsertionSort implements TextSorter {

    @Override
    public String[] sort(Comparator<String> comparator, String[] data) {
        for(int i = 1; i < data.length - 1; i++){

            String valueToSort = data[i];

            for(int j = i-1; j != 0; --j) {
                if( comparator.compare(valueToSort, data[j]) == -1){
                    String temp = data[j];
                    data[j] = valueToSort;
                    data[j+1] = temp;
                }
            }
        }
        return data;
    }
}