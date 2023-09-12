package com.kh.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class RequestPostServlet
 */
@WebServlet("/test2.do")
public class RequestPostServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RequestPostServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		response.getWriter().append("Served at: ").append(request.getContextPath()); // 자동 작성된 코드 필요 없는 바, 삭제하면 됨
		
		// 2021.12.30(목) 15h10
//		System.out.println("doPost()하면 doGet()한다고 하네~");
		
		// 단계0) post 방식의 기본 encoding 설정은 ISO-8859-1이기 때문에, 값을 뽑기 전에 미리 UTF-8 방식으로 encoding 설정을 해야 함
		request.setCharacterEncoding("UTF-8");
		
		// 단계1) request.getParameter() 또는 request.getParameterValues()로 값 뽑아내기
		String name = request.getParameter("name");
		String gender = request.getParameter("gender");
		int age = Integer.parseInt(request.getParameter("age")); // 참조자료형을 원시자료형으로 바꾸기 위해서는 parsing해야 함
		String city = request.getParameter("city");
		double height = Double.parseDouble(request.getParameter("height"));
		String[] foods = request.getParameterValues("food");
		
		// 값들 잘 뽑혔는지 console에 출력해서 확인
		/*
		System.out.println("name : " + name); // 사용자로부터 input 태그에 입력받은, value 속성의, '한글' 값을 출력해보고자 함 -> post로 전달되는 방식이  utf-8이 아닌데, utf-8 방식으로 뽑아보려고 하니 깨져서 출력됨 -> encoding 설정 때문에 생기는 이슈
		System.out.println("gender : " + gender);
		System.out.println("age : " + age);
		System.out.println("city : " + city); // post로 전달되는, 사용자가 input에 입력한, value 속성의, '한글' 값을 출력하고자 할 때, 깨져서 나옴 -> encoding 설정 때문에 생기는 이슈
		System.out.println("height : " + height);
		
		if (foods == null) {
			System.out.println("foods : 없음");
		} else {
			System.out.println("foods : " + String.join(" - ", foods)); // post로 전달되는, 사용자가 input에 입력한, value 속성의, '한글' 값을 출력하고자 할 때, 깨져서 나옴 -> encoding 설정 때문에 생기는 이슈
			// 16h10 requestTest_Post.html에서 form > input checkbox 만들 때, value가 아닌 id 속성 값으로 'x식'을 부여했었음 -> 'foods : on - on - on'으로 출력됨
			// 2022.1.12(수) API String 클래스 수업 내용 중에 String.join() 없는 것 같은데, 언제/어디서 배웠었지? ㅠ.ㅠ  -> Java의 정석 p.471 여러 문자열/배열의 문자열 사이에 구분자를 넣어서 결합 vs split()(구분자로 문자열을 나누어/잘라 문자열 배열에 담아 반환함)
		}
		*/
		
		// 단계2) 뽑은 값들을 service - DAO - DB로 넘겨서 요청 내용 처리 -> 현재는 DB 만들어 놓은 것 없으므로 생략/갔다 왔다고 침
		
		// 단계3) 응답 페이지 <- JSP 이용해서 응답 페이지 만들기; 응답을 JSP에 위임/맡김
		// JSP(Java Server Page): html 내에 Java 코드를 넣음 -> 단, 그 JSP 응답 화면에서 필요로 하는 데이터를 request 객체에 담아서 Servlet으로부터 보내줘야 함 -> 응답은 JSP가 함
		
		// 단계3a) request에 attribute 영역이 있는데, key-value 세트로 묶어서 보낼 수 있음 -> request.setAttribute(String "key 값", value(Object)); JSP에서 사용할 수 있도록 request의 attribute 영역 값 세팅
		request.setAttribute("name", name);
		request.setAttribute("age", age); // 나의 질문 = 나이, 키 자료형 보존되는가? -> 나의 생각 = responsePage.jsp에서 getAttribute() 하면 Object로 반환되는 바, 자료형 보존 안 됨
		request.setAttribute("gender", gender);
		request.setAttribute("city", city);
		request.setAttribute("height", height);
		request.setAttribute("foods", foods);
		
		request.setAttribute("msg", "성공적으로 추가했습니다");
		
		// 단계3b) 현재 작업 중인 doGet() 메소드에서 응답 페이지 만드는 과정을 JSP에 위임; 응답 페이지를 JSP에게 위임/떠넘기기 -> 위임 시 필요한 객체 = RequestDispatcher
		// 단계3b-1) request.getRequestDispatcher(jsp경로) -> 응답하고자 하는 view(JSP)를 선택하면서 객체 생성
		RequestDispatcher view = request.getRequestDispatcher("view/responsePage.jsp");
		// 단계3b-2) forward() 메소드 -> forwarding
		view.forward(request, response);
	} // doGet() 종료

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		System.out.println("doPost() 잘 호출되나요?");
		doGet(request, response); // doPost() 호출하면 doGet()을 호출하는 바, doGet()에서 작업하면 됨
	}

}
