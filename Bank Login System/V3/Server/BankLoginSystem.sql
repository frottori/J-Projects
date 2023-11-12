DROP DATABASE IF EXISTS BankLoginSystem;
CREATE DATABASE BankLoginSystem;
USE BankLoginSystem;

CREATE TABLE INFO (
    USERNAME VARCHAR(50),
    FIRSTNAME VARCHAR(50),
    LASTNAME VARCHAR(50),
    PASSWORD VARCHAR(50),
    BALANCE FLOAT(10,2),
    GENDER VARCHAR(6),
    PRIMARY KEY (USERNAME)
);

INSERT INTO INFO 
VALUES
('frottori','Frosso','Varsou','froso2003',100.0,'Female'),
('filegeiasou','Aggelos','Mentzelos','penguin1234',50.0,'Male'),
('dimas','Alexandros','Dimas','pizza',25000.0,'Male');