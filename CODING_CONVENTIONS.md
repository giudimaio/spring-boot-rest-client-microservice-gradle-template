# Convenzioni

Questo documento raccoglie le convenzioni utilizzate nello sviluppo del codice del presente progetto.

:warning: **Il presente documento è una bozza; va ancora validato alla luce dei processi già identificati per il più
ampio ambito di PICO.**

## Flusso di sviluppo

Il flusso di sviluppo proposto è una semplificazione
di [GitFlow](https://www.atlassian.com/git/tutorials/comparing-workflows/gitflow-workflow). In breve:

- il _default branch_ del repository è il _branch_ `develop`
- a meno di eccezioni, nessuno esegue commit/push direttamente su `develop`
- i task di sviluppo vengono raccolti sotto forma di _Issue_ sul repository GitHub
- ogni qualvolta uno sviluppatore si ritrova libero da task può semplicemente consultare la lista delle _Issue_ e
  selezionarne una che non sia già assegnata a qualcun altro, e che abbia la label `development` (dando priorità a
  quelle con _label_ `high priority` ed evitando quelle con label `blocked`)
- una volta selezionata una _Issue_, lo sviluppatore la assegna a sé stesso, quindi crea un nuovo _branch_ a partire dal
  _branch_ `develop`; la _naming convention_ per la creazione dei _branch_ è `<prefisso>/<numero-issue>`:
    - `<prefisso>` è una dicitura che indica il tipo di task, seguendo le stesse convenzioni dettate
      da [Conventional Commits](https://www.conventionalcommits.org/en/v1.0.0/) (e.g. se il task consiste nello sviluppo
      di una nuova _feature_, il prefisso sarà `feat/`; se si tratta della risoluzione di un bug sarà `fix/`)
    - `numero-issue` è il numero identificativo della _Issue_ scelta dallo sviluppatore, senza il carattere `#`
    - alcuni esempi: `feat/12`, `fix/14`, `refactor/44`
- sul proprio _branch_ così creato, lo sviluppatore **è esortato ad eseguire _commit_ frequenti**; questo consentirà se
  necessario di analizzare facilmente quanto fatto durante la risoluzione del task, perché ad ogni piccola modifica
  corrisponderà un commit (e un relativo messaggio)
- similmente, lo sviluppatore è esortato ad eseguire _merge_ frequenti da `develop` al proprio _branch_, così da
  mantenerlo aggiornato e semplificare le attività di _merge_ ad attività ultimate
- una volta che lo sviluppatore riterrà di aver completato l'attività descritta nella _Issue_ potrà aprire una _Pull
  Request_, che verrà quindi rivista ed eventualmente approvata, determinando il _merge_ del _branch_ dello sviluppatore
  nel _branch_ `develop`
- questo repository (all'interno della cartella `.github`) offre un template per la creazione della _Pull Request_, che
  viene automaticamente utilizzato da GitHub quando lo sviluppatore ne crea una nuova; tale template presenta dei
  contenuti esemplificativi, che lo sviluppatore deve sostituire con quelli rilevanti alla propria specifica _Pull
  Request_
- per preparare un nuovo rilascio, viene _creato_ (ogni volta) a partire dal _branch_ `develop` un _branch_ `release`;
  tale _branch_ viene utilizzato dai processi CI/CD per l'installazione negli ambienti di pre-produzione
- qualora durante i test di pre-produzione emergessero correzioni da eseguire, queste verranno effettuate _direttamente
  sul _branch_ `release`_; in altre parole, una volta generato il _branch_ `release` non si torna più indietro, si "
  martella" il _branch_ `release` finché questo non passa i test (ovviamente questo significa che prima di generare il
  _branch_ `release` occorre essere ragionevolmente certi che il codice sia pronto a superare i test)
- una volta superati i test di pre-produzione, contestualmente al passaggio in produzione, viene effettuato il _merge_
  del _branch_ `release` nel _branch_ `main`; quest'ultimo dovrà essere in ogni momento una fedele diapositiva dello
  stato del codice nell'ambiente di produzione
- terminato il passaggio in produzione, il _branch_ `release` viene eliminato (verrà ricreato al rilascio successivo) e
  viene eseguito il _merge_ del _branch_ `main` nel _branch_ `develop` (così da riallineare `develop` ad eventuali
  correzioni eseguite in corso d'opera durante i test di pre-produzione)

Esiste anche un processo dedicato per le fix urgenti:

- viene _creato_ un _branch_ `hotfix` a partire dal _branch_ `main`
- lo sviluppatore incaricato della fix urgente (che sarà comunque rappresentata da una _Issue_ GitHub, contrassegnata da
  label `development`, `high priority` e soprattutto `hotfix`) crea un nuovo branch, con le stesse convenzioni descritte
  per il processo normale **ma partendo dal _branch_ `hotfix` anziché dal _branch_ `develop`**
- lo sviluppatore implementa quindi quanto necessario sul proprio _branch_ così creato; una volta pronto alla
  pubblicazione, lo sviluppatore apre una _Pull Request_ secondo le stesse convenzioni descritte per il processo
  normale, ma la apre verso il _branch_ `hotfix`
- il contenuto del _branch_ `hotfix` viene utilizzato per eseguire i test di pre-produzione; similmente a come accade
  nel processo normale, qualsiasi correzione viene da qui in poi eseguita _direttamente sul _branch_ `hotfix`_
- una volta superati i test di pre-produzione, contestualmente al passaggio in produzione, viene effettuato il _merge_
  del _branch_ `hotfix` nel _branch_ `main`
- terminato il passaggio in produzione, il _branch_ `hotfix` viene eliminato (verrà ricreato al rilascio successivo) e
  viene eseguito il _merge_ del _branch_ `main` nel _branch_ `develop` (così da riallineare `develop` ad eventuali
  correzioni eseguite in corso d'opera durante i test di pre-produzione)

## Linguaggio

I documenti del progetto (file `README`, etc) sono scritti in lingua italiana, in quanto la probabilità che vengano
consultati da lettori non italiani è bassissima.

Il codice del progetto, le configurazioni, e tutto il resto del contenuto, sono invece scritti in lingua inglese.

All'interno del codice del progetto viene rispettato un [glossario](./GLOSSARY.md) comune, al fine di evitare di
chiamare una stessa entità in modi diversi. Ad esempio, l'espressione _"Richiesta di assistenza"_ è stata tradotta con
_"Support Request"_; _tutti_ i riferimenti a questa espressione dovranno quindi utilizzare il termine
_"Support Request"_, e non un suo sinonimo e.g. _"Assistance Request"_. Quando uno sviluppatore si imbatte in un nuovo
termine, aggiorna il glossario con la traduzione scelta per quel nuovo termine.

I messaggi dei _commit_ saranno scritti in lingua inglese, mentre i messaggi degli _squash commit_ introdotti
dalle MR nel _branch_ `develop` saranno in lingua italiana, così da determinare la generazione di note di rilascio in
lingua italiana.

## Pattern

L'applicazione osserva un pattern ricorrente per l'esposizione di operazioni tramite servizi web:

- le entità gestite dall'applicazione sono definite nel package
  `com.ibm.fstech.smart.console.be.buoni.sconto.model.entity`
- le operazioni da esporre ai client web sono definite nel package
  `com.ibm.fstech.smart.console.be.buoni.sconto.controller`, all'interno di
  classi che si conformano alle seguenti specifiche (si veda la classe [
  `DummyController`](./src/main/java/it/poste/pv/be/form/mail/controller/DummyController.java) a titolo di esempio):
    - per ogni risorsa REST esposta viene definita una classe dedicata
    - il nome della classe coincide con quello della risorsa REST volta al singolare, al quale viene aggiunto il
      suffisso `Controller`; ad esempio la classe contenente le operazioni relative all'esposizione della risorsa
      `/employees` si chiamerà `EmployeeController`
    - la classe è annotata con le annotazioni `@RestController`, `@RequestMapping` e `@RequiredArgsConstructor`
    - all'annotazione `@RequestMapping` viene passato come unico parametro il nome della _base path_ della risorsa
      esposta, e.g. `@RequestMapping("/employee")`
    - il nome delle risorse non viene però mai definito tramite stringhe statiche all'interno delle classi controller;
      viene invece definito nell'apposita classe `ApiConstants`, nel package
      `com.ibm.fstech.smart.console.be.buoni.sconto.model.framework`; in questo modo, l'annotazione diventa e.g.
      `@RequestMapping(ApiConstants.EmployeesController.BASE_PATH)`
    - la classe `ApiConstants` definisce quindi in un unico punto i nomi di tutte le risorse esposte, tramite
      sotto-classi
      statiche dedicate; eventuali valori potenzialmente riutilizzabili da molteplici classi controller vengono invece
      definiti nella sotto-classe statica `ApiConstants.Common`
    - le singole operazioni esposte presso la risorsa vengono definite tramite l'uso delle annotazioni `@GetMapping`,
      `@PostMapping`, `@PutMapping`, etc; a tali annotazioni viene passato come parametro di input il nome della risorsa
      esposta, se presente, oppure la costante `ApiConstants.Common.EMPTY_PATH` se l'operazione espone la risorsa
      presente
      alla _base path_
    - la responsabilità della classe è unicamente quella di esporre le operazioni, i metodi della classe delegano
      qualsiasi logica di business alla relativa classe _service_
    - la classe _service_ viene istanziata tramite una variabile `private final`, ci pensa poi Lombok (tramite
      l'annotazione `@RequiredArgsConstructor`) a definire il costruttore che consente l'istanziazione
- la logica di business eseguita a fronte dell'invocazione delle operazioni REST è implementata nel package
  `com.ibm.fstech.smart.console.be.buoni.sconto.application.service`, all'interno di classi che si conformano alle
  seguenti specifiche (si veda la classe `DummyService` nel modulo `application` a titolo di esempio):
    - per ogni risorsa REST esposta viene definita una classe dedicata
    - il nome della classe coincide con quello della risorsa REST volta al singolare, al quale viene aggiunto il
      suffisso `Service`; ad esempio la classe contenente la logica di business delle operazioni relative
      all'esposizione della risorsa`/employees` si chiamerà `EmployeeService`
    - la classe è annotata con l'annotazione `@Service` e, qualora sia necessaria l'istanziazione di altre classi (e.g.
      per l'accesso al database), `@RequiredArgsConstructor`
    - la responsabilità della classe è quella di fornire l'implementazione principale relativa alle operazioni REST
      definite nelle classi controller; eventuale logica specializzata (e.g. per accedere al database, invocare servizi
      esterni, etc) viene delegata ad apposite classi, che vengono instanziate tramite property `private final`
- la logica di accesso al database è implementata nel package
  `com.ibm.fstech.smart.console.be.buoni.sconto.application.repository`, all'interno di interfacce che si conformano
  alle seguenti specifiche (si veda la classe `DummyRepository` a titolo di esempio):
    - per ogni entità JPA definita nel package `com.ibm.fstech.smart.console.be.buoni.sconto.model.entity` del modulo
      `model` viene definita un'interfaccia dedicata
    - il nome dell'interfaccia coincide con quello dell'entità, al quale viene aggiunto il suffisso `Repository`; ad
      esempio l'interfaccia che consente l'accesso alla tabella definita dalla entity `Dummy` si chiamerà
      `DummyRepository`
    - l'interfaccia estende l'interfaccia `JpaRepository<K,V>`; se possibile, è opportuno raccogliere metodi di query
      comuni in interfacce "di base" ed estendere quelle; a titolo di esempio si veda l'interfaccia
      `BaseUuidRepository`, utilizzabile per tutte quelle _repository interface_ le cui entità presuppongano la presenza
      di una colonna `String uuid`
    - l'interfaccia non è annotata con alcuna annotazione; non è quindi necessario utilizzare l'annotazione
      `@Component`, `@Repository` o nessun'altra
    - le query definite all'interno dell'interfaccia devono utilizzare il più possibile la
      feature [JPA Query Methods](https://docs.spring.io/spring-data/jpa/reference/jpa/query-methods.html), l'utilizzo
      di query esplicite tramite l'annotation `@Query` va evitata a meno di eccezioni estreme
    - qualora fosse necessario, a fronte di una query, restituire solo alcune delle colonne della tabella, l'indicazione
      è quella di utilizzare la tecnica delle "proiezioni"; a titolo di esempio si veda l'interfaccia
      `BaseUuidRepository`, dove al fine di restituire la sola colonna `id` viene utilizzata la proiezione
      `IdProjection`
- i DTO (Data Transfer Objects) utilizzati dall'applicazione sono definiti nel package
  `com.ibm.fstech.smart.console.be.buoni.sconto.model.dto` del modulo `model`, e si dividono in due ulteriori package:
    - `web`: questi DTO sono utilizzati per l'integrazione tramite interfacce web, ad esempio trovano posto in questo
      package le request e response dei controller REST dell'applicazione
    - `persistence`: questi DTO sono utilizzati per l'integrazione con il database, ad esempio trovano posto in questo
      package le proiezioni che consentono di limitare l'output di una query solo ad alcune colonne della tabella

## Tooling

È necessario l'utilizzo di una IDE che consenta di configurare `CheckStyle` come tool di formattazione del codice,
tipicamente questo avviene tramite un plugin. Il plugin deve a sua volta essere configurato in modo tale da eseguire la
formattazione del codice in base al file `style_checks.xml`.

A titolo di esempio, in IntelliJ Idea la configurazione richiede le seguenti operazioni:

- installazione del plugin `Checkstyle-Idea`
- navigazione, nelle impostazioni di Intellij, al menu `Settings|Editor|Code Style`
    - selezionare, nel dropdown _Scheme_, l'opzione `Project`
    - cliccare l'icona _Ingranaggio_, quindi selezionare `Import Scheme|Checkstyle configuration`, infine selezionare il
      file `style_checks.xml` (dalla root di queste repository) come file di riferimento dal quale importare la
      configurazione

Analogamente, è necessario l'utilizzo di una IDE che consenta di applicare le regole di formattazione facilmente
all'intera codebase, per evitare di dover correggere lo stile un file alla volta; ancora una volta IntelliJ costituisce
un esempio virtuoso, consentendo di riformattare il codice in un intero modulo o addirittura di formattarlo pre-commit
(maggiori informazioni [qui](https://www.jetbrains.com/help/idea/reformat-and-rearrange-code.html)).