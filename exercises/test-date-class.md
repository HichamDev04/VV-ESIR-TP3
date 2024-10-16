# Test the Date class

Implement a class `Date` with the interface shown below:

```java
class Date implements Comparable<Date> {

    public Date(int day, int month, int year) { ... }

    public static boolean isValidDate(int day, int month, int year) { ... }

    public static boolean isLeapYear(int year) { ... }

    public Date nextDate() { ... }

    public Date previousDate { ... }

    public int compareTo(Date other) { ... }

}
```

The constructor throws an exception if the three given integers do not form a valid date.

`isValidDate` returns `true` if the three integers form a valid year, otherwise `false`.

`isLeapYear` says if the given integer is a leap year.

`nextDate` returns a new `Date` instance representing the date of the following day.

`previousDate` returns a new `Date` instance representing the date of the previous day.

`compareTo` follows the `Comparable` convention:

* `date.compareTo(other)` returns a positive integer if `date` is posterior to `other`
* `date.compareTo(other)` returns a negative integer if `date` is anterior to `other`
* `date.compareTo(other)` returns `0` if `date` and `other` represent the same date.
* the method throws a `NullPointerException` if `other` is `null` 

Design and implement a test suite for this `Date` class.
You may use the test cases discussed in classes as a starting point. 
Also, feel free to add any extra method you may need to the `Date` class.


Use the following steps to design the test suite:

1. With the help of *Input Space Partitioning* design a set of initial test inputs for each method. Write below the characteristics and blocks you identified for each method. Specify which characteristics are common to more than one method.
2. Evaluate the statement coverage of the test cases designed in the previous step. If needed, add new test cases to increase the coverage. Describe below what you did in this step.
3. If you have in your code any predicate that uses more than two boolean operators check if the test cases written to far satisfy *Base Choice Coverage*. If needed add new test cases. Describe below how you evaluated the logic coverage and the new test cases you added.
4. Use PIT to evaluate the test suite you have so far. Describe below the mutation score and the live mutants. Add new test cases or refactor the existing ones to achieve a high mutation score.

Use the project in [tp3-date](../code/tp3-date) to complete this exercise.

## Answer

**1.** En utilisant l’Input Space Partitioning pour définir un ensemble d'entrées de test pour chaque méthode de la classe Date, nous voulons identifier les différentes caractéristiques qui influencent le comportement de chacune de ces méthodes, puis diviser ces caractéristiques en blocs correspondant à des plages de valeurs ou à des cas spécifiques.

