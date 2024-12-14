$(document).ready(function() {
    $('#users-toggle-header').click(function() {
        $('#users-table').slideToggle();
        $(this).find('.arrow-down').toggleClass('rotate');
    });

    $('#flights-toggle-header').click(function() {
        $('#flights-table-wrapper').slideToggle();
        $(this).find('.arrow-down').toggleClass('rotate');
    });


});