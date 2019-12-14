var completeTable;
var req;
var isIE;

function init() {
    completeTable = document.getElementById("complete-table");
}

function docomplete() {

    var url = "AutoComplete?action=complete";
    req = initRequest();
    req.open("GET", url, true);
    req.onreadystatechange = callback;
    req.send(null);
}

function initRequest() {
    if (window.XMLHttpRequest) {
        if (navigator.userAgent.indexOf('MSIE') != -1) {
            isIE = true;
        }
        return new XMLHttpRequest();
    } else if (window.ActiveXObject) {
        isIE = true;
        return new ActiveXObject("Microsoft.XMLHTTP");
    }
}

function callback() {
    clearTable();

    if (req.readyState == 4) {
        if (req.status == 200) {
            parseMessages(req.responseXML);
        }
    }
}

function clearTable() {
    if (completeTable.getElementsByTagName("tr").length > 0) {
        completeTable.style.display = 'none';
        for (loop = completeTable.childNodes.length -1; loop >= 0 ; loop--) {
            completeTable.removeChild(completeTable.childNodes[loop]);
        }
    }
}

function appendProduct(orderName,orderPrice) {

   completeTable.innerHTML+= "                <tr>\n" +
       "                    <td>\n" +
       "                        <a href=\"#0\" class=\"remove_item\"><i class=\"icon_minus_alt\"></i></a> <strong>1x</strong> "+orderName+"\n" +
       "                    </td>\n" +
       "                    <td>\n" +
       "                        <strong class=\"pull-right\">$"+orderPrice+"</strong>\n" +
       "                    </td>\n" +
       "                </tr>\n"
}

function parseMessages(responseXML) {

    // no matches returned
    if (responseXML == null) {
        return false;
    } else {

        var orders = responseXML.getElementsByTagName("Orders")[0];

        if (orders.childNodes.length > 0) {

            for (loop = 0; loop < products.childNodes.length; loop++) {
                var order = products.childNodes[loop];
                var orderName = product.getElementsByTagName("Name")[0];
                var orderPrice = product.getElementsByTagName("price")[0];
                appendProduct(orderName.childNodes[0].nodeValue,
                    orderPrice.childNodes[0].nodeValue);
            }
        }
    }
}