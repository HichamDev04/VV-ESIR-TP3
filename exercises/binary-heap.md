# Implementing and testing a binary heap

A [*binary heap*](https://en.wikipedia.org/wiki/Binary_heap) is a data structure that contains comparable objects and it is able to efficiently return the lowest element.
This data structure relies on a binary tree to keep the insertion and deletion operations efficient. It is the base of the [*Heapsort* algorithm](https://en.wikipedia.org/wiki/Heapsort).

Implement a `BinaryHeap` class with the following interface:

```java
class BinaryHeap<T> {

    public BinaryHeap(Comparator<T> comparator) { ... }

    public T pop() { ... }

    public T peek() { ... }

    public void push(T element) { ... }

    public int count() { ... }

}
```

A `BinaryHeap` instance is created using a `Comparator` object that represents the ordering criterion between the objects in the heap.
`pop` returns and removes the minimum object in the heap. If the heap is empty it throws a `NotSuchElementException`.
`peek` similar to `pop`, returns the minimum object but it does not remove it from the `BinaryHeap`.
`push` adds an element to the `BinaryHeap`.
`count` returns the number of elements in the `BinaryHeap`.

Design and implement a test suite for this `BinaryHeap` class.
Feel free to add any extra method you may need.

Use the following steps to design the test suite:

1. With the help of *Input Space Partitioning* design a set of initial test inputs for each method. Write below the characteristics and blocks you identified for each method. Specify which characteristics are common to more than one method.
2. Evaluate the statement coverage of the test cases designed in the previous step. If needed, add new test cases to increase the coverage. Describe below what you did in this step.
3. If you have in your code any predicate that uses more than two boolean operators check if the test cases written to far satisfy *Base Choice Coverage*. If needed add new test cases. Describe below how you evaluated the logic coverage and the new test cases you added.
4. Use PIT to evaluate the test suite you have so far. Describe below the mutation score and the live mutants. Add new test cases or refactor the existing ones to achieve a high mutation score.

Use the project in [tp3-heap](../code/tp3-heap) to complete this exercise.

## Answer
**1.** En utilisant l’**Input Space Partitioning** pour définir un ensemble d'entrées de test pour chaque méthode de la classe `BinaryHeap`, nous avons identifié plusieurs caractéristiques clés qui influencent le comportement de chaque méthode. 

Pour la méthode `push`, les principales caractéristiques sont les **valeurs des éléments** à ajouter dans le `BinaryHeap`, ainsi que le **nombre d’éléments** déjà présents. Nous avons identifié plusieurs blocs pour ces caractéristiques : des éléments comparables (entiers, chaînes de caractères, objets personnalisés) et des tailles de tas variées (tas vide, petit tas, grand tas). De plus, il est nécessaire de prendre en compte si l’élément est plus grand ou plus petit que les éléments existants dans le tas, car cela influence la réorganisation de la structure.

Pour la méthode `pop`, la caractéristique principale est l’**état du tas** (vide ou non). Les blocs identifiés incluent les cas où le tas est vide, où il contient un seul élément et où il contient plusieurs éléments. Dans chaque cas, le comportement attendu varie : un appel à `pop` sur un tas vide devrait lever une exception (`NoSuchElementException`), tandis que dans les autres cas, l'élément minimal doit être retourné et supprimé correctement.

La méthode `peek` est similaire à `pop` dans la mesure où elle dépend également de l’**état du tas**. Les mêmes blocs sont applicables ici, à la différence que `peek` ne modifie pas le tas, donc le tas doit rester inchangé après l’appel à la méthode, ce qui est une caractéristique importante à vérifier.

Pour la méthode `count`, la caractéristique principale est le **nombre d’éléments dans le tas**, et les blocs à tester incluent un tas vide, un tas avec un seul élément, et un tas avec plusieurs éléments. La méthode `count` doit simplement retourner le nombre d'éléments présents dans le tas à tout moment.

Certaines caractéristiques, comme l’**état du tas** (vide, avec un seul élément, avec plusieurs éléments), sont communes à plusieurs méthodes, notamment `pop`, `peek`, et `count`. Les blocs définis pour ces caractéristiques peuvent alors être partagés et réutilisés dans les tests des différentes méthodes, ce qui va optimiser la conception de notre suite de tests.


**2** La couverture de test a commencé à 0%, car initialement, la classe `BinaryHeap` n'était pas encore implémentée, et aucune suite de tests n'avait été écrite. Après l'implémentation des méthodes essentielles et l'ajout des tests pour chaque méthode, nous avons commencé à observer une couverture du code plus complète.

Nous avons exécuté la suite de tests avec JaCoCo, et le rapport indique une couverture de **92%** pour les instructions et de **72%** pour les branches. Cependant, certaines branches et instructions ne sont pas encore entièrement couvertes. Par exemple, des branches dans les conditions de certaines méthodes de gestion du tas binaire ne le sont pas.

Les erreurs initiales ont été corrigées, et nous avons ajouté des tests pour couvrir des cas particuliers tels que les tas vides, les valeurs limites pour `push` et `pop`, et les comportements attendus lorsque des éléments sont retirés d'un tas non vide, ce qui a amélioré la couverture et permis de vérifier la robustesse de l'implémentation.


**3** Pour évaluer la couverture logique avec **Base Choice Coverage (BCC)** dans ce TP, nous avons vérifié les prédicats complexes dans les méthodes de la classe `BinaryHeap`. En particulier, nous nous sommes concentrés sur les conditions avec plusieurs opérateurs booléens, car ce sont celles qui nécessitent une attention particulière pour assurer une bonne couverture.

En examinant le code des méthodes comme `pop`, `peek` et `push`, nous avons identifié les conditions qui utilisent des opérateurs de comparaison (tels que `<`, `==`) et nous avons donc veillé à ce que chaque branche soit testée. Par exemple, dans la méthode `pop`, la condition vérifie si le tas est vide avant de retirer l'élément minimum. Nous avons donc ajouté des tests supplémentaires pour gérer les cas suivants :
- Lorsque le tas est vide et qu'une opération `pop` est tentée (ce qui doit déclencher une exception).
- Lorsque le tas contient un seul élément et que `pop` est appelé.
- Lorsque plusieurs éléments sont présents, pour vérifier la réorganisation correcte après un `pop`.
Nous avons également veillé à ce que les conditions dans `push` soient correctement testées, en tenant compte de divers cas limites, comme l'ajout d'un élément dans un tas vide ou l'ajout d'éléments dans un ordre décroissant pour vérifier le maintien de la propriété de tas.

Ces nouveaux tests ont contribué à améliorer la robustesse de notre suite de tests, en s'assurant que chaque condition logique est bien couverte. Comme l'indique le rapport JaCoCo, nous avons une couverture des instructions de **92%**, avec seulement **14 instructions manquantes** sur **199** et une couverture de branches de **81%**, ce qui montre que la plupart des chemins logiques dans le code sont couverts par nos tests.


**4.** 
