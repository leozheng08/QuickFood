import java.util.ArrayList;
import java.util.List;

/**
 * @program: QuickFood
 * @package: beans
 * @author: Luping
 * @create: 11/1/19 8:33 PM
 */
public class RestaurantResult {
    private List<Business> businesses;
    private int total;
    private Region region;

    public List<Business> getBusinesses() {
        return businesses;
    }

    public void setBusinesses(ArrayList<Business> businesses) {
        this.businesses = businesses;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public Region getRegion() {
        return region;
    }

    public void setRegion(Region region) {
        this.region = region;
    }

    @Override
    public String toString() {
        return "RestaurantResult{" +
                "businesses=" + businesses +
                ", total=" + total +
                ", region=" + region +
                '}';
    }
}
