<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>管理员上传书本</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
</head>
<body style="background-image: url('${pageContext.request.contextPath}/images/background/935677fc3f0bb9e.jpg');background-size:100% 100%;" onload="checkUser(${empty sessionScope.user})">
	<c:choose>
		<c:when test="${!empty sessionScope.user.u_permission}">
			<div class="header" style="background-color:white;">
				<div class="header_logo"></div>
				<a href="${pageContext.request.contextPath}/bookAction_getData">
					<div class="header_index">
						首页
					</div>
				</a>
				<a href="${pageContext.request.contextPath}/managerAction_getUser">
					<div class="h_user">
						管理用户
					</div>
				</a>
				<a href="${pageContext.request.contextPath}/managerAction_getBook">
					<div class="h_book">
						管理书本
					</div>
				</a>
				<a href="${pageContext.request.contextPath}/managerAction_getRecord">
					<div class="h_record">
						操作历史
					</div>
				</a>
				<div class="header_user">
					<div class="user_img" onmouseover="infoon()" onmouseout="infooff()"
						onclick="login('${empty sessionScope.user}','${pageContext.request.contextPath}')">
						<span class="glyphicon glyphicon-user"></span> <span
							style="color:red;font-weight:400">${sessionScope.user.username }</span>
					</div>
					<a href="${pageContext.request.contextPath}/pages/manager/upload.jsp">
						<div class="user_upload">
							<span id="upload_flag" class="glyphicon glyphicon-arrow-up">上传</span>
						</div>
					</a>
				</div>
			</div>
			<div id="user_info" class="user_info" onmouseover="infoon()"
				onmouseout="infooff()">
				<c:choose>
					<c:when test="${empty sessionScope.user }">
						<div class="list_login">
							<a href="${pageContext.request.contextPath}/pages/user/login.jsp">前往登录</a>
						</div>
					</c:when>
					<c:otherwise>
						<div class="info_img">
							<c:choose>
								<c:when test="${empty sessionScope.user.image }">
									<img id="user_img" alt="头像" style="border-radius:100%;" src="${pageContext.request.contextPath }/images/flag/user_img(default).png">
								</c:when>
								<c:otherwise>
									<img id="user_img" alt="头像" style="border-radius:100%;" src="${pageContext.request.contextPath }/images/user/userImg/${sessionScope.user.image }">
								</c:otherwise>
							</c:choose>
						</div>
						<div class="list_half">ID：<span style="color:#0080c0;">${sessionScope.user.uid }</span></div>
						<div class="list_half">用户名:<span style="color:red;">${sessionScope.user.username }</span></div>
						<a href="${ pageContext.request.contextPath}/pages/user/User.jsp">
							<div class="list_all">
								<span style="font-weight:550;font-size:15px;">个人中心</span>
							</div>
						</a>
						<!-- 未实现暂时用User.jsp过渡 -->
						<a href="${ pageContext.request.contextPath}/pages/user/User.jsp">
							<div class="list_btn">
								设置
							</div>
						</a>
						<div class="list_btn" onclick="logout()">
							退出
						</div>
					</c:otherwise>
				</c:choose>
			</div>
			<div class="upload_bottom2">
				<a href="${pageContext.request.contextPath }/pages/manager/bulkUpload.jsp"><div class="BulkUpload"><span class="glyphicon glyphicon-arrow-up">批量上传</span></div></a>
				<input id="uploadResult" type="text" value="${requestScope.uploadResult}" style="display:none">
				<div class="upload_form2">
					<form action="${pageContext.request.contextPath }/bookAction_upload"
						method="post" enctype="multipart/form-data" onsubmit="return check2()">
						<div class="upload_title">
							<div class="title_font">标题/作品名 ：</div>
							<div class="title_input">
								<input id="upload_title" name="book.bname" type="text" maxlength="15"
									placeholder="输入标题/书名(1-20个字符、数字)" onblur="checktitle()">
							</div>
							<div id="title_F" class="upload_flag_default"></div>
							<div id="title_warning" class="title_warning"></div>
						</div>
						<img id="upload_img" alt="预览图"
							src="${pageContext.request.contextPath }/images/background/bookimg-default.jpg">
						<div class="img_button">
							<input type="button" value="上传封面" onclick="uploadi()" style="width:100px;margin-right:-18px;">
							<br>
							<div style="margin-left:5px;font-size:12px;color:red;">(仅限jpg,jpeg)</div>
						</div>
						<input type="file" id="image" name="image"
							accept="image/*" style="display:none;">
						<div class="file_button">
							<input type="button" value="上传作品" style="width:200px;height:30px;cursor: pointer;"
								onclick="uploadf()"> <br>
							<div style="font-size:12px;color:red;margin-left:30px;cursor: default;">(仅限doc,docx,txt)</div>
							<div id="file_F" style="margin-top:-40px;margin-left:203px;cursor: default;" class="upload_flag_default"></div>
						</div>
						<input type="file"
							accept="application/msword, application/vnd.openxmlformats-officedocument.wordprocessingml.document, text/plain"
							id="upload" name="upload" style="display:none;" onchange="checkF()"> 
							<br>
						<div class="upload_desc">
							<div class="desc_font">书本概述:</div>
							<div style="margin-top:5px;">
								<textarea name="book.description" class="desc_content" placeholder="输入概述"></textarea>
							</div>
						</div>
						<div style="margin-top:20px;">
							<div class="upload_font">出版日期:</div>
							<div style="width:310px;height:25px;margin-left:-5px;">
								<input type="text" id="year" name="year" class="date" style="width:40px;">年
								<input type="text" id="month" name="month" class="date" style="width:30px;">月
								<input type="text" id="day" name="day" class="date" style="width:30px;">日
								<div style="font-size:12px;margin-left:190px;margin-top:-20px;color:blue">例如1990;1;2</div>
							</div>
						</div>
						<div>
							<div class="upload_font">ISBN:</div>
							<div style="width:210px;height:25px;">
								<input id="ISBN" type="text" name="book.ISBN" style="width:60%;">
							</div>
						</div>
						<br>
						<div>
						    <div class="upload_font" style="text-align:center">类别:</div>
							<select id="type" name="book.type" class="upload_font">
								<option value="网络小说" selected>网络小说</option>
								<option value="文学作品">文学作品</option>
								<option value="社会科学">社会科学</option>
							</select>
						</div>
						<div>
							<div class="upload_font">作者:</div>
							<input type="text" id="author" name="book.author" style="width:80px;">
						</div>
						<div class="upload_button2">
							<input type="submit" value="提交">
						</div>
						<div style="width:120px;height:30px;margin-left:-100px;margin-top:110px;">
							<a href="${pageContext.request.contextPath }/managerAction_getBook" style="color:blue;">修改上传书本信息</a>
						</div>
					</form>
				</div>
			</div>
		</c:when>
		<c:otherwise>
			<a href="${pageContext.request.contextPath}/pages/user/login.jsp"
				style="margin-left:600px;">前往登录</a>
		</c:otherwise>
	</c:choose>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/jquery-3.2.1.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/jquery.cookie.js"></script>
<script
	src="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/manager/edit.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/user/upload.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/user/index.js"></script>
</body>
<link rel="stylesheet"
	href="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/user/upload.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/manager/edit.css">
</html>
