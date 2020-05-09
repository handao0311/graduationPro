$(function () {

    //加载录入考试成绩页面
    load_enterTestScore();


});

function load_enterTestScore() {

    $("#le").click(function () {

        $("#teacher_body").load("./views/teacher/enterTestScore.html",function () {
            $.getScript("./static/js/teacher/enterTestScore.js");
        })
    });


}
