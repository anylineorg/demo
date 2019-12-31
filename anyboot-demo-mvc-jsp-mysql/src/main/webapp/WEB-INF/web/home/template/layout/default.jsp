<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!doctype html>
<html>
<head>
<jsp:include page="/WEB-INF/web/home/inc/head.jsp"></jsp:include>
</head>
	
<body>
content:${anyline_template_content_path }
app:${app }
top<br/>
<hr/>
<jsp:include page="${anyline_template_content_path }"></jsp:include>
<br/><hr/>
bottom
</body>
</html>