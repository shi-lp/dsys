/*这里是核心框架级别的js，一般都需要引用它*/
jQuery.ajaxSetup({
	cache : false
//关闭AJAX相应的缓存
});
(function($) {
	$.fn.parseForm = function() {
		var serializeObj = {};
		// 目标json
		var tempObj = {};
		// 中间json，存放对象或List
		var midObj = {};
		var objName = "";
		var paramName = "";
		// 讲字符串转换未数组进行循环操作 
		var array = this.serializeArray();
		var str = this.serialize();
		$(array).each(function() {
			if (serializeObj[this.name]) {
				if ($.isArray(serializeObj[this.name])) {
					serializeObj[this.name].push(this.value);
				} else {
					serializeObj[this.name] = [ serializeObj[this.name], this.value ];
				}
			} else {
				// 判断是否是对象属性
				if (this.name.indexOf(".") != -1) {
					var index = this.name.lastIndexOf(".");
					objName = this.name.substring(0, index);
					paramName = this.name.substring(index + 1, this.name.length);
					if (this.name.indexOf("[") != -1) { // 列表对象

					} else { // 单对象
						midObj[paramName] = this.value;
					}
				} else {
					serializeObj[this.name] = this.value;
				}
			}
		});
//		serializeObj[objName] = midObj;
		serializeObj = midObj;
		return JSON.stringify(serializeObj);
	};
	$.fn.serializeJson = function() {
		var jsonData1 = {};
		var serializeArray = this.serializeArray();
		// 先转换成{"id": ["12","14"], "name": ["aaa","bbb"], "pwd":["pwd1","pwd2"]}这种形式
		$(serializeArray).each(function() {
			if (jsonData1[this.name]) {
				if ($.isArray(jsonData1[this.name])) {
					jsonData1[this.name].push(this.value);
				} else {
					jsonData1[this.name] = [ jsonData1[this.name], this.value ];
				}
			} else {
				jsonData1[this.name] = this.value;
			}
		});
		// 再转成[{"id": "12", "name": "aaa", "pwd":"pwd1"},{"id": "14", "name": "bb", "pwd":"pwd2"}]的形式
		var vCount = 0;
		// 计算json内部的数组最大长度
		for (var item in jsonData1) {
			var tmp = $.isArray(jsonData1[item]) ? jsonData1[item].length : 1;
			vCount = (tmp > vCount) ? tmp : vCount;
		}

		if (vCount > 1) {
			var jsonData2 = new Array();
			for (var i = 0; i < vCount; i++) {
				var jsonObj = {};
				for (var item in jsonData1) {
					jsonObj[item] = jsonData1[item][i];
				}
				jsonData2.push(jsonObj);
			}
			return jsonData2;
		} else {
			return jsonData1;
		}
	};
})(jQuery);

/**Pingtech 命名空间，所有核心的组件都基于他开发*/

var Zytech = window.Zytech || {};

/*在使用的时候  使用Zytech的相关组件，可以是用pt.xxx 调用方法*/
(function(window) {
	window.pt = window.zytech = Zytech;
})(window);

/**bdp 命名空间，里面提供了一些常用的核心工具类的封装**/
var bdp = window.bdp || {};

/**
 * 和应用系统相关的
 * @return {TypeName} 
 */
Zytech.App = {
	/**项目的上下文路径*/
	getContext : function() {
		var appHost = "";
		/*var url = window.location.pathname + ".basePath";
		$.ajax({
			type : "POST",
			url : url,
			async : false,
			type : "post",
			dataType : 'json',
			data : {
				basePath : "getHost"
			},
			success : function(data) {
				appHost = data.host;
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				//				alert("操作后台异常：");
			}
		});*/
		return appHost;
	},
	/**项目的全路径*/
	getBaseUrl : function() {
		var appHost = "";
		/*var url = window.location.pathname + ".basePath";
		$.ajax({
			type : "POST",
			url : url,
			async : false,
			type : "post",
			dataType : 'json',
			data : {
				basePath : "getHostFull"
			},
			success : function(data) {
				appHost = data.host;
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				//				alert("操作后台异常：");
			}
		});*/
		return appHost;
	}
};

/**
 * url相关的便捷工具类
 * 1.从url中获取指定参数
 * @memberOf {TypeName} 
 * @return {TypeName} 
 */
Zytech.App.URLUtils = (function() {
	var url = window.location.href,
		paraString = url.substring(url.indexOf("?") + 1, url.length).split("&"),
		paraObj = {};
	isLoad = false;

	return {
		/**
		 * 用来给单个参数值解码
		 * @param {Object} url
		 * @return {TypeName} 
		 */
		urlDecode : function(url) {
			return decodeURIComponent(decodeURIComponent(url));
		},

		/**
		 * 用来给单个参数值编码
		 * @param {Object} url
		 * @return {TypeName}  编码后的字符串
		 */
		urlEncode : function(url) {
			return encodeURIComponent(encodeURIComponent(url));
		},

		/**
		 * 通过名称获取url的参数
		 * @param {Object} name
		 * @return {TypeName}  返回参数值
		 */
		params : function(name) {
			if (!isLoad) {
				for (var i = 0; j = paraString[i]; i++) {
					var paramVal = j.substring(j.indexOf("=") + 1, j.length);
					paraObj[j.substring(0, j.indexOf("="))] = this.urlDecode(paramVal);
				}
			}
			return paraObj[name];
		}
	};
})();




/**
 * map 对象实现
 * 实例:
 * var map = new HashMap();</br>
		map.put("zhangsan","HHHHHHHHHH");</br>
		alert(map.get("zhangsan"));</br>
		alert(map.length());</br>
		alert(map.remove("zhangsan"));</br>
		alert(map.length());</br>		
		alert(map.get("zhangsan"));</br>
 * @author zohan 
 * @memberOf {TypeName} 
 */
function HashMap() {
	this.count = -1;
	this.container = {};
}



/**
 * 对象存放
 * @param {Object} key
 * @param {Object} obj
 * @memberOf {TypeName} 
 */
HashMap.prototype.put = function(key, obj) {
	this.container[key] = obj;
	this.count++;
};
/**
 * 对象移除
 * @param {Object} key
 * @memberOf {TypeName} 
 */
HashMap.prototype.remove = function(key) {
	delete this.container[key];
	this.count--;
};
/**
 * 对象的个数 
 * @memberOf {TypeName} 
 * @return {TypeName} 
 */
HashMap.prototype.length = function() {
	return this.count + 1;
};
/**
 * 获取对象
 * @param {Object} key
 * @memberOf {TypeName} 
 * @return {TypeName} 
 */
HashMap.prototype.get = function(key) {
	return this.container[key];
};



/**路径封装
 * ctx的值类似：/zytech/
 * @param {Object} window
 */
(function(window) {
	window.ctx = window.ctx || Zytech.App.getContext();
	window.baseUrl = window.baseUrl || Zytech.App.getBaseUrl();
	/**
	 * 使用方法 :存放对象bdp.data('key',value)</br>
	 * 获取对象，bdp.data(key)返回存储的对象，不存在返回 undefined</br>
	 * 支持iframe。
	 * @author zohan
	 * @param {Object} k
	 * @param {Object} v
	 * @return {TypeName} 
	 */
	window.bdp.data = top.bdp.data || function(k, v) {
		window.dataMap = top.dataMap || new HashMap();
		if (k != undefined && v != undefined) {
			dataMap.put(k, v);
		} else {
			return dataMap.get(k);
		}
		return v;
	};
})(window);


/**
 * 动态加载外部js
 * @param {Object} url
 */
Zytech.App.loadScript = function(url) {
	var script = document.createElement("script");
	script.type = "text/javascript";
	script.src = url;
	document.body.appendChild(script);
};

/**
 *  动态加载外部css
 * @param {Object} url
 */
Zytech.App.loadStyle = function(url) {
	var link = document.createElement("link"),
		head = document
			.getElementsByTagName("head")[0];
	link.rel = "stylesheet";
	link.type = "text/css";
	link.href = url;
	head.appendChild(link);
};
/**
 *  获取url的参数 存入需要获取的参数
 * @param {Object} url
 * @returns 返回空串 
 */
String.prototype.GetValue = function(para) {
	var reg = new RegExp("(^|&)" + para + "=([^&]*)(&|$)");
	var r = this.substr(this.indexOf("\?") + 1).match(reg);
	if (r != null) return unescape(r[2]);
	return "";
};

/**
 * 
 * @param data 数据
 * @param entityAs 实体as名称
 * @param entityManager 实体信息
 */
function setInputValue(data, entityAs, entityManager) {
	// 设置数据字符串
	var str = "data.";
	// 设置字段
	var objs = entityManager.allFields;
	// 设置实体的as名称
	var tempStr = entityAs + ".";
	// 设置name名称
	var nameStr = "";
	// 设置临时存储数据字符串
	var temp = "";
	// 设置临时存储val
	var tempVal = "";
	// 循环数据
	for (var i = 0; i < objs.length; i++) {
		// 获取数据对象信息
		temp = str + objs[i].name;
		// 获取名称信息
		nameStr = tempStr + objs[i].name;
		// 获取数据值
		tempVal = eval(temp);
		// 获取表单中name值为对象中 将值设置为获取到的数据值
		$("[name='" + nameStr + "']").each(function() {
			if (tempVal) {
				if (this.nodeName == "INPUT" || this.nodeName == "TEXTAREA" || this.nodeName == "SELECT") {
					$(this).val(tempVal);
				} else {
					$(this).html(tempVal);
				}
			}
		});
	}
}