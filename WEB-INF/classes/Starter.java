public class Starter {

    private String starterName;
    private double price;
    private String url;

    public Starter(String starterName, double price, String url) {
        this.starterName = starterName;
        this.price = price;
        this.url = url;
    }

    public String getStarterName() {
        return starterName;
    }

    public void setStarterName(String starterName) {
        this.starterName = starterName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
