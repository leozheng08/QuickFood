import java.util.List;

public class Menu {

    private String businessid;
    private List<Starter> starters;
    private List<mainCourse> mainCourses;
    private List<Dessert> desserts;
    private List<Drink> drinks;

    public Menu(String businessid, List<Starter> starters, List<mainCourse> mainCourses, List<Dessert> desserts, List<Drink> drink) {
        this.businessid = businessid;
        this.starters = starters;
        this.mainCourses = mainCourses;
        this.desserts = desserts;
        this.drinks = drink;
    }

    public String getBusinessid() {
        return businessid;
    }

    public void setBusinessid(String businessid) {
        this.businessid = businessid;
    }

    public List<Starter> getStarters() {
        return starters;
    }

    public void setStarters(List<Starter> starters) {
        this.starters = starters;
    }

    public List<mainCourse> getMainCourses() {
        return mainCourses;
    }

    public void setMainCourses(List<mainCourse> mainCourses) {
        this.mainCourses = mainCourses;
    }

    public List<Dessert> getDesserts() {
        return desserts;
    }

    public void setDesserts(List<Dessert> desserts) {
        this.desserts = desserts;
    }

    public List<Drink> getDrink() {
        return drinks;
    }

    public void setDrink(List<Drink> drinks) {
        this.drinks = drinks;
    }
}
