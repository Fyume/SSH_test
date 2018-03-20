/**
 * 
 */
function login(user, path) {
	if (user == "true") {// 没登陆
		window.location.href = path + "/pages/user/login.jsp";
	} else {
		window.location.href = path + "/pages/user/User.jsp";
	}
}
function infoon() {
	$("#user_info").css("display", "inline");
}
function infooff() {
	$("#user_info").css("display", "none");
}
function del_U(uid){
	if (confirm("确定删除用户ID为 " + uid + " 的信息吗？")) {
		return true;
	}
	return false;
}
function del_B(bid){
	if (confirm("确定删除书本ID为 " + bid + " 的信息吗？")) {
		return true;
	}
	return false;
}
function alter_U(num, uid) {
	if (confirm("确定修改用户ID为 " + uid + " 的信息吗？")) {
		$.ajax({
			url : '/SSH_test/managerAction_alter_U',
			type : "POST",
			timeout : 1000,
			data : {
				json : JSON.stringify(getJsonData1(num))
			},
			async : false,// 取消异步请求
			dataType : "json",
			cache : false,
			success : function messageSuccess(data) {// 返回时的方法
				alert($("#u_permission" + num).val());
			},
		});
	}
}
function alter_B(num, bid) {
	if (checkPublish(num)) {// 检查日期
		if (confirm("确定修改书本ID为 " + bid + " 的信息吗？")) {
			$.ajax({
				url : '/SSH_test/managerAction_alter_B',
				type : "POST",
				timeout : 1000,
				data : {
					json : JSON.stringify(getJsonData2(num))
				},
				async : false,// 取消异步请求
				dataType : "json",
				cache : false,
				success : function messageSuccess(data) {// 返回时的方法
					alert($("#u_permission" + num).val());
				},
			});
		}
	}
}
function getJsonData1(num) {
	var json = {
		uid : $("#uid" + num).val(),
		username : $("#username" + num).val(),
		u_permission : parseInt($("#u_permission" + num).val())
	}
	return json;
}
function getJsonData2(num) {
	var json = {
		bid : $("#bid" + num).val(),
		bname : $("#bname" + num).val(),
		ISBN : $("#ISBN" + num).val(),
		year : $("#year" + num).val(),
		month : $("#month" + num).val(),
		date : $("#date" + num).val(),
		description : $("#description" + num).val(),
		type : $("#type" + num).val()
	}
	return json;
}
function page(DBRs) {
	if (DBRs == "true") {// 删除书本结果
		alert("删除成功");
	}
	var page = $("#page").val();
	for (var int = 1; int < 100; int++) {
		if (int == page) {
			$("#a_" + page).css("color", "red");
			break;
		}
	}
}
function page2(count) {
	var page = $("#page").val();
	$("#a_" + page).css("color", "red");
	$("#a_" + page).css("font-weight", "700");
	for (var int = 0; int < count; int++) {
		if (int != page) {
			$("#a_" + int).css("color", "black");
			$("#a_" + page).css("font-weight", "0");
		}
	}
}
function alter_Img(ID, bid) {
	/*window.Iid = ID;// 全局方便操作*/
	window.bid = bid;// 全局方便操作
	var src = $("#" + ID)[0].src;// 选择器选择img标签的结果是个数组
	$("#B-I").css("display", "block");
	$("#B-I").css("background", 'rgba(0, 0, 0, 0.5)');
	$("#B-img").css("opacity", 1);
	$("#B-img").attr('src', src);
	adjust();
	/* alert($("#B-img").width()+";"+$("#B-img").height()); */
	$("#B-img").css('margin-left', '20%');
}
function adjust() {// 修改大图的比例
	var width = $("#B-img").width();
	var height = $("#B-img").height();
	if (width > 0 && height > 0) {
		var ratio = 800 / width;// 大致比例(默认大图宽800px)
		height = height * ratio;
		$("#B-img").attr('width', '800px');
		$("#B-img").attr('height', height + "px");
	}
}
function disapper(path) {// 恢复正常视图 假如需要修改图片 则弹出询问框
	$("#image")[0].value = "";// 清空选择的文件
	$("#alt_btn").css("display","none");
	$("#B-I").css("display", "none");
}
function alt_btn(){
	var str = $("#image")[0].value;
	if (str != "") {
		$("#alt_btn").css("display","inline");
	}else{
		$("#alt_btn").css("display","none");
	}
}
function update_Img(){
	var str = $("#image")[0].value;
	if (str != "") {
		var point = str.indexOf(".");
		var len = str.length - point;
		/* alert("len:"+len); */
		if (len > 3 && len < 6) {
			var suffix = str.substring(point + 1, str.length);
			/* alert("suffix:"+suffix); */
			if (suffix == "jpg" || suffix == "jpeg") {
				if (confirm("确定修改封面吗？")) {
					/* alert($("#image")[0].value); */
					var bid = window.bid;
					if (bid != null) {
						var formData = new FormData($("#ImageForm")[0]);
						formData.append("bid", bid);// 后台直接getparameter试过可以
						$.ajax({
							url : '/SSH_test/bookAction_updateI',
							type : 'POST',
							data : formData,
							async : false,
							cache : false,
							contentType : false,
							processData : false,
							success : function(data) {
								// 刷新重新设置image的src(这里涉及到tomcat和eclipse的文件部署问题，已在server.xml加入相应配置，详情看浏览器收藏夹)
								/*
								 * var image = $("#"+window.Iid);//获取修改的图片 var
								 * src1 = image[0].src; var sign =
								 * src1.indexOf("\\");
								 * image[0].src=src1.substring(0, sign)+data;
								 * alert("数据："+data);
								 * alert("image[0].src:"+image[0].src);
								 */
								alert("修改成功");
								window.location.reload();
							},
							error : function() {
								alert("上传错误");
							}
						});
					}
				}
			} else {
				alert("请选择jpg类型的图片文件");
			}
		}
	}
}

