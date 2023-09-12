package com.kh.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class PizzaServlet
 */
@WebServlet("/confirmPizza.do")
public class PizzaServlet extends HttpServlet {
	//	2022.1.3(월) 16h10
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PizzaServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 사용자의 요청 및 데이터(pizza order form.jsp 파일을 통해 받음)를 처리하기 위해 Servlet 클래스 생성 -> controller 역할
		// 단계0) post 방식일 때(만) 전달 값 중에 한글이 있을 경우 encoding 처리 vs get 방식일 때는 어차피 utf-8로 날아옴
		
		// 단계1) 요청 시 전달된/사용자가 요청 시 입력한 값들이 request 객체에 담겨있는데, 그 값들 뽑기 + 데이터 가공 처리 -> 변수에 기록
		// request.getParameter("key 값") -> String형 값 반환
		// request.getParameterValues("key 값") -> String[] 배열 반환
		
		// 주문자 정보
		String userName = request.getParameter("userName"); // "강판다"
		String phone = request.getParameter("phone"); // "01012345678"
		String address = request.getParameter("address"); // "서울시 마포구"
		String message = request.getParameter("message"); // "맛있게 준비해주세요" ou 빈 문자열 ""
		
		// 주문 정보
		String pizza = request.getParameter("pizza"); // select 태그에서 1개만 고를 수 있음 -> String 자료형으로 뽑아옴 e.g. "potato pizza"
		String[] toppings = request.getParameterValues("topping"); // checkbox인 바 여러 개 선택 가능 -> 찬(["올리브", "양파"]) ou 빈(null) String[] 배열
		String[] sides = request.getParameterValues("side"); // 찬(["감자 튀김", "피클"]) ou 빈(null) String[] 배열
		String payment = request.getParameter("payment"); // radio 버튼에서 1개만 선택 가능하게 만듦 -> String 자료형 반환 e.g. "cash"
		
		// 단계2) 요청 처리: 보통의 흐름 = controller - service - DAO - DB
		
		// 사용자가 보게 될 내용(이 프로젝트에서는 응답 JSP "pizza 결제 페이지")을 처리
		
		// 가격 정하기
		int price = 0;
		
		switch(pizza) { // pizza의 기본 가격
		case "hawaian pizza" : price += 5000; break;
		case "mushroom pizza" : price += 7000; break;
		case "cinnamon pizza" : price += 6500; break;
		case "avocado pizza" : price += 8500; break;
		case "potato pizza" : price += 7500; break;
		
		}
		
		// toppings 추가 시 가격 추가
		if (toppings != null) {
			for (int i = 0; i < toppings.length; i++) {
				switch(toppings[i]) {
				case "치즈" : 
				case "고구마" :
				case "파인애플" : price += 2000; break;
				case "올리브" : price += 1500; break;
				case "양파" :
				case "파프리카" : price += 1000; break;
				}
			}
		}
		
		// side menus 추가 시 가격 추가
		if (sides != null) {
			for (int i = 0; i < sides.length; i++) {
				switch(sides[i]) {
				case "피클" : price += 1000; break;
				case "호떡" :
				case "붕어빵" : price += 2000; break;
				case "인절미" : price += 1500; break;
				case "조각 케익" : price += 4000; break;
				case "감자 튀김" : price += 3500; break;
				}
			}
		}
		
		// 단계3) 요청 처리 후 사용자가 보게 될 응답 페이지(결제 페이지) 만들기 또는 JSP에게 위임
		// 단계3a) 응답 페이지에 필요한 값 담기 @request의 attribute 영역
		request.setAttribute("userName", userName); // key 값을 'name'으로 request 객체의 attribute 영역에 넣음
		request.setAttribute("phone", phone);
		request.setAttribute("address", address);
		request.setAttribute("message", message);
		
		request.setAttribute("pizza", pizza);
		request.setAttribute("toppings", toppings); // String[] 배열로 빼왔으니까, 넘어갈 때도 String[] 배열로 넘어감
		request.setAttribute("sides", sides);
		request.setAttribute("payment", payment);
		
		request.setAttribute("price", price);
		
		// 단계3b) request.getRequestDispatcher() 호출해서 RequestDispatcher(위임) 객체 생성 + 위임할 JSP 파일(=응답 페이지)의 경로 기술
		RequestDispatcher view = request.getRequestDispatcher("views/05_PizzaPayment.jsp");
		
		// forwarding -> request 및 reponse 객체 가지고 (어딘가로) 넘겨/전달해줌
		view.forward(request, response);
	} // doGet() 종료

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

} // PizzaServlet 클래스 영역 끝
