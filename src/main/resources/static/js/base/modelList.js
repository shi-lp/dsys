$(function(){
	loadModelList();
});

function loadModelList(){
	var html = '';
	jQuery.ajax({
		type : "post", //请求方式
		url : ctx + "/roleModel/getAllModel", //发送请求地址
		dataType : "json",
		async : false,
		data : {
			"userId" :""
		},
		//请求成功pwd函数
		success : function(data) {
			console.log(data)
			if(data){
				var datas = data.data;
				for(var i = 0 ; i < datas.length ; i ++){
					html += '<tr><td>';
					html += datas[i].modelName + '</td>';
					html += '<td>' + datas[i].level + '</td>';
					html += '<td>' + datas[i].clientType + '</td>';
					html += '<td>' + datas[i].modelOrder + '</td>';
					html += '<td>' + datas[i].doFlag + '</td>';
					html += '<td><button class="btn btn-success btn-xs" onclick=enableModel(\''+datas[i].sId+'\')><i class="fa fa-check"></i></button>';
					html += '<button class="btn btn-primary btn-xs" onclick=editModel(\''+datas[i].sId+'\')><i class="fa fa-pencil"></i></button>';
					html += '<button class="btn btn-danger btn-xs" onclick=deleModel(\''+datas[i].sId+'\')><i class="fa fa-trash-o "></i></button>';
					html += '</td></tr>';
					
				}
			}
			$('#modelList').append(html);
		},
		error : function(data) {
		}
	});
}

function addModel(){
	
}
// 模块启用
function enableModel(sid){
	
}

// 模块编辑
function editModel(sid){
	
}

// 模块删除
function deleModel(sid){
	
}