# Enterprise QA Automation Framework

A hybrid, data-driven automation framework designed to execute end-to-end user journeys, backend API interactions, and direct database validations.

## 🛠 Tech Stack
* **Language:** Java 26
* **UI Automation:** Selenium WebDriver (Page Object Model)
* **BDD Engine:** Cucumber
* **Execution & Assertions:** TestNG
* **API Testing:** RestAssured
* **Data Handling:** Apache POI (Excel)
* **Database:** JDBC (H2 In-Memory for demonstration)
* **Build Tool:** Gradle
* **Reporting:** ExtentReports

## 🏗 Architecture Highlights
* **Page Object Model (POM):** Clean separation of web locators and test logic. Elements are strictly encapsulated.
* **Smart Synchronization:** A custom `BasePage` handles explicit waits (`WebDriverWait`), eliminating the need for `Thread.sleep()`.
* **Data-Driven:** Leverages TestNG `@DataProvider` and Apache POI to run test iterations from external `.xlsx` files.
* **State Sharing:** API and Database step definitions share context smoothly through POJOs (Plain Old Java Objects).
* **Parallel Execution:** Configured at the `testng.xml` level and Cucumber runner level for concurrent thread execution.

## 🚀 How to Run the Tests

To execute the full suite (UI, API, and DB tests in parallel), run the following command from the project root:

```
./gradlew clean test
```

## 📊 Test Reporting

This framework uses **ExtentReports** to generate rich, interactive HTML dashboards.
* A custom `TestListener` intercepts test results.
* Screenshots are automatically captured and embedded as Base64 strings on UI test failures.
* After execution, the report is located at: `target/ExtentReport.html`. Open it in any browser.

## 👤 Author
**Naveen Kumar Rana**