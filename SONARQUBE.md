# Configurazione di SonarQube in ambiente locale

In attesa di una CI/CD ancora più potente, eseguiremo in ambiente locale le scansioni SonarQube preliminari all'apertura
di Pull Request.

Di seguito si descrivono le operazioni necessarie alla configurazione di SonarQube nell'ambiente locale.

## Prerequisiti

Docker installato e relativo _daemon_ in esecuzione.

## Installazione

Eseguire i seguenti comandi per installare e avviare SonarQube:

```bash
docker volume create sonarqube_data

docker run \
  --name sonarqube-local \
  -p 9000:9000 \
  -v sonarqube_data:/opt/sonarqube/data \
  sonarqube:latest
```

## Prima configurazione

Raggiungere l'interfaccia web di SonarQube all'indirizzo http://localhost:9000/, tramite browser web.

Le credenziali di primo accesso sono `admin`/`admin`, ma verrà chiesto di modificare la password contestualmente al
primo accesso; scegliere una password a proprio piacimento e annotarla, servità ovviamente per tutti gli accessi
successivi.

Una volta aggiornata la password, e chiusi eventuali popup di benvenuto, verrà aperta la pagina di creazione di un nuovo
progetto (http://localhost:9000/projects/create).
Raggiunta tale pagina, occorre eseguire le seguenti azioni:

1. selezionare **_Create a local project_** (ultima opzione, dopo tutte le altre)
2. inserire un valore nell'input _Project display name_, ad esempio `smart-console-be-buoni-sconto`
3. inserire un valore nell'input _Project key_, ad esempio `smart-console-be-buoni-sconto` (può essere uguale al
   _Project display name_)
4. inserire il valore `develop` nell'input _Main branch name_, quindi premere il pulsante **_Next_**
5. nella pagina successiva selezionare **_Use the global setting_**, quindi premere il pulsante **_Create project_**
6. nella pagina successiva, selezionare l'opzione **_Locally_** (ultima opzione, dopo tutte le altre)
7. nella pagina successiva, inserire un valore nell'input _Token name_, ad esempio `smart-console-be-buoni-sconto` (può
   essere uguale al _Project display name_)
8. dal dropdown _Expires in_ selezionare l'opzione **_No expiration_** (questa sarebbe una _very bad practice_ di
   sicurezza in un vero ambiente CI/CD, ma siccome stiamo configurando un'istanza locale va bene così)
9. premere il pulsante **_Generate_** a destra del dropdown
10. prendere nota del token generato (**non sarà più possibile visualizzarlo!**), quindi premere il pulsante *
    *_Continue_**
11. nella nuova sezione apparsa sotto a quella del token selezionare l'opzione **_Maven_**, quindi copiare lo snippet di
    codice che apparirà
12. eseguire lo snippet nella root del progetto di questa applicazione

Da questo momento in poi sarà possibile eseguire la scansione SonarQube a piacimento, utilizzando il medesimo snippet (
si consiglia di sviluppare un piccolo script, così da non dover copia-incollare lo snippet ogni volta).

## Utilizzi successivi

Grazie all'utilizzo di un volume persistente, nonostante si tratti sia eseguita tramite Docker questa istanza di
SonarQube è in grado di mantenere memoria delle scansioni eseguite, anche a seguito di arresto/riavvio del container.

Sarà sufficiente (ri)avviare il container, al quale è stato assegnato il nome `sonarqube-local` al momento
della [creazione](#installazione):

```bash
docker start sonarqube
```