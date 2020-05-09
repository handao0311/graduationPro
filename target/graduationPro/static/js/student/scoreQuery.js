
var table_data;

$(function () {

    $("#downloadPDF").hide();

   $("#determine").click(function () {
       query_score();
   });

    $("#downloadPDF").click(function () {

        download_PDF();

    })


});




function download_PDF() {

    $.ajax({

        url : "/graduationPro/api/pdf",
        type: "post",
        data : JSON.stringify(table_data),
        contentType : "application/json",
        dataType : "json",
        success : function (result) {
            alert("请求成功");

            url = result.data;
            alert(url)
            var d = new Date().getTime();
            var saveName= "hanchao2016last.pdf";//这里文件名我用了毫秒数加上后缀
            var $a = document.createElement('a');
            $a.setAttribute("href", url);
            $a.setAttribute("download", saveName);
            $a.setAttribute("target","_blank");//弹出窗体
            //模拟js事件
            var evObj = document.createEvent('MouseEvents');
            evObj.initMouseEvent( 'click', true, true, window, 0, 0, 0, 0, 0, false, false, true, false, 0, null);
            $a.dispatchEvent(evObj);

        },
        error : function () {
            alert("请求失败")
        }
    });
}


function query_score() {

    $("#scoreTable").html("");

    var name = getCookie("username");
    var year = $("#yearCategory").find("option:selected").text();
    var team = $("#teamCategory").find("option:selected").text();

    alert(name + "  " + year + "  " + team);

    $.ajax({

        url : "/graduationPro/api/score/",
        type : "get",
        data : {
            "name" : name,
            "year" : year,
            "team" : team
        },
        dataType : "json",
        success : function (result) {

            if (result.status == 1) {

                var thead = "<thead>" +
                                "<tr>" +
                                    "<th>姓名</th>" +
                                    "<th>学年</th>" +
                                    "<th>学期</th>" +
                                    "<th>课程名</th>" +
                                    "<th>课程编号</th>" +
                                    "<th>类型</th>" +
                                    "<th>学分</th>" +
                                    "<th>成绩</th>" +
                                "</tr>" +
                            "</thead>";

                $("#scoreTable").append(thead);


                var data = result.data;
                table_data = data;
                $(data).each(function (index,obj) {

                    if (obj.credit == null){
                        obj.credit = "敬请期待";
                    }

                    if (obj.score == null){
                        obj.score = "敬请期待";
                    }

                    var row = "<tr>" +
                                    "<td>"+ obj.name +"</td>" +
                                    "<td>"+ obj.year +"</td>" +
                                    "<td>"+ obj.team +"</td>" +
                                    "<td>"+ obj.courseName +"</td>" +
                                    "<td>"+ obj.courseID +"</td>" +
                                    "<td>"+ obj.type +"</td>" +
                                    "<td>"+ obj.credit +"</td>" +
                                    "<td>"+ obj.score +"</td>" +
                                "</tr>";

                    $("#scoreTable").append(row);

                });

                $("#downloadPDF").show();

            }else {
                alert(result.message);
            }

        },
        error : function () {
            alert("请求失败");
        }


    });




}