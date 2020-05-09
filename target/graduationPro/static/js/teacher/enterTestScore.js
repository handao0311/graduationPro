
var studentIDList = [];
$(function () {

    //加载学生姓名,班级,学号
    load_studentInfo();

    // 上传学生成绩
    $("#teacher_enterscore_button").click(function () {
        upload_student_score();
    });
});


function upload_student_score() {

    var info = [];

    for (var i=0 ; i<studentIDList.length ; i++){

        var studentID = studentIDList[i];

        data = {
            "teacherName" : getCookie("username"),
            "studentID" : studentID,
            "score" : $("#" + studentID + "score").val(),
            "credit" : $("#" + studentID + "credit").val()
        }

        info.push(data)
    }

    $.ajax({

        url : "/graduationPro/api/teacher/score/",
        type : "post",
        data : JSON.stringify(info),
        contentType : "application/json",
        dataType : "json",
        success : function (result) {
            alert("请求成功")
            if (result.status == 1){
                alert(111)
                var score_div = "<div class=\"jumbotron\">" +
                                    "  <h1>成绩录入完成!</h1>\n" +
                                "</div>";
                alert(222)
                $("#teacher_body_div").html("");
                $("#teacher_body_div").append(score_div);

            }else {
                alert(result.message);
            }
        },
        error : function () {
            alert("请求失败");
        }

    });

}


function load_studentInfo() {

    $.ajax({

       url : "/graduationPro/api/teacher/score/",
       type : "get",
       dataType : "json",
       success : function (result) {

            if (result.status == 1){

                var data = result.data;

                $(data).each(function (index,obj) {

                    var tr = "<tr>" +
                            "<td>"+ obj.name +"</td>" +
                            "<td id = \"" + obj.studentID + "\">"+ obj.studentID +"</td>" +
                            "<td>"+ obj.grade +"</td>" +
                            "<td><input id=\"" + obj.studentID +"score\"type=\"text\"></td>" +
                            "<td><input id=\"" + obj.studentID +"credit\"type=\"text\"></td>" +
                        "</tr>";

                    studentIDList.push(obj.studentID);

                    $("#teacher_enterscore_table").append(tr);
                });

            } else {
                alert(result.message)
            }
       },
        error : function () {
            alert("请求失败");
        }
    });


}