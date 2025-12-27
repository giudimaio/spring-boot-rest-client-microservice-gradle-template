# Conventions

This document collects the conventions used in developing the code for this project.

## Development Flow

The proposed development flow is a simplification
of [GitFlow](https://www.atlassian.com/git/tutorials/comparing-workflows/gitflow-workflow). In short:

- the repository's default branch is the `develop` branch.
- unless otherwise noted, no one commits/pushes directly to `develop`.
- development tasks are collected as issues on the GitHub repository.
- whenever a developer has no tasks, they can simply consult the list of issues and
select one that isn't already assigned to someone else and has the `development` label (giving priority
to those with the `high priority` label and avoiding those with the `blocked` label).
- once an issue is selected, the developer assigns it to themselves, then creates a new branch from the
`develop` branch. The naming convention for creating branches is `<prefix>/<issue-number>`:
- `<prefix>` is a term that indicates the type of task, following the same conventions dictated by
[Conventional Commits](https://www.conventionalcommits.org/en/v1.0.0/) (e.g., if the task consists of the development
of a new feature, the prefix will be `feat/`; if it is a bug fix, it will be `fix/`)
- `issue-number` is the identification number of the issue chosen by the developer, without the `#` character.
- Some examples: `feat/12`, `fix/14`, `refactor/44`
- The developer is encouraged to perform frequent commits on their newly created branch. This will allow, if necessary,
to easily analyze what has been done while solving the task, because every small change
will correspond to a commit (and a related message).
- Similarly, the developer is encouraged to perform frequent _merges_ from `develop` to their _branch_, so as to
keep it up to date and simplify the _merge_ activities once the tasks are completed.
- Once the developer believes they have completed the activity described in the _Issue_, they can open a _Pull
Request_, which will then be reviewed and possibly approved, determining the _merge_ of the developer's _branch_
into the _develop_ _branch.
- To prepare a new release, a _release_ _branch_ is created (each time) starting from the _develop_ _branch.
This branch is used by CI/CD processes for installation in pre-production environments.
- If any fixes emerge during pre-production testing, they will be applied directly to the `release` branch. In other words, once the `release` branch is generated, there's no going back; the `release` branch is "hammered" until it passes the tests (obviously, this means that before generating the
`release` branch, you must be reasonably certain that the code is ready to pass the tests).
- Once the pre-production tests have passed, the `release` branch is merged into the `main` branch at the same time as the production release. The latter must always be a faithful snapshot of the status of the code in the production environment.
- Once the transition to production is complete, the `release` branch is deleted (it will be recreated in the next release) and
the `main` branch is merged into the `develop` branch (so as to realign `develop` with any corrections made during pre-production testing).

There is also a dedicated process for urgent fixes:

- a `hotfix` branch is created from the `main` branch.
- the developer responsible for the urgent fix (which will still be represented by a GitHub Issue, marked with the `development`, `high priority`, and above all `hotfix` labels) creates a new branch, with the same conventions described
for the normal process, but starting from the `hotfix` branch instead of the `hotfix` branch. _branch_ `develop`**
- the developer then implements what is needed on their own _branch_ thus created; once ready for
publication, the developer opens a _Pull Request_ according to the same conventions described for the
normal process, but opens it towards the _branch_ `hotfix`.
- the contents of the _branch_ `hotfix` are used for pre-production testing; Similar to what happens
in the normal process, any fixes from now on are performed directly on the `hotfix` branch.

Once the pre-production tests have passed, the `hotfix` branch is merged into the `main` branch at the same time as the release to production.

Once the release to production is complete, the `hotfix` branch is deleted (it will be recreated in the next release) and
the `main` branch is merged into the `develop` branch (so as to realign `develop` with any
fixes made during pre-production testing).

There is also a dedicated process for urgent fixes:

- a `hotfix` branch is created from the `main` branch.
- the developer responsible for the urgent fix (which will still be represented by a GitHub Issue, marked with the `development`, `high priority`, and above all `hotfix` labels) creates a new branch, following the same conventions described for the normal process, but starting from the `hotfix` branch instead of the `develop` branch.
- the developer then implements what is needed on their newly created branch. Once ready for publication, the developer opens a Pull Request following the same conventions described for the normal process, but opening it towards the `hotfix` branch.
- the contents of the `hotfix` branch are used for pre-production testing. Similar to what happens
in the normal process, any fixes from now on are made directly on the `hotfix` branch.

Once the pre-production tests have passed, the `hotfix` branch is merged into the `main` branch at the same time as the release to production.

Once the release to production is complete, the `hotfix` branch is deleted (it will be recreated in the next release) and
the `main` branch is merged into the `develop` branch (so as to realign `develop` with any
fixes made during pre-production testing).

## Language

The project documents (`README` files, etc.) are written in Italian, as the likelihood of them
being consulted by non-Italian readers is very low.

The project code, configurations, and all other content are written in English.

A common [glossary](./GLOSSARY.md) is followed within the project code to avoid referring to the same entity in different ways. For example, the expression "Richiesta di assistenza" has been translated as
"Support Request"; all references to this expression should therefore use the term
"Support Request" and not a synonym, such as "Assistance Request". When a developer encounters a new term,
they update the glossary with the chosen translation for that new term.

Commit messages will be written in English, while squashed commit messages introduced
by MRs in the `develop` branch will be in Italian, thus determining the generation of release notes in
Italian.

## Pattern

The application observes a recurring pattern for exposing operations via web services:

