// Login Form

$(function() {
    var button = $('#logoutButton');
   // button.removeAttr('href');
    button.mouseup(function(logout) {
        button.toggleClass('active');
    });
    
    $(this).mouseup(function(logout) {
        if(!($(logout.target).parent('#logoutButton').length > 0)) {
            button.removeClass('active');
        }
    });
});
