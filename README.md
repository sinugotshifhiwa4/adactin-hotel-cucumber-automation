# Adactin Hotel Test Automation Framework

## Overview

The **Adactin Hotel Automation Framework** is a **Java-based Selenium + Cucumber (BDD)** test automation solution
designed to validate end-to-end user workflows of the Adactin Hotel application.

The framework follows a clean and scalable architecture using:

* **Page Object Model (POM)**
* **Cucumber BDD (Gherkin)**
* **JUnit 5**
* **Maven build management**
* **Environment-driven configuration**

It supports execution across multiple environments and browsers, and is structured for both **local execution** and
**CI/CD integration**.

---

## Tech Stack

* **Language:** Java 21
* **Automation:** Selenium WebDriver
* **BDD:** Cucumber
* **Test Runner:** JUnit 5
* **Build Tool:** Maven
* **Logging:** Log4j2
* **Config Management:** dotenv-java
* **Reporting:** Allure + Cucumber HTML/JSON

---

## Project Structure

```
.
├── envs/                     # Environment configuration files (.env)
│   ├── .env.dev
│   ├── .env.example
│
├── src/
│   ├── main/java/com/tshifhiwa/
│   │   ├── configuration/
│   │   │   ├── driver/       # Driver setup & management
│   │   │   ├── environment/  # Env detection & resolution
│   │   │   ├── logger/       # Logging utilities
│   │   │   ├── sanitization/ # Data sanitization
│   │   │
│   │   ├── pages/
│   │   │   ├── base/         # BasePage (common actions)
│   │   │   ├── adactinHotel/ # Application-specific pages
│   │   │
│   │   ├── context/          # TestContext (shared state)
│   │
│   ├── test/java/com/tshifhiwa/
│   │   ├── hooks/            # Cucumber hooks
│   │   ├── runner/           # TestRunner
│   │   ├── stepdefinitions/  # Step definitions
│   │
│   ├── test/resources/
│       ├── features/         # Gherkin feature files
│
├── target/
│   ├── allure-results/       # Raw Allure results
│   ├── site/
│   │   └── allure-maven-plugin/  # Generated Allure report
│   ├── cucumber-reports/     # HTML & JSON Cucumber reports
│
└── pom.xml
```

---

## Getting Started

### 1. Clone the repository

```bash
git clone <your-repo-url>
cd adactin-hotel-cucumber-automation
```

---

### 2. Install dependencies

```bash
mvn clean install
```

---

### 3. Setup Environment Configuration

Environment variables are stored in the `envs/` folder.

#### Create environment file:

```bash
cp envs/.env.example envs/.env.dev
```

Then update values inside `.env.dev`.

---

## Test Execution

### Basic Command

```bash
mvn clean verify -Pgeneral-user
```

---

### Run with Parameters

```bash
mvn clean verify -Pgeneral-user -Dtags=@<tag> -DENV=<env> -Dbrowser=<browser> -DforkCount=<count>
```

---

## Supported Configurations

### Environments

* `dev`
* `qa`
* `uat`
* `preprod`
* `prod`

---

### Tags

* `@sanity`
* `@regression`

---

### Browsers

* `chrome`
* `edge`
* `firefox`

---

### Example Runs

```bash
# Run sanity tests on QA in Chrome
mvn clean verify -Pgeneral-user -Dtags="@sanity" -DENV=qa -Dbrowser=chrome

# Run regression tests on UAT in Firefox
mvn clean verify -Pgeneral-user -Dtags="@regression" -DENV=uat -Dbrowser=firefox

# Run regression tests on preprod in Edge
mvn clean verify -Pgeneral-user -Dtags="@regression" -DENV=preprod -Dbrowser=edge

# Run regression tests with 4 parallel forks
mvn clean verify -Pgeneral-user -Dtags="@regression" -DENV=dev -Dbrowser=firefox -DforkCount=4

# Run sequentially (useful for debugging)
mvn clean verify -Pgeneral-user -Dtags="@regression" -DENV=dev -Dbrowser=chrome -DforkCount=1
```

---

## Reporting

### Allure Report (Recommended)

Allure results are written to `target/allure-results` during test execution.

#### Generate report (local)

```bash
mvn allure:report -Pgeneral-user
```

Report is generated to `target/site/allure-maven-plugin/index.html`.

#### Serve report in browser (local)

```bash
mvn allure:serve -Pgeneral-user
```

This starts a local server and opens the report automatically in your browser.

> **Note:** `allure:serve` is for local use only. Do not use it in CI pipelines.

---

### Cucumber Reports

HTML and JSON reports are also generated under:

```
target/cucumber-reports/index.html
target/cucumber-reports/cucumber.json
```

---

### CI/CD Reporting

In CI pipelines, only `mvn clean verify -Pgeneral-user` is needed. The raw Allure results in
`target/allure-results` are picked up by the CI platform directly:

