/**
 * @program: fastfood
 * @package: utils
 * @author: Luping
 * @create: 10/29/19 1:25 PM
 * api documentation : https://www.yelp.com/developers/documentation/v3/get_started
 */
public class APIConfig {

    public static final String HEADER_KEY = "Authorization";
    //    app key
    public static final String HEADER_VALUE = "Bearer 6oXefcXr_FLeNNjpKNPMkqGcLgT7Q3sc4Mkv_bndPLe8Wf4IUXN0pp2I4ffXEBvVAiaVgHbSaEqygIILr2llezOvBNllYefh2i4fvmV7Nj4GNivAzTe6w9XB_UW3XXYx";

    //   eg.  https://api.yelp.com/v3/businesses/search?term=restaurants&location=60616
    public static final String BUSINESS_SEARCH = "https://api.yelp.com/v3/businesses/search";

    public final static String PHONE_SEARCH = "https://api.yelp.com/v3/businesses/search/phone";

    //   eg. https://api.yelp.com/v3/transactions/delivery/search?latitude=37.786882&longitude=-122.399972
    public final static String TRANSACTION_SEARCH = "https://api.yelp.com/v3/transactions/delivery/search";

    //   eg. https://api.yelp.com/v3/businesses/0PPM0wxA2r8XVM8vbMm9Pw
    public final static String BUSINESS_DETAILS = "https://api.yelp.com/v3/businesses/";

    public final static String BUSINESS_MATCH = "https://api.yelp.com/v3/businesses/matches";

    //   eg. https://api.yelp.com/v3/businesses/0PPM0wxA2r8XVM8vbMm9Pw/reviews
    public final static String REVIEWS = "https://api.yelp.com/v3/businesses/";

    public final static String AUTOCOMPLETE = "https://api.yelp.com/v3/autocomplete";

}

