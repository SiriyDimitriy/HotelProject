$(document).ready(function () {
    $("#clear").on('click', function (event) {
        $("#name").val("");
        $("#email").val("");
        $("#tel").val("");
        $("#datepicker-1").val("");
        $("#datepicker-2").val("");
        $("#comment").val("");
    });

    $("#approve").on('click', function (event) {
        var regName = /^([A-Za-zа-яА-Я\s]+)$/;
        var regEmail = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,6})+$/;
        var regTel = /^\+{1}\d{12}$/;

        var date1 = StrToDate($("#datepicker-1").val());
        var date2 = StrToDate($("#datepicker-2").val());
        var now = new Date();

        var errors = false;

        var s = "<b>В заполнении формы Вы допустили следующие ошибки:</b><br><br>";
        if ($("input").is("#name") || $("input").is("#email") || $("input").is("#tel")) {
            if (!regName.test($("#name").val())) {
                s += "Некорректно введенное имя - допускаются только буквы<br>";
                errors = true;
                $("#name").val("");
            }
            if (!regEmail.test($("#email").val())) {
                s += "Некорректно введенный электронный адрес - введите, пожалуйста, существующий<br>";
                errors = true;
                $("#email").val("");
            }
            if (!regTel.test($("#tel").val())) {
                s += "Некорректно введенный телефон - вводите в формате +380*********<br>";
                errors = true;
                $("#tel").val("");
            }
        }
        if (date1 == 'Invalid Date' || date2 == 'Invalid Date') {
            s += "Введена несуществующая дата, воспользуйтесь календарём<br>";
            errors = true;
            $("#datepicker-1").val("");
            $("#datepicker-2").val("");
        } else {
            if (now.getTime() > date1.getTime()) {
                s += "Дата въезда некорректна или прошла<br>";
                errors = true;
                $("#datepicker-1").val("");
            }

            if (now.getTime() > date2.getTime()) {
                s += "Дата выезда некорректна или прошла<br>";
                errors = true;
                $("#datepicker-2").val("");
            }

            if (date1.getTime() > date2.getTime()) {
                s += "Дата въезда позже даты выезда<br>";
                errors = true;
                $("#datepicker-2").val("");
            }
        }
        if (errors) {
            alert("При заполнении формы возникли ошибки");
            $(".cont").html(s);
            return false;
        }
        return true;
    });
    function StrToDate(Dat) {
        var data = new Date(Dat.replace(/(\d+).(\d+).(\d+)/, '$3/$2/$1'));
        return data;
    }
});