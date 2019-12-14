import com.google.gson.*;
import com.google.gson.reflect.TypeToken;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/ListPage")
public class ListPage extends HttpServlet {
    private ArrayList<Business> businessList = new ArrayList<>();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        Utilities utility = new Utilities(request, out);
        String locationStr = request.getParameter("searchInput");
        System.out.println("-- ListPage.doGet --" + locationStr);
        Map<String, String> params = new HashMap<>();
        params.put("term", "restaurant");
        if (!locationStr.isEmpty() && !locationStr.equals("")) {
            params.put("location", locationStr);
        } else {
            params.put("location", "60616");
        }
        // get 10 items
        params.put("limit", "50");
        String result = HttpBaseUtil.get(APIConfig.BUSINESS_SEARCH, params);
        System.out.println("-- Test.onSuccess --" + result);
        JsonObject jsonObject = new JsonParser().parse(result).getAsJsonObject();
        JsonArray businessArray = jsonObject.getAsJsonArray("businesses");
        Gson gson = new Gson();
        for (JsonElement business : businessArray) {
            Business businessesBean = gson.fromJson(business, new TypeToken<Business>() {
            }.getType());
            System.out.println("-- Test.onSuccess gson --" + businessesBean);
            businessList.add(businessesBean);
        }
        try {
            printHeader(50, locationStr, out);

            System.out.println("-- ListPage.doGet --" + businessList.size());
            if (null != businessList && businessList.size() > 0) {
                for (int i = 0; i < businessList.size(); i++) {
                    out.print("<div class='strip_list wow fadeIn' data-wow-delay='0.1s'>");
                    if (i < 3) {
                        out.print("<div class='ribbon_1'>Popular</div>");
                    }
                    out.print("<div class='row'><div class='col-md-9 col-sm-9'><div class='desc'><div class='thumb_strip'>\n");
                    out.print("");
                    out.print(" <a href='detail_page.html'><img src='" + businessList.get(i).getImage_url() + "' alt=''></a>\n");
                    // TODO: 11/3/19 reviews
                    //out.print("</div><div class='rating'><i class='icon_star voted'></i><i class='icon_star voted'></i><i class='icon_star voted'></i><i class='icon_star voted'></i><i class='icon_star'></i> (<small><a href='#0'>98 reviews</a></small>)</div>\n");
                    out.print("</div><div class='rating'>");
//                    for (int j = 0; j < businessList.get(i).getRating(); j++) {
//                        out.print("<i class='icon_star voted'></i>");
//                    }
//                    for (int j = 0; j < 5 - businessList.get(i).getRating(); j++) {
//                        out.print("<i class='icon_star'></i>");
//                    }
                    out.print("</div>");
                    out.print("<h3>" + businessList.get(i).getName() + "</h3><div class='type'>");
                    for (int j = 0; j < businessList.get(i).getCategories().size(); j++) {
                        out.print(businessList.get(i).getCategories().get(j).getTitle());
                        out.print(" ");
                    }
                    out.print("</div>\n");
                    out.print("<div class='location'>" + businessList.get(i).getLocation().getDisplay_address().toString() + "<span class='opening'> ");
                    if (businessList.get(i).isIs_closed()) {
                        out.print("<span color='red'> opening");
                    } else {
                        out.print("<span color='red'> closed");
                    }
                    out.print("</span>  price :" + businessList.get(i).getPrice() + "</div>\n");
                    out.print("<ul><li>Take away<i class='icon_check_alt2 ok'></i></li><li>Delivery<i class='icon_check_alt2 no'></i></li></ul>\n");
                    out.print("</div></div><div class='col-md-3 col-sm-3'><div class='go_to'><div><a href='detail_page.html' class='btn_1'>View Menu</a></div>\n");
                    out.print("</div></div></div></div>");
                }
                // TODO: 11/3/19  load more
                out.print("<a href='#0' class='load_more_bt wow fadeIn' data-wow-delay='0.2s'>Nothing more...</a></div></div></div>\n");

            }
            utility.printHtml("listPageFooter.html");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    public void printHeader(int resultCount, String address, PrintWriter out) {
        out.print("<!DOCTYPE html>\n" +
                "<html>\n" +
                "<head>\n" +
                "    <meta charset=\"utf-8\">\n" +
                "    <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\n" +
                "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\n" +
                "    <meta name=\"keywords\" content=\"pizza, delivery food, fast food, sushi, take away, chinese, italian food\">\n" +
                "    <meta name=\"description\" content=\"\">\n" +
                "    <meta name=\"author\" content=\"Ansonika\">\n" +
                "    <title>QuickFood - Quality delivery or take away food</title>\n" +
                "\n" +
                "    <!-- Favicons-->\n" +
                "    <link rel=\"shortcut icon\" href=\"img/favicon.ico\" type=\"image/x-icon\">\n" +
                "    <link rel=\"apple-touch-icon\" type=\"image/x-icon\" href=\"img/apple-touch-icon-57x57-precomposed.png\">\n" +
                "    <link rel=\"apple-touch-icon\" type=\"image/x-icon\" sizes=\"72x72\" href=\"img/apple-touch-icon-72x72-precomposed.png\">\n" +
                "    <link rel=\"apple-touch-icon\" type=\"image/x-icon\" sizes=\"114x114\" href=\"img/apple-touch-icon-114x114-precomposed.png\">\n" +
                "    <link rel=\"apple-touch-icon\" type=\"image/x-icon\" sizes=\"144x144\" href=\"img/apple-touch-icon-144x144-precomposed.png\">\n" +
                "\n" +
                "    <!-- GOOGLE WEB FONT -->\n" +
                "    <link href=\"https://fonts.googleapis.com/css?family=Lato:300,400,700,900\" rel=\"stylesheet\">\n" +
                "\n" +
                "    <!-- BASE CSS -->\n" +
                "    <link href=\"css/animate.min.css\" rel=\"stylesheet\">\n" +
                "    <link href=\"css/bootstrap.min.css\" rel=\"stylesheet\">\n" +
                "    <link href=\"css/menu.css\" rel=\"stylesheet\">\n" +
                "    <link href=\"css/style.css\" rel=\"stylesheet\">\n" +
                "    <link href=\"css/responsive.css\" rel=\"stylesheet\">\n" +
                "    <link href=\"css/elegant_font/elegant_font.min.css\" rel=\"stylesheet\">\n" +
                "    <link href=\"css/fontello/css/fontello.min.css\" rel=\"stylesheet\">\n" +
                "    <link href=\"css/magnific-popup.css\" rel=\"stylesheet\">\n" +
                "    <link href=\"css/pop_up.css\" rel=\"stylesheet\">\n" +
                "\n" +
                "    <!-- Radio and check inputs -->\n" +
                "    <link href=\"css/skins/square/grey.css\" rel=\"stylesheet\">\n" +
                "    <link href=\"css/ion.rangeSlider.css\" rel=\"stylesheet\">\n" +
                "    <link href=\"css/ion.rangeSlider.skinFlat.css\" rel=\"stylesheet\">\n" +
                "\n" +
                "    <!-- YOUR CUSTOM CSS -->\n" +
                "    <link href=\"css/custom.css\" rel=\"stylesheet\">\n" +
                "</head>\n" +
                "\n" +
                "<body>\n" +
                "\n" +
                "<!--[if lte IE 8]>\n" +
                "<p class=\"chromeframe\">You are using an <strong>outdated</strong> browser. Please <a href=\"http://browsehappy.com/\">upgrade your browser</a>.</p>\n" +
                "<![endif]-->\n" +
                "\n" +
                "<div id=\"preloader\">\n" +
                "    <div class=\"sk-spinner sk-spinner-wave\" id=\"status\">\n" +
                "        <div class=\"sk-rect1\"></div>\n" +
                "        <div class=\"sk-rect2\"></div>\n" +
                "        <div class=\"sk-rect3\"></div>\n" +
                "        <div class=\"sk-rect4\"></div>\n" +
                "        <div class=\"sk-rect5\"></div>\n" +
                "    </div>\n" +
                "</div><!-- End Preload -->\n" +
                "\n" +
                "<!-- Header ================================================== -->\n" +
                "<header>\n" +
                "    <div class=\"container-fluid\">\n" +
                "        <div class=\"row\">\n" +
                "            <div class=\"col--md-4 col-sm-4 col-xs-4\">\n" +
                "                <a href=\"index.html\" id=\"logo\">\n" +
                "                    <img src=\"img/logo.png\" width=\"190\" height=\"23\" alt=\"\" data-retina=\"true\" class=\"hidden-xs\">\n" +
                "                    <img src=\"img/logo_mobile.png\" width=\"59\" height=\"23\" alt=\"\" data-retina=\"true\" class=\"hidden-lg hidden-md hidden-sm\">\n" +
                "                </a>\n" +
                "            </div>\n" +
                "            <nav class=\"col--md-8 col-sm-8 col-xs-8\">\n" +
                "                <a class=\"cmn-toggle-switch cmn-toggle-switch__htx open_close\" href=\"javascript:void(0);\"><span>Menu mobile</span></a>\n" +
                "                <div class=\"main-menu\">\n" +
                "                    <div id=\"header_menu\">\n" +
                "                        <img src=\"img/logo.png\" width=\"190\" height=\"23\" alt=\"\" data-retina=\"true\">\n" +
                "                    </div>\n" +
                "                    <a href=\"#\" class=\"open_close\" id=\"close_in\"><i class=\"icon_close\"></i></a>\n" +
                "                    <ul>\n" +
                "                        <li class=\"submenu\">\n" +
                "                            <a href=\"javascript:void(0);\" class=\"show-submenu\">Home<i class=\"icon-down-open-mini\"></i></a>\n" +
                "                            <ul>\n" +
                "                                <li><a href=\"index.html\">Home Video background</a></li>\n" +
                "                                <li><a href=\"index_2.html\">Home Static image</a></li>\n" +
                "                                <li><a href=\"index_3.html\">Home Text rotator</a></li>\n" +
                "                                <li><a href=\"index_8.html\">Home Layer slider</a></li>\n" +
                "                                <li><a href=\"index_4.html\">Home Cookie bar</a></li>\n" +
                "                                <li><a href=\"index_5.html\">Home Popup</a></li>\n" +
                "                                <li><a href=\"index_6.html\">Home Mobile synthetic</a></li>\n" +
                "                                <li><a href=\"index_7.html\">Top Menu version 2</a></li>\n" +
                "                            </ul>\n" +
                "                        </li>\n" +
                "                        <li class=\"submenu\">\n" +
                "                            <a href=\"javascript:void(0);\" class=\"show-submenu\">Restaurants<i class=\"icon-down-open-mini\"></i></a>\n" +
                "                            <ul>\n" +
                "                                <li><a href=\"list_page.html\">Row listing</a></li>\n" +
                "                                <li><a href=\"grid_list.html\">Grid listing</a></li>\n" +
                "                                <li><a href=\"map_listing.html\">Map listing</a></li>\n" +
                "                                <li><a href=\"detail_page.html\">beans.Restaurant Menu</a></li>\n" +
                "                                <li><a href=\"submit_restaurant.html\">Submit beans.Restaurant</a></li>\n" +
                "                                <li><a href=\"cart.html\">Order step 1</a></li>\n" +
                "                                <li><a href=\"cart_2.html\">Order step 2</a></li>\n" +
                "                                <li><a href=\"cart_3.html\">Order step 3</a></li>\n" +
                "                                <li><a href=\"cart_datepicker.html\">Order Date/Time picker</a></li>\n" +
                "                            </ul>\n" +
                "                        </li>\n" +
                "                        <li><a href=\"about.html\">About us</a></li>\n" +
                "                        <li><a href=\"faq.html\">Faq</a></li>\n" +
                "                        <li class=\"submenu\">\n" +
                "                            <a href=\"javascript:void(0);\" class=\"show-submenu\">Pages<i class=\"icon-down-open-mini\"></i></a>\n" +
                "                            <ul>\n" +
                "                                <li><a href=\"RTL_version/index.html\">RTL version</a></li>\n" +
                "                                <li><a href=\"admin.html\">Admin section</a></li>\n" +
                "                                <li><a href=\"submit_driver.html\">Submit Driver</a></li>\n" +
                "                                <li><a href=\"#0\" data-toggle=\"modal\" data-target=\"#login_2\">beans.User servlets.Login</a></li>\n" +
                "                                <li><a href=\"#0\" data-toggle=\"modal\" data-target=\"#register\">beans.User servlets.Register</a></li>\n" +
                "                                <li><a href=\"detail_page_2.html\">beans.Restaurant detail page</a></li>\n" +
                "                                <li><a href=\"blog.html\">Blog</a></li>\n" +
                "                                <li><a href=\"contacts.html\">Contacts</a></li>\n" +
                "                                <li><a href=\"coming_soon/index.html\">Coming soon page</a></li>\n" +
                "                                <li><a href=\"shortcodes.html\">Shortcodes</a></li>\n" +
                "                                <li><a href=\"icon_pack_1.html\">Icon pack 1</a></li>\n" +
                "                                <li><a href=\"icon_pack_2.html\">Icon pack 2</a></li>\n" +
                "                            </ul>\n" +
                "                        </li>\n" +
                "                        <li><a href=\"#0\" data-toggle=\"modal\" data-target=\"#login_2\">servlets.Login</a></li>\n" +
                "                        <!--                    <li><a href=\"#0\">Purchase this template</a></li>-->\n" +
                "                    </ul>\n" +
                "                </div><!-- End main-menu -->\n" +
                "            </nav>\n" +
                "        </div><!-- End row -->\n" +
                "    </div><!-- End container -->\n" +
                "</header>\n" +
                "<!-- End Header =============================================== -->\n" +
                "\n" +
                "<!-- SubHeader =============================================== -->\n" +
                "<section class=\"parallax-window\" id=\"short\" data-parallax=\"scroll\" data-image-src=\"img/sub_header_short.jpg\" data-natural-width=\"1400\" data-natural-height=\"350\">\n" +
                "    <div id=\"subheader\">\n" +
                "        <div id=\"sub_content\">\n" +
                "            <h1>" + resultCount + " results in your zone</h1>\n" +
                "            <div><i class=\"icon_pin\"></i>" + address + "</div>\n" +
                "        </div><!-- End sub_content -->\n" +
                "    </div><!-- End subheader -->\n" +
                "</section><!-- End section -->\n" +
                "<!-- End SubHeader ============================================ -->\n" +
                "\n" +
                "<div id=\"position\">\n" +
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
                "<div class=\"collapse\" id=\"collapseMap\">\n" +
                "    <div id=\"map\" class=\"map\"></div>\n" +
                "</div><!-- End Map -->");

    }

}
