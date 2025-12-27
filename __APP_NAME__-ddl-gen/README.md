# DDL-gen application

## Sommario

Questa applicazione consente di generare automaticamente le DDL per creazione e distruzione di tutte le entità JPA
previste dal micro-servizio.

Si tratta di un'applicazione Spring Boot, ma di tipo "batch": parte, genera le DDL, termina.

La configurazione dell'applicazione avviene tramite il file `application.yaml`, dove vengono impostati i
parametri di connessione al database (un mero database H2 in-memory), ma soprattutto il dialetto SQL da utilizzare e
la path alla quale generare i file SQL.

Il file `application.yaml` a sua volta fa riferimento ad alcune variabili d'ambiente, così da rendere
flessibile il comportamento dell'applicazione. **È quindi necessario caricare un file `.env`, o comunque definire
le variabili d'ambiente necessarie, al fine di eseguire l'applicazione.**

## Utilizzo

I seguenti comandi, lanciati dalla _root_ del modulo `ddl-gen`, consentono la generazione delle DDL.

Utilizzando i dialetti MariaDB e Oracle i log mostreranno un'eccezione, in quanto i rispettivi "non si aspettano" di
connettersi ad un'istanza H2; l'eccezione tuttavia non è bloccante, e le DDL vengono comunque generate correttamente.

**Per generare le DDL con sintassi H2:**

```bash
export $(grep -v '^#' h2.env | xargs) && ./gradlew bootRun
```

**Per generare le DDL con sintassi Oracle:**

```bash
export $(grep -v '^#' oracle.env | xargs) && ./gradlew bootRun
```

**Per generare tutte le DDL in un colpo solo:**

```bash
export $(grep -v '^#' h2.env | xargs) && ./gradlew bootRun && export $(grep -v '^#' oracle.env | xargs) && ./gradlew bootRun
```