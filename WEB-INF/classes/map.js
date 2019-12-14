var map;
var directionsService;
var directionsRenderer;
var start;
function initMap(){
    directionsService = new google.maps.DirectionsService();
    directionsRenderer = new google.maps.DirectionsRenderer();
    var chicago = new google.maps.LatLng(41.850033, -87.6500523);
    var mapOptions = {
        zoom:7,
        center: chicago
    }
    map = new google.maps.Map(document.getElementById('map'), mapOptions);
    directionsRenderer.setMap(map);
    start = new google.maps.LatLng(37.7699298, -122.4469157);
    geocode();


}

function geocode(){
    var location = '3021 S Michigan Ave';
    axios.get('https://maps.googleapis.com/maps/api/geocode/json',{
        params:{
            address:location,
            key:'AIzaSyBicN5EAdRH-1kG9-xqnZK0w8KO9g5drfk'
        }
    }).then(function(response){
        console.log(response);
        var lat = response.data.results[0].geometry.location.lat;
        var lng = response.data.results[0].geometry.location.lng;
        var end = new google.maps.LatLng(lat, lng);
        var request = {
            origin: start,
            destination: end,
            travelMode: 'DRIVING'
        };
        directionsService.route(request, function(result, status) {
            if (status == 'OK') {
                directionsRenderer.setDirections(result);
            }
        });
    }).catch(function(error){
        console.log(error);
    });

}