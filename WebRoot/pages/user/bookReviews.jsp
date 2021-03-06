<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/mytags" prefix="myTags"%>
<script type="text/javascript">
</script>
<c:if test="${empty sessionScope.rfb_set }">
	<span>还没有评论呢，赶紧发表你的感想吧~~</span>
</c:if>
<c:if test="${!empty sessionScope.rfb_set }">
	<c:forEach items="${sessionScope.rfb_set }" var="rfb" begin="0"
		end="7" varStatus="num">
		<div id="reviews_content${rfb.rbid }" class="reviews_content">
			<div class="content_top">
				<div class="con_top_img">
					<img alt=""
						src="${pageContext.request.contextPath}/images/user/userImg/${rfb.user.image}">
				</div>
				<div class="con_top_font">${rfb.user.username}</div>
				<div class="con_top_font2">的评论</div>
				<div class="con_top_font" style="float:right;">
					<myTags:date value="${rfb.time*1000}"></myTags:date>
				</div>
			</div>
			<div class="content_center">${rfb.content }</div>
			<c:forEach items="${rfb.rfr }" var="rfr" begin="0" end="10" varStatus="nnum">
				<c:choose>
					<c:when test="${!empty sessionScope.book }">
						<c:if test="${rfr.book.bid eq sessionScope.book.bid }">
							<div id="rfr_div${rfr.rrid }" class="rfr_div">
								<div class="content_top">
									<div class="con_top_img">
										<img alt="" src="${pageContext.request.contextPath}/images/user/userImg/${rfr.user1.image}">
									</div>
									<div class="con_top_font">${rfr.user1.username }</div>
									<div class="con_top_font2">回复</div>
									<div class="con_top_img">
										<img alt="" src="${pageContext.request.contextPath}/images/user/userImg/${rfr.user2.image} ">
									</div>
									<div class="con_top_font">${rfr.user2.username }</div>
									<div class="con_top_font" style="float:right;"><myTags:date value="${rfr.time*1000}"></myTags:date></div>
								</div>
								<div class="content_center">
									${rfr.content }
								</div>
								<div class="content_bottom2" id="inner${rfr.rrid }" onclick="openReviews(this,'rfr_div${rfr.rrid }')">回复</div>
							</div>
						</c:if>
					</c:when>
					<c:otherwise>
						<c:if test="${rfr.work.wid eq sessionScope.work.wid }">
							<div id="rfr_div${rfr.rrid }" class="rfr_div">
								<div class="content_top">
									<div class="con_top_img">
										<img alt="" src="${pageContext.request.contextPath}/images/user/userImg/${rfr.user1.image}">
									</div>
									<div class="con_top_font">${rfr.user1.username }</div>
									<div class="con_top_font2">回复</div>
									<div class="con_top_img">
										<img alt="" src="${pageContext.request.contextPath}/images/user/userImg/${rfr.user2.image} ">
									</div>
									<div class="con_top_font">${rfr.user2.username }</div>
									<div class="con_top_font" style="float:right;"><myTags:date value="${rfr.time*1000}"></myTags:date></div>
								</div>
								<div class="content_center">
									${rfr.content }
								</div>
								<div class="content_bottom2" id="inner${rfr.rrid }" onclick="openReviews(this,'rfr_div${rfr.rrid }')">回复</div>
							</div>
						</c:if>
					</c:otherwise>
				</c:choose>
			</c:forEach>
			<div class="content_bottom" id="outer${rfb.rbid }"
				onclick="openReviews(this,'reviews_content${rfb.rbid }')">回复</div>
		</div>
	</c:forEach>
</c:if>
