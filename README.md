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

It supports execution across multiple environments and browsers, and is structured for both **local execution** and *
*CI/CD integration**.

---

## Tech Stack

* **Language:** Java 21
* **Automation:** Selenium WebDriver
* **BDD:** Cucumber
* **Test Runner:** JUnit 5
* **Build Tool:** Maven
* **Logging:** Log4j2
* **Config Management:** dotenv-java

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
│   ├── cucumber-reports/     # HTML & JSON reports
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
mvn test
```

---

### Run with Parameters

```bash
mvn test -Dtags=@<tag> -DENV=<env> -Dbrowser=<browser>
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
mvn test -Dtags="@sanity" -DENV=qa -Dbrowser=chrome

# Run regression tests on UAT in Firefox
mvn test -Dtags="@regression" -DENV=uat -Dbrowser=firefox

# Run regression tests on preprod in Edge
mvn test -Dtags="@regression" -DENV=preprod -Dbrowser=edge
```

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

### Reporting

* HTML reports generated under:

```
target/cucumber-reports/index.html
```

* JSON reports for integrations:

```
target/cucumber-reports/index.json
```

---

## Maven Configuration

Key runtime properties (from `pom.xml`):

| Property    | Description                |
|-------------|----------------------------|
| `env`       | Target environment         |
| `browser`   | Browser to run tests       |
| `tags`      | Cucumber tag filter        |
| `forkCount` | Parallel execution control |

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

---

## CI/CD Ready

The framework is designed to integrate easily with:

* Jenkins
* GitHub Actions
* GitLab CI

Example:

```bash
mvn clean test -DENV=qa -Dtags="@regression" -Dbrowser=chrome
```

---

## Troubleshooting

### Driver Issues

* Ensure browser is installed
* Match WebDriver version with browser

---

### Environment Not Loading

* Check `.env.<env>` exists
* Ensure correct `ENV` is passed

---

### Tests Not Running

* Verify tags:

```bash
-Dtags="@sanity"
```

---

## Future Enhancements

* Selenium Grid integration
* Parallel execution improvements
* API testing integration
* Advanced reporting (Allure)

---

## Author

**Tshifhiwa Sinugo**

---
