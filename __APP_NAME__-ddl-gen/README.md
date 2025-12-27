# DDL-gen application

## Summary

This application allows you to automatically generate DDLs for the creation and destruction of all JPA entities
expected by the microservice.

It is a Spring Boot application, but a "batch" application: it starts, generates the DDLs, and terminates.

The application is configured using the `application.yaml` file, where the
database connection parameters (a simple H2 in-memory database) are set, but above all, the SQL dialect to use and
the path to generate the SQL files.

The `application.yaml` file in turn references some environment variables, making
the application's behavior flexible. **It is therefore necessary to load an `.env` file, or otherwise define
the necessary environment variables, in order to run the application.**

## Usage

The following commands, run from the _root_ of the `ddl-gen` module, allow you to generate the DDLs.

When using Oracle dialect, the logs will show an exception because the respective "doesn't expect" to
connect to an H2 instance; however, the exception is not blocking, and the DDLs are still generated correctly.

**To generate DDLs with H2 syntax:**

```bash
export $(grep -v '^#' h2.env | xargs) && ./gradlew bootRun
```

**To generate DDLs with Oracle syntax:**

```bash
export $(grep -v '^#' oracle.env | xargs) && ./gradlew bootRun
```

**To generate all DDLs at once:**

```bash
export $(grep -v '^#' h2.env | xargs) && ./gradlew bootRun && export $(grep -v '^#' oracle.env | xargs) && ./gradlew bootRun
```