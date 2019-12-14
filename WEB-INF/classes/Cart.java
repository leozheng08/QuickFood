import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/Cart")
public class Cart extends HttpServlet {

    public static ArrayList<String> orderNames = new ArrayList<>();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        Utilities utility = new Utilities(request,out);
//        if(!utility.isLoggedin()){
//            HttpSession session = request.getSession(true);
//            session.setAttribute("login_msg", "Please Login to add items to cart");
//            response.sendRedirect("HomePage");
//            return;
//        }
        String userName= utility.username();
        String orderName = request.getParameter("OrderName");
        double orderPrice = Double.parseDouble(request.getParameter("OrderPrice"));
        String id = request.getParameter("Id");
        utility.storeOrder(userName,orderName,orderPrice,id);
        System.out.println(orderName+":"+orderPrice+","+id);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        Utilities utility = new Utilities(request,out);
        ArrayList<Order> orders = utility.getCustomerOrders();
        utility.printHtml("homePageHeader.html");
        out.println("<!-- SubHeader =============================================== -->\n" +
                "<section class=\"parallax-window\"  id=\"short\"  data-parallax=\"scroll\" data-image-src=\"img/sub_header_cart.jpg\" data-natural-width=\"1400\" data-natural-height=\"350\">\n" +
                "    <div id=\"subheader\">\n" +
                "        <div id=\"sub_content\">\n" +
                "            <h1>Place your order</h1>\n" +
                "            <div class=\"bs-wizard\">\n" +
                "                <div class=\"col-xs-4 bs-wizard-step complete\">\n" +
                "                    <div class=\"text-center bs-wizard-stepnum\"><strong>1.</strong> Your details</div>\n" +
                "                    <div class=\"progress\"><div class=\"progress-bar\"></div></div>\n" +
                "                    <a href=\"cart.html\" class=\"bs-wizard-dot\"></a>\n" +
                "                </div>\n" +
                "\n" +
                "                <div class=\"col-xs-4 bs-wizard-step active\">\n" +
                "                    <div class=\"text-center bs-wizard-stepnum\"><strong>2.</strong> Payment</div>\n" +
                "                    <div class=\"progress\"><div class=\"progress-bar\"></div></div>\n" +
                "                    <a href=\"#0\" class=\"bs-wizard-dot\"></a>\n" +
                "                </div>\n" +
                "\n" +
                "                <div class=\"col-xs-4 bs-wizard-step disabled\">\n" +
                "                    <div class=\"text-center bs-wizard-stepnum\"><strong>3.</strong> Finish!</div>\n" +
                "                    <div class=\"progress\"><div class=\"progress-bar\"></div></div>\n" +
                "                    <a href=\"cart_3.html\" class=\"bs-wizard-dot\"></a>\n" +
                "                </div>\n" +
                "            </div><!-- End bs-wizard -->\n" +
                "        </div><!-- End sub_content -->\n" +
                "    </div><!-- End subheader -->\n" +
                "</section><!-- End section -->\n" +
                "<!-- End SubHeader ============================================ -->\n");

        out.println("<div id=\"position\">\n" +
                "    <div class=\"container\">\n" +
                "        <ul>\n" +
                "            <li><a href=\"#0\">Home</a></li>\n" +
                "            <li><a href=\"#0\">Category</a></li>\n" +
                "            <li>Page active</li>\n" +
                "        </ul>\n" +
                "        <a href=\"#0\" class=\"search-overlay-menu-btn\"><i class=\"icon-search-6\"></i> Search</a>\n" +
                "    </div>\n" +
                "</div><!-- Position -->");

