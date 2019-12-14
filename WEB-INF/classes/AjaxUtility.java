import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

public class AjaxUtility {
    StringBuffer sb = new StringBuffer();

    public StringBuffer readdata(ArrayList<Order> orders){
        Iterator it = orders.iterator();
        while (it.hasNext())
        {
           Order pi = (Order)it.next();
            if(pi!=null)
            {
                int orderId = pi.getOrderId();
                String orderName = pi.getOrderName();
                double orderPrice = pi.getOrderPrice();
                sb.append("<Order>");
                sb.append("<Id>" + orderId + "</Id>");
                sb.append("<Name>" + orderName + "</Name>");
                sb.append("<price>" +orderPrice + "</Price>");
                sb.append("</order>");
                }
            }
        return sb;
        }
}

