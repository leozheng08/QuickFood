

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

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

    public static void insertUser(String username, String password, String repassword, String emailAddress) {
        try {

            getConnection();
            String insertIntoCustomerRegisterQuery = "INSERT INTO Registration(username,password,repassword,emailaddress) "
                    + "VALUES (?,?,?,?);";

            PreparedStatement pst = conn.prepareStatement(insertIntoCustomerRegisterQuery);
            pst.setString(1, username);
            pst.setString(2, password);
            pst.setString(3, repassword);
            pst.setString(4, emailAddress);
            pst.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static HashMap<String, User> selectUser() {
        HashMap<String, User> hm = new HashMap<String, User>();
        try {
            getConnection();
            Statement stmt = conn.createStatement();
            String selectCustomerQuery = "select * from Registration";
            ResultSet rs = stmt.executeQuery(selectCustomerQuery);
            while (rs.next()) {
                User user = new User(rs.getString("username"), rs.getString("password"));
                hm.put(rs.getString("username"), user);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return hm;
    }

    public static void InsertBussinesses(){
        try{
            getConnection();
            String deleteTrans = "delete from transactions;";
            PreparedStatement psttTrans = conn.prepareStatement(deleteTrans);
            psttTrans.executeUpdate();

            String deleteCate = "delete from  categories;";
            PreparedStatement psttCate = conn.prepareStatement(deleteCate);
            psttCate.executeUpdate();

            String deleteBus = "delete from  Businesses;";
            PreparedStatement psttBus = conn.prepareStatement(deleteBus);
            psttBus.executeUpdate();

            String insertBussinessQurey = "INSERT INTO  Businesses(id,alias,businessname,imageurl,isClosed,url,reviewcount,rating,coordinatelatitude,coordinatelongitude,price,phone,displayphone,distance,locationaddress1,locationaddress2,locationaddress3,locationcity,locationzipcode,locationcountry,locationstate,locationdisplayaddress)" +
                    "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            String insertCategoriesQuery = "INSERT INTO  categories(id,alias,title)"+"VALUES(?,?,?)";
            String insertTransactionsQuery = "INSERT INTO  transactions(id,transactionDes)"+"VALUES(?,?)";
            for(int i=0;i<ReadJson.businesses.size();i++){
                PreparedStatement pst = conn.prepareStatement(insertBussinessQurey);
                PreparedStatement pstCate = conn.prepareStatement(insertCategoriesQuery);
                PreparedStatement pstTrans = conn.prepareStatement(insertTransactionsQuery);
                Business bus = ReadJson.businesses.get(i);

                pst.setString(1,bus.getId());
                pst.setString(2,bus.getAlias());
                pst.setString(3,bus.getName());
                pst.setString(4,bus.getImage_url());
                pst.setBoolean(5,bus.isIs_closed());
                pst.setString(6,bus.getUrl());
                pst.setLong(7,bus.getReview_count());
                pst.setDouble(8,bus.getRating());
                pst.setDouble(9,bus.getCoordinates().getLatitude());
                pst.setDouble(10,bus.getCoordinates().getLongitude());
                pst.setString(11,bus.getPrice());
                pst.setString(12,bus.getPhone());
                pst.setString(13,bus.getDisplay_phone());
                pst.setString(14,bus.getDistance());
                pst.setString(15,bus.getLocation().getAddress1());
                pst.setString(16,bus.getLocation().getAddress2());
                pst.setString(17,bus.getLocation().getAddress3());
                pst.setString(18,bus.getLocation().getCity());
                pst.setString(19,bus.getLocation().getZip_code());
                pst.setString(20,bus.getLocation().getCountry());
                pst.setString(21,bus.getLocation().getState());
                String displayAddress = String.join(",",bus.getLocation().getDisplay_address());
                pst.setString(22,displayAddress);
                pst.executeUpdate();



                for(Category cate:bus.getCategories()){
                    pstCate.setString(1,bus.getId());
                    pstCate.setString(2,cate.getAlias());
                    pstCate.setString(3,cate.getTitle());
                    pstCate.executeUpdate();
                }

                for(int j=0;j<bus.getTransactions().size();j++){
                    pstTrans.setString(1,bus.getId());
                    pstTrans.setString(2,bus.getTransactions().get(j));
                    pstTrans.executeUpdate();
                }


            }

        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public static ArrayList<Business> getBussinesses(String zipcode) {
        ArrayList<Business> businessArrayList = new ArrayList<>();
        try{
            getConnection();
            String selectBusiness= "select * from Businesses where locationzipcode =?";
            PreparedStatement pst = conn.prepareStatement(selectBusiness);
            pst.setString(1,zipcode);
            ResultSet rsB = pst.executeQuery();
            while(rsB.next()){
                String selectTransactions= "select * from transactions where id =?";
                PreparedStatement pstTransactions = conn.prepareStatement(selectTransactions);
                pstTransactions.setString(1,rsB.getString("id"));
                ResultSet rsT = pstTransactions.executeQuery();
                String selectCategories= "select * from categories where id =?";
                PreparedStatement pstCategories = conn.prepareStatement(selectCategories);
                pstCategories.setString(1,rsB.getString("id"));
                ResultSet rsC = pstCategories.executeQuery();
                List<String> transactions = new ArrayList<>();
                while(rsT.next()){
                    transactions.add(rsT.getString("transactionDes"));
                }
                List<Category> categories = new ArrayList<>();
                while(rsC.next()){
                    Category category = new Category(rsC.getString("alias"),rsC.getString("title"));
                    categories.add(category);
                }
                Coordinate coordinate = new Coordinate(rsB.getDouble("coordinatelatitude"),rsB.getDouble("coordinatelongitude"));
                List<String> displayAddress = new ArrayList<String>(Arrays.asList(rsB.getString("locationdisplayaddress").split(",")));;
                Location location = new Location(rsB.getString("locationaddress1"),rsB.getString("locationaddress2"),rsB.getString("locationaddress3"),
                        rsB.getString("locationcity"),rsB.getString("locationzipcode"),rsB.getString("locationcountry"),rsB.getString("locationstate"),displayAddress
                );
                Business business = new Business(rsB.getString("id"),rsB.getString("alias"),rsB.getString("businessname"),
                rsB.getString("imageurl"),rsB.getBoolean("isClosed"),rsB.getString("url"),rsB.getLong("reviewcount"),
                        rsB.getDouble("rating"),coordinate,rsB.getString("price"),location,rsB.getString("phone"),rsB.getString("displayphone"),
                rsB.getString("distance"),categories,transactions);
                businessArrayList.add(business);
            }

        }
        catch(Exception e){
            e.printStackTrace();
        }
        return businessArrayList;
    }

    public static Business getBussiness(String bussinessId) {
        ArrayList<Business> businessArrayList = new ArrayList<>();
        try{
            getConnection();
            String selectBusiness= "select * from Businesses where id =?";
            PreparedStatement pst = conn.prepareStatement(selectBusiness);
            pst.setString(1,bussinessId);
            ResultSet rsB = pst.executeQuery();
            while(rsB.next()){
                String selectTransactions= "select * from transactions where id =?";
                PreparedStatement pstTransactions = conn.prepareStatement(selectTransactions);
                pstTransactions.setString(1,rsB.getString("id"));
                ResultSet rsT = pstTransactions.executeQuery();
                String selectCategories= "select * from categories where id =?";
                PreparedStatement pstCategories = conn.prepareStatement(selectCategories);
                pstCategories.setString(1,rsB.getString("id"));
                ResultSet rsC = pstCategories.executeQuery();
                List<String> transactions = new ArrayList<>();
                while(rsT.next()){
                    transactions.add(rsT.getString("transactionDes"));
                }
                List<Category> categories = new ArrayList<>();
                while(rsC.next()){
                    Category category = new Category(rsC.getString("alias"),rsC.getString("title"));
                    categories.add(category);
                }
                Coordinate coordinate = new Coordinate(rsB.getDouble("coordinatelatitude"),rsB.getDouble("coordinatelongitude"));
                List<String> displayAddress = new ArrayList<String>(Arrays.asList(rsB.getString("locationdisplayaddress").split(",")));;
                Location location = new Location(rsB.getString("locationaddress1"),rsB.getString("locationaddress2"),rsB.getString("locationaddress3"),
                        rsB.getString("locationcity"),rsB.getString("locationzipcode"),rsB.getString("locationcountry"),rsB.getString("locationstate"),displayAddress
                );
                Business newbusiness = new Business(rsB.getString("id"), rsB.getString("alias"), rsB.getString("businessname"),
                        rsB.getString("imageurl"), rsB.getBoolean("isClosed"), rsB.getString("url"), rsB.getLong("reviewcount"),
                        rsB.getDouble("rating"), coordinate, rsB.getString("price"), location, rsB.getString("phone"), rsB.getString("displayphone"),
                        rsB.getString("distance"), categories, transactions);
                businessArrayList.add(newbusiness);
            }

        }
        catch(Exception e){
            e.printStackTrace();
        }
        return businessArrayList.get(0);
    }

    public static ArrayList<Business> getTop(String zipcode,int number) {
        ArrayList<Business> businessArrayList = new ArrayList<>();
        try{
            getConnection();
            String selectBusiness= "SELECT  * FROM Businesses where locationzipcode =?order by reviewcount desc limit ?; ";
            PreparedStatement pst = conn.prepareStatement(selectBusiness);
            pst.setString(1,zipcode);
            pst.setInt(2,number);
            ResultSet rsB = pst.executeQuery();
            while(rsB.next()){
                String selectTransactions= "select * from transactions where id =?";
                PreparedStatement pstTransactions = conn.prepareStatement(selectTransactions);
                pstTransactions.setString(1,rsB.getString("id"));
                ResultSet rsT = pstTransactions.executeQuery();
                String selectCategories= "select * from categories where id =?";
                PreparedStatement pstCategories = conn.prepareStatement(selectCategories);
                pstCategories.setString(1,rsB.getString("id"));
                ResultSet rsC = pstCategories.executeQuery();
                List<String> transactions = new ArrayList<>();
                while(rsT.next()){
                    transactions.add(rsT.getString("transactionDes"));
                }
                List<Category> categories = new ArrayList<>();
                while(rsC.next()){
                    Category category = new Category(rsC.getString("alias"),rsC.getString("title"));
                    categories.add(category);
                }
                Coordinate coordinate = new Coordinate(rsB.getDouble("coordinatelatitude"),rsB.getDouble("coordinatelongitude"));
                List<String> displayAddress = new ArrayList<String>(Arrays.asList(rsB.getString("locationdisplayaddress").split(",")));;
                Location location = new Location(rsB.getString("locationaddress1"),rsB.getString("locationaddress2"),rsB.getString("locationaddress3"),
                        rsB.getString("locationcity"),rsB.getString("locationzipcode"),rsB.getString("locationcountry"),rsB.getString("locationstate"),displayAddress
                );
                Business business = new Business(rsB.getString("id"),rsB.getString("alias"),rsB.getString("businessname"),
                        rsB.getString("imageurl"),rsB.getBoolean("isClosed"),rsB.getString("url"),rsB.getLong("reviewcount"),
                        rsB.getDouble("rating"),coordinate,rsB.getString("price"),location,rsB.getString("phone"),rsB.getString("displayphone"),
                        rsB.getString("distance"),categories,transactions);
                businessArrayList.add(business);
            }

        }
        catch(Exception e){
            e.printStackTrace();
        }
        return businessArrayList;
    }

    public static void InsertMenu(){
        try{
            getConnection();
            String deleteRM = "delete from restaurantmenu;";
            PreparedStatement psttRM = conn.prepareStatement(deleteRM);
            psttRM.executeUpdate();

            String deleteS = "delete from  starter;";
            PreparedStatement psttS = conn.prepareStatement(deleteS);
            psttS.executeUpdate();

            String deleteMC = "delete from  maincourses;";
            PreparedStatement psttMC = conn.prepareStatement(deleteMC);
            psttMC.executeUpdate();

            String deleteDT = "delete from  dessert;";
            PreparedStatement psttDT = conn.prepareStatement(deleteDT);
            psttDT.executeUpdate();

            String deleteDK= "delete from  drink;";
            PreparedStatement psttDK = conn.prepareStatement(deleteDK);
            psttDK.executeUpdate();

            String insertStarterQurey = "INSERT INTO  starter(id,starter,price,imageurl)" +
                    "VALUES (?,?,?,?)";
            String insertMainCoursesQurey = "INSERT INTO maincourses(id,maincourse,price,imageurl)" +
                    "VALUES (?,?,?,?)";
            String insertDessertQurey = "INSERT INTO  dessert(id,dessert,price,imageurl)" +
                    "VALUES (?,?,?,?)";
            String insertDrinkQurey = "INSERT INTO  drink(id,drink,price,imageurl)" +
                    "VALUES (?,?,?,?)";

            for(int i=0;i<ReadJson.businesses.size();i++){
                PreparedStatement pstS = conn.prepareStatement(insertStarterQurey);
                PreparedStatement pstMC = conn.prepareStatement(insertMainCoursesQurey);
                PreparedStatement pstDT = conn.prepareStatement(insertDessertQurey);
                PreparedStatement pstDK = conn.prepareStatement(insertDrinkQurey);
                Business bus = ReadJson.businesses.get(i);

                pstS.setString(1,bus.getId());
                pstS.setString(2,"Mexican Enchiladas");
                pstS.setDouble(3,9.40);
                pstS.setString(4,"img/Mexican Enchiladas.jpg");
                pstS.executeUpdate();
                pstS.setString(1,bus.getId());
                pstS.setString(2,"Fajitas");
                pstS.setDouble(3,6.80);
                pstS.setString(4,"img/Fajitas.jpg");
                pstS.executeUpdate();
                pstS.setString(1,bus.getId());
                pstS.setString(2," Royal Fajitas");
                pstS.setDouble(3,5.70);
                pstS.setString(4,"img/Royal Fajitas.jpg");
                pstS.executeUpdate();
                pstS.setString(1,bus.getId());
                pstS.setString(2,"Chicken Enchilada Wrap");
                pstS.setDouble(3,5.20);
                pstS.setString(4,"img/Chicken Enchilada Wrap.jpg");
                pstS.executeUpdate();


                pstMC.setString(1,bus.getId());
                pstMC.setString(2,"Cheese Quesadilla");
                pstMC.setDouble(3,12.00);
                pstMC.setString(4,"img/Cheese Quesadilla.jpg");
                pstMC.executeUpdate();

                pstMC.setString(1,bus.getId());
                pstMC.setString(2,"Chorizo & Cheese");
                pstMC.setDouble(3,24.71);
                pstMC.setString(4,"img/Chorizo & Cheese.jpg");
                pstMC.executeUpdate();
                pstMC.setString(1,bus.getId());
                pstMC.setString(2,"Beef Taco");
                pstMC.setDouble(3,8.70);
                pstMC.setString(4,"img/Beef Taco.jpg");
                pstMC.executeUpdate();
                pstMC.setString(1,bus.getId());
                pstMC.setString(2," Minced Beef Double Layer");
                pstMC.setDouble(3,6.30);
                pstMC.setString(4,"img/Minced Beef Double Layer.jpg");
                pstMC.executeUpdate();
                pstMC.setString(1,bus.getId());
                pstMC.setString(2,"Piri Piri Chicken");
                pstMC.setDouble(3,7.40);
                pstMC.setString(4,"img/Piri Piri Chicken.jpg");
                pstMC.executeUpdate();

                pstDT.setString(1,bus.getId());
                pstDT.setString(2,"Chocolate Fudge Cake");
                pstDT.setDouble(3,24.71);
                pstDT.setString(4,"img/Chocolate Fudge Cake.jpg");
                pstDT.executeUpdate();
                pstDT.setString(1,bus.getId());
                pstDT.setString(2,"Cheesecake");
                pstDT.setDouble(3,7.50);
                pstDT.setString(4,"img/Cheesecake.jpg");
                pstDT.executeUpdate();
                pstDT.setString(1,bus.getId());
                pstDT.setString(2,"Apple Pie & Custard");
                pstDT.setDouble(3,9.70);
                pstDT.setString(4,"img/Apple Pie & Custard.jpg");
                pstDT.executeUpdate();
                pstDT.setString(1,bus.getId());
                pstDT.setString(2,"Profiteroles");
                pstDT.setDouble(3,12.00);
                pstDT.setString(4,"img/Profiteroles.jpg");
                pstDT.executeUpdate();

                pstDK.setString(1,bus.getId());
                pstDK.setString(2,"Coke 0.33L");
                pstDK.setDouble(3,5.70);
                pstDK.setString(4,"img/Coke 0.33L.jpg");
                pstDK.executeUpdate();
                pstDK.setString(1,bus.getId());
                pstDK.setString(2,"Diet Coke 0.33L");
                pstDK.setDouble(3,2.70);
                pstDK.setString(4,"img/Diet Coke 0.33L.jpg");
                pstDK.executeUpdate();
                pstDK.setString(1,bus.getId());
                pstDK.setString(2,"Diet Coke 1L");
                pstDK.setDouble(3,5.70);
                pstDK.setString(4,"img/Diet Coke 1L.jpg");
                pstDK.executeUpdate();
                pstDK.setString(1,bus.getId());
                pstDK.setString(2,"Fanta Orange 0.33L");
                pstDK.setDouble(3,2.70);
                pstDK.setString(4,"img/Fanta Orange 0.33L.jpg");
                pstDK.executeUpdate();

            }

        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public static Menu getMenu(String bussinessId) {
        List<Starter> starters = new ArrayList<>();
        List<mainCourse> mainCourses = new ArrayList<>();
        List<Dessert> desserts = new ArrayList<>();
        List<Drink> drinks = new ArrayList<>();
        Menu menu = null;
        try {

            getConnection();
            String selectStarters = "select * from starter where id=?";
            PreparedStatement pstS = conn.prepareStatement(selectStarters);
            pstS.setString(1, bussinessId);
            ResultSet rsS = pstS.executeQuery();
            while (rsS.next()) {
                String starterName = rsS.getString("starter");
                double price = rsS.getDouble("price");
                String imageurl = rsS.getString("imageurl");
                Starter starter = new Starter(starterName, price, imageurl);
                starters.add(starter);
            }

            String selectMaincourses = "select * from maincourses where id=?";
            PreparedStatement pstMC = conn.prepareStatement(selectMaincourses);
            pstMC.setString(1, bussinessId);
            ResultSet rsMC = pstMC.executeQuery();
            while (rsMC.next()) {
                String mainCourseName = rsMC.getString("maincourse");
                double price = rsMC.getDouble("price");
                String imageurl = rsMC.getString("imageurl");
                mainCourse mainC = new mainCourse(mainCourseName, price, imageurl);
                mainCourses.add(mainC);
            }

            String selectDesserts = "select * from dessert where id=?";
            PreparedStatement pstDT = conn.prepareStatement(selectDesserts);
            pstDT.setString(1, bussinessId);
            ResultSet rsDT = pstDT.executeQuery();
            while (rsDT.next()) {
                String dessertName = rsDT.getString("dessert");
                double price = rsDT.getDouble("price");
                String imageurl = rsDT.getString("imageurl");
                Dessert dessert = new Dessert(dessertName, price, imageurl);
                desserts.add(dessert);
            }

            getConnection();
            String selectDrinks = "select * from drink where id=?";
            PreparedStatement pstDK = conn.prepareStatement(selectDrinks);
            pstDK.setString(1, bussinessId);
            ResultSet rsDK = pstDK.executeQuery();
            while (rsDK.next()) {
                String drinkName = rsDK.getString("drink");
                double price = rsDK.getDouble("price");
                String imageurl = rsDK.getString("imageurl");
                Drink drink = new Drink(drinkName, price, imageurl);
                drinks.add(drink);
            }
            menu = new Menu(bussinessId, starters, mainCourses, desserts, drinks);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return menu;
    }

    public static HashMap<Integer, ArrayList<OrderPayment>> selectOrder() {
        HashMap<Integer, ArrayList<OrderPayment>> orderPayments = new HashMap<Integer, ArrayList<OrderPayment>>();
        try {
            getConnection();
            String selectOrderQuery = "select * from CustomerOrders";
            PreparedStatement pst = conn.prepareStatement(selectOrderQuery);
            ResultSet rs = pst.executeQuery();
            ArrayList<OrderPayment> orderList = new ArrayList<OrderPayment>();
            while (rs.next()) {
                if (!orderPayments.containsKey(rs.getInt("OrderId"))) {
                    ArrayList<OrderPayment> arr = new ArrayList<OrderPayment>();
                    orderPayments.put(rs.getInt("orderId"), arr);
                }
                ArrayList<OrderPayment> listOrderPayment = orderPayments.get(rs.getInt("OrderId"));
                System.out.println("data is" + rs.getInt("OrderId") + orderPayments.get(rs.getInt("OrderId")));

                OrderPayment order = new OrderPayment(rs.getInt("OrderId"), rs.getString("userName"), rs.getString("orderName"), rs.getDouble("orderPrice"), rs.getString("nameCardOrder"), rs.getString("cardNumber"),rs.getString("expireMonth"),rs.getString("expireYear"),rs.getString("CCV"),rs.getString("address"),rs.getString("businessId"));
                listOrderPayment.add(order);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return orderPayments;
    }

    public static void insertOrder(int orderId, String userName, String orderName, double orderPrice, String nameCardOrder, String cardNumber, String expireMonth, String expireYear, String ccv, String address,String businessId) {
        try {

            getConnection();
            String insertIntoCustomerOrderQuery = "INSERT INTO CustomerOrders(OrderId,userName,orderName,orderPrice,nameCardOrder,cardNumber,expireMonth,expireYear,CCV,address,businessId ) "
                    + "VALUES (?,?,?,?,?,?,?,?,?,?,?);";

            PreparedStatement pst = conn.prepareStatement(insertIntoCustomerOrderQuery);
            //set the parameter for each column and execute the prepared statement
            pst.setInt(1, orderId);
            pst.setString(2, userName);
            pst.setString(3, orderName);
            pst.setDouble(4, orderPrice);
            pst.setString(5, nameCardOrder);
            pst.setString(6, cardNumber);
            pst.setString(7, expireMonth);
            pst.setString(8, expireYear);
            pst.setString(9, ccv);
            pst.setString(10, address );
            pst.setString(11, businessId );
            pst.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
