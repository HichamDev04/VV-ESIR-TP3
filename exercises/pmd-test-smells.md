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


Nous avons testé le projet commons-cli en utilisant PMD à l'aide de la règle JUnitTestsShouldIncludeAssert. Pour ce faire, nous avons utilisé la commande : 
```bash
pmd check -d commons-collections/src/test/java/org/apache/commons/collections4 -R category/java/bestpractices.xml/JUnitTestsShouldIncludeAssert -f text
```
Après avoir exécuté la commande PMD pour analyser les tests JUnit dans le projet **commons-cli**, nous avons constaté que plusieurs tests ne comportaient aucunes assertions, ce qui les rend inutiles car on ne peut pas vérifier si les conditions de test ont été satisfaites. Les résultats obtenus ont révélé que des classes comme `PredicateUtilsTest` ou `AbstractBidiMapTest` contenaient des méthodes de test sans aucune vérification des résultats attendus.

La solution générale à ce type de problème consiste à s'assurer que chaque méthode de test inclut au moins une assertion pour vérifier le comportement du code testé. Par exemple, si une méthode doit renvoyer une certaine valeur ou modifier un objet de manière attendue, une assertion comme `assertEquals(expectedValue, actualValue)` doit être ajoutée pour valider le résultat. Ainsi, nous pourrons garantir que le test échoue lorsque les conditions ne sont pas remplies.

Dans le cas du fichier BasicParserTest.java, bien que certaines méthodes soient désactivées (avec @Disabled), il est important de s'assurer que, lorsque ces tests seront activés, des assertions soient intégrées afin de valider le comportement attendu du BasicParser. Par exemple, pour tester le traitement correct d'une option avec un nom long, on pourrait ajouter une assertion telle que :
```java
@Test
public void testShortOptionConcatenatedQuoteHandling() throws Exception {
    // Configuration des arguments pour tester l'option courte avec gestion des guillemets
    String[] args = new String[] { "-a\"quoted value\"" };

    // Création d'une instance d'options, ajout de l'option courte 'a'
    Options options = new Options();
    options.addOption("a", true, "short option with concatenated quote");

    // Analyse des arguments avec le parser
    CommandLine cmd = parser.parse(options, args);

    // Vérification que l'option 'a' a bien été reconnue
    assertTrue(cmd.hasOption("a"));

    // Vérification que la valeur associée à l'option 'a' est bien "quoted value"
    assertEquals("quoted value", cmd.getOptionValue("a"));
}

```

