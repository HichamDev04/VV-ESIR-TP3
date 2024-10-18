package fr.istic.vv;

import org.junit.jupiter.api.Test;

import static fr.istic.vv.StringUtils.isBalanced;
import static org.junit.jupiter.api.Assertions.*;

class StringUtilsTest {
    @Test
    void testChaineVide() {
        assertTrue(isBalanced("")); 
    }

    @Test
    void testSymboleImbrique() {
        assertTrue(isBalanced("{}"));
        assertTrue(isBalanced("[]"));
        assertTrue(isBalanced("()"));
    }

    @Test
    void testMultiSymboleImbrique() {
        assertTrue(isBalanced("{}[]"));
        assertTrue(isBalanced("(){}"));
    }

    @Test
    void testMultiImbrique() {
        assertTrue(isBalanced("{[()]}"));
        assertTrue(isBalanced("{{}}"));
    }

    @Test
    void testSeulFermOuvr() {
        assertFalse(isBalanced("{[(")); 
        assertFalse(isBalanced("}])")); 
    }    

    @Test
    void testComplexConditions() {
        assertFalse(isBalanced("{{["));
        assertFalse(isBalanced("[(])"));
        assertFalse(isBalanced("{[)}"));
    }
}
