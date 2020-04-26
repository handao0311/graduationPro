$(function(){
	
	$("#button").click(function(){
		alert(1);
		load_info();
	})
	
});

function load_info(){
	
	$("#uls").load("./views/1.html",function(){
		alert("successful");
	}); //这句话不起作用
	alert($("#uls"))
	alert(2);
}
