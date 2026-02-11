# 📚 ISBN Tools - Unit Testing with JUnit & Mockito

This project demonstrates how to implement **unit tests** in Java using **JUnit** and **Mockito**.  
It focuses on testing ISBN validation logic and stock management behavior with external dependencies mocked.

---

## 🚀 Project Overview

The project contains two main components:

### 1️⃣ ISBN Validation (`ValidateISBN`)
Validates ISBN numbers with the following rules:

- ✅ Valid 10-digit ISBN
- ✅ Valid 13-digit ISBN
- ✅ 10-digit ISBN ending with `X`
- ❌ Invalid ISBN numbers
- ❌ Non-numeric ISBN values
- ❌ ISBNs with incorrect length

Unit tests are written to verify all these scenarios.

---

### 2️⃣ Stock Management (`StockManager`)
The `StockManager` retrieves book information from:

- 🗄️ Database Service
- 🌐 External Web Service

Mockito is used to:

- Mock external services
- Stub method responses
- Verify interactions between components

The logic tested:

- If data exists in the database → use database service
- If not → fallback to web service
- Generate correct locator codes

---

## 🧪 Technologies Used

- Java
- JUnit 4
- Mockito
- Maven / Gradle (depending on setup)

---

## 🧩 Key Testing Concepts Demonstrated

### ✅ Unit Testing with JUnit
- `@Test`
- `@Before`
- `assertTrue`, `assertFalse`, `assertEquals`
- `expected` exceptions

### ✅ Mocking with Mockito
- `mock()`
- `when(...).thenReturn(...)`
- `verify()`
- `times()`
- `never()`
- `anyString()`

### ✅ Test Types
- Dummy objects
- Stubs
- Mocks
- Behavior verification
- Exception testing

---

## 🗂️ Example Test Scenarios

### ✔ ISBN Validation Tests
- Valid and invalid ISBN cases
- Edge cases (X ending, non-numeric input)
- Exception handling

### ✔ Stock Manager Tests
- Database priority logic
- Web service fallback logic
- Locator code generation
- Interaction verification with Mockito

---

## 🧠 Learning Goals

This repository is useful for understanding:

- How to write clean unit tests
- How to avoid tautological tests
- How to isolate dependencies using mocks
- Difference between stub, mock, and dummy objects
- How to test behavior instead of implementation

---

## 📌 Notes

This project is especially helpful for:

- Beginners in unit testing
- Java developers learning Mockito
- Understanding real-world test scenarios

---

## 👩‍💻 Author

Created for learning and practicing unit testing with Java, JUnit, and Mockito.

---

## ⭐ If you find this project useful, feel free to star the repository!
