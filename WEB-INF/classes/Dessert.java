public class Dessert {

    private String dessertName;
    private double price;
    private String url;

    public Dessert(String dessertName, double price, String url) {
        this.dessertName = dessertName;
        this.price = price;
        this.url = url;
    }

    public String getDessertName() {
        return dessertName;
    }

    public void setDessertName(String dessertName) {
        this.dessertName = dessertName;
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
