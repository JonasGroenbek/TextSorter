package com.jonasgroenbek;

import java.util.Arrays;
import java.util.Comparator;

public class MergeSort implements TextSorter {

    Comparator<String> comparator;
    String[] data;
    String[] aux;

    @Override
    public String[] sort(Comparator<String> compare, String[] data) {
        this.comparator = compare;
        this.data = data;
        this.aux = new String[data.length];
        System.arraycopy(data, 0, aux, 0, data.length);

        split(0, data.length - 1);

        return data;
    }

    /**
     * Takes advantage of recursion by creating the respective subarrays with split and merging in the correct order
     *
     * @param low
     * @param high
     */
    private void split(int low, int high) {

        //basecase
        if (low < high) {

            int middle = Math.round((low + high) / 2);

            //recursively splits the left half into seperate elements
            split(low, middle);

            //recursively splits the right half into seperate elements
            split(middle + 1, high);

            //merges the sorted subarrays and inserts into this.data
            merge(low, middle, high);
        }
    }

    /**
     * @param low    the start index of the span of which to merge from the original array
     * @param middle the split that segregates the previously sorted arrays
     * @param high   the end index of the span of which to merge from the original array
     */
    private void merge(int low, int middle, int high) {

        int lowPointer = low;
        int highPointer = middle + 1;
        int insertPointer = low;

        while (lowPointer <= middle && highPointer <= high) {
            if (comparator.compare(aux[lowPointer], aux[highPointer]) < 0) {
                data[insertPointer] = aux[lowPointer];
                lowPointer++;
            } else {
                data[insertPointer] = aux[highPointer];
                highPointer++;
            }
            insertPointer++;
        }

        //only looks at lowPointer since highPointer is already sorted in the correct place
        while (lowPointer <= middle) {
            data[insertPointer] = aux[lowPointer];
            insertPointer++;
            lowPointer++;
        }

        System.arraycopy(data, low, aux, low, high - low);
    }
}