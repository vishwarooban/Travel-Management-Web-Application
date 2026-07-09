CREATE DATABASE railway;
USE railway;
CREATE TABLE login (
    id INT AUTO_INCREMENT PRIMARY KEY,
    email VARCHAR(100) NOT NULL,
    mobile VARCHAR(20) NOT NULL,
    password VARCHAR(100) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
CREATE TABLE contact (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL,
    phone VARCHAR(20) NOT NULL,
    message TEXT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
    -- View Records
SELECT * FROM login;
SELECT * FROM contact;

       -- Delete All Records
       DELETE FROM login;
       DELETE FROM contact;
       
       
         -- Drop Tables
         DROP TABLE login;
         DROP TABLE contact;
         
         -- Count Records
         SELECT COUNT(*) FROM login;
		 SELECT COUNT(*) FROM contact;
         
         -- Show All Tables
         SHOW TABLES;
         
           -- Describe Tables
           DESC login;
           DESC contact;