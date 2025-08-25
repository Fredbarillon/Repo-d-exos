Mise en place d’un projet TypeScript

1) Initialiser le projet et installer TypeScript :
   mkdir mon-projet && cd mon-projet
   npm init -y
   npm install -D typescript

2) Générer le tsconfig.json :
   npx tsc --init

3) Organisation :
   - Créer un dossier src/
   - Ajouter un fichier src/index.ts

4) Configuration tsconfig.json :
   "include": ["src"],
   "exclude": ["node_modules"],
   Décommenter et régler :
   "rootDir": "./src",
   "outDir": "./dist"

5) Compiler le projet :
   npx tsc



 Questions de réflexion

* Pourquoi installe-t-on TypeScript comme dépendance de développement et non comme dépendance “classique” ?
  parce que l'on veut pas de compilation en prod, juste en dev
* Quelle est l’utilité de `npx` par rapport à une installation globale d’outils ?
  ca sert à exécuter un outil installé dans le projet (dans node_modules) sans avoir besoin de l’installer globalement sur la machine
* Pourquoi ne pas utiliser `rm -rf` pour nettoyer un dossier dans un projet partagé entre plusieurs OS ?
  car on peu avoir des soucis entre version de windows, de terminal, de OS... mieux vaut prendre rimraf