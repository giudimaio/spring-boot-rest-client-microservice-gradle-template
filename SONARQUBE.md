## Prerequisites

Docker installed and its daemon running.

## Installation

Run the following commands to install and start SonarQube:

```bash
docker volume create sonarqube_data

docker run \
--name sonarqube-local \
-p 9000:9000 \
-v sonarqube_data:/opt/sonarqube/data \
sonarqube:latest
```

## Initial Setup

Access the SonarQube web interface at http://localhost:9000/ via a web browser.

The initial login credentials are `admin`/`admin`, but you will be asked to change your password the first time you log in.
Choose a password of your choice and write it down; you will obviously need it for all subsequent logins.

Once you've updated your password and closed any welcome pop-ups, the new project creation page will open
(http://localhost:9000/projects/create).
Once you reach this page, perform the following actions:

1. Select **_Create a local project_** (last option, after all others)
2. Enter a value in the _Project display name_ input, for example, `smart-console-be-buoni-sconto`
3. Enter a value in the _Project key_ input, for example, `smart-console-be-buoni-sconto` (can be the same as the
_Project display name_)
4. Enter the value `develop` in the _Main branch name_ input, then press the **_Next_** button
5. On the next page, select **_Use the global setting_**, then press the **_Create project_** button
6. On the next page, select the **_Locally_** option (last option, after all others)
7. On the next page, enter a value in the _Token name_ input, for example, `smart-console-be-buoni-sconto` (can
be the same as the _Project display name_)
8. From the _Expires in_ dropdown, select the **_No expiration_** option (this would be a _very bad security practice_ in a real CI/CD environment, but since we're configuring a local instance, it's fine).
9. Press the **_Generate_** button to the right of the dropdown.
10. Make note of the generated token (**you won't be able to see it anymore!**), then press the *
*_Continue_** button.
11. In the new section that appears below the token section, select the **_Maven_** option, then copy the code snippet that appears.
12. Run the snippet in the root of this application's project.

From now on, you can run the SonarQube scan as often as you like, using the same snippet (
it's recommended to develop a small script so you don't have to copy and paste the snippet every time).

## Subsequent Uses

Thanks to the use of a persistent volume, even though it runs via Docker, this instance of
SonarQube is able to retain memory of the scans performed, even after stopping/restarting the container.

Simply restart the container, which was named `sonarqube-local` when
[creation](#installation):

```bash
docker start sonarqube

```