- the entities managed by the application are defined in the package
`com.corp.it.sample.message.consumer.model.entity`
- the operations to be exposed to web clients are defined in the package
`com.corp.it.sample.message.consumer.controller`, within
classes that conform to the following specifications (see the class [
`DummyController`](./src/main/java/it/poste/pv/be/form/mail/controller/DummyController.java) as an example):
- a dedicated class is defined for each exposed REST resource
- the class name coincides with that of the REST resource in the singular, appended with the
`Controller` suffix; For example, the class containing the operations related to exposing the resource
`/employees` will be called `EmployeeController`
- The class is annotated with the annotations `@RestController`, `@RequestMapping`, and `@RequiredArgsConstructor`
- The `@RequestMapping` annotation is passed as its only parameter the name of the _base path_ of the exposed resource,
e.g., `@RequestMapping("/employee")`
- However, the name of the resources is never defined via static strings within the controller classes;
it is instead defined in the dedicated `ApiPaths` class, in the
`com.corp.it.sample.message.consumer.model.framework` package; thus, the annotation becomes, e.g.,
`@RequestMapping(ApiPaths.EmployeesController.BASE_PATH)`
- The `ApiPaths` class therefore defines the names of all exposed resources in a single place, through dedicated
static subclasses; any values ​​potentially reusable by multiple controller classes are instead
defined in the static subclass `ApiPaths.Common`.
- The individual operations exposed at the resource are defined using the `@GetMapping`,
`@PostMapping`, `@PutMapping`, etc. annotations; These annotations are passed as an input parameter the name of the exposed resource,
if present, or the constant `ApiPaths.Common.EMPTY_PATH` if the operation exposes the resource present
at the _base path_
- the class's responsibility is solely to expose the operations; the class's methods delegate
any business logic to the related _service_ class.
- the _service_ class is instantiated via a `private final` variable; Lombok then defines the constructor that allows instantiation (using
the `@RequiredArgsConstructor` annotation).
- The business logic executed when REST operations are invoked is implemented in the
`com.corp.it.sample.message.consumer.application.service` package, within classes that conform to the
following specifications (see the `DummyService` class in the `application` module as an example):
- A dedicated class is defined for each exposed REST resource.
- The class name coincides with that of the REST resource in the singular, to which the
`Service` suffix is ​​added. For example, the class containing the business logic for operations related
to exposing the `/employees` resource will be called `EmployeeService`.

The class is annotated with the `@Service` annotation and, if other classes need to be instantiated (e.g.,
for database access), `@RequiredArgsConstructor`.

The class is responsible for providing the main implementation of the REST operations
defined in the controller classes. Any specialized logic (e.g., to access the database, invoke external services,
etc.) is delegated to specific classes, which are instantiated via the `private final` property.
- The database access logic is implemented in the
`com.corp.it.sample.message.consumer.application.repository` package, within interfaces that conform
to the following specifications (see the `DummyRepository` class for an example):
- For each JPA entity defined in the `com.corp.it.sample.message.consumer.model.entity` package of the
`model` module, a dedicated interface is defined.
- The interface name coincides with that of the entity, appended with the suffix `Repository`; for
example, the interface that allows access to the table defined by the `Dummy` entity will be called
`DummyRepository`.
- The interface extends the `JpaRepository<K,V>` interface. If possible, it's a good idea to gather common query methods
into "base" interfaces and extend them; for example, see the
`BaseUuidRepository` interface, which can be used for all repository interfaces whose entities require the presence
of a `String uuid` column.

The interface is not annotated with any annotation; it is therefore not necessary to use the
`@Component`, `@Repository`, or any other annotation.

Queries defined within the interface should use the
[JPA Query Methods](https://docs.spring.io/spring-data/jpa/reference/jpa/query-methods.html) feature as much as possible. The use
of explicit queries using the `@Query` annotation should be avoided except in extreme cases.

If a query needs to return only some of the table's columns, the recommendation
is to use the "projections" technique. For example, see the
`BaseUuidRepository` interface, where the projection
`IdProjection` is used to return only the `id` column.
- The DTOs (Data Transfer Objects) used by the application are defined in the
`com.corp.it.sample.message.consumer.model.dto` package of the `model` module, and are divided into two additional packages:
- `web`: These DTOs are used for integration via web interfaces; for example, this package contains the requests and responses of the application's REST controllers.
- `persistence`: These DTOs are used for database integration; for example, this package contains the projections that allow you to limit the output of a query to only certain columns in the table.

## Tooling

You need an IDE that allows you to configure `CheckStyle` as a code formatting tool.
This is typically done via a plugin. The plugin must in turn be configured to perform
code formatting based on the `checkstyle.xml` file.

For example, in IntelliJ Idea, configuration requires the following steps:

- Install the `Checkstyle-Idea` plugin
- Navigate to the `Settings|Editor|Code Style` menu in IntelliJ settings
- Select the `Project` option from the _Scheme_ dropdown
- Click the _Gear_ icon, then select `Import Scheme|Checkstyle configuration`, and finally select the
`checkstyle.xml` file (from the root of these repositories) as the reference file from which to import the
configuration.

Similarly, it is necessary to use an IDE that allows you to easily apply formatting rules
to the entire codebase, to avoid having to correct the style file by file. Once again, IntelliJ provides
a virtuous example, allowing you to reformat code into an entire module or even format it pre-commit
(more information [here](https://www.jetbrains.com/help/idea/reformat-and-rearrange-code.html)).

:warning: **Warning: It is essential to exclude the `sample-message-consumer-feign-api` module from the scope of application
of the style rules.**
This module is in fact auto-generated at each build, and the generated files do not necessarily respect the parameters
contained in the template defined in the `checkstyle.xml` file; If you don't exclude it from the scope of application of the rules,
each PR risks carrying dozens (or even hundreds) of modified files each time, that is,
the generated files to which formatting is applied/reset each time.

In IntelliJ, the simplest way to exclude the module is to mark it as 'Excluded':

![Exclusion Example](./docs/img/IJ-mark-directory-as.png)

Make sure all the folders within it are also excluded.
