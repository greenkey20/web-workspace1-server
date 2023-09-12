<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% // 스크립틀릿에 일반 Java 코드 작성 가능 -> controller로부터 request 객체의 attribute 속성에 받아온 Java 변수들 사용 가능하도록/빼내기 위해서; 이렇게 변수 선언 시에는 JSP 문서 맨 위에 작성
	String userName = (String)request.getAttribute("userName"); // getAttribute() 반환형 = Object -> String에 대입하기 위해서는 강제 형 변환 필요
	String phone = (String)request.getAttribute("phone"); 
	String address = (String)request.getAttribute("address"); 
	String message = (String)request.getAttribute("message"); 
	
	String pizza = (String)request.getAttribute("pizza"); 
	String[] toppings = (String[])request.getAttribute("toppings"); // String[] 배열로 넘겨왔으니까, String[] 배열로 담음
	String[] sides = (String[])request.getAttribute("sides");
	String payment = (String)request.getAttribute("payment");
	
	int price = (int)request.getAttribute("price");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>pizza 결제 페이지</title>
</head>
<body>
	<!--2022.1.3(월) 17h
		JSP 문서 상 생긴 코드 문제에 대해서 Eclipse가 제대로 알려주지 않을 수도 있음
		대표적인 문제 = 오타 70% + key 값 20% + 기타 파악하기 어려운 오류 10%(e.g. console에 error가 안 뜸 -> 현업 개발자들의 해결책 결론 = 최후의 방법으로써 재부팅 + 학우들도 하다보니 됨)
		서버에 프로젝트 여러 개(3-4개) 올라가면 연산할 것이 많아지면, 전기신호가 있다(1)없다(0) 헷갈리며 연산 오차 발생 가능-->
    <h1>pizza 결제 페이지</h1>

    <h2>주문 내역</h2>

    <h4>[주문자 정보]</h4>
    <!--client가 작성한 value 값은 xx로 표현함-->
    <ul>
        <li>성함: <%= userName %></li>
        <li>전화번호: <%= phone %></li>
        <li>주소: <%= address %></li>

        <% if (message.equals("")) { %>  <!--case1) 요청사항 작성하지 않은 경우-->
        	<li>요청사항: 작성 안 함</li>
        <% } else { %> <!--case2) 요청사항 작성한 경우-->
        	<li>요청사항: <%= message %></li>
        <% } %>
    </ul>

    <br>
    <h4>[주문 정보]</h4>
    <ul>
        <li>pizza: <%= pizza %></li>

        <% if (toppings == null) { %> <!--case1) toppings 선택하지 않은 경우-->
	        <li>toppings: 선택 안 함</li>
	    <% } else { %> <!--case2) toppings 선택한 경우-->
    	   <li>toppings: <%= String.join(",", toppings) %></li> <!--또는 Arrays.toString(toppings); 등 가능-->
    	<% } %>

        <% if (sides == null) { %> <!--case1) side menus 선택하지 않은 경우-->
 	       <li>side menus: 선택 안 함</li>
 	    <% } else {  %> <!--case2) side menus 선택한 경우-->
 	    	<li>side menus: <%= String.join(",", sides) %></li> <!--표현식에는 세미콜론 안 붙임; String.join("구분자", 배열명) = 배열 안의 내용물을 구분자로 구분해서 하나의 긴 문자열로 만들어서 예쁘게 보이게 해 줌-->
 	    <% } %>

        <% if (payment.equals("cash")) { %> <!--case1) 현금 결제를 선택한 경우-->
        	<li>결제 방식: 현금</li>
        <% } else { %> <!--case2) 카드 결제를 선택한 경우-->
	        <li>결제 방식: 카드</li>
	    <% } %>
    </ul>

    <br>
    <h3>위와 같이 주문하셨습니다.</h3>
    <h2>총 가격: <%= price %>원</h2>

</body>
</html>