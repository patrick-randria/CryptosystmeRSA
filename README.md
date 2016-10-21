Cryptosystème à clé publique RSA (version simplifiée)
====================================================

0- Compilation de tous les fichiers:

   $javac *.java
   
1- Génération de nombre premier en réimplémentant l'algo de Miller Rabin : genpremier
   Prend en entrée standard la taille t et le nombre de boucle k
   
   $java genpremier 512 40
   
2- Calcul de l'inverse en arithmétique modulaire : EuclideEtendu.java

   Calcul de r = u*a + v*b avec a et b lus en entrée
   
   $java EuclideEtendu 120 23
   

3- Génération de la paire de clé :  gencle.java   

   Clés publiques enregistrées dans [nom].pub sous le format <t n b>
   Clés privées enregistrées dans [nom].priv sour le format <t n p q a b>
   Utiliser la commande suivante pour générer des clés de 32 bits et enregistrer les clés dans jol-32.pub et jol-32.priv
   
   $java gencle 32 jol-32
   
4- Programme de chiffrement :  ciffre.java

   Pour chiffrer le contenu du fichier message.txt en mode ECB et avec les clés dans jol-32.pub, utiliser la commande suivante
   
   $cat message.txt | java chiffre jol-32
   
5- Programme de déchiffrement : dechiffre.java

   $cat jol-32.chiffre | java dechiffre jol-32
   
6- Programme de signature : signe.java

   Signe le contenu de message.txt avec les clés privées dans jol-32.priv et ecrit la signature dans jol-32.sign
   
   $cat message.txt | java signe jol-32
   
7- Vérification de signature   

   $cat message.txt | java verifie jol-32 jol-32.sign
  
   
   