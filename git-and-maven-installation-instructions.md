# Maven
## For Windows
1.	Gå til https://maven.apache.org/download.cgi 
2.	Last ned Binary zip archive 
3.	Pakk ut et passende sted og kopier stien ditt du pakket ut
4.	Trykk på Windows-knappen og skriv “miljø” (“environment”) og åpne miljøvariabler
5.	Legg til to ny variabler under systemvariabler:
1.	Variabelnavn: M2_HOME, Verdi: Kopier inn stien fra 3.
2.	Variabelnavn: MAVEN_HOME, Verdi: Kopier inn stien fra 3.
6.	Finn “PATH”, trykk endre og legg til ny verdi: %M2_HOME%\bin
7.	Lagre
8.	Åpne kommandolinja (Windows + r —> skriv inn “cmd” —> enter)
9.	Skriv “mvn —version”. Skal da få opp maven versjon.

## For Mac
*  Hvis du ikke har homebrew, åpne terminalen og kjør:
 ```
 /usr/bin/ruby -e "$(curl -fsSL https://raw.githubusercontent.com/Homebrew/install/master/install)"
```
*  Kjør deretter:
```
brew install maven
```
*   Se punkt 11 i listen ovenfor


# Git
## For Windows, Mac og Linux
Se her: https://gist.github.com/derhuerst/1b15ff4652a867391f03