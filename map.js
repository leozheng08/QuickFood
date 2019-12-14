function initMap(){
    var directionService = new google.maps.DirectionsService;
    var directionDisplay = new google.maps.DirectionsRenderer;
    var map = new google.maps.Map(document.getElementById('map'),{
        zoom:7,
        center:{lat:41.88, lng:-87.63}
    })
}