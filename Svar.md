# Svar på spørsmål

*I denne filen legger du inn svar på spørsmålene som stilles i oppgaveteksten, eventuelt kommentarer og informasjon om kreative løsninger*

## Kommentarer
Har i deler av oppgaven samarbeidet godt med Åsmund Groven (oppg 4). Har også diskutert oppgave 6 med Belming Husanovic og Skage Lysgaard. Fikk litt tips for å implementere Locations av gruppeleder Rikke. Har også kommentert store deler av koden, er ikke sikkert svarene i denne filen er like forståelige. 

## Spørsmål

## Oppgave 1 - Abstrakte Ting

### 1.1) 

1. boolean isPlaying(); returnerer True om spilleren er aktiv og klar til å spille. 
2. void movePlayer(); som har feks et parameter basert på gridden til spillet og retning.  
3. int getDamage(); som returnerer en int basert på hvor mye damage spilleren gir andre aktører.
4. int getHealthLost(); som returnerer en int (health) basert på hvor mye damage aktøren har tapt på å bli angrepet av spilleren. Eller omvendt. 
5. int getHealth(); som returnerer en int basert på hvor mye health aktøren har igjen.

### 1.2) 
1.  int getCurrentHealth(); returnerer hvor mange health points aktørene har igjen. Den kan feks returnere healthen til kaniner, edderkopper eller spilleren. 
2. default String getEmoji(); returnerer emojien til de forskjellige aktørene. 
3. int getMaxHealth(); returnerer aktørene sin maksimale health.
4. int handleDamage(int amount); returnerer "informasjon" til aktørene basert på egenskapene og hvor mye de forskjellige aktørene tåler av damage i utgangspunktet. Den er alltid mindre eller lik getCurrentHealth.
5. default boolean isDestroyed(); returnerer en true eller false verdi basert på om aktøren er ødelagt og om den skal bli fjernet fra kartet eller ikke. 


### 1.3)
1. int getCurrentHealth(); blir implementert i Carrot-klassen gjennom interfacet IItem. Den returnerer hp, altså healthen til gulroten, som er en aktør i spillet. 
2. default String getEmoji(); returnerer "Printer" ved hjelp av "coloured" metoden som tar inn en emoji som string, og gir den en farge. 
3. int getMaxHealth(); returnerer et integer som beskriver hp eller healthen til aktøren. 
4. string getShortName(); returnerer en string, som er navnet til aktøren. Et navn har også en ekte gulrot.  
5. string getTaste(); er en metode som ikke finnes i klassen, men som kan returnere en string som sier noe om hva aktøren smaker.  



### 1.4)
Amulet, Carrot, Dust, IActor, Wall blir implentert direkte. Alle de andre klassene under filen objects blir implementert indirekte gjennom andre interfaces, som alle ligger under interfacet IItem. 

### 1.5)
Opprettet klassen Gold som implementerer IItem-interfacet. Implementerte videre metodene getCurrentHealth, getDefence, getMaxHealth, getLongName, getShortName, getSize, handleDamage og getSymbol. 
- Satt SYMBOL = 'G';
- Endret getLongName til å returnere "gold".
- Endret getShortName til å returnerer "expensive gold".
- Endret getMaxHealth til å returnere 15.
- Endret getSize til å returnere 2.
- getCurrentHealth returnerer hp.

## Oppgave 2 - The Rabbit

### 2.1)
IActor klassen utvider og benytter metodene til IItem interfacet. Extend tyder dermed at IActor arver på IItem, dermed er IActor en subclass (barnet), og IItem er en superclass (forelder). Arv kan defineres som prosessen der en klasse tilegner seg egenskapene (metoder og felt) til en annen. Ved bruk av arv blir informasjonen gjort håndterbar i en hierarkisk rekkefølge.

### 2.2)
I metoden doTurn() blir det først bestemt om Rabbit er sulten eller ikke i isHungry. Om den er sulten, spiser den i ruten den står i om mulig (eatIfPossible()). Om den har mat (gotFood), er vurderingen slutt og det returneres tilbake til starten av metoden, en direction blir valgt i selectMove metoden, og performMove metoden blir så brukt, som tar inn game og dir. 

### 2.3)
Vil implementere getPossibleMoves. Starter med å lage en liste for mulige moves. Videre bruker jeg for-each loop der det blir iterert gjennom alle retninger rundt lokasjonen. Der benyttes enumet GridDirection, som er en klasse der retningene blir lagret i form av x og y koordinater. Feks er GridDirection.EAST dx: 1 og dy 0. Bruker deretter metoden canGo som tar inn to parameter, Location from og GridDirection dir. Den sjekker om det er mulig for aktøren å gå i en retning. Om den kan gå i retningen, legges retningen til i possibleMoves og returneres. 

