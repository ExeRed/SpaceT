var selectedSeat = null; // chosen seat
$(document).ready(function() {
    // kliker
    $('.seat').click(function() {
        // delete active in previous seat
        if (selectedSeat !== null) {
            $(selectedSeat).removeClass('active');
        }
        // choosing new and add active
        selectedSeat = this;
        $(selectedSeat).addClass('active');
    });

    document.getElementById("buy").addEventListener("click", function() {
        var selectedSeat = document.getElementsByClassName("active")[0];
        if (selectedSeat) {
            var row = selectedSeat.getAttribute("data-row");
            var column = selectedSeat.getAttribute("data-column");
            window.location.href = "/purchase?row=" + row + "&column=" + column;
        } else {
            alert("Please select a seat before buying!");
        }
    });

});