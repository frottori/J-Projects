## Folder Structure

The workspaces for `Client_localhost` and `Server` contain two folders by default, where:

- `src`: the folder to maintain sources
- `lib`: the folder to maintain dependencies

Meanwhile, the compiled output files will be generated in the `bin` folder by default.

## SQL Script
The `Server` workspace has a `BankLoginSystem.sql` that makes a local database with some default accounts. You need to run it for the database to work in the PC you want to have as a server.

## .JAR File
The `Client.jar` runs the Application and has access to the database only when the Server is running
