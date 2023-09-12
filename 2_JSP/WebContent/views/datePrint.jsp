<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.Date, java.text.SimpleDateFormat" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<!--2022.1.3(월) 14h45-->
	<% 
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy년 MM월 dd일");
		String today = sdf.format(date);
	%>
	<!--SimpleDateFormat = 원하는 양식으로 날짜 지정할 수 있는 클래스-->
	
	<!-- <h4 style="color:green"> <%= date %> </h4> -->
	<h4 style="color:green"> <%= today %> </h4>

</body>
</html>