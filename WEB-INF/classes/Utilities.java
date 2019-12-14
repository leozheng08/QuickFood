import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Utilities extends HttpServlet {
    HttpServletRequest request;
    PrintWriter out;
    String url;
    HttpSession session;

    public Utilities(HttpServletRequest request, PrintWriter out){
        this.request = request;
        this.out = out;
        this.url = this.getFullUrl();
        this.session = this.request.getSession(true);
    }


    public void printHtml(String file) throws IOException {
        String result = HtmlToString(file);
        if (file == "homePageHeader.html"||file=="MapHeader.html"){
            if(session.getAttribute("username")!=null){
                String username = (String)session.getAttribute("username");
                result = result+"<li><a href='Logout'>Hello,"+username+"</a></li>";
            } else{
                result = result + "<li><a href=\"#0\" data-toggle=\"modal\" data-target=\"#login_2\">Login</a></li>";
            }
            result = result+"</ul>\n" +
                    "</div><!-- End main-menu -->\n" +
                    "</nav>\n" +
                    "</div><!-- End row -->\n" +
                    "</div><!-- End container -->\n" +
                    "</header>\n" +
                    "<!-- End Header =============================================== -->";
        }
        out.print(result);
    }

    private String getFullUrl() {
        String scheme = request.getScheme();
        String serverName = request.getServerName();
        int serverPort = request.getServerPort();
        String contextPath = request.getContextPath();
        StringBuffer url = new StringBuffer();
        url.append(scheme).append("://").append(serverName);

        if ((serverPort != 80) && (serverPort != 443)) {
            url.append(":").append(serverPort);
        }
        url.append(contextPath);
        url.append("/");
        return url.toString();
    }

    private String HtmlToString(String file) throws IOException {
        String result = null;
        String webPage = url + file;
        URL url = new URL(webPage);
        URLConnection urlConnection = url.openConnection();
        InputStream is = urlConnection.getInputStream();
        InputStreamReader isr = new InputStreamReader(is);

        int numCharsRead;
        char[] charArray = new char[1024];
        StringBuffer sb = new StringBuffer();
        while ((numCharsRead = isr.read(charArray)) > 0) {
            sb.append(charArray, 0, numCharsRead);
        }
        result = sb.toString();
        return result;
    }

    public boolean isLoggedin() {
        if (session.getAttribute("username")==null)
            return false;
        return true;
    }

    public String username(){
        if(session.getAttribute("username")!=null) {
            return session.getAttribute("username").toString();
        }
        else
            return null;
    }

    public void storeOrder(String userName, String orderName, double orderPrice,String id) {
        if(!OrderHashMap.orders.containsKey(userName)){
            ArrayList<Order> arr = new ArrayList<Order>();
            OrderHashMap.orders.put(username(),arr);
        }
        ArrayList<Order> orderItems = OrderHashMap.orders.get(userName);
        Order order = new Order(orderItems.size()+1,orderName,orderPrice,id);
        orderItems.add(order);
    }

    public ArrayList<Order> getCustomerOrders() {
        ArrayList<Order> order = new ArrayList<Order>();
        if(OrderHashMap.orders.containsKey(username())){
            order= OrderHashMap.orders.get(username());
        }
        return order;
    }

    public void logout() {
        session.removeAttribute("username");
    }

    public int getOrderPaymentSize() throws IOException, ClassNotFoundException {
        HashMap<Integer, ArrayList<OrderPayment>> orderPayments = new HashMap<Integer, ArrayList<OrderPayment>>();
        try
        {
            orderPayments =MySqlDataStoreUtilities.selectOrder();

        }
        catch(Exception e)
        {

        }
        int size=0;
        for(Map.Entry<Integer, ArrayList<OrderPayment>> entry : orderPayments.entrySet()){
            size=size + 1;

        }
        return size;
    }

    public void storePayment(int orderId, String orderName, double orderPrice, String nameCardOrder,String cardNumber,String expireMonth, String expireYear,String CCV, String address,String businessId) {
        HashMap<Integer, ArrayList<OrderPayment>> orderPayments = new HashMap<Integer, ArrayList<OrderPayment>>();
        // get the payment details file
        try {
            orderPayments = MySqlDataStoreUtilities.selectOrder();
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (orderPayments == null) {
            orderPayments = new HashMap<Integer, ArrayList<OrderPayment>>();
        }
        if (!orderPayments.containsKey(orderId)) {
            ArrayList<OrderPayment> arr = new ArrayList<OrderPayment>();
            orderPayments.put(orderId, arr);
        }
        ArrayList<OrderPayment> listOrderPayment = orderPayments.get(orderId);
        OrderPayment orderpayment = new OrderPayment(orderId, username(), orderName, orderPrice, nameCardOrder, cardNumber,expireMonth, expireYear, CCV, address,businessId);
        listOrderPayment.add(orderpayment);

        try {
            MySqlDataStoreUtilities.insertOrder(orderId, username(), orderName, orderPrice, nameCardOrder, cardNumber,expireMonth, expireYear, CCV, address, businessId);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