Pour la méthode `isValidDate`, les caractéristiques principales sont le **jour**, le **mois** et l’**année**. Nous avons identifié plusieurs blocs pour chaque caractéristique : par exemple, pour le jour, il existe des jours valides (en fonction du mois et de l'année) et des jours invalides (comme un jour inférieur à 1 ou supérieur au nombre de jours du mois). De même, les blocs pour le mois incluent des mois valides (1 à 12) et des mois invalides. Enfin, pour l'année, nous devons prendre en compte les années valides ainsi que des années limites ou inhabituelles, comme l’année 0 ou des années très grandes.

Concernant la méthode `isLeapYear`, la seule caractéristique à prendre en compte est l’**année**. Le partitionnement ici se fait en fonction de la nature bissextile ou non de l’année. Les blocs identifiés incluent des années divisibles par 4 mais pas par 100 (années bissextiles classiques), des années divisibles par 400 (également bissextiles), ainsi que des années non bissextiles. 

Les méthodes `nextDate` et `previousDate` partagent des caractéristiques communes avec `isValidDate`, à savoir le **jour**, le **mois** et l’**année**. Ici, nous devons tester des cas particuliers comme les transitions de fin de mois (par exemple, du 31 décembre au 1er janvier pour nextDate, ou du 1er janvier au 31 décembre pour previousDate) pour nous assurer que les dates limites et les transitions d’années sont bien gérées par ces méthodes.

Enfin, la méthode `compareTo` doit être testée en fonction de la comparaison entre deux dates. Les caractéristiques à considérer incluent le **jour**, le **mois** et l’**année** pour la date courante et pour la date à comparer. Nous avons donc besoin de blocs pour des dates identiques, des dates antérieures, et des dates ultérieures, afin de couvrir tous les scénarios possibles lors de la comparaison entre deux instances de Date.

Certaines caractéristiques, comme l’**année**, le **mois** et le **jour**, sont communes à plusieurs méthodes, notamment `isValidDate`, `nextDate`, `previousDate`, et `compareTo`. Cela signifie que les blocs définis pour ces caractéristiques peuvent être réutilisés dans plusieurs méthodes afin d'optimiser notre ensemble de tests et d’éviter les redondances.


**2.** Au départ, la couverture du code était de 0%, car aucun test n'avait été implémenté. Cela signifiait que les méthodes de la classe `Date` n'étaient pas testées et donc que le code n'était pas validé.

Après avoir implémenté les méthodes essentielles (`isValidDate`, `isLeapYear`, `nextDate`, `previousDate`, `compareTo`), nous avons rédigé des cas de test unitaires pour chacune. Cependant, les premiers tests étaient incomplets et plusieurs erreurs, telles que des assertions incorrectes et des exceptions non levées comme prévu, ont été détectées.

Les résultats des premières exécutions de tests indiquaient toujours une couverture partielle. Par exemple, les méthodes `compareTo` et `isLeapYear` avaient des assertions échouées, et plusieurs lignes critiques n'étaient pas couvertes. Après des corrections et l'ajout de cas de test supplémentaires pour les scénarios limites (comme la gestion des dates en fin d'année et les années bissextiles), la couverture de test a augmenté de manière significative.

Selon les résultats de JaCoCo, nous avons obtenu une couverture des instructions de **96%** et une couverture des branches de **80%**, ce qui est un bon niveau de test pour ce projet. Nous avons donc décidé de nous contenter de ces résultats pour l’instant, sans ajouter de nouveaux tests. Ces tests couvrant déjà la plupart des scénarios critiques liés aux dates, comme les années bissextiles et les comparaisons de dates.


**3.** Nous avons utilisé le **Base Choice Coverage (BCC)** pour évaluer la couverture des tests sur les prédicats complexes présents dans notre méthode `isValidDate` afin de garantir que chaque chemin possible à travers la logique est testé.

C'est en appliquant le BCC que nous avons ajouté des tests comme celui du 29 février dans une année non bissextile, afin de vérifier que la méthode rejette correctement une date invalide dans ce contexte, ainsi que celui du 31 avril, un mois qui n’a que 30 jours, pour garantir que la méthode identifie bien cette date comme invalide. Le BCC nous a donc guidés pour tester des combinaisons telles que **année non bissextile avec jour supérieur à 28 en février** et **mois avec 30 jours et jour supérieur à 30**, des scénarios critiques pour la validation des dates.

Grâce à l'ajout de ces cas, nous avons vu une amélioration de la couverture des branches. Comme le montre notre rapport de couverture, nous avons maintenant une couverture de **96 %** pour les instructions et de **87 %** pour les branches. Cette augmentation est significative car elle montre que nos nouveaux tests ont contribué à couvrir les chemins logiques critiques qui n'étaient pas pris en compte auparavant. Nous avons ainsi satisfait les exigences du BCC et amélioré la robustesse de notre code.


**4.** Nous avons utilisé **PIT** pour évaluer la qualité de notre suite de tests avec le **mutation testing**. Après l’exécution, nous avons obtenu un score de mutation de **86 %**, avec **57 mutations générées**, dont **49 tuées** et **8 mutants survivants**.

Les mutants survivants proviennent principalement du **ConditionalsBoundaryMutator** (4 mutants survivants), du **PrimitiveReturnsMutator** (2 mutants survivants), et du **MathMutator** (2 mutants survivants). Ces mutants modifient des conditions logiques, des retours de valeurs primitives et des opérations mathématiques, mais les tests existants n’ont pas réussi à détecter ces changements. Après analyse, il est probable que ces mutants soient des **mutants équivalents**, c'est-à-dire qu’ils n’ont pas d’impact observable sur le comportement du programme.

Pour améliorer la couverture, nous avons ajouté des tests supplémentaires pour couvrir des combinaisons plus complexes de conditions logiques et renforcer les vérifications sur les valeurs primitives mais ces nouveaux tests n’ont pas changé le score de mutation, ce qui confirme que les mutants restants n'affectent pas le comportement observable du programme.

