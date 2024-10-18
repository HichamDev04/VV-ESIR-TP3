package fr.istic.vv;

import java.util.Comparator;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;


public class BinaryHeapTest {

    // Test de la méthode pop sur un tas vide
    @Test
    public void testPopOnEmptyHeap() {
        BinaryHeap<Integer> heap = new BinaryHeap<Integer>(Comparator.naturalOrder());
        assertThrows(NoSuchElementException.class, heap::pop); // Doit lever une exception
    }

    // Test de la méthode peek sur un tas vide
    @Test
    public void testPeekOnEmptyHeap() {
        BinaryHeap<Integer> heap = new BinaryHeap<Integer>(Comparator.naturalOrder());
        assertThrows(NoSuchElementException.class, heap::peek); // Doit lever une exception
    }

    // Test de la méthode push avec un seul élément
    @Test
    public void testPushSingleElement() {
        BinaryHeap<Integer> heap = new BinaryHeap<Integer>(Comparator.naturalOrder());
        heap.push(42);
        assertEquals(42, (int) heap.peek()); // L'élément doit être présent sans être retiré
    }

    // Test de la méthode pop avec un seul élément
    @Test
    public void testPopSingleElement() {
        BinaryHeap<Integer> heap = new BinaryHeap<Integer>(Comparator.naturalOrder());
        heap.push(42);
        assertEquals(42, (int) heap.pop()); // L'élément doit être retiré correctement
        assertEquals(0, heap.count()); // Le tas doit être vide après l'extraction
    }

    // Test de la méthode pop avec plusieurs éléments dans l'ordre naturel
    @Test
    public void testPopWithNaturalOrder() {
        BinaryHeap<Integer> heap = new BinaryHeap<Integer>(Comparator.naturalOrder());
        heap.push(3);
        heap.push(1);
        heap.push(2);
        assertEquals(1, (int) heap.pop()); // Le plus petit élément doit être retiré en premier
        assertEquals(2, (int) heap.pop()); // Ensuite le suivant
        assertEquals(3, (int) heap.pop()); // Puis le plus grand
    }

    // Test de la méthode pop avec un comparateur inverse (tas max)
    @Test
    public void testPopWithMaxHeap() {
        BinaryHeap<Integer> heap = new BinaryHeap<Integer>(Comparator.reverseOrder());
        heap.push(3);
        heap.push(1);
        heap.push(2);
        assertEquals(3, (int) heap.pop()); // Le plus grand élément doit être retiré en premier
        assertEquals(2, (int) heap.pop()); // Ensuite le suivant
        assertEquals(1, (int) heap.pop()); // Puis le plus petit
    }

    // Test de la méthode push et pop avec des doublons
    @Test
    public void testHeapWithDuplicates() {
        BinaryHeap<Integer> heap = new BinaryHeap<Integer>(Comparator.naturalOrder());
        heap.push(2);
        heap.push(1);
        heap.push(2);
        assertEquals(1, (int) heap.pop()); // Le plus petit élément doit être retiré en premier
        assertEquals(2, (int) heap.pop()); // Ensuite les doublons
        assertEquals(2, (int) heap.pop());
    }

    // Test pour vérifier la méthode count
    @Test
    public void testCount() {
        BinaryHeap<Integer> heap = new BinaryHeap<Integer>(Comparator.naturalOrder());
        assertEquals(0, heap.count()); // Au départ, le tas est vide
        heap.push(1);
        heap.push(2);
        assertEquals(2, heap.count()); // Le tas doit contenir 2 éléments
        heap.pop();
        assertEquals(1, heap.count()); // Après extraction, il reste 1 élément
    }

    // Test de la méthode peek sur un tas non vide
    @Test
    public void testPeekNonEmptyHeap() {
        BinaryHeap<Integer> heap = new BinaryHeap<Integer>(Comparator.naturalOrder());
        heap.push(5);
        heap.push(3);
        heap.push(7);
        assertEquals(3, (int) heap.peek()); // Le plus petit élément doit être visible sans être retiré
    }

    // Test de la méthode peek après avoir extrait tous les éléments
    @Test
    public void testPeekAfterPopAll() {
        BinaryHeap<Integer> heap = new BinaryHeap<Integer>(Comparator.naturalOrder());
        heap.push(5);
        heap.pop();
        assertThrows(NoSuchElementException.class, heap::peek); // Doit lever une exception car le tas est vide
    }

    // Test de la méthode pop après avoir ajouté et retiré tous les éléments
    @Test
    public void testPopAfterEmptyingHeap() {
        BinaryHeap<Integer> heap = new BinaryHeap<Integer>(Comparator.naturalOrder());
        heap.push(5);
        heap.pop(); // Retire l'élément
        assertThrows(NoSuchElementException.class, heap::pop); // Doit lever une exception car le tas est maintenant vide
    }

    // Test pour vérifier le comportement lorsque des éléments sont ajoutés puis retirés
    @Test
    public void testPushAndPopMultipleElements() {
        BinaryHeap<Integer> heap = new BinaryHeap<Integer>(Comparator.naturalOrder());
        heap.push(10);
        heap.push(4);
        heap.push(8);
        assertEquals(4, (int) heap.pop()); // Le plus petit élément est retiré en premier
        assertEquals(8, (int) heap.pop()); // Ensuite l'élément suivant
        assertEquals(10, (int) heap.pop()); // Enfin le dernier élément
    }

    // Cas spécifique pour le BCC - Pousser un élément avec des valeurs limites
    @Test
    public void testPushEdgeCases() {
        BinaryHeap<Integer> heap = new BinaryHeap<Integer>(Comparator.naturalOrder());
        heap.push(Integer.MIN_VALUE);
        heap.push(Integer.MAX_VALUE);
        assertEquals(Integer.MIN_VALUE, (int) heap.pop()); // Le plus petit élément doit être retiré en premier
        assertEquals(Integer.MAX_VALUE, (int) heap.pop()); // Puis le plus grand
    }

    // Cas spécifique pour le BCC - Mélange de valeurs négatives, zéro et positives
    @Test
    public void testPushWithNegativeZeroPositive() {
        BinaryHeap<Integer> heap = new BinaryHeap<Integer>(Comparator.naturalOrder());
        heap.push(-10);
        heap.push(0);
        heap.push(10);
        assertEquals(-10, (int) heap.pop()); // Négatif d'abord
        assertEquals(0, (int) heap.pop());   // Puis zéro
        assertEquals(10, (int) heap.pop());  // Enfin positif
    }

    // Test de pop après l'ajout de doublons
    @Test
    public void testPopWithDuplicates() {
        BinaryHeap<Integer> heap = new BinaryHeap<Integer>(Comparator.naturalOrder());
        heap.push(5);
        heap.push(5);
        heap.push(5);
        assertEquals(5, (int) heap.pop()); // Le premier doublon
        assertEquals(5, (int) heap.pop()); // Le deuxième doublon
        assertEquals(5, (int) heap.pop()); // Le troisième doublon
    }
}
