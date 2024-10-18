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
1. L'input space partitionning (ISP) partitionne l'ensemble du domaine en sous-ensemble tel que l'intersection de chacun soit vide mais que l'union de tous couvre l'entièreté du domaine. Ci-dessous les sous-ensembles choisis. Pour la suite, dans les tests, nous choisirons une seule valeur de chaque block.
  ![tableau](/exercises/tableau-input-space-partitionning.png)

2. Pour augmenter la couverture, nous ajoutons des tests d'un même block en modifiant les symboles utilisés et la longueur de la chaîne de caractère.

3. Dans notre code nous avons un prédicat qui utilise plus de 2 opérateurs booléens. Nous devons donc vérifier que nous couvrons toutes les combinaisons de conditions possibles. Par exemple, nous avons parfois plusieurs "||" ou "&&". Dans ce cas, nous devons couvrir les choix de base en testant chaque combinaison de vrai/faux.
Le code ci-dessous teste à la fois l'ordre et la correspondance des symboles.
```
@Test
void testComplexConditions() {
    assertFalse(isBalanced("{[)}")); 
}
```

4. Nous avons utiliser PIT pour évaluer la suite des tests dont nous disposons. Tout d'abord 18 mutations ont été générées et 18 ont été tuées ce qui montre un score de mutation de 100%. Nous avons donc une bonne couverture. Il n'y a alors pas besoin d'ajouter de tests supplémentaires.
```
>> Generated 18 mutations Killed 18 (100%)
```
PIT a produit 3 mutants BooleanTrueReturnValsMutator (remplace true par false), 2 mutants BooleanFalseReturnValsMutator (remplace false par true) et 13 NegateConditionalsMutator (inverse conditions logiques en remplaçant par exemple "==" par "!=" ou "<" par ">=").
```
> org.pitest.mutationtest.engine.gregor.mutators.BooleanTrueReturnValsMutator
>> Generated 3 Killed 3 (100%)
> KILLED 3 SURVIVED 0 TIMED_OUT 0 NON_VIABLE 0
> MEMORY_ERROR 0 NOT_STARTED 0 STARTED 0 RUN_ERROR 0
> NO_COVERAGE 0
--------------------------------------------------------------------------------
> org.pitest.mutationtest.engine.gregor.mutators.BooleanFalseReturnValsMutator
>> Generated 2 Killed 2 (100%)
> KILLED 2 SURVIVED 0 TIMED_OUT 0 NON_VIABLE 0
> MEMORY_ERROR 0 NOT_STARTED 0 STARTED 0 RUN_ERROR 0
> NO_COVERAGE 0
--------------------------------------------------------------------------------
> org.pitest.mutationtest.engine.gregor.mutators.NegateConditionalsMutator
>> Generated 13 Killed 13 (100%)
> KILLED 13 SURVIVED 0 TIMED_OUT 0 NON_VIABLE 0
> MEMORY_ERROR 0 NOT_STARTED 0 STARTED 0 RUN_ERROR 0
> NO_COVERAGE 0
--------------------------------------------------------------------------------
```


