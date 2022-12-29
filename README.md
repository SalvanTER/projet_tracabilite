# Execution des programmes 
## Log injector 

Ce programme permet de logger un projet Maven existant et de générer un projet Maven vide avec un fichier de log.

### Prérequis

- IntelliJ IDEA (ou tout autre IDE compatible avec Maven)
- Maven installé sur votre ordinateur

### Installation

1. Ouvrez IntelliJ et ouvrez le projet log_injector en utilisant la commande "Ouvrir un projet".
2. Assurez-vous que le projet est configuré comme un projet Maven. Si ce n'est pas le cas, vous pouvez le configurer en cliquant sur "File -> Project Structure -> Project Settings -> Project" et en sélectionnant "Maven" dans la liste déroulante "Project SDK".
3. Exécutez le programme

### Utilisation

Lorsque vous exécutez le programme, vous serez invité à renseigner le chemin vers le projet à logger et le chemin de destination pour le résultat, qui doit être un projet Maven vide. Entrez les chemins demandés et le programme effectuera le logging du projet en créant un projet Maven vide contenant les classes java loggés.

## Simple project result

Ce programme se décompose en 3 parties, une première partie concerne le simple programme permettant de réaliser des actions sur un dépôt, 
la deuxième partie concerne la simulation qui va simuler une dizaine d'utilisateurs faisant des actions sur le dépôt et la troisième parti du programme va générer les profils des utilisateurs

### Installation

1. Ouvrez IntelliJ et ouvrez le projet simple_application_result en utilisant la commande "Ouvrir un projet".
2. Assurez-vous que le projet est configuré comme un projet Maven. Si ce n'est pas le cas, vous pouvez le configurer en cliquant sur "File -> Project Structure -> Project Settings -> Project" et en sélectionnant "Maven" dans la liste déroulante "Project SDK".
3. Exécutez le programme

### Utilisation

Lorsque vous exécutez le programme un menu s'ouvre:
-Simulation(va générer une 10 utilisateurs qui vont faire des simulations sur un dépôt et afficher les 10% des utilisateurs ayant écris, lus et recherchés les produits les plus cher)
-Application(Execution normal du programme ou on pourra se connecter/s'inscrire et ajouter/supprimer/afficher/modifier/rechercher un ou plusieurs produits sur un dépôt)

Dans ces deux cas, à la fin de l'exécution le programme génèrera les fichiers suivants:
-products.json : la liste des produits dans le dépôt
-read_actions_profiles.json : profils des utilisateurs ayant lu une ou plusieurs fois dans le dépôt
-write_actions_profiles.json : profils des utilisateurs ayant écrit une ou plusieurs fois dans le dépôt
-search_profiles.json : profils des utilisateurs ayant cherchés un ou plusieurs produits
-trace.json : trace de l'application suite à son exécution
-users.json : liste des utilisateurs inscrit sur l'application
