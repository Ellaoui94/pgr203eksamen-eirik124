![Java CI with Maven](https://github.com/kristiania/pgr203eksamen-eirik124/workflows/Java%20CI%20with%20Maven/badge.svg)
# PGR203 EKSAMEN

#### Gruppemedlemmer:
Pål Anders Byenstuen, Eirik Lundanes og Vibeke Opgård.

-----------------------------------------------------------

## Beskrivelse av prosjekt

Til eksamen i PGR203 Avansert Java har vi laget en webapplikasjon som kan lagre og liste ut prosjekter, oppgaver og medlemmer. Det er mulig å legge til et medlem bestående av navn og e-postadresse på et prosjekt, og tildelingen vises på websiden. Et prosjekt består av navn, prosjektmedlem, oppgaver, beskrivelse og status på prosjektet.

Programmet kan brukes ved å besøke localhost:8080 i nettleseren.

### Arbeidsform
Arbeidet med innlevering 1-3 foregikk i stor grad som testdrevet par(trio)-programmering over Discord, noe som fungerte veldig bra. Eksamensoppgaven krevde derimot at vi måtte utforske temaet enda dypere, og vi begynte derfor å jobbe mer fysisk sammen. Istedenfor at vi fordelte issues mellom oss, så fokuserte alle tre på ett og ett issue, i hovedsak på samme maskin. Derfor fremkommer commitloggen ganske misvisende når det kommer til deltakelse i prosjektet. [Projects-tabben](https://github.com/kristiania/pgr203eksamen-eirik124/projects/1) er en bedre representasjon av arbeidsfordelingen enn commitloggen.

Link til par(trio)-programmeringsvideo:
[Parprogrammerings-video](https://youtu.be/t6ZQ8bYZdlo)

Link til pull request som viser hvordan vi jobbet sammen over Discord på innlevering 2:
[pgr203innlevering2-eirik124/pull/1](https://github.com/kristiania/pgr203innlevering2-eirik124/pull/1)


## GitHub Actions
Link til Github Actions:
[Github Actions](https://github.com/kristiania/pgr203eksamen-eirik124/actions)

-----------------------------
## UML-modeller


### Database-struktur

![](docs/database_structure.png)

### Server-struktur

![](docs/http_structure.png)

Struktur som viser hva som skjer i programmet når man henter ut eksempelvis prosjektmedlemmer:
![](docs/server_structure.png)

-----------------------------
## Hvordan det bygges

Man bygger .jar-filen ved å velge View -> Tool Windows -> Maven. Da åpnes det et Maven-vindu under ```Lifecycle```. Man velger så ```Package``` og deretter kjører den pakking og bygging på et par sekunder.

-----------------------------
## Hvordan det kjøres

Det må settes opp en PostgreSQL-database som er mulig å koble seg til. Deretter lager du en ```pgr203.properties```-fil som skal inneholde følgende:
```
dataSource.url=jdbc:postgresql:   //server:port/databasename   //URL'en til serveren
dataSource.username=username      //Brukernavnet som har access til SQL-serveren
dataSource.password=passord       //Et hemmelig og sikkert passord
```

Man kjører serveren med .jar-filen ved å kjøre ```java -Dfile.encoding=UTF-8 -jar target/http-server.jar``` eller det man renamer filen til etter build. 
Default port er 8080. Nettsiden kjøres default på localhost:8080 eller den porten som blir satt av bruker.

-----------------------------
## Funksjonalitet

Hovedsiden består av 4 kategorier (medlemmer, prosjekter, oppgaver og tildelinger). I de tre første kategoriene får bruker mulighet til å opprette objekter av hver type og oppdatere/endre disse samt få opp en liste med alt som er opprettet. Når man oppretter objekter blir man omdirigert tilbake dit man var. I tildelingskategorien kan medlemmer og oppgaver tildeles til prosjekter, og det kan velges status og legges til beskrivelse av prosjektet. Det finnes en egen seksjon som viser alle tildelinger og en egen seksjon der man kan filtrere prosjekter basert på status.

## Eksamen sjekkliste

### Sjekkliste for innlevering
- [ ] Dere har lastet opp en ZIP-fil med navn basert på navnet på deres Github repository
- [X] Koden er sjekket inn på github.com/kristiania-repository
- [X] Dere har committed kode med begge prosjektdeltagernes GitHub konto (alternativt: README beskriver arbeidsform)
README.md

### Readme.md

- [X] README.md inneholder en korrekt link til Github Actions
- [X] README.md beskriver prosjektets funksjonalitet, hvordan man bygger det og hvordan man kjører det
- [ ] README.md beskriver eventuell ekstra leveranse utover minimum
- [X] README.md inneholder et diagram som viser datamodellen
Koden

### Koden

- [X] mvn package bygger en executable jar-fil
- [X] Koden inneholder et godt sett med tester
- [X] java -jar target/...jar (etter mvn package ) lar bruker legge til og liste ut data fra databasen via webgrensesnitt
- [X] Programmet leser dataSource.url , dataSource.username og dataSource.password fra pgr203.properties for å connecte til databasen
- [X] Programmet bruker Flywaydb for å sette opp databaseskjema
- [X] Server skriver nyttige loggmeldinger, inkludert informasjon om hvilken URL den kjører på ved oppstart
Funksjonalitet

### Funksjonalitet

- [X] Programmet kan liste prosjektdeltagere fra databasen
- [X] Programmet lar bruker opprette nye prosjektdeltagere i databasen
- [X] Programmet kan opprette og liste prosjektoppgaver fra databasen
- [X] Programmet lar bruker tildele prosjektdeltagere til oppgaver
- [ ] Flere prosjektdeltagere kan tildeles til samme oppgave
- [X] Programmet lar bruker endre status på en oppgave

### Ekstra poeng sjekklist
- [X] Håndtering av request target "/"
- [X] Avansert datamodell (mer enn 3 tabeller)
- [X] Avansert funksjonalitet (redigering av prosjektmedlemmer, statuskategorier, prosjekter)
- [ ] Implementasjon av cookies for å konstruere sesjoner: https://tools.ietf.org/html/rfc6265#section-3
- [X] UML diagram som dokumenterer datamodell og/eller arkitektur (presentert i README.md)
- [ ] Rammeverk rundt Http-håndtering (en god HttpMessage class med HttpRequest og HttpResponse subtyper) som gjenspeiler RFC7230
- [X] God bruk av DAO-pattern
- [X] God bruk av Controller-pattern
- [X] Korrekt håndtering av norske tegn i HTTP
- [X] Link til video med god demonstrasjon av ping-pong programmering
- [ ] Automatisk rapportering av testdekning i Github Actions
- [ ] Implementasjon av Chunked Transfer Encoding: https://tools.ietf.org/html/rfc7230#section-4.1
- [ ] Annet
