import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

@WebServlet(name = "AutoComplete")
public class AutoComplete extends HttpServlet {

    private ServletContext context;


    @Override
    public void init(ServletConfig config) throws ServletException {
        this.context = config.getServletContext();

    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            PrintWriter out = response.getWriter();
            Utilities utility = new Utilities(request,out);
            String action = request.getParameter("action");
            StringBuffer sb = new StringBuffer();
            boolean namesAdded = false;
            if (action.equals("complete")){
                ArrayList<Order> orders = utility.getCustomerOrders();
                AjaxUtility a=new AjaxUtility();
                sb=a.readdata(orders);
                if(sb!=null || !sb.equals(""))
                {
                    namesAdded=true;
                }
                if (namesAdded)
                {
                    response.setContentType("text/xml");
                    response.getWriter().write("<Orders>" + sb.toString() + "</Orders>");
                }
                else
                {
                    //nothing to show
                    response.setStatus(HttpServletResponse.SC_NO_CONTENT);
                }
            }
        }
        catch(Exception e){

        }
    }
}
