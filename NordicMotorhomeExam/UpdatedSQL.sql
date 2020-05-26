DROP DATABASE IF EXISTS motorhomedatabase;
CREATE DATABASE motorhomedatabase;
USE motorhomedatabase;

-- Create all tables

CREATE TABLE zips
(
zips_id INT PRIMARY KEY AUTO_INCREMENT,
city VARCHAR(20) NOT NULL,
country VARCHAR(20) NOT NULL
);
CREATE TABLE model
(
model_id INT PRIMARY KEY NOT NULL,
name_model VARCHAR(30) NOT NULL,
type_model VARCHAR(30) NOT NULL,
brand VARCHAR(35) NOT NULL,
size VARCHAR(15) NOT NULL,
layout VARCHAR(10) NOT NULL,
seats VARCHAR(10) NOT NULL
);
CREATE TABLE motorhomes(
motor_id INT PRIMARY KEY AUTO_INCREMENT,
reg_number VARCHAR(10) NOT NULL,
motor_model INT NOT NULL,
status_rent VARCHAR(15) NOT NULL,
mileage INT NOT NULL,
capacity INT NOT NULL,
FOREIGN KEY (motor_model)
REFERENCES model(model_id)
);

CREATE TABLE customers(
cus_id INT PRIMARY KEY AUTO_INCREMENT,
first_name VARCHAR(30)  NOT NULL,
last_name VARCHAR(30) NOT NULL, 
address VARCHAR(50) NOT NULL,
cus_zips INT NOT NULL,
driver_license VARCHAR(9) NOT NULL,
email VARCHAR(50) NOT NULL,
phone_number VARCHAR(10) NOT NULL,
FOREIGN KEY(cus_zips)
REFERENCES zips (zips_id)
);
CREATE TABLE reservations
(
res_id INT PRIMARY KEY AUTO_INCREMENT,
res_customer INT NOT NULL,
res_motorhome INT NOT NULL,
date_made DATE NOT NULL,
date_reservation DATE NOT NULL,
invoice_id INT DEFAULT NULL,
price INT NOT NULL,
FOREIGN KEY  (res_motorhome)
REFERENCES motorhomes(motor_id),
FOREIGN KEY (res_customer)
REFERENCES customers(cus_id)
);
CREATE TABLE extras(
extra_id INT PRIMARY KEY AUTO_INCREMENT,
name_extra VARCHAR(45) NOT NULL,
price INT NOT NULL,
available VARCHAR(3) NOT NULL,
extra_reservation INT NOT NULL,
FOREIGN KEY (extra_reservation)
REFERENCES reservations(res_id)
);

CREATE TABLE invoice
(
invoices_id INT PRIMARY KEY NOT NULL,
total_price INT NOT NULL,
canceled TINYINT NOT NULL,
invoice_reservation INT NOT NULL,
FOREIGN KEY (invoice_reservation)
REFERENCES reservations(res_id)
);
INSERT INTO zips VALUE
(2000,"Frederiksberg","Denmark"),
(5000,"Odense C","Denmark"),
(2400,"København NV","Denmark");
INSERT INTO model VALUE
(1,"","Model C luxury","",7,"7 meters","C"),
(2,"","Model B basic ","",5,"5 meters","B"),
(3,"","Model B basic ","",4,"6 meters","B");
INSERT INTO customers VALUES
(1,"Alexander","Minchev","st.Highstreet 45",5000,"353121327","juicyapple312@gmail.com","4556894033"),
(2,"Georrge","Jungle","bul.Highbulevard 6",2400,"569873841","partyManego55@gmail.com","4568793104"),
(3,"Michael","Michelsen","st.Donald 4",2000,"450934673","theManisHere@yahoo.com","4578634578");
INSERT INTO motorhomes VALUE
(1,"AC9489321",2,"Available",3000,100),
(2,"AC8485673",2,"Repair",1000,100),
(3,"CB3123353",3,"Rented",100,500);
INSERT INTO reservations VALUE
(675893,1,1,"2020-03-26","2020-05-31",null,500),
(987321,3,2,"2020-05-28","2020-06-06",null,400),
(678914,2,3,"2020-05-24","2020-06-01",null,250);
INSERT INTO extras VALUES
(1,"Cushions",50,"Yes",675893),
(2,"Blankets",50,"Yes",675893),
(3,"Baby Seat",0,"Yes",675893);
