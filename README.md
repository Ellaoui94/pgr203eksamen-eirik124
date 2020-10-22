![Java CI with Maven](https://github.com/kristiania/pgr203eksamen-eirik124/workflows/Java%20CI%20with%20Maven/badge.svg)
# pgr203eksamen-eirik124

#### Gruppemedlemmer:
Pål Anders Byenstuen, Eirik Lundanes og Vibeke Opgård.

-----------------------------------------------------------

## Beskrivelse av prosjekt

Oppgaven er utviklet med test-drevet parprogrammering. Vi har laget en webapplikasjon som kan lagre og liste ut prosjektmedlemmer fra databasen. Det er mulig å legge til et "project member" med fornavn, etternavn og e-postadresse og se den på websiden.

### Surefire Report
Link til Surefire report fra kjøring
[Test Report](https://github.com/kristiania/pgr203innevering3-eirik124/runs/1283456544)

-----------------------------
### Hvordan det bygges
Baseres på programmet IntelliJ fremgangsmåte kan variere fra program til program

Man bygger .jar-filen ved å velge View -> Tool Windows -> Maven. Da åpnes det et Maven-vindu under ```Lifecycle```. Man velger så ```Package``` og deretter kjører den pakking og bygging på et par sekunder.

-----------------------------
### Hvordan det kjøres

Vær sikker på at du har en PostgreSQL-database satt opp som er mulig å koble seg til. Deretter lager du en ```pgr203.properties``` som skal inneholde følgende
```
dataSource.url=jdbc:postgresql://server:port/databasename   //urlen til serveren
dataSource.username=username   //brukernavnet som har access til SQL serveren
dataSource.password=passord //her setter du et passord  som er sikkert som ingen vet
```

Man kjører serveren med .jar-filen ved å kjøre ```java -jar target/http-server.jar``` eller det man renamer filen til etter build. 
Default port er 8080. Nettsiden kjøres default på localhost:8080 eller den porten som blir satt av bruker.
