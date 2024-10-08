# On assertions

Answer the following questions:

1. The following assertion fails `assertTrue(3 * .4 == 1.2)`. Explain why and describe how this type of check should be done.

2. What is the difference between `assertEquals` and `assertSame`? Show scenarios where they produce the same result and scenarios where they do not produce the same result.

3. In classes we saw that `fail` is useful to mark code that should not be executed because an exception was expected before. Find other uses for `fail`. Explain the use case and add an example.

4. In JUnit 4, an exception was expected using the `@Test` annotation, while in JUnit 5 there is a special assertion method `assertThrows`. In your opinion, what are the advantages of this new way of checking expected exceptions?

## Answer
1. L’assertion échoue car, du fait de leur stockage en mémoire sous forme binaire, les nombres flottants ne sont pas toujours représentés à la perfection. Ici, multiplier 3 par 0.4 n’est donc pas exactement égal à 1.2 à cause des erreurs d’arrondi causées par la représentation en binaire.
Pour effectuer ce genre de vérification, on ne comparera pas directement le résultat du produit au nombre flottant mais on utilisera une marge de tolérance pour s’assurer que les deux résultats sont assez proches l’un de l’autre. En java on utilise la méthode `assertEquals(resultat, operation, delta)` où `delta` est une valeur représentant la tolérance d'erreur acceptable. On aurait donc eu par exemple : 
```
assertEquals(1.2, 3 * 0.4, 0.0001);
```

2. assertEquals() vérifie si deux objets ont un contenu identique, même s'ils sont des instances différentes et assertSame() vérifie si deux objets sont la même instance (référence mémoire). Les 2 scénarios vont produire le même résultat si 2 données ont la même valeur et utilisent le même espace mémoire.

3. La méthode `fail()` est aussi utilisée pour :
- Vérifier un état non valide dans une boucle ou une condition complexe
Afin de vérifier que certaines conditions spécifiques n'ont pas lieu dans des branches de code complexes, nous pouvons utiliser `fail()` pour signaler ce cas non prévu.
Exemple : utilisation de `fail()` pour spécifier qu’on ne doit pas avoir d’éléments nuls dans la liste
```
for (int i = 0; i < list.size(); i++) {
    if (list.get(i) == null) {
        fail("La liste contient un élément nul à l'index : " + i);
    }
}
```
- Signaler une non-implémentation dans un test en cours de développement
Lorsque nous développons un test et que certaines fonctionnalités n’ont pas encore été implémentées, nous pouvons utiliser `fail()` pour rappeler ou indiquer explicitement que ce test n'est pas encore complet.
Exemple : Utilisation de fail() pour indiquer qu’une partie du code est en attente d’implémentation
```
@Test
public void testNouvelleFonctionnalite() {
    // Logique du test à implémenter
    fail("Ce test n'est pas encore implémenté");
}
```
- Signaler un défaut de couverture de test pour des cas exceptionnels ou imprévus
Dans certaines situations, il peut y avoir des branches de code théoriquement inaccessibles mais, pour des raisons de couverture, nous pouvons tester ces chemins afin de garantir que le code se comporte comme prévu même dans des situations anormales.
Exemple : Utilisation de fail() pour s'assurer que seuls les cas "A" et "B" sont traités
```
switch (type) {
    case "A":
        // faire quelque chose
        break;
    case "B":
        // faire autre chose
        break;
    default:
        fail("Type non pris en charge : " + type);
}
```

5. La méthode `assertThrows` de JUnit 5 a plusieurs avantages par rapport à l'annotation `@Test` de JUnit 4. Tout d'abord, `assertThrows` permet de cibler la partie du code qui doit lever l'exception alors que `@Test` couvre toute la méthode. De plus, la méthode de JUnit 5 permet de capturer et vérifier des détails comme le message de l'exception.
Enfin, `assertThrows` rend les tests plus lisibles et expressifs.
