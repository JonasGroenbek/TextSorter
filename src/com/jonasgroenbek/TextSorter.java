package com.jonasgroenbek;

import java.util.Comparator;

public interface TextSorter {
    String[] sort(Comparator<String> compare, String[] data);
}
