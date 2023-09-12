package com.kh.controller; // src 폴더 = Java 소스코드 작성 + 패키지 구조 만들어줌

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class RequestGetServlet
 */
@WebServlet("/test1.do") // 이 annotation의 의미 = url mapping; 이 url 요청(이 들어오면)은 이 일은 내가 받아서 처리하겠다 -> 나의 질문 = mapping 값은 Servlet 클래스마다 달라야 하나요? -> 2022.1.11(화) 나의 생각 = 하나의 application 내에서는 달라야 하는 듯 vs 같은 mapping 값을 받으면 컴퓨터(?)는 어떤 것을 처리해야 할지 모름
public class RequestGetServlet extends HttpServlet { // 클래스 및 인터페이스 이름은 대문자로 시작 = coding convention -> Servlet의 이름 = RequestGetServlet(
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RequestGetServlet() { // 생성자
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		response.getWriter().append("Served at: ").append(request.getContextPath()); // 자동으로 생성된 이 부분 삭제하고 시작
		
		// get 방식 요청이 오면 이 메소드가 호출+실행됨
//		System.out.println("get 방식 요청이 오면 이 doGet() 메소드가 호출+실행됨 - 진짜 그런지 확인해 봐요");
		
		/* 2021.12.29(수) 17h30
		 * get 방식으로 요청했으면 doGet() 메소드가 호출됨
		 * 첫번째 매개변수 HttpServletRequest request에는 요청 시 전달된 내용들(사용자가 입력한 값 e.g. form 태그 key+value 세트, 요청 전송 방식(get/post), 요청한 사용자의 ip 주소 등)이 담김
		 * 두번째 매개변수 HttpServletResponse response는 요청 처리 후 응답을 할 때 사용하는 객체
		 * 
		 * 요청 처리 과정
		 * 단계1) 요청을 처리하기 위해, 요청 시 전달된/사용자가 입력한 값들(request의 parameter 영역 안에 존재하며, key(name 속성 값)-value(value 속성 값) 세트로 담겨있음)을 뽑음
		 * cf. request의 parameter 영역 = 보따리, map -> key 값으로 value 접근 가능
		 * request의 parameter 영역으로부터 전달된 데이터를 뽑는 메소드
		 * 1. request.getParameter("키 값") -> 그에 해당하는 value 값; 반환형 = String; 무조건 문자열 형으로 반환되기 때문에, 다른 자료형으로 변경하려면 파싱해야 함
		 * 2. request.getParameterValues("키 값") -> 그에 해당하는 value 값; 반환형 = 문자열 배열 String[]; 하나의 key 값으로 여러 개의 values를 받는 경우(e.g. checkbox) 문자열 배열형으로 반환 가능
		 * 단계2) 뽑아낸 값들을 가지고 (그냥 넘기거나 ou 가공해서) 요청 처리함(service -> DAO -> DB)
		 * 단계3) 처리 결과에 따른 성공/실패 페이지 응답 <- 요청 페이지(x) view에 보여줄 내용(o)을 돌려주는 것
		 */
		
		// 2021.12.30(목) 10h
		// 단계1)
		String name = request.getParameter("name"); // "강해피"(사용자가 정상적으로 값을 입력했을 경우) ou ""(텍스트 상자가 빈 경우 빈 문자열이 넘어감)
		String gender = request.getParameter("gender"); // "M" ou "F" ou null(라디오 버튼의 경우, 체크된 것이 없으면 null이 넘어감)
		int age = Integer.parseInt(request.getParameter("age")); // getParameter()에 의해 무조건 문자열로 넘어가기 때문에 숫자로 변환("4" -> 4) -> String은 참조자료형인데, int는 원시(primitive)자료형인 바, 형 변환 불가능 -> Wrapper 클래스로 parsing(Java wrapper 클래스 수업 때 배웠음)
		// 사용자가 정상적으로 값을 입력했을 경우 ou 나이 입력 안 해서 ""(빈 문자열을 숫자로 바꾸려고 하기 때문에 NumberFormatException 오류 발생) cf. NumberFormatException = runTime exception(코드 상에는 문제가 없는데, 사용자가 이상한 것을 입력해서 실행 도중 발생할 수 있는 -> Eclipse가 처리할 수 없는 바, 개발자가 알아서 처리해야 함 -> 2022.1.11(화) 나의 질문 = NumberFormatException은 어떻게 처리/대비해야 하는가?)
		
		String city = request.getParameter("city"); // "서울시"
		double height = Double.parseDouble(request.getParameter("height")); // Double 클래스의 parseDouble() 사용 -> double형 자료형으로  parsing e.g. 158.7
		String[] foods = request.getParameterValues("food"); // ["한식", "조식"] ou null(체크박스의 경우, 체크된 것이 하나도 없으면 null이 넘어감)
		
		// 변수에 잘 담겼는지 출력해보기; 단, 이렇게 console에 찍어보는 건 서버(역할 = 요청에 맞는 응답을 보내주는 것)에 무리가 많이 가기 때문에, 확인 후에는 주석 처리해두기
		/*
		System.out.println("name : " + name); // 이름 입력 안 하면 "" 출력됨
		System.out.println("gender : " + gender); // 성별 선택 안 하면 null 출력됨
		System.out.println("age : " + age); // 나이 입력 안 하면 브라우저 500 오류 발생(java.lang.NumberFormatException: For input string: "")
		System.out.println("city : " + city);
		System.out.println("height : " + height);
//		System.out.println("foods : " + foods); // 그냥 배열(참조자료형)명 'foods' 쓰면 배열의 주소가 출력됨([Ljava.lang.String;@6faae71e) vs Arrays.toString(foods); 배열이 빈 경우, 배열 주소 및 배열 내용물 null 출력됨
		if (foods == null) {
			System.out.println("foods : 없음");
		} else {
			System.out.println("foods : " + Arrays.toString(foods)); // 배열 안의 내용물을 확인할 수 있는 메소드
		}
		*/
		
		// 단계2) 이 뽑아낸 값들을 가지고 요청 처리(-> JDBC(DB와 상호작용))
		// JDBC에서 보통의 흐름 = controller단에서 service단의 메소드를 호출하며 값을 전달 -> DAO 호출 -> DB SQL문 실행 -> 결과 반환
		/*
		Person p = new Person(name, gender, age, city, height, foods);
		
		int result = new PersonService().insertPerson(p);
		
		if (result > 0) { // 데이터 추가 성공 시
			
		} else { // 실패 시
			
		}
		*/
		
		// 자주 보는 오류 -> 오류는 고치면 되므로 + 공부(문제가 왜 발생했고, 어떻게 해결하는지 정리/숙지) 기회가 되므로, 오류 발생을 담담하게 받아들이자(화/짜증 날 수는 있음)(o) 무서워하기(x)
		// 404: 파일이나 Servlet을 못 찾았을 때 발생 <- 경로가 잘못되었거나, 또는 파일명에 오타
		// 500: server단 Java 소스코드 상의 오류(예외 발생 등) e.g. null pointer, file(properties 파일 등) not found, sql exceptions.. 
		
		// 단계3) 응답 페이지(html로 작성된 문서) 넘기기; response 객체를 통해 사용자에게 html(응답 화면) 전달
		// JSP가 나오기 전(옛날에 사용하던 방식 = Servlet이 먼저 나왔기 때문에 Servlet만 이용) Java를 이용해서/Java 코드 안에 html 코드(+css, script도 가능)를 넣어서 응답 페이지 넘기기
		// 장점: Java 코드 내에서 작성하기 때문에, 반복문, 조건문, 유용한 Java 메소드들 활용 가능
		// 단점: 아주 복잡함 + 혹시라도 html을 수정하고자 할 때, Java 코드 내에서 수정이 이루어지기 때문에 수정된 내용을 다시 반영시키려면 서버를 restart(해당 어플리케이션을 사용하던 사람들의 사용도 한 번 끊김)해줘야 함 + 유지/보수 어려움
		// 좋은 코드 = business logic과 presentation logic(보여지는 것)의 분리 vs 이 방식은 분리 안 됨
		
		// 단계3a) 이제부터 내가 출력할 내용의 문서 형태는 html + 문자 set은 utf-8 사용
		response.setContentType("text/html; charset=UTF-8");
		
		// 단계3b) client와의 통로 생성 -> (내가) 응답(을 전송)하고자 하는 사용자/요청자와의 스트림 연결
		PrintWriter out = response.getWriter(); // 2022.1.11(화) 나의 질문 = PrintWriter가 무엇인가? -> Java의 정석 2권 p.894 byte 기반의 보조 stream 중 하나
		
		// 단계3c) 생성된 스트림을 통해 응답 html 구문을 1줄씩 출력
		out.println("<html>"); // 요청자에게 보내고자 하는 응답 페이지에 "" 문자열을 출력함 -> 2022.1.12(화) 나의 질문 = System.out.println/printf와 이 클래스 상 println/printf의 차이점은 무엇인가?(수업 시간에 강사님께서 설명해 주셨는데, 당시에 중요하게 듣지 못함 ㅠ.ㅠ)
		out.println("<head>");
		out.println("<style>");
		out.println("h2 {color:green}");
		out.println("#noname {color:lightgreen}");
		out.println("#name {color:limegreen}");
		out.println("#age {color:lime}");
		out.println("#city {color:forestgreen}");
		out.println("#height {color:yellowgreen}");
		out.println("#gender {color:greenyellow}");
		out.println("li {color:seagreen}");		
		out.println("</style>");
		out.println("</head>");
		out.println("<body>");
		
		out.println("<h2>개인 정보 응답 화면</h2>");
		
		/* xx 님은
		 * x살이며,
		 * xx에 삽니다.
		 * 키는 xx.xcm이고,
		 * 성별은 [case1 선택을 안 했습니다] ou [case2 남/여자입니다].
		 * 좋아하는 음식은 [case1 없습니다] ou [case2 xx, xx, ..]
		 * 사용자가 입력하는 값에 따라 내용이 달라지는 동적인 화면을 Servlet(Java 코드에 html 코드 넣음)에서 만들어서 응답해줌
		 */
		
		if (name == "") {
			out.println("<span id='noname'>이름을 안 적어주셔서 누구인지 모르겠어요 >.<</span>");
		} else {
			out.printf("<span id='name'>%s</span> 님은<br>", name);
			out.printf("<span id='age'>%d</span>살이며,<br>", age);
			out.printf("<span id='city'>%s</span>에 삽니다.<br>", city);
			out.printf("키는 <span id='height'>%.1f</span>cm이고,<br>", height); // default = 소수점 아래 6자리까지 표시
			
			out.print("성별은 ");
			if (gender == null) {
				out.println("선택을 안 했습니다.<br>");
			} else {
				if (gender.equals("M")) {
					out.println("<span id='gender'>남자</span>입니다.<br>");				
				} else {
					out.println("<span id='gender'>여자</span>입니다.<br>");	
				}
			} // else문 영역 끝
			
			out.print("좋아하는 음식은");
			if (foods == null) {
				out.println(" 없습니다.");
			} else {
				out.println("<ul>");				
				for (int i = 0; i < foods.length; i++) {
					out.printf("<li>%s</li>", foods[i]);
				}				
				out.println("</ul>");
			}			
		} // else문 영역 끝	
		
		out.println("</body>");
		out.println("</html>");
	} // doGet() 종료

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
