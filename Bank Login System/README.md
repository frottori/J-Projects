# Bank Login System

### V1 :

A simple Bank Login System with pre-existing UserAccounts and the ability to add new ones while the program is running, you can deposit or withdraw money from each account. If you withdraw an amount bigger than the account's balance an error message will pop out asking if you're sure you want to be in debt.
There is also a password hide icon that shows, if selected, the password field.

The main function is contained on the LoginPage.java .

### V2 :
Updated UserAccounts that uses MySQL Database (local host) to hold the Account Information for each User so that everytime the program is executed the changes to the Accounts are saved and also if a new user is created. 

## V3 :
Access to the database even remotely using TCP Sockets where a server has a local connection to the database and the Client accesses the information via the Server (here it is localhost but you can configure it over the internet)

## V4:
A website of the BankLoginSystem with access to the accounts database. This is done with Maven WebApp project and the deployment is done locally using TomCat Server. You can deploy it locally using the .war file and you also need the database to your system.