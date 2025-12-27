# spring-boot-rest-client-microservice-gradle-template

This project serves as a template for Gradle-based Spring Boot backend applications.
It allows you to quickly create new projects with a modular structure and ready-to-use configurations.

---

## Template Structure

The template is organized into multiple modules; it contains:

### `application` Module

- Contains the Spring Boot application core.
- Typical structure:

```
APP_NAME-application/
├─ src/main/java/com/CORP_NAME/it/APP_NAME/application/
│ └─ Application.java # Spring Boot main class
├─ src/main/resources/
│ └─ application.yml # Spring configurations
├─ src/test/java/...
└─ build.gradle.kts # Gradle configurations for the module
```

- **Main classes**:
- `Application.java`: main class annotated with `@SpringBootApplication`.
- `com.__CORP_NAME__.it.__APP_NAME__.application` directory, replaced with the names of the corporation and application.

---

## Generating a new project

To create a new project based on this template, use the `generate.py` script in the `tools/` folder.

## Project Generation Script

The `generate.py` script allows you to create a new Spring Boot project from the template, replacing placeholders and renaming folders.

---

### Options

| Option | Required | Description |
|-----------------|-------------|--------------------------------------------------------------------------|
| `--program-name` | Yes | Project name/program |
| `--app-name` | Yes | Application name |
| `--corp-name` | Yes | Corporation name |
| `--out-dir` | No | Destination folder for the generated project (default: `./out`) |
| `--force` | No | Overwrites any existing folders |

---

### How the script works

1. **Copy the template:**
Copies the entire template content to a **temporary folder**, avoiding modifying the original template.

2. **Rename directories:**
All folders containing the placeholders `__CORP_NAME__` and `__APP_NAME__` are renamed, even if they are part of longer filenames (e.g., `__APP_NAME__-application` → `viaggiatreno-application`).

3. **Replace placeholders in files:**
In files with the extensions `.java`, `.kt`, `.gradle`, `.yml`, `.yaml`, and `.properties`, the contents of `__CORP_NAME__` and `__APP_NAME__` are replaced with the provided values.

4. **Removing the generation script:**
The `generate.py` script in the `tools/` folder is removed from the generated project.

5. **Creating the final folder:**
The temporary folder is moved to the final destination with the name:

### Example execution

```bash
python3 tools/generate.py \
--program-name my-program \
--app-name my-app \
--corp-name my-corp \
--out-dir my-out-dir
```

