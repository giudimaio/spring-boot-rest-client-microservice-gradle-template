# Spring Boot Backend Template

Questo progetto serve come **template per applicazioni Spring Boot backend** basate su Gradle.  
Permette di creare rapidamente nuovi progetti con una struttura modulare e configurazioni pronte all'uso.

---

## Struttura del template

Il template è organizzato in più moduli; contiene:

### Modulo `application`

- Contiene il **core dell’applicazione** Spring Boot.
- Struttura tipica:

  ```
  APP_NAME-application/
  ├─ src/main/java/com/CORP_NAME/it/APP_NAME/application/
  │ └─ Application.java # classe main di Spring Boot
  ├─ src/main/resources/
  │ └─ application.yml # configurazioni Spring
  ├─ src/test/java/...
  └─ build.gradle.kts # configurazioni Gradle per il modulo
  ```


- **Classi principali**:
    - `Application.java`: classe main annotata con `@SpringBootApplication`.
    - Directory `com.__CORP_NAME__.it.__APP_NAME__.application` da sostituire con i nomi della corporation e dell’applicazione.

---

## Generazione di un nuovo progetto

Per creare un nuovo progetto basato su questo template, utilizza lo script `generate.py` presente nella cartella `tools/`.

## Script di generazione del progetto

Lo script `generate.py` permette di creare un nuovo progetto Spring Boot a partire dal template, sostituendo placeholder e rinominando le cartelle.

---

### Opzioni

| Opzione          | Obbligatoria | Descrizione                                                               |
|-----------------|--------------|--------------------------------------------------------------------------|
| `--program-name` | Sì           | Nome del progetto/program                                    |
| `--app-name`     | Sì           | Nome dell’applicazione                            |
| `--corp-name`    | Sì           | Nome della corporation                                 |
| `--out-dir`      | No           | Cartella di destinazione per il progetto generato (default: `./out`)     |
| `--force`        | No           | Sovrascrive eventuali cartelle già esistenti                              |

---

### Come funziona lo script

1. **Copia del template:**  
   Copia tutto il contenuto del template in una **cartella temporanea**, evitando di modificare il template originale.

2. **Rinomina delle directory:**  
   Tutte le cartelle contenenti i placeholder `__CORP_NAME__` e `__APP_NAME__` vengono rinominate, anche se fanno parte di nomi più lunghi (es. `__APP_NAME__-application` → `viaggiatreno-application`).

3. **Sostituzione dei placeholder nei file:**  
   Nei file con estensione `.java`, `.kt`, `.gradle`, `.yml`, `.yaml`, `.properties`, il contenuto `__CORP_NAME__` e `__APP_NAME__` viene sostituito con i valori forniti.

4. **Rimozione dello script di generazione:**  
   Lo script `generate.py` presente nella cartella `tools/` viene rimosso dal progetto generato.

5. **Creazione della cartella finale:**  
   La cartella temporanea viene spostata nella destinazione finale con nome:



### Esempio di esecuzione

```bash
python3 tools/generate.py \
    --program-name my-program \
    --app-name my-app \
    --corp-name my-corp \
    --out-dir my-out-dir
```





