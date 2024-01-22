create database logins;
-- Login database
CREATE DATABASE IF NOT EXISTS mydb; 
USE mydb;
CREATE TABLE IF NOT EXISTS users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL
);
select * from users;
 -- vendor database---
 use logins;
CREATE TABLE Vendor (
    vendorName VARCHAR(255),
    BankAccountNum INT,
    BankName VARCHAR(255),
    AddressLine1 VARCHAR(255),
    AddressLine2 VARCHAR(255),
    City VARCHAR(255),
    Country VARCHAR(255),
    ZipCode INT
);
select * from Vendor;
truncate table users;
truncate table vendor;
SELECT * FROM githubdb.Users;

