public class Drink {

    private String drinkName;
    private Double price;
    private String url;

    public Drink(String drinkName, Double price, String url) {
        this.drinkName = drinkName;
        this.price = price;
        this.url = url;
    }

    public String getDrinkName() {
        return drinkName;
    }

    public void setDrinkName(String drinkName) {
        this.drinkName = drinkName;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