        out.println("<!-- Content ================================================== -->\n" +
                "<div class=\"container margin_60_35\">\n" +
                "    <div class=\"row\">\n" +
                "        <div class=\"col-md-3\">\n" +
                "            <div class=\"box_style_2 hidden-xs info\">\n" +
                "                <h4 class=\"nomargin_top\">Delivery time <i class=\"icon_clock_alt pull-right\"></i></h4>\n" +
                "                <p>\n" +

                "                </p>\n" +
                "                <hr>\n" +
                "                <h4>Secure payment <i class=\"icon_creditcard pull-right\"></i></h4>\n" +
                "                <p>\n" +

                "                </p>\n" +
                "            </div><!-- End box_style_2 -->\n" +
                "\n" +
                "            <div class=\"box_style_2 hidden-xs\" id=\"help\">\n" +
                "                <i class=\"icon_lifesaver\"></i>\n" +
                "                <h4>Need <span>Help?</span></h4>\n" +
//                "                <a href=\"tel://004542344599\" class=\"phone\">+45 423 445 99</a>\n" +
                "                <small>Monday to Friday 9.00am - 7.30pm</small>\n" +
                "            </div>\n" +
                "        </div><!-- End col-md-3 -->\n" +
                "\n" +
                "        <div class=\"col-md-6\">\n" +
                "            <div class=\"box_style_2\">\n" +
                "                <h2 class=\"inner\">Payment methods</h2>\n" +
                "                <div class=\"payment_select\">\n" +
                "                    <label><input type=\"radio\" value=\"\" checked name=\"payment_method\" class=\"icheck\">Credit card</label>\n" +
                "                    <i class=\"icon_creditcard\"></i>\n" +
                "                </div>\n" +
                "<form action='CheckOut' method='get'>"+
                "                <div class=\"form-group\">\n" +
                "                    <label>Name on card</label>\n" +
                "                    <input type=\"text\" class=\"form-control\" id=\"name_card_order\" name=\"name_card_order\" placeholder=\"First and last name\">\n" +
                "                </div>\n" +
                "                <div class=\"form-group\">\n" +
                "                    <label>Card number</label>\n" +
                "                    <input type=\"text\" id=\"card_number\" name=\"card_number\" class=\"form-control\" placeholder=\"Card number\">\n" +
                "                </div>\n" +
                "                <div class=\"row\">\n" +
                "                    <div class=\"col-md-6\">\n" +
                "                        <label>Expiration date</label>\n" +
                "                        <div class=\"row\">\n" +
                "                            <div class=\"col-md-6 col-sm-6\">\n" +
                "                                <div class=\"form-group\">\n" +
                "                                    <input type=\"text\" id=\"expire_month\" name=\"expire_month\" class=\"form-control\" placeholder=\"mm\">\n" +
                "                                </div>\n" +
                "                            </div>\n" +
                "                            <div class=\"col-md-6 col-sm-6\">\n" +
                "                                <div class=\"form-group\">\n" +
                "                                    <input type=\"text\" id=\"expire_year\" name=\"expire_year\" class=\"form-control\" placeholder=\"yyyy\">\n" +
                "                                </div>\n" +
                "                            </div>\n" +
                "                        </div>\n" +
                "                    </div>\n" +
                "                    <div class=\"col-md-6 col-sm-12\">\n" +
                "                        <div class=\"form-group\">\n" +
                "                            <label>Security code</label>\n" +
                "                            <div class=\"row\">\n" +
                "                                <div class=\"col-md-4 col-sm-6\">\n" +
                "                                    <div class=\"form-group\">\n" +
                "                                        <input type=\"text\" id=\"ccv\" name=\"ccv\" class=\"form-control\" placeholder=\"CCV\">\n" +
                "                                    </div>\n" +
                "                                </div>\n" +
                "                                <div class=\"col-md-8 col-sm-6\">\n" +
                "                                    <img src=\"img/icon_ccv.gif\" width=\"50\" height=\"29\" alt=\"ccv\"><small>Last 3 digits</small>\n" +
                "                                </div>\n" +
                "                            </div>\n" +
                "                        </div>\n" +
                "                    </div>\n" +
                "                </div><!--End row -->\n" +
                "               <div class=\"form-group\">\n" +
                "                    <label>Address</label>\n" +
                "                    <input type=\"text\" id=\"address\" name=\"address\" class=\"form-control\" placeholder=\"address\">\n" +
                "                </div>\n" +
                "                <div class=\"payment_select\" id=\"paypal\">\n" +
                "                    <label><input type=\"radio\" value=\"\" name=\"payment_method\" class=\"icheck\">Pay with paypal</label>\n" +
                "                </div>\n" +
                "                <div class=\"payment_select nomargin\">\n" +
                "                    <label><input type=\"radio\" value=\"\" name=\"payment_method\" class=\"icheck\">Pay with cash</label>\n" +
                "                    <i class=\"icon_wallet\"></i>\n" +
                "                </div>\n" +
                "            </div><!-- End box_style_1 -->\n" +
                "        </div><!-- End col-md-6 -->\n");

                displayCart(request,response);
                out.println(
                "    </div><!-- End row -->\n" +
                "</div><!-- End container -->\n" +
                "<!-- End Content =============================================== -->\n");
        utility.printHtml("CheckOutFooter.html");

    }

    private void displayCart(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        Utilities utility = new Utilities(request,out);
        ArrayList<Order> orders = utility.getCustomerOrders();
        System.out.println(orders.size());
        out.print("<div class=\"col-md-3\" id=\"sidebar\">\n" +
                "    <div class=\"theiaStickySidebar\">\n" +
                "        <div id=\"cart_box\" >\n" +
                "            <h3>Your order <i class=\"icon_cart_alt pull-right\"></i></h3>\n" +
                "            <table class=\"table table_summary\">\n" +
                "                <tbody>\n" );
        if(orders.size()>0) {
            for(int i=0;i<orders.size();i++) {
                out.print(
                        "                <tr>\n" +
                                "                    <td>\n" +
                                "                        <a href=\"#0\" class=\"remove_item\"><i class=\"icon_minus_alt\"></i></a> <strong>1x</strong> "+orders.get(i).getOrderName()+"\n" +
                                "                    </td>\n" +
                                "                    <td>\n" +
                                "                        <strong class=\"pull-right\">$"+Math.round(orders.get(i).getOrderPrice()*100)/100.0+"</strong>\n" +
                                "                    </td>\n" +
                                "                </tr>\n"
                );
            }
        }
                out.print(
                "                </tbody>\n" +
                "            </table>\n" +
                "            <hr>\n" +
                "            <div class=\"row\" id=\"options_2\">\n" +
                "                <div class=\"col-lg-6 col-md-12 col-sm-12 col-xs-6\">\n" +
                "                    <label><input type=\"radio\" value=\"\" checked name=\"option_2\" class=\"icheck\">Delivery</label>\n" +
                "                </div>\n" +
                "                <div class=\"col-lg-6 col-md-12 col-sm-12 col-xs-6\">\n" +
                "                    <label><input type=\"radio\" value=\"\" name=\"option_2\" class=\"icheck\">Take Away</label>\n" +
                "                </div>\n" +
                "            </div><!-- Edn options 2 -->\n" +
                "\n" +
                "            <hr>\n" +
                "            <table class=\"table table_summary\">\n" +
                "                <tbody>\n" +
                "                <tr>\n" +
                "                    <td>\n" );
        double subTotal=0.0;
        for(int i=0;i<orders.size();i++){
            subTotal = subTotal+orders.get(i).getOrderPrice();
        }
        double Total = subTotal+10;
        out.print(
                "                        Subtotal <span class=\"pull-right\">$"+Math.round(subTotal*100)/100.0+"</span>\n" );
        out.print(
                "                    </td>\n" +
                "                </tr>\n" +
                "                <tr>\n" +
                "                    <td>\n" +
                "                        Delivery fee <span class=\"pull-right\">$10</span>\n" +
                "                    </td>\n" +
                "                </tr>\n" +
                "                <tr>\n" +
                "                    <td class=\"total\">\n" +
                "                        TOTAL <span class=\"pull-right\">$"+Math.round(Total*100)/100.0+"</span>\n" +
                "                    </td>\n" +
                "                </tr>\n" +
                "                </tbody>\n" +
                "            </table>\n" +
                "            <hr>\n" +
                        "<input class='btn_full' type='submit' value='Confirm the order'>"+
//                "            <a class=\"btn_full\" href=\"cart.html\">Order now</a>\n" +
                "        </div><!-- End cart_box -->\n" +
                "    </div><!-- End theiaStickySidebar -->\n" +
                "</div><!-- End col-md-3 -->\n" +
                "\n");
    }
}
