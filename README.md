# Payroll System

## Overview

A simple Java payroll system with MySQL database backend, user login with hashed passwords (SHA-256 or MD5), employee management, and payroll calculation.

## Setup

1. Install MySQL and create a database using `setup.sql`.
2. Update database credentials in `DatabaseManager.java`.
3. Add the MySQL Connector/J library (`mysql-connector-java-x.x.x.jar`) to your classpath.
4. Compile Java files:  
   `javac -cp "lib/mysql-connector-java-x.x.x.jar" -d out src/*.java`
5. Run the program:  
   `java -cp "lib/mysql-connector-java-x.x.x.jar;out" Main`

## Usage

- Select hash algorithm on login screen.
- Enter valid username and password.
- Manage employees (add/view).
- Calculate payroll (gross, tax, net pay).

## Notes

- Passwords are hashed in the database.
- Sample data included in SQL script.
- Modify as needed for PTO, overtime, and reporting.

---

