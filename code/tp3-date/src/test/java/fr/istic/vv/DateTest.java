package fr.istic.vv;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

class DateTest {

    // Tests du constructeur pour une date valide et une date invalide
    @Test
    void testConstructor_ValidDate() {
        Date date = new Date(12, 5, 2023);
        assertEquals(12, date.getDay());
        assertEquals(5, date.getMonth());
        assertEquals(2023, date.getYear());
    }

    @Test
    void testConstructor_InvalidDate() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Date(32, 13, 2023); // Jours et mois invalides
        });
    }

    // Tests pour isValidDate - Cas valides (Combinatoire BCC)
    @Test
    void testIsValidDate_ValidCases() {
        assertTrue(Date.isValidDate(29, 2, 2020)); // Année bissextile (février)
        assertTrue(Date.isValidDate(28, 2, 2021)); // Année non bissextile (février)
        assertTrue(Date.isValidDate(31, 12, 2022)); // Fin d'année
        assertTrue(Date.isValidDate(30, 4, 2023)); // Mois avec 30 jours (avril)
        assertTrue(Date.isValidDate(1, 1, 2022));  // Début de l'année
        assertTrue(Date.isValidDate(15, 6, 2021)); // Mois médian, date valide
        assertTrue(Date.isValidDate(31, 1, 2021)); // Cas de fin de mois (janvier)
    }

    // Tests pour isValidDate - Cas invalides (Combinatoire BCC)
    @Test
    void testIsValidDate_InvalidCases() {
        assertFalse(Date.isValidDate(31, 4, 2023)); // Avril a 30 jours
        assertFalse(Date.isValidDate(29, 2, 2021)); // Année non bissextile (février)
        assertFalse(Date.isValidDate(32, 1, 2023)); // Jour invalide (hors borne)
        assertFalse(Date.isValidDate(0, 5, 2022));  // Jour invalide (inférieur à 1)
        assertFalse(Date.isValidDate(15, 13, 2023)); // Mois invalide (supérieur à 12)
    }

    // Tests pour l'année bissextile
    @Test
    void testIsLeapYear() {
        assertTrue(Date.isLeapYear(2020)); // Année bissextile
        assertFalse(Date.isLeapYear(2021)); // Année non bissextile
        assertTrue(Date.isLeapYear(2000)); // Année séculaire bissextile
        assertFalse(Date.isLeapYear(1900)); // Année séculaire non bissextile
        assertFalse(Date.isLeapYear(2019)); // Année simple
    }

    // Tests pour la méthode nextDate - Cas combinatoires pour le BCC
    @Test
    void testNextDate() {
        Date date = new Date(31, 12, 2020);
        Date nextDay = date.nextDate();
        assertEquals(1, nextDay.getDay());
        assertEquals(1, nextDay.getMonth());
        assertEquals(2021, nextDay.getYear());

        date = new Date(28, 2, 2020); // Année bissextile
        nextDay = date.nextDate();
        assertEquals(29, nextDay.getDay());
        assertEquals(2, nextDay.getMonth());
        assertEquals(2020, nextDay.getYear());

        date = new Date(28, 2, 2021); // Année non bissextile
        nextDay = date.nextDate();
        assertEquals(1, nextDay.getDay());
        assertEquals(3, nextDay.getMonth());
        assertEquals(2021, nextDay.getYear());

        date = new Date(30, 4, 2023); // Mois avec 30 jours
        nextDay = date.nextDate();
        assertEquals(1, nextDay.getDay());
        assertEquals(5, nextDay.getMonth());
        assertEquals(2023, nextDay.getYear());

        // Cas ajouté pour vérifier la transition entre les mois
        date = new Date(30, 6, 2023);
        nextDay = date.nextDate();
        assertEquals(1, nextDay.getDay());
        assertEquals(7, nextDay.getMonth());
    }

    // Test pour les conditions aux frontières supplémentaires
    @Test
    void testNextDate_EndOfMonthBoundary() {
        Date date = new Date(30, 6, 2023); // Juin a 30 jours
        Date nextDay = date.nextDate();
        assertEquals(1, nextDay.getDay());
        assertEquals(7, nextDay.getMonth());
        assertEquals(2023, nextDay.getYear());

        date = new Date(31, 7, 2023); // Juillet a 31 jours
        nextDay = date.nextDate();
        assertEquals(1, nextDay.getDay());
        assertEquals(8, nextDay.getMonth());
        assertEquals(2023, nextDay.getYear());
    }

    // Test pour améliorer la couverture des retours primitifs
    @Test
    void testGetters_PrimitiveReturns() {
        Date date = new Date(15, 8, 2023);
        assertEquals(15, date.getDay());
        assertEquals(8, date.getMonth());
        assertEquals(2023, date.getYear());
    }

    // Tests pour la méthode previousDate - Cas combinatoires pour le BCC
    @Test
    void testPreviousDate() {
        Date date = new Date(1, 1, 2021);
        Date prevDay = date.previousDate();
        assertEquals(31, prevDay.getDay());
        assertEquals(12, prevDay.getMonth());
        assertEquals(2020, prevDay.getYear());

        date = new Date(1, 3, 2020); // Année bissextile
        prevDay = date.previousDate();
        assertEquals(29, prevDay.getDay());
        assertEquals(2, prevDay.getMonth());
        assertEquals(2020, prevDay.getYear());

        date = new Date(1, 3, 2021); // Année non bissextile
        prevDay = date.previousDate();
        assertEquals(28, prevDay.getDay());
        assertEquals(2, prevDay.getMonth());
        assertEquals(2021, prevDay.getYear());

        // Cas ajouté pour tester le passage entre mois à 30 et 31 jours
        date = new Date(1, 8, 2023);
        prevDay = date.previousDate();
        assertEquals(31, prevDay.getDay());
        assertEquals(7, prevDay.getMonth());
    }

    // Tests pour la méthode compareTo - Cas combinatoires pour le BCC
    @Test
    void testCompareTo() {
        Date date1 = new Date(12, 5, 2023);
        Date date2 = new Date(12, 5, 2023);
        Date date3 = new Date(11, 5, 2023);

        assertEquals(0, date1.compareTo(date2)); // Même date
        assertTrue(date1.compareTo(date3) > 0);  // date1 est après date3
        assertTrue(date3.compareTo(date1) < 0);  // date3 est avant date1

        Date date4 = new Date(13, 5, 2023); // Test d'une date après
        assertTrue(date4.compareTo(date1) > 0);
    }

    // Test pour vérifier l'exception NullPointerException dans compareTo
    @Test
    void testCompareTo_NullPointerException() {
        Date date1 = new Date(12, 5, 2023);
        assertThrows(NullPointerException.class, () -> {
            date1.compareTo(null); // Devrait lancer NullPointerException
        });
    }
}
