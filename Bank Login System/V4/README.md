## Folder Structure

- `banklogin`: Maven Project

The file structure of a Maven web application (webapp) typically follows the following structure

- `src`: Contains the source code and resources for the project.
- `main`: Main source directory for application code.
- `java`: Java source files for the main application (Servlets)
- `webapp`: Contains web application resources.
- `WEB-INF`: Web application configuration directory.
- `target`:  a standard directory in Maven projects where all the build output and intermediate files are placed

## Useful Files

 - `web.xml`: Deployment descriptor for configuring servlets, filters, etc.
 - `pom.xml`: Project Object Model (POM) file that defines the project configuration, dependencies, and build settings for Maven.
   - Dependencies for this project:
     - `jakarta.servlet-api`
     - `mysql-connector-java`

## SQL Script
`BankLoginSystem.sql` makes a local database with some default accounts. You need to run it for the database to work in the PC you want to have as a server.