package com.jonasgroenbek;

import java.util.Comparator;

/**
 * Max heap with minimal functionality to accustom the program.
 */
public class HeapSort implements TextSorter {

    String[] heap;
    int size;
    Comparator<String> comparator;

    /**
     * @param comparator the prioritizer
     * @param data       the subject to sort
     */
    @Override
    public String[] sort(Comparator<String> comparator, String[] data) {
        this.heap = new String[data.length];
        this.size = data.length;
        this.comparator = comparator;

        //builds the heap O(n log(n))
        for (int i = 0; i < size; i++) {
            heap[i] = data[i];
            int node = i;
            while (comparator.compare(heap[getParent(node)], heap[node]) < 0) {
                int temp = getParent(node);
                swap(node, getParent(node));
                node = temp;
            }
        }

        for (int j = size - 1; j >= 0; j--) {
            // Move current root to end
            swap(0, j);

            // call max heapify on the reduced heap
            heapify(0);
            size--;
        }
        return heap;
    }

    private int getParent(int node) {
        return (node - 1) / 2;
    }

    /**
     * if parent is smaller than one of or both children, replace with the largest of the two. If the child is a parent
     * itself, check if it is still a heap and repeat from the rightmost tree to the leftmost tree (based on the array
     * placement)
     */
    private void heapify(int node) {

        int largest = node; // Initialize largest as root
        int leftChild = leftChild(node);
        int rightChild = rightChild(node);

        // If left child is larger than root
        if (leftChild < size && comparator.compare(heap[leftChild], heap[largest]) > 0)
            largest = leftChild;

        // If right child is larger than largest so far
        if (rightChild < size && comparator.compare(heap[rightChild], heap[largest]) > 0)
            largest = rightChild;

        // If largest is not root
        if (largest != node) {
            swap(node, largest);

            // Recursively heapify the affected sub-tree
            heapify(largest);
        }
    }


    //swap two nodes
    private void swap(int node1, int node2) {
        String temp = heap[node1];
        heap[node1] = heap[node2];
        heap[node2] = temp;
    }

    //getting left child of node
    private int leftChild(int parentIndex) {
        return ++parentIndex * 2 - 1;
    }

    //getting rigth child of node
    private int rightChild(int parentIndex) {
        return ++parentIndex * 2;
    }
}
