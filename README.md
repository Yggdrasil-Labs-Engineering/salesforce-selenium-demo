# Salesforce Selenium Demo

## Overview

This project is a lightweight Java Selenium demonstration that validates authenticated navigation within Salesforce.

The purpose of the demo is to show how Selenium can be used to automate and validate a third-party web application while handling real-world concerns such as authentication, session trust, and application navigation.

## What This Demo Proves

This project demonstrates:

- Java-based Selenium automation
- authenticated Salesforce UI access
- browser session handling
- navigation to Salesforce Accounts
- explicit UI validation
- PASS / FAIL test execution with JUnit and Maven

## Technology

- Java 21
- Selenium WebDriver
- JUnit 5
- Maven
- Google Chrome
- Salesforce Developer Edition

## Setup

Clone the repository and ensure the following are installed:

- Java 21
- Maven
- Google Chrome

Set the required Salesforce credentials as environment variables.

Example in PowerShell:

```powershell
$env:SALESFORCE_USERNAME = "your-salesforce-username"
$env:SALESFORCE_PASSWORD = "your-salesforce-password"
```

Do not store credentials directly in the source code.

## Run the Test

From the project root:

```powershell
mvn test
```

A successful execution should complete with:

```text
PASS: Salesforce Accounts page loaded successfully.
BUILD SUCCESS
```

## Project Goal

This project is intended as a small proof-of-concept showing how Selenium can validate authenticated workflows in an external platform such as Salesforce.

More detailed engineering notes covering architecture, authentication strategy, design decisions, limitations, and workflow-testing relevance are maintained in the project Wiki.
