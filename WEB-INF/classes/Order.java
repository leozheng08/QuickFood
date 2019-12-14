public class Order {

    private int orderId;
    private String orderName;
    private double orderPrice;
    private String businessId;

    public Order(int orderId, String orderName, double orderPrice, String businessId) {
        this.orderId = orderId;
        this.orderName = orderName;
        this.orderPrice = orderPrice;
        this.businessId = businessId;
    }

    public String getBusinessId() {
        return businessId;
    }

    public void setBusinessId(String businessId) {
        this.businessId = businessId;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public String getOrderName() {
        return orderName;
    }

    public void setOrderName(String orderName) {
        this.orderName = orderName;
    }

    public double getOrderPrice() {
        return orderPrice;
    }

    public void setOrderPrice(double orderPrice) {
        this.orderPrice = orderPrice;
    }
}