function showPublish(page) {
	if (page == "" || page == null) {
		var num = 1;
	} else {
		var num = (page - 1) * 5 + 1;// 该页初始的num
	}
	if (($("#year" + num).val() == "") && ($("#month" + num).val() == "")
			&& ($("#date" + num).val() == "")) {
		for (var i = num; i < (num + 5); i++) {
			DateFormat($("#publish" + i).val(), i);
		}
	}
}
function DateFormat(timeStamp, num) {
	var time = new Date(timeStamp * 1000 * 60 * 60);
	$("#year" + num).val(time.getYear());
	$("#month" + num).val(time.getMonth());
	$("#date" + num).val(time.getDate());
}
function checkPublish(num) {
	var year = /^\d{4}$/;
	var month = /^\d{1,2}$/;
	var date = /^\d{1,2}$/;
	if (year.test($("#year" + num).val()) == false
			|| month.test($("#month" + num).val()) == false
			|| date.test($("#date" + num).val()) == false) {
		alert("日期输入有误，请修改");
		return false;
	}
	return true;
}
function checkForm() {
	$("#image")[0].click();
}
// 用户筛选部分
function u_select() {
	if ($("#u_select").css("display") == "block") {
		$("#u_select").css("display", "none");
		$("#slt_flag").attr("class", "glyphicon glyphicon-chevron-down");
	} else {
		$("#u_select").css("display", "block");
		$("#slt_flag").attr("class", "glyphicon glyphicon-chevron-up");
	}
	// alert($("#u_select").css("display"));
}
function select_u() {
	var json = {
		uid : $("#slt_id").val(),
		username : $("#slt_name").val(),
		u_status : $("#slt_status").val(),
		u_permission : $("#slt_permission").val()
	}
	$.ajax({
		url : '/SSH_test/managerAction_select_U',
		type : "POST",
		timeout : 1000,
		data : {
			json : JSON.stringify(json)
		},
		async : false,// 取消异步请求
		dataType : "json",
		cache : false,
		success : function() {// 返回时的方法
			window.location.href = '/SSH_test/pages/manager/edit.jsp';
		},
		error : function() {
			alert("错误");
		}
	});
}
// 书本筛选部分
function b_select() {
	if ($("#b_select").css("display") == "block") {
		$("#b_select").css("display", "none");
		$("#slt_flag2").attr("class", "glyphicon glyphicon-chevron-down");
	} else {
		$("#b_select").css("display", "block");
		$("#slt_flag2").attr("class", "glyphicon glyphicon-chevron-up");
	}
	// alert($("#u_select").css("display"));
}
function select_b() {
	var bid = $("#slt_bid").val();
	if (bid == "") {
		bid = -1;
	}
	var arr = [ $("#slt_year1").val(), $("#slt_month1").val(),
				$("#slt_date1").val(), $("#slt_year2").val(),
				$("#slt_month2").val(), $("#slt_date2").val() ];
	if (checkBook(arr)) {
		var json = {
			bid : bid,
			bname : $("#slt_bname").val(),
			ISBN : $("#slt_ISBN").val(),
			year1 : arr[0],
			month1 : arr[1],
			date1 : arr[2],
			year2 : arr[3],
			month2 : arr[4],
			date2 : arr[5],
			author : $("#slt_author").val(),
			type : $("#slt_type").val()
		}
		$.ajax({
			url : '/SSH_test/managerAction_select_B',
			type : "POST",
			timeout : 1000,
			data : {
				json : JSON.stringify(json)
			},
			async : false,// 取消异步请求
			dataType : "json",
			cache : false,
			success : function() {// 返回时的方法
				window.location.href = '/SSH_test/pages/manager/edit.jsp';
			},
			error : function() {
				alert("筛选出错");
			}
		});
	}
}
function checkTime(arr) {
	for (var i = 0; i < arr.length; i++) {
		if (arr[i] == "") {
			arr[i] = "0";
		}
	}
	var time1 = new Date(arr[0], arr[1], arr[2]).getTime();
	var time2 = new Date(arr[3], arr[4], arr[5]).getTime();
	var time3 = new Date(0,0,0).getTime();
	if(time1>=time3&&time2>=time3){
		if (time1 <= time2||time2==time3) {
			return true;
		}
	}
	alert("日期错误！");
	return false;
}
function checkUser() {// 统一检查user筛选条件
	if (checkUid() == true && checkUname() == true) {
		return true;
	}
	return false;
}
function checkBook(arr1) {// 统一检查book筛选条件
	var arr = [ checkBid(), checkBname(), checkISBN(), checkYear(1),
			checkMonth(1), checkDate(1), checkYear(2), checkMonth(2),
			checkDate(2), checkTime(arr1) ];
	var j = 0;
	for (var i = 0; i < arr.length; i++) {
		if (arr[i] == true) {
			j++;
		}
	}
	if (j == arr.length) {
		return true;
	}
	return false;
}
function checkUid() {
	var str = /[a-zA-Z0-9_]{0,16}/;
	if ($("#slt_id").val() != "") {
		if ($("#slt_id").val().match(str) == null) {
			alert("由0到16位数字字母下划线组成！");
			return false;
		}
	}
	return true;
}
function checkUname() {
	var str = /^[\u4E00-\u9FA5]{2,4}$/;// 2个到4个中文
	if ($("#slt_name").val() != "") {
		if (str.test($("#slt_name").val()) == false) {
			alert("由2-4个中文组成！");
			$("#slt_name").focus();
			return false;
		}
	}
	return true;
}
function checkBid() {
	var str = /[0-9]{0,16}/;
	if ($("#slt_bid").val() != "") {
		if ($("#slt_bid").val().match(str) == null) {
			alert("由0到16位数字组成！");
			$("#slt_id").focus();
			return false;
		}
	}
	return true;
}
function checkBname() {
	var str = /^[a-zA-Z0-9\u4e00-\u9fa5 ]{1,20}$/;// 1到20个中文字母数字
	if ($("#slt_bname").val() != "") {
		if (str.test($("#slt_bname").val()) == false) {
			alert("书名填写有误");
			$("#slt_bname").focus();
			return false;
		}
	}
	return true;
}
function checkISBN() {
	var str = /^[0-9]*$/;
	if ($("#slt_ISBN").val() != "") {
		if (str.test($("#slt_ISBN").val()) == false) {
			alert("ISBN填写有误");
			$("#slt_ISBN").focus();
			return false;
		}
	}
	return true;
}
function checkYear(num) {
	var str = /^\d{4}$/;
	if ($("#slt_year" + num).val() != "") {
		if (str.test($("#slt_year" + num).val()) == false) {
			alert("第"+num+"个年份填写有误");
			$("#slt_year" + num).focus();
			return false;
		}
	}
	return true;
}
function checkMonth(num) {
	var str = /^\d{1,2}$/;
	if ($("#slt_month" + num).val() != "") {
		if (str.test($("#slt_month" + num).val()) == false) {
			alert("第"+num+"个月份填写有误");
			$("#slt_month" + num).focus();
			return false;
		}
	}
	return true;

}
function checkDate(num) {
	var str = /^\d{1,2}$/;
	if ($("#slt_date" + num).val() != "") {
		if (str.test($("#slt_date" + num).val()) == false) {
			alert("第"+num+"个日子填写有误");
			$("#slt_date" + num).focus();
			return false;
		}
	}
	return true;
}
/*************记录模块*************/
function timeStamp(num,timestamp){
	var time = new Date(timestamp * 1000 * 60);
	var str = (time.getYear()+1900)+"-"+(time.getMonth()+1)+"-"+time.getDate()+" "+time.getHours()+"点";
	$("#time" + num).html(str);
}