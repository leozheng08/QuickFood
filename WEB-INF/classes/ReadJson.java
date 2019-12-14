import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.Map;

import com.google.gson.JsonArray;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class ReadJson {

    public static ArrayList<Business> businesses = new ArrayList<>();
    public static void read(){
        JSONParser jsonParser = new JSONParser();
        try (FileReader reader = new FileReader("/Users/zhengleo/IdeaProjects/food/chicago-yelp-reviews-for-es.json"))
        {
            //Read JSON file
            Object obj = jsonParser.parse(reader);
            JSONObject unitsJson = (JSONObject) obj;
            JSONArray restaurantList = (JSONArray) unitsJson.get("restaurants");
//            System.out.println(restaurantList);

            //Iterate over employee array
            restaurantList.forEach( restaurant -> parseRestaurantObject( (JSONObject) restaurant ) );
            System.out.println(businesses);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    private static void parseRestaurantObject(JSONObject restaurant) {
        String id = (String) restaurant.get("id");
        String alias = (String) restaurant.get("alias");
        String name = (String) restaurant.get("name");
        String imageUrl = (String) restaurant.get("image_url");
        boolean isClosed = (Boolean) restaurant.get("is_closed");
        String url = (String) restaurant.get("url");
        long reviewCount = (long) restaurant.get("review_count");
        ArrayList<Category> categories = new ArrayList<>();
        JSONArray categoryList = (JSONArray) restaurant.get("categories");
        Iterator itr2 = categoryList.iterator();
        while(itr2.hasNext()){
            Map map = ((Map)itr2.next());
            String categoryAlias = (String)map.get("alias");
            String categoryTitle = (String)map.get("title");
            Category cate = new Category(categoryAlias,categoryTitle);
            categories.add(cate);
        }

        Double rating = (Double) restaurant.get("rating");
        Map<Double, Double> coordinateMap = (Map<Double, Double>) restaurant.get("coordinates");
        double latitude = coordinateMap.get("latitude");
        double longitude = coordinateMap.get("longitude");
        Coordinate coordinate = new Coordinate(latitude,longitude);

        JSONArray transactionList = (JSONArray) restaurant.get("transactions");
        ArrayList<String> transactions = new ArrayList<>();
        for(int i=0;i<transactionList.size();i++){
            transactions.add((String)transactionList.get(i));
        }
        String price = (String) restaurant.get("price");
        Map locationMap = (Map) restaurant.get("location");
        Location location = new Location();
        location.setAddress1((String)locationMap.get("address1"));
        location.setAddress2((String)locationMap.get("address2"));
        location.setAddress3((String) locationMap.get("address3"));
        location.setCity((String)locationMap.get("city"));
        location.setZip_code((String)locationMap.get("zip_code"));
        location.setCountry((String) locationMap.get("country"));
        location.setState((String)locationMap.get("state"));
        List<String> stringList = (List<String>)locationMap.get("display_address");
        location.setDisplay_address(stringList);
        String phone = (String) locationMap.get("phone");
        String displayPhone = (String) locationMap.get("display_phone");
        String distance = (String) locationMap.get("distance");
        Business business = new Business( id, alias,  name, imageUrl, isClosed,  url,  reviewCount,rating, coordinate,
                price,  location,  phone,  displayPhone,distance, categories,  transactions);
        businesses.add(business);
    }

    public static void main(String[] args){
        ReadJson.read();
    }
}
