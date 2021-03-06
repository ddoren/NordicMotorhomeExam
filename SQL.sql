DROP DATABASE IF EXISTS motorhomedatabase;
CREATE DATABASE motorhomedatabase;
USE motorhomedatabase;

-- Create all tables
CREATE TABLE employees
(
employee_id INT PRIMARY KEY AUTO_INCREMENT,
first_name VARCHAR(30) NOT NULL,
email	VARCHAR(50) NOT NULL,
employ_pass VARCHAR(10) NOT NULL,
type_employee VARCHAR(20) NOT NULL
);
CREATE TABLE zips
(
zips_id INT PRIMARY KEY AUTO_INCREMENT,
city VARCHAR(20) NOT NULL,
country VARCHAR(20) NOT NULL
);
CREATE TABLE carmodel
(
model_id INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
model_name VARCHAR(30) NOT NULL,
model_type VARCHAR(30) NOT NULL,
brand VARCHAR(35) NOT NULL,
size VARCHAR(15) NOT NULL,
layout VARCHAR(10) NOT NULL,
seats VARCHAR(10) NOT NULL
);
CREATE TABLE motorhomes(
motor_id INT PRIMARY KEY AUTO_INCREMENT,
reg_number VARCHAR(10) NOT NULL,
motor_model INT,
status_rent VARCHAR(15) NOT NULL,
price_per_day VARCHAR(20) NOT NULL,
mileage INT NOT NULL,
capacity INT NOT NULL,
FOREIGN KEY (motor_model)
REFERENCES carmodel(model_id) ON DELETE SET NULL 
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
bank_card VARCHAR(20) NOT NULL,
FOREIGN KEY(cus_zips)
REFERENCES zips (zips_id)
);
CREATE TABLE reservations
(
res_id INT PRIMARY KEY AUTO_INCREMENT,
res_customer INT,
res_motorhome INT,
date_made DATE NOT NULL,
date_reservation_start DATE NOT NULL,
date_reservation_end DATE NOT NULL,
season VARCHAR(20) NOT NULL,
invoice_id INT ,
price INT NOT NULL,
FOREIGN KEY  (res_motorhome)
REFERENCES motorhomes(motor_id) ON DELETE SET NULL,
FOREIGN KEY (res_customer)
REFERENCES customers(cus_id) ON DELETE SET NULL
);
CREATE TABLE extras(
extra_id INT PRIMARY KEY AUTO_INCREMENT,
name_extra VARCHAR(45) NOT NULL,
price INT DEFAULT 0,
available VARCHAR(3) NOT NULL
);

CREATE TABLE invoices
(
invoices_id INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
invoice_reservation INT NOT NULL,
service VARCHAR(20) NOT NULL,
addit_expenses INT DEFAULT 0,
addit_exp_descript VARCHAR(30) DEFAULT "None",
canceled VARCHAR(3) NOT NULL,
total_price INT NOT NULL
);

CREATE TABLE Extras_in_Reservations
(reservation_id INT NOT NULL,
extra_id INT NOT NULL,
FOREIGN KEY  (reservation_id)
REFERENCES reservations(res_id) ON DELETE CASCADE,
FOREIGN KEY (extra_id)
REFERENCES extras(extra_id) ON DELETE CASCADE
);

-- Sample data
INSERT INTO employees VALUE
(1,"Josh","mailme@gmail.com","josh12tech","mechanic"),
(2,"Michael","michael.work@gmail.com","qwerty99","owner"),
(3,"Jenny","j.camilla@gmail.com","camJ123n","sales assistant"),
(4,"George","g.rasmus@gmail.com","123clean32","cleaning assistant"),
(5,"Mike","m.thomson@gmail.com","stats123","bookkeeper");
INSERT INTO zips VALUES
(2000,"Frederiksberg","Denmark"),
(5000,"Odense C","Denmark"),
(2400,"København NV","Denmark");
INSERT INTO carmodel VALUES
(1,"Mercedes-Benz","Model C luxury","",7,"7 meters","C"),
(2,"Fiat","Model B basic ","",5,"5 meters","B"),
(3,"RAM","Model B basic ","",4,"6 meters","B"),
(4,"Mercedes-Benz","Model B basic ","",4,"6 meters","B"),
(5,"Fiat","Model C Luxury ","",7,"7 meters","C");
INSERT INTO customers VALUES
(1,"Alexander","Minchev","st.Highstreet 45",5000,"353121327","juicyapple312@gmail.com","4556894033", "1212121212121212"),
(2,"Georrge","Jungle","bul.Highbulevard 6",2400,"569873841","partyManego55@gmail.com","4568793104", "3232323232323232"),
(3,"Michael","Michelsen","st.Donald 4",2000,"450934673","theManisHere@yahoo.com","4578634578", "3232121232321212");
INSERT INTO motorhomes VALUE
(1,"AC9489321",2,"Ready",1000,3000,100),
(2,"AC8485673",1,"Repair",500,1000,100),
(3,"CB3123353",1,"Ready",800,7000,500),
(4,"GL3489215",3,"Cleaning",700,4000,200),
(5,"PO8215683",2,"Ready",400,10000,700),
(6,"JK6215783",1,"Ready",500,5000,800),
(7,"KA4521441",4,"Ready",600,6000,700),
(8,"NE4745212",4,"Ready",600,2500,700),
(9,"BL3441447",5,"Ready",950,3200,700);
INSERT INTO reservations VALUE
(1,1,1,"2020-03-26","2020-06-25","2020-06-29","Normal season", DEFAULT,4550),
(2,2,3,"2020-05-28","2020-07-06","2020-07-10","Normal season", DEFAULT,3300),
(3,3,5,"2020-05-24","2020-06-14","2020-06-19","Normal season", DEFAULT,1150);
INSERT INTO extras VALUES
(1,"Cushions",50,"Yes"),
(2,"Blankets",50,"Yes"),
(3,"Baby Seat",0,"Yes");
INSERT INTO invoices VALUES
(1, 3, "Motorhome Rent", DEFAULT, DEFAULT, "No", 4550),
(2, 2, "Reservation", DEFAULT, DEFAULT, "Yes", 3300),
(3, 1, "Motorhome Rent", DEFAULT, DEFAULT, "No", 1150);
INSERT INTO Extras_in_Reservations VALUES
(3,1),
(3,2),
(2,1),
(1,2), 
(2,2),
(1,3);
