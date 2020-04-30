$(function () {

    //初始化加载学生通讯录
    load_studentMailList();

    //模糊查询

    $("#mailList_head_div_name").submit(function () {
        submit_query();
    });

});

function submit_query() {

    load_studentMailList();

}


function load_studentMailList() {

    var mailList_head_name_value = $("#mailList_head_name").val();
    var mailList_head_class_value = $("#mailList_head_class").val();

    if (mailList_head_name_value == ""){

        mailList_head_name_value = "name";
    }

    if (mailList_head_class_value == ""){

        mailList_head_class_value = "grade";
    }

    $.ajax({

        url : "/graduationPro/api/mailList/",
        type : "get",
        data : {
            "name" : mailList_head_name_value,
            "grade" : mailList_head_class_value
        },
        dataType : "json",
        success : function (result) {
            // alert("请求成功");
            var page = result.data;
            info = page.data;

            load_table();
            if (page.totalCount >= 3) {

                for(var i=0 ; i < page.pageSize ; i++){
                    var tr = "<tr>" +
                        "<td>"+ info[i].name +"</td>" +
                        "<td>" + info[i].nikeName + "</td>" +
                        "<td>" + info[i].studentID + "</td>" +
                        "<td>" + info[i].qq + "</td>" +
                        "<td>" + info[i].motto + "</td>" +
                        "</tr>"

                    $("#mailList_table").append(tr);

                }
            }else {

                for(var i=0 ; i < page.totalCount ; i++){
                    var tr = "<tr>" +
                        "<td>"+ info[i].name +"</td>" +
                        "<td>" + info[i].nikeName + "</td>" +
                        "<td>" + info[i].studentID + "</td>" +
                        "<td>" + info[i].qq + "</td>" +
                        "<td>" + info[i].motto + "</td>" +
                        "</tr>"

                    $("#mailList_table").append(tr);

                }

            }

            $(".M-box").pagination({
                pageCount : page.totalPage,
                coping: true,
                homePage: "首页",
                endPage: "尾页",
                prevContent: "上页",
                nextContent: "下页",
                current: 1,
                callback : function (api) {

                    $.ajax({

                        url: "/graduationPro/api/mailListPage",
                        type: "get",
                        data : {
                            currentPage : api.getCurrent(),
                            "name" : mailList_head_name_value,
                            "grade" : mailList_head_class_value
                        },
                        dataType: "json",
                        success : function (res) {
                            alert("内部请求成功");
                            load_table();
                            var page1 = res.data;
                            var tel = page1.data;
                            $(tel).each(function (index,obj) {

                                var tr1 = "<tr>" +
                                        "<td>"+ obj.name +"</td>" +
                                        "<td>" + obj.nikeName + "</td>" +
                                        "<td>" + obj.studentID + "</td>" +
                                        "<td>" + obj.qq + "</td>" +
                                        "<td>" + obj.motto + "</td>" +
                                    "</tr>"

                                $("#mailList_table").append(tr1);
                            });

                        },
                        error : function () {
                            alert("内部请求失败");
                        }

                    });
                }
            });
        } ,
        error: function () {
            alert("请求失败");
        }

    });
    return false;
}

function load_table() {

    $("#mailList_table").html("");

    var thead = "<thead>" +
        "<tr>" +
        "<th>姓名</th>" +
        "<th>昵称</th>" +
        "<th>学号</th>" +
        "<th>QQ</th>" +
        "<th>座右铭</th>" +
        "</tr>" +
        "</thead>"

    $("#mailList_table").append(thead);

}
