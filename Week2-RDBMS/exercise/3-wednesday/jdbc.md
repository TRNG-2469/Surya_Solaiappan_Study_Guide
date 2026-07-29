# JDBC Types

## Type 1: JDBC-ODBC Bridge Driver
Translates JDBC calls into ODBC calls via an ODBC driver installed on the machine.

ODBC (Open Database Connectivity) is a universal programming standard that lets software applications talk to different database systems, using a driver manager, database drivers, and data source names (DSNs).

## Type 2: Native-API Driver
Converts JDBC calls into database-specific native client API calls using C/C++ libraries.

## Type 3: Network Protocol Driver
The Java client communicates with a middleware server, which then talks to the database.

## Type 4: Pure Java / Thin Driver
Written entirely in Java and connects directly to the database via socket communication.