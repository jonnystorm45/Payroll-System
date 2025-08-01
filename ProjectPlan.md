# Project Plan

## Goals

- Build a secure, testable, modular payroll system
- Support basic login and payroll calculation
- Allow test data and extensibility

## Design Decisions

- Java used with MySQL backend
- Modular class design (`Employee`, `PayrollCalculator`)
- Strategy pattern for hashers (planned)
- CLI-based interface for simplicity

## Algorithm Notes

- Gross = hours × rate
- Tax = 20%
- Net = gross - tax