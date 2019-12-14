import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "Startup")
public class Startup extends HttpServlet {

    public void init() throws ServletException
    {
       ReadJson.read();
       MySqlDataStoreUtilities.InsertBussinesses();
       MySqlDataStoreUtilities.InsertMenu();


    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
