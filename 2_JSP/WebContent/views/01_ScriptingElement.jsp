<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.Arrays" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>scripting 원소</title>
</head>
<body>
	<!--2022.1.3(월) 11h15-->
	<h1>scripting 원소</h1>
	<!--@ = 지시어
		JSP 문서인 바, Java 문법 + html 문법 모두 사용 가능-->
	<!--html 주석 -> 개발자 도구 tab에 노출됨-->
	<%--JSP 주석 -> 개발자 도구 tab에 노출 안 됨--%>
		
	<%--
		<%= sum %> 이렇게 스크립틀릿 시작 시(= 포함), 내용/변수 출력
		<% %> 일반적인 스크립틀릿; 선언
		스크립틀릿 내의 Java 코드의 실행 순서 = 순수 Java 코드와 같이 위->아래 순서대로 흐름; 먼저 선언하고 나서, 출력식을 통해 출력 가능--%>
	<% 
		// 스크립틀릿: 이 안에 일반적인 Java 코드(변수 선언, 초기화, 제어문 등)를 작성 
		// 1~100 누적 합 구하기
		int sum = 0;
		for (int i = 1; i <=100; i++) {
			sum += i;
		}
		
		// System.out.println("1~100 누적 합 sum : " + sum); // System 클래스 = Eclipse 관련
	%>
	
	웹 화면에서 출력하고자 한다면,<br>
	1. 스크립틀릿으로 출력 가능: <% out.println(sum); %> <br> <%--브라우저 상 출력 관련이므로 System 클래스 필요x; 메소드이기 때문에 문장 끝에 세미콜론; 씀--%>
	2. 표현/출력식으로 출력 가능: <%= sum %> <%--스크립틀릿보다는 표현/출력식 방식 사용하기 -> 나의 질문 = 왜?--%>
	<%--html 문서 수정하고 있는 것이므로, 서버 요청한 웹페이지 http://localhost:8888/2_JSP/views/01_ScriptingElement.jsp 새로고침하면 내용 반영되어 있음--%>
	
	<% 
		// 배열 사용
		String[] names = {"강해피", "강토미", "강미피"}; // String이라는 참조자료형 -> 변수명을 출력 시, 변수에 담긴 주소값이 그대로 출력됨
	%>
	
	<h5>배열의 길이: <%= names.length %></h5> <%--3--%>
	names라는 변수에 담긴 주소 값: <%= names %> <%--[Ljava.lang.String;@4a81d8de--%> <br>
	names라는 문자열 배열에 담긴 내용물: <%= Arrays.toString(names) %> <%--Arrays라는 클래스가 무엇인지 모르기 때문에 import해줘야 함 <- 클래스별 api 보면 어디 소속인지 알 수 있음; [강해피, 강토미, 강미피]--%>
	
	<h4>반복문을 통해 html 요소를 반복적으로 화면에 출력</h4>
	<%--반복문 없이 출력한 것 vs--%>
	<%--
	<ul>
		<li><%= names[0] %></li>
		<li><%= names[1] %></li>
		<li><%= names[2] %></li>
	</ul>
	--%>
	
	<%--vs 반복문으로 출력--%>
	<ul>
		<%--for문--%>
		<% for (int i = 0; i < names.length; i++) { %>
			<li><%= names[i] %></li>
		<% } %>
		
		<!--향상된 for문 = 배열이나 컬렉션에 순차적으로 접근 -> 순수 조회용; 값 추가/수정 불가능-->
		<% for (String s:names) { %>
			<li><%= s %></li>
		<% } %>
	</ul>
	
	<%! // 메소드는 느낌표 붙인 선언문으로 선언해야 함 -> JSP에서 메소드 정의할 일 없는 바, 몰라도 되는 내용임
		public void test() { // 현재 이 JSP에서만 사용 가능한 메소드
		}
	%>
	
	<% test(); %> <!--메소드 호출은 이렇게-->

</body>
</html>