# QuickFood(Meal delivery webstie with Best route prediction)

Team leader: Zeyuan,Zheng Email Address:zzheng26@hawk.iit.edu
Team member: Luping,Xue Email Address: lxue9@hawk.iit.edu

## Environment:
* Mysql Database
* JDK 1.7
* Tomcat 7.0.342
* Anaconda
* Jupiter Notebook

## External library:
* json-simple-1.11.jar
* gson-2.3.1.jar

## Deployment:
### 1.Database
(1) Run QueryForCreatingDatabase.sql to create tables in quickfooddatabase;

```sql
create table Businesses(
id varchar(120),
alias varchar(1000),
businessname varchar(1000),
imageurl varchar(1000),
isClosed tinyint(1),
url varchar(1200),
reviewcount mediumtext, 
rating double, 
coordinatelatidute double, 
coordinatelongitude double,
price varchar(40),
phone varchar(40), 
displayphone varchar(40), 
distance varchar(1000), 
locationaddress1 varchar(1200), 
locationaddress2 varchar(1200),locationaddress3 varchar(1200),
locationcity varchar(45), 
locationzipcode varchar(45), 
locationcountry varchar(45), 
locationstate varchar(45), 
locationdisplayaddress varchar(120),
Primary key(id));
```
```sql
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
```
```sql
Create table starter
(
id varchar(120),
starter varchar(120),
price double, 
imageurl varchar(1000)
);
```
```sql
Create table maincourses
(
id varchar(120),
maincourse varchar(120),
price double,
imageurl varchar(1000)
);
```
```sql
Create table dessert
(
id varchar(120),
dessert varchar(120),
price double,
imageurl varchar(1000)
);
```
```sql
Create table drink
(
id varchar(120),
drink varchar(120),
price double,
imageurl varchar(1000)
);
```
```sql
Create table categories
(
id varchar(120),
alias varchar(120),
title varchar(120)
);
```
```sql
Create table Registeration
(
username varchar(120),
password varchar(120),
repassword varchar(120),
emailaddress varchar(120)
);
```
```sql
Create table transactions
(
id varchar(120),
transactionDes varchar(120)
);
```
```sql
Create table restaurantmenu
(
restaurantid varchar(120),
starterid varchar(120),
maincourseid varchar(120),
dessertid varchar(120),
drinkid varchar(120)
)
```
### 2.Jupiter NoteBook
(1)Upload searchRestaurantUpdate.ipynb
```python
from pprint import pprint
yelp_api = YelpAPI(‘m3wTJA76DZqAox7RC6l5vZ1KLqS1jOiso39vhcCLFF844bf04DpPxbCwIPr2MpFGc-_e93bIC1vCCHHBH1d23SOkww4EzRpaBCwgSSNDWvTFR-S25vye4vLvqIK8XXYx')

import time
import json

df__business_reviews = pd.DataFrame()
list__business_reviews_documents = []
data = {}
data['restaurants'] = []
while True:
    for x in range(20):
        response = yelp_api.search_query(categories='Restaurants', 
                                 location='Chicago Loop, West Town, Near West Side Chicago, Near North Side Chicago, Near South Side Chicago,chicago, il', 
                                 sort_by='rating', limit=50, offset=x*50)
        df__business_reviews = df__business_reviews.append(pd.DataFrame(response['businesses']))

    for review in response['businesses']:
        data['restaurants'].append(review)
    with open('chicago-yelp-reviews-for-es.json', 'w') as outfile:
        json.dump(data, outfile)
    print('Going to sleep for 3 minutes now ...')
    time.sleep(180)    
    continue

Going to sleep for 3 minutes now ...
Going to sleep for 3 minutes now ...
pprint(response[‘businesses'])
```
![image](https://user-images.githubusercontent.com/48393773/73128533-6461d980-3f96-11ea-98c7-6fac2e6e9408.png)

(2)Run all cells then you will get a file named “chicago-yelp-reviews-for-es.json”

### 3.Drag “chicago-yelp-reviews-for-es.json”  into project folder.

![image](https://user-images.githubusercontent.com/48393773/73128663-56ad5380-3f98-11ea-9fae-f37de6a213c2.png)

### 4. Edit Connection of DB, and compile

```java
public class MySqlDataStoreUtilities {

    public static Connection conn;
    static String message;
    private static Statement statement;
    private static ResultSet resultSet;
    private static String url = "jdbc:mysql://localhost:3306/quickfooddatabase?userTimezone=true&serverTimezone=UTC";
    private static String username = "root", pass = "5991308Zheng";

    public static String getConnection() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url, username, pass);
            message = "Successfull";
            return message;
        } catch (SQLException e) {
            message = "unsuccessful";
            return message;
        } catch (Exception e) {
            message = e.getMessage();
            return message;
        }
    }
```



### 5. Drag project folder (food ) into Tomcat

![image](https://user-images.githubusercontent.com/48393773/73128670-7e042080-3f98-11ea-8251-dabbe06cbffd.png)

### 6. Start Tomcat

### 7. Open the homepage of QuickFood website and register a user.

![image](https://user-images.githubusercontent.com/48393773/73128702-eb17b600-3f98-11ea-9d6e-010706d11a5b.png)

### 8. Check the Register table.

![image](https://user-images.githubusercontent.com/48393773/73128761-c96afe80-3f99-11ea-86dd-a7c730cee72e.png)

### 9. Login

![image](https://user-images.githubusercontent.com/48393773/73128699-e5ba6b80-3f98-11ea-8c69-ce8ea919e4ec.png)

### 10. Login success

![image](https://user-images.githubusercontent.com/48393773/73128712-14384680-3f99-11ea-82d9-1fe1237d8d75.png)

### 11. Input zip-code to search related restaurants

![image](https://user-images.githubusercontent.com/48393773/73128716-22866280-3f99-11ea-8476-a0997577e748.png)

### 12. display searching answers.

![image](https://user-images.githubusercontent.com/48393773/73128722-3c27aa00-3f99-11ea-81c2-178e2e670e7b.png)

### 13. view into restaurant’s order page.

![image](https://user-images.githubusercontent.com/48393773/73128726-48136c00-3f99-11ea-8203-d3b7254f4d7c.png)

### 14. select meals and click on order now.

![image](https://user-images.githubusercontent.com/48393773/73128730-52ce0100-3f99-11ea-9d7d-c9c1c561b5f3.png)

### 15. input delivery address and bank information.

![image](https://user-images.githubusercontent.com/48393773/73128733-68432b00-3f99-11ea-9e2b-eaa7669e510d.png)

### 16. confirm the order.

![image](https://user-images.githubusercontent.com/48393773/73128738-742eed00-3f99-11ea-885c-5f0ef730c339.png)


### 17. check the order stored in CustomerOrders table.

![image](https://user-images.githubusercontent.com/48393773/73128741-80b34580-3f99-11ea-88a8-dabc93e44e02.png)

### 18. click on the delivery schedule button to show the best route prediction of the order.

![image](https://user-images.githubusercontent.com/48393773/73128744-8b6dda80-3f99-11ea-9c30-9657078b394a.png)