### 2.4)
(Svar her)

## Oppgave 3 - Objektfabrikken

### 3.1)
Symbolet "@" representerer et Player-objekt, og en finner det i klassen Player, som en feltvariabel. feltvariabelen gjør slik at alle metodene i klassen kan bruke variabelen.
Symbolet "." representerer Dust-objektet og en finner det i klassen Dust, som en feltvariabel.
La til støtte for å opprette Dust-objekter og Gull-objekter. 

### 3.2)
Fordi, slik som det er forklart i SOLID, så påpeiker det første punktet at hver klasse skal ha et enkelt ansvar, og at det ansvaret kun skal anngå den spesifike klassen. Det vil si at om vi hadde endret symbolet i en klasse, så hadde det bare hatt påvirkning på den spesifikke klassen. Derfor vil vi endre, slik at vi tar ut symbolet direkte fra klassen ved å bruke "klassenavn".SYMBOL, istedenfor å endre character i hver klasse.

Endret metoden createItem slik at dette nå gjøres. 

Problemet er no fikset fordi metoden i ItemFactory kaller på Rabbit.SYMBOL i Rabbit klassen, og vil hente ut verdien som er angitt som SYMBOL. Om den verdien er endret til "r", vil den bli returnert "r", ettersom det er denne verdien ItemFactory returnerer.

### 3.3)
Gikk inn i mappen resources og videre inn i en tekstfil, som representerer kartet. GameMap leser inn tekstfilene fra level1.txt, og lager det visuelt. La inn noen symbol som representerer Gull, altså "G", inn i tekstfilen. Da vises dette i spillets kart.


## Oppgave 4 - Et smartere kart

### 4.1)
Gjorde oppgave 4 i samarbeid med Åsmund Groven.

Lager en tom liste av locations og et tomt location objekt, lagret som Neighbourhood. Lager et tomt tempLocation objekt. Legger videre til en current location slik at settet ikke er tomt når det itereres over. Videre lager man en for loop for å repetere dette for alle dist. Da itererer dist antall ganger slik at man legger til naboene "dist" antall ut fra currentLocation. 

Lager en tempList som er en midlertidig liste naboer, for å unngå duplikat. Så brukes en for-each loop for å loope gjennom currentLocation i tempList.
Videre lages en for-each loop for directions i alle 8 retninger. Da sjekkes alle dir rundt currentLocation. Sjekker så om det er mulig å gå i retningen . Om det er mulig, settes tempLocation til currentLocation. Sjekker videre om listen allerede ikke inneholder tempLocation, da legges i tilfellet tempLocation til i Neighbourhood. 

Fjerner videre lokajonen vi står i, slik at bare nabolaget er i listen. Sorterer videre lokasjonene i riktig rekkefølge. 

### 4.2)
getNeighbourhood-metoden ble forbedret ved å brukte metoden canGo, som sjekker om det er mulig å gå i dir rundt currentLocation.

### 4.3)
Brukte funksjonen til getNeighbourhood i getReachable, og den bestod testene.

## Oppgave 5

### 5.1)
Kjørte main metoden. Det tar ikke mange moves før kanine går tom for energi. 

### 5.2)
Implementerte metoden directionTo. Gjorde først et forsøk å returnere retningene basert på dx og dy verdiene. Det fungerte ikke, klarte ikke feilsøke nok til å finne ut hvorfor. 
Valgte derfor å skrive en funksjon som bruker en for-each loop. Den sjekker om distansen til neighbor er mindre enn enn distansen mellom location og punktet, og returnerer dir om det er true. 

### 5.3)
Endret i doTurn for å gjøre Rabbit smartere. Brukte metoden getNearbyItems. Bedre forklaring i kommentarene i koden. 

### 5.4)
(svar her)

### 5.5)
(svar her)

## Oppgave 6

### 6.1)

Se pickUp metoden i Player klassen for kommentarer. 

For hasItemm returnerer man bare inventory.contains(item). Den returnerer true om listen inneholder itemet. 

### 6.2)
Lager en metode som sjekker om inventory er tomt eller ikke. Om det er tomt, displayes en melding om at det er tomt. 
Om det ikke er tomt, dropper den det første itemet i listen. 

### 6.3)
Se koden for kommentaren. 

## Fri oppgave (Oppg. 7)

### Plan
(Skriv planen her)

### Utførelse
(Forklar i korte trekk hva du har gjort)

### Flere utvidelser
(Legg inn eventuelle flere utvidelser du har gjort her)
