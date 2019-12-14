$("#btnGetChartData").click(function () {
    $("#btnGetChartData").hide();
    $.ajax({
        url: 'https://cors-anywhere.herokuapp.com/https://api.yelp.com/v3/businesses/search?term=restraunt&location=newyork',
        method: 'GET',
        datatype:'json',
        headers: {
            'Authorization':'Bearer m3wTJA76DZqAox7RC6l5vZ1KLqS1jOiso39vhcCLFF844bf04DpPxbCwIPr2MpFGc-_e93bIC1vCCHHBH1d23SOkww4EzRpaBCwgSSNDWvTFR-S25vye4vLvqIK8XXYx',
        },
        success: function (data) {
            console.log(data);
        }
    });
});