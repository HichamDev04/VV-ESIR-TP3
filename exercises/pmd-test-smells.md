# Detecting test smells with PMD

In folder [`pmd-documentation`](../pmd-documentation) you will find the documentation of a selection of PMD rules designed to catch test smells.
Identify which of the test smells discussed in classes are implemented by these rules.

Use one of the rules to detect a test smell in one of the following projects:

- [Apache Commons Collections](https://github.com/apache/commons-collections)
- [Apache Commons CLI](https://github.com/apache/commons-cli)
- [Apache Commons Math](https://github.com/apache/commons-math)
- [Apache Commons Lang](https://github.com/apache/commons-lang)

Discuss the test smell you found with the help of PMD and propose here an improvement.
Include the improved test code in this file.

## Answer

Les règles PMD que nous avons identifiées sont :

- **JUnitAssertionsShouldIncludeMessage** :
Il survient lorsque des assertions sont écrites sans message d'explication. Cela complique le débogage, car lorsque plusieurs assertions échouent, il est difficile de savoir laquelle a causé l'échec.

- **JUnitTestsShouldIncludeAssert** :
Il correspond à des tests JUnit qui ne contiennent pas d’assertions, ce qui les rend redondants ou inutiles. Nous avons couvert en cours l’importance d’inclure au moins une assertion dans chaque test unitaire, car un test sans assertion ne vérifie rien.

- **UnnecessaryBooleanAssertion** :
Cette règle PMD signale les tests qui vérifient des choses évidentes comme assertTrue(true) ou assertFalse(false). Ces tests n'apportent rien, car ils réussissent toujours.

- **UseAssertEqualsInsteadOfAssertTrue** :
Ce test smell correspond à une mauvaise utilisation de l'assertion assertTrue() là où une comparaison entre deux valeurs (assertEquals()) aurait été plus appropriée. 


Nous avons testé le projet commons-cli 
