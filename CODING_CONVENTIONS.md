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

The project documents (`README` files, etc.) are written in English.

The project code, configurations, and all other content are written in English.

Commit messages will be written in English, while squashed commit messages introduced
by MRs in the `develop` branch will be in Italian, thus determining the generation of release notes in
Italian.