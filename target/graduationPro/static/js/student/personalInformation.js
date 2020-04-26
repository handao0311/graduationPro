$(function () {

    load_personalInformation();

    $("#changeInfo").click(function () {
        
        change_personalInformation();
    });

    $("#modal_confirm").click(function () {

        change_personalInformation_confirm();
    });


    $("form").submit(function () {

        return load_image();
    });

    load_studentImage();

});

//加载图片
function load_studentImage() {

    $.ajax({

        url : "/graduationPro/api/image/",
        type : "get",
        data : {
            "studentID" : $("#studentPF_left table tr:eq(3)").find("td").eq(1).text()
        },
        dataType : "json",
        success : function (result) {
            //alert(result.data)
            $("#studentImage").attr("src","./static/image/"+result.data);
            //alert("请求成功");
        },
        error : function () {
            alert("请求失败456");
        }
    })
}



//上传图片
function load_image() {

    var formData = new FormData($("#myform")[0]);
    formData.append("studentID",$("#studentPF_left table tr:eq(3)").find("td").eq(1).text());
    $.ajax({
        url:"/graduationPro/api/image/",
        type:"POST",
        dataType:"json",
        data : formData,
        processData: false,// 不加会报错
        contentType: false,// 不加会报错
        success:function(result) {

            alert(result.data.imgUrl)
            if (result.status == 1){

                $("#studentImage").attr("src","./static/image/"+result.data.imgUrl);
            }else {
                alert(result.message);
            }
        },
        error : function () {
            alert("请求失败");
        }
    })

    return false;
}


//模态框修改信息
function change_personalInformation_confirm() {

    var data = {
        "name" : $("#studentPF_left table tr:eq(0)").find("td").eq(1).text(),
        "nikeName" : $("#modalNikeName").val(),
        "qq" : $("#modalQQ").val(),
        "motto" : $("#modalMotto").val()
    }
    $.ajax({

        url : "/graduationPro/api/personalInformation/",
        type: "post",
        contentType : "application/json",
        dataType: "json",
        data : JSON.stringify(data),
        success : function (result) {

            if (result.status == 1) {
                $("#nikeName").html($("#modalNikeName").val());
                $("#qq").html($("#modalQQ").val());
                $("#motto").html($("#modalMotto").val());

                $('#myModal').modal('hide')
            }else {
                alert(result.message)
            }

        },
        error : function () {
            alert("请求失败");
        }
    });






}



// 点击编辑资料
function change_personalInformation() {

    $("#modalNikeName").val($("#studentPF_left table tr:eq(1)").find("td").eq(1).text());
    $("#modalQQ").val($("#studentPF_left table tr:eq(5)").find("td").eq(1).text());
    $("#modalMotto").val($("#studentPF_left table tr:eq(8)").find("td").eq(1).text());

}




//加载个人信息
function load_personalInformation() {

    // alert("a")
    //alert(getCookie("username"))
    // alert("c")
    $.ajax({
        url : "/graduationPro/api/personalInformation/",
        data : {
            "loginname" : getCookie("username")
        },
        type : "get",
        dataType : "json",
        success : function (result) {
            //alert(result.status)
            if (result.status == 1){
                //alert("taishuaile");
                $("#personalInformationTable").html("");
                
                var data = result.data;

                var name_tr = "<tr>" +
                                    "<td>姓名 :</td>" +
                                    "<td>"+data.name+"</td>" +
                                "</tr>"

                var nikeName_tr = "<tr>" +
                    "<td>昵称 :</td>" +
                    "<td id = \"nikeName\">"+data.nikeName+"</td>" +
                    "</tr>"

                var sex_tr = "<tr>" +
                    "<td>性别 :</td>" +
                    "<td>"+data.sex+"</td>" +
                    "</tr>"


                var studentID_tr = "<tr>" +
                    "<td>学号 :</td>" +
                    "<td>"+data.studentID+"</td>" +
                    "</tr>"


                var grade_tr = "<tr>" +
                    "<td>班级 :</td>" +
                    "<td>"+data.grade+"</td>" +
                    "</tr>"


                var qq_tr = "<tr>" +
                    "<td>QQ :</td>" +
                    "<td id = \"qq\">"+data.qq+"</td>" +
                    "</tr>"

                var dateOfBirth_tr = "<tr>" +
                    "<td>出生日期 :</td>" +
                    "<td>"+new Date(data.dateOfBirth).toLocaleDateString()+"</td>" +
                    "</tr>"

                var admissionTime_tr = "<tr>" +
                    "<td>入学时间 :</td>" +
                    "<td>"+data.admissionTime+"</td>" +
                    "</tr>"

                var motto_tr = "<tr>" +
                    "<td>座右铭 :</td>" +
                    "<td id = \"motto\">"+data.motto+"</td>" +
                    "</tr>"

                //
                $("#personalInformationTable").append(name_tr);
                $("#personalInformationTable").append(nikeName_tr);
                $("#personalInformationTable").append(sex_tr);
                $("#personalInformationTable").append(studentID_tr);
                $("#personalInformationTable").append(grade_tr);
                $("#personalInformationTable").append(qq_tr);
                $("#personalInformationTable").append(dateOfBirth_tr);
                $("#personalInformationTable").append(admissionTime_tr);
                $("#personalInformationTable").append(motto_tr);

            }
        },
        error : function () {
            alert("请求失败123")
        }

    });



}