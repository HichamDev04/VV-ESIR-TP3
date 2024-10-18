package fr.istic.vv;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.NoSuchElementException;

public class BinaryHeap<T> {
    
    private ArrayList<T> heap;
    private Comparator<T> comparator;

    // Constructeur qui prend un comparateur pour définir l'ordre
    public BinaryHeap(Comparator<T> comparator) {
        this.comparator = comparator;
        this.heap = new ArrayList<>();
    }

    // Renvoie et enlève le plus petit élément du tas
    public T pop() {
        if (heap.isEmpty()) {
            throw new NoSuchElementException();
        }
        T result = heap.get(0);
        T last = heap.remove(heap.size() - 1);
        if (!heap.isEmpty()) {
            heap.set(0, last);
            heapifyDown(0);
        }
        return result;
    }

    // Renvoie le plus petit élément sans l'enlever
    public T peek() {
        if (heap.isEmpty()) {
            throw new NoSuchElementException();
        }
        return heap.get(0);
    }

    // Ajoute un élément au tas
    public void push(T element) {
        heap.add(element);
        heapifyUp(heap.size() - 1);
    }

    // Renvoie le nombre d'éléments dans le tas
    public int count() {
        return heap.size();
    }

    // Remonter l'élément à sa position correcte
    private void heapifyUp(int index) {
        T element = heap.get(index);
        while (index > 0) {
            int parentIndex = (index - 1) / 2;
            T parent = heap.get(parentIndex);
            if (comparator.compare(element, parent) >= 0) {
                break;
            }
            heap.set(index, parent);
            index = parentIndex;
        }
        heap.set(index, element);
    }

    // Faire descendre l'élément à sa position correcte
    private void heapifyDown(int index) {
        int size = heap.size();
        T element = heap.get(index);
        while (index < size) {
            int leftChildIndex = 2 * index + 1;
            int rightChildIndex = 2 * index + 2;
            int smallestIndex = index;

            if (leftChildIndex < size && comparator.compare(heap.get(leftChildIndex), element) < 0) {
                smallestIndex = leftChildIndex;
            }
            if (rightChildIndex < size && comparator.compare(heap.get(rightChildIndex), heap.get(smallestIndex)) < 0) {
                smallestIndex = rightChildIndex;
            }
            if (smallestIndex == index) {
                break;
            }
            heap.set(index, heap.get(smallestIndex));
            index = smallestIndex;
        }
        heap.set(index, element);
    }
}
