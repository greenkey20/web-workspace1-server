<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%  // 이 구문 = scriptlet(스크립틀릿) = html 내에 Java 코드를 쓸 수 있는 영역

   	// 현재 이 JSP에서 필요로 하는 데이터는 request 객체의 attribute 영역에 담겨있음 -> request.getAttribute("key 값")으로 가져오기 -> 반환 자료형 = Object = Java에서 최상위 클래스/자료형
	String name = (String)request.getAttribute("name"); // type mismatch; 대입 연산자의 양변의 자료형이 다름 -> 부모 자료형(getAttribute()의 반환형 Object)은 자식 자료형(String)에 들어갈 수 없음(vs 자식 자료형은 부모 자료형에 들어갈 수 있음) -> 강제 형 변환
	int age = (int)request.getAttribute("age");
	String gender = (String)request.getAttribute("gender");
	String city = (String)request.getAttribute("city");
	double height = (double)request.getAttribute("height");
	String[] foods = (String[])request.getAttribute("foods"); // 2022.1.12(수) 나의 생각 = 사용자가 요청 페이지(form 태그)에서 좋아하는 음식 아무 것도 선택/체크하지 않은 경우 null이 Servlet으로 넘어감 -> getParameterValues()는 null 반환 -> String[] foods에 null 저장됨 -> request.getAttribute("foods")로 null Object 반환 -> 이 JSP 문서 상 String[] foods = null  
	
	// 새롭게 추가한 msg
	String msg = (String)request.getAttribute("msg");	
 %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>개인 정보 응답 화면</title>
<style>
    h2 {color: mediumspringgreen;}
    span {font-weight: bold;}
    #name {color: lime;}
    #age {color: mediumseagreen;}
    #city {color: palegreen;}
    #height {color: forestgreen;}
    #gender {color: limegreen;}
    li {color: green;}
</style>
</head>
<body>
    <!--2021.12.30(목) 16h30-->
    <h2>개인 정보 응답 화면</h2>
    
    <% if (name == "") { %>
    	이름을 안 적어주셔서 누구인지 모르겠습니다 @.@
    <% } else { %>
    	<span id="name"> <%= name %> </span> 님은 <!--출력할 내용에는 = 붙임; span: inline(o) block(x) 요소 중 하나로, 아무 의미 없고, 영역 구분 짓기 위한 용도로 사용-->
	    <span id="age"> <%= age %> </span>세이며,
	    <span id="city"> <%= city %> </span>에 거주 중입니다.
	    키는 <span id="height"> <%= height %> </span>cm이고,
	
	    성별은 <!--"M"/"F"/null; 라디오 버튼인 바, 아래 3개 경우의 수 중 1가지 case만 발생하므로 id가 겹치는 일은 발생하지 않음-->
	    <% if (gender == null) { %> <!--성별 선택 안 한 경우-->
		    <span id="gender">선택을 안 했습니다.</span>
	    <% } else { %>
	    	<% if (gender.equals("M")) { %> <!--남자일 경우-->
	    		<span id="gender">남성</span>입니다.
	    	<% } else { %> <!--여자일 경우-->
			    <span id="gender">여성</span>입니다.
			<% } %>
		<% } %>		 
	
	    좋아하는 음식은
	    <% if (foods == null) { %> <!--체크를 안 한 경우-->
			없습니다.
	    <% } else { %> <!--1개 이상 체크를 한 경우-->
	    	<ul>
	    		<% for (int i = 0; i < foods.length; i++) { %>
	    			<li> <%= foods[i] %> </li>
	    		<% } %>
	    	</ul>
	    <% } %>
	    
	    <div>요청 결과 : <%= msg %> </div>
    <% } %>
</body>
</html>