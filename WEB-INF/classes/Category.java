/**
 * @program: QuickFood
 * @package: servlets
 * @author: Luping
 * @create: 11/1/19 8:26 PM
 */
public class Category {

    /**
     * alias : pizza
     * title : Pizza
     */

    private String alias;
    private String title;

    public Category(String alias,String title){
        this.alias = alias;
        this.title = title;
    }
    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "{" +
                "alias='" + alias + '\'' +
                ", title='" + title + '\'' +
                '}';
    }
}
