$(function() {
	$('#bregister').keydown(function(event) {
		if (event.keyCode == 13) {
			register();
		}
	});
});

function register() {
	var pwd = $('#pwd').val();
	$('#pwd').val(hex_md5(pwd));
	$('#pwd1').val(hex_md5(pwd));
	jQuery.ajax({
		url : ctx + "/user/regeisterUser", //发送请求地址
		type : "post", //请求方式
		contentType : 'application/json;charset=utf-8',
		dataType : "json",
		async : false,
		data : $("#registerForm").parseForm(),
		//请求成功pwd函数
		success : function(data) {
			if (data.msg == 1) {
				alert("注册成功，现在可以登录了！");
				window.location = ctx + "/login.html";
			} else {
				alert("注册失败，请联系管理员！");
			}
		},
		error : function(data) {
			alert("注册失败，请联系管理员！");
		}
	});
}

// 判断注册账号是否存在
function doExist() {
	var account = jQuery('#account').val();
	if (account) {
		jQuery.ajax({
			url : ctx + "/user/accountIsExist", //发送请求地址
			type : "post", //请求方式
			dataType : "json",
			async : false,
			data : {
				"account" : jQuery('#account').val()
			},
			//请求成功pwd函数
			success : function(data) {
				if (data.status == 1) {
					$('#vaccount').html(data.msg);
				} else {
					$('#vaccount').html('');
				}
			},
			error : function(data) {
				console.log(data)
			}
		});
	}
}


// 发送手机号验证
function sendSMS() {
	var phone = jQuery('#phone').val();
	if (phone) {
		jQuery.ajax({
			url : ctx + "/sign/sendPhone", //发送请求地址
			type : "post", //请求方式
			dataType : "json",
			async : false,
			data : {
				"phoneNumber" : phone
			},
			//请求成功pwd函数
			success : function(data) {
				if (data.status == 1) {
					$('#veMail').html(data.msg);
				} else {
					$('#veMail').html('');
				}
			},
			error : function(data) {
				console.log(data)
			}
		});
	}
}

// 发送邮件验证
function sendMail() {
	var eMail = jQuery('#eMail').val();
	if (eMail) {
		jQuery.ajax({
			url : ctx + "/sign/sendEMail", //发送请求地址
			type : "post", //请求方式
			dataType : "json",
			async : false,
			data : {
				"eMail" : eMail
			},
			//请求成功pwd函数
			success : function(data) {
				if (data.status == 1) {
					$('#vphone').html(data.msg);
				} else {
					$('#vphone').html('');
				}
			},
			error : function(data) {
				console.log(data)
			}
		});
	}
}

//发送手机号验证
function sendSMS() {
	var phone = jQuery('#phone').val();
	if (phone) {
		jQuery.ajax({
			url : ctx + "/sign/signPhone", //发送请求地址
			type : "post", //请求方式
			dataType : "json",
			async : false,
			data : {
				"phoneNumber" : phone
			},
			//请求成功pwd函数
			success : function(data) {
				if (data.status == 1) {
					$('#vphone').html(data.msg);
				} else {
					$('#vphone').html('');
				}
			},
			error : function(data) {
				console.log(data)
			}
		});
	}
}

function signMail() {
	var eMail = jQuery('#eMail').val();
	if (eMail) {
		jQuery.ajax({
			url : ctx + "/sign/signEMail", //发送请求地址
			type : "post", //请求方式
			dataType : "json",
			async : false,
			data : {
				"eMail" : eMail
			},
			//请求成功pwd函数
			success : function(data) {
				if (data.status == 1) {
					$('#veMail').html(data.msg);
				} else {
					$('#veMail').html('');
				}
			},
			error : function(data) {
				console.log(data)
			}
		});
	}
}