$(function () {

    //加载用户名
    load_username();

    //加载学生的个人信息
    load_pf();


    //加载学生的成绩
    load_sq();

    //加载教学评估
    load_te();

    //加载同学通讯录
    load_ml();


});

function load_ml() {

    $("#ml").click(function () {

        $("#right_body").load("./views/student/mailList.html",function () {

            $.getScript("./static/js/student/mailList.js");

        });

    });

}


function load_te() {

    $("#te").click(function () {

        $("#right_body").load("./views/student/teachingEvaluation.html",function () {


            $.getScript("./static/js/student/teachingEvaluation.js");

        });

    });

}



function load_sq() {

    $("#sq").click(function () {

        $("#right_body").load("./views/student/scoreQuery.html",function () {


            $.getScript("./static/js/student/scoreQuery.js");

        });

    });

}




function load_username() {

    //alert(111);
    $("#username").html(getCookie("username"));

}

function load_pf(){

    $("#pf").click(function () {
        //alert($("#right_body"));
        $("#right_body").load("./views/student/personalInformation.html",function () {
            $.getScript("./static/js/student/personalInformation.js");
        });
    });

}