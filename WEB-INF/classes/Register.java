

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

@WebServlet("/Register")
public class Register extends HttpServlet {
    private String errorMsg=null;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        Utilities utility = new Utilities(request, out);

        String firstName = request.getParameter("First Name");
        String lastName = request.getParameter("Last Name");
        String emailAddress = request.getParameter("emailAddress");
        String password = request.getParameter("password");
        String repassword = request.getParameter("repassword");
        String userName= firstName+","+lastName;
        System.out.println(userName);
        HashMap<String, User> map = new HashMap<>();
        if(!password.equals(repassword)) {
            errorMsg = "Passwords doesn't match!";
        } else{
            String message = MySqlDataStoreUtilities.getConnection();
            if(message.equals("Successfull")) {
                try {
                    map = MySqlDataStoreUtilities.selectUser();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                if (map.containsKey(userName)) {
                    errorMsg = "Username already exist ";
                } else {
                    User user = new User(userName, password);
                    map.put(userName, user);
                    try {
                        MySqlDataStoreUtilities.insertUser(userName, password, repassword, emailAddress);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    HttpSession session = request.getSession(true);
                    session.setAttribute("loginMsg", userName + "'s account has been created. Please login");
                    response.sendRedirect("HomePage");
                    return;
                }
            }
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
