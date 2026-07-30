# Pair Programming: JDBC & DAO Pattern

**Repository:** [JDBC_DAO Project](https://github.com/snsorl/JDBC_DAO/tree/main)

## Overview & Team Roles
This project demonstrates the DAO (Data Access Object) design pattern and JDBC integration. The task was completed collaboratively using pair programming techniques.

* **Trainee A (Surya):** Primary contributor for core data access logic (`save`, `getById`, `getAll`).
* **Trainee B (Aishwarya):** Primary contributor for modification and deletion logic (`update`, `delete`).

---

## File Structure & Contributions

| File | Primary Contributors              | Responsibilities / Notes                                                            |
| :--- |:----------------------------------|:------------------------------------------------------------------------------------|
| `ConnectionFactory.java` | **Surya & Aishwarya** (Jointly)   | Reused and adapted connection logic from the `Products` example.                    |
| `BookDAO.java` | **Code Given To Us**             | Directly used the code from GitHub for the interface.                               |
| `BookDAOImpl.java` | **Surya & Aishwarya (Jointly)** | Concrete implementation using JDBC (Split duties below).                            |
| `Book.java` | **Code Given To Us (Surya)**                        | Directly used the code from GitHub and Intellij's inBuilt generate methods feature. |
| `Main.java` | **Surya & Aishwarya** (Jointly)   | Driver application testing and executing all DAO operations.                        |

---

## Method Breakdown in `BookDAOImpl.java`

### **Surya's Contributions**
* `save()` — Handles insertion of new `Book` records into the database.
* `getById()` — Fetches a specific `Book` entity by its unique identifier.
* `getAll()` — Retrieves all `Book` entries from the database into a collection.

### **Aishwarya's Contributions**
* `update()` — Modifies existing `Book` records in the database.
* `delete()` — Removes a `Book` record by ID.

---

## How to Run
1. Ensure your database connection settings are configured in the `.env` file.
2. Run `Main.java` to execute the database operations and test CRUD capabilities.