$(document).ready(function () {
    widthFunctions();
});

function widthFunctions() {
    var winHeight = $(window).height();
    var winWidth = $(window).width();

    var contentHeight = $("#content").height();

    if (winHeight) {
        $("#content").css("min-height", winHeight);
    }

    if (contentHeight) {
        $("#sidebar-left2").css("height", contentHeight);
    }

}