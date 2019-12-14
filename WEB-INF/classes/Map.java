import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/Map")
public class Map extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        Utilities utility = new Utilities(request,out);
        String businessId = request.getParameter("originId");
        Business originBusiness = MySqlDataStoreUtilities.getBussiness(businessId);
        double latitude = originBusiness.getCoordinates().getLatitude();
        double longitude = originBusiness.getCoordinates().getLongitude();
        String destination = request.getParameter("destination");
        utility.printHtml("homePageHeader.html");
        out.print("<div id='map'></div>");
        out.print("<script>" +

              "var map;\n" +
                "var directionsService;\n" +
                "var directionsRenderer;\n" +
                "var start;\n" +
                "function initMap(){\n" +
                "    directionsService = new google.maps.DirectionsService();\n" +
                "    directionsRenderer = new google.maps.DirectionsRenderer();\n" +
                "    var chicago = new google.maps.LatLng(41.850033, -87.6500523);\n" +
                "    var mapOptions = {\n" +
                "        zoom:7,\n" +
                "        center: chicago\n" +
                "    }\n" +
                "    map = new google.maps.Map(document.getElementById('map'), mapOptions);\n" +
                "    directionsRenderer.setMap(map);\n" +
                "    start = new google.maps.LatLng("+latitude+", "+longitude+");\n" +
                "    geocode();\n" +
                "\n" +
                "\n" +
                "}\n" +
                "\n" +
                "function geocode(){\n" +
                "    var location = '"+destination+"';\n" +
                "    axios.get('https://maps.googleapis.com/maps/api/geocode/json',{\n" +
                "        params:{\n" +
                "            address:location,\n" +
                "            key:'AIzaSyBicN5EAdRH-1kG9-xqnZK0w8KO9g5drfk'\n" +
                "        }\n" +
                "    }).then(function(response){\n" +
                "        console.log(response);\n" +
                "        var lat = response.data.results[0].geometry.location.lat;\n" +
                "        var lng = response.data.results[0].geometry.location.lng;\n" +
                "        var end = new google.maps.LatLng(lat, lng);\n" +
                "        var request = {\n" +
                "            origin: start,\n" +
                "            destination: end,\n" +
                "            travelMode: 'DRIVING'\n" +
                "        };\n" +
                "        directionsService.route(request, function(result, status) {\n" +
                "            if (status == 'OK') {\n" +
                "                directionsRenderer.setDirections(result);\n" +
                "            }\n" +
                "        });\n" +
                "    }).catch(function(error){\n" +
                "        console.log(error);\n" +
                "    });\n" +
                "\n" +
                "}"+
                "</script>");
        out.print("<script async defer src=\"https://maps.googleapis.com/maps/api/js?key=AIzaSyBicN5EAdRH-1kG9-xqnZK0w8KO9g5drfk&callback=initMap\">\n" +
                "    </script>");
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
