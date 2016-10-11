$(document).ready(function () {

    $('#ru_but').addClass('selected');
    $('#en_but').removeClass('selected');
    if ($('#lang').attr('attr') === 'en') {
        $('#en_but').addClass('selected');
        $('#ru_but').removeClass('selected');
    }
});