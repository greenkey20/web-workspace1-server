<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>초롱 pizzeria</title>
</head>
<body>
	<h1>초롱 pizzeria에 오신 것을 환영합니다 ^_^</h1>
	<!--2021.1.3(월) 15h-->

    <h2>오늘의 날짜</h2>
    <%@ include file="datePrint.jsp" %> <!--visual studio code에서 jsp 못 읽어서 이 줄 맨앞 < 빨간색 표시되는데, 무시해도 됨-->

    <br><br>
    <!--입력 양식의 name 속성이 있어야 key+value 세트로 넘길 수 있음-->
    <form action="/2_JSP/confirmPizza.do" method="get"> <!--action = context path + Servlet에서 url mapping할 주소-->
    <!--Servlet 안 만든 상태에서 요청 보내면  "요청된 리소스 [/2_JSP/confirmPizza.do]은(는) 가용하지 않습니다"-->
        <fieldset>
            <legend>주문자 정보</legend>
                <table>
                    <!--(tr>th+td)*4-->
                    <tr> <!--1행 추가-->
                        <th>이름</th> <!--제목처럼 쓰고 싶은 내용을 넣을 칸-->
                        <td><input type="text" name="userName" required></td> <!--일반 칸; 태그는 용도에 맞게 사용하면 됨; 필수 입력 사항에는 required 속성 부여-->
                    </tr>
                    <tr>
                        <th>전화번호</th>
                        <td><input type="phone" name="phone" required></td>
                    </tr>
                    <tr>
                        <th>주소</th>
                        <td><input type="text" name="address" required></td>
                    </tr>
                    <tr>
                        <th>요청사항</th>
                        <td><textarea name="message" style="resize: none;"></textarea></td>
                    </tr>                    
                </table>
        </fieldset>

        <br>

        <!--메뉴는 골라야 할 것이 많으므로, 따로 만들고자 함 + 같은 form 태그 안에만 있으면 됨-->
        <fieldset>
            <legend>주문 정보</legend>
            <table>
                <tr>
                    <th>pizza</th>
                    <td>
                        <select name="pizza"> <!--선택지(option)가 여러 개인 경우 select 태그에 name 속성 부여-->
                            <option>hawaian pizza</option>
                            <option>mushroom pizza</option>
                            <option>cinnamon pizza</option>
                            <option>avocado pizza</option>
                            <option>potato pizza</option>
                        </select>
                    </td>
                </tr>
                <tr>
                    <th>topping</th>
                    <td>
                        <input type="checkbox" name="topping" value="치즈">치즈
                        <input type="checkbox" name="topping" value="고구마">고구마
                        <input type="checkbox" name="topping" value="파인애플">파인애플
                        <input type="checkbox" name="topping" value="올리브">올리브
                        <input type="checkbox" name="topping" value="양파">양파
                        <input type="checkbox" name="topping" value="파프리카">파프리카
                    </td>
                </tr>
                <tr>
                    <th>side menus</th>
                    <td>
                        <input type="checkbox" name="side" value="피클">피클
                        <input type="checkbox" name="side" value="호떡">호떡
                        <input type="checkbox" name="side" value="붕어빵">붕어빵
                        <input type="checkbox" name="side" value="인절미">인절미
                        <input type="checkbox" name="side" value="조각 케익">조각 케익
                        <input type="checkbox" name="side" value="감자 튀김">감자 튀김
                    </td>
                </tr>
                <tr>
                    <th>결제 방식</th>
                    <td>
                        <!--input[type=radio value=]*2-->
                        <!--name 속성 걸어야 radio 버튼 선택지 중 1개만 선택 가능함-->
                        <input type="radio" name="payment" value="cash" checked>현금 결제
                        <input type="radio" name="payment" value="card">카드 결제
                    </td>
                </tr>
            </table>
        </fieldset>

        <input type="submit" value="주문하기">
        <input type="reset" value="다시 고르기">

    </form>	

</body>
</html>