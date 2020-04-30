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

    //修改登陆密码
    $("#modal_save_changePassword").click(function () {

        change_password();
    });

    //登出逻辑
    $("#header_loginout").click(function () {

        loginout();
    });



});

function loginout() {

    $.ajax({

       url : "/graduationPro/api/loginout",
       type: "post",
       dataType: "json",
       success : function (result) {
           if (result.status == 1){
               window.location.href = "./login.html";
           }
       },
        error : function () {
            alert("请求失败");
        }
    });



}


function change_password() {

    var newPassword = $("#newPassword").val();

    var confirmPassword = $("#confirmPassword").val();

    if (newPassword != confirmPassword){

        alert("两次密码输入不一致");

        return;
    }

    $.ajax({

        url : "/graduationPro/api/password",
        type : "post",
        data :{
            "username" : getCookie("username"),
            "newPassword" : newPassword
        },
        dataType : "json",
        success : function (result) {
            if(result.status == 1){

                $('#changePasswordModal').modal('hide');

            }else {
                alert(result.message);
            }
        },
        error : function () {
            alert("请求失败");
        }
    });

}


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
