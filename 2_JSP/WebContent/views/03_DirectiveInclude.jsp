<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>include 지시어</title>
</head>
<body>
	<!--2021.1.3(월) 14h30-->
	<h1>include 지시어</h1>
		
	<!--<h4>&lt;%= sum %> </h4>--> <!--이 변수가 존재하는 jsp 문서를 include하는 구문 위에 이 코드가 있으면, 이 변수는 사용 불가능-->
	
	<h2>01_ScriptingElement.jsp 파일 include해보기</h2>
	<div style="border:1px solid green">
		<%@ include file="01_ScriptingElement.jsp" %>
	</div>
	
	<hr>
	<h4> 1부터 100까지의 총 합계: <%= sum %> </h4> <!--변수 sum을 출력하겠다는 출력식 -> include한 파일의 변수도 사용 가능하게 됨-->
	특징: 포함한 JSP 상에 있는 모든 화면 구성 요소들이 뜸 + 포함한 JSP 상에 있는 모든 변수들을 가져다 쓸 수 있음<br>
	
	<br>
	그러면 여기서 sum이라는 변수를 선언할 수 있을까? -> 중복된 변수 선언/사용 불가능
	<%
		// int sum = 0; // 중복된 변수명 -> 사용 불가능	
	%>
	
	<h2>오늘 날짜</h2>
	<%@ include file="datePrint.jsp" %>
	특징: 포함한 JSP 상에 있는 모든 스타일 속성들도 가져와서 반영이 됨<br>
	
	<!--나의 질문 = include한 여러 파일에 변수 등이 중복된다면 어떤 일이 일어나는가?-->

</body>
</html>