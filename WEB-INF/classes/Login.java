import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

@WebServlet("/Login")
public class Login extends HttpServlet {
    private String errorMsg=null;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        HashMap<String, User> map=new HashMap<String, User>();
        try{
            map = MySqlDataStoreUtilities.selectUser();
        } catch (Exception e){
            e.printStackTrace();
        }

        User tempUser = map.get(username);
        if(tempUser==null){
            errorMsg = "this beans.User is not exist!";
        } else{
            String userPassword = tempUser.getPassword();
            if(!password.equals(userPassword)){
                errorMsg="the password is not correct";
            } else{
                HttpSession session = request.getSession(true);
                session.setAttribute("username", tempUser.getName());
                response.sendRedirect("HomePage");
                return;
            }
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
