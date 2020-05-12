

$(function () {

    //获取教学评估的数量
    get_ste();

});



function get_ste() {

    $.ajax({

       url : "/graduationPro/api/teacher/teachingEval/",
        type : "get",
        data : {
           "name": getCookie("username")
        },
        dataType : "json",
        success : function (result) {

           if(result.status == 1){

               var data = result.data;

               $("#jxzl_yx").html(data.qualityYx);
               $("#jxzl_lh").html(data.qualityLh);
               $("#jxzl_yb").html(data.qualityYb);

               $("#kthd_yx").html(data.interactionYx);
               $("#kthd_lh").html(data.interactionLh);
               $("#kthd_yb").html(data.interactionYb);

               $("#ktxy_yx").html(data.novelYx);
               $("#ktxy_lh").html(data.novelLh);
               $("#ktxy_yb").html(data.novelYb);

               var youxiu = data.qualityYx + data.interactionYx + data.novelYx;
               // alert(youxiu)
               var lianghao = data.qualityLh + data.interactionLh + data.novelLh;
               // alert(lianghao)
               var yiban = data.qualityYb + data.interactionYb + data.novelYb;
               // alert(yiban)


               var ctx = document.getElementById('myChart');

               var myChart = new Chart(ctx, {
                   type: 'bar',
                   data: {
                       labels: ['优秀', '良好', '一般'],
                       datasets: [{
                           label: '教学评估查看',
                           data: [youxiu,lianghao,yiban],
                           backgroundColor: [
                               'rgba(255, 99, 132, 0.2)',
                               'rgba(54, 162, 235, 0.2)',
                               'rgba(255, 206, 86, 0.2)'
                           ],
                           borderColor: [
                               'rgba(255, 99, 132, 1)',
                               'rgba(54, 162, 235, 1)',
                               'rgba(255, 206, 86, 1)'
                           ],
                           borderWidth: 1
                       }]
                   },
                   options: {
                       scales: {
                           yAxes: [{
                               ticks: {
                                   beginAtZero: true
                               }
                           }]
                       }
                   }
               });



           }else {
               alert(result.message);
           }


        },
        error : function () {
            alert("请求失败");
        }



    });





}