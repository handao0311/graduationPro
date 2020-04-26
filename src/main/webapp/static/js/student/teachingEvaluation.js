$(function () {

    load_teachEval();



   $("#offer").click(function () {

        var english = $("#english").html();
        var englishTeacher = $("#englishTeacher").html();
        var englishSelectQuality = $("#englishSelectQuality").find("option:selected").text();
        var englishSelectInteraction = $("#englishSelectInteraction").find("option:selected").text();
        var englishSelectNovel = $("#englishSelectNovel").find("option:selected").text();

       var math = $("#math").html();
       var mathTeacher = $("#mathTeacher").html();
       var mathSelectQuality = $("#mathSelectQuality").find("option:selected").text();
       var mathSelectInteraction = $("#mathSelectInteraction").find("option:selected").text();
       var mathSelectNovel = $("#mathSelectNovel").find("option:selected").text();

       var c = $("#c").html();
       var cTeacher = $("#cTeacher").html();
       var cSelectQuality = $("#cSelectQuality").find("option:selected").text();
       var cSelectInteraction = $("#cSelectInteraction").find("option:selected").text();
       var cSelectNovel = $("#cSelectNovel").find("option:selected").text();

       var linearAlgebra = $("#linearAlgebra").html();
       var linearAlgebraTeacher = $("#linearAlgebraTeacher").html();
       var linearAlgebraSelectQuality = $("#linearAlgebraSelectQuality").find("option:selected").text();
       var linearAlgebraSelectInteraction = $("#linearAlgebraSelectInteraction").find("option:selected").text();
       var linearAlgebraSelectNovel = $("#linearAlgebraSelectNovel").find("option:selected").text();

       var history = $("#history").html();
       var historyTeacher = $("#historyTeacher").html();
       var historySelectQuality = $("#historySelectQuality").find("option:selected").text();
       var historySelectInteraction = $("#historySelectInteraction").find("option:selected").text();
       var historySelectNovel = $("#historySelectNovel").find("option:selected").text();

       var name = getCookie("username");
       var teachEval = [

           {
               "studentName" : name,
               "courseName" : english,
               "teacherName" : englishTeacher,
               "quality": englishSelectQuality,
               "interaction" : englishSelectInteraction,
               "novel" : englishSelectNovel
           },

           {
               "studentName" : name,
               "courseName" : math,
               "teacherName" : mathTeacher,
               "quality": mathSelectQuality,
               "interaction" : mathSelectInteraction,
               "novel" : mathSelectNovel
           },

           {
               "studentName" : name,
               "courseName" : c,
               "teacherName" : cTeacher,
               "quality": cSelectQuality,
               "interaction" : cSelectInteraction,
               "novel" : cSelectNovel
           },

           {
               "studentName" : name,
               "courseName" : linearAlgebra,
               "teacherName" : linearAlgebraTeacher,
               "quality": linearAlgebraSelectQuality,
               "interaction" : linearAlgebraSelectInteraction,
               "novel" : linearAlgebraSelectNovel
           },

           {
               "studentName" : name,
               "courseName" : history,
               "teacherName" : historyTeacher,
               "quality": historySelectQuality,
               "interaction" : historySelectInteraction,
               "novel" : historySelectNovel
           }


       ];

       $.ajax({

            url : "/graduationPro/api/teachingEval/",
            type : "post",
            data : JSON.stringify(teachEval),
           contentType : "application/json",
           dataType : "json",
           success : function (result) {

                if (result.status == 1){
                    $("#teach table select").hide();

                    $("#englishSelectQuality").next().html(englishSelectQuality);
                    $("#englishSelectInteraction").next().html(englishSelectInteraction);
                    $("#englishSelectNovel").next().html(englishSelectNovel);

                    $("#mathSelectQuality").next().html(mathSelectQuality);
                    $("#mathSelectInteraction").next().html(mathSelectInteraction);
                    $("#mathSelectNovel").next().html(mathSelectNovel);

                    $("#cSelectQuality").next().html(cSelectQuality);
                    $("#cSelectInteraction").next().html(cSelectInteraction);
                    $("#cSelectNovel").next().html(cSelectNovel);

                    $("#linearAlgebraSelectQuality").next().html(linearAlgebraSelectQuality);
                    $("#linearAlgebraSelectInteraction").next().html(linearAlgebraSelectInteraction);
                    $("#linearAlgebraSelectNovel").next().html(linearAlgebraSelectNovel);

                    $("#historySelectQuality").next().html(historySelectQuality);
                    $("#historySelectInteraction").next().html(historySelectInteraction);
                    $("#historySelectNovel").next().html(historySelectNovel);


                    $("#offer").hide();

                }else {
                    alert(result.message);
                }
           },
           error : function () {
               alert("请求失败");
           }


       });



   });

});

function load_teachEval() {

    $.ajax({

        url : "/graduationPro/api/teachingEval",
        type: "get",
        data: {
            "name" : getCookie("username")
        },
        dataType: "json",
        success : function (result) {

            if (result.status == 1) {

                $("#teach table select").hide();

                data = result.data;

                $("#englishSelectQuality").next().html(data[0].quality);
                $("#englishSelectInteraction").next().html(data[0].interaction);
                $("#englishSelectNovel").next().html(data[0].novel);

                $("#mathSelectQuality").next().html(data[1].quality);
                $("#mathSelectInteraction").next().html(data[1].interaction);
                $("#mathSelectNovel").next().html(data[1].novel);

                $("#cSelectQuality").next().html(data[2].quality);
                $("#cSelectInteraction").next().html(data[2].interaction);
                $("#cSelectNovel").next().html(data[2].novel);

                $("#linearAlgebraSelectQuality").next().html(data[3].quality);
                $("#linearAlgebraSelectInteraction").next().html(data[3].interaction);
                $("#linearAlgebraSelectNovel").next().html(data[3].novel);

                $("#historySelectQuality").next().html(data[4].quality);
                $("#historySelectInteraction").next().html(data[4].interaction);
                $("#historySelectNovel").next().html(data[4].novel);


                $("#offer").hide();

            }else {
                alert(result.message);
            }

        },
        error : function () {
            alert("请求失败")
        }

    });


}