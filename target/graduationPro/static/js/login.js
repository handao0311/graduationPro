$(function () {

    $("form #inputName").val(getCookie("loginName"));

    $("form").submit(function () {

        return login();
    });

})

function login() {

    var loginName = $("form #inputName").val();
    var password = $("form #inputPassword").val();
    var remember = $("form input[type=checkbox]:checked").val();

    var role = $("form input[type=radio]:checked").val();

    //alert(loginName + " " + password + " " + remember + " " + role);

    $.ajax({

        url: "/graduationPro/api/login/",
        data:{
            "username":loginName,
            "password":password,
            "role":role
        },
        type: "get",
        dataType: "json",
        success: function (result) {
            //alert("请求成功");

            if (result.status == 1) {

                addCookie("username",loginName,20);

                if (role == "student") {

                    if (remember == "remember"){
                        addCookie("loginName",loginName,5);
                    }

                    //alert("即将进入学生主页");
                    window.location.href = "./studentIndex.html";
                }else if(role == "teacher"){

                    if (remember == "remember"){
                        addCookie("loginName",loginName,5);
                    }
                    //alert("即将进入教师主页");
                    window.location.href = "./teacherIndex.html";
                }else if (role == "manager"){

                    if (remember == "remember"){
                        addCookie("loginName",loginName,5);
                    }
                    //alert("即将进入管理员主页");
                    window.location.href = "./views/managerIndex.html";
                }
            }else {
                alert("用户名或密码输入不正确");
                $("#inputPassword").val("")
            }

        },
        error: function () {
            alert("请求失败");
        }
    });

    return false;
}