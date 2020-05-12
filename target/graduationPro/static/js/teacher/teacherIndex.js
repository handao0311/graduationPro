$(function () {

    //加载录入考试成绩页面
    load_enterTestScore();

    //加载查看教学评估
    load_seeTeachingEvaluation();

});

function load_seeTeachingEvaluation() {

    $("#ste").click(function () {

        $("#teacher_body").load("./views/teacher/seeTeachingEvaluation.html",function () {
            $.getScript("./static/js/teacher/seeTeachingEvaluation.js");
        })
    });

}

function load_enterTestScore() {

    $("#le").click(function () {

        $("#teacher_body").load("./views/teacher/enterTestScore.html",function () {
            $.getScript("./static/js/teacher/enterTestScore.js");
        })
    });


}
