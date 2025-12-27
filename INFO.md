# `spring-boot-rest-client-microservice`

## Overview

This application was generated using a Maven archetype and is organized as follows:

- The `application` module hosts the application itself; it contains:
- The application's main class (`Application.java`)
- Configuration classes (`config` package)
- Repository classes for domain entities (`repository` package)
- Business logic (`controller` and `service` packages)
- The `model` module contains the entities (usually POJOs) referenced by the application; it is divided into:
- The `entity` package contains the JPA definitions of the database tables
- The `framework` package contains "service" entities; For example, the `ApiConstants.java` class collects the names of REST resources in a
single location
- the `dto` package contains the objects responsible for data transfer (Data Transfer Objects); it is divided into the
`web` package, containing the DTOs used by the `*Controller` classes, and the `persistence` package, containing the DTOs
used in database integration.
- the `framework` module contains the classes that define behaviors and uses chosen for the application, for example, the
_naming strategy_ adopted by JPA to assign a name to database tables/columns. This module is designed to
contain classes agnostic to the business logic of the specific application being designed; in
other words, this module could (and should) exist in a single central location and be shared by all
applications that make up a system.
- `ddl-gen` module, this module **does not become part of the _classpath_**; it is only a service tool that allows you to
generate the SQL scripts needed to create (and destroy) the tables defined in the `model` module.

## Mapping

For object mapping (for example, to map an _entity_ to a DTO that represents that _entity_,
hiding certain _properties_ and vice versa), this application uses [`MapStruct`](https://mapstruct.org/).

## Logging

This application is limited to using the generic SLF4J interfaces, and delegates the configuration and choice
of a specific implementation to the `smart-console-logging-library` library, introduced in the `pom.xml` of the project's
`application` module.

## Database

Database integration is done via Spring JPA. The related dependency is introduced by the `model` module, which
as mentioned above, defines the database's entities.
**This module is designed for the entities specifically defined for this application**. Any entities of cross-application interest
**must be defined in a "central" module**.
Access to the database is via interfaces that extend `JpaRepository`, typically one for each entity;
these interfaces reside in the `repository` package of the `application` module.

## Integration with third-party systems

This application does not include an `integration`/`adapter` module containing the integration logic with
third-party systems; the reason for this absence is that it is assumed that such a module can be used across multiple microservices,
and therefore it makes no sense to duplicate it in each of them.

Still on the subject of integrations, the application is equipped with a tool (_dependency_
`cz.habarta.typescript-generator`:`typescript-generator-maven-plugin`, in the `model` module) that automatically generates,
at each _build_, a `.ts` file containing the "translation" into TypeScript of the DTOs defined in the application.
You can configure which classes are actually processed by the tool by modifying the _property_
`<classPatterns>` in the plugin configuration.

Furthermore, thanks to the `org.springdoc`:`springdoc-openapi-maven-plugin` plugin, the application automatically generates
during the `verify` phase the JSON containing the OpenAPI description of the API it exposes.

## Docker

Two working Dockerfiles are already present in the [`src/main/docker`](./src/main/docker) folder, one for the
regular JVM build, and another for the native build (based on GraalVM).

## Code Quality

This application is configured to ensure a certain level of code quality and a
shared style standard.

These goals are achieved through the following plugins:

- `maven-checkstyle-plugin`: based on the rules defined in the [`style_checks.xml`](./style_checks.xml) file, checks
that the style used in the project's classes is consistent with these rules.
- `spotbugs-maven-plugin`: checks for any bugs or poor programming practices; takes into account
the exclusions defined in the [`spotbugs_filter.xml`](./spotbugs_filter.xml) file.

The checks performed by these plugins are triggered at every commit thanks to the pre-commit hook defined in [
`.githooks/pre-commit`](./.githooks/pre-commit).
Similarly, at every commit, the pre-commit hook [`.githooks/commit-msg`](./.githooks/commit-msg) verifies that the
commit message complies with the [Conventional Commits](https://www.conventionalcommits.org/en/v1.0.0/) convention.

## Caching

This application implements a simple caching practice using `Caffeine`. Similar to the dependencies
responsible for RBAC, the related configurations are relegated to a dedicated module;
This way, you can replace one _provider_ with another by simply modifying the `pom.xml` of the
`application` module, without touching the code itself.

## Changelog

Thanks to the adoption of the [Conventional Commits](https://www.conventionalcommits.org/en/v1.0.0/) standard, it is possible to
autogenerate an application changelog.
There are numerous tools that allow this; this application is designed to use [
`node-autochglog`](https://github.com/BudWhiteStudying/node-autochglog):

```bash
npx node-autochglog
```

This tool can be configured via the [`node-autochglog.config.json`](./node-autochglog.config.json) file.
