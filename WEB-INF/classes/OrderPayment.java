import java.util.Date;

public class OrderPayment {
    private int orderId;
    private String userName;
    private String orderName;
    private double orderPrice;
    private String nameCardOrder;
    private String cardNumber;
    private String expireMonth;
    private String expireYear;
    private String CCV;
    private String address;
    private String businessId;

    public OrderPayment(int orderId, String userName, String orderName, double orderPrice, String nameCardOrder, String cardNumber, String expireMonth, String expireYear, String CCV,String address,String businessId) {
        this.orderId = orderId;
        this.userName = userName;
        this.orderName = orderName;
        this.orderPrice = orderPrice;
        this.nameCardOrder = nameCardOrder;
        this.cardNumber = cardNumber;
        this.expireMonth = expireMonth;
        this.expireYear = expireYear;
        this.CCV = CCV;
        this.address= address;
        this.businessId = businessId;
    }

    public String getBusinessId() {
        return businessId;
    }

    public void setBusinessId(String businessId) {
        this.businessId = businessId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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

    public String getNameCardOrder() {
        return nameCardOrder;
    }

    public void setNameCardOrder(String nameCardOrder) {
        this.nameCardOrder = nameCardOrder;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getExpireMonth() {
        return expireMonth;
    }

    public void setExpireMonth(String expireMonth) {
        this.expireMonth = expireMonth;
    }

    public String getExpireYear() {
        return expireYear;
    }

    public void setExpireYear(String expireYear) {
        this.expireYear = expireYear;
    }

    public String getCCV() {
        return CCV;
    }

    public void setCCV(String CCV) {
        this.CCV = CCV;
    }
}
