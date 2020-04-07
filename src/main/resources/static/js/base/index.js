var ready = false;
var initHeight = 0;

//加载公用信息
function initCommonInfo() {

}

//加载表头信息
function initTopInfo() {

}

//加载可见模块信息
function initModelInfo() {
    var html = '';
    jQuery.ajax({
        type: "post", //请求方式
        url: ctx + "/roleModel/getModel", //发送请求地址
        dataType: "json",
        async: false,
        data: {
            "userName": "",
            "pwd": ""
        },
        //请求成功pwd函数
        success: function (data) {
            if (data.data) {
            	// 父类集合
                var parentModels = new Array();
            	// 初始化子类数据map
                var dataMap = new HashMap();
                // 获取数据信息
                var datas = data.data.data;
				var childModels;
                for (var i = 0; i < datas.length; i++) {// 循环数据信息将数据放入map
                	// 为空添加父类
                	if(datas[i].parentId == null || datas[i].parentId == "" || datas[i].parentId == "-1"){
						parentModels.push(datas[i]);
					}else{
						if(dataMap.get(datas[i].parentId) == null){
							childModels = new Array();
						}else{
							childModels = dataMap.get(datas[i].parentId);
						}
						childModels.push(datas[i]);
						// 存储数据
						dataMap.put(datas[i].parentId, childModels);
					}
                }
                var childreD;
                for (var i = 0; i < parentModels.length; i++) {
                    childreD = dataMap.get(parentModels[i].sid);
                    html += '<li class="sub-menu"><a href="javascript:;"><i class="fa fa-desktop"></i>';
                    html += '<span>' + parentModels[i].modelName + '</span></a><ul class="sub">';
                    for (var j = 0; j < childreD.length; j++) {
                        html += '<li><a href="javascript:;" onclick="addCenter(\'' + childreD[j].modelUrl + '\')">' + childreD[j].modelName + '</a></li>';
                    }
                    html += '</ul></li>';
                }
            }
            $('.sidebar-menu').append(html);
            loadJs("../lib/common-scripts.js",function(){
            });
        },
        error: function (data) {
        }
    });
}

function loadJs(url,callback){
    var script=document.createElement('script');
    script.type="text/javascript";
    if(typeof(callback)!="undefined"){
        if(script.readyState){
            script.onreadystatechange=function(){
                if(script.readyState == "loaded" || script.readyState == "complete"){
                    script.onreadystatechange=null;
                    callback();
                }
            }
        }else{
            script.onload=function(){
                callback();
            }
        }
    }
    script.src=url;
    document.body.appendChild(script);
}
// 加载首页显示信息
function initMainDisplayInfo() {

}

function addCenter(url){
    $('#centerFrame').attr('src',url);
    $('#centerFrame').src = url;
}