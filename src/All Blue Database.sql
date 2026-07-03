CREATE DATABASE Allblue;
USE Allblue;
CREATE TABLE login(
   id INT PRIMARY KEY AUTO_INCREMENT,
   email VARCHAR(100),
   mobile VARCHAR(20),
   password VARCHAR(100)
);
CREATE TABLE contact(
   id INT PRIMARY KEY AUTO_INCREMENT,
   name VARCHAR(100),
   email VARCHAR(100),
   phone VARCHAR(20),
   message TEXT
);
select id,email,mobile,password  from login inner join contact on id, name, email,phone,message ; 

drop database allblue;

