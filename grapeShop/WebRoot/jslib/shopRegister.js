var idok=false;
var nameok=false;
var passwordok=false;
var passwordConfirmok=false;
var phoneok=false;
var accountok=false;


function showShopIdTip(){
	idok=false;
	
	msg='<fieldset style="font-size:40%;color:#E00000;border:#E00000 dashed 1px;">此Id将作为店铺唯一标识，4~10个字符，包括数字下划线，字母，必须以英文字母开始</fieldset>';
	$("#shopIdTip").html(msg);
	$("#shopIdNews").html("");
	return;
}

function showShopNameTip(){
	
	nameok=false;
	msg='<fieldset style="font-size:40%;color:#E00000;border:#E00000 dashed 1px;">4~10个字符，包括数字下划线，字母，必须以英文字母开始</fieldset>';
	$("#shopNameTip").html(msg);
	$("#shopNameNews").html("");
	return;
}

function showShopPwdTip(){
	passwordok=false;
	
	msg='<fieldset style="font-size:80%;color:#E00000;border:#E00000 dashed 1px;">6~10个字符，区分大小写，不能以空格，用户名作为密码</fieldset>';
	$("#shopPwdTip").html(msg);
	$("#shopPwdNews").html("");
	return;
}

function showPwdConfirmTip(){
	passwordConfirmok=false;
	
	$("#pwdConfirmNews").html("");
}

function showPhoneTip(){
	phoneok=false;
	
	$("#phoneNews").html("");
}

function showAccountTip(){
	accountok=false;
	
	msg='<fieldset style="font-size:80%;color:#E00000;border:#E00000 dashed 1px;">请输入您的银行账户，该账户将作为您在本商城的支付账户</fieldset>';
	$("#accountTip").html(msg);
	$("#accountNews").html("");
	return;
}


function hideUserIdTip(){
	$("#shopIdTip").html("");
	return;
}
function hideShopNameTip(){
	$("#shopNameTip").html("");
	return;
}
function hideShopPwdTip(){
	$("#shopPwdTip").html("");
	return;
}

function  hideAccountTip(){
	$("#accountTip").html("");
	return;
}

function checkShopId(){
	idok=false;
	var shopId=$("#shopId").val();
	
	if(shopId.length<4){
		msg="店铺Id字符不能小于4.";
		msg='<div style="font-size:40%;color:#FF0000;">'+msg+'</div>';
		$("#shopIdNews").html(msg);
		return false;
	}
	//暂时先写成true，这个由下面的逻辑控制
	idok=true;
	$.get("servlet/UserHandleServlet?type=checkUserName?userName="+userName,null,writeUserNameInfo);
}
function writeUserNameInfo(data){
	//alert(data);
	if(data==EXISTEDNAME){
		msg="用户名重复";
	}else if(data==SUCCESS){
		msg="账号可用";
		idok=true;
	}else{
		msg="系统繁忙，稍后再试";
	}
	msg='<div style="font-size:40%;color:#00FF00">'+msg+'</div>';
	$("#shopIdNews").html(msg);
}
function checkShopName(){
	nameok=false;
	var shopName=$("#shopName").val();
	var result=$("#shopNameNews");
	if(shopName.length<4){
		msg="店铺名称字符不能小于4.";
		msg='<div style="font-size:40%;color:#FF0000;">'+msg+'</div>';
		$("#shopNameNews").html(msg);
		return false;
	}
	msg='<div style="font-size:40%;color:#FFFF3300;">店铺名称有效</div>';
	result.html(msg);
	nameok=true;
	
}

function checkpwd1(){
	passwordok=false;
	var shopPwd=$("#shopPwd").val();
	var result=$("#shopPwdNews");
	if(shopPwd.length<6){
		msg="店铺密码字符不能小于6位.";
		msg='<div style="font-size:40%;color:#FF0000;">'+msg+'</div>';
		result.html(msg);
		return false;
	}
	if(shopPwd.indexOf(" ")>=0){
		msg="密码不能有空格.";
		msg='<div style="font-size:40%;color:#FF0000;">'+msg+'</div>';
		result.html(msg);
		return false;
	}
	msg='<div style="font-size:40%;color:#FFFF33;">密码有效</div>';
	result.html(msg);
	passwordok=true;
	
}
function checkpwd2(){
	var shopPwd=$("#shopPwd").val();
	var shopPwd2=$("#pwdConfirm").val();
	var result=$("#pwdConfirmNews");
	if(shopPwd!=shopPwd2){
		msg='<div style="font-size:40%;color:#FF0000;">与第一次输入的密码不匹配</div>';
		result.html(msg);
		
		return false;
	}
		result.html("");
		passwordConfirmok=true;
	
}

function checkPhone(){
	
	var result=$("#phoneNews");
	var shopPhone=$("#shopPhone").val();
	if(shopPhone.length<1){
		msg="电话号码不能为空";
		msg='<div style="font-size:40%;color:#FF0000;">'+msg+'</div>';
		result.html(msg);
		return false;
	}
	result.html("");
	phoneok=true;
	
}

function checkAccount(){
	
	var result=$("#accountNews");
	var shopAccount=$("#shopAccount").val();
	if(shopAccount.length<1){
		msg="银行账号不能为空";
		msg='<div style="font-size:40%;color:#FF0000;">'+msg+'</div>';
		result.html(msg);
		return false;
	}
	result.html("");
	accountok=true;
	
}

function checkinfo(){
	var result=$("#register");
	if(idok==false||nameok==false||passwordok==false||passwordConfirmok==false||phoneok==false||accountok==false){
		result.attr("disabled",true);
	}else{
		result.attr("disabled",false);
	}
	
	return;
}

function reset(){
	var shopId=$("#shopId");
	shopId.html("");
	var shopName=$("#shopName");
	shopName.html("");
	var shopPwd=$("#shopPwd");
	shopPwd.html("");
	var pwdConfirm=$("#pwdConfirm");
	pwdConfirm.html("");
	var shopPhone=$("#shopPhone");
	shopPhone.html("");
	var shopAccount=$("#shopAccount");
	shopAccount.html("");
}




