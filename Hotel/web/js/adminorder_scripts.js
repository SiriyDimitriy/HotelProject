$(document).ready(function () {
     
        $('.cost').each(function(i,elem) {
	if ($(this).text() == '0') {
            var parent = $(this).parent();
            parent.css('color','red');
            parent.css('font-weight','bold');
	}
        });
        $(".user").on('click', function (event) { 
            var target = $(event.target);
            var email = target.attr('myemail');
            var tel = target.attr('mytel');
            var name = target.attr('myname');
            target.html(name + "<br>" + email + "<br>" + tel);
        });
        $(".delete").on('click', function (event) { 
            var target = $(event.target);
            var orderid = target.attr('del');
            $.ajax({
                type: "POST",
                url: "ajaxDeleteOrder",
                data: {'orderid': orderid},
                success: function (data) {
                    $(".reload").click();
                },
                error: function (xhr, ajaxOptions, thrownError) {
                    alert(xhr.status);
                            alert(thrownError);
                },
                complete: function (data) {
                }

            });
        });
        $(".update").on('click', function (event) {
            
            var target = $(event.target);
            var parent = target.parent();
            var siblingCost = parent.prev();
            var siblingRoom = siblingCost.prev();
            var select = siblingRoom.children();
            roomNumber=select.val();
            var orderid = target.attr('upd');
            $.ajax({
                type: "POST",
                url: "ajaxSetOrder",
                data: {'orderid': orderid, 'roomNumber':roomNumber},
                success: function (data) {
                    $(".reload").click();
                },
                error: function (xhr, ajaxOptions, thrownError) {
                    alert(xhr.status);
                            alert(thrownError);
                },
                complete: function (data) {
                }

            });
            return false;
        });
    });


