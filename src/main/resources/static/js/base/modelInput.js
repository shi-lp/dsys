$(function() {
	loadClientType();
	$('#bmodelCommit').keydown(function(event) {
		if (event.keyCode == 13) {
			modelCommit();
		}
	});
});

function modelCommit() {
	jQuery.ajax({
		url : ctx + "/roleModel/addModel", //发送请求地址
		type : "post", //请求方式
		contentType : 'application/json;charset=utf-8',
		dataType : "json",
		async : false,
		data : $("#addModelForm").parseForm(),
		success : function(data) {
			if (data.status == 1) {
				// window.location = ctx + "/login.html";
			} 
			alert(data.msg);
		},
		error : function(data) {
			alert("模块新增失败，请联系管理员！");
		}
	});
}

function changeLevel(data){
	if(data == 1){
		$('#dParentId').style.display = 'none';
	}else if(data == 2){
		$('#dParentId').style.display = '';
	}
}

// 从数据字典获取配置链接类型
function loadClientType(){
	
}