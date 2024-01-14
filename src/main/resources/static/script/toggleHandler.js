$(document).ready(function() {
    $('#users-toggle-header').click(function() {
        $('#users-table').slideToggle();
        $(this).find('.arrow-down').toggleClass('rotate');
    });

    $('#statistics-toggle-header').click(function() {
        $('#statistics-wrapper').slideToggle();
        $(this).find('.arrow-down').toggleClass('rotate');
    });

    $('#tickets-toggle-header').click(function() {
        $('#tickets-table').slideToggle();
        $(this).find('.arrow-down').toggleClass('rotate');
    });
});