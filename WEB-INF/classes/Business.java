import java.util.List;

/**
 * @program: QuickFood
 * @package: servlets
 * @author: Luping
 * @create: 11/1/19 8:26 PM
 */
public class Business {

    /**
     * id : Bxd9Y2PsdvIOGIKFQh53RQ
     * alias : giordanos-chicago-30
     * name : Giordano's
     * image_url : https://s3-media1.fl.yelpcdn.com/bphoto/OKxuR9ZqBNY-4wvP0Z0SPQ/o.jpg
     * is_closed : false
     * url : https://www.yelp.com/biz/giordanos-chicago-30?adjust_creative=9spWyoMs-VuIP5dHIaCVCQ&utm_campaign=yelp_api_v3&utm_medium=api_v3_business_search&utm_source=9spWyoMs-VuIP5dHIaCVCQ
     * review_count : 2574
     * categories : [{"alias":"pizza","title":"Pizza"},{"alias":"salad","title":"Salad"},{"alias":"italian","title":"Italian"}]
     * rating : 4
     * coordinates : {"latitude":41.885165,"longitude":-87.623753}
     * transactions : []
     * price : $$
     * location : {"address1":"130 E Randolph","address2":"","address3":"","city":"Chicago","zip_code":"60601","country":"US","state":"IL","display_address":["130 E Randolph","Chicago, IL 60601"]}
     * phone : +13126161200
     * display_phone : (312) 616-1200
     * distance : 39.737245241967024
     */

    private String id;
    private String alias;
    private String name;
    private String image_url;
    private boolean is_closed;
    private String url;
    private long review_count;
    private double rating;
    private Coordinate coordinate;
    private String price;
    private Location location;
    private String phone;
    private String display_phone;
    private String distance;
    private List<Category> categories;
    private List<String> transactions;

    public Business(String id, String alias, String name, String imageUrl,boolean isClosed, String url, long review_count, double rating, Coordinate coordinate,
                    String price, Location location, String phone, String display_phone,String distance, List<Category> categories, List<String> transactions){

        this.id = id;
        this.alias = alias;
        this.name = name;
        this.image_url = imageUrl;
        this.is_closed = isClosed;
        this.url = url;
        this.review_count = review_count;
        this.rating = rating;
        this.coordinate = coordinate;
        this.price = price;
        this.location = location;
        this.phone = phone;
        this.display_phone = display_phone;
        this.distance = distance;
        this.categories = categories;
        this.transactions = transactions;

    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public boolean isIs_closed() {
        return is_closed;
    }

    public void setIs_closed(boolean is_closed) {
        this.is_closed = is_closed;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public long getReview_count() {
        return review_count;
    }

    public void setReview_count(long review_count) {
        this.review_count = review_count;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public Coordinate getCoordinates() {
        return coordinate;
    }

    public void setCoordinates(Coordinate coordinate) {
        this.coordinate = coordinate;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getDisplay_phone() {
        return display_phone;
    }

    public void setDisplay_phone(String display_phone) {
        this.display_phone = display_phone;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    public List<String> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<String> transactions) {
        this.transactions = transactions;
    }

    @Override
    public String toString() {
        return "Business{" +
                "id='" + id + '\'' +
                ", alias='" + alias + '\'' +
                ", name='" + name + '\'' +
                ", image_url='" + image_url + '\'' +
                ", is_closed=" + is_closed +
                ", url='" + url + '\'' +
                ", review_count=" + review_count +
                ", rating=" + rating +
                ", coordinates=" + coordinate +
                ", price='" + price + '\'' +
                ", location=" + location +
                ", phone='" + phone + '\'' +
                ", display_phone='" + display_phone + '\'' +
                ", distance=" + distance +
                ", categories=" + categories +
                ", transactions=" + transactions +
                '}';
    }
}
