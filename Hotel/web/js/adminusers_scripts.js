$(document).ready(function () {
        $(".popup-link-1").on('click', function (event) {
            var target = $(event.target);
            var parent = target.parent();
            var email = parent.attr('id')
            $.ajax({
                type: "POST",
                url: "ajaxOrders",
                data: {'email': email},
                success: function (data) {

                    var s = '<div class="popup-box" id="popup-box-1">';
                    s += '<div class="close">X</div><div class="top">';
                    if (!data) {
                        s += '<h2>У данного пользователя нет заказов</h2>';
                        s += '</div></div>';
                    } else {
                        var jsonOrders = JSON.parse(data);
                        s += '<h2>История заказов пользователя ' + jsonOrders[0]['useremail'] + '</h2>';
                        s += '</div>';
                        s += '<div class="bottom"><table class="table table-bordred"><thead>';
                        s += '<tr><th>№<br>Заказа</th><th>Дата заявки</th>';
                        s += '<th>Дата вьезда</th><th>Дата выезда</th>';
                        s += '<th>Кол-во<br>мест</th><th>Тип <br>номера</th>';
                        s += '<th>Комментарий</th><th>№ номера</th><th>Стоимость</th>';
                        s += '</tr></thead><tbody>';
                        jsonOrders.forEach(function (item, i, jsonOrders) {
                            

                            s += '<tr><td align="center">' + item['id'] + '</td>'
                            s += '<td align="center">' + item['orderdate'] + '</td>';
                            s += '<td align="center">' + item['startdate'] + '</td>';
                            s += '<td align="center">' + item['enddate'] + '</td>';
                            s += '<td align="center">' + item['places'] + '</td>';
                            s += '<td align="center">' + item['class'] + '</td>';
                            s += '<td align="center">' + item['usercomment'] + '</td>';
                            s += '<td align="center">' + item['roomnumber'] + '</td>';
                            s += '<td align="center">' + item['cost'] + '</td></tr>';
                        });
                        s += '</tbody></table></div></div>';
                    }

                    $('body').append(s);

                    $('body').append('<div id="blackout"></div>');

                    var boxWidth = 900;

                    function centerBox() {

                        var winWidth = $(window).width();
                        var winHeight = $(document).height();
                        var scrollPos = $(window).scrollTop();

                        var disWidth = (winWidth - boxWidth) / 2
                        var disHeight = scrollPos + 150;

                        $('.popup-box').css({'width': boxWidth + 'px', 'left': disWidth + 'px', 'top': disHeight + 'px'});
                        $('#blackout').css({'width': winWidth + 'px', 'height': winHeight + 'px'});

                        return false;
                    }

                    $(window).resize(centerBox);
                    $(window).scroll(centerBox);
                    centerBox();

                    var scrollPos = $(window).scrollTop();

                    $('#popup-box-1').show();
                    $('#blackout').show();
                    $('html,body').css('overflow', 'hidden');


                    $('html').scrollTop(scrollPos);

                    $('[class*=popup-box]').click(function (e) {

                        e.stopPropagation();
                    });
                    $('html').click(function () {
                        var scrollPos = $(window).scrollTop();

                        $('[id^=popup-box-]').hide();
                        $('#blackout').hide();
                        $("html,body").css("overflow", "auto");
                        $('html').scrollTop(scrollPos);
                    });
                    $('.close').click(function () {
                        var scrollPos = $(window).scrollTop();
                        $('[id^=popup-box-]').remove();
                        //$('[id^=popup-box-]').hide();
                        $('#blackout').hide();
                        $("html,body").css("overflow", "auto");
                        $('html').scrollTop(scrollPos);

                    });
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