* **GitHub Actions** — Allure report is automatically published to GitHub Pages after every run:
  👉 **https://sinugotshifhiwa4.github.io/adactin-hotel-cucumber-automation**
* **Jenkins** — use the Allure Jenkins Plugin pointing to `target/allure-results`
* **GitLab CI** — publish `target/allure-results` as a pipeline artifact

---

## Framework Features

### Environment Management

* Dynamic environment resolution via `ENV` system property
* `.env`-based configuration
* Centralized environment handling (`EnvironmentManager`, `EnvironmentResolver`)

---

### Driver Management

* Centralized WebDriver setup using:

    * `DriverFactory`
    * `BrowserManager`
    * `DriverInitializer`
* Supports multiple browsers
* Scalable for remote/grid execution

---

### Page Object Model (POM)

* `BasePage` for reusable Selenium actions
* Page-specific classes (e.g., `LoginPage`)
* Clean separation of test logic and UI interaction

---

### Cucumber BDD

* Feature files written in **Gherkin**
* Step definitions mapped cleanly
* Hooks for setup and teardown

---

### Test Context

* Shared data across steps using `TestContext`
* Avoids tight coupling between steps

---

### Logging

* Centralized logging using **Log4j2**
* Configurable logging levels
* Clean test execution logs

---

## Maven Configuration

Key runtime properties (from `pom.xml`):

| Property    | Description                          | Default |
|-------------|--------------------------------------|---------|
| `env`       | Target environment                   | `dev`   |
| `browser`   | Browser to run tests                 | `chrome`|
| `tags`      | Cucumber tag filter                  | `@sanity`|
| `forkCount` | Parallel fork count (Surefire forks) | `1`     |

### Maven Profiles

| Profile        | Description                          | Active By Default |
|----------------|--------------------------------------|-------------------|
| `general-user` | Default profile for local and CI use | Yes               |

#### `general-user` Profile

This is the only profile and is active by default. It configures:

* **maven-surefire-plugin** — runs `TestRunner.java` with the following system properties:
    * `ENV` — target environment
    * `browser` — browser to use
    * `cucumber.filter.tags` — tag filter
    * `allure.results.directory` — writes Allure results to `target/allure-results`
    * `java.util.logging.manager` — routes JUL logging through Log4j2
* **allure-maven plugin** — generates the Allure report from `target/allure-results`

Always activate it explicitly:

```bash
-Pgeneral-user
```

---

## Hooks & Test Lifecycle

* **GlobalHooks** – suite-level setup
* **Hooks** – scenario-level setup/teardown
* Automatically handles:

    * Driver initialization
    * Cleanup after tests

---

## Best Practices

* Keep step definitions **thin**
* Place all UI logic inside **Page Objects**
* Use **TestContext** for shared state
* Avoid hardcoding test data → use environment/config
* Tag tests properly (`@sanity`, `@regression`)
* Always run with `-Pgeneral-user` profile

---

## CI/CD Ready

The framework is designed to integrate easily with:

* Jenkins
* GitHub Actions
* GitLab CI

### GitHub Actions

The workflow supports manual triggers via `workflow_dispatch` with the following inputs:

| Input       | Description          | Default      | Options                              |
|-------------|----------------------|--------------|--------------------------------------|
| `env`       | Target environment   | `dev`        | dev, qa, uat, preprod, prod          |
| `browser`   | Browser              | `chrome`     | chrome, firefox, edge                |
| `tags`      | Cucumber tags        | `@regression`| @regression, @sanity                 |
| `forkCount` | Parallel fork count  | `2`          | 1, 2, 4, 8                           |

Push and pull request triggers use these defaults automatically.

Example CI command:

```bash
mvn clean verify -Pgeneral-user -DENV=qa -Dtags="@regression" -Dbrowser=chrome -DforkCount=2
```

### Allure Report (GitHub Pages)

The Allure report is automatically published after every CI run:

👉 **https://sinugotshifhiwa4.github.io/adactin-hotel-cucumber-automation**

---

## Troubleshooting

### Driver Issues

* Ensure browser is installed
* Match WebDriver version with browser

---

### Environment Not Loading

* Check `.env.<env>` exists in the `envs/` folder
* Ensure correct `ENV` is passed

---

### Tests Not Running

* Verify tags are correct:

```bash
-Dtags="@sanity"
```

* Ensure the `-Pgeneral-user` profile is included

---

### Allure Results Not Found

* Ensure `allure.results.directory` system property is set in Surefire config in `pom.xml`
* Check that `target/allure-results` exists after running `mvn clean verify`

---

## Future Enhancements

* Selenium Grid integration
* Parallel execution improvements
* API testing integration

---

## Author

**Tshifhiwa Sinugo**

---