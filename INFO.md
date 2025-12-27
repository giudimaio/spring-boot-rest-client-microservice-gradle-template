# `smart-console-be-buoni-sconto`

## Panoramica

Questa applicazione è stata generata tramite un _archetype_ Maven, ed è organizzata come segue:

- Modulo `application`, ospita l'applicazione vera e propria; contiene:
    - la classe _main_ dell'applicazione (`Application.java`)
    - le classi di configurazione (package `config`)
    - le classi repository relative a entità di dominio (package `repository`)
    - la logica di business (package `controller` e `service`)
- Modulo `model`, contiene le entità (POJO, tendenzialmente) referenziate dall'applicazione; si suddivide in:
    - package `entity`, contenente le definizioni JPA delle tabelle del database
    - package `framework`, contenente entità "di servizio"; ad esempio la classe `ApiConstants.java` raccoglie in un
      unico
      punto i nomi delle risorse REST
    - package `dto`, contiene gli oggetti preposti al transito di dati (Data Transfer Objects, appunto); si suddivide in
      package `web`, contenente i DTO utilizzati dalle classi `*Controller`, e package `persistence`, contenente i DTO
      utilizzati nell'integrazione con il database
- Modulo `framework`, contiene le classi che definiscono comportamenti e usi scelti per l'applicazione, ad esempio la
  _naming strategy_ adottata da JPA per assegnare un nome alle tabelle/colonne del database. Questo modulo è pensato per
  contenere classi agnostiche rispetto alla logica di business della specifica applicazione oggetto del progetto; in
  altre parole, questo modulo potrebbe (e dovrebbe) esistere in un unico punto centrale, ed essere condiviso da tutte le
  applicazioni che costituiscano un sistema.
- Modulo `ddl-gen`, questo modulo **non entra a far parte del _classpath_**, è solo un tool di servizio che consente di
  generare gli script SQL necessari alla creazione (e alla distruzione) delle tabelle definite nel modulo `model`.

## Mapping

Per la mappatura degli oggetti (ad esempio per mappare una _entity_ ad un DTO che rappresenti tale _entity_
nascondendone determinate _property_ e viceversa), questa applicazione adotta [`MapStruct`](https://mapstruct.org/).

## Logging

Questa applicazione si limita all'utilizzo delle interfacce generiche di SLF4J, e delega la configurazione e la scelta
di una specifica implementazione alla libreria `smart-console-logging-library`, introdotta nel `pom.xml` del modulo
`application` del progetto.

## Database

L'integrazione con il database avviene tramite Spring JPA. La relativa _dependency_ è introdotta dal modulo `model`, che
come detto sopra si occupa di definire le _entity_ del database.
**Tale modulo è pensato per le _entity_ specificamente definite per questa applicazione**, eventuali _entity_ di
interessa trasversale **vanno definite in un modulo "centrale"**.
L'accesso al database avviene tramite interfacce che estendono `JpaRepository`, tendenzialmente una per ogni entità;
tali interfacce risiedono nel package `repository` del modulo `application`.

## Integrazione con sistemi terzi

Questa applicazione _non_ prevede un modulo `integration`/`adapter` contenente la logica di integrazione con sistemi
terzi; il motivo di questa assenza è che si presuppone che un simile modulo possa essere trasversale a più microservizi,
e quindi non abbia senso duplicarlo in ognuno di essi.

Sempre in tema di integrazioni, l'applicazione è però dotata di uno strumento (_dependency_
`cz.habarta.typescript-generator`:`typescript-generator-maven-plugin`, nel modulo `model`) che genera automaticamente,
ad ogni _build_, un file `.ts` contenente la "traduzione" in TypeScript dei DTO definiti nell'applicazione.
È possibile configurare quali classi siano effettivamente processate dallo strumento agendo sulla _property_
`<classPatterns>` nella configurazione del plugin.

Inoltre, grazie al plugin `org.springdoc`:`springdoc-openapi-maven-plugin`, l'applicazione genera automaticamente
durante la fase `verify` anche il JSON contenente la descrizione OpenAPI dell'API da essa esposta.

`TODO:` aggiungere menzione a feign!

## Docker

Nella cartella [`src/main/docker`](./src/main/docker) sono già presenti due `Dockerfile` già funzionanti, uno per la
regolare build JVM, e un altro per la build nativa (basata su GraalVM).

## Qualità del codice

Questa applicazione è dotata di una configurazione che cerca di garantire un certo livello di qualità del codice, e uno
standard di stile condiviso.

Tali obiettivi sono perseguiti tramite i seguenti _plugin_:

- `maven-checkstyle-plugin`: in base alle regole definite nel file [`style_checks.xml`](./style_checks.xml), verifica
  che lo stile utilizzato nelle classi del progetto sia coerente con tali regole
- `spotbugs-maven-plugin`: verifica la presenza di eventuali _bug_, o pratiche di cattiva programmazione; tiene conto
  delle esclusioni definite nel file [`spotbugs_filter.xml`](./spotbugs_filter.xml)

Le verifiche esercitate da questi plugin vengono scatenate ad ogni _commit_ grazie al _pre-commit hook_ definito in [
`.githooks/pre-commit`](./.githooks/pre-commit).
Similmente, ad ogni commit, il _pre-commit hook_ [`.githooks/commit-msg`](./.githooks/commit-msg) verifica che il
messaggio di commit rispetti la convenzione [Conventional Commits](https://www.conventionalcommits.org/en/v1.0.0/).

## Caching

Questa applicazione implementa una banale pratica di _caching_ tramite `Caffeine`. Similmente a come accade per le
dipendenze responsabili del RBAC, le relative configurazioni sono relegate ad un modulo dedicato;
in questo modo, è possibile sostituire un _provider_ con un altro agendo semplicemente sul `pom.xml` del modulo
`application`, senza toccare il codice in sé.

## Changelog

Grazie all'adozione dello standard [Conventional Commits](https://www.conventionalcommits.org/en/v1.0.0/), è possibile
auto-generare un changelog dell'applicazione.
Esistono numerosi strumenti che consentono di farlo, questa applicazione è predisposta all'uso di [
`node-autochglog`](https://github.com/BudWhiteStudying/node-autochglog):

```bash
npx node-autochglog
```

Tale strumento può essere configurato tramite il file [`node-autochglog.config.json`](./node-autochglog.config.json).