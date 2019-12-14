import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

@WebServlet( "/CheckOut")
public class CheckOut extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            String nameCardOrder = request.getParameter("name_card_order");
            String cardNumber = request.getParameter("card_number");
            String expireMonth = request.getParameter("expire_month");
            String expireYear = request.getParameter("expire_year");
            String CCV = request.getParameter("ccv");
            String address = request.getParameter("address");
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        Utilities utility = new Utilities(request, out);
        if(!utility.isLoggedin())
        {
            HttpSession session = request.getSession(true);
            session.setAttribute("login_msg", "Please Login to Pay");
            response.sendRedirect("Login");
            return;
        }
        if(!nameCardOrder.isEmpty() && !cardNumber.isEmpty() && !expireMonth.isEmpty()&&!expireYear.isEmpty()&&!CCV.isEmpty()){
            int orderId = 0;
            try {
                orderId = utility.getOrderPaymentSize()+1;
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            for (Order oi : utility.getCustomerOrders()){
                utility.storePayment(orderId,oi.getOrderName(),oi.getOrderPrice(),nameCardOrder,cardNumber,expireMonth,expireYear,CCV,address,oi.getBusinessId());
            }
            utility.printHtml("homePageHeader.html");
            out.print("<!-- SubHeader =============================================== -->\n" +
                    "<section class=\"parallax-window\" id=\"short\" data-parallax=\"scroll\" data-image-src=\"img/sub_header_cart.jpg\" data-natural-width=\"1400\" data-natural-height=\"350\">\n" +
                    "    <div id=\"subheader\">\n" +
                    "    \t<div id=\"sub_content\">\n" +
                    "    \t <h1>Place your order</h1>\n" +
                    "            <div class=\"bs-wizard\">\n" +
                    "                <div class=\"col-xs-4 bs-wizard-step complete\">\n" +
                    "                  <div class=\"text-center bs-wizard-stepnum\"><strong>1.</strong> Your details</div>\n" +
                    "                  <div class=\"progress\"><div class=\"progress-bar\"></div></div>\n" +
                    "                  <a href=\"cart.html\" class=\"bs-wizard-dot\"></a>\n" +
                    "                </div>\n" +
                    "                               \n" +
                    "                <div class=\"col-xs-4 bs-wizard-step complete\">\n" +
                    "                  <div class=\"text-center bs-wizard-stepnum\"><strong>2.</strong> Payment</div>\n" +
                    "                  <div class=\"progress\"><div class=\"progress-bar\"></div></div>\n" +
                    "                  <a href=\"cart_2.html\" class=\"bs-wizard-dot\"></a>\n" +
                    "                </div>\n" +
                    "            \n" +
                    "              <div class=\"col-xs-4 bs-wizard-step complete\">\n" +
                    "                  <div class=\"text-center bs-wizard-stepnum\"><strong>3.</strong> Finish!</div>\n" +
                    "                  <div class=\"progress\"><div class=\"progress-bar\"></div></div>\n" +
                    "                  <a href=\"#0\" class=\"bs-wizard-dot\"></a>\n" +
                    "                </div>  \n" +
                    "\t\t</div><!-- End bs-wizard --> \n" +
                    "        </div><!-- End sub_content -->\n" +
                    "\t</div><!-- End subheader -->\n" +
                    "</section><!-- End section -->\n" +
                    "<!-- End SubHeader ============================================ -->");

            out.print(" <div id=\"position\">\n" +
                    "        <div class=\"container\">\n" +
                    "            <ul>\n" +
                    "                <li><a href=\"#0\">Home</a></li>\n" +
                    "                <li><a href=\"#0\">Category</a></li>\n" +
                    "                <li>Page active</li>\n" +
                    "            </ul>\n" +
                    "            <a href=\"#0\" class=\"search-overlay-menu-btn\"><i class=\"icon-search-6\"></i> Search</a>\n" +
                    "        </div>\n" +
                    "    </div><!-- Position -->");

            out.print("!-- Content ================================================== -->\n" +
                    "<div class=\"container margin_60_35\">\n" +
                    "\t<div class=\"row\">\n" +
                    "\t\t<div class=\"col-md-offset-3 col-md-6\">\n" +
                    "\t\t\t<div class=\"box_style_2\">\n" +
                    "\t\t\t\t<h2 class=\"inner\">Order confirmed!</h2>\n" +
                    "\t\t\t\t<div id=\"confirm\">\n" +
                    "\t\t\t\t\t<i class=\"icon_check_alt2\"></i>\n" +
                    "\t\t\t\t\t<h3>Thank you!</h3>\n" +
                    "\t\t\t\t\t<p>\n" +

                    "\t\t\t\t\t</p>\n" +
                    "\t\t\t\t</div>\n" +
                    "\t\t\t\t<h4>Summary</h4>\n" +

                    "\t\t\t\t<table class=\"table table-striped nomargin\">\n" +
                    "\t\t\t\t<tbody>\n" );
            ArrayList<Order> orders = utility.getCustomerOrders();
            for(int i=0;i<orders.size();i++) {
               out.print( "\t\t\t\t<tr>\n" +
                        "\t\t\t\t\t<td>\n" +
                        "\t\t\t\t\t\t<strong>"+orders.get(i).getOrderName()+"</strong> " +
                        "\t\t\t\t\t</td>\n" +
                        "\t\t\t\t\t<td>\n" +
                        "\t\t\t\t\t\t<strong class=\"pull-right\">$"+Math.round(orders.get(i).getOrderPrice()*100)/100.0+"</strong>\n" +
                        "\t\t\t\t\t</td>\n" +
                        "\t\t\t\t</tr>\n" );
            }
            double subTotal=0.0;
            for(int i=0;i<orders.size();i++){
                subTotal = subTotal+orders.get(i).getOrderPrice();
            }
            double Total = subTotal+10;
            out.print(
                    "\t\t\t\t<tr>\n" +
                    "\t\t\t\t\t<td>\n" +
                    "\t\t\t\t\t\t Delivery schedule <a href='Map?originId="+orders.get(0).getBusinessId()+"&destination="+address+"' class=\"tooltip-1\" data-placement=\"top\" title=\"\" data-original-title=\"Please consider 30 minutes of margin for the delivery!\"><i class=\"icon_question_alt\"></i></a>\n" +
                    "\t\t\t\t\t</td>\n" +
                    "\t\t\t\t\t<td>\n" +
                    "\t\t\t\t\t\t<strong class=\"pull-right\">Today 07.30 pm</strong>\n" +
                    "\t\t\t\t\t</td>\n" +
                    "\t\t\t\t</tr>\n" +
                    "\t\t\t\t<tr>\n" +
                    "\t\t\t\t\t<td class=\"total_confirm\">\n" +
                    "\t\t\t\t\t\t TOTAL\n" +
                    "\t\t\t\t\t</td>\n" +
                    "\t\t\t\t\t<td class=\"total_confirm\">\n" +
                    "\t\t\t\t\t\t<span class=\"pull-right\">$"+Total+"</span>\n" +
                    "\t\t\t\t\t</td>\n" +
                    "\t\t\t\t</tr>\n" +
                    "\t\t\t\t</tbody>\n" +
                    "\t\t\t\t</table>\n" +

                    "\t\t\t</div>\n" +
                    "\t\t</div>\n" +
                    "\t</div><!-- End row -->\n" +
                    "</div><!-- End container -->\n" +
                    "<!-- End Content =============================================== -->");
        }
        out.print("<!-- Footer ================================================== -->\n" +
                "\t<footer>\n" +
                "        <div class=\"container\">\n" +
                "            <div class=\"row\">\n" +
                "                <div class=\"col-md-4 col-sm-3\">\n" +
                "                  \t<h3>Secure payments with</h3>\n" +
                "                    <p><img src=\"img/cards.png\" alt=\"\" class=\"img-responsive\"></p>\n" +
                "                    \n" +
                "                </div>\n" +
                "                <div class=\"col-md-3 col-sm-3\">\n" +
                "                    <h3>About</h3>\n" +
                "                    <ul>\n" +
                "                        <li><a href=\"about.html\">About us</a></li>\n" +
                "                        <li><a href=\"faq.html\">Faq</a></li>\n" +
                "                         <li><a href=\"contacts.html\">Contacts</a></li>\n" +
                "                        <li><a href=\"#0\" data-toggle=\"modal\" data-target=\"#login_2\">Login</a></li>\n" +
                "                        <li><a href=\"#0\" data-toggle=\"modal\" data-target=\"#register\">Register</a></li>\n" +
                "                        <li><a href=\"#0\">Terms and conditions</a></li>\n" +
                "                    </ul>\n" +
                "                </div>\n" +
                "                <div class=\"col-md-3 col-sm-3\"  id=\"newsletter\">\n" +
                "                    <h3>Newsletter</h3>\n" +
                "                    <p>Join our newsletter to keep be informed about offers and news.</p>\n" +
                "\t\t\t\t\t<div id=\"message-newsletter_2\"></div>\n" +
                "\t\t\t\t\t\t<form method=\"post\" action=\"assets/newsletter.php\" name=\"newsletter_2\" id=\"newsletter_2\">\n" +
                "                        <div class=\"form-group\">\n" +
                "                            <input name=\"email_newsletter_2\" id=\"email_newsletter_2\"  type=\"email\" value=\"\"  placeholder=\"Your mail\" class=\"form-control\">\n" +
                "                          </div>\n" +
                "                            <input type=\"submit\" value=\"Subscribe\" class=\"btn_1\" id=\"submit-newsletter_2\">\n" +
                "                    \t</form>\n" +
                "                </div>\n" +
                "                <div class=\"col-md-2 col-sm-3\">\n" +
                "                    <h3>Settings</h3>\n" +
                "                    <div class=\"styled-select\">\n" +
                "                        <select class=\"form-control\" name=\"lang\" id=\"lang\">\n" +
                "                            <option value=\"English\" selected>English</option>\n" +
                "                            <option value=\"French\">French</option>\n" +
                "                            <option value=\"Spanish\">Spanish</option>\n" +
                "                            <option value=\"Russian\">Russian</option>\n" +
                "                        </select>\n" +
                "                    </div>\n" +
                "                    <div class=\"styled-select\">\n" +
                "                        <select class=\"form-control\" name=\"currency\" id=\"currency\">\n" +
                "                            <option value=\"USD\" selected>USD</option>\n" +
                "                            <option value=\"EUR\">EUR</option>\n" +
                "                            <option value=\"GBP\">GBP</option>\n" +
                "                            <option value=\"RUB\">RUB</option>\n" +
                "                        </select>\n" +
                "                    </div>\n" +
                "                </div>\n" +
                "            </div><!-- End row -->\n" +
                "            <div class=\"row\">\n" +
                "                <div class=\"col-md-12\">\n" +
                "                    <div id=\"social_footer\">\n" +
                "                        <ul>\n" +
                "                            <li><a href=\"#0\"><i class=\"icon-facebook\"></i></a></li>\n" +
                "                            <li><a href=\"#0\"><i class=\"icon-twitter\"></i></a></li>\n" +
                "                            <li><a href=\"#0\"><i class=\"icon-google\"></i></a></li>\n" +
                "                            <li><a href=\"#0\"><i class=\"icon-instagram\"></i></a></li>\n" +
                "                            <li><a href=\"#0\"><i class=\"icon-pinterest\"></i></a></li>\n" +
                "                            <li><a href=\"#0\"><i class=\"icon-vimeo\"></i></a></li>\n" +
                "                            <li><a href=\"#0\"><i class=\"icon-youtube-play\"></i></a></li>\n" +
                "                        </ul>\n" +
                "                        <p>© Quick Food 2015</p>\n" +
                "                    </div>\n" +
                "                </div>\n" +
                "            </div><!-- End row -->\n" +
                "        </div><!-- End container -->\n" +
                "    </footer>\n" +
                "<!-- End Footer =============================================== -->\n" +
                "\n" +
                "<div class=\"layer\"></div><!-- Mobile menu overlay mask -->\n" +
                "\n" +
                "<!-- Login modal -->   \n" +
                "<div class=\"modal fade\" id=\"login_2\" tabindex=\"-1\" role=\"dialog\" aria-labelledby=\"myLogin\" aria-hidden=\"true\">\n" +
                "\t\t<div class=\"modal-dialog\">\n" +
                "\t\t\t<div class=\"modal-content modal-popup\">\n" +
                "\t\t\t\t<a href=\"#\" class=\"close-link\"><i class=\"icon_close_alt2\"></i></a>\n" +
                "\t\t\t\t<form action=\"#\" class=\"popup-form\" id=\"myLogin\">\n" +
                "                \t<div class=\"login_icon\"><i class=\"icon_lock_alt\"></i></div>\n" +
                "\t\t\t\t\t<input type=\"text\" class=\"form-control form-white\" placeholder=\"Username\">\n" +
                "\t\t\t\t\t<input type=\"text\" class=\"form-control form-white\" placeholder=\"Password\">\n" +
                "\t\t\t\t\t<div class=\"text-left\">\n" +
                "\t\t\t\t\t\t<a href=\"#\">Forgot Password?</a>\n" +
                "\t\t\t\t\t</div>\n" +
                "\t\t\t\t\t<button type=\"submit\" class=\"btn btn-submit\">Submit</button>\n" +
                "\t\t\t\t</form>\n" +
                "\t\t\t</div>\n" +
                "\t\t</div>\n" +
                "\t</div><!-- End modal -->   \n" +
                "    \n" +
                "<!-- Register modal -->   \n" +
                "<div class=\"modal fade\" id=\"register\" tabindex=\"-1\" role=\"dialog\" aria-labelledby=\"myRegister\" aria-hidden=\"true\">\n" +
                "\t\t<div class=\"modal-dialog\">\n" +
                "\t\t\t<div class=\"modal-content modal-popup\">\n" +
                "\t\t\t\t<a href=\"#\" class=\"close-link\"><i class=\"icon_close_alt2\"></i></a>\n" +
                "\t\t\t\t<form action=\"#\" class=\"popup-form\" id=\"myRegister\">\n" +
                "                \t<div class=\"login_icon\"><i class=\"icon_lock_alt\"></i></div>\n" +
                "\t\t\t\t\t<input type=\"text\" class=\"form-control form-white\" placeholder=\"Name\">\n" +
                "\t\t\t\t\t<input type=\"text\" class=\"form-control form-white\" placeholder=\"Last Name\">\n" +
                "                    <input type=\"email\" class=\"form-control form-white\" placeholder=\"Email\">\n" +
                "                    <input type=\"text\" class=\"form-control form-white\" placeholder=\"Password\"  id=\"password1\">\n" +
                "                    <input type=\"text\" class=\"form-control form-white\" placeholder=\"Confirm password\"  id=\"password2\">\n" +
                "                    <div id=\"pass-info\" class=\"clearfix\"></div>\n" +
                "\t\t\t\t\t<div class=\"checkbox-holder text-left\">\n" +
                "\t\t\t\t\t\t<div class=\"checkbox\">\n" +
                "\t\t\t\t\t\t\t<input type=\"checkbox\" value=\"accept_2\" id=\"check_2\" name=\"check_2\" />\n" +
                "\t\t\t\t\t\t\t<label for=\"check_2\"><span>I Agree to the <strong>Terms &amp; Conditions</strong></span></label>\n" +
                "\t\t\t\t\t\t</div>\n" +
                "\t\t\t\t\t</div>\n" +
                "\t\t\t\t\t<button type=\"submit\" class=\"btn btn-submit\">Register</button>\n" +
                "\t\t\t\t</form>\n" +
                "\t\t\t</div>\n" +
                "\t\t</div>\n" +
                "\t</div><!-- End Register modal -->\n" +
                "    \n" +
                "     <!-- Search Menu -->\n" +
                "\t<div class=\"search-overlay-menu\">\n" +
                "\t\t<span class=\"search-overlay-close\"><i class=\"icon_close\"></i></span>\n" +
                "\t\t<form role=\"search\" id=\"searchform\" method=\"get\">\n" +
                "\t\t\t<input value=\"\" name=\"q\" type=\"search\" placeholder=\"Search...\" />\n" +
                "\t\t\t<button type=\"submit\"><i class=\"icon-search-6\"></i>\n" +
                "\t\t\t</button>\n" +
                "\t\t</form>\n" +
                "\t</div>\n" +
                "\t<!-- End Search Menu -->\n" +
                "    \n" +
                "<!-- COMMON SCRIPTS -->\n" +
                "<script src=\"js/jquery-2.2.4.min.js\"></script>\n" +
                "<script src=\"js/common_scripts_min.js\"></script>\n" +
                "<script src=\"js/functions.js\"></script>\n" +
                "<script src=\"assets/validate.js\"></script>\n" +
                "\n" +
                "</body>\n" +
                "</html>");
    }
}
