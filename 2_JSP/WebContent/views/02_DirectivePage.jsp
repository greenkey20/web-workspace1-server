<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%> <!--errorPage="error500.jsp"-->
<%@ page import="java.util.ArrayList, java.util.Date"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>page 지시어</title>
</head>
<body>
	<!--2022.1.3(월) 12h-->
	<h1>page 지시어</h1>
	
	<%
		// java.lang은 기본적으로 import됨 -> String 클래스는 import 없이 사용 가능 vs ArrayList 사용하려면 page 지시어 import 필요
		ArrayList<String> list = new ArrayList<>();
		// import 하는 대신 객체 생성 시 경로 지정하는 방법도 있음(강사님 화면 캡쳐만 하고, 필기 못 함)
		
		list.add("Servlet");
		list.add("JSP");
		
		// 2개 이상의 클래스를 import하고 싶은 경우, 줄 바꿔서  <%@ page import="".. 쓰거나, comma(,) 뒤에 나열 e.g. Date 사용/추가
		Date today = new Date(); // Date 객체 생성 및 변수 today 선언
	%>
	
	<p>
		list의 길이: <%= list.size() %> <br>
		list가 텅 비었나? 확인: <%= list.isEmpty() %> <br> <!--true(list가 빈 경우)/false(list가 비지 않은 경우) 반환-->
		0번 index에 들어있는 요소에 접근: <%= list.get(0) %> <br>
		1번 index에 들어있는 요소에 접근: <%= list.get(1) %> <br><br>
		
		현재 날짜 및 시간: <%= today %> <!--위에서 Date 객체 today 생성한 것 출력해봄 -> Mon Jan 03 12:31:49 KST 2022-->
	</p>
	
	<!--&lt;%= list.get(10) %>--> <!--index를 넘어간 것에 접근하려고 한 바, index out of bounds exception/500 error(Java 코드의 문제) 발생 -> 이렇게 error 발생한 페이지를 사용자에게 보여주면 안 됨(회사의 신뢰성 하락 + 보안 문제)-->
	<!--error500.jsp 페이지를 만듦 -> 사용자는 어떤 에러인지 볼 수 없음 vs 개발자는 Eclipse console에서  오류 내용을 확인해야 함(오류 앞부분 '근본 원인' 및 Java exception)-->

</body>
</html>