# Balanced strings

A string containing grouping symbols `{}[]()` is said to be balanced if every open symbol `{[(` has a matching closed symbol `)]}` and the substrings before, after and between each pair of symbols is also balanced. The empty string is considered as balanced.

For example: `{[][]}({})` is balanced, while `][`, `([)]`, `{`, `{(}{}` are not.

Implement the following method:

```java
public static boolean isBalanced(String str) {
    ...
}
```

`isBalanced` returns `true` if `str` is balanced according to the rules explained above. Otherwise, it returns `false`.

Use the coverage criteria studied in classes as follows:

1. Use input space partitioning to design an initial set of inputs. Explain below the characteristics and partition blocks you identified.
2. Evaluate the statement coverage of the test cases designed in the previous step. If needed, add new test cases to increase the coverage. Describe below what you did in this step.
3. If you have in your code any predicate that uses more than two boolean operators, check if the test cases written so far satisfy *Base Choice Coverage*. If needed, add new test cases. Describe below how you evaluated the logic coverage and the new test cases you added.
4. Use PIT to evaluate the test suite you have so far. Describe below the mutation score and the live mutants. Add new test cases or refactor the existing ones to achieve a high mutation score.

Write below the actions you took on each step and the results you obtained.
Use the project in [tp3-balanced-strings](../code/tp3-balanced-strings) to complete this exercise.

## Answer
1. L'input space partitionning (ISP) partitionne l'ensemble du domaine en sous-ensemble tel que l'intersection de chacun soit vide mais que l'union de tous couvrent l'entièreté du domaine. Ci-dessous les sous-ensembles choisis. Pour la suite, dans les tests, nous choisirons une seule valeur de chaque block.
  ![tableau](/exercises/tableau-input-space-partitionning.png)

2. Pour augmenter la couverture, nous ajoutons des tests d'un même block en modifiant les symboles utilisés et la longueur de la chaîne de caractère.

3. Dans notre code nous avons un prédicat qui utilise plus de 2 opérateurs booléens. Nous devons donc vérifier si nous couvrons toutes les combinaisons de conditions possibles. Par exemple, nous avons parfois plusieurs "||" ou "&&". Dans ce cas, nous devons couvrir les choix de base en testant chaque combinaison de vrai/faux.
Le code ci-dessous teste à la fois l'ordre et la correspondance des symboles.
```
@Test
void testComplexConditions() {
    assertFalse(isBalanced("{[)}")); 
}
```

4. 
