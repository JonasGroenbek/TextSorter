package com.jonasgroenbek;

import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;

public class TrieSort implements TextSorter {

    private String[] data;
    private int sortPointer;

    @Override
    public String[] sort(Comparator<String> compare, String[] data) {
        Node root = new Node();
        this.data = data;

        //populating the trie
        for(int i = 0; i < data.length; i++){
            root.insert(data[i].toCharArray(), data[i].length());
        }

        //using the tree to place correct order in the data argument
        root.sort("");

        return data;
    }

    private class Node {
        TreeMap<Character, Node> children = new TreeMap();
        int count = 0; //amount of times the word has occured

        Node(){}

        Node(boolean isLeaf) { if (isLeaf) count++; }

        public void increment(){ count++; }

        public void sort(String word){
            for(int i = 0; i < count; i++){
                data[sortPointer] = word;
                sortPointer++;
            }
            if(!isLeaf()) children.forEach((key, value) -> value.sort(word+key));
        }

        public boolean isLeaf(){ return children.size() < 1; }

        public void insert(char[] letters, int remainder) {
            Character next = letters[letters.length - remainder];
            boolean hasNext = children.containsKey(next);

            if (remainder == 1) {
                if (hasNext)
                    children.get(next).increment();
                else
                    children.put(next, new Node(true));

            } else {
                if(hasNext)
                    children.get(next).insert(letters, --remainder);
                else {
                    children.put(next, new Node());
                    children.get(next).insert(letters, --remainder);
                }
            }

        }
    }
}