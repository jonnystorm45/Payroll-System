# Payroll Management System

Secure Java-based user registration with hashed passwords stored in MySQL.

## Setup
1. Create database and table (SecurityModuleDB.users).
2. Place `mysql-connector-java-8.0.33.jar` in `lib/`.
3. Compile:
   javac -cp "lib/*" -d out src/*.java

4. Run:
   java -cp "lib/*;out" Main

## Running Tests
javac -cp "lib/*" -d out src/*.java test/*.java
java -jar lib/junit-platform-console-standalone-1.10.0.jar --class-path out --scan-class-path