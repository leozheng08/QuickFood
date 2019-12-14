public class mainCourse {

    private String mainCourseName;
    private double price;
    private String url;

    public mainCourse(String mainCourseName, double price, String url) {
        this.mainCourseName = mainCourseName;
        this.price = price;
        this.url = url;
    }

    public String getMainCourseName() {
        return mainCourseName;
    }

    public void setMainCourseName(String mainCourseName) {
        this.mainCourseName = mainCourseName;
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
