import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/detailPage")
public class detailPage extends HttpServlet {
 private Menu menu=null;
 private Business business = null;
 List<Starter> starterList = new ArrayList<>();
 List<mainCourse> mainCoursesist = new ArrayList<>();
 List<Dessert> dessertsList = new ArrayList<>();
 List<Drink> drinksList = new ArrayList<>();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        Utilities utility = new Utilities(request,out);
        utility.printHtml("homePageHeader.html");
       String name = request.getParameter("name");
       String id = request.getParameter("id");
       business = MySqlDataStoreUtilities.getBussiness(id);
       System.out.println(business);
       long reviewCount =  Long.parseLong(request.getParameter("reviewcount") );
       double rating = Double.parseDouble(request.getParameter("rating"));
       String imageUrl = request.getParameter("imageUrl");
       menu = MySqlDataStoreUtilities.getMenu(id);
       starterList = menu.getStarters();
       mainCoursesist = menu.getMainCourses();
       dessertsList = menu.getDesserts();
       drinksList = menu.getDrink();

//       utility.storeOrder(ordername,orderprice,);

       out.print("<!-- SubHeader =============================================== -->\n" +
               "<section class=\"parallax-window\" data-parallax=\"scroll\" data-image-src=\"img/sub_header_2.jpg\" data-natural-width=\"1400\" data-natural-height=\"470\">\n" +
               "    <div id=\"subheader\">\n" +
               "        <div id=\"sub_content\">\n" +
               "            <div id=\"thumb\"><img src=\""+business.getImage_url()+"\" alt=\"\"></div>" +
               "<div class=\"rating\">");
        int times = (int)rating;
        for(int time=1;time<=5;time++){
            if(time<=times) {
                out.print(" <i class=\"icon_star voted\"></i>");
            }
            else{
                out.print(" <i class=\"icon_star \"></i>");
            }
        }
               out.print(
               " (<small><a href=\"detail_page_2.html\">Read "+reviewCount+" reviews</a></small>)</div>\n" +
               "            <h1>"+name+"</h1>\n" + "<div><i class=\"icon_pin\"></i>"+business.getLocation().getDisplay_address()+"- <strong>Delivery charge:</strong> $10, free over $15.</div>" +
               "        </div><!-- End sub_content -->\n" +
               "    </div><!-- End subheader -->\n" +
               "</section><!-- End section -->\n" +
               "<!-- End SubHeader ============================================ -->");


        out.print("<div id=\"position\">\n" +
                "    <div class=\"container\">\n" +
                "        <ul>\n" +
                "            <li><a href=\"#0\">Home</a></li>\n" +
                "            <li><a href=\"#0\">Category</a></li>\n" +
                "            <li>Page active</li>\n" +
                "        </ul>\n" +
                "        <a href=\"#0\" class=\"search-overlay-menu-btn\"><i class=\"icon-search-6\"></i> Search</a>\n" +
                "    </div>\n" +
                "</div><!-- Position -->\n" +
                "\n" +
                "<!-- Content ================================================== -->\n" +
                "<div class=\"container margin_60_35\">\n" +
                "    <div class=\"row\">\n" +
                "\n" +
                "        <div class=\"col-md-3\">\n" +
                "            <p><a href=\"list_page.html\" class=\"btn_side\">Back to search</a></p>\n" +
                "            <div class=\"box_style_1\">\n" +
                "                <ul id=\"cat_nav\">\n" +
                "                    <li><a href=\"#starters\" class=\"active\">Starters <span>("+starterList.size()+")</span></a></li>\n" +
                "                    <li><a href=\"#main_courses\">Main Courses <span>("+mainCoursesist.size()+")</span></a></li>\n" +
                "                    <li><a href=\"#desserts\">Desserts <span>("+dessertsList.size()+")</span></a></li>\n" +
                "                    <li><a href=\"#drinks\">Drinks <span>("+drinksList.size()+")</span></a></li>\n" +
                "                </ul>\n" +
                "            </div><!-- End box_style_1 -->\n" +
                "\n" +
                "            <div class=\"box_style_2 hidden-xs\" id=\"help\">\n" +
                "                <i class=\"icon_lifesaver\"></i>\n" +
                "                <h4>Need <span>Help?</span></h4>\n" +
                "                <a href=\"tel://004542344599\" class=\"phone\">"+business.getDisplay_phone()+"</a>\n" +
                "                <small>Monday to Friday 9.00am - 7.30pm</small>\n" +
                "            </div>\n" +
                "        </div><!-- End col-md-3 -->");

        out.print("<div class=\"col-md-6\">\n" +
                "    <div class=\"box_style_2\" id=\"main_menu\">\n" +
                "        <h2 class=\"inner\">Menu</h2>\n" +

                "        <h3 class=\"nomargin_top\" id=\"starters\">Starters</h3>\n" +
                "        <p>\n" +
                "        </p>\n" +
                "        <table class=\"table table-striped cart-list\">\n" +
                "            <thead>\n" +
                "            <tr>\n" +
                "                <th>\n" +
                "                    Item\n" +
                "                </th>\n" +
                "                <th>\n" +
                "                    Price\n" +
                "                </th>\n" +
                "                <th>\n" +
                "                    Order\n" +
                "                </th>\n" +
                "            </tr>\n" +
                "            </thead><tbody>");

        for(int i=0;i<starterList.size();i++){
            int tag=1;
            out.print("<tr>\n" +
                    "                <td>\n" +
                    "                    <figure class=\"thumb_menu_list\"><img src=\""+starterList.get(i).getUrl()+"\" alt=\"thumb\"></figure>\n" +
                    "                    <h5 id='orderName1"+i+"'>"+starterList.get(i).getStarterName()+"</h5>\n" +
                    "                    <p>\n" +
                    "                    </p>\n" +
                    "                </td>\n" +
                    "                <td>\n" +
                    "                    <strong id='orderPrice1"+i+"'>$"+starterList.get(i).getPrice()+"</strong>\n" +
                    "                </td>\n" +
                    "                <td class=\"options\">\n" +
//                    "<a href=\"Cart?businessid="+id+"&ordername="+starterList.get(i).getStarterName()+"&orderprice="+starterList.get(i).getPrice()+"\"><i id='demo'class=\"icon_plus_alt2\"><input type='hidden' id='demo' value='1'></i></a>\n" +
                    "                    <a href='javascript:addItemToCart("+tag+","+i+")'  ><i class=\"icon_plus_alt2\"></i></a>\n" +
                    "                </td>\n" +
                    "            </tr>");
        }
        out.print("</tbody>\n" +
                "        </table>");


        out.print("<hr>\n" +
                "        <h3 id=\"main_courses\">Main courses</h3>\n" +
                "        <p>\n" +

                "        </p>\n" +
                "        <table class=\"table table-striped cart-list \">\n" +
                "            <thead>\n" +
                "            <tr>\n" +
                "                <th>\n" +
                "                    Item\n" +
                "                </th>\n" +
                "                <th>\n" +
                "                    Price\n" +
                "                </th>\n" +
                "                <th>\n" +
                "                    Order\n" +
                "                </th>\n" +
                "            </tr>\n" +
                "            </thead>\n" +
                "            <tbody>");

        for(int i=0;i<mainCoursesist.size();i++){
            int tag=2;
            out.print("<tr>\n" +
                    "                <td>\n" +
                    "                    <figure class=\"thumb_menu_list\"><img src=\""+mainCoursesist.get(i).getUrl()+"\" alt=\"thumb\"></figure>\n" +
                    "                    <h5 id='orderName2"+i+"'>"+mainCoursesist.get(i).getMainCourseName()+"</h5>\n" +
                    "                    <p>\n" +
                    "                    </p>\n" +
                    "                </td>\n" +
                    "                <td>\n" +
                    "                    <strong id='orderPrice2"+i+"'>$"+mainCoursesist.get(i).getPrice()+"</strong>\n" +
                    "                </td>\n" +
                    "                <td class=\"options\">\n" +
//                    "                    <a href=\"Cart?businessid="+id+"&ordername="+mainCoursesist.get(i).getMainCourseName()+"&orderprice="+mainCoursesist.get(i).getPrice()+"\"><i class=\"icon_plus_alt2\"><input type='hidden' id='demo' value='1'></i></a>\n" +
                    "                    <a href='javascript:addItemToCart("+tag+","+i+")'  ><i class=\"icon_plus_alt2\"></i></a>\n" +
                    "                </td>\n" +
                    "            </tr>");
        }
        out.print("</tbody>\n" +
                "        </table>");

        out.print(" <hr>\n" +
                "        <h3 id=\"desserts\">Desserts</h3>\n" +
                "        <p>\n" +

                "        </p>\n" +
                "        <table class=\"table table-striped cart-list \">\n" +
                "            <thead>\n" +
                "            <tr>\n" +
                "                <th>\n" +
                "                    Item\n" +
                "                </th>\n" +
                "                <th>\n" +
                "                    Price\n" +
                "                </th>\n" +
                "                <th>\n" +
                "                    Order\n" +
                "                </th>\n" +
                "            </tr>\n" +
                "            </thead>\n" +
                "            <tbody>");
        for(int i=0;i<dessertsList.size();i++){
            int tag=3;
            out.print("  <tr>\n" +
                    "                <td>\n" +
                    "                    <figure class=\"thumb_menu_list\"><img src=\""+dessertsList.get(i).getUrl()+"\" alt=\"thumb\"></figure>\n" +
                    "                    <h5 id='orderName3"+i+"'>"+dessertsList.get(i).getDessertName()+"</h5>\n" +
                    "                    <p>\n" +
                    "                    </p>\n" +
                    "                </td>\n" +
                    "                <td>\n" +
                    "                    <strong id='orderPrice3"+i+"'>$"+dessertsList.get(i).getPrice()+"</strong>\n" +
                    "                </td>\n" +
                    "                <td class=\"options\">\n" +
//                    "                    <a href=\"Cart?businessid="+id+"&ordername="+dessertsList.get(i).getDessertName()+"&orderprice="+dessertsList.get(i).getPrice()+"\"><i class=\"icon_plus_alt2\"></i></a>\n" +
                    "                    <a href='javascript:addItemToCart("+tag+","+i+")'  ><i class=\"icon_plus_alt2\"></i></a>\n" +

                    "                </td>\n" +
                    "            </tr>");
        }
        out.print("</tbody>\n" +
                "        </table>");


        out.print(" <hr>\n" +
                "        <h3 id=\"drinks\">Drinks</h3>\n" +
                "        <p>\n" +
                "            Te ferri iisque aliquando pro, posse nonumes efficiantur in cum. Sensibus reprimique eu pro. Fuisset mentitum deleniti sit ea.\n" +
                "        </p>\n" +
                "        <table class=\"table table-striped cart-list \">\n" +
                "            <thead>\n" +
                "            <tr>\n" +
                "                <th>\n" +
                "                    Item\n" +
                "                </th>\n" +
                "                <th>\n" +
                "                    Price\n" +
                "                </th>\n" +
                "                <th>\n" +
                "                    Order\n" +
                "                </th>\n" +
                "            </tr>\n" +
                "            </thead>\n" +
                "            <tbody>");
        for(int i=0;i<drinksList.size();i++){
            int tag=4;
            out.print("<tr>\n" +
                    "                <td>\n" +
                    "                    <figure class=\"thumb_menu_list\"><img src=\""+drinksList.get(i).getUrl()+"\" alt=\"thumb\"></figure>\n" +
                    "                    <h5 id='orderName4"+i+"'>"+drinksList.get(i).getDrinkName()+"</h5>\n" +
                    "                    <p>\n" +
                    "                    </p>\n" +
                    "                </td>\n" +
                    "                <td>\n" +
                    "                    <strong id='orderPrice4"+i+"'>$"+drinksList.get(i).getPrice()+"</strong>\n" +
                    "                </td>\n" +
                    "                <td class=\"options\">\n" +
//                    "                    <a href=\"Cart?businessid="+id+"&ordername="+drinksList.get(i).getDrinkName()+"&orderprice="+drinksList.get(i).getPrice()+"\"  ><i class=\"icon_plus_alt2\"></i></a>\n" +
                    "                    <a href='javascript:addItemToCart("+tag+","+i+")'  ><i class=\"icon_plus_alt2\"></i></a>\n" +
//                                    "<button onclick='doComplete()' ><i class=\"icon_plus_alt2\"></i></button>"+

                    "                </td>\n" +
                    "            </tr>");
        }
        out.print("</tbody>\n" +
                "        </table>" +
                "</div><!-- End box_style_1 -->\n" +
                "</div><!-- End col-md-6 -->");


        out.print("<div class=\"col-md-3\" id=\"sidebar\">\n" +
                "    <div class=\"theiaStickySidebar\">\n" +
                "        <div id=\"cart_box\" >\n" +
                "            <h3>Your order <i class=\"icon_cart_alt pull-right\"></i></h3>\n" +
                "            <table id='CustomerOrderTable' class=\"table table_summary\">\n" +
                "                <tbody id='complete-table'>\n" +



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
                "            <table id='CustomerOrderTotalTable'class=\"table table_summary\">\n" +
                "                <tbody>\n" +
                "                <tr>\n" +
                "                    <td id='Subtotal'>\n" +
                "                        Subtotal <span class=\"pull-right\">$0.0</span>\n" +
                "                    </td>\n" +
                "                </tr>\n" +
                "                <tr>\n" +
                "                    <td>\n" +
                "                        Delivery fee <span class=\"pull-right\">$10</span>\n" +
                "                    </td>\n" +
                "                </tr>\n" +
                "                <tr>\n" +
                "                    <td id='Total'class=\"total\">\n" +
                "                        TOTAL <span class=\"pull-right\">$0.0</span>\n" +
                "                    </td>\n" +
                "                </tr>\n" +
                "                </tbody>\n" +
                "            </table>\n" +
                "            <hr>\n" +
                "<p hidden id='businessId'>"+id+"</p>"+
                "            <a class=\"btn_full\" href=javascript:checkOut()>Order now</a>\n" +
                "        </div><!-- End cart_box -->\n" +
                "    </div><!-- End theiaStickySidebar -->\n" +
                "</div><!-- End col-md-3 -->");
        out.print(
                "</div><!-- End row -->\n" +
                "</div><!-- End container -->\n" +
                "<!-- End Content =============================================== -->");
        utility.printHtml("detailPageFooter.html");
    }


}
