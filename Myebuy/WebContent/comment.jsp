<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<link type="text/css" rel="stylesheet" href="css/style.css" />
<script type="text/javascript"src="${pageContext.request.contextPath}/js/jquery-1.11.1.js"></script>
	
<script type="text/javascript"src="${pageContext.request.contextPath}/js/My97DatePicker/WdatePicker.js"></script>
	
</head>
<body>
	<div id="header" class="wrap">
		<jsp:include page="common/top.jsp" />
	</div>
	<div class="wrap" id="main">
		<div class="lefter">
			<jsp:include page="common/left.jsp" />
		</div>
		<div class="main">
		<div class="guestbook">
			<h2>全部留言</h2>
			<ul>
				 <c:forEach items="${commentList}" var="comment">
					<li>
						<dl>
							<dt>网友 :${comment.nickName}</dt> 
							<dd class="author">
								 ${comment.content}
							<span class="timer">
								<fmt:formatDate value="${comment.createTime}" type="date" pattern="yyyy-MM-dd HH:mm:ss"/> 
							</span>
							<c:if test="${not empty comment.replyContent }">
								<dd>
								官方回复 :  ${comment.replyContent}&nbsp;
								<span class="timer"> 
									<fmt:formatDate value="${comment.replyTime}" type="date" pattern="yyyy-MM-dd HH:mm:ss" /> 
								</span>
								</dd>
							</c:if>
							</dd>
						</dl>
					</li><br>
				</c:forEach> 
			</ul>
			<div class="clear"></div>
			<div class="pager">
				<div class="clearfix">
					${pageCode}
				</div>
			</div>
			<!-- 写留言 -->
			<div id="reply-box">
				<form action="comment?oper=save" method="post" onsubmit="return checkForm()">
					<table>
						<tr>
							<td class="field">用户名：</td>
							<td><span>${user.userName}</span></td>
						</tr>
						<tr>
							<td class="field">留言内容：</td>
							<td><textarea id="content" name="content"></textarea></td>
						</tr>
						<tr>
							<td></td>
	<td><label class="ui-blue"><input type="submit" name="submit" value="提交留言"/></label>&nbsp;&nbsp;<font id="error" color="red">${error}</font></td>
						</tr>
					</table>
				</form>
			</div>
		</div>
		</div>
	</div>

	<div id="footer">
		<jsp:include page="common/footer.jsp" />
	</div> 

</body>
<script type="text/javascript">
//jquery 代码！
	function checkForm(){
		var nickName = $("#nickName").val();//获取用户在浏览器上写的内容
		var content = $("#content").val();
		/* if(""==nickName){
			 $("#error").html("昵称不能为空"); 
			return false;//返回了
		}  */
		if(""==content){
			$("#error").html("留言内容不能为空");
			return false;//返回了
		}
		return true;
	}
</script>
</html>