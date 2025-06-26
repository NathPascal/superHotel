Feature: Ajout d'un hotel

  Scenario: L'utilisateur ajoute un hotel
    Given une ville existe avec un identifiant
    When j'ajoute un hotel avec ses informations
    Then la réponse doit contenir l'hotel avec son nom
