import org.codehaus.jackson.annotate.JsonIgnore;

public class Geometry {
    private Locationtype location ;

    private String location_type;

    @JsonIgnore
    private Object bounds;

    @JsonIgnore

    private Object viewport;

    public Locationtype getLocation() {
        return location;
    }

    public void setLocation(Locationtype location) {
        this.location = location;
    }

    public String getLocation_type() {
        return location_type;
    }

    public void setLocation_type(String location_type) {
        this.location_type = location_type;
    }

    public Object getBounds() {
        return bounds;
    }

    public void setBounds(Object bounds) {
        this.bounds = bounds;
    }

    public Object getViewport() {
        return viewport;
    }

    public void setViewport(Object viewport) {
        this.viewport = viewport;
    }
}
