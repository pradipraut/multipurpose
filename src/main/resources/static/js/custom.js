// ALERT FADE

window.setTimeout(function () {
    $(".alert").fadeTo(500, 0).slideUp(500, function () {
        $(this).remove();
    });
}, 4000);

/*----end----*/


// new form design

$(".mat-input").focus(function () {
    $(this).parent().addClass("is-active is-completed");
});

$(".mat-input").focusout(function () {
    if ($(this).val() === "")
        $(this).parent().removeClass("is-completed");
    $(this).parent().removeClass("is-active");
});

//tooltip
$(document).ready(function () {
    $('[data-toggle="tooltip"]').tooltip();
});