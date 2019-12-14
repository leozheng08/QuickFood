/**
 * @program: QuickFood
 * @package: servlets
 * @author: Luping
 * @create: 11/1/19 8:26 PM
 */
public class Coordinate {

    /**
     * latitude : 41.885165
     * longitude : -87.623753
     */
    private double latitude;
    private double longitude;

    public Coordinate(double latitude, double longitude){
        this.latitude = latitude;
        this.longitude = longitude;
    }


    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }
}
