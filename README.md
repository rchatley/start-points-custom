Work in progress. Aiming for a command api such as this...

$ ./cyber-dojo create-collection refactoring=https://github.com/JonJagger/cyber-dojo-refactoring-exercises.git

$ ./cyber-dojo up exercises=refactoring

from a cyber-dojo server which will pull the repo and put it into a docker 
volume named refactoring which will then used as the source of exercises in the setup page.
