Payroll System Project README
Project Overview
This Payroll System is a Java desktop application with a graphical user interface (GUI) built using Swing. It connects to a MySQL database to manage user authentication, employee records, and payroll calculations. The system supports secure login with selectable hashing algorithms (SHA-256 or MD5), employee data entry, and payroll reporting.

Features
User Login:
Secure login with password hashing (SHA-256 or MD5). Users authenticate against credentials stored in a MySQL database.

Employee Management:
Add employee records including name, hours worked, and hourly rate.

Payroll Calculation:
Calculates gross pay, tax, and net pay for all employees and displays a detailed payroll report.

Database Integration:
Stores user credentials and employee data in a MySQL database.

GUI:
User-friendly Swing interface for login and main payroll management.

Prerequisites
Java Development Kit (JDK) 17 or newer installed.

MySQL Server installed and running.

MySQL Connector/J JDBC driver (tested with version 9.4.0).

IDE or command-line tools for compiling and running Java applications.

Basic familiarity with MySQL command line or GUI (e.g., MySQL Workbench).

Setup Instructions
1. Clone or Download the Project
Download the source code to your local machine.

2. Set Up MySQL Database
Log into MySQL:

bash

mysql -u root -p
Create the database:

sql

CREATE DATABASE PayrollDB;
USE PayrollDB;
Create the users table:

sql

CREATE TABLE users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    password_hash VARCHAR(255) NOT NULL
);
Create the employees table:

sql

CREATE TABLE employees (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    hours_worked DOUBLE NOT NULL,
    hourly_rate DOUBLE NOT NULL
);
Insert a test user (replace yourhashedpassword with the actual hashed password for your chosen hash):

sql

INSERT INTO users (username, password_hash)
VALUES ('testuser', 'yourhashedpassword');
To generate a hashed password for password123 with SHA-256, you can use the Java hasher code or any online SHA-256 generator.

3. Configure DatabaseManager.java
Modify the database connection details in DatabaseManager.java:

java

conn = DriverManager.getConnection(
    "jdbc:mysql://localhost:3306/PayrollDB?useSSL=false&serverTimezone=UTC",
    "root",
    "your_mysql_password"
);
Replace "your_mysql_password" with your actual MySQL root password.

4. Compile and Run the Application
Compile the project:

bash

javac -cp "lib/mysql-connector-j-9.4.0.jar" -d out src/*.java
Run the application:

bash

java -cp "lib/mysql-connector-j-9.4.0.jar;out" Main
Usage
Login Screen:
Select hash algorithm (SHA-256 or MD5), then enter username and password.

Main Screen:
Add employees by entering name, hours worked, and hourly rate. Click "Add Employee" to save.

Payroll Calculation:
Click "Calculate Payroll" to view a report of gross pay, taxes, and net pay for all employees.

Project Structure
src/: Java source code files.

lib/: MySQL JDBC driver jar file.

out/: Output folder for compiled classes.

README.docx: This documentation file.

Important Notes
Passwords are stored hashed in the database; plain text passwords are never saved.

The payroll tax calculation is a simple flat rate (you may customize PayrollCalculator class).

Error handling is basic; improve for production use.

GUI is implemented with Swing and is basic but functional.

Future Improvements
Add user roles and session management.

Implement employee editing and deletion.

Add more detailed payroll rules (overtime, PTO, deductions).

Improve GUI with better layout and input validation.

Export payroll reports to files (CSV, PDF).

Add logging and unit tests.

Contact
For any questions or issues, please contact:
Storm Anderson 
Email: janderson322@ivytech.edu