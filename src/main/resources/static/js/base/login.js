function login() {
	var userName = jQuery('#userName').val();
	var pwd = jQuery('#pwd').val();
	pwd = hex_md5(pwd);
	jQuery.ajax({
		type : "post", //请求方式
		url : ctx + "/auth/login", //发送请求地址
		dataType : "json",
		async : false,
		data : {
			"userName" : userName,
			"pwd" : pwd
		},
		//请求成功pwd函数
		success : function(data) {
			if(data){
				if(data.status == 1){
					window.location = "/index.html";
				}else{
					alert(data.msg);
				}
			}
//			console.log(data)
//			if (data.data != null) {
//				if (data.data.sguanliyuanleixing != null && data.data.sguanliyuanleixing != '' && data.data.sguanliyuanleixing != undefined) {
//					if (data.data.sguanliyuanleixing == '1') {
//						window.location = ctx + "/sysindex.html";
//					} else if (data.data.sguanliyuanleixing == '3') {
//						alert('普通管理员');
//					} else if (data.data.sguanliyuanleixing == '0' &&
//						data.data.sguanlianleixing == "0000000100050001") {
//						SetPwdAndChk();
//						
//					} else {
//						alert('该用户没有权限');
//					}
//				} else {
//					alert('该用户没有权限');
//				}
//			} else {
//				alert('用户名或密码错误');
//			}
		},
		error : function(data) {
			console.log(data)
		}
	});
}

function GetLastUser() {
	var id = "49BAC005-7D5B-4231-8CEA-16939BEACD6FF7PT";
	var usr = GetCookie(id);
	if (usr != null) {
		GetPwdAndChk();
	}
}

//取Cookie的值 
function GetCookie(name) {
	var arg = name + "=";
	var alen = arg.length;
	var clen = document.cookie.length;
	var i = 0;
	while (i < clen) {
		var j = i + alen;
		if (document.cookie.substring(i, j) == arg)
			return getCookieVal(j);
		i = document.cookie.indexOf(" ", i) + 1;
		if (i == 0) break;
	}
	return null;
}

function getCookieVal(offset) {
	var endstr = document.cookie.indexOf(";", offset);
	if (endstr == -1)
		endstr = document.cookie.length;
	return unescape(document.cookie.substring(offset, endstr));
}

//写入到Cookie 
function SetCookie(name, value, expires) {
	var argv = SetCookie.arguments;
	//本例中length = 3 
	var argc = SetCookie.arguments.length;
	var expires = (argc > 2) ? argv[2] : null;
	var path = (argc > 3) ? argv[3] : null;
	var domain = (argc > 4) ? argv[4] : null;
	var secure = (argc > 5) ? argv[5] : false;
	document.cookie = name + "=" + escape(value) +
		((expires == null) ? "" : ("; expires=" + expires.toGMTString())) +
		((path == null) ? "" : ("; path=" + path)) +
		((domain == null) ? "" : ("; domain=" + domain)) +
		((secure == true) ? "; secure" : "") +
		";Set-Cookie=HttpOnly";
}

//点击登录时触发客户端事件 
function SetPwdAndChk() {
	//定义的id
	var id = "49BAC005-7D5B-4231-8CEA-16939BEACD6FF7PT";
	//将最后一个用户信息写入到Cookie 
	SetLastUser(jQuery('#userName').val());
	//如果记住密码选项被选中 
	if (document.getElementById('chkRememberPwd').checked == true) {
		//取密码值 
		var userName = jQuery('#userName').val();
		var expdate = new Date();
		expdate.setTime(expdate.getTime() + 14 * (24 * 60 * 60 * 1000));
		//将用户名和密码写入到Cookie 
		SetCookie(id, userName, expdate);

	} else {
		//如果没有选中记住密码,则立即过期 
		ResetCookie();
	}
}

function ResetCookie() {
	var id = "49BAC005-7D5B-4231-8CEA-16939BEACD6FF7PT";
	var expdate = new Date();
	SetCookie(id, null, expdate);
}

function SetLastUser(usr) {
	var id = "49BAC005-7D5B-4231-8CEA-16939BEACD6FF7PT";
	var expdate = new Date();
	//当前时间加上两周的时间 
	expdate.setTime(expdate.getTime() + 14 * (24 * 60 * 60 * 1000));
	SetCookie(id, usr, expdate);
}

//用户名失去焦点时调用该方法 
function GetPwdAndChk() {
	var usr = "49BAC005-7D5B-4231-8CEA-16939BEACD6FF7PT";
	var userName = GetCookie(usr);
	if (userName != null) {
		document.getElementById('chkRememberPwd').checked = true;
		jQuery('#userName').val(userName);
	} else {
		document.getElementById('chkRememberPwd').checked = false;
		jQuery('#userName').val('');
	}
}