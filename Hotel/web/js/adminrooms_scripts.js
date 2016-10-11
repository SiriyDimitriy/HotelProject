$(document).ready(function () {
        $(".comment").on('click', function (event) {
            var target = $(event.target);
            var roomid = target.attr('roomid');
            var innertarget = target.text();
            target.html('<input type="text" class="span3" value="' + innertarget + '">');
        });
        $(".update").on('click', function (event) {
            var target = $(event.target);
            var roomid = target.attr('name');
            var parent = target.parent();
            var previousParent = parent.prev();
            var input = previousParent.children();
            var comment = input.val();
            previousParent.html(comment);
            $.ajax({
                type: "POST",
                url: "ajaxRoom",
                data: {'roomid': roomid, 'comment':comment},
                success: function (data) {
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


