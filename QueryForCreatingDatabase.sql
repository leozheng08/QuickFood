use quickfooddatabase;

create table Businesses(id varchar(120),alias varchar(1000),businessname varchar(1000),imageurl varchar(1000),
isClosed tinyint(1),url varchar(1200),reviewcount mediumtext, rating double, coordinatelatidute double, coordinatelongitude double,
price varchar(40),phone varchar(40), displayphone varchar(40), distance varchar(1000), locationaddress1 varchar(1200), locationaddress2 varchar(1200),locationaddress3 varchar(1200),
locationcity varchar(45), locationzipcode varchar(45), locationcountry varchar(45), locationstate varchar(45), locationdisplayaddress varchar(120),Primary key(id));


Create table CustomerOrders
(
OrderId integer,
userName varchar(40),
orderName varchar(40),
orderPrice double,
nameCardOrder VARCHAR(1200),
cardNumber VARCHAR(1200),
expireMonth VARCHAR(120),
expireYear VARCHAR(120),
CCV VARCHAR(120),
address VARCHAR(1200),
businessId VARCHAR(1200),
Primary key(OrderId,userName,orderName)
);

Create table starter
(
id varchar(120),
starter varchar(120),
price double, 
imageurl varchar(1000)
);


Create table maincourses
(
id varchar(120),
maincourse varchar(120),
price double,
imageurl varchar(1000)
);

Create table dessert
(
id varchar(120),
dessert varchar(120),
price double,
imageurl varchar(1000)
);

Create table drink
(
id varchar(120),
drink varchar(120),
price double,
imageurl varchar(1000)
);

Create table categories
(
id varchar(120),
alias varchar(120),
title varchar(120)
);

Create table Registeration
(
username varchar(120),
password varchar(120),
repassword varchar(120),
emailaddress varchar(120)
);

Create table transactions
(
id varchar(120),
transactionDes varchar(120)
);

Create table restaurantmenu
(
restaurantid varchar(120),
starterid varchar(120),
maincourseid varchar(120),
dessertid varchar(120),
drinkid varchar(120)
);

