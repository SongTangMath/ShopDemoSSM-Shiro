create database shopdemospring charset utf8mb4;
use shopdemospring;
create table product_info
(id int(10) primary key auto_increment,
product_name varchar(60) not null,
product_status int(10)not null,
price int(10),
inventory_quantity int(10),
picture_link varchar(120),
product_plan text,
buying_price int(10) default 0,
product_category varchar(60),
unique(product_name)
)charset utf8mb4;
create table user
(id int(10) primary key auto_increment,
username varchar(20),
password varchar(10),
phone varchar(11),
address varchar(30), 
unique(username))
charset utf8mb4;
create table employee
	(
			id int(10) primary key auto_increment,
			identity_card varchar(20),
			name varchar(30),			
			password varchar(20),			
			department_name varchar(30),
			job varchar(30),	
			salary int(10),
			unique(identity_card)
		)charset utf8mb4;

create table order_info
	(
		order_id int(10) primary key auto_increment,
username varchar(20) not null,
order_datetime datetime not null,
product_name varchar(60)not null,
product_quantity int(10)not null,
price int(10) not null,
extended_attribute_string  varchar(60) default '',
buying_price int(10) default 0,
product_category varchar(60)
)	charset utf8mb4;

create table category(
category_id int(10) primary key auto_increment,
category_name varchar(60),
parent_id int(10),
is_end tinyint(4) default 0,
category_status tinyint(4) default 0,
category_level int(10),
unique(category_name) )charset utf8mb4;

create table extended_attribute(
attribute_id int(10) primary key auto_increment,
	product_id int(10) ,
attribute_name varchar(60),
attribute_value varchar(60),
unique(product_id,attribute_name))charset utf8mb4;

