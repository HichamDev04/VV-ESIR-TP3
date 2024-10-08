# On assertions

Answer the following questions:

1. The following assertion fails `assertTrue(3 * .4 == 1.2)`. Explain why and describe how this type of check should be done.

2. What is the difference between `assertEquals` and `assertSame`? Show scenarios where they produce the same result and scenarios where they do not produce the same result.

3. In classes we saw that `fail` is useful to mark code that should not be executed because an exception was expected before. Find other uses for `fail`. Explain the use case and add an example.

4. In JUnit 4, an exception was expected using the `@Test` annotation, while in JUnit 5 there is a special assertion method `assertThrows`. In your opinion, what are the advantages of this new way of checking expected exceptions?

## Answer
2. assertEquals() vérifie si deux objets ont un contenu identique, même s'ils sont des instances différentes et assertSame() vérifie si deux objets sont la même instance (référence mémoire). Les 2 scénarios vont produire le même résultat si 2 données ont la même valeur et utilisent le même espace mémoire.
4. La méthode `assertThrows` de JUnit 5 a plusieurs avantages par rapport à l'annotation `@Test` de JUnit 4. Tout d'abord, `assertThrows` permet de cibler la partie du code qui doit lever l'exception alors que `@Test` couvre toute la méthode. De plus, la méthode de JUnit 5 permet de capturer et vérifier des détails comme le message de l'exception.
Enfin, `assertThrows` rend les tests plus lisibles et expressifs.
