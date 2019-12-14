import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/ListPageForAjax")
public class ListPageForAjax extends HttpServlet {
    private ArrayList<Business> businesses;
    private ArrayList<Business> topBusinesses;
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        Utilities utility = new Utilities(request, out);
        String zipcode = request.getParameter("searchInput");
        System.out.println(zipcode);
        if (!zipcode.isEmpty() && !zipcode.equals("")) {
            businesses = MySqlDataStoreUtilities.getBussinesses(zipcode);
            topBusinesses = MySqlDataStoreUtilities.getTop(zipcode,10);
        } else {
            businesses = MySqlDataStoreUtilities.getBussinesses("60616");
            topBusinesses = MySqlDataStoreUtilities.getTop("60616",10);
        }
       utility.printHtml("homePageHeader.html");
        out.print("<!-- SubHeader =============================================== -->\n" +
                "<section class=\"parallax-window\" id=\"short\" data-parallax=\"scroll\" data-image-src=\"img/foodiesfeed.com_coconut-flakes-in-a-wooden-bowl-with-tea.jpg\" data-natural-width=\"1400\" data-natural-height=\"350\">\n" +
                "    <div id=\"subheader\">\n" +
                "        <div id=\"sub_content\">\n");
        out.print( "            <h1>"+businesses.size()+" results searched</h1>\n" );
        out.print(
//                "            <div><i class=\"icon_pin\"></i> 135 Newtownards Road, Belfast, BT4 1AB</div>\n" +
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
//       utility.printHtml("listPageLeftNavigationBar.html");
       for(int i=0;i<businesses.size();i++){
           String imageUrl = businesses.get(i).getImage_url();
           long reviewCount = businesses.get(i).getReview_count();
           String name = businesses.get(i).getName();
           List<Category> categories = businesses.get(i).getCategories();
           String displayAddress = String.join(",",businesses.get(i).getLocation().getDisplay_address());
           String price = businesses.get(i).getPrice();
           boolean isClosed = businesses.get(i).isIs_closed();
           System.out.println(isClosed);
           List<String> transactions = businesses.get(i).getTransactions();
           double rating = businesses.get(i).getRating();
           String id = businesses.get(i).getId();

           out.print("<div class=\"strip_list wow fadeIn\" data-wow-delay=\"0."+i+"s\">\n");
           for(Business topB:topBusinesses){
               if(id.equalsIgnoreCase(topB.getId())){
                   out.print(
                           "    <div class=\"ribbon_1\">\n" +
                                   "        Popular\n" +
                                   "    </div>\n" );
               };
           }

           out.print(
                   "    <div class=\"row\">\n" +
                   "        <div class=\"col-md-9 col-sm-9\">\n" +
                   "            <div class=\"desc\">\n" +
                   "                <div class=\"thumb_strip\">\n" +
                   "                    <a href=\"detail_page.html\"><img src=\""+imageUrl+"\" alt=\"\"width=\"110\" height=\"110\"></a>\n" +
                   "                </div>\n" +
                   "                <div class=\"rating\">\n");
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
                   "                   (<small><a href=\"#0\">"+reviewCount+" reviews</a></small>)\n" +
                   "                </div>\n" +
                   "                <h3>"+name+"</h3>\n" +
                   "                <div class=\"type\">\n" );
           for(int j=0;j<categories.size();j++){
               if(j<categories.size()-1) {
                   out.print(categories.get(j).getTitle() + "/");
               }
               else{
                   out.print(categories.get(j).getTitle());
               }
           }
           out.print(
                   "                </div>\n" +
                   "                <div class=\"location\">\n" +displayAddress+
                   "  <span class=\"opening\">");
           if(isClosed==true){
               out.print("closed");
           }
           else{
               out.print("openning");
           }
           out.print("</span> "+
                   "                </div>\n" +
                   "                <ul>\n");
           for(String ele:transactions){
               out.print("<li>"+ele+"<i class=\"icon_check_alt2 ok\"></i></li>\n");
           }
           out.print("                </ul>\n" +
                   "            </div>\n" +
                   "        </div>\n" +
                   "        <div class=\"col-md-3 col-sm-3\">\n" +
                   "            <div class=\"go_to\">\n" +
                   "                <div>\n" +
                   "                    <a href=\"detailPage?name="+name+"&id="+id+"&reviewcount="+reviewCount+"&rating="+rating+"&imageUrl="+imageUrl+"\" class=\"btn_1\">View Menu</a>\n" +
                   "                </div>\n" +
                   "            </div>\n" +
                   "        </div>\n" +
                   "    </div><!-- End row-->\n" +
                   "</div><!-- End strip_list-->");
       }
        utility.printHtml("listPageFooter.html");
    }
}
