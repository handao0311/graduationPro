//根据指定的cookie的key,去获取cookie中的value
function getCookie(objName){
	//key=value;expires=时间;
	//获取cookie的字符串,用分号拆开,拆成js的数组
	var arrStr = document.cookie.split(";");
	for(var i=0;i<arrStr.length;i++){
		var temp = arrStr[i].split("=");
		if(temp[0]==objName){
			
			return unescape(temp[1]);
		}
	}

	return '';
}

//添加cookie
function addCookie(objName,objValue,objHours){
	
	var str = objName+"="+escape(objValue);
	
	if(objHours>0){
		
		var ms = objHours*3600*1000;
		var date = new Date();
		date.setTime(ms+date.getTime());
		str+="; Expires="+date.toGMTString();

	}
	document.cookie=str;
	
	
}

//删除cookie
function deleteCookie(objName){
	
	var date=new Date();
	date.setTime(date.getTime()-1);
	
	var val = getCookie(objName);
	if(val!=null){
		
		document.cookie = objName+"="+val+"; Expires="+date.toGMTString();
		
	}
	
	
}

//设置cookie为30天
function setCookie(objName,objValue){
	
	var date = new Date();
	date.setTime(date.getTime()+30*24*3600*1000);
	
	document.cookie = objName+"="+escape(objValue)+"; Expires="+date.toGMTString();

}










