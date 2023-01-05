# Compte rendu TP Algorithmes génétiques

Le but de ce TP est de résoudre deux problèmes à l'aide d'algorithme génétiques.
Dans un premier temps nous allons mettre en place la classe "Algorithme Genetique" puis modéliser les deux problèmes précédement cités dans le but d'apporter une solution.

## 1 - Définition classe Algorithme Génétique

### 1.1 - Individu

Individu est une interface permettant à la classe Population de pouvoir manipuler des Individus quelque soit leur nature.

### 1.2 - Population

Une population est donc un ensemble d'invidus.

Les premières méthodes à mettre en places sont les méthodes : adaptation_moyenne et adaptation_maximale
Ces méthodes permettent respectivement de retourner la adaptation moyenne c,'est à dire le score de chaque individu pour le problème en cours divisé par le nombre d'invidu. La deuxième méthode retourne le score d'adaption du meilleur individu par rapport au problème en cours.

Maintenant on peut développer "selection", celle-ci, grâce à la méthode de la roulette selectionne un individu. Plus un élément de population a une forte adaptation plus il a de chance d'être retourner par cette méthode.

Enfin il y a la méthode preproduction. Ici on choisi via la méthode "selection" deux invidus de la population dans le but de les reproduire ensemble pour avoir deux enfants qui possèdent une partie de chacun des deux parents (croissement). Une fois la nouvelle population remplie, il faut appliquer une fonction de mutation pour apporter un caractère aléatoire à la nouvelle génération. Cela évite de convergé vers une solution sous-optimal.

## 2 - Problème du sac à dos

Ici l'objectif est de trouvé la combinaison d'objet permettant de remplir le sac à 100%.

Il faut donc choisir une représentation de ce problème en développant la classe "Individu SAD. 
Nous avons choisi d'utiliser un double pour stocker le poids max et deux listes : l'une contenu les éléments de la liste d'élément choisi et une autre liste similaire avec la valeur des éléments choisi.

Lors de l'initialisation les éléments sont donc définis aléatoirement.

Pour la fonction d'adaptation nous avons renvoyer le poids actuel de la selection. S'il dépasse le poids max alors la méthode renvoie 0.

*Les fonctions croissement et mutations sont triviales*

Enfin dans la classe "Client Sac A Dos" nous avons créer une double qui effectue des phases de reproductions jusqu'à un nombre de génération maximal ou score suffisant atteint.

Dans les deux cas proposés en entré l'algorithme trouve une solution. L'algothime semble donc fonctionnel.

## 3 - Problème de du voyageur de commerce

Dans ce nouveau problème l'objectif est de trouver le chemin le plus court passant par toute les villes et en revenant à la ville de départ.

Pour la représentation du problème (dans Individu_VDC) nous avons dans deux tableaux les coordonnées en x et en y de toute les villes et dans un dernier tableau l'odre de passage dans ces villes.

Pour l'adaptation le calcul revient à la caculer l'ensemble des distances séparant les villes une à une via le calcul :

$$d = \sqrt{(x_{i+1} - x_i)^2 + (y_{i+1} - y_i)^2}$$

Ensuite pour le croissement il faut faire attention à ne pas remettre les mêmes éléments dans le même enfant. Pour éviter cela nous enregistrons les éléments enregistrés dans les deux enfants dans la première étape et nous completons par les éléments manquants.

Pour la mutation on intervertis aléatoirement deux villes.

*La classe Client est globallement la même que pour le problème du Sac à dos*

Enfin nous avons rajouté l'étape d'optimisation locale donnée dans le sujet.

Notre algorithme trouve la meilleur solution pour les cas 4, 16 et 64. Cependant pour le cas avec 250 villes l'algorithme de trouve pas la solution attendue.

## 4 - Conclusion

Pour conclure ce TP nous a permis d'approfondir nos connaisance en algorithmes génétiques. Cette technique pourra nous être très utile par la suite pour trouver des solutions, au moins approché, à des problèmes.